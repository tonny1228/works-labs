/**
 * 
 */
package works.tonny.apps.demo.onetomany;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import works.tonny.apps.Entity;

/**
 * @author 祥栋
 */
@javax.persistence.Entity
@Table(name = "demo_gender")
public class Gender extends Entity {
	private String name;

	/**
	 * {@inheritDoc}
	 * 
	 * @see works.tonny.apps.Entity#getId()
	 */
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(length = 50)
	public String getId() {
		return super.getId();
	}

	/**
	 * @return the name
	 */
	@Column(length = 10)
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
