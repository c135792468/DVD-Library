/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author 69591
 */
public class DVDLibraryView {
    private UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }
    
    //print the main menu
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVD");
        io.print("2. Create New DVD");
        io.print("3. View a DVD by id#");
        io.print("4. Remove a DVD");
        io.print("5. Edit a DVD");
        io.print("6. Search a DVD by title");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }
    
    public DVD creatNewDVD() {
        //ask user for dvd id
        String id = io.readString("Please enter a unique Id for the DVD");
        DVD currentDVD = new DVD(id);
        //call the editDVDInfo to create or update the dvd info
        return editDVDInfo(currentDVD);
    }
    
    public void displayEditDVDBanner () {
        io.print("=== Edit DVD ===");
    }
    
    public void displayDVDNotfound () {
        io.print("DVD not found");
    }
    
    public boolean dVDExist(DVD dVD){
        return dVD != null;
    }
    
    //create or edit DVD info
    public DVD editDVDInfo(DVD currentDVD){
        String title = io.readString("Please enter title");
        String releaseDate = io.readString("Please enter release date");
        String mPAARating = io.readString("Please enter MPAA rating");
        String director = io.readString("Please enter director name");
        String studio = io.readString("Please enter studio");
        String userNote = io.readString("Please enter note");
        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setmPAARating(mPAARating);
        currentDVD.setStudio(studio);
        currentDVD.setUserNote(userNote);
        currentDVD.setDirector(director);
        return currentDVD;
    }
    
    public void displayCreateSuccessBanner() {
    io.print(
            "DVD successfully created");
    }
    
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVD ===");
    }
    
    //display all DVDs in the collection
    public void displayDVDList(List<DVD> dVDList) {
        for (DVD currentDVD : dVDList) {
            printDVDInfo(currentDVD);
        }
    }
    
    //display DVD by title
    public void displayDVDByTitle(List<DVD> dVDList, String title) {
        for (DVD currentDVD : dVDList) {
            if(currentDVD.getTitle() == null ? title == null : currentDVD.getTitle().equals(title) ){
                displayDVD(currentDVD);
            }     
        }
    }
    
    // print DVD info
    public void printDVDInfo(DVD currentDVD){
        String dVDInfo = String.format("#%s : %s %s %s %s %s %s",
                currentDVD.getDVDId(),
                currentDVD.getTitle(),
                currentDVD.getReleaseDate(),
                currentDVD.getmPAARating(),
                currentDVD.getDirector(),
                currentDVD.getStudio(),
                currentDVD.getUserNote()
                );
                io.print(dVDInfo);
    }
    
    public void displayDisplayDVDBanner () {
        io.print("=== Display DVD ===");
    }
    
    public String getDVDIdChoice() {
        return io.readString("Please enter the DVD ID.");
    }
        
    public String getDVDTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }
    
    public void displayDVD(DVD dVD) {
        if (dVD != null) {
            io.print(dVD.getDVDId());
            io.print(dVD.getTitle());
            io.print(dVD.getReleaseDate());
            io.print(dVD.getmPAARating());
            io.print(dVD.getDirector());
            io.print(dVD.getStudio());
            io.print(dVD.getUserNote());
        } else {
            io.print("No such DVD.");
        }
    }
    
    public void displayRemoveDVDBanner () {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(DVD dVDRecord) {
        if(dVDRecord != null){
            io.print(" DVD successfully removed.");
        }else{
            io.print("No such DVD.");
        }
    }
   
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public boolean keepGoing(){
        String respond = io.readString("Enter 'Back' back to main menu, anything else to continue.");
        return !"Back".equals(respond);
    }
    
}
