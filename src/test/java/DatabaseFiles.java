import POJO.Hello;
import Servlet.BrawlAPIAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.Stack;

public class DatabaseFiles {

    static String SQLUrl = "jdbc:sqlite:BrawlHall.db";
    //it's one l
    static Statement st;
    static ResultSet rs;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, JsonProcessingException {


        Connection con = DriverManager.getConnection(SQLUrl);
        st = con.createStatement();

        //rules are different for an embedded database

      dropAllTable();
      createAPITable();
      createBadBrawlersTable();
      createIDTable();
      populateID();
      populateBadBrawlerTable();
      addToken();
    }

    static void populateBadBrawlerTable() throws SQLException {
        if(checkExist("BadBrawlers")){

                    st.executeUpdate("INSERT OR IGNORE INTO BadBrawlers (Brawler) VALUES " +
                            "('MICO');");
                    st.executeUpdate("INSERT OR IGNORE INTO BadBrawlers (Brawler) VALUES " +
                            "('LILY');");
                    st.executeUpdate("INSERT OR IGNORE INTO BadBrawlers (Brawler) VALUES " +
                            "('EDGAR');");
                    st.executeUpdate("INSERT OR IGNORE INTO BadBrawlers (Brawler) VALUES " +
                            "('EDGAR');");
            st.executeUpdate("INSERT OR IGNORE INTO BadBrawlers (Brawler) VALUES " +
                    "('KIT');");
            st.executeUpdate("INSERT OR IGNORE INTO BadBrawlers (Brawler) VALUES " +
                    "('EMZ');");


            System.out.println("bad Update SUCCESS");
        }else{
            System.out.println("table does not exist");
        }
    }
   static void createBadBrawlersTable(){
        try {
            if (!checkExist("BadBrawlers")) {
                st.executeUpdate("CREATE TABLE BadBrawlers (\n" +
                        "    Brawler TEXT PRIMARY KEY \n" +
                        ");\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static void populateID() throws SQLException, JsonProcessingException {
        String exist = "8OQQ9YRGL";
        String me = "PRYQQLRJV";
        String notReal = "3kKkkk";

        Stack<String> items = new Stack<>();
        items.add(me);
        items.add(exist);
        items.add(notReal);

        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if(checkExist("BrawlerID")){
            while(!items.empty()){
                BrawlAPIAccess api = new BrawlAPIAccess();
                String item = items.pop();
                Hello hello = om.readValue(api.getStats(item), Hello.class);
                if(hello.getName() != null){
                    //alr boys this guy is real, add him in
                    st.executeUpdate("INSERT OR IGNORE INTO BrawlerID (nickname, ID) VALUES " +
                            "('"+ hello.getName() +"'," +
                            "'" + item + "');");
                }
            }
            System.out.println("Brawler Update SUCCESS");
        }else{
            System.out.println("table brawlerid does not exist");
        }

    }
    static void addToken() throws SQLException {
        if (checkExist("APIToken")) {
            st.executeUpdate("INSERT OR IGNORE INTO APIToken (Name, Token) VALUES " +
                    "('Joseph_Home'," +
                    "'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9');");
            st.executeUpdate("INSERT OR IGNORE INTO APIToken VALUES " +
                    "('School'," +
                    "'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9');");
            System.out.println("TOKEN update SUCCESS");
        }else{
            System.out.println("no such table (APIToken)");
        }

    }

    static void createAPITable() {
        try {
            if (!checkExist("APIToken")) {
                st.executeUpdate("CREATE TABLE APIToken (\n" +
                        "    Name  TEXT,\n" +
                        "    Token TEXT PRIMARY KEY \n" +
                        ");\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("process");
    }

    static void createIDTable() {
        try {
            if (!checkExist("BrawlerID")) {
                st.executeUpdate("CREATE TABLE BrawlerID (\n" +
                        "    nickname  TEXT,\n" +
                        "    ID TEXT PRIMARY KEY \n" +
                        ");\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void dropAllTable() throws SQLException {
        if(checkExist("APIToken"))
        st.executeUpdate("DROP  TABLE APIToken");
        if(checkExist("BrawlerID"))
        st.executeUpdate("DROP TABLE BrawlerID");
        if(checkExist("BadBrawlers"))
            st.executeUpdate("DROP TABLE BadBrawlers");
    }

    static boolean checkExist(String table) throws SQLException {
        rs = st.executeQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='" + table + "'");
        if (rs.getInt("count(*)") == 1) {
            return true;
        }
        return false;
    }

}


//creates a droppable test for testing purposes
//WARNING: SQLITE DOES NOT SUPPORT SCHEMAS, RAW DOG YOUR DATA






