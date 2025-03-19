import POJO.BattleList;
import Servlet.BrawlAPIAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.ls.LSOutput;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class Jackson {

    public static void main(String[] args) throws IOException {


        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //please spell battlelog right
        BrawlAPIAccess api = new BrawlAPIAccess();
        //do some calculations blah blah#PRYQQLRJV", "battlelog"
        System.out.println(api.getBatteLog("PRYQQLRJV"));

       BattleList items = om.readValue(api.getBatteLog("PRYQQLRJV"), BattleList.class);
        int test = items.getItems().size();
        Map<String, Integer> jsonMap = Map.of("test", test);
        //map is necessary to 'wrap' the primitive/standalone object to give it value
        //out
        String json = om.writeValueAsString(jsonMap);
        System.out.println(json);
    }
}
