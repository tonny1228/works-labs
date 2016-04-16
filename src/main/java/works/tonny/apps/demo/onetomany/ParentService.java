/**
 * 
 */
package works.tonny.apps.demo.onetomany;

import java.util.List;

import works.tonny.apps.EntityService;

/**
 * @author 祥栋
 */
public interface ParentService extends EntityService<Parent> {
	List<Gender> list();
}