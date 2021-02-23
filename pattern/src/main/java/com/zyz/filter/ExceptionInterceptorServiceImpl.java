package com.zyz.filter;

public class ExceptionInterceptorServiceImpl implements InterceptorService {

    public Object before(TargetInvocation targetInvocation) {
        System.err.println("before");
        return null;
    }

    public Object interceptor(TargetInvocation targetInvocation) {
        this.before(targetInvocation);

        return null;
    }

    public Object after(TargetInvocation targetInvocation) {
        return null;
    }
}
