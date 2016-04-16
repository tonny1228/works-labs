/**
 * 
 */
package works.tonny.apps.demo.onetomany;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import works.tonny.apps.Entity;

/**
 * @author 祥栋
 */
@javax.persistence.Entity
@Table(name = "demo_parent")
public class Parent extends Entity {
	private String name;

	private Set<Child> children;

	private Set<Property> properties;

	private Set<Gender> genders;

	/**
	 * {@inheritDoc}
	 * 
	 * @see works.tonny.apps.Entity#getId()
	 */
	@Override
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
	@Column(length = 50)
	public String getName() {
		return name;
	}

	/**
	 * 父类控制子类的添加、修改、删除。只要不关联就删除
	 * 
	 * @return the children
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "parent_id")
	// @Cascade({ org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	@OrderBy("id")
	public Set<Child> getChildren() {
		return children;
	}

	/**
	 * 父类控制子类的添加、修改、删除。取消关联不删除，而是置parent_id为空。orphanRemoval
	 * 
	 * @return the properties
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "parent_id")
	@OrderBy("id")
	public Set<Property> getProperties() {
		return properties;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(Set<Child> children) {
		this.children = children;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

	/**
	 * @return the genders
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "demo_parent_gender", joinColumns = @JoinColumn(name = "parent_id"), inverseJoinColumns = @JoinColumn(name = "gender_id"))
	public Set<Gender> getGenders() {
		return genders;
	}

	/**
	 * @param genders the genders to set
	 */
	public void setGenders(Set<Gender> genders) {
		this.genders = genders;
	}
}
