package com.z.dodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DodoApplication {

	public static void main(String[] args) {
		Log.d("服务初始化 DodoApplication.main() . . .");
		SpringApplication.run(DodoApplication.class, args);
	}

}
