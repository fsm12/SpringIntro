package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 애노테이션으로
@SpringBootApplication
public class HelloSpringApplication {
	// localhost:8080
	public static void main(String[] args) {
		// HelloSpringApplication 이 클래스를 넣어주면, SpringBootApplication이 실행됨
		// 톰캣이라는 웹서버를 내장하여서, 이 서버를 자체적으로 띄우면서 Spring이 올라옴
		SpringApplication.run(HelloSpringApplication.class, args);
	}
}
