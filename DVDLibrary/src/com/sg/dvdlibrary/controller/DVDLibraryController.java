/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImp;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author 69591
 */
public class DVDLibraryController {

    private DVDLibraryView view;

    public DVDLibraryController(DVDLibraryView view, DVDLibraryDao dao) {
        this.view = view;
        this.dao = dao;
    }
    private DVDLibraryDao dao;
    
    // app start
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try{
        //ask user to selecte a function
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listDVDs();
                    break;
                case 2:
                    createDVD();
                    break;
                case 3:
                    viewDVDById();
                    break;
                case 4:
                    removeDVD();
                    break;
                case 5:
                    editDVD();
                    break;
                case 6:
                    viewDVDByTitle();
                    break;
                case 7:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
        } catch (DVDLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    //print main menu
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    //create new DVD
    private void createDVD() throws DVDLibraryDaoException {
        do{
            view.displayCreateDVDBanner();
            DVD newDVD = view.creatNewDVD();
            dao.addDVD(newDVD.getDVDId(), newDVD);
            view.displayCreateSuccessBanner();
        }while(view.keepGoing());
    }
    
    //print the list of dvds
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        //get all dvds
        List<DVD> dVDList = dao.getAllDVDs();
        view.displayDVDList(dVDList);
    }
    
    //look for a dvd by id
    private void viewDVDById() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        //ask for dvd id
        String dVDId = view.getDVDIdChoice();
        //go look for dvd by id
        DVD dVD = dao.getDVDById(dVDId);
        view.displayDVD(dVD);
    }
    
    //remove a dvd
    private void removeDVD() throws DVDLibraryDaoException {
        do{
            view.displayRemoveDVDBanner();
            //ask for dvd id
            String dVDId = view.getDVDIdChoice();
            //remove the dvd by id
            DVD removedDVD = dao.removeDVD(dVDId);
            view.displayRemoveResult(removedDVD);
        }while(view.keepGoing());
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    
    //edit a dvd
    private void editDVD() throws DVDLibraryDaoException {
        do{
            view.displayEditDVDBanner();
            //ask user for the id
            String dVDId = view.getDVDIdChoice();
            //get the dvd by id
            DVD dVD = dao.getDVDById(dVDId);
            //if dvd found, edit it
            if(view.dVDExist(dVD)){
                DVD currentDVD = view.editDVDInfo(dVD);
                dao.editDVD(currentDVD.getDVDId(), currentDVD);
            }
            else{
                view.displayDVDNotfound();
            }
        }while(view.keepGoing());
    }
    
    //look for a dvd by title
    private void viewDVDByTitle() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        //ask user for title
        String dVDTitle = view.getDVDTitleChoice();
        //get all the dvds
        List<DVD> dVDList = dao.getAllDVDs();
        //go look for the dvd that has the title
        view.displayDVDByTitle(dVDList, dVDTitle);
    }
}
