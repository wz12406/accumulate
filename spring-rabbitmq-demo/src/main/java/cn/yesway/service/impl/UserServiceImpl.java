package cn.yesway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yesway.dao.UserDao;
import cn.yesway.entity.User;
import cn.yesway.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public User getUserByLoginName(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}
}
