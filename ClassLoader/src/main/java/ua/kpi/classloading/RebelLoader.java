package ua.kpi.classloading;


import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RebelLoader extends ClassLoader {

    private Map<String, Class<?>> classesHash = new HashMap<>();

    private final List<String> classPath;

    public RebelLoader(List<String> classPath) {
        this.classPath = new ArrayList<>(classPath);
    }


    // override to bypass standard/parent loader because class located in standard directory CLASSPATH
    @Override
    public synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class result = findClass(name);
        if (resolve) {
            resolveClass(result);
        }

        return result;
    }


    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
        Class result = (Class) classesHash.get(name);

        if (result != null) {
            System.out.println("Class " + name + " found in cache");
            return result;
        }

        File f = findFile(Constants.TARGET + File.separator + name.replace(".", File.separator), ".class");
        System.out.println("Class " + name + (f == null ? "" : " found in " + f));

        if (f == null) {
            return findSystemClass(name); // call system class loader
        }

        try {
            byte[] classBytes = Files.toByteArray(f);
            result = defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Cannot load class " + name + ": " + e);
        } catch (ClassFormatError e) {
            throw new ClassNotFoundException("Format of class file incorrect for class " + name + ": " + e);
        }

        classesHash.put(name, result);
        return result;
    }

    @Override
    protected URL findResource(String name) {
        File f = findFile(name, "");
        if (f == null) return null;

        try {
            return f.toURI().toURL();
        } catch (java.net.MalformedURLException e) {
            return null;
        }

    }

    private File findFile(String fileName, String extension) {
        File f = new File(fileName + extension);

        if (f.exists()) {
            return f;
        }

        return null;
    }

}
