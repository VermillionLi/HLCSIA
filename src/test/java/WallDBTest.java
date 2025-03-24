import Algorithms.WallHonor;
import Algorithms.WallShame;
import POJO.BattleLog;
import POJO.WallInformation;
import Servlet.BrawlAPIAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.IDN;
import java.sql.*;

public class WallDBTest {
    static String SQLUrl = "jdbc:sqlite:BrawlHall.db";
    //it's one l
    static Statement st;
    static ResultSet rs;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        Connection con = DriverManager.getConnection(SQLUrl);
        st = con.createStatement();
        BattleLog[] x = crazyShit();
        for (int i = 0; i < x.length; i++) {
            System.out.println(om.writeValueAsString(x[i]));
        }
        WallShame wallShame = new WallShame(x);
       /// WallHonor wallHonor = new WallHonor(x);
        WallInformation info = wallShame.getInformation();
        System.out.println(om.writeValueAsString(info));


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
