package cn.cloudStream.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MealServiceDay01Application {

	public static void main(String[] args) {
		SpringApplication.run(MealServiceDay01Application.class, args);
	}

}
