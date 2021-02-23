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
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_user'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibRoleExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_role'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibUserRoleExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_user_role'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibAlienRaceExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_alien_race'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibAlienPassportExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_alien_passport'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibSourceTechnologyExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_source_technology'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibTechnologyExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_technology'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibActDetentionsExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_act_detentions'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibNationExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_nation'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibEarthDocumentExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_earth_document'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibEmployeesExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_employees'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibDeliveryTypeExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_delivery_type'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibPaymentTypeExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_payment_type'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibTypesContractExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_types_contract'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibSellTechnologyDocumentExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_sell_technology_document'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibBuyTechnologyDocumentExists() throws SQLException {
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_buy_technology_document'");
        assertTrue(rs.next());
    }

    @Test
    void checkMibDistributeTechnologyItemExists() throws SQLException {
        /*
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "mib_types_contract", null);
        assertTrue(rs.next());
*/
        ResultSet rs = Select("SELECT * FROM pg_catalog.pg_tables WHERE schemaname = 'public' AND tablename = 'mib_types_contract'");
        assertTrue(rs.next());
    }

    ResultSet Select(String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }
}
