package com.mx.eon.ranking.ranking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Data Transfer Object for Ranking Data
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
public class RankingDTO {

	/**
	 * Team name
	 */
	private String teamName;
	
	/**
	 * Team points (match) 
	 */
	private int points;
	
	/**
	 * ranking points
	 */
	private int rankingPoints;
	
	/**
	 * position
	 */
	private int position;
	
}
