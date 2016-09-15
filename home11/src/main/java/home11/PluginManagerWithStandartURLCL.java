package home11;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to load Plugins
 *
 * @version 2.0
 * @autor Trotsenko Konstantin
 */
public class PluginManagerWithStandartURLCL {

    private final String pluginRootDirectory;
    private Map<String, Class<?>> cacheClass = new HashMap<>();

    public PluginManagerWithStandartURLCL(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    /**
     * Method to load plugins with URLClassLoader or search it in cash
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
        Class<?> clazz = cacheClass.get(pluginClassName);
        if (clazz!=null){
            System.out.println("From cash!");
        }
        if (clazz == null) {
            File file = new File(pluginRootDirectory + pluginName + "\\");
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            clazz = urlClassLoader.loadClass(pluginClassName);
            cacheClass.put(pluginClassName, clazz);
            System.out.println(clazz);
            System.out.println(cacheClass);
        }
        Object object = clazz.newInstance();
        Plugin plugin = (Plugin) object;
        plugin.doUself();
        return plugin;
    }
}
