package Servlet;

import JDBC.SQLite;
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


@WebServlet("/sql/brawler")
public class SQLiteBrawlerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        //else it doesn't properly send
        res.setContentType("application/json");

        PrintWriter out = res.getWriter();
        String brawler = req.getParameter("brawler");
        SQLite sqLite = null;
        try {
            sqLite = new SQLite();
            System.out.println("servlet attempts to add: " + brawler);
            sqLite.addBadBrawlers(brawler);
            out.println("just put anything in here...");
            out.flush();
            sqLite.getSt().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}