package com.revature.services;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.repositories.UserDao;


@ExtendWith(MockitoExtension.class)
public class UserServices {
	
	@Mock
	private UserDao ud;
	
	@InjectMocks
	private UserAuth ua;
	
	@Test
	public void loginUserTest() {
		Mockito.when(ud.loginUser(null)).thenReturn(true);
		boolean expected = true;
		boolean actual = ua.loginUser(null);
		assertEquals(expected, actual);
	}

}