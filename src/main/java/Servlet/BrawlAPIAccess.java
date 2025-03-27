package Servlet;

import JDBC.SQLite;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;

public class BrawlAPIAccess {
            //String school = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjVlN2M5ZmMzLTBmZDItNGI5NC1iMjg5LTFkYmMyOGUxZTNiMCIsImlhdCI6MTczOTM5Nzc5Niwic3ViIjoiZGV2ZWxvcGVyL2I1NGE2Mjk1LWY5NzItYTc1My03MjliLTY4OTVlNjk4NTg2MyIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiNjkuNTYuMTI3LjIzOSJdLCJ0eXBlIjoiY2xpZW50In1dfQ.D-Q6wAxeIIYOX5kjvc3D4j_GxyWs-wI0en1rcZNT7tJUK_a1qtlJwlUZx_Sod6gAB_I6hawWCxpJvKW52bp1Pg";
    //String tokenHeome = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImQ0MmI5OTkwLTQ2OTQtNGE1Yi05ZjEyLWY4OGYxN2VlY2NiYSIsImlhdCI6MTczOTQ5NzQ3Mywic3ViIjoiZGV2ZWxvcGVyL2I1NGE2Mjk1LWY5NzItYTc1My03MjliLTY4OTVlNjk4NTg2MyIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiNzYuMTM1LjUwLjI1MiJdLCJ0eXBlIjoiY2xpZW50In1dfQ.g6kWA12zbLlkSidwyM1lyIoV9o3NNWbwi4czM-VBuOnnOYCmstcvgR7O9fwJf-zc6klMsgveTnWBEo6ZIr7cbg";
    String id;
        //my ID: #PRYQQLRJV
    String response;
    String query;

    String token;




    public BrawlAPIAccess() {
        query = "https://api.brawlstars.com/v1";
        {
            try {
                //oh... you are doing another st here, becareful
                token = new SQLite().getAPI();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public String getBatteLog(String id){
        query = "https://api.brawlstars.com/v1";
        query += "/players/%23" + id;
        query += "/battlelog";
        //do, not, spell, battlelog wrong
        return getResponse();
    }
    public String getStats(String id){
        query = "https://api.brawlstars.com/v1";
        query += "/players/%23" + id;
        return getResponse();
    }
    private void sendAPI(){
        try {
            this.id = id;
            HttpRequest r = HttpRequest.newBuilder()
                  // .uri(URI.create("https://api.brawlstars.com/v1/players/%23PRYQQLRJV/battlelog"))
                    .uri(URI.create(query))
                    .header("Authorization", "Bearer " + token)
                    .GET()
                    .build();
            HttpClient c = HttpClient.newHttpClient();
            HttpResponse<String> response = c.send(r, HttpResponse.BodyHandlers.ofString());

            this.response = response.body();
        }catch (Exception e){
            System.out.println("Brawl API fail");
            throw new RuntimeException(e);
        }
    }
    public String getResponse() {
        sendAPI();
        return response;
    }
}

