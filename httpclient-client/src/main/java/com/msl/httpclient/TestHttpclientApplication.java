package com.msl.httpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.msl.httpclient.runner.SendRequestRunner;

@SpringBootApplication
public class TestHttpclientApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TestHttpclientApplication.class, args);
		SpringApplication.run(SendRequestRunner.class, args);
	}
}
