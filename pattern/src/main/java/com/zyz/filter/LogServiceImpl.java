package com.zyz.filter;

public class LogServiceImpl implements InterceptorService {

    public Object before(TargetInvocation targetInvocation) {
        System.out.println("before");
        return null;
    }

    public Object interceptor(TargetInvocation targetInvocation) {
        this.before(targetInvocation);
        System.out.println("interceptor");
//        targetInvocation.
        this.after(targetInvocation);
        return null;
    }

    public Object after(TargetInvocation targetInvocation) {
        System.out.println("after");
        return null;
    }
}
