package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.repository.UserDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDaoTest {
	
	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;
	
	// カウントメソッドのテスト１
	@Test
	public void countTest1() {
		
		// カウントメソッドの結果が２件であることをテスト
		assertEquals(dao.count(), 2);
	}
	
	// カウントメソッドのテスト２
	@Test
	@Sql("/testdata.sql")
	public void countTest2() {
		
		// カウントメソッドの結果が３件であることをテスト
		assertEquals(dao.count(), 3);
	}
}