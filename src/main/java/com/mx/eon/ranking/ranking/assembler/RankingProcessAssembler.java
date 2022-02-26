package com.mx.eon.ranking.ranking.assembler;

import static com.mx.eon.ranking.ranking.constants.RankingConstants.SCANNER_DELIMITER;
import static com.mx.eon.ranking.ranking.constants.RankingConstants.STRING_SPLITTER;
import static com.mx.eon.ranking.ranking.constants.RankingConstants.VOID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.mx.eon.ranking.ranking.dto.LeagueDTO;
import com.mx.eon.ranking.ranking.dto.MatchResultsDTO;
import com.mx.eon.ranking.ranking.dto.RankingDTO;

/**
 * Assembler for Ranking Process
 * 
 * @author Noe Salazar
 * 
 * @version 1.0
 *
 */
@Component
public class RankingProcessAssembler {

	/**
	 * Method to generate the {@link LeagueDTO} 
	 * 
	 * @param fileLines list with file lines (Strings)
	 * @return {@link LeagueDTO} 
	 */
	public LeagueDTO getLeagueDTO(List<String> fileLines) {
	
		LeagueDTO leagueDTO = null;
		
		if (fileLines != null && !fileLines.isEmpty()) {
						
			List<MatchResultsDTO> matchResultsList = generateMatchResultsList(fileLines);
	
			leagueDTO = new LeagueDTO();
			leagueDTO.setMatchList(matchResultsList);

		} else {
			System.out.println("[Error] No content to process");
		}
		
		return leagueDTO;
		
	}
	
	/**
	 * Method to get the ranking points map
	 * 
	 * @param leagueDTO {@link LeagueDTO}
	 * @return map with teams names and ranking points
	 */
	public Map<String, Integer> getRankingPointsMap (LeagueDTO leagueDTO) {
		
		Map<String, Integer> map = null;
		
		if(leagueDTO != null) {
			
			map = new TreeMap<>();
			
			List<MatchResultsDTO> matchResultsList = leagueDTO.getMatchList();
			
			for (MatchResultsDTO matchResultsDTO : matchResultsList) {
				
				String teamNameA = matchResultsDTO.getTeamAMatchResults().getTeamName();
				Integer rankingpointsA = matchResultsDTO.getTeamAMatchResults().getRankingPoints(); 
				
				fillMap(teamNameA, rankingpointsA, map);
				
				String teamNameB = matchResultsDTO.getTeamBMatchResults().getTeamName();
				Integer rankingpointsB = matchResultsDTO.getTeamBMatchResults().getRankingPoints(); 
				
				fillMap(teamNameB, rankingpointsB, map);
				
			}
			
		} else {
			System.out.println("[Error] No content to process and generate map");
		}
		
		return map;
		
	}
	
	/**
	 * Method to get the Ranking list from the entry map
	 * 
	 * @param map entry map 
	 * @return {@link RankingDTO}
	 */
	public List<RankingDTO> getRankingList(Map<String, Integer> map) {
		
		List<RankingDTO> rankingList = new ArrayList<RankingDTO>();
		
		if (map != null && !map.isEmpty()) {
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				
				RankingDTO rankingDTO = new RankingDTO();
				
				rankingDTO.setRankingPoints(entry.getValue());
				
				rankingDTO.setTeamName(entry.getKey());
				
				rankingList.add(rankingDTO);
				
			}
			
		} else {
			System.out.println("[Error] No data to process in Map");
		}
		
