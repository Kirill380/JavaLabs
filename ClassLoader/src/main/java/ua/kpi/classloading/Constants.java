package ua.kpi.classloading;


import com.google.common.base.Joiner;

import java.io.File;

public final class Constants {
    public static final String MODULE_NAME = "ClassLoader";
    public static final String SRC = Joiner.on(File.separator).join(MODULE_NAME, "src", "main", "java");
    public static final String TARGET = Joiner.on(File.separator).join(MODULE_NAME, "target", "classes");
}
