package com.animes;

import com.animes.exception.DBException;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Log4j2
public class ConnectionFactory {
    private Connection connection = null;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/anime_store";
    private static final String USER = "root";
    private static final String PASSWD = "root";

    public static Connection getConnection()  {
        try {
            return DriverManager.getConnection(URL, USER, PASSWD);
        } catch (SQLException e) {
            log.error("Erro while create connection {}",e.getMessage());
            throw new DBException(e.getMessage());
        }
    }

}
