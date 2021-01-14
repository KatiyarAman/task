package com.quokka.task.dao;


import org.hibernate.Interceptor;

import com.quokka.task.entity.Auditable;

import java.util.List;
import java.util.Map;

public interface GenericRepository<T extends Auditable> {

    public void setEntity(Class<T> clazz);

   // public T get(Long id);

    public List<T> findAllByColumn(String column, Object value);

    

    public T findByColumn(String column, Object value);
    public T findByColumnIsDeleted(String column,Object value);
    
    public T findByColumnAndId(String column, Object value,String col, Object val);
    public List<T> findByColuAndId(String column, Object value,String col, Object val);

    //public T save(T object, Interceptor interceptor);

   // public void setDeleted(Long id);

   // public void update(Map<String, Object> object, Long id);

    public List<T> list();

    public void sort(List<T> data, String column);

    public List<T> list(int offset, int limit);

    public int count();

	public T save(T object);

	public void setDeleted(int emp_Id);

	void update(Map<String, Object> object, int emp_Id);

	public T get(int emp_Id);
}
