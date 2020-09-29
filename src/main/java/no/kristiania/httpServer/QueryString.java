package no.kristiania.httpServer;

import java.util.HashMap;
import java.util.Map;

public class QueryString {

    private Map<String, String> parameters = new HashMap<>();


    public QueryString(String queryString) {
        for (String parameter : queryString.split("&")) {
            int equalPos = parameter.indexOf('=');
            String value = parameter.substring(equalPos + 1);
            String parameterName = parameter.substring(0, equalPos);
            parameters.put(parameterName, value);
        }




    }

    public String getParameter(String name) {
        return parameters.get(name);
    }
}