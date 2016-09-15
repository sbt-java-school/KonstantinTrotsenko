package home11;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to load Plugins
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class PluginManager {

    private final String pluginRootDirectory;
    private static Map<String, Class<?>> cacheClass = new HashMap<>();

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    /**
     * Method to load plugins with MyURLClassLoader
     * @param pluginName plugin name
     * @param pluginClassName plugin class name
     * @return plugin object
     * @throws MalformedURLException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {
        File file = new File(pluginRootDirectory + pluginName + "\\");
        URL url = file.toURI().toURL();
        URL[] urls = new URL[]{url};
        URLClassLoader urlClassLoader = new MyURLClassLoader(urls);
        Class clazz = urlClassLoader.loadClass(pluginClassName);
        System.out.println(clazz);
        Object object = clazz.newInstance();
        Plugin plugin = (Plugin) object;
        plugin.doUself();
        return plugin;
    }

    private static class MyURLClassLoader extends URLClassLoader {

        public MyURLClassLoader(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }

        public MyURLClassLoader(URL[] urls) {
            super(urls);
        }

        public MyURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
            super(urls, parent, factory);
        }

        /**
         * Method to searh class in cash or load class
         * @param name class name
         * @return class object
         * @throws ClassNotFoundException
         */
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            Class<?> result = cacheClass.get(name);
            if (result != null) {
                System.out.println("From cash!");
            }
            if (result == null) {
                result = super.loadClass(name);
                cacheClass.put(name, result);
            }
            return result;
        }
    }
}
