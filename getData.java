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

public class getData 
{
    public static void main( String[] args ) throws ClientProtocolException, IOException
    {
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	HttpGet httpGet = new HttpGet("https://login.mailchimp.com/oauth2/metadata");
        httpGet.addHeader("Authorization", "OAuth d5aaea35b00f4b85fec2e00b14b2be0d");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity responseEntity = response.getEntity();
        //System.out.println(EntityUtils.toString(responseEntity));
        JSONObject obj = new JSONObject(EntityUtils.toString(responseEntity));
        String accountName = obj.getString("accountname");
        System.out.println("acount name:"+accountName);
        String endpoint =  obj.getString("api_endpoint");
        System.out.println("endpoint:"+endpoint);
        JSONObject result = obj.getJSONObject("login");
        String loginName = result.getString("login_name");
        System.out.println("login name:"+loginName);
        httpClient.close();	
    }
}