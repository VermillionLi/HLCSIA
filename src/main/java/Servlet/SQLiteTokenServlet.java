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


@WebServlet("/sql/token")
public class SQLiteTokenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //else it doesn't properly send
        res.setContentType("application/json");

        PrintWriter out = res.getWriter();
        String token = req.getParameter("token");
        try {
            SQLite sqLite = new SQLite();
            System.out.println("Server attempting to change token: " + token);
            sqLite.changeAPI(token);
            out.println("just put anything in here...");
            out.flush();
            sqLite.getSt().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}