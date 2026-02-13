package com.moussa.rimma_backend;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;

@SpringBootApplication
public class RimmaBackendApplication {

	public static void main(String[] args) {
        SpringApplication.run(RimmaBackendApplication.class, args);
	}

}
