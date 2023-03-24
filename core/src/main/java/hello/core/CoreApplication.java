package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 자동으로 만들어짐
// 설정 : Settings > Build, Execution, Deployment > Build Tools > Gradle > IntelliJ IDEA로 둘다 설정
@SpringBootApplication // ComponentScan이 들어가 있음
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
