package com.myhuholi.homogochi;

import org.springframework.boot.SpringApplication;

public class TestTamagochiApplication {

	public static void main(String[] args) {
		SpringApplication.from(HomogochiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
