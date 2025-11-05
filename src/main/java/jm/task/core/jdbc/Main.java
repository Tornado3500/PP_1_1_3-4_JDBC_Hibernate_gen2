package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // реализуйте алгоритм здесь
        UserDao dao = new UserDaoJDBCImpl();
        UserService service = new UserServiceImpl(dao);

        String name1 = "Фёдор";
        service.saveUser(name1, "Иванов", (byte) 25);
        System.out.println("User с именем — " + name1 + " добавлен в базу данных");

        String name2 = "Мария";
        service.saveUser(name2, "Петрова", (byte) 30);
        System.out.println("User с именем — " + name2 + " добавлен в базу данных");

        String name3 = "Алексей";
        service.saveUser(name3, "Сидоров", (byte) 22);
        System.out.println("User с именем — " + name3 + " добавлен в базу данных");

        String name4 = "Елена";
        service.saveUser(name4, "Сыкухина", (byte) 28);
        System.out.println("User с именем — " + name4 + " добавлен в базу данных");

        // Получение всех пользователей из базы и вывод в консоль
        List<User> users = service.getAllUsers();
        System.out.println("\n=== Все пользователи в базе данных ===");
        for (User user : users) {
            System.out.println(user); // Будет автоматически вызываться toString()
        }

        service.cleanUsersTable();
        service.dropUsersTable();
        service.close();
    }
}


