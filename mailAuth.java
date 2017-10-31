package com.hof.test_mailchimp;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class mailAuth 
{
    public static void main( String[] args ) throws ClientProtocolException, IOException
    {
    	CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost("https://login.mailchimp.com/oauth2/token");


        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        builder.addTextBody("grant_type", "authorization_code");
        builder.addTextBody("client_id", "283225773926");
        builder.addTextBody("client_secret", "4fc0a87bf4c3fd54692a5d7dcdc40499");
        builder.addTextBody("redirect_uri", "https://tpconnect.yellowfin.bi/getToken.jsp");
        builder.addTextBody("code", "652a1399e4313c896696ad4580109c12");
        //builder.addBinaryBody("file", new File("..."), ContentType.APPLICATION_OCTET_STREAM, "file.ext");

        HttpEntity multipart = builder.build();

        uploadFile.setEntity(multipart);

        CloseableHttpResponse response = httpClient.execute(uploadFile);
        HttpEntity responseEntity = response.getEntity();
        
        JSONObject obj = new JSONObject(EntityUtils.toString(responseEntity));
        String token = obj.getString("access_token");
        System.out.println("access tokent::"+token);
    }
}
