package works.tonny.apps.mybatis;

import works.tonny.apps.demo.onetomany.Parent;

import java.util.List;

/**
 * Created by Tonny on 2016/4/16.
 */
public interface PaDAO {
    @MybatisSupport()
    List<Parent> list(String parentName);

}
