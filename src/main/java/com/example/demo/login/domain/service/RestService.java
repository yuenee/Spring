package com.example.demo.login.domain.service;

import java.util.List;

import com.example.demo.login.domain.model.User;

public interface RestService {
	
	// １件登録用メソッド
	public boolean insert(User user);
	
	// １件検索用メソッド
	public User selectOne(String userId);
	
	// 全件検索用メソッド
	public List<User> selectMany();
	
	// １件更新用メソッド
	public boolean update(User user);
	
	// １件削除用メソッド
	public boolean delete(String userId);
}