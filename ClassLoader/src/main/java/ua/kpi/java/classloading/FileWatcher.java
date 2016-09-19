package ua.kpi.java.classloading;

import com.google.common.util.concurrent.Uninterruptibles;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static ua.kpi.java.classloading.Constants.SRC;
import static ua.kpi.java.classloading.Constants.TARGET;

public class FileWatcher implements Runnable {
    public volatile boolean stop = false;
    private String className;
    private Long lastModified = 0L;

    public FileWatcher(String fileName) {
        this.className = fileName;
    }

    //TODO find better solution of searching file
    @Override
    public void run() {
        String srcName = className.replace(".", File.separator) + ".java";
        File file = new File(SRC +  File.separator + srcName);
        while (!stop) {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Path filePath = FileSystems.getDefault().getPath(file.getAbsolutePath());
            long lastModified = 0;
            try {
                lastModified = Files.getLastModifiedTime(filePath).toMillis();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
//            System.out.println("Last modified at: " + df.format(lastModified) );
            if (this.lastModified != lastModified) {
                this.lastModified = lastModified;
                compileClass(className);
            }

            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }

    }

    private void compileClass(String className) {
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        final DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        final String fileName = className.replace(".", File.separator) + ".java";
        final StandardJavaFileManager manager = compiler.getStandardFileManager(diagnostics, null, null);
        final File file = new File(SRC + File.separator + fileName);
        final Iterable<? extends JavaFileObject> sources = manager.getJavaFileObjectsFromFiles(Arrays.asList(file));
        final Iterable<String> options = Arrays.asList("-d", TARGET);
        final JavaCompiler.CompilationTask task = compiler.getTask(null, manager, diagnostics, options, null, sources);
        task.call();
    }
}