		return rankingList;			
		
	}
	
	/*
	 * ==================================================================== 
	 *                         private methods
	 * ====================================================================
	 */
	
	/**
	 * Method to fill map with ranking and teams information
	 * 
	 * @param teamName team name
	 * @param rankingpoints ranking points
	 * @param map map with ranking and teams information
	 */
	private void fillMap(String teamName, Integer rankingpoints, Map<String, Integer> map) {
		
		if(!map.containsKey(teamName)) {
			map.put(teamName, rankingpoints);
		} else {
			Integer newpoints = rankingpoints + map.get(teamName);
			map.put(teamName, newpoints);
		}
		
	}
	
	
	/**
	 * Metthod to create the list of {@link MatchResultsDTO} from the String List
	 * 
	 * @param fileLines String list (from file)
	 * @return list of {@link MatchResultsDTO}
	 */
	private List<MatchResultsDTO> generateMatchResultsList(List<String> fileLines) {
		
		List<MatchResultsDTO> matchResultsList = new ArrayList<>();
		
		for (String string : fileLines) {
			
			
			if (string != null && !string.isEmpty() ) {
				
				String rowArr[] = string.split(STRING_SPLITTER);
				
				MatchResultsDTO matchResultsDTO = createMatchResultsDTO(rowArr);
				addRankingPoints(matchResultsDTO);
				
				if (matchResultsDTO != null) {
					matchResultsList.add(matchResultsDTO);
				}
				
			}
			
		}
		
		return matchResultsList;
		
	}
	
	/**
	 * Method to generate the {@link MatchResultsDTO} from the array
	 * 
	 * @param rowArr Array to process
	 * @return {@link MatchResultsDTO} 
	 */
	private MatchResultsDTO createMatchResultsDTO(String rowArr[]) {
		
		MatchResultsDTO matchResultsDTO = null;
		
		if (rowArr.length == 2) {
			
			matchResultsDTO = new MatchResultsDTO(); 
			
			matchResultsDTO.setTeamAMatchResults(generateDTOFromStr(rowArr[0]));
			matchResultsDTO.setTeamBMatchResults(generateDTOFromStr(rowArr[1]));
			
		}
		
		return matchResultsDTO;
		
	}
	
	/**
	 * Method to add ranking points per match 
	 * 
	 * @param matchResultsDTO {@link MatchResultsDTO}
	 */
	private void addRankingPoints(MatchResultsDTO matchResultsDTO) {
		
		int pointsA = matchResultsDTO.getTeamAMatchResults().getPoints();
		int pointsB = matchResultsDTO.getTeamBMatchResults().getPoints();
		
		if(pointsA == pointsB) { /* tie */
			matchResultsDTO.getTeamAMatchResults().setRankingPoints(1);
			matchResultsDTO.getTeamBMatchResults().setRankingPoints(1);
		} else if (pointsA > pointsB) { /* A won */
			matchResultsDTO.getTeamAMatchResults().setRankingPoints(3);
			matchResultsDTO.getTeamBMatchResults().setRankingPoints(0);
		} else { /* B won */
			matchResultsDTO.getTeamAMatchResults().setRankingPoints(0);
			matchResultsDTO.getTeamBMatchResults().setRankingPoints(3);
		}

	}
	

	/**
	 * Method to generate a {@link RankingDTO} from a String
	 * 
	 * @param dto String to be processed
	 * @return {@link RankingDTO}
	 */
	private RankingDTO generateDTOFromStr(String dto) {
		
		RankingDTO rankingDTO = null;
		
		if (dto != null && !dto.isEmpty()) {
			
			rankingDTO = new RankingDTO();
			
			int points = getIntegerFromStr(dto);
			
			rankingDTO.setPoints(points);
			rankingDTO.setTeamName(getTeamString(dto, points));
			
		}
				
		return rankingDTO;
		
	}
	
	/**
	 * Method to get a int from a String, uses a regex to get the right
	 * int. Returns 0 if the string is invalid
	 *  
	 * @param str String to process
	 * @return int to return
	 */
	private int getIntegerFromStr(String str) {
		
		Scanner scanner = null;
		int integer = 0;
		
		try {			
			scanner = new Scanner(str).useDelimiter(SCANNER_DELIMITER);
			integer = scanner.nextInt();
		} finally {
			scanner.close();			
		}
		
		return integer;
		
	}
	
	/**
	 * Get team name (String)
	 * 
	 * @param str String to be processed
	 * @param points integer with points
	 * @return team name
	 */
	private String getTeamString(String str, Integer points) {
		
		String team = str.replace(points.toString(), VOID);  
		
		if(team != null && !team.isEmpty()) {
			team = team.trim();
		}
		
		return team;
		
	}
	
}
