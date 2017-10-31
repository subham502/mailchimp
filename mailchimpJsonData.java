package com.hof.test_mailchimp;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class mailchimpJsonData {
	public static void getJsonData(String token) throws ClientProtocolException, IOException
    {
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	String endpoint = "https://us13.api.mailchimp.com/3.0/";
    	HttpGet httpGet = new HttpGet("https://login.mailchimp.com/oauth2/metadata");
        httpGet.addHeader("Authorization", "OAuth "+token);

        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity responseEntity = response.getEntity();
        JSONObject obj = new JSONObject(EntityUtils.toString(responseEntity));
    }
}
