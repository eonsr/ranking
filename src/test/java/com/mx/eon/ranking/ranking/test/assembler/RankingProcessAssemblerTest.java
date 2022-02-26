package com.mx.eon.ranking.ranking.test.assembler;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.eon.ranking.ranking.assembler.RankingProcessAssembler;
import com.mx.eon.ranking.ranking.dto.LeagueDTO;
import com.mx.eon.ranking.ranking.dto.MatchResultsDTO;
import com.mx.eon.ranking.ranking.dto.RankingDTO;


/**
 * Class to test {@link RankingProcessAssembler}
 * 
 * @author Noe Salazar
 * 
 * @version 1.0
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class RankingProcessAssemblerTest {
	
	/**
	 * RankingProcessAssembler 
	 */
	@Autowired
	private RankingProcessAssembler rankingProcessAssembler;
	
	List<String> stringListLoaded;
	
	/**
	 * init method
	 */
	@BeforeAll
	void init() {
		
		stringListLoaded = new ArrayList<String>();
		
		stringListLoaded.add("Lions 3, Snakes 3");
		stringListLoaded.add("Tarantulas 1, FC Awesome 0");
		stringListLoaded.add("Lions 1, FC Awesome 1");
		stringListLoaded.add("Tarantulas 3, Snakes 1");
		stringListLoaded.add("Lions 4, Grouches 0");
		
	}

	/**
	 * method to test getLeagueDTO
	 */
	@Test
	void testGetLeagueDTO() {
		
		/*Test 1*/
		LeagueDTO leagueDTO = rankingProcessAssembler.getLeagueDTO(null);
		
		assertNull(leagueDTO);
		
		/*Test 2*/
		leagueDTO = rankingProcessAssembler.getLeagueDTO(new ArrayList<>());
		
		assertNull(leagueDTO);
		
		/*Test 3*/
		leagueDTO = rankingProcessAssembler.getLeagueDTO(stringListLoaded);
		
		assertNotNull(leagueDTO);
		
		List<MatchResultsDTO> matchResultsList = leagueDTO.getMatchList();
		
		for (MatchResultsDTO matchResultsDTO : matchResultsList) {
			System.out.println(matchResultsDTO);
		}
		
		
	}
	
	/**
	 * method to test getRankingPointsMap
	 */
	@Test
	void testGetRankingPointsMap() {
		
		/*Test 1*/
		LeagueDTO leagueDTO = rankingProcessAssembler.getLeagueDTO(stringListLoaded);
		
		assertNotNull(leagueDTO);
		assertNotNull(leagueDTO.getMatchList().get(0).getTeamAMatchResults());
		assertNotNull(leagueDTO.getMatchList().get(0).getTeamBMatchResults());
		
		leagueDTO.getMatchList().get(0).getTeamAMatchResults().getPoints();
		leagueDTO.getMatchList().get(0).getTeamAMatchResults().getPosition();
		leagueDTO.getMatchList().get(0).getTeamAMatchResults().getTeamName();
		
		leagueDTO.getMatchList().get(0).getTeamBMatchResults().getPoints();
		leagueDTO.getMatchList().get(0).getTeamBMatchResults().getPosition();
		leagueDTO.getMatchList().get(0).getTeamBMatchResults().getTeamName();
		
		Map<String, Integer> map = rankingProcessAssembler.getRankingPointsMap(leagueDTO);
		
		assertNotNull(map);
		
		map.forEach((k, v) -> System.out.println((k + ":" + v)));
		
		/*Test 2*/
		map = rankingProcessAssembler.getRankingPointsMap(null);
		
		assertNull(map);
		
	}
	
	/**
	 * method to test getRankingList
	 */
	@Test
	void testGetRankingList(){
		
		/*Test 1*/
		LeagueDTO leagueDTO = rankingProcessAssembler.getLeagueDTO(stringListLoaded);
		
		assertNotNull(leagueDTO);
		
		Map<String, Integer> map = rankingProcessAssembler.getRankingPointsMap(leagueDTO);
		
		assertNotNull(map);
		
		List<RankingDTO> rankingList = rankingProcessAssembler.getRankingList(map);
		
		assertNotNull(rankingList);
		
		
		/*Test 2*/
		rankingList = rankingProcessAssembler.getRankingList(null);
		
		assertNotNull(rankingList);
		
		/*Test 3*/
		rankingList = rankingProcessAssembler.getRankingList(new TreeMap<>());
		
		assertNotNull(rankingList);
		
	}
	
}
