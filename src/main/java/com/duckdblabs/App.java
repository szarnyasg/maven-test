package com.duckdblabs;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.duckdb.DuckDBConnection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        DuckDBConnection conn = (DuckDBConnection) DriverManager.getConnection("jdbc:duckdb:new.duckdb");
        Statement stmt = conn.createStatement();

        var y = stmt.executeQuery("SELECT MAX_BY(employee_id, salary) as employee_with_biggest_salary FROM employees;\n");
        while (y.next()) {
            System.out.println(y.getDouble(1));
        }


        var x = stmt.executeQuery("SELECT MIN_BY(employee_id, salary) as employee_with_least_salary FROM employees;\n");
        while (x.next()) {
            System.out.println(x.getDouble(1));
        }
        stmt.close();
    }
}
