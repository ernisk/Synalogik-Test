# Synalogik Java Code Test
### by Ernestas Kaziulis



## Installation
- ***Requires Java SE Development Kit 17 (jdk-17)***

- Made on Windows Eclipse 2022-03, but should work with any IDE

- After every method you can run Maven Build with "clean verify" on Eclipse or "mvn package" [check] to build the code and test it.

- If you have imported the code as a java project and not a jar file you can add the project as a project dependency in other java projects build path to then use FileInfo class

### Method 1: Clone Repository
> 1. Open your terminal for git
> 2. Go to your desired cloning destination
> 3. type in your terminal "git clone https://github.com/ernisk/Synalogik-Test.git"
> 4. DONE: This repository should now be in your folder
> 5. NOTE: If you are on Eclipse and cloned the repo; you can go to "File>Open Projects from File System" and import it to your workspace as a maven folder

### Method 2: Download package
> 1. Click Code button at the top of the repository and press Download Zip
> 2. Import project as an Archive File (.zip) to your IDE
> 3. DONE: Project should now be in your workspace

### Method 3: Install fileinfo.jar
> 1. Download fileinfo.jar from this repository
> 2. Import jar file as library to your java project
> 3. DONE: You can now refer in your code as import com.ernestasapp.SynalogikFileInfo.FileInfo;
> 4. NOTE: You won't be able to run maven build as the jar does not include a pom file

## Code Description

### main/FileInfo.java
> FileInfo(Path in) Constructor Taking in Path parameter (Make sure the path is from your src folder to your text file) for example: 
> new FileInfo(Paths.get("src/test/resources/com/ernestasapp/SynalogikFileInfo/testfile.txt"));
> FileInfo then reads the path using the Read() function and converts the file text into a string.
> Calculations for the word count and average is done with the assumption of a word being a series of characters,including some punctuation, separated by 
> space,newline,comma or full stop.
> Output of the file infomation is then system outputed to the console.

### test/FileInfoTest.java
> FileInfoTest contains 5 tests for FileInfo java class
> 1. Test constructor runs
> 2. Exits on an invalid file (path leads to a non existing file)
> 3. Exits on an empty file, using empty.txt (no characters not including the .split() characters)
> 4. Contains correct values, using testfile.txt with the sample text from the java test email (calculations and variables after file has been successfully read)
> 5. Outputs values to console in correct format given the java test email

