package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {


    private final UserDao dao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    // Остальные методы реализации интерфейса UserService...

    /**
     * Закрытие соединения с базой данных или освобождение ресурсов.
     */
    @Override
    public void close() {
        // Здесь должна происходить очистка используемых ресурсов
        // Например, закрытие открытого соединения с базой данных
        ((UserDaoJDBCImpl)dao).closeConnection();
    }
    public void createUsersTable() {

    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }

}
