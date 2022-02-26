package com.mx.eon.ranking.ranking.util;

import static com.mx.eon.ranking.ranking.constants.RankingConstants.VOID;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Class with utils for files
 * 
 * @author Noe Salazar
 * 
 * @version 1.0
 *
 */
@Component
public class FileUtil {

	/**
	 * Lee el archivo file para almacenar los registros encontrados en el en 
	 * una lista de Strings
	 * 
	 * @param file, archivo a leer
	 * @return Lista con registros encontrados en el archivo 
	 */
	public List<String> readFileIntoStrList(File file) {
		
		List<String> fileLines = new ArrayList<String>();
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		try {
			
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			String line = VOID;
			
			while(line != null) {
				
				line = bufferedReader.readLine();
				
				if(line != null) {
					fileLines.add(line);					
				}
				
			}
			
			bufferedReader.close();
			
		} catch(Exception e) {
			
			System.out.println("[Error] problems reading the file");
			
		} 
		
		return fileLines;
		
	} 
	
}
