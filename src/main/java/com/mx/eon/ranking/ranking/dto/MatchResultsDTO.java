package com.mx.eon.ranking.ranking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Match DTO
 * 
 * @author Noe Salazar
 * 
 * @version 1.0
 *
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MatchResultsDTO {

	/**
	 * team A Match Results
	 */
	private RankingDTO teamAMatchResults;
	
	/**
	 * team B Match Results
	 */
	private RankingDTO teamBMatchResults;
	
}
