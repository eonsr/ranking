package com.mx.eon.ranking.ranking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;

import com.mx.eon.ranking.ranking.service.RankingProcessService;

@SpringBootApplication
@Profile("!test")
public class RankingApplication {

	/**
	 * main for the class
	 * 
	 * @param args args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(RankingApplication.class, args);
		
		RankingProcessService rankingProcessService = applicationContext.getBean(RankingProcessService.class);
		
		rankingProcessService.executeRankingProcess();
		
	}

}
