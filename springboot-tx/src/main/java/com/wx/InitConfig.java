package com.wx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
@Slf4j
public class InitConfig implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("dataSource: {}", dataSource);
        Connection connection = dataSource.getConnection();
        log.info("connection: {}", connection);
        log.info("connection: {}", jdbcTemplate);
    }
}
