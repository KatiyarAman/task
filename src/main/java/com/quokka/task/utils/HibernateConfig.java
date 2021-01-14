//package  com.quokka.task.utils;
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import com.quokka.task.config.AppPropertiesConfig;
//
//
//
//
//
//
//@Configuration
//@EnableTransactionManagement
//@EnableAutoConfiguration
//@ComponentScan({"com.quokka.task"})
//@Component
//public class HibernateConfig {
//	
//	AppPropertiesConfig appProperties = new AppPropertiesConfig();
//	Properties properties = appProperties.getPropValues();
//	String url = properties.getProperty("spring.datasource.url");
//	String driver = properties.getProperty("spring.datasource.driver");
//	String userName = properties.getProperty("spring.datasource.username");
//	String password = properties.getProperty("spring.datasource.password");
//	String beanData = properties.getProperty("springBeanData");
//	
////	@Bean
////    public LocalSessionFactoryBean sessionFactory() {
////        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
////        sessionFactory.setDataSource(dataSource());
////         sessionFactory.setPackagesToScan(new String[] { beanData });
////        sessionFactory.setHibernateProperties(hibernateProperties());
////        return sessionFactory;
////     }
//	
//	 @Bean
//	    public DataSource dataSource() {
//	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	        dataSource.setDriverClassName(driver);
//	        dataSource.setUrl(url);
//	        dataSource.setUsername(userName);
//	        dataSource.setPassword(password);
//	        return dataSource;
//	    }
//	 
//	 private Properties hibernateProperties() {
//	        Properties properties = new Properties();
//	        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
//	        properties.put("hibernate.show_sql", "true");
//	        properties.put("hibernate.event.merge.entity_copy_observer", "allow");
//            properties.put("hibernate.hbm2ddl.auto", "update");
//	        return properties;        
//	    }
//	 
////	 @Bean
////	    @Autowired
////	    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
////	    {
////	        HibernateTransactionManager htm = new HibernateTransactionManager();
////	        htm.setSessionFactory(sessionFactory);
////	        return htm;
////	    }
//	 
//}