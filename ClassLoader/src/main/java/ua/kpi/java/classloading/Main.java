package ua.kpi.java.classloading;


import static com.google.common.collect.ImmutableList.of;

public class Main {
    public static final String CLASS = "ua.kpi.java.classloading.TestModule";
    private boolean stop = false;

    public static void main(String[] args) throws Exception {
        new Main().runProgram();
    }

    private void runProgram() throws Exception {
        ChangeChecker changeChecker = new ChangeChecker(CLASS);
        new Thread(changeChecker).start();
        while (!stop) {
            ClassLoader loader = new RebelLoader(of("."));
            Class clazz = Class.forName(CLASS, true, loader);
            Object testModule = clazz.newInstance();
            System.out.println(testModule);

            try {
                Thread.sleep(6 * 1000);
            } catch (InterruptedException e) {
                System.err.println("The thread was interrupted");
                break;
            }
        }
        changeChecker.stop = true;
    }


}
