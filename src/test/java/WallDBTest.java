import Algorithms.WallAlgorithm;
import Algorithms.WallHonor;
import Algorithms.WallShame;
import JDBC.SQLite;
import POJO.BattleLog;
import POJO.WallInformation;
import Servlet.BrawlAPIAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.net.IDN;
import java.sql.*;

public class WallDBTest {
    static String SQLUrl = "jdbc:sqlite:BrawlHall.db";
    //it's one l
    static Statement st;
    static ResultSet rs;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, JsonProcessingException {

        String WallType = "HONOR";
        if (WallType == null || WallType.isEmpty()) {
            System.out.println("{\"status\":\"Missing WallType\"}");
            return;
        }

        ObjectMapper om = new ObjectMapper();
        //ensure Jackson doesn't fail on some stupid things
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        BrawlAPIAccess api = new BrawlAPIAccess();
        //retrieve JSON file from API and deserialize into POJO
        WallAlgorithm wall;

        try {
            SQLite sql = new SQLite();
            if(WallType.equalsIgnoreCase("honor")){
                wall = new WallHonor(sql.getBattleLogs());
            }else{
                wall = new WallShame(sql.getBattleLogs());
            }

            WallInformation info = wall.getInformation();

            String JSON = om.writeValueAsString(info);
            //return JSON to frontend

            //send info: status: always 'ok'
            System.out.println(JSON);

        } catch (SQLException e) {
            System.out.println("{\"status\":\"SQL ERROR cannot find your friends\"}");
            throw new RuntimeException(e);
        }






    }


    public static BattleLog[] crazyShit() throws SQLException, JsonProcessingException {
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
            BattleLog bl = om.readValue(api.getBatteLog(rs.getString("ID")), BattleLog.class);
            bl.setNameTag(rs.getString("nickname"), rs.getString("ID"));
            battleLogs[i] = bl;

        }
        return battleLogs;
    }


}
