package com.example.rentaandsale.realestate.AnnouncementData;

/**
 * Created by elbhwashy on 12/17/2017.
 */

public class Announcement {
    private int announcementId;
    private String announcementTitle;
    private String announcementPrice;
    private String announcementType;
    private String announcementNumberOfRooms;
    private String announcementFloor;
    private String announcementDescription;


    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int id) {
        this.announcementId = id;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String price) {
        this.announcementTitle = price;
    }

    public String getAnnouncementPrice() {
        return announcementPrice;
    }

    public void setAnnouncementPrice(String price) {
        this.announcementPrice = price;
    }

    public String getAnnouncementType() {
        return announcementType;
    }

    public void setAnnouncementType(String type) {
        this.announcementType = type;
    }


    public String getAnnouncementNumberOfRooms(){return announcementNumberOfRooms;}
    public void setAnnouncementNumberOfRooms(String room){this.announcementNumberOfRooms = room;}

    public String getAnnouncementFloor(){return announcementFloor;}
    public void setAnnouncementFloor(String floor){this.announcementFloor = floor;}

    public String getAnnouncementDescription(){return announcementDescription;}
    public void setAnnouncementDescription(String description){this.announcementDescription = description;}
}
