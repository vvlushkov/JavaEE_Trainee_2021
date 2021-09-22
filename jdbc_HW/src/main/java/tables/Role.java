package tables;

/**
 * Class for table of Roles.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class Role {
    /** Role`s ID */
    private Long id;
    /** Role`s name */
    private String name;

    /**
     * Basic empty constructor.
     */
    public Role() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
