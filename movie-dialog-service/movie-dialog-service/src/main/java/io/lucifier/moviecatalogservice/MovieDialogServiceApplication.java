package io.lucifier.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MovieDialogServiceApplication {
    
	//@LoadBalanced, the library will take care of service discovery in client side, it provides an abstraction,
	//you tell the library go through the eureka and pass that messaage to that service
	@Bean//producer
	@LoadBalanced//it does service discovery(searching the exact url in eureka) while consume other services in a load balanced way,
	public RestTemplate getRestTemplate() {//using loanbalancd, we tell resttemplate, don't go to the url actual url i am giving you,
		//the url given is basically a hint,what service you need to discover, 
		return new RestTemplate();
	}	

	
	public static void main(String[] args) {
		SpringApplication.run(MovieDialogServiceApplication.class, args);
	}

}
