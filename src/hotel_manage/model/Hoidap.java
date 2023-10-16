/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_manage.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 503
 */
public class Hoidap implements Serializable{
    final long serialVersionUID = 25L;
    List<Room> rooms;
    String notification;
    Boolean error;

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public String getNotification() {
        return notification;
    }

    public Boolean getError() {
        return error;
    }

    public Hoidap(List<Room> rooms) {
        this.rooms = rooms;
        this.error = false;
    }

    public Hoidap(String notification) {
        this.notification = notification;
        this.error = true;
    }

    public Hoidap() {
    }
    
    
    
    
}
