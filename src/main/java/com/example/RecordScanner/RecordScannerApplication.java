package com.example.RecordScanner;


import com.example.RecordScanner.model.Record;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class RecordScannerApplication {
	public static void main(String[] args) throws IOException  {
		SpringApplication.run(RecordScannerApplication.class, args);

			//FOR TESTING METHODS

		String url = "https://bpdts-test-app.herokuapp.com/users";
		String cityUrl = "https://bpdts-test-app.herokuapp.com/city/London/users";

		Record.findUsers(url,cityUrl);
			System.out.println("GET USERS DONE");
			System.out.println("GET CITY DONE");


		//follow this link to the http://localhost:9099/retrieve-users to view the output
		//to view API method go to http://localhost:9099/swagger-ui.html to view Get method
		}
	}


