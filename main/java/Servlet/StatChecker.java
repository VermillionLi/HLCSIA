package Servlet;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.Map;
//must use jartaka, javax support from jetty ended
//must switch to jartaka
import POJO.*;

//must use jartaka, javax support from jetty ended
//must switch to jartaka


@WebServlet("/statChecker")
public class StatChecker extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String playerID = req.getParameter("playerID");;
        if (playerID == null || playerID.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write("{\"error\":\"Missing playerID\"}");
            return;
        }
        System.out.println(playerID);
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //please spell battlelog right
        BrawlAPIAccess api = new BrawlAPIAccess();
        //do some calculations blah blah#PRYQQLRJV", "battlelog"
        System.out.println(api.getBatteLog("PRYQQLRJV"));
        BattleList items = om.readValue(api.getBatteLog("PRYQQLRJV"), BattleList.class);
        int test = items.getItems().size();
        Map<String, Integer> jsonMap = Map.of("test", test);
        //map is necessary to 'wrap' the primitive/standalone object to give it value
        //out
        String json = om.writeValueAsString(jsonMap);
        PrintWriter out = res.getWriter();
        out.println(json);
        out.flush();
        //test playerId: PRYQQLRJV
        //result WORKED!!!!


    }
}