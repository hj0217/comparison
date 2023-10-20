package com.demo1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;


@SpringBootApplication
//@EnableJdbcRepositories(basePackages = "com.demo1.mapper") //mapper 인터페이스 경로 // Spring Data JDBC를 사용하여 데이터베이스와 상호작용 하는데 중점을 둔 데이터 액세스 프레임워크
//@MapperScan(value = "com.demo1.mapper", sqlSessionFactoryRef = "sqlSessionFactory") //SQL 매퍼와 관련된 설정을 지원하는 데이터 엑세스 프레임워크
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
		System.out.println("10/20: 확인용 1");

	}

}
