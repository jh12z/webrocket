package webrocket.util;

/**
 * Class util.
 */
public class ClassUtil {

	/**
	 * Creates a new instance of the given class.
	 *
	 * @param className The class name.
	 * @return The instance created.
	 */
	public static Object newInstance(String className) {
		Class clazz = loadClass(className);
		return newInstance(clazz);
	}

	/**
	 * Creates a new instance of the given class.
	 *
	 * @param clazz The class object.
	 * @param <T> The instance type.
	 * @return The instance created.
	 */
	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("Cannot instantiate class " + clazz, e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Cannot access class " + clazz, e);
		}
	}

	/**
	 * Loads the given class.
	 * Tries to load the class using the context class loader first and then the class loader that loaded this class.
	 *
	 * @param className The class name.
	 * @return The class object.
	 */
	public static Class loadClass(String className) {
		try {
			return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
		} catch (ClassNotFoundException e) {
			try {
				return Class.forName(className, true, ClassUtil.class.getClassLoader());
			} catch (ClassNotFoundException e2) {
				throw new RuntimeException("Class " + className + " not found.", e2);
			}
		}
	}
}
