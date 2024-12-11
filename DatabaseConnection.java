import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/onlinebanking"; // URL zur Datenbank
    private static final String USER = "root"; // Datenbankbenutzername
    private static final String PASSWORD = ""; // Datenbankpasswort

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}