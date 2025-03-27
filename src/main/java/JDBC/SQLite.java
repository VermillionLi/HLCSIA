package JDBC;

import POJO.BattleLog;
import POJO.Hello;
import Servlet.BrawlAPIAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;

public class SQLite {

    static String SQLUrl = "jdbc:sqlite:BrawlHall.db";
    //it's one l

    //please avoid concurrency, NO STATIC
     Statement st;
     //CANNOT BE STATIC, CLOSE EVERY STATEMENT AFTER DONE
     ResultSet rs;
    Connection con;

    public SQLite() throws SQLException {

        try {
            con = DriverManager.getConnection(SQLUrl);
            st = con.createStatement();
            //random stuff in database confic, auto commit has to be off in order for it to be working
            //check multi threading error:https://stackoverflow.com/questions/12194972/reason-for-java-sql-sqlexception-database-in-auto-commit-mode
            con.setAutoCommit(true);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void addPlayer(String tag) throws SQLException, JsonProcessingException {
        //given: the guy us real
        BrawlAPIAccess api = new BrawlAPIAccess();
        //get his name
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Hello hello = om.readValue(api.getStats(tag), Hello.class);
        st = con.createStatement();
            st.executeUpdate("INSERT OR IGNORE INTO BrawlerID (nickname, ID) VALUES " +
                    "('"+ hello.getName() +"'," +
                    "'" + tag + "');");
        System.out.println("attempts to add : " + tag);
        st.close();
    }
    public BattleLog[] getBattleLogs() throws SQLException, JsonProcessingException {
        rs = st.executeQuery("SELECT count(*) FROM BrawlerID");
        BattleLog[] battleLogs = new BattleLog[rs.getInt("count(*)")];
        rs = st.executeQuery("SELECT * FROM BrawlerID");
        BrawlAPIAccess api = new BrawlAPIAccess();
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        for (int i = 0; i < battleLogs.length; i++) {
            //put .next up front
            rs.next();
            System.out.println(rs.getString("ID"));
            System.out.println(api.getBatteLog(rs.getString("ID")));
            BattleLog bl = om.readValue(api.getBatteLog(rs.getString("ID")), BattleLog.class);
            bl.setNameTag(rs.getString("nickname"), rs.getString("ID"));
            battleLogs[i] = bl;

        }
        st.close();
        return battleLogs;

    }
    public String[] retrieveBadBrawlerTable() throws SQLException {
        String[] x = {"table not found"};
        if(checkExist("BadBrawlers")){
            rs = st.executeQuery("SELECT  COUNT(*) FROM BadBrawlers");
             x = new String[rs.getInt("COUNT(*)")];
             //ENSURE LEXICOGONAL SORT
            rs = st.executeQuery("SELECT * FROM BadBrawlers ORDER BY LOWER(Brawler)");
            for (int i = 0; i < x.length; i++) {
                rs.next();
                x[i] = rs.getString("Brawler").toUpperCase();
            }
        }else{
            System.out.println("DNE");
        }
        st.close();
        return x;
    }

    /**
     * changes the API toke, there can only be one item in this table
     */
     public void changeAPI(String token) throws SQLException {
         if(checkExist("APIToken")) {
             st.executeUpdate("DROP TABLE APIToken");
         }
         createAPITable();
         st.executeUpdate("INSERT INTO APIToken (Token) VALUES " +
                 "('"+ token+"');");
         st.close();
     }

    /**
     * currently the API table with TOKENS is limited to only one TOKEN
     */
    void createAPITable() {
        try {
            if (!checkExist("APIToken")) {
                st.executeUpdate("CREATE TABLE APIToken (\n" +
                        "    Token TEXT PRIMARY KEY \n" +
                        ");\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     public String getAPI() throws SQLException {
        rs = st.executeQuery("SELECT * FROM APIToken LIMIT 1");
       String toReturn = rs.getString("Token");
         st.close();
        return toReturn;

     }

    void createBadBrawlerTable(){
        try {
            if (!checkExist("BadBrawlers")) {
                st.executeUpdate("CREATE TABLE BadBrawlers (\n" +
                        "    Brawler TEXT PRIMARY KEY \n" +
                        ");\n");
                st.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     void createIDTable() {
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

    /**
     * destroys and recreates badBrawler table
     * no, you don't get to unselect kit lily or edgar from this table, I hate all 3 of them
     */


    public void dropBadBrawlers() throws SQLException {
        if(checkExist("BadBrawlers")) {
            st.executeUpdate("DROP TABLE BadBrawlers");
        }
        createBadBrawlerTable();
        st.executeUpdate("INSERT OR IGNORE INTO BadBrawlers (Brawler) VALUES " +
                "('KIT');");
        st.executeUpdate("INSERT OR IGNORE INTO BadBrawlers (Brawler) VALUES " +
                "('LILY');");
        st.executeUpdate("INSERT OR IGNORE INTO BadBrawlers (Brawler) VALUES " +
                "('EDGAR');");

    }
    public void dropBrawlerIDs() throws SQLException {
        if(checkExist("BrawlerID")) {
            st.executeUpdate("DROP TABLE BrawlerID");
        }
            createIDTable();
        st.close();
    }
    public void addBadBrawlers(String brawler) throws SQLException {
        //funny you can put anything in here
        st.executeUpdate("INSERT OR IGNORE INTO BadBrawlers (Brawler) VALUES " +
                "('"+ brawler + "');");
        st.close();
    }


     boolean checkExist(String table) throws SQLException {
        rs = st.executeQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='" + table + "'");
        if (rs.getInt("count(*)") == 1) {
            st.close();
            return true;
        }
        return false;

    }

    public  Statement getSt() {
        return st;
    }

    public Connection getCon() {
        return con;
    }
}

//WARNING: SQLITE DOES NOT SUPPORT SCHEMAS
