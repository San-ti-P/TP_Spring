package isi.deso.tpspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TpspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpspringApplication.class, args);
		System.out.println("http://localhost:8080/login");
		System.out.println("username: tpdesarrollo");
		System.out.print("password: contra123");
	}
}