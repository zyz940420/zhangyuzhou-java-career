package com.zyz.filter;

public interface InterceptorService {
    /**
     *
     * @param targetInvocation
     * @return
     */
    Object before(TargetInvocation targetInvocation);

    /**
     *
     * @param targetInvocation
     * @return
     */
    Object interceptor(TargetInvocation targetInvocation);

    /**
     *
     * @param targetInvocation
     * @return
     */
    Object after(TargetInvocation targetInvocation);
}
