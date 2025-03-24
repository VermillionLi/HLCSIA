package JDBC;

import java.sql.*;

public class SQLite {

    static String SQLUrl = "jdbc:sqlite:BrawlHall.db";
    //it's one l
    static Statement st;
    static ResultSet rs;

    public SQLite() throws SQLException {
        Connection con = DriverManager.getConnection(SQLUrl);
        st = con.createStatement();
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
        return x;
    }
     void createBadBrawlerTable(){
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
     void createAPITable() {
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

     void dropAllTable() throws SQLException {
        if(checkExist("APIToken"))
            st.executeUpdate("DROP  TABLE APIToken");
        if(checkExist("BrawlerID"))
            st.executeUpdate("DROP TABLE BrawlerID");
    }

     boolean checkExist(String table) throws SQLException {
        rs = st.executeQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='" + table + "'");
        if (rs.getInt("count(*)") == 1) {
            return true;
        }
        return false;
    }

}

//WARNING: SQLITE DOES NOT SUPPORT SCHEMAS
