package com.Ankita.SecurityApplication;

import com.Ankita.SecurityApplication.entities.User;
import com.Ankita.SecurityApplication.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {

		User user=new User(4L,"ankita@gmail.com","1234","ankita");
		String token=jwtService.generatesToken(user);
		System.out.println(token);
		Long id=jwtService.getUserIdFromToken(token);
		System.out.println(id);


	}



}
