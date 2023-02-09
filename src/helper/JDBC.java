package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBC {

    /** String of protocal */
    private static final String protocol = "jdbc";
    /** String of vendor */
    private static final String vendor = ":mysql:";
    /** String of location */
    private static final String location = "//localhost/";
    /** String of database name */
    private static final String databaseName = "client_schedule";
    /** String of full jdbc url */
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    /** String of driver */
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    /** String of username */
    private static final String userName = "sqlUser";
    /** String of password */
    private static String password = "Passw0rd!";
    /** Connection interface */
    public static Connection connection;

    /**
     * Opens connection to database
     */
    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * closes connection to database
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
