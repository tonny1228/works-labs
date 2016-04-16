/**
 * 
 */
package works.tonny.apps.demo.onetomany;

import java.util.List;

import works.tonny.apps.util.EntityUtils;

/**
 * @author 祥栋
 */
public class ParentServiceImpl implements ParentService {

	private ParentDAO parentDAO;

	private GenderDAO genderDAO;

	/**
	 * {@inheritDoc}
	 * 
	 * @see works.tonny.apps.EntityService#save(java.lang.Object)
	 */
	public String save(Parent t) {

		return parentDAO.save(t);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see works.tonny.apps.EntityService#get(java.lang.String)
	 */
	public Parent get(String id) {

		return parentDAO.get(id);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see works.tonny.apps.EntityService#update(java.lang.Object)
	 */
	public void update(Parent t) {
		Parent db = get(t.getId());
		EntityUtils.merge(db.getChildren(), t.getChildren());
		EntityUtils.merge(db.getProperties(), t.getProperties());
		parentDAO.update(db);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see works.tonny.apps.EntityService#delete(java.lang.String)
	 */
	public void delete(String id) {
		parentDAO.delete(get(id));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see works.tonny.apps.EntityService#delete(java.lang.String[])
	 */
	public void delete(String[] ids) {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see works.tonny.apps.demo.onetomany.ParentService#list()
	 */
	public List<Gender> list() {
		return genderDAO.list();
	}

	/**
	 * @return the parentDAO
	 */
	public ParentDAO getParentDAO() {
		return parentDAO;
	}

	/**
	 * @param parentDAO the parentDAO to set
	 */
	public void setParentDAO(ParentDAO parentDAO) {
		this.parentDAO = parentDAO;
	}

	/**
	 * @return the genderDAO
	 */
	public GenderDAO getGenderDAO() {
		return genderDAO;
	}

	/**
	 * @param genderDAO the genderDAO to set
	 */
	public void setGenderDAO(GenderDAO genderDAO) {
		this.genderDAO = genderDAO;
	}

}
