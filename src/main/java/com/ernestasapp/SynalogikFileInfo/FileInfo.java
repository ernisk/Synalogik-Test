package com.ernestasapp.SynalogikFileInfo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileInfo(Path in): Path parameter from src folder to file
 * 
 * For reading and reports statistics on file (preferably .txt)
 *
 */
public class FileInfo 
{
	private String fileString,wordFreqString;
	private List<String> wordList;
	private  List<Integer> wordCount = new ArrayList<Integer>(); //Using ArrayList for more complex appending and retrieving than a simple array
	private List<Integer> wordCountLabel = new ArrayList<Integer>(); //Label is the same index as to the count it refers to 
	private int totalWords,i,wordFreqMax;
	private double averageLength;
	private boolean found;
	
	public FileInfo(Path in)
	{
		if(read(in)==null) { //Check if file is valid and set object fileString if valid
			System.err.println("FileInfo("+in+"): Invalid Path.");
			return; //Exit if invalid
		}
		
		if(fileString.isBlank()) {//Check if file is empty
			System.out.println("File is Empty.");
			return;
		}
		
		wordList = new ArrayList<>(Arrays.asList(fileString.split("[ . ,\\s]+"))); //Split whole String into individual words (a series of characters,including some punctuation, separated by space,newline,comma and full stop)
		if(wordList.get(0)=="") {//When there's an empty space/newline before the first word, split() includes "" as the first element in wordList so this removes that
			wordList.remove(0);
		}
		
		totalWords=wordList.size(); //Set totalWords length from length of word array
		wordCount.add(0);
		wordCountLabel.add(0);//Add initial values to fill position 0, (no words can have 0 characters)
		
		for(String e : wordList) {
			found=false;
			for(i=0 ; i<wordCountLabel.size() && wordCountLabel.get(i)<=e.length(); i++) { //use for loop with index of label and insertion index to keep order of label
				if(wordCountLabel.get(i)==e.length()) {//Find if word length is already in the array
					wordCount.set(i,wordCount.get(i)+1); //Increment by 1
					found=true;
					break;
				}
			}
			if(found==false) {
				wordCount.add(i,1);
				wordCountLabel.add(i,e.length());
			}
			averageLength+=e.length();
		}
		averageLength=averageLength/totalWords;//Get Average
		averageLength=Math.round(averageLength * 1000.0) / 1000.0; //Round to 3dp
		
		System.out.println("Word count = "+totalWords);
		System.out.println("Average word length = "+averageLength);
		
		for(i=1 ; i<wordCountLabel.size(); i++) { //loop through array and print out the wordLength label and count
			System.out.println("Number of words of length "+wordCountLabel.get(i)+" is "+wordCount.get(i));
			
			//Also check for most frequent length
			if(wordCount.get(i)>wordFreqMax) {//Check if current count is greater than current max
				wordFreqMax=wordCount.get(i);//Update Max and String
				wordFreqString="The most frequently occurring word length is "+wordCount.get(i)+", for word lengths of "+wordCountLabel.get(i);
			}
			else if(wordCount.get(i)==wordFreqMax) {//Equal frequency adds length label to string
				wordFreqString+=" & "+wordCountLabel.get(i);
			}
		}
		
		System.out.println(wordFreqString);
		
	}
	
	public String read(Path in){ //Method for reading text file and converting to String output or null if invalid
		
		try
		{
		    byte[] bytes = Files.readAllBytes(in);
		    fileString = new String (bytes);
		    return fileString;
		} 
		catch (Exception e) 
		{
		    return null; //File does not exist or can't be read
		}
	}
	
	//Getters for Testing

	public String getWordFreqString()
	{
		return wordFreqString;
	}

	public List<String> getWordList()
	{
		return wordList;
	}

	public List<Integer> getWordCount()
	{
		return wordCount;
	}

	public List<Integer> getWordCountLabel()
	{
		return wordCountLabel;
	}

	public int getTotalWords()
	{
		return totalWords;
	}

	public int getWordFreqMax()
	{
		return wordFreqMax;
	}

	public double getAverageLength()
	{
		return averageLength;
	}

	
	
	
}
