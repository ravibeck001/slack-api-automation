package com.slack.api.automation.utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JSONRead {

    public List<String> getValueFromJSON(String response, String key) throws Exception {
        Object obj = new JSONParser().parse(response);
        List<String> values = new LinkedList<>();
        ArrayList<JSONObject> array = ((JSONArray) ((JSONObject) obj).get("channels"));
        for (JSONObject j : array)
            values.add((String) j.get(key));
        return values;
    }
}