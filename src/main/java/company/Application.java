package main.java.company;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;


/**
 * Created by movses on 2/18/16.
 */
@SpringBootApplication
//@EnableAutoConfiguration(exclude={HibernateJpaAutoConfiguration.class})
public class Application {

//    @Autowired
//    DataSource dataSource;

    public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
    }
}
