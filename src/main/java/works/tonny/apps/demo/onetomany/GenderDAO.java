/**
 * 
 */
package works.tonny.apps.demo.onetomany;

import java.util.List;

import works.tonny.apps.EntityDAO;
import works.tonny.apps.support.ListSupport;

/**
 * @author 祥栋
 */
public interface GenderDAO extends EntityDAO<Gender> {
	@ListSupport(order = "id desc")
	List<Gender> list();
}