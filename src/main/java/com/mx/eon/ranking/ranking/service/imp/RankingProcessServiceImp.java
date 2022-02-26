package com.mx.eon.ranking.ranking.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.eon.ranking.ranking.bo.RankingProcessBO;
import com.mx.eon.ranking.ranking.service.RankingProcessService;

/**
 * Implementation of {@link RankingProcessService}
 * 
 * @author Noe Salazar
 * 
 * @version 1.0
 *
 */
@Service
public class RankingProcessServiceImp implements RankingProcessService {

	@Autowired
	private RankingProcessBO rankingProcessBO;
	
	@Override
	public void executeRankingProcess() {
		
		rankingProcessBO.executeRankingProcess();

	}

}
