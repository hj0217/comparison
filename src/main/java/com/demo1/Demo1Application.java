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
	}

}
