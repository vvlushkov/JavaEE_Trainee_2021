package daoTests;

import com.solution.lushkov.dao.impl.RoleDaoImpl;
import com.solution.lushkov.dao.RoleDao;
import com.solution.lushkov.entity.Role;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class RoleDaoTest {
    RoleDao roleDao = new RoleDaoImpl();

    @Test
    public void roleCreateTest() throws Exception {
        String name = "Another Role";

        Role expectedRole = new Role();
        expectedRole.setRole_name(name);
        roleDao.create(expectedRole);

        Role roleFromDb = roleDao.findByName(name);
        assertThat(roleFromDb, equalTo(expectedRole));

        roleDao.remove(roleFromDb);
    }

    @Test
    public void roleReadByIdTest() throws Exception {
        String name = "Another Role";

        Role expectedRole = new Role();
        expectedRole.setRole_name(name);
        roleDao.create(expectedRole);

        Long id = roleDao.findByName(name).getRole_id();
        expectedRole.setRole_id(id);

        Role roleFromDb = roleDao.findById(id);
        assertThat(roleFromDb, equalTo(expectedRole));

        roleDao.remove(roleFromDb);
    }

    @Test
    public void roleReadByNameTest() throws Exception {
        String name = "Another Role";

        Role expectedRole = new Role();
        expectedRole.setRole_name(name);
        roleDao.create(expectedRole);
        Long id = roleDao.findByName(name).getRole_id();
        expectedRole.setRole_id(id);

        Role roleFromDb = roleDao.findByName(name);
        assertThat(roleFromDb, equalTo(expectedRole));

        roleDao.remove(roleFromDb);
    }

    @Test
    public void roleUpdateTest() throws Exception {
        String name = "Another Role";

        Role role = new Role();
        role.setRole_name(name);
        roleDao.create(role);
        Long id = roleDao.findByName(name).getRole_id();

        Role expectedRole = new Role(id, "Another Role #2");
        roleDao.update(expectedRole);

        Role actualRole = roleDao.findById(id);
        assertThat(actualRole, equalTo(expectedRole));

        roleDao.remove(actualRole);
    }

    @Test
    public void roleDeleteTest() throws Exception {
        String name = "Another Role";

        Role roleForDelete = new Role();
        roleForDelete.setRole_name(name);
        roleDao.create(roleForDelete);
        Long id = roleDao.findByName(name).getRole_id();

        roleDao.remove(roleForDelete);

        Role roleFromDb = roleDao.findById(id);
        assertThat(roleFromDb, equalTo(null));
    }
}
