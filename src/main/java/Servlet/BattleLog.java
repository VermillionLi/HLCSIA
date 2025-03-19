package Servlet;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
//must use jartaka, javax support from jetty ended
//must switch to jartaka
import POJO.*;

//must use jartaka, javax support from jetty ended
//must switch to jartaka


@WebServlet("/battleLog")
public class BattleLog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        jakarta.servlet.ServletContext context = getServletContext();
        String json = req.getParameter("playerID");
        //test playerId: PRYQQLRJV
        context.log(json);
        String playerID ="";
        if (playerID.equals("") || playerID.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write("{\"error\":\"Missing playerID\"}");
            return;
        }
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        BattleList items = om.readValue(json, BattleList.class);

        System.out.println(items.getItems().size());


    }
}
