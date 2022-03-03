/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

/**
 *
 * @author 69591
 */
public class DVD {
    private String title;
    private String releaseDate;
    private String mPAARating;
    private String director;
    private String studio;
    private String userNote;
    private String dVDId;

    public void setDVDId(String iD) {
        this.dVDId = iD;
    }

    public String getDVDId() {
        return dVDId;
    }

    public DVD(String iD) {
        this.dVDId = iD;
    }
    
    public DVD(){
        
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setmPAARating(String mPAARating) {
        this.mPAARating = mPAARating;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getmPAARating() {
        return mPAARating;
    }

    public String getDirector() {
        return director;
    }

    public String getStudio() {
        return studio;
    }

    public String getUserNote() {
        return userNote;
    }
    
}
