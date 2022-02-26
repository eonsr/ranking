package com.mx.eon.ranking.ranking.bo;

import static com.mx.eon.ranking.ranking.constants.RankingConstants.FILE_EXTENSION;
import static com.mx.eon.ranking.ranking.constants.RankingConstants.FILE_MAX_SIZE;
import static com.mx.eon.ranking.ranking.constants.RankingConstants.FILE_NAME;
import static com.mx.eon.ranking.ranking.constants.RankingConstants.FILE_NAME_EXTENSION;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mx.eon.ranking.ranking.assembler.RankingProcessAssembler;
import com.mx.eon.ranking.ranking.dto.LeagueDTO;
import com.mx.eon.ranking.ranking.dto.RankingDTO;
import com.mx.eon.ranking.ranking.template.RankingProcessTemplate;
import com.mx.eon.ranking.ranking.util.FileUtil;

/**
 * implementation of {@link RankingProcessTemplate}
 * 
 * @author Noe Salazar
 * 
 * @version 1.0
 *
 */
@Component
public class RankingProcessBO extends RankingProcessTemplate{
	
	/**
	 * {@link FileUtil}
	 */
	@Autowired
	private FileUtil fileUtil;
	
	/**
	 * {@link RankingProcessAssembler}
	 */
	@Autowired
	private RankingProcessAssembler rankingProcessAssembler;

	@Override
	public void printInstructions() {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("\n");
		stringBuilder.append("\n");
		stringBuilder.append("Instructions to get the Ranking Table:");
		stringBuilder.append("\n");
		stringBuilder.append("\n");
		stringBuilder.append("1.- Enter the path of the file + the file name (must be ");
		stringBuilder.append(FILE_NAME);
		stringBuilder.append(") and the extension muste be (");
		stringBuilder.append(FILE_EXTENSION);
		stringBuilder.append(")");
		stringBuilder.append("\n");
		stringBuilder.append("2.- Press Enter");
		stringBuilder.append("\n");
		stringBuilder.append("\n");
		stringBuilder.append("If you want to Exit the program press Ctrl + C");
		
		System.out.println(stringBuilder);
		
	}

	@Override
	public String getFilePath() {
		
		
		System.out.println("Please enter the path of the file + the file name:");
		
		Scanner scanner = null;
		String filePath = null;
		
		scanner = new Scanner(System.in);
				
		try {
			filePath = scanner.nextLine();
		} finally {
			scanner.close();			
		}
		
		System.out.println("The path + file to process is [" + filePath + "]");
		
		return filePath;
	}

	@Override
	public boolean isFilePathValid(String filePath) {
		
		boolean isValid = false;
		
		if (filePath != null && !filePath.isEmpty()) {
			isValid = true;
		} else {
			System.out.println("[Error] The File Path can not be null or empty");
		}
		
		return isValid;
		
	}

	@Override
	public boolean isFileValid(File rankingFile) {
		
		boolean isValid = false;
		
		if(rankingFile != null && rankingFile.exists() && rankingFile.isFile()) {
			
			String filename = rankingFile.getName();
			
			System.out.println("File Name from file to process [" + filename + "]");
			
			isValid = areFileDetailsValid(filename, rankingFile);
			
		} else {
			System.out.println("[Error] The file is not valid");
		}
		
		return isValid;

	}

	@Override
	public List<RankingDTO> processFile(File rankingFile) {
		
		List<RankingDTO> rankingList = new ArrayList<RankingDTO>();
		
		if (rankingFile != null) {
			
			List<String> fileLines = fileUtil.readFileIntoStrList(rankingFile);
			
			LeagueDTO leagueDTO = rankingProcessAssembler.getLeagueDTO(fileLines);
			
			Map<String, Integer> map = rankingProcessAssembler.getRankingPointsMap(leagueDTO);
			
			rankingList = rankingProcessAssembler.getRankingList(map);
			
		} else {
			System.out.println("[Error] The file can not be null");
		}
		
		return rankingList;
		
	}

	@Override
	public void printOutput(List<RankingDTO> rankingList) {
		
		System.out.println("\n\n");
		System.out.println("Ranking Table:");
		System.out.println("\n");
		
		Comparator<RankingDTO> compareRP_TN = Comparator
				.comparing(RankingDTO::getRankingPoints).reversed()
				.thenComparing(RankingDTO::getTeamName);

		
		List<RankingDTO> sortedList = rankingList.stream()
				.sorted(compareRP_TN)
				.collect(Collectors.toList());
		
		int i = 1;
		
		for (RankingDTO rankingDTO : sortedList) {
			
			StringBuilder stringBuilder = new StringBuilder();
			
			if (i > 1) {
				calcualtePositionNum(i, rankingDTO, sortedList.get(i-2));
				stringBuilder.append(rankingDTO.getPosition());				
			} else {
				rankingDTO.setPosition(i);
				stringBuilder.append(i);
			}
			
			stringBuilder.append(". ");
			stringBuilder.append(rankingDTO.getTeamName());
			stringBuilder.append(", ");
			stringBuilder.append(rankingDTO.getRankingPoints());
			stringBuilder.append(" ");
			stringBuilder.append(getPointStr(rankingDTO.getRankingPoints()));
			
			System.out.println(stringBuilder);
			
			i ++;
			
		}
		
	}


	/*
	 * ==================================================================== 
	 *                         private methods
	 * ====================================================================
	 */
	
	/**
	 * Method to valdiate details inf file to process
	 * 
	 * @param filename name of the file
	 * @param rankingFile File to be processed
	 * @return true if valid false other wise
	 */
	private boolean areFileDetailsValid(String filename, File rankingFile) {
		
		boolean areValid = false;
		
		if (filename.equals(FILE_NAME_EXTENSION)) {
			
			long fileSize = FileUtils.sizeOf(rankingFile); 
			
			System.out.println("File Size [" + fileSize + "]");
			
			if(fileSize <= FILE_MAX_SIZE) {
				areValid = true;					
			} else {
				System.out.println("[Error] The max permitted size for a file is [" + FILE_MAX_SIZE + "]");
			}
			
		} else {
			System.out.println("[Error] The file name must be  [" + FILE_NAME_EXTENSION + "]");
		}
		
		return areValid;
		
	}
	
	/**
	 * Method to generate the String for points (pt or pts)
	 *  
	 * @param points int
	 * @return String
	 */
	private String getPointStr(int points) {
		
		String str = null;
		
		if(points == 1) {
			str = "pt";
		} else {
			str = "pts";
		}
		
		return str;
		
	}
	
	/**
	 * Method to calcualte position
	 * 
	 * @param i iteration variable
	 * @param currentDTO current {@link RankingDTO}
	 * @param previousDTO previous {@link RankingDTO}
	 */
	private void calcualtePositionNum(int i, RankingDTO currentDTO, RankingDTO previousDTO) {
		
		if (i > 1) {
			
			if(currentDTO.getRankingPoints() == previousDTO.getRankingPoints()) {
				currentDTO.setPosition(previousDTO.getPosition());
			} else {
				currentDTO.setPosition(previousDTO.getPosition() + 1);
			}
			
		} else {
			currentDTO.setPosition(1);
		}
		
	}
	
}


