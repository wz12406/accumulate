package cn.yesway.service;

import cn.yesway.entity.User;

public interface UserService {
	public abstract User getUserByLoginName(String loginName);
}
