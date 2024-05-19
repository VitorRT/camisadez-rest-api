package br.com.camisadez.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

@RestController
@RequestMapping("/hello")
class HelloRestController {

	@GetMapping
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("Hello buddy!");
	}
}