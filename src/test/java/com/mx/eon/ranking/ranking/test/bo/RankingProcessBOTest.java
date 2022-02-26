package com.mx.eon.ranking.ranking.test.bo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.mx.eon.ranking.ranking.constants.RankingConstants.FILE_NAME_EXTENSION;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.eon.ranking.ranking.bo.RankingProcessBO;
import com.mx.eon.ranking.ranking.dto.RankingDTO;

/**
 * Class to test {@link RankingProcessBO}
 * 
 * @author Noe Salazar
 * 
 * @version 1.0
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class RankingProcessBOTest {

	@Autowired
	private RankingProcessBO rankingProcessBO;
	
	/**
	 * File for testing
	 */
	File somefile;
	
	/**
	 * File for testing
	 */
	File no_extension;
	
	/**
	 * Path for testing
	 */
	File path;
	
	/**
	 * File for testing
	 */
	File empty_valid;
	
	/**
	 * File for testing
	 */
	File filled_valid;
	
	/**
	 * File for testing
	 */
	File filled_invalid;
	
	@BeforeAll
	void init() {
		
		/* File path */
		StringBuilder filePath = new StringBuilder();

		filePath.append("src");
		filePath.append(File.separator);
		filePath.append("test");
		filePath.append(File.separator);
		filePath.append("resources");
		filePath.append(File.separator);
		filePath.append("testFiles");
		filePath.append(File.separator);
		
		path = new File(filePath.toString());
		
		/* Filename */
		StringBuilder fileName = null;
		
		/*
		 * ==================================================================== 
		 *                         somefile.txt
		 * ====================================================================
		 */
		
		fileName = new StringBuilder();
		fileName.append(filePath);
		fileName.append("somefile.txt");
		
		somefile = new File(fileName.toString());
		
		/*
		 * ==================================================================== 
		 *                        no_extension.txt
		 * ====================================================================
		 */
		
		fileName = new StringBuilder();
		fileName.append(filePath);
		fileName.append("no_extension");
		
		no_extension = new File(fileName.toString());
		
		/*
		 * ==================================================================== 
		 *                          empty_valid
		 * ====================================================================
		 */
		
		fileName = new StringBuilder();
		fileName.append(filePath);
		fileName.append(FILE_NAME_EXTENSION);
		
		empty_valid = new File(fileName.toString());
		
		/*
		 * ==================================================================== 
		 *                          filled_valid
		 * ====================================================================
		 */
		
		fileName = new StringBuilder();
		fileName.append(filePath);
		fileName.append(File.separator);
		fileName.append("ok_fill");
		fileName.append(File.separator);
		fileName.append(FILE_NAME_EXTENSION);
		
		filled_valid = new File(fileName.toString());
		
		/*
		 * ==================================================================== 
		 *                          filled_invalid
		 * ====================================================================
		 */
		
		fileName = new StringBuilder();
		fileName.append(filePath);
		fileName.append(File.separator);
		fileName.append("error_fill");
		fileName.append(File.separator);
		fileName.append(FILE_NAME_EXTENSION);
		
		filled_invalid = new File(fileName.toString());
		
		
	}
	
	@Test
	void testPrintInstructions() {
		rankingProcessBO.printInstructions();
	}
	
	/*@Test*/
	void testgetFilePath() {
		rankingProcessBO.getFilePath();
	}
	
	@Test
	void testIsFilePathValid() {
		
		/*Test 1*/
		String filePath = null;
		
		boolean isValid = rankingProcessBO.isFilePathValid(filePath);
		
		assertFalse(isValid, "should be invalid");
		
		
		/*Test 2*/
		filePath = "";
		
		isValid = rankingProcessBO.isFilePathValid(filePath);
		
		assertFalse(isValid, "should be invalid");
		
		
		/*Test 3*/
		filePath = "str";
		
		isValid = rankingProcessBO.isFilePathValid(filePath);
		
		assertTrue(isValid, "should valid");
		
	}
	
	@Test
	void testIsFileValid() {
		
		/*Test 1*/
		boolean isValid = rankingProcessBO.isFileValid(null);
		
		assertFalse(isValid, "should be invalid");
		
		/*Test 2*/
		isValid = rankingProcessBO.isFileValid(new File("something"));
		
		assertFalse(isValid, "should be invalid");
		
		/*Test 3*/
		isValid = rankingProcessBO.isFileValid(path);
		
		assertFalse(isValid, "should be invalid");
		
		/*Test 4*/
		isValid = rankingProcessBO.isFileValid(somefile);
		
		assertFalse(isValid, "should be invalid");
		
		/*Test 5*/
		isValid = rankingProcessBO.isFileValid(empty_valid);
		
		assertTrue(isValid, "should be valid");
		
		/*Test 6*/
		isValid = rankingProcessBO.isFileValid(filled_valid);
		
		assertTrue(isValid, "should be valid");
		
		/*Test 7*/
		isValid = rankingProcessBO.isFileValid(filled_invalid);
		
		assertFalse(isValid, "should be invalid");
		
	}
	
	@Test
	void testPrintOutput() {
		
		RankingDTO grouchesDTO = new RankingDTO("Grouches", 0, 0, 0);
		RankingDTO snakesDTO = new RankingDTO("Snakes", 0, 1, 0);
		RankingDTO fcAwesomeDTO = new RankingDTO("FC Awesome", 0, 1, 0);
		RankingDTO lionsDTO = new RankingDTO("Lions", 0, 5, 0);
		RankingDTO tarantulasDTO = new RankingDTO("Tarantulas", 0, 6, 0);
		
		List<RankingDTO> rankingList = new ArrayList<>();
		
		rankingList.add(grouchesDTO);
		rankingList.add(snakesDTO);
		rankingList.add(fcAwesomeDTO);
		rankingList.add(lionsDTO);
		rankingList.add(tarantulasDTO);
		
		rankingProcessBO.printOutput(rankingList);
		
	}
	
}
