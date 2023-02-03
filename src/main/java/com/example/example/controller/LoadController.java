package com.example.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoadController {
    
	@GetMapping("/load/cpu")
	public String cpu() {
		long startTime = System.currentTimeMillis();

		// some logic to consume more cpu
		double x = 1d;
		for (int i = 0; i < 1000000; i++) {
			x += Math.sqrt(x);
		}

		long endTime = System.currentTimeMillis();
		log.info("処理時間: " + (endTime - startTime) + "[ms]");
		return "Complete";
	}

}
