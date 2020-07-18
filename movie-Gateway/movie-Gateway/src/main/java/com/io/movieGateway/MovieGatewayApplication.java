package com.io.movieGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;

import com.io.movieGateway.filters.GatewayFilter;
import com.io.movieGateway.filters.PostFilter;
import com.io.movieGateway.filters.RouteFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy//to enable routing
public class MovieGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieGatewayApplication.class, args);
	}
	
	@Bean
	public GatewayFilter gateway()
	{
		return new GatewayFilter();
	}
	
	@Bean
	public PostFilter postfilter()
	{
		return new PostFilter();
	}
	
	@Bean
	public RouteFilter routefilter()
	{
		return new RouteFilter();
	}
	
}
