package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 69591
 */
public interface DVDLibraryDao {
    DVD addDVD(String dVDId, DVD dVD) throws DVDLibraryDaoException;
    List<DVD> getAllDVDs() throws DVDLibraryDaoException;
    DVD removeDVD(String dVDId) throws DVDLibraryDaoException;
    DVD getDVDById(String dVDId) throws DVDLibraryDaoException;
    DVD editDVD(String dVDId, DVD dVD) throws DVDLibraryDaoException;
}
