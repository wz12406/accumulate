package cn.yesway.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.yesway.entity.User;
import cn.yesway.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext.xml" })
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Test
	public void getUserByLoginName() {
		User user = userService.getUserByLoginName("user");
		System.out.println(user);
	}
}
