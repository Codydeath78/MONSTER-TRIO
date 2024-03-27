package monstertrio.myanime.app.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DatabaseHelper {
    private static final Logger logger = LoggerFactory.getLogger(ValidationHelper.class);
    private static final String URL = "jdbc:sqlite:myanime.sqlite";
    private Connection conn=null;

    // Constructor to establish the connection
    public DatabaseHelper() {
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            logger.error("Error occurred while accessing Database", e);
        }
    }

    //addAnime
    //updateAnime
    //deleteAnime
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.error("Error occurred while accessing Database", e);
        }
    }
    //addAnime
    //deleteAnime
    //updateAnime
}
