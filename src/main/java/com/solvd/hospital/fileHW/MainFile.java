package com.solvd.hospital.fileHW;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainFile {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> totalWords = new HashMap<>();
        String s = FileUtils.readFileToString(new File(System.getProperty("user.dir") + File.separator + "FileReaderWriter"+File.separator +"fileToRead.txt"), "UTF-8");
        String[] allWords = StringUtils.split(s);

        for(int i=0; i< allWords.length; i++){
            totalWords.put((allWords[i]), (totalWords.get(allWords[i])==null)?1:totalWords.get(allWords[i])+1);
        }

        FileUtils.writeStringToFile(new File(System.getProperty("user.dir") + File.separator + "FileReaderWriter"+File.separator +"fileToRead.txt"),"\n"+ StringUtils.remove(StringUtils.remove(StringUtils.replace(totalWords.toString(),", ","\n"),'}'),'{'), "UTF-8", true);

    }
}
