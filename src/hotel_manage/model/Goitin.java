/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_manage.model;

import java.io.Serializable;

/**
 *
 * @author 503
 */
public class Goitin implements Serializable{
    final long serialVersionUID = 24L;
    Room room;
    String keyword;
    boolean isAdd;

    public Goitin(Room room) {
        this.room = room;
        this.isAdd = true;
    }

    public Goitin(String keyword){
        this.keyword = keyword;
        this.isAdd = false;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean isIsAdd() {
        return isAdd;
    }

    public void setIsAdd(boolean isAdd) {
        this.isAdd = isAdd;
    }
    
    
    
    
    
    
}
