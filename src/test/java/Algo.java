import Algorithms.IndividualStatsCalculator;
import POJO.Battle;
import POJO.BattleList;
import Servlet.BrawlAPIAccess;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class Algo {
    public static void main(String[] args) throws IOException {

        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //please spell battlelog right
        BrawlAPIAccess api = new BrawlAPIAccess();
        //do some calculations blah blah#PRYQQLRJV", "battlelog"
        System.out.println(api.getBatteLog("PRYQQLRJV"));


        BattleList items = om.readValue(api.getBatteLog("PRYQQLRJV"), BattleList.class);

        int test = items.getItems().size();
        IndividualStatsCalculator calc = new IndividualStatsCalculator(items.getItems());

        //map is necessary to 'wrap' the primitive/standalone object to give it value
        //out
        String json = om.writeValueAsString(calc);
        System.out.println(json);
    }
}
