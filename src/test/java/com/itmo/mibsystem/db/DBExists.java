package com.itmo.mibsystem.db;

import com.itmo.mibsystem.ConfProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DBExists {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/mib";
    static final String USER = "mib_admin";
    static final String PASS = "password";
    static Connection connection = null;

    @BeforeAll
    public static void setup() throws IOException {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    @Test
    void checkMibUserExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_user", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibRoleExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_role", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibUserRoleExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_user_role", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibAlienRaceExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_alien_race", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibAlienPassportExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_alien_passport", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibSourceTechnologyExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_source_technology", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibTechnologyExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_technology", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibActDetentionsExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_act_detentions", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibNationExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_nation", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibEarthDocumentExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_earth_document", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibEmployeesExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_employees", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibDeliveryTypeExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_delivery_type", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibPaymentTypeExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_payment_type", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibTypesContractExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_types_contract", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibSellTechnologyDocumentExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_sell_technology_document", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibBuyTechnologyDocumentExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_buy_technology_document", null);
        assertTrue(rs.next());
    }

    @Test
    void checkMibDistributeTechnologyItemExists() throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_types_contract", null);
        assertTrue(rs.next());
    }
}
