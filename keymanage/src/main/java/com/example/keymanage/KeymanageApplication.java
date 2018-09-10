package com.example.keymanage;

import com.example.keymanage.Util.TokenThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KeymanageApplication {

	public static void main(String[] args) {

		SpringApplication.run(KeymanageApplication.class, args);
		new Thread(new TokenThread()).start();
	}
}
