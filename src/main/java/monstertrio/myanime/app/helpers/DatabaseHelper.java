package monstertrio.myanime.app.helpers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import monstertrio.myanime.app.controllers.AnimeAddController;
import monstertrio.myanime.app.controllers.AnimeListController;
import monstertrio.myanime.app.models.Anime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DatabaseHelper {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final String URL = "jdbc:sqlite:src/main/resources/database/myanime.sqlite";

    // Constructor to establish the connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String title, int userId, int page) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(DatabaseHelper.class.getResource(fxmlFile));
            root = loader.load();

            if (page == 3 || page == 4) {//AnimeList or Anime Add
                if (page == 3) {//anime list
                    AnimeListController animeListController = loader.getController();
                    String name = getName(userId);
                    animeListController.setUserInformation(userId, name);
                } else {
                    AnimeAddController animeAddController = loader.getController();
                    animeAddController.setUserInformation(userId);
                }
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            logger.error("An error occurred in \"Change Scene\":", e);
        }
    }

    /*********************************User Functions*********************************/

//Login User
    public int loginUser(String username, String password) throws SQLException {
        String query = "SELECT id, password FROM users WHERE username= ?";
        String hashedPassword = hashPassword(password);
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storedHashedPassword = resultSet.getString("password");
                assert hashedPassword != null;
                if (hashedPassword.equals(storedHashedPassword)) {
                    System.out.println("User Logged In Successfully. ");
                    return resultSet.getInt("id");
                } else {
                    System.out.println("Incorrect password.");
                }
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error logging in: " + e.getMessage());
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return -1;
    }

    private static String getName(int userId) {
        String name = "";
        Connection conn = null;
        String query = "SELECT name FROM users WHERE id=?";
        try {
            conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            System.out.println("Error getting name: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return name;
    }


    public int get_user_id(String username) {
        int user_id = -1;
        String query = "SELECT id FROM users WHERE username= ?";

        try(Connection conn = getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(query);
        ) {
            preparedStatement.setString(1,username);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                user_id=resultSet.getInt("id");
            }
        }catch (SQLException e) {
            logger.error("Error retrieving user ID for username '{}': {}", username, e.getMessage());

        }
        return user_id;
    }
    //add User
    public boolean signUpUser(String name, String username, String password) throws SQLException {
        // String query1="SELECT * FROM users WHERE username=?";
        String query = "INSERT INTO users(name, username, password) VALUES(?,?,?)";
        try (Connection conn = getConnection();
             //PreparedStatement psCheckAvail = conn.prepareStatement(query1);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            // Check if user already exists
            if (checkUsername(username, conn)) {
                String hashedPassword = hashPassword(password);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, hashedPassword);
                preparedStatement.executeUpdate();
                closeConnection(conn);
                return true;
            }
            closeConnection(conn);
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            // Handle the exception here (e.g., log the error, display a message to the user, etc.)
            throw e; // Re-throw the exception to propagate it to the calling code
        }
        return false;
    }

    public boolean changeUserPassword(int user_id, String username, String newPassword) throws SQLException {
        String query1 = "SELECT username from users where id=?";
        String query2 = "UPDATE users SET password=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query1);
             PreparedStatement preparedStatement1 = conn.prepareStatement(query2)) {
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedUsername = resultSet.getString("username");
                if (storedUsername.equals(username)) {
                    String newHashedPassword = hashPassword(newPassword);
                    preparedStatement1.setString(1, newHashedPassword);
                    preparedStatement1.setInt(2, user_id);
                    preparedStatement1.executeUpdate();
                    closeConnection(conn);
                    logger.info("Password updated successfully for user: {}", username);
                    return true;
                } else {
                    logger.warn("Username '{}' does not match stored username '{}'", username, storedUsername);
                }
            } else {
                logger.warn("No user found with ID: {}", user_id);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception occurred: {}", e.getMessage());
        }
        return false;
    }

    private boolean checkUsername(String username, Connection conn) {
        String query = "SELECT * FROM users WHERE username=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                return false;
            }
        } catch (SQLException e) {
            logger.error("An error occurred:", e);
        }
        return true;
    }

    public ObservableList<Anime> getAnimeListForUser(int userId) {
        ObservableList<Anime> animeList = FXCollections.observableArrayList();
        String query = "SELECT * FROM anime WHERE user_id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Anime anime = new Anime();
                anime.setId(resultSet.getInt("id"));
                anime.setTitle(resultSet.getString("title"));
                anime.setDesc(resultSet.getString("desc"));
                anime.setRating(resultSet.getInt("rating"));
                anime.setStatus(resultSet.getString("status"));
                anime.setGenre(resultSet.getString("genre"));
                anime.setImageUrl(resultSet.getString("image_url"));
                animeList.add(anime);
                System.out.println("Retrieved anime for the user: " + anime.getTitle());
            }
            closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animeList;
    }


    //addAnime
    public void addAnime(Anime anime) {
        String query = "INSERT INTO anime (title, desc, rating, status, genre, user_id, image_url) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, anime.getTitle());
            pstmt.setString(2, anime.getDesc());
            pstmt.setInt(3, anime.getRating());
            pstmt.setString(4, anime.getStatus());
            pstmt.setString(5, anime.getGenre());
            pstmt.setInt(6, anime.getUserId());
            pstmt.setString(7, anime.getImageUrl());

            pstmt.executeUpdate();

            System.out.println("Anime Successfully Added");
            closeConnection(conn);
        } catch (SQLException e) {
            System.out.println("Error adding anime: " + e.getMessage());
        }
    }


    public void deleteAnime(int animeId) {
        String query = "DELETE FROM anime WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, String.valueOf(animeId));
            pstmt.executeUpdate();
            closeConnection(conn);
        } catch (SQLException e) {
            System.out.println("Error in Deleting");
        }
    }


    private void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.error("Error occurred while accessing Database", e);
        }
    }


    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle hashing algorithm not found
            logger.error("Error occurred while hashing password", e);
            return null;
        }
    }
}
