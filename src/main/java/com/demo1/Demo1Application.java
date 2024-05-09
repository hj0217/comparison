package com.demo1;


import com.demo1.commonDto.Member;
import com.demo1.commonDto.Role;
import com.demo1.customer.dto.HistoryDto;
import com.demo1.jsondiff.HistoryUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(Demo1Application.class, args);

		Member change = Member.builder()
				.id(101)
				.password("changedPassword")
				.role(Role.builder()
						.role("ROLE_USER")
						.build())
				.role(Role.builder()
						.role("ROLE_MASTER")
						.build())
				.parent(Member.builder()
						.id(201)
						.email("changedInnep@innisfeeep.com")
						.password("epPASSWORD")
						.grandParent(Member.builder()
								.id(301)
								.password("chlwhdqhtlahq")
								.build())
						.build())
				.build();

		System.out.println(HistoryUtil.checkDiff(change));
	}

}
