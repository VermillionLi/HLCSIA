import Algorithms.IndividualStatsCalculator;
import POJO.BattleLog;
import POJO.IndividualInformation;
import Servlet.BrawlAPIAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

public class ServletTest {
    public static void main(String[] args) throws JsonProcessingException {
        String playerID = "randomgarbage";
        //make sure jS end cannot type space
        if (playerID == null || playerID.isEmpty()) {
            System.out.println("null/empty");
            return;
        }

        ObjectMapper om = new ObjectMapper();
        //ensure Jackson doesn't fail on some stupid things
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        BrawlAPIAccess api = new BrawlAPIAccess();
        //retrieve JSON file from API and deserialize into POJO
        BattleLog items = om.readValue(api.getBatteLog(playerID), BattleLog.class);
        //instantiate calculation class on POJO, automatically calculates
        IndividualStatsCalculator calc = new IndividualStatsCalculator(items.getItems());
        //turn calculation into a POJO for the frontend
        IndividualInformation info = calc.getInformation();
        String JSON = om.writeValueAsString(info);
        //return JSON to frontend//send info: status: 'ok' or 'error'
        System.out.println(JSON);
    }
}
