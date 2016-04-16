package works.tonny.apps.mybatis;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;
import works.tonny.apps.support.BaseDAOSupport;
import works.tonny.apps.support.ListSupport;

/**
 * Created by Tonny on 2016/4/16.
 */
public class MybatisInterceptor  implements IntroductionInterceptor {
    @Override
    public boolean implementsInterface(Class<?> intf) {
        return intf.isInterface() && BaseDAOSupport.class.isAssignableFrom(intf);
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        BaseDAOSupport<?> executor = (BaseDAOSupport<?>) methodInvocation.getThis();
        Class returnType = methodInvocation.getMethod().getReturnType();
        MybatisSupport support = methodInvocation.getMethod().getAnnotation(MybatisSupport.class);


        return null;
    }
}
