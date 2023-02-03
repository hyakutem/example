package com.example.example.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DelayController {

	/**
	 * 指定した[ms]経過後に"OK"を返す
	 * curl -v http://localhost:8080/delay/500
	 */
	@RequestMapping(value = "/delay/{millis}", method = RequestMethod.GET)
	@ResponseBody
	String index(@PathVariable String millis) {
		log.info("sleeping: " + millis + "[ms]");
		sleeping(Integer.parseInt(millis));
		return "OK";
	}

	/**
	 * 指定した[ms]をMaxとして、0~Maxのランダムな[ms]経過後に"OK"を返す
	 * curl -v http://localhost:8080/rdelay/3000
	 */
	@RequestMapping(value = "/rdelay/{millis}", method = RequestMethod.GET)
	@ResponseBody
	String random(@PathVariable String millis) {
		int maxMillis = Integer.parseInt(millis);
		int randomMillis = randomIntFor(maxMillis);
		log.info("sleeping: " + randomMillis + "[ms] args: " + maxMillis + "[ms]");
		sleeping(randomMillis);
		return "OK";
	}

	void sleeping(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			log.warn("wait中にException発生しました", e);
		}
	}

	// Refs: https://javamex.com/tutorials/random_numbers/xorshift.shtml#.XkUmSRP7TUo
	private static int randomIntFor(int max) {
		long x = System.nanoTime();
		x ^= (x << 21);
		x ^= (x >>> 35);
		x ^= (x << 4);
		return Math.abs((int) x % max);
	}

}
