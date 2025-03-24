import Algorithms.IndividualStatsCalculator;
import POJO.BattleLog;
import POJO.IndividualInformation;
import Servlet.BrawlAPIAccess;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Algo {
    public static void main(String[] args) throws IOException {

        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //please spell battlelog right
        BrawlAPIAccess api = new BrawlAPIAccess();
        //do some calculations blah blah#PRYQQLRJV", "battlelog"

        //sad's accoutn 8OQQ9YRGL
        //my account: PRYQQLRJV
        BattleLog items = om.readValue(api.getBatteLog("PRYQQLRJV"), BattleLog.class);
        System.out.println(om.writeValueAsString(items));
        IndividualStatsCalculator calc = new IndividualStatsCalculator(items.getItems());
        System.out.println(calc.peek());
        //map is necessary to 'wrap' the primitive/standalone object to give it value
        //out
        IndividualInformation info = calc.getInformation();
        String json = om.writeValueAsString(info);
        System.out.println(json);
    }
}
