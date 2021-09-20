import entityDaoPostgres.JdbcPostgresRoleDao;
import tables.Role;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class RoleDaoTest {

    private final JdbcPostgresRoleDao roleDao = new JdbcPostgresRoleDao();

    public RoleDaoTest() throws Exception {
    }

    //просто базовая проверка работостпособности методом
    @Test
    public void roleTest() throws Exception {
        List<Role> list = roleDao.findAll();
        assertEquals(2, list.size());
        assertEquals(false, list.isEmpty());
        assertEquals("Admin", list.get(0).getName());
        assertEquals("Customer", list.get(1).getName());
        assertEquals((Long)1L, list.get(0).getId());
        assertEquals((Long)2L, list.get(1).getId());
        Role role = roleDao.findById(1L);
        assertEquals("Admin", role.getName());
        role = roleDao.findByName("Customer");
        assertEquals((Long)2L, role.getId());
    }

}
