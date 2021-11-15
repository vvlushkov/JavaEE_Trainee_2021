package daoTests;

import com.solution.lushkov.dao.impl.UserDaoImpl;
import com.solution.lushkov.dao.UserDao;
import com.solution.lushkov.entity.Role;
import com.solution.lushkov.entity.User;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void userCreateTest() throws Exception {
        String login = "userNew";
        String password = "password";
        String email = "userNew@email.com";
        String userFN = "User";
        String userLN = "User";
        String birthday = "2000-01-01";
        Role role = new Role(2L, "User");

        User expectedUser = new User(login, password, email,
                userFN, userLN, birthday, role);
        userDao.create(expectedUser);

        User userFromDb = userDao.findByLogin(login);
        assertThat(userFromDb, equalTo(expectedUser));

        userDao.remove(userFromDb);
    }

    @Test
    public void userReadByIdTest() throws Exception {
        String login = "userNew";
        String password = "password";
        String email = "userNew@email.com";
        String userFN = "User";
        String userLN = "User";
        String birthday = "2000-01-01";
        Role role = new Role(2L, "User");

        User expectedUser = new User(login, password, email,
                userFN, userLN, birthday, role);
        userDao.create(expectedUser);

        Long id = userDao.findByLogin(login).getUserId();
        expectedUser.setUserId(id);

        User userFromDb = userDao.findById(id);
        assertThat(userFromDb, equalTo(expectedUser));

        userDao.remove(userFromDb);
    }

    @Test
    public void userReadByLoginTest() throws Exception {
        String login = "userNew";
        String password = "password";
        String email = "userNew@email.com";
        String userFN = "User";
        String userLN = "User";
        String birthday = "2000-01-01";
        Role role = new Role(2L, "User");

        User expectedUser = new User(login, password, email,
                userFN, userLN, birthday, role);
        userDao.create(expectedUser);

        Long id = userDao.findByLogin(login).getUserId();
        expectedUser.setUserId(id);

        User userFromDb = userDao.findByLogin(login);
        assertThat(userFromDb, equalTo(expectedUser));

        userDao.remove(userFromDb);
    }

    @Test
    public void userReadByEmailTest() throws Exception {
        String login = "userNew";
        String password = "password";
        String email = "userNew@email.com";
        String userFN = "User";
        String userLN = "User";
        String birthday = "2000-01-01";
        Role role = new Role(2L, "User");

        User expectedUser = new User(login, password, email,
                userFN, userLN, birthday, role);
        userDao.create(expectedUser);

        Long id = userDao.findByLogin(login).getUserId();
        expectedUser.setUserId(id);

        User userFromDb = userDao.findByEmail(email);
        assertThat(userFromDb, equalTo(expectedUser));

        userDao.remove(userFromDb);
    }

    @Test
    public void userUpdateTest() throws Exception {
        String login = "userNew";
        String password = "password";
        String email = "userNew@email.com";
        String userFN = "User";
        String userLN = "User";
        String birthday = "2000-01-01";
        Role role = new Role(2L, "User");

        User user = new User(login, password, email,
                userFN, userLN, birthday, role);
        userDao.create(user);

        Long id = userDao.findByLogin(login).getUserId();
        user.setUserId(id);

        User expectedUser = new User(id, login, "updatedPass",
                "updated@email.com", userFN, "newUserLN",
                birthday, role);
        userDao.update(expectedUser);

        User actualUser = userDao.findById(id);
        assertThat(actualUser, equalTo(expectedUser));

        userDao.remove(actualUser);
    }

    @Test
    public void userDeleteTest() throws Exception {
        String login = "userNew";
        String password = "password";
        String email = "userNew@email.com";
        String userFN = "User";
        String userLN = "User";
        String birthday = "2000-01-01";
        Role role = new Role(2L, "User");
        User userForDelete = new User(login, password, email,
                userFN, userLN, birthday, role);
        userDao.create(userForDelete);
        Long id = userDao.findByLogin(login).getUserId();
        userForDelete.setUserId(id);

        userDao.remove(userForDelete);

        User userFromDb = userDao.findById(id);
        assertThat(userFromDb, equalTo(null));
    }
}
