package monstertrio.myanime.app.helpers;

import monstertrio.myanime.app.models.Anime;
import monstertrio.myanime.app.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DatabaseHelper {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);
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

    //add User
    public void addUser(User user) throws SQLException{
        //check if user already exists
        PreparedStatement psCheckAvail=conn.prepareStatement("SELECT * FROM users WHERE username=?");
        psCheckAvail.setString(1,user.getUsername());
        ResultSet resultSet = psCheckAvail.executeQuery();
        if(resultSet.isBeforeFirst()){
            System.out.println("User already exists!");
            //javafx alert
        } else{
        String query="INSERT INTO users(name, username, password) VALUES(?,?,?)";
        PreparedStatement psInsert = conn.prepareStatement(query);
        psInsert.setString(1, user.getName());
        psInsert.setString(2,user.getUsername());
        psInsert.setString(3, user.getPassword());
        psInsert.executeUpdate();
        }
    }
    //addAnime
    public void addAnime(Anime anime){

    }
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
