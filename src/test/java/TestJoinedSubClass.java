import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import works.tonny.apps.lab.MyUser;
import works.tonny.apps.support.HibernateDAO;
import works.tonny.apps.user.Privilege;
import works.tonny.apps.user.service.UserEntityService;

/**
 * 
 */

/**
 * @author 祥栋
 */
@ContextConfiguration(locations = { "classpath:applicationContext-mod-basic.xml",
		"classpath*:config/applicationContext-mod-user.xml", "classpath:applicationContext-mod-console.xml" })
@Transactional()
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TestJoinedSubClass extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private HibernateDAO daoSupport;

	@Autowired
	private UserEntityService userEntityService;

	@Test
	public void testJoined() {
		daoSupport.find("from JoinedClass");
	}

	@Test
	public void testJoinedUser() {
		daoSupport.find("from User");
		daoSupport.find("from Title");
		daoSupport.find("from Role");
		daoSupport.find("from UserInfo");
		daoSupport.find("from RolePrivilege");
		daoSupport.find("from BusinessUnit");
		daoSupport.find("from Department");
		daoSupport.find("from Job");
		daoSupport.find("from JobLevel");
		daoSupport.find("from Member");
		daoSupport.find("from Position");
		daoSupport.find("from PositionPrivilege");
		daoSupport.find("from SystemResource");
		daoSupport.find("from UserPrivilege");
		daoSupport.find("from Catalog");
		daoSupport.find("from Form");
		daoSupport.find("from Element");
		daoSupport.find("from FormValue");
		daoSupport.find("from Mail");
		daoSupport.find("from Attachment");
	}

	@Test
	public void testMyUser() {
		MyUser user = new MyUser();
		user.setUsername("fsd");
		user.setName("f");
		user.setHello("hello");
		userEntityService.create(user, "2c95f6604304b087014304b645380002");
	}

	@Test
	public void testUpdateMyUser() {
		// MyUser user = (MyUser) userEntityService.getUserByUsername("fsd");
		userEntityService
				.removeUserFromPosition("40283e8143659715014365971f0c0000", "2c95f6604304b087014304b645380002");
		// userEntityService.updateUserinfo("40283e8143659715014365971f0c0000", null,
		// "2c95f6604304b087014304b645380002",
		// "2c95f6604304b087014304b645380002");
	}

	@Test
	public void testDeletePri() {
		daoSupport.delete(daoSupport.get(Privilege.class, "pri.auth"));
	}
}
