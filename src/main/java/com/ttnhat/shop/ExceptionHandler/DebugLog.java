package com.ttnhat.shop.ExceptionHandler;

import java.util.Arrays;
import java.util.List;

public class DebugLog {
    private Exception ex;

    public DebugLog(Exception ex) {
        this.ex = ex;
    }

    public String getLog(){
        StackTraceElement stackTraceElements = this.ex.getStackTrace()[0];
        List<String> list = Arrays.asList(stackTraceElements.getClassName(), stackTraceElements.getMethodName(),
                ":line : " + (Integer.toString(stackTraceElements.getLineNumber())));
        String s = String.join("/", list);
        return s;
    }
}
