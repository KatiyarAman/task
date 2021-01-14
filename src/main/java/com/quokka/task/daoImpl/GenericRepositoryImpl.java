package com.quokka.task.daoImpl;


import com.google.gson.Gson;
import com.quokka.task.dao.GenericRepository;
import com.quokka.task.entity.Auditable;
import com.quokka.task.model.exception.EntityUpdateException;

import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.*;

public class GenericRepositoryImpl<T extends Auditable> implements GenericRepository<T> {

    private final static Logger log = LoggerFactory.getLogger(GenericRepositoryImpl.class);

    private Class<T> entityType;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private Map<String, Object> getDeleteQuery() {
        log.info("creating generic delete query");
        Map<String, Object> query = new HashMap<>();
        query.put("is_deleted", true);
        return query;
    }


    protected final Session getCurrentSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).withOptions().jdbcTimeZone(TimeZone.getTimeZone("UTC")).openSession();
    }

    @Override
    public void setEntity(Class<T> clazz) {
        this.entityType = clazz;
    }

    @Override
    public T get(int emp_Id) {
        log.info("Generic Query for getting entity by id '{}'", emp_Id);
        return get("emp_Id", emp_Id);
    }

    @Override
    public List<T> findAllByColumn(String column, Object value) {
        log.info("Generic Query for getting all entities by column '{}' and value '{}'", column, value);
        return getAll(column, value);
    }



    private Long countQuery(Session session, String column, Object value) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<T> root = criteria.from(entityType);
        criteria.select(builder.count(root)).where(builder.and(builder.isFalse(root.get("is_deleted")), builder.equal(root.get(column), value)));
        return session.createQuery(criteria).getSingleResult();
    }

    @Override
    public T findByColumn(String column, Object value) {
        log.info("Generic Query for getting an entity by column '{}' and value '{}'", column, value);
        return get(column, value);
    }

    @Override
    public T save(T object) {
        log.info("Generic Query for persisting an entity for '{}'", object);

        T entity = null;
        Transaction transaction = null;
        try (Session session = getCurrentSession()) {
            session.beginTransaction();
            session.persist(object);
            session.getTransaction().commit();
            log.info("Entity is persisted successfully");
            entity = object;
        } catch (Exception ex) {
            log.error("Entity can not be persisted due to this exception");
            rollBackTransaction(transaction);
            ex.printStackTrace();
        }
        return entity;
    }

    @Override
    public void setDeleted(int emp_Id) {
        log.info("Entity is going to set as deleted {}", emp_Id);

        Transaction transaction = null;
        try (Session session = getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createQuery(getUpdateCriteriaQuery(session, getDeleteQuery(), emp_Id));
            transaction.commit();
            log.info("Entity has been marked as deleted {}", emp_Id);
        } catch (Exception ex) {
            log.error("Entity can not be set as deleted due to exception");
            rollBackTransaction(transaction);
            ex.printStackTrace();
            throw new EntityUpdateException("Entity can not be set as deleted due to exception");
        }
    }

    @Override
    public void update(Map<String, Object> object, int emp_Id) {
        log.info("Entity is going to update by by generic update for these columns {}", new Gson().toJson(object));

        Transaction transaction = null;
        try (Session session = getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createQuery(getUpdateCriteriaQuery(session, object, emp_Id)).executeUpdate();
            transaction.commit();
            log.info("Entity has been updated successfully by generic update");
        } catch (Exception ex) {
            log.error("Entity can not be set as deleted due to exception");
            rollBackTransaction(transaction);
            ex.printStackTrace();
            throw new EntityUpdateException("Entity can not be set as deleted due to exception");
        }
    }

    @Override
    public List<T> list() {
        List<T> entities = new ArrayList<>();

        try (Session session = getCurrentSession()) {
            log.info("Getting list of entities for '{}'", entityType.toString());

            entities = produceListQuery(session).getResultList();
        } catch (Exception ex) {
            log.error("Entities did not get due to exception");
            ex.printStackTrace();
        }
        return entities;
    }

    //Descending sorting
    @Override
    public void sort(List<T> data, final String column) {
        data.sort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if ("dateCreated".equals(column)) {
                    return o1.getDateCreated().after(o2.getDateCreated()) ? -1 : 0;
                }
                return o1.getEmp_Id() > o2.getEmp_Id() ? -1 : 0;
            }
        });
    }

    @Override
    public List<T> list(int offset, int limit) {
        List<T> entities = new ArrayList<>();

        try (Session session = getCurrentSession()) {
            log.info("Getting list  of entities for '{}' with offset '{}' and limit '{}'", entityType.toString(), offset, limit);

            entities = produceListQuery(session).setFirstResult(offset).setMaxResults(limit).getResultList();
        } catch (Exception ex) {
            log.error("Entities did not get due to exception");
            ex.printStackTrace();
        }
        return entities;
    }

    private List<T> list_(Session session, int offset, int limit, String column, Object value) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityType);
        Root<T> root = criteria.from(entityType);
        criteria.select(root).where(builder.and(builder.isFalse(root.get("is_deleted")), builder.equal(root.get(column), value)));
        return session.createQuery(criteria).setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    @Override
    public int count() {
        int count = 0;

        try (Session session = getCurrentSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
            Root<T> root = criteria.from(entityType);
            criteria.select(builder.count(root.get("emp_Id"))).where(builder.isFalse(root.get("is_deleted")));
            count = session.createQuery(criteria).getSingleResult().intValue();
        } catch (Exception ex) {
            log.error("Error occurred while counting the records of this entity.");
            ex.printStackTrace();
        }
        return count;
    }

    private Query<T> produceListQuery(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityType);
        Root<T> root = criteria.from(entityType);
        criteria.select(root).where(builder.isFalse(root.get("is_deleted")));
        return session.createQuery(criteria);
    }

    private T get(String column, Object value) {
        log.info("Generic Query for column {} and value {}", column, value);
        try (Session session = getCurrentSession()) {
            return session.createQuery(getCriteriaQuery(session, column, value)).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for {} column due to this exception", column);
            return null;
        }
         
    }

    private List<T> getAll(String column, Object value) {
        log.info("Generic Query for column {} and value {}", column, value);
        try (Session session = getCurrentSession()) {
            return session.createQuery(getallCriteriaQuery(session, column, value)).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for list output of {} column due to this exception", column);
            return null;
        }
    }
       // get all
    protected CriteriaQuery<T> getallCriteriaQuery(Session session, String column, Object value) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityType);
        Root<T> entityRoot = criteriaQuery.from(entityType);

        criteriaQuery.select(entityRoot).where(
                builder.and(builder.equal(entityRoot.get(column), value)));

        log.info("search criteria query is created successfully");
        return criteriaQuery;
    }
    
    
    protected CriteriaQuery<T> getCriteriaQuery(Session session, String column, Object value) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityType);
        Root<T> entityRoot = criteriaQuery.from(entityType);

        criteriaQuery.select(entityRoot).where(
                builder.and(builder.equal(entityRoot.get(column), value), builder.equal(entityRoot.get("is_deleted"), false)));

        log.info("search criteria query is created successfully");
        return criteriaQuery;
    }

    private CriteriaUpdate<T> getUpdateCriteriaQuery(Session session, Map<String, Object> objectMap, int emp_Id) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<T> criteriaUpdate = builder.createCriteriaUpdate(entityType);
        Root<T> entityRoot = criteriaUpdate.from(entityType);

        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            criteriaUpdate.set(entry.getKey(), entry.getValue());
        }

        criteriaUpdate.where(builder.equal(entityRoot.get("emp_Id"), emp_Id));

        log.info("update criteria query is created successfully with id where clause");
        return criteriaUpdate;
    }

    protected void rollBackTransaction(Transaction transaction) {
        if (transaction != null && (transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK))
            transaction.rollback();

        log.info("transaction is rolled back successfully.");
    }

   //author aman-298
	@Override
	public T findByColumnAndId(String column, Object value, String col, Object val) {
		// TODO Auto-generated method stub
		log.info("Generic Query for getting an entity by column '{}' and value '{}' and col '{}' and val '{}' ", column, value,col,val);
		
		return get(column,value,col,val);
	}

	 private T get(String column, Object value,String col, Object val) {
	        log.info("Generic Query for column {} and value {}", column, value);
	        log.info("FindByColumnAndId :");
	        try (Session session = getCurrentSession()) {
	            return session.createQuery(getCriteriaQuery(session, column, value,col,val)).setMaxResults(1).uniqueResult();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            log.error("Generic Query can not be completed for {} column due to this exception", column);
	            return null;
	        }
	 }
	 protected CriteriaQuery<T> getCriteriaQuery(Session session, String column, Object value,String col, Object val) {
	        CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityType);
	        Root<T> entityRoot = criteriaQuery.from(entityType);

	        criteriaQuery.select(entityRoot).where(
	                builder.and(builder.equal(entityRoot.get(column), value),builder.equal(entityRoot.get(col), val)));

	        log.info("search criteria query is created successfully FindByColumnAndId :");
	        return criteriaQuery;
	    }
	 //author aman


	@Override
	public List<T> findByColuAndId(String column, Object value, String col, Object val) {
		// TODO Auto-generated method stub
		log.info("Generic Query for getting an entityList by column '{}' and value '{}' and col '{}' and val '{}' ", column, value,col,val);

		return getAll(column, value,col,val);
	}
	private List<T> getAll(String column, Object value,String col, Object val) {
        log.info("Generic Query for column {} and value {}", column, value);
        try (Session session = getCurrentSession()) {
            return session.createQuery(gettCriteriaQuery(session, column, value,col,val)).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for list output of {} column due to this exception", column);
            return null;
        }
	}
	
	 protected CriteriaQuery<T> gettCriteriaQuery(Session session, String column, Object value,String col, Object val) {
	        CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityType);
	        Root<T> entityRoot = criteriaQuery.from(entityType);

	        criteriaQuery.select(entityRoot).where(
	        		builder.and(builder.equal(entityRoot.get(column), value),builder.equal(entityRoot.get(col), val)));
	        log.info("search criteria query is created successfully");
	        return criteriaQuery;
	    }

// author aman
	@Override
	public T findByColumnIsDeleted(String column, Object value) {
		// TODO Auto-generated method stub
		return getIsDeleted(column,value);
	}
	private T getIsDeleted(String column, Object value) {
        log.info("Generic Query IsDeleted for column {} and value {}", column, value);
        try (Session session = getCurrentSession()) {
            return session.createQuery(getIsDeletedCriteriaQuery(session, column, value)).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query IsDeleted can not be completed for {} column due to this exception", column);
            return null;
        }
	}
        protected CriteriaQuery<T> getIsDeletedCriteriaQuery(Session session, String column, Object value) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(entityType);
            Root<T> entityRoot = criteriaQuery.from(entityType);

            criteriaQuery.select(entityRoot).where(
                    builder.and(builder.equal(entityRoot.get(column), value)));

            log.info("search criteria query IsDeleted is created successfully");
            return criteriaQuery;
        }
}
