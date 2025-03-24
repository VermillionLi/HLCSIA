import POJO.BattleLog;
import Servlet.BrawlAPIAccess;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class Jackson {

    public static void main(String[] args) throws IOException {
/**
 * WORKING (POJOS) as of 3/21/12:27 AM
 *
 */

        ObjectMapper om = new ObjectMapper();
        //we don't have to deserialize everything, not failing on some random shit is important
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //please spell battlelog right
        BrawlAPIAccess api = new BrawlAPIAccess();
        //do some calculations blah blah#PRYQQLRJV", "battlelog"
        System.out.println(api.getBatteLog("PRYQQLRJV"));




       BattleLog items = om.readValue(api.getBatteLog("8OQQ9YRGL"), BattleLog.class);

        String json = om.writeValueAsString(items);
        System.out.println(json);
        int test = items.getItems().length;
        Map<String, Integer> jsonMap = Map.of("test", test);
        //map is necessary to 'wrap' the primitive/standalone object to give it value
        //out
        json = om.writeValueAsString(jsonMap);
        System.out.println(json);
    }
}
