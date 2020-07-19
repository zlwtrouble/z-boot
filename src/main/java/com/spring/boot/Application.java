package com.spring.boot;

import com.spring.boot.common.utils.SpringContextExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude =
		{DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Application {

	// 在需要停止的类注入
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(Application.class, args);

		context.close();
	}


}
