package ru.rtln.reportservice.aop.pointcut;

public class Pointcut {

    @org.aspectj.lang.annotation.Pointcut("within(ru.rtln.reportservice.*)")
    public void isServiceLayer() {
    }

    @org.aspectj.lang.annotation.Pointcut("isServiceLayer() && @annotation(ru.rtln.reportservice.aop.annotation.Loggable)")
    public void callLoggableServiceLayerMethod() {
    }
}
