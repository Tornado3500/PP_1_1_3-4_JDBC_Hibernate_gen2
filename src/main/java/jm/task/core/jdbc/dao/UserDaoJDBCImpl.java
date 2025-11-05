package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_db_name";
    private static final String USERNAME = "your_user";
    private static final String PASSWORD = "your_password";

    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGSERIAL PRIMARY KEY," +
                    "username VARCHAR(50) NOT NULL UNIQUE," +
                    "email VARCHAR(100) NOT NULL UNIQUE," +
                    "password VARCHAR(100) NOT NULL)";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    @Override
    public void createUsersTable() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(CREATE_TABLE_SQL);
            System.out.println("Таблица успешно создана.");
        }
    }

    @Override
    public void dropUsersTable() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS users;");
            System.out.println("Таблица удалена.");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String insertSql = "INSERT INTO users(username, email, password) VALUES(?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, age);
            pstmt.executeUpdate();
            System.out.println("Пользователь сохранен.");
        }
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        String deleteSql = "DELETE FROM users WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {
            pstmt.setLong(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Пользователь с ID=" + id + " удален.");
            } else {
                System.out.println("Пользователь с указанным ID не найден.");
            }
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        String selectSql = "SELECT * FROM users";
        List<User> usersList = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSql)) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String username = rs.getString("username"); // Или "name"
                String email = rs.getString("email"); // Или "lastName"
                byte age = rs.getByte("age");
                User user = new User(id, username, email, age);
                usersList.add(user);
            }
        }
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("TRUNCATE TABLE users");
            System.out.println("Все записи удалены из таблицы.");
        } catch (SQLException ex) {
            ex.printStackTrace(); // Логирование или обработка ошибки
        }
    }

    public UserDaoJDBCImpl() { /* По желанию */ }

    public void closeConnection() {
    }
}
