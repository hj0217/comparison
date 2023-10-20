package com.demo1;


import com.demo1.mapper.TermDao;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@RequiredArgsConstructor
//@MapperScan(value ="com.demo1.mapper.", sqlSessionFactoryRef = "sqlSessionFactory")
@MapperScan(value ="com.demo1.mapper.daoInterface.", sqlSessionFactoryRef = "sqlSessionFactory")
public class SpringConfig extends HikariConfig {

    //private final DataSource dataSource;

//    @Bean
//    public TermService termService() {
//        return new TermService(termRepository());
//    }

//    @Bean
//    public JdbcTermRepository termRepository() {
//        return new JdbcTermRepository((HikariDataSource) dataSource());
//    }


    @Bean
    public TermDao termDao () {
        return new TermDao();
    }

    @Bean
    public DataSource dataSource() {
        System.out.println("10/20: 확인용 2");
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521/xe");
        hikariDataSource.setUsername("System");
        hikariDataSource.setPassword("1234");
        return hikariDataSource;
    }

    /*=====================================Mybatis를 연결하기 위한 클래스들======================================*/
    //데이터베이스 연결 및 SQL 실행 관리
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        System.out.println("10/20: 확인용 3");
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("com/demo1/mapper/TermMapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    //SQL 실행 및 트랜잭션 관리 => DAO implements Interface??
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        System.out.println("10/20: 확인용 4");
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
