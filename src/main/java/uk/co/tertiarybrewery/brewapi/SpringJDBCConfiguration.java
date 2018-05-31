package uk.co.tertiarybrewery.brewapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringJDBCConfiguration {
    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/brewcontroller?verifyServerCertificate=false&useSSL=false&requireSSL=false");
        dataSource.setUsername("brewcontroller");
        dataSource.setPassword("swejfnsFe8q");


        return dataSource;
    }
}
