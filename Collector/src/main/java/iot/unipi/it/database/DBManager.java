package iot.unipi.it.database;

import iot.unipi.it.coap.resources.Actuator;
import iot.unipi.it.coap.resources.Thermometer;

import java.sql.*;

public class DBManager {
    private static String DB_URL;
    private static String user;
    private static String password;
    private PreparedStatement pstRegisterTherm;
    private PreparedStatement pstRegisterAct;
    private PreparedStatement pstInsert;
    private PreparedStatement pstRetrieveAct;
    private PreparedStatement pstRemoveTherm;

    public DBManager(String URL, String user, String password){
        DB_URL = URL;
        this.user = user;
        this.password = password;

        try{
            Connection conn = DriverManager.getConnection(DB_URL, this.user, this.password);
            pstRegisterTherm = conn.prepareStatement(
                "UPDATE rooms " +
                    "SET sensName = ? " +
                    "WHERE sensIP = ?;"
            );
            pstRegisterAct = conn.prepareStatement(
                "UPDATE rooms " +
                    "SET actName = ? " +
                    "WHERE actIP = ?;"
            );
            pstInsert = conn.prepareStatement(
                "INSERT INTO measures (sensIP, temperature, timestamp) " +
                    "VALUES (?, ?, ?);"
            );
            pstRetrieveAct = conn.prepareStatement(
                "SELECT actIP, actName " +
                    "FROM rooms " +
                    "WHERE sensIP = ?;"
            );
            pstRemoveTherm = conn.prepareStatement(
                "UPDATE rooms " +
                    "SET sensName = NULL " +
                    "WHERE sensIP = ?;"
            );


        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeTherm(Thermometer t){
        try{
            pstRemoveTherm.setString(1, t.getNodeAddress());
            pstRemoveTherm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insert(String nodeAddress, double temperature, long timestamp){
        try{
            pstInsert.setString(1, nodeAddress);
            pstInsert.setDouble(2, temperature);
            Timestamp ts = new Timestamp(timestamp);
            pstInsert.setTimestamp(3, ts);
            pstInsert.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void registerTherm(Thermometer t){
        try{
            pstRegisterTherm.setString(1, t.getResourceName());
            pstRegisterTherm.setString(2, t.getNodeAddress());
            pstRegisterTherm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void registerAct(Actuator a){
        try{
            pstRegisterAct.setString(1, a.getResourceName());
            pstRegisterAct.setString(2, a.getNodeAddress());
            pstRegisterAct.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Actuator retrieveAct(Thermometer t){
        Actuator a = null;
        try{
            pstRetrieveAct.setString(1, t.getNodeAddress());
            ResultSet rs = pstRetrieveAct.executeQuery();

            while(rs.next()){
                a = new Actuator(rs.getString("actIP"), rs.getString("actName"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return a;
    }
}
