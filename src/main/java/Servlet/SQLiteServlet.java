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


@WebServlet("/sql")
public class SQLiteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        try {
            SQLite sqLite = new SQLite();
            String request = req.getParameter("request");
            System.out.println(req.getParameter("request"));
            if(request.equalsIgnoreCase("removePlayers")){
                System.out.println("attempting to erase friends data");
                sqLite.dropBrawlerIDs();
            }else if(request.equalsIgnoreCase("removeBad")){
                sqLite.dropBadBrawlers();
                System.out.println("attempting to erase badBrawler data");
            }else{
                System.out.println("request was unknown");
                //nothing happens
            }
            sqLite.getSt().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("just put anything in here...");
        out.flush();
    }


}