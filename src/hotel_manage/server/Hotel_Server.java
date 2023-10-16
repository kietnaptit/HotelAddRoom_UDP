/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_manage.server;


import hotel_manage.model.Goitin;
import hotel_manage.model.Hoidap;
import hotel_manage.model.Room;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 503
 */
public class Hotel_Server {

    public Hotel_Server(){};
    public void RunServer() throws IOException, ClassNotFoundException, SQLException{
        System.out.println("Hotel Server Notification");
        RoomDAO roomDAO = new RoomDAO();
        DatagramSocket ss = new DatagramSocket(6969);
        DatagramSocket ss2 = new DatagramSocket(6868);
        byte[] incomingData = new byte[1024];
        byte[] incomingData2 = new byte[1024];
        while(true){
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            ss.receive(incomingPacket);
            byte[] data = incomingPacket.getData();
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            Goitin goitin = (Goitin)is.readObject();
            InetAddress address = incomingPacket.getAddress();
            int port = incomingPacket.getPort();
            String reply = "";
            if(goitin.isIsAdd()){
                Room room = goitin.getRoom();
                Hoidap hoidap = new Hoidap();
                if(!roomDAO.isRoomExisited(room)){
                    if(roomDAO.addARoom(room)){
                        List<Room> rooms = roomDAO.listAllRoom();
                        hoidap = new Hoidap(rooms);
                        System.out.println("ROOM " + room.getId() + " was added successfully");
                    }else{
                        System.out.println("ROOM " + room.getId() + " was not added successfully");
                        String notitfication = "Something when wrong. Try again";
                        hoidap = new Hoidap(notitfication);
                    }
                }else{
                    System.out.println("ROOM " + room.getId() + " is exisited in database");
                    String notitfication = "Room is exisited, add another one or check your input";
                    hoidap = new Hoidap(notitfication);
                }
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(outputStream);
                os.writeObject(hoidap);
                byte[] replyByte = outputStream.toByteArray();
                DatagramPacket replyPacket = new DatagramPacket(replyByte, replyByte.length, address, port);
                ss.send(replyPacket);
            }else{
                String keyword = goitin.getKeyword();
                System.out.println("Client dang thuc hien tim phong voi Keyword " + keyword);
                List<Room> rooms = roomDAO.searchRoom(keyword);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(outputStream);
                os.writeObject(rooms);
                byte[] data2 = outputStream.toByteArray();
                DatagramPacket sendPacket2 = new DatagramPacket(data2, data2.length, address, port);
                ss.send(sendPacket2);
               
            }
            
        }
    }
    
}
