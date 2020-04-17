package com.slack.api.test.automation;

import com.slack.api.automation.services.APIServices;
import com.slack.api.automation.utilities.JSONRead;
import com.slack.api.automation.utilities.PropertyFileReader;
import com.slack.api.automation.utilities.RandomGenerator;
import com.slack.api.automation.utilities.SlackApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class SlackAPITest {
    @BeforeMethod
    void prepareRequestSpecification() {
        PropertyFileReader.loadPropertyFile();
        APIServices.setRestAssuredRequestResource();
    }

    @Test
    void runTest() throws Exception {

        SlackApi slackApi = new SlackApi();
        JSONRead jsonRead = new JSONRead();

        //Create a new Channel
        Response response = slackApi.createNewChannel("channel-ravi" + RandomGenerator.generateRandomString(5));
        Assert.assertEquals(response.getStatusCode(), 200, "Error in Response");

        Map<Object, Object> responseMap;
        responseMap = response.jsonPath().getMap("channel");

        String channelCreated = (String) responseMap.get("name");
        String channelId = (String) responseMap.get("id");

        //Join the newly created Channel
        response = slackApi.joinChannel(channelCreated);
        Assert.assertEquals(response.getStatusCode(), 200, "Error in joinChannel");

        String newChannelName = "new-name-" + RandomGenerator.generateRandomString(3);
        response = slackApi.renameChannel(channelId, newChannelName);
        Assert.assertEquals(response.getStatusCode(), 200, "Error in renameChannel");

        response = slackApi.listAllChannel();
        Assert.assertEquals(response.getStatusCode(), 200, "Error in listAllChannel");

        //Get All channels list
        String jsonResponseString = response.asString();
        List<String> channelList = jsonRead.getValueFromJSON(jsonResponseString, "name");
        Reporter.log("Channel List After Rename Channel : "+ channelList);

        //Validate if the Channel name has changed successfully
        boolean verify = slackApi.channelExistInTheList(channelList, newChannelName);
        Assert.assertTrue(verify, "Rename Channel Failed");

        //Archive the Channel
        response = slackApi.archiveChannel(channelId);
        Assert.assertEquals(response.getStatusCode(), 200, "Archive Channel Failed");

        //List Channel after Archive
        response = slackApi.listAllChannel();
        Assert.assertEquals(response.getStatusCode(), 200, "Error in listAllChannel");

        //Get All channels list
        jsonResponseString = response.asString();

        //Validate if the Channel Archive successfully by checking "is_archived" status
        verify = slackApi.getIsArchivedStatusFromJSONById(jsonResponseString, channelId);
        Assert.assertTrue(verify, "Channel Archive Failed");

    }

}