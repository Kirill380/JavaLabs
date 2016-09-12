package ua.kpi.java.classloading;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class ChangeChecker implements Runnable {
    public static final String SRC = ".\\ClassLoader\\src\\main\\java\\";
    public static final String TARGET = ".\\ClassLoader\\target\\classes\\";
    public volatile boolean stop = false;
    private String fileName;
    private Long prevTime = 0L;

    public ChangeChecker(String fileName) {
        this.fileName = fileName;
    }

    //TODO find better solution of searching file
    @Override
    public void run() {
        while (!stop) {
            String srcName = fileName.replace('.', '\\') + ".java";
            Path file = Paths.get(SRC + srcName);
            BasicFileAttributes attrs = null;
            try {
                attrs = Files.readAttributes(file, BasicFileAttributes.class);
            } catch (IOException e) {
                System.err.println("Error" + e.getMessage());
            }
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//            System.out.println("Previous time: " + df.format(prevTime));
//            System.out.println("Last accessed at:" + df.format(attrs.lastAccessTime().toMillis()));
            if (prevTime < attrs.lastAccessTime().toMillis()) {
                prevTime = attrs.lastAccessTime().toMillis();
                try {
                    Runtime.getRuntime().exec("javac -d .." + TARGET + " ." + SRC + srcName);
                } catch (IOException e) {
                    System.err.println("Error while executing commonfd line command: " + e.getMessage());
                }
            }
        }

    }
}
