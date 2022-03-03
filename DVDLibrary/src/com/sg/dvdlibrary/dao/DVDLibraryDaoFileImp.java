/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author 69591
 */
public class DVDLibraryDaoFileImp implements  DVDLibraryDao {
    
    private Map<String, DVD> dVDs = new HashMap<>();
    public static final String DVD_FILE = "library.txt";
    public static final String DELIMITER = "::";
    
    @Override
    public DVD addDVD(String dVDId, DVD dVD) throws DVDLibraryDaoException {
        //load from dvd file
        loadRoster();
        //insert to map
        DVD prevDVD = dVDs.put(dVDId, dVD);
        //write to dvd file
        writeRoster();
        return prevDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        //load from dvd file
        loadRoster();
        return new ArrayList<DVD>(dVDs.values());
    }

    @Override
    public DVD removeDVD(String dVDId) throws DVDLibraryDaoException {
        //load from dvd file
        loadRoster();
        DVD removedDVD = dVDs.remove(dVDId);
        //write to dvd file
        writeRoster();
        return removedDVD;
    }

    @Override
    public DVD getDVDById(String dVDId) throws DVDLibraryDaoException {
        //load from dvd file
        loadRoster();
        return dVDs.get(dVDId);
    }    

    @Override
    public DVD editDVD(String dVDId, DVD dVD) throws DVDLibraryDaoException {
        //load from dvd file
        loadRoster();
        //insert to map
        DVD prevDVD = dVDs.put(dVDId, dVD);
        //write to dvd file
        writeRoster();
        return prevDVD;
    }
    
    //Turn a line of text from DVD file to a DVD object
    private DVD unmarshallDVD(String dVDAsText){
        String[] dVDTokens = dVDAsText.split(DELIMITER);
        String dVDId = dVDTokens[0];
        DVD dVDFromFile = new DVD(dVDId);
        dVDFromFile.setTitle(dVDTokens[1]);
        dVDFromFile.setReleaseDate(dVDTokens[2]);
        dVDFromFile.setmPAARating(dVDTokens[3]);
        dVDFromFile.setDirector(dVDTokens[4]);
        dVDFromFile.setStudio(dVDTokens[5]);
        dVDFromFile.setUserNote(dVDTokens[6]);
        return dVDFromFile;
    }
    
    //Load the DVD library from a file
    private void loadRoster() throws DVDLibraryDaoException {
        //Scanner for reading the file
        Scanner scanner;
        //try catch block for read file
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load DVD data into memory.", e);
        }
        String currentLine;
        DVD currentDVD;
        //Going through DVD_FILE line by line, decoding each line into
        //DVD object by calling the unmarshallDVD
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            dVDs.put(currentDVD.getDVDId(), currentDVD);
        }
        scanner.close();
    }
    
    //Turn a DVD object into a line of text for DVD file
    private String marshallDVD(DVD aDVD){
        String dVDAsTaxt = aDVD.getDVDId() + DELIMITER;
        dVDAsTaxt += aDVD.getTitle() + DELIMITER;
        dVDAsTaxt += aDVD.getReleaseDate() + DELIMITER;
        dVDAsTaxt += aDVD.getmPAARating() + DELIMITER;
        dVDAsTaxt += aDVD.getDirector() + DELIMITER;
        dVDAsTaxt += aDVD.getStudio() + DELIMITER;
        dVDAsTaxt += aDVD.getUserNote();
        return dVDAsTaxt;
    }
    
    // Writes all DVDs in the library to DVD_FILE
    private void writeRoster() throws DVDLibraryDaoException {
        PrintWriter out;
        // try catch block for writing to file exception
        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save DVD data.", e);
        }
        //Write out the DVD to DVD file
        String dVDAsText;
        //Get all the DVDS
        List<DVD> dVDList = this.getAllDVDs();
        //For each DVD turn into a String by marshallDVD and then write to file
        for (DVD currentDVD : dVDList) {
            dVDAsText = marshallDVD(currentDVD);
            out.println(dVDAsText);
            out.flush();
        }
        out.close();
    } 
}
