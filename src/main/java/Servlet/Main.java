package Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);

        //servlet container, used to manage session servlets
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        //http://localhost:8080/
        context.setContextPath("/");

        //from the resource folder, find webapp where the html.index is at
        URL resource = Main.class.getResource("/webApp/");
        if (resource == null) {
            throw new RuntimeException("Static resource directory not found!");
        }

        //necessary to send initial static content
        ServletHolder staticServlet = new ServletHolder("default", new DefaultServlet());
        context.addServlet(staticServlet, "/");
        context.setResourceBase(resource.toExternalForm());
        //adds servlets to context

       //Jetty in this case (idk why) cannot find the servlet holder for string name of classes
        //must manually add them using servletholder

        //battle log does nothing except return battle log, raw data
        context.addServlet(new ServletHolder(new BattleLogServlet()), "/battleLog");
        //returns processed info for individuals
        context.addServlet(new ServletHolder(new IndividualStatsServlet()), "/statChecker");
        //the crowning jewel of this project, featuring wall of honor & shame
        context.addServlet(new ServletHolder(new Walls()), "/walls");
        //sql database UI
        context.addServlet(new ServletHolder(new SQLiteServlet()), "/sql");
        context.addServlet(new ServletHolder(new SQLiteTokenServlet()), "/sql/token");
        context.addServlet(new ServletHolder(new SQLiteBrawlerServlet()), "/sql/brawler");
        server.setHandler(context);


        //link server to servlets and start
        server.start();
        System.out.println("Server started on http://localhost:8081");
        System.out.println("remember to clear cache for JS updates to take effect!");
        server.join();
        //WILL NOT PROCESS FURTHER

    }
}

