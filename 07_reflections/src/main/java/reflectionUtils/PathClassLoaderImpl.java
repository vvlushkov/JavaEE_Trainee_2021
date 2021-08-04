package reflectionUtils;

import com.nixsolutions.ppp.reflection.PathClassLoader;

import java.nio.file.Path;

/**
 * Class that contains one method to load (find) class
 * by the name from specified directory.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class PathClassLoaderImpl implements PathClassLoader {
    /** Field of {@code path} */
    private Path path;

    /** Method to get {@code path} */
    @Override
    public Path getPath() {
        return path;
    }

    /** Method to set {@code path} */
    @Override
    public void setPath(Path path) {
        this.path = path;
    }

    /**
     * !!!!!! НЕ СДЕЛАН !!!!!!
     *
     * This method load (find) class
     * by the name from specified directory.
     *
     * @param   name
     *          name of class that need find.
     * @return  Object of found class.
     * @throws  ClassNotFoundException
     *          If needed class does not exist in said path
     *          or name of needed class are incorrect.
     */
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        return null;
    }
}
