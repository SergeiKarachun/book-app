package by.sergo.book.app.bookclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@EnableEurekaClient*/
public class BookClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookClientApplication.class, args);
	}

}
