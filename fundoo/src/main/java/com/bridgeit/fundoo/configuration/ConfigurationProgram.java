package com.bridgeit.fundoo.configuration;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bridgeit.fundoo.dao.ILabelDao;
import com.bridgeit.fundoo.dao.INoteDao;
import com.bridgeit.fundoo.dao.IUserDao;
import com.bridgeit.fundoo.dao.LabelDao;
import com.bridgeit.fundoo.dao.NoteDao;
import com.bridgeit.fundoo.dao.UserDao;
import com.bridgeit.fundoo.service.ILabelService;
import com.bridgeit.fundoo.service.INoteService;
import com.bridgeit.fundoo.service.IUserService;
import com.bridgeit.fundoo.service.LabelServiceImplementation;
import com.bridgeit.fundoo.service.NoteServiceImplementation;
import com.bridgeit.fundoo.service.UserServiceImplementation;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.bridgeit.fundoo")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class ConfigurationProgram {
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";

	   private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";

	   private static final String PROPERTY_NAME_DATABASE_URL = "db.url";

	   private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";



	   private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";

	   private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

	   private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";



	   @Resource

	   private Environment env;



	   @Bean

	   public DataSource dataSource() {
		   System.out.println("data source");
		   //System.out.println("122");
	       DriverManagerDataSource dataSource = new DriverManagerDataSource();

	//System.out.println("6656");

	       dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
	       
	       dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
	       //System.out.println("raetgtawryer");
	       dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));

	       dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

	     //  System.out.println("hi");
	       System.out.println(dataSource);

	       return dataSource;

	   }



	  @Bean

	   public LocalSessionFactoryBean sessionFactory() {
		   System.out.println("session factory");

	       LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

	       sessionFactoryBean.setDataSource(dataSource());

	       
	       sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(

	PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

	       sessionFactoryBean.setHibernateProperties(hibProperties());

	       return sessionFactoryBean;

	   }



	  private Properties hibProperties() {
		   System.out.println("property");

	       Properties properties = new Properties();

	       properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));

	       properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
	       System.out.println(properties);
	       return properties; 

	   }



	   @Bean

	   public HibernateTransactionManager transactionManager() {
		   System.out.println("transaction");

	       HibernateTransactionManager transactionManager = new HibernateTransactionManager();

	       transactionManager.setSessionFactory(sessionFactory().getObject());

	       return transactionManager;

	   }
	   @Bean
	   public IUserService getIUserService()
	   {
		   System.out.println("service object");
		   return new UserServiceImplementation();
	   }
	   @Bean
	   public IUserDao getIUserDao()
	   {
		   System.out.println("user dao object");
		   return new UserDao();
	   }
	
	   @Bean
	   public String getKey()
	   {
		   return "ramana";
	   }
	   @Bean
	   public ILabelService getILabelService()
	   {
		   return new LabelServiceImplementation();
	   }
	   @Bean
	   public INoteService getINoteService()
	   {
		   return new NoteServiceImplementation();
	   }
	   @Bean
	   public INoteDao getINoteDao()
	   {
		   return new NoteDao();
	   }
	  

	   
}
