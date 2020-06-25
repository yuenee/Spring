package com.example.demo.login.domain.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.mybatis.UserMapper;
import com.example.demo.login.domain.service.RestService;

@Transactional
@Service("RestServiceMybatisImpl")
public class RestServiceMybatisImpl implements RestService {
	
	@Autowired
	UserMapper userMapper2;
	
	@Override
	public boolean insert(User user) {
		
		// insert実行
		return userMapper2.insert(user);
	}
	
	@Override
	public User selectOne(String userId) {
		
		// select実行
		return userMapper2.selectOne(userId);
	}
	
	@Override
	public List<User> selectMany() {
		
		//select実行
		return userMapper2.selectMany();
	}
	
	@Override
	public boolean update(User user) {
		
		// update実行
		return userMapper2.updateOne(user);
	}
	
	@Override
	public boolean delete(String userId) {
		
		// delete実行
		return userMapper2.deleteOne(userId);
	}
}