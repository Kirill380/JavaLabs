package ua.kpi.java.classloading;


import com.google.common.util.concurrent.Uninterruptibles;

import java.util.concurrent.TimeUnit;

import static com.google.common.collect.ImmutableList.of;

public class Main {
    public static final String CLASS = "ua.kpi.java.classloading.TestModule";
    private boolean stop = false;

    public static void main(String[] args) throws Exception {
        new Main().runProgram();
    }

    private void runProgram() throws Exception {
        FileWatcher changeChecker = new FileWatcher(CLASS);
        new Thread(changeChecker).start();
        while (!stop) {
            ClassLoader loader = new RebelLoader(of("."));
            Class clazz = Class.forName(CLASS, true, loader);
            Object testModule = clazz.newInstance();
            System.out.println(testModule);
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        }
        changeChecker.stop = true;
    }


}
