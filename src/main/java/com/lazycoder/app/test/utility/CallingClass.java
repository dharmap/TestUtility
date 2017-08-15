package com.lazycoder.app.test.utility;

public class CallingClass extends SecurityManager {
    public static final CallingClass INSTANCE = new CallingClass();

    public Class[] getCallingClasses() {
        return getClassContext();
    }
}