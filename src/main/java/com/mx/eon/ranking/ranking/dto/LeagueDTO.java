package com.mx.eon.ranking.ranking.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * League DTO
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
public class LeagueDTO {
	
	/**
	 * {@link MatchResultsDTO} list
	 */
	private List<MatchResultsDTO> matchList;

}
