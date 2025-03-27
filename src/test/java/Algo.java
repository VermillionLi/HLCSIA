import Algorithms.IndividualStatsCalculator;
import POJO.BattleLog;
import POJO.Event;

import POJO.IndividualInformation;
import Servlet.BrawlAPIAccess;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


import java.util.ArrayList;

public class Algo {
    public static void main(String[] args) throws IOException {

        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //please spell battlelog right
        BrawlAPIAccess api = new BrawlAPIAccess();
        //do some calculations blah blah#PRYQQLRJV", "battlelog"

        //gets event rotation
        //THIS IS THE POJO BattleLog, NOT the servlet one
        //it's get battlelog not stats, stats is not what you want
        System.out.println(api.getBatteLog("8OQQ9YRGL"));
        BattleLog bl = om.readValue(api.getBatteLog("8OQQ9YRGL"), BattleLog.class);
        IndividualStatsCalculator calc = new IndividualStatsCalculator(bl.getItems());


       // IndividualStatsCalculator calc = new IndividualStatsCalculator(items.getItems());
        //out
        //IndividualInformation info = calc.getInformation();
        String json = om.writeValueAsString(calc.getInformation());
        System.out.println(json);
    }
}
