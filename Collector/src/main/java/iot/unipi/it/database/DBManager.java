package iot.unipi.it.database;

import iot.unipi.it.coap.resources.Actuator;
import iot.unipi.it.coap.resources.Thermometer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                "INSERT INTO measures (sensIP, timestamp, temperature) " +
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

    }

    public void insert(String nodeAddress, double temperature, long timestamp){

    }

    public void registerTherm(Thermometer t){

    }

    public void registerAct(Actuator a){

    }

    public Actuator retrieveAct(Thermometer t){
        Actuator a = null;

        return a;
    }
}
