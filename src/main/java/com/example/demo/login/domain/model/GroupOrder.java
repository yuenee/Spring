package com.example.demo.login.domain.model;

import javax.validation.GroupSequence;

@GroupSequence({ValidGroup1.class, ValidGroup2.class, ValidGroup3.class})
public interface GroupOrder {
	
}