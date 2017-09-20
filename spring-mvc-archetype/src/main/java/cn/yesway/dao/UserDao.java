package cn.yesway.dao;

import cn.yesway.entity.User;

public interface UserDao {
	public abstract User getUserByLoginName(String loginName);
}
