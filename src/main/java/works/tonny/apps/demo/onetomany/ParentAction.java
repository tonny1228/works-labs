/**
 * 
 */
package works.tonny.apps.demo.onetomany;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.collection.AbstractPersistentCollection;

import works.tonny.apps.support.CommonAction;
import works.tonny.apps.support.EntityLazyProxy;
import works.tonny.apps.support.ServiceManager;

/**
 * @author 祥栋
 *
 */
/**
 * @author 祥栋
 */
public class ParentAction extends CommonAction {

	private ParentService parentService;

	private Parent parent;

	private List<Child> child;

	private List<Property> property;

	private List<Gender> gender;

	private String id;

	public String add() {
		request.setAttribute("genders", parentService.list());
		return "add";
	}

	public String save() {
		parent.setChildren(new HashSet<Child>());
		parent.getChildren().addAll(child);
		parent.setProperties(new HashSet<Property>());
		parent.getProperties().addAll(property);
		parent.setGenders(new HashSet<Gender>());
		parent.getGenders().addAll(gender);
		parentService.save(parent);
		return "list";
	}

	public String update() {
		parent.setChildren(new HashSet<Child>());
		parent.getChildren().addAll(child);
		parent.setProperties(new HashSet<Property>());
		parent.getProperties().addAll(property);
		parentService.update(parent);
		return "list";
	}

	public String delete() {
		parentService.delete(id);
		return "list";
	}

	public String edit() {
		parent = parentService.get(id);
		entityLazyProxy = ServiceManager.lookup(EntityLazyProxy.class);
		refresh(parent);
//		refresh(parent.getChildren());
//		refresh(parent.getProperties());
		return "edit";
	}

	/**
	 * 
	 */
	public void refresh(Object entity) {
		if (entity instanceof AbstractPersistentCollection) {
			AbstractPersistentCollection collection = (AbstractPersistentCollection) entity;
			if (collection.wasInitialized()) {
				return;
			}
//			collection.getOwner()
		}

		entityLazyProxy.refresh(entity);
	}

	/**
	 * @return the parentService
	 */
	public ParentService getParentService() {
		return parentService;
	}

	/**
	 * @param parentService the parentService to set
	 */
	public void setParentService(ParentService parentService) {
		this.parentService = parentService;
	}

	/**
	 * @return the parent
	 */
	public Parent getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Parent parent) {
		this.parent = parent;
	}

	/**
	 * @return the child
	 */
	public List<Child> getChild() {
		return child;
	}

	/**
	 * @param child the child to set
	 */
	public void setChild(List<Child> child) {
		this.child = child;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the property
	 */
	public List<Property> getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public void setProperty(List<Property> property) {
		this.property = property;
	}

	/**
	 * @return the gender
	 */
	public List<Gender> getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(List<Gender> gender) {
		this.gender = gender;
	}

}
