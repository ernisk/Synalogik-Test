package com.ernestasapp.SynalogikFileInfo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Unit test for simple FileInfo.
 */
public class FileInfoTest 
{
	//Retrieve Console outputs for tests
			private final ByteArrayOutputStream outCurrent = new ByteArrayOutputStream();
			private final ByteArrayOutputStream errCurrent = new ByteArrayOutputStream();

			@BeforeEach
			public void getStreams() {
			    System.setOut(new PrintStream(outCurrent));
			    System.setErr(new PrintStream(errCurrent));
			}

			
			/*Test:
			 * 1.FileInfo constructor runs
			 * 2.Exit on invalid file
			 * 3.Exit on empty file
			 * 4.Correct values for Total,Average, Word Count, Frequency [testFile]
			 * 5.Correct console output [testFile]
			 */
			
			@Test
			@DisplayName("1.FileInfo constructor runs")
			public void testConstructor()
			{
				try {
					new FileInfo(Paths.get("src/test/resources/com/app/SynalogikFileInfo/testfile.txt"));
				} catch(Exception e){ /*Fail if object can't be made*/
					fail("Object not made");
				}
			}
			
			
			
			@Test
			@DisplayName("2.Exit on invalid file")
			public void testInvalidFile() {
				new FileInfo(null);
			    assertEquals("FileInfo(null): Invalid Path.", errCurrent.toString().trim()); //Test for equal outputs
			}
			
			@Test
			@DisplayName("3.Exit on empty file")
			public void testEmptyFile() {
				new FileInfo(Paths.get("src/test/resources/com/app/SynalogikFileInfo/empty.txt"));
			    assertEquals("File is Empty.", outCurrent.toString().trim());
			}
			

			@Test
			@DisplayName("4.Correct values for Total,Average, Word Count, Frequency")
			public void testValues() {
				FileInfo FileObject= new FileInfo(Paths.get("src/test/resources/com/app/SynalogikFileInfo/testfile.txt"));
				assertEquals(9, FileObject.getTotalWords());
				assertEquals(4.556, FileObject.getAverageLength());
				assertEquals("[0, 1, 1, 1, 2, 2, 1, 1]", FileObject.getWordCount().toString()); //Convert from ArrayList<Integer> to string for easier comparison
				assertEquals("[0, 1, 2, 3, 4, 5, 7, 10]", FileObject.getWordCountLabel().toString());
				assertEquals("The most frequently occurring word length is 2, for word lengths of 4 & 5", FileObject.getWordFreqString());
			}
			

			@Test
			@DisplayName("5.Correct console output")
			public void testOutput() {
				new FileInfo(Paths.get("src/test/resources/com/app/SynalogikFileInfo/testfile.txt"));
				String[] outLines=outCurrent.toString().split("\\n"); //Split whole console output string into lines equal to the expected lines in the test
			    assertEquals("Word count = 9", outLines[0].trim());
			    assertEquals("Average word length = 4.556", outLines[1].trim());
			    assertEquals("Number of words of length 1 is 1", outLines[2].trim());
			    assertEquals("Number of words of length 2 is 1", outLines[3].trim());
			    assertEquals("Number of words of length 3 is 1", outLines[4].trim());
			    assertEquals("Number of words of length 4 is 2", outLines[5].trim());
			    assertEquals("Number of words of length 5 is 2", outLines[6].trim());
			    assertEquals("Number of words of length 7 is 1", outLines[7].trim());
			    assertEquals("Number of words of length 10 is 1", outLines[8].trim());
			    assertEquals("The most frequently occurring word length is 2, for word lengths of 4 & 5", outLines[9].trim());
			}
			
			
			

			

}
