package com.mx.eon.ranking.ranking.test.util;

import static com.mx.eon.ranking.ranking.constants.RankingConstants.FILE_NAME_EXTENSION;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.eon.ranking.ranking.util.FileUtil;

/**
 * Class to test {@link FileUtil}
 * 
 * @author Noe Salazar
 * 
 * @version 1.0
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class FileUtilTest {
	
	/**
	 * {@link FileUtil}
	 */
	@Autowired
	private FileUtil fileUtil;
	
	/**
	 * File for testing
	 */
	File empty_valid;
	
	/**
	 * File for testing
	 */
	File filled_valid;
	
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
		
		/* Filename */
		StringBuilder fileName = null;
		
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
		
	}
	
	/**
	 * method to test readFileIntoStrList
	 */
	@Test
	void testReadFileIntoStrList() {
		
		/*Test 1*/
		List<String> fileLines = fileUtil.readFileIntoStrList(null);
		
		System.out.println("fileLines " + fileLines);
		
		assertNotNull(fileLines);
		assertTrue(fileLines.isEmpty(), "should be empty");
		
		
		/*Test 2*/
		fileLines = fileUtil.readFileIntoStrList(new File("file"));
		
		System.out.println("fileLines " + fileLines);
		
		assertNotNull(fileLines);
		assertTrue(fileLines.isEmpty(), "should be empty");
		
		
		/*Test 3*/
		fileLines = fileUtil.readFileIntoStrList(empty_valid);
		
		System.out.println("fileLines " + fileLines);
		
		assertNotNull(fileLines);
		assertTrue(fileLines.isEmpty(), "should be empty");
		
		
		/*Test 4*/
		fileLines = fileUtil.readFileIntoStrList(filled_valid);
		
		System.out.println("fileLines " + fileLines);
		
		assertNotNull(fileLines);
		assertFalse(fileLines.isEmpty(), "should not be empty");
		assertEquals(5, fileLines.size());
		
	}

}
