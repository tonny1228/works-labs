/**
 * 
 */
package works.tonny.apps.lab;

import javax.persistence.Entity;
import javax.persistence.Table;

import works.tonny.apps.user.model.UserInfo;

/**
 * @author 祥栋
 */
@Entity
@Table(name = "test_user")
public class MyUser extends UserInfo {

	private String hello;

	/**
	 * @return the hello
	 */
	public String getHello() {
		return hello;
	}

	/**
	 * @param hello the hello to set
	 */
	public void setHello(String hello) {
		this.hello = hello;
	}

}
