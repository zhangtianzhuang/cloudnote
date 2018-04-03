package com.ztz.factory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.FactoryBean;

public class LocalSessionFactory implements FactoryBean<SessionFactory>{

	@Override
	public SessionFactory getObject() throws Exception {
//		Configuration configuration = new Configuration().configure();
//		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//				.applySettings(configuration.getProperties()).build();
//		SessionFactory sessionFactory = configuration.configure()
//				.buildSessionFactory(serviceRegistry);
//		return sessionFactory;
		Configuration cfg= new Configuration();
		cfg.configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		return sessionFactory;
	}

	@Override
	public Class<?> getObjectType() {
		return SessionFactory.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
