import htmlParser.ToHtmlParser;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        ToHtmlParser parser = new ToHtmlParser();
        HashMap<String, String> h = new HashMap<>();
        h.put("1","1");

        System.out.println(parser.dailyPatientList(h));

    }
}
