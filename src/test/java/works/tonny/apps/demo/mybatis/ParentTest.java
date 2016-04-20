package works.tonny.apps.demo.mybatis;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import works.tonny.apps.demo.onetomany.Parent;
import works.tonny.apps.demo.onetomany.ParentDAO;
import works.tonny.apps.mybatis.PaDAO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Tonny on 2016/4/16.
 */
@ContextConfiguration(locations = {"classpath:config/applicationContext-basic.xml",
        "classpath*:config/applicationContext-*mysql.xml", "classpath*:/config/applicationContext-mod-*.xml"})
public class ParentTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
//    @Qualifier("paDAO")
    private ParentDAO parentDAO;

//    @Autowired
//    private PaDAO paDAO;

    @Test
    public void testOtm() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        System.out.println(paDAO);
        List<Parent> list = parentDAO.list("");
        for (Parent parent : list) {
            System.out.println(parent);
            System.out.println(parent.getChildren());
        }

        List<Parent> list2 = parentDAO.list();
        for (Parent parent : list2) {
            System.out.println(parent);
            System.out.println(parent.getChildren());
        }
//        System.out.println(list);
    }

}
