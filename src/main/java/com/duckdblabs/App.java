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
        DuckDBConnection conn = (DuckDBConnection) DriverManager.getConnection("jdbc:duckdb:");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE OR REPLACE TABLE employees(employee_id NUMERIC, department_id NUMERIC, salary NUMERIC);");
        stmt.execute("INSERT INTO employees VALUES\n" +
                "  (1001, 10, 10000),\n" +
                "  (1020, 10, 9000),\n" +
                "  (1030, 10, 8000),\n" +
                "  (900, 20, 15000),\n" +
                "  (2000, 20, NULL),\n" +
                "  (2010, 20, 15000),\n" +
                "  (2020, 20, 8000);");
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
