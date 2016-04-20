/**
 *
 */
package works.tonny.apps.demo.onetomany;

import works.tonny.apps.EntityDAO;
import works.tonny.apps.mybatis.MybatisSupport;
import works.tonny.apps.support.ListSupport;

import java.util.List;

/**
 * @author 祥栋
 */
public interface ParentDAO extends EntityDAO<Parent> {

    @MybatisSupport()
    List<Parent> list(String parentName);

    @ListSupport()
    List<Parent> list();
}