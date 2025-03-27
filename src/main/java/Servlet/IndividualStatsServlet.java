package Servlet;

import Algorithms.IndividualStatsCalculator;
import JDBC.SQLite;
import POJO.BattleLog;
import POJO.IndividualInformation;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.SQLException;
//must use jartaka, javax support from jetty ended
//must switch to jartaka


//must use jartaka, javax support from jetty ended
//must switch to jartaka


@WebServlet("/statChecker")
public class IndividualStatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        res.setContentType("application/json");

        PrintWriter out = res.getWriter();
        String playerID = req.getParameter("playerID");;
        ObjectMapper om = new ObjectMapper();
        //ensure Jackson doesn't fail on some stupid things
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        BrawlAPIAccess api = new BrawlAPIAccess();
        //retrieve JSON file from API and deserialize into POJO
        BattleLog items = om.readValue(api.getBatteLog(playerID), BattleLog.class);
        //instantiate calculation class on POJO, automatically calculates

        IndividualStatsCalculator calc = new IndividualStatsCalculator(items.getItems());
        //automatically updates the Database with new player if exist

        //turn calculation into a POJO for the frontend
        IndividualInformation info = calc.getInformation();

        if (!Double.isNaN(info.getWinLoseRate())){ //this guy is real
            try {
                SQLite sql = new SQLite();
                sql.addPlayer(playerID);;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        String JSON = om.writeValueAsString(info);
        //return JSON to frontend
        out.println(JSON);
        //resets
        out.flush();



    }
}