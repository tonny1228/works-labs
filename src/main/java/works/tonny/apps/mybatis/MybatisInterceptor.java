package works.tonny.apps.mybatis;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.ibatis.session.SqlSessionFactory;
import org.llama.library.log.Log;
import org.llama.library.log.LogFactory;
import org.llama.library.log.Logger;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.aop.IntroductionInterceptor;
import works.tonny.apps.support.BaseDAOSupport;
import works.tonny.apps.support.ListSupport;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Mybatis Interceptor 拦截mybatis支持dao中的方法，使用mybatis进行处理。
 * Created by Tonny on 2016/4/16.
 */
public class MybatisInterceptor implements IntroductionInterceptor {
    private static final Map<Class, MapperFactoryBean> MAPPED = new HashMap();
    private final SqlSessionFactory sqlSessionFactory;
    private Logger log = LogFactory.getLogger(MybatisInterceptor.class);

    public MybatisInterceptor(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override

    public boolean implementsInterface(Class<?> intf) {
        return intf.isInterface() && MapperFactoryBean.class.isAssignableFrom(intf);
    }

    /**
     * 拦截方法，如果支持mybatis则由mybatis spring框架处理，否则继续执行
     *
     * @param methodInvocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        MybatisSupport support = methodInvocation.getMethod().getAnnotation(MybatisSupport.class);

        if (support == null) {
            log.debug("mybatis not support:" + methodInvocation.getMethod());
            return methodInvocation.proceed();
        }
        Class aClass = methodInvocation.getMethod().getDeclaringClass();
        log.debug(aClass + ":" + methodInvocation.getMethod());
        if (!MAPPED.containsKey(aClass)) {
            MapperFactoryBean value = new MapperFactoryBean();
            value.setSqlSessionFactory(sqlSessionFactory);
            value.setMapperInterface(aClass);
            MAPPED.put(aClass, value);
        }


        Object executor = MAPPED.get(aClass).getObject();
        Method method = aClass.getDeclaredMethod(methodInvocation.getMethod().getName(), methodInvocation
                .getMethod().getParameterTypes());
        return method.invoke(executor, methodInvocation.getArguments());
    }
}
