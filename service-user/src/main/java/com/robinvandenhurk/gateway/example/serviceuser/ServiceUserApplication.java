package com.robinvandenhurk.gateway.example.serviceuser;

import com.robinvandenhurk.gateway.library.userinjection.LoadUserFromHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.sql.SQLException;

@SpringBootApplication
@Import(LoadUserFromHeaderFilter.class)
public class ServiceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceUserApplication.class, args);
	}

}
