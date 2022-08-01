package com.example.objectmapper;

import com.example.objectmapper.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		System.out.println("-----------------");

		var objectMapper = new ObjectMapper();

		//	object -> text
		//	object mapper 가 get method 를 활용한다
		var user = new User("user",10, "010-1234-1234");
		var text = objectMapper.writeValueAsString(user);
		System.out.println(text);

		//	text -> object
		//	object mapper 는  default 생성자를 필요로 한다
		var objectUser = objectMapper.readValue(text, User.class);
		System.out.println(objectUser);

	}

}
