package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Responsible for reading a csv file and holding the contents of it
 * 
 * @author Rajesh K
 *
 */
public class CSVReader {

//	private static final String CSV_FILE_NAME = "test.csv";
	private static final String CSV_DELIMITER = "\t";	

	/**
	 * reads the elements of each row of a CSV file provided and evaluates whether given element is present at given row number.
	 * 
	 * @param rowNumber - to look at
	 * @param element -  to look for
	 * @return - Boolean
	 */
	public boolean readElements(final String csvFileName, final int rowNumber, final String element) {
		final Map<Integer, List<String>> csvCache = readCSVFile(csvFileName, CSV_DELIMITER);
		System.out.println("File Contents are::" + csvCache);
		final List<String> elements = csvCache.get(rowNumber);
		return elements != null && elements.contains(element);
	}

	private Map<Integer, List<String>> readCSVFile(final String fileName, final String delimiter) {
		final Map<Integer, List<String>> csvCache = new HashMap<Integer, List<String>>();
		final File testCsvFile = new File("./test/resources/" + fileName);
		System.out.println("File reading has started for.." + testCsvFile.getAbsolutePath());
		BufferedReader bufferedReader = null;
		try {
			final InputStreamReader streamReader = new InputStreamReader(new FileInputStream(testCsvFile));
			bufferedReader = new BufferedReader(streamReader);
			int rowNumber = 1;
			String line = "";
			while (line != null) {
				line = bufferedReader.readLine();
				if (line != null) {
					csvCache.put(rowNumber, Arrays.asList(line.split(delimiter)));
					rowNumber++;
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			System.out.println("File operations had failed  due to :" + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unexpected problem has occurred due to :" + e);
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				System.out.println("Unable to close the readers due to:" + e);
				e.printStackTrace();
			}
		}
		
		return csvCache;
	}

}
