package com.zhaolw.zoo.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude =
		{DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Application {

	// 在需要停止的类注入
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		System.out.println("开始启动");
		context = SpringApplication.run(Application.class, args);
		System.out.println("启动完成");
		//context.close();
	}


}
