package Servlet;

import JDBC.SQLite;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.channels.GatheringByteChannel;
import java.sql.SQLException;
//must use jartaka, javax support from jetty ended
//must switch to jartaka


//must use jartaka, javax support from jetty ended
//must switch to jartaka


@WebServlet("/battleLog")
/**
 * not used at the moment, later versions might need this
 */
public class BattleLogServlet extends HttpServlet {
  /*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //else it doesn't properly send
        res.setContentType("application/json");

        PrintWriter out = res.getWriter();
        String playerID = req.getParameter("playerID");
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        BrawlAPIAccess api = new BrawlAPIAccess();
        POJO.BattleLog items = om.readValue(api.getBatteLog(playerID), POJO.BattleLog.class);
        String JSON = om.writeValueAsString(items);
        //ONLY THE INDIVIDUAL STATS CAN ADD NEW PLAYERS
        out.println(JSON);
        out.flush();


    }

     */


}

