package com.itmo.mibsystem.db;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DBForeignKeyExists {
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
    public void checkKeyUserRoleExists() throws SQLException {
        String curTable = "mib_user_role";
        ResultSet rs = FindForeignKey(curTable);
        int countFK = 0;

        while (rs.next()) {
            if(rs.getString(3).equals("mib_user") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else if(rs.getString(3).equals("mib_role") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else {
                assertTrue(false);
            }
        }

        assertEquals(countFK, 2);
    }

    @Test
    public void checkKeyAlienPassportExists() throws SQLException {
        String curTable = "mib_alien_passport";
        ResultSet rs = FindForeignKey(curTable);
        int countFK = 0;
        while (rs.next()) {
            if(rs.getString(3).equals("mib_alien_race") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else {
                assertTrue(false);
            }
        }

        assertEquals(countFK, 1);
    }

    @Test
    public void checkKeyTechnologyExists() throws SQLException {
        String curTable = "mib_technology";
        ResultSet rs = FindForeignKey(curTable);
        int countFK = 0;
        while (rs.next()) {
            if(rs.getString(3).equals("mib_alien_race") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else    if(rs.getString(3).equals("mib_source_technology") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else {
                assertTrue(false);
            }
        }

        assertEquals(countFK, 2);
    }

    @Test
    public void checkKeyActDetentionsExists() throws SQLException {
        String curTable = "mib_act_detentions";
        ResultSet rs = FindForeignKey(curTable);
        int countFK = 0;
        while (rs.next()) {
            if(rs.getString(3).equals("mib_employees") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else    if(rs.getString(3).equals("mib_alien_passport") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else {
                assertTrue(false);
            }
        }

        assertEquals(countFK, 2);
    }

    @Test
    public void checkKeyEarthDocumentExists() throws SQLException {
        String curTable = "mib_earth_document";
        ResultSet rs = FindForeignKey(curTable);
        int countFK = 0;
        while (rs.next()) {
            if(rs.getString(3).equals("mib_nation") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else if(rs.getString(3).equals("mib_type_earth_documents") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else if(rs.getString(3).equals("mib_alien_passport") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else {
                assertTrue(false);
            }
        }

        assertEquals(countFK, 3);
    }

    @Test
    public void checkKeyEmployeesExists() throws SQLException {
        String curTable = "mib_employees";
        ResultSet rs = FindForeignKey(curTable);
        int countFK = 0;
        while (rs.next()) {
            if(rs.getString(3).equals("mib_employees") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else if(rs.getString(3).equals("mib_user") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else {
                assertTrue(false);
            }
        }

        assertEquals(countFK, 2);
    }

    @Test
    public void checkKeySellTechnologyDocumentExists() throws SQLException {
        String curTable = "mib_sell_technology_document";
        ResultSet rs = FindForeignKey(curTable);
        int countFK = 0;
        while (rs.next()) {
            if(rs.getString(3).equals("mib_technology") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else if(rs.getString(3).equals("mib_types_contract") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else if(rs.getString(3).equals("mib_alien_passport") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else {
                assertTrue(false);
            }
        }

        assertEquals(countFK, 3);
    }

    @Test
    public void checkKeyBuyTechnologyDocumentExists() throws SQLException {
        String curTable = "mib_buy_technology_document";
        ResultSet rs = FindForeignKey(curTable);
        int countFK = 0;
        while (rs.next()) {
            if(rs.getString(3).equals("mib_payment_type") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else if(rs.getString(3).equals("mib_delivery_type") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else if(rs.getString(3).equals("mib_technology") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else {
                assertTrue(false);
            }
        }

        assertEquals(countFK, 3);
    }

    @Test
    public void checkKeyDistributeTechnologyItemExists() throws SQLException {
        String curTable = "mib_distribute_technology_item";
        ResultSet rs = FindForeignKey(curTable);
        int countFK = 0;
        while (rs.next()) {
            if(rs.getString(3).equals("mib_technology") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else if(rs.getString(3).equals("mib_employees") && rs.getString(7).equals(curTable)) {
                countFK++;
            }
            else {
                assertTrue(false);
            }
        }

        assertEquals(countFK, 2);
    }

    ResultSet FindForeignKey(String table) throws SQLException {
        return Select(" SELECT" +
                "           tc.table_schema," +
                "           tc.constraint_name," +
                "           ccu.table_name AS foreign_table_name," +
                "           kcu.column_name," +
                "           ccu.column_name AS foreign_column_name," +
                "           ccu.table_schema AS foreign_table_schema," +
                "           tc.table_name" +
                "           FROM" +
                "           information_schema.table_constraints AS tc\n" +
                "           JOIN information_schema.key_column_usage AS kcu\n" +
                "           ON tc.constraint_name = kcu.constraint_name\n" +
                "           AND tc.table_schema = kcu.table_schema\n" +
                "           JOIN information_schema.constraint_column_usage AS ccu\n" +
                "           ON ccu.constraint_name = tc.constraint_name\n" +
                "           AND ccu.table_schema = tc.table_schema\n" +
                "          WHERE tc.constraint_type = 'FOREIGN KEY' AND tc.table_name= '" + table + "'");
    }

    ResultSet Select(String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }
}
