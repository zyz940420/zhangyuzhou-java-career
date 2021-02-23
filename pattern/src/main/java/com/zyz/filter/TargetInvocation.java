package com.zyz.filter;

import java.util.List;

public class TargetInvocation {

    private static List<InterceptorService> interceptorServiceList;

    private static TargetService targetService;

    public void addInterceptor(InterceptorService interceptorService){
        interceptorServiceList.add(interceptorService);
    }

    public void invoke(){
//        interceptorService.interceptor(targetService);
    }
}
