package Servlet;

import Algorithms.IndividualStatsCalculator;
import Algorithms.WallAlgorithm;
import Algorithms.WallHonor;
import Algorithms.WallShame;
import JDBC.SQLite;
import POJO.BattleLog;
import POJO.IndividualInformation;
import POJO.WallInformation;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.SQLException;
import java.util.Map;
//must use jartaka, javax support from jetty ended
//must switch to jartaka


//must use jartaka, javax support from jetty ended
//must switch to jartaka


@WebServlet("/walls")
public class Walls extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        res.setContentType("application/json");

        PrintWriter out = res.getWriter();
        String WallType = req.getParameter("WallType");
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
            out.println(JSON);
            //resets
            out.flush();
        } catch (SQLException e) {
            out.println("{\"POJOStatus\":\"SQL ERROR cannot find your friends\"}");
            throw new RuntimeException(e);
        }
    }
}