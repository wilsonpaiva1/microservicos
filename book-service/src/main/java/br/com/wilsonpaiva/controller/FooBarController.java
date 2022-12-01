package br.com.wilsonpaiva.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Recover;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Foo Bar endpoint")

@RestController
@RequestMapping("book-service")
public class FooBarController {
	
	private Logger logger = LoggerFactory.getLogger(FooBarController.class);
	
	@GetMapping("/foo-bar")
	//@Retryable (value = {HttpClientErrorException.class},  maxAttempts = 5, backoff = @Backoff(delay = 1000))
	//@CircuitBreaker
	//@Retry(name = "foo-bar",fallbackMethod ="fallbackMethod" )
	//@CircuitBreaker(name = "default",fallbackMethod ="fallbackMethod" )
	//@RateLimiter(name="default")
	@RateLimiter(name="default")
	@Operation(summary =  " Find a specific book by uour ID")
	public String fooBar() {
		logger.info("Request to foo-bar is received");
		//var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar",String.class);
		
		return "Foo Bar!!!";
		//return response.getBody();
	}
	
	@Recover
	public String fallbackMethod(RuntimeException ex) {
		return "fallbackMethod foo-bar!";
		
	}
}
