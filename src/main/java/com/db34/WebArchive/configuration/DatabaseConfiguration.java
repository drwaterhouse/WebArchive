package com.db34.WebArchive.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.db34.WebArchive.jdbc")
public class DatabaseConfiguration {

//    @Bean
//    public DataSource dataSource() {
//        DriverManager dataSource = new DriverManager();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/springjdbc");
//        dataSource.setUsername("guest_user");
//        dataSource.setPassword("guest_password");
// 
//        return dataSource;
//    }
}