import POJO.Hello;
import Servlet.BrawlAPIAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jacksonName {
    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //please spell battlelog right
        BrawlAPIAccess api = new BrawlAPIAccess();
        //do some calculations blah blah#PRYQQLRJV", "battlelog"
        System.out.println(api.getStats("PRYQQLRJV"));

        Hello hello = om.readValue(api.getStats("PRYQQLRJV"), Hello.class);

        //map is necessary to 'wrap' the primitive/standalone object to give it value
        //out
        String json = om.writeValueAsString(hello);
        System.out.println(json);
    }
}
