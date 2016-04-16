package works.tonny.apps.mybatis;

import org.aopalliance.aop.Advice;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

/**
 * Created by Tonny on 2016/4/16.
 */
public class MybatisAdvisor extends DefaultIntroductionAdvisor {
    public MybatisAdvisor() {
        super(new MybatisInterceptor());
    }
}
