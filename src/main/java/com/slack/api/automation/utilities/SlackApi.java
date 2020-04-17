package com.slack.api.automation.utilities;

import com.slack.api.automation.services.APIServices;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Reporter;
import java.util.*;

public class SlackApi {

    public Response createNewChannel(String channelName) {
        Map map = new HashMap<>();
        map.put("name", channelName);
        Reporter.log("Creating Channel " + channelName);
        return APIServices.createPostResponseWithFormParameter(RestAssured.baseURI + PropertyFileReader.getPATH_CREATE_CHANNEL(), map);
    }

    public Response joinChannel(String channelName) {
        Map map = new HashMap<>();
        map.put("name", channelName);
        Reporter.log("Joining Channel " + channelName);
        return APIServices.createPostResponseWithFormParameter(RestAssured.baseURI + PropertyFileReader.getPATH_JOIN_CHANNEL(), map);
    }

    public Response renameChannel(String channelId, String channelName) {
        Map map = new HashMap<>();
        map.put("channel", channelId);
        map.put("name", channelName);
        Reporter.log("Renaming Channel with new name" + channelName);
        return APIServices.createPostResponseWithFormParameter(RestAssured.baseURI + PropertyFileReader.getPATH_RENAME_CHANNEL(), map);
    }

    public Response listAllChannel() {
        return APIServices.createGetResponse(RestAssured.baseURI + PropertyFileReader.getPATH_LIST_CHANNELS());
    }

    public Response archiveChannel(String channelId) {
        Map map = new HashMap<>();
        map.put("channel", channelId);
        Reporter.log("Archiving Channel " + channelId);
        return APIServices.createGetResponseWithQueryParameter(RestAssured.baseURI + PropertyFileReader.getPATH_ARCHIVE_CHANNEL(), map);
    }

    public boolean channelExistInTheList(List<String> list, String key) {
        for (String string : list) {
            if (string.equals(key))
                return true;
        }
        return false;
    }

    public boolean getIsArchivedStatusFromJSONById(String response, String idValue) throws Exception {
        Object obj = new JSONParser().parse(response);
        ArrayList<JSONObject> array = ((JSONArray) ((JSONObject) obj).get("channels"));
        for (JSONObject j : array)
            if (j.get("id").equals(idValue)) {
                return  (boolean) j.get("is_archived");
            }
        return false;
    }
}
