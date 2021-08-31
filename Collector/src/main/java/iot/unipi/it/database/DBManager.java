package iot.unipi.it.database;

public class DBManager {
    private static String DB_URL;
    private static String user;
    private static String password;

    public DBManager(String URL, String user, String password){
        DB_URL = URL;
        this.user = user;
        this.password = password;
    }
}
