package com.example.Ac2_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@SpringBootApplication
public class Ac2ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ac2ProjectApplication.class, args);

	}

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World";
	}

}
