package com.mx.eon.ranking.ranking.template;

import java.io.File;
import java.util.List;

import com.mx.eon.ranking.ranking.dto.RankingDTO;

/**
 * Template for Ranking Process
 * 
 * @author Noe Salazar
 * 
 * @version 1.0
 *
 */
public abstract class RankingProcessTemplate {

	/**
	 * Method to execute the ranking process
	 */
	public void executeRankingProcess() {
		
		printInstructions();

		String filePath = getFilePath();
		
		if (isFilePathValid(filePath)) {
			
			File rankingFile = new File(filePath);
			
			if (isFileValid(rankingFile)) {
				
				List<RankingDTO> rankingList = processFile(rankingFile);
				printOutput(rankingList);
				
			} else {
				System.out.println("[Error] the file is not valid, process terminated");
			}
			
		} else {
			System.out.println("[Error] the file path is not valid, process terminated");
		}

	}
	
	/**
	 * Method to print the program instructions 
	 */
	public abstract void printInstructions();
	
	/**
	 * Method to get the path + file to process
	 * 
	 * @return string with the filePath
	 */
	public abstract String getFilePath();
	
	/**
	 * Method that validates the entry path from <code>getFilePath</code>
	 * 
	 * @param filePath String with file + path to validate
	 * @return true if valid false otherwise 
	 */
	public abstract boolean isFilePathValid(String filePath);
	
	/**
	 * Valdiates the file (object from entry path), checks file size 
	 * with a long value, if the file exists, if its not a folder
	 * 
	 * @param rankingFile File to be processed
	 * @return true if valid false otherwise 
	 */
	public abstract boolean isFileValid(File rankingFile);
	
	/**
	 * Method to execute the process in the File
	 * 
	 * @param rankingFile Fiule to process
	 * @return List to be printed
	 */
	public abstract List<RankingDTO> processFile(File rankingFile);
	
	/**
	 * Method to print the list in the specified order
	 * 
	 * @param rankingList list to be printed 
	 */
	public abstract void printOutput(List<RankingDTO> rankingList);
	
}
