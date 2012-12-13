package br.edu.unicapital.tccjsf.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;

public class JPAUtil {
	
	public static String getTableName(Class<?> entityClass) {
		String tableName = null;
		if (entityClass.getAnnotation(Table.class) != null) {
			Table table = entityClass.getAnnotation(Table.class);
			tableName = table.name();
			return tableName;
		}
		return entityClass.getSimpleName();
	}
	
	public static EntityManagerFactory getEntityManagerFactory(){
		return Persistence.createEntityManagerFactory("TccJsf");
	}
}
