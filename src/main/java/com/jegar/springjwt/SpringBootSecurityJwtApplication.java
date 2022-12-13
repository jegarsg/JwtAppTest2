package com.jegar.springjwt;

import com.jegar.springjwt.models.User;
import com.jegar.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class SpringBootSecurityJwtApplication
{

		public static void main(String[] args) {
			SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
		}

	}



