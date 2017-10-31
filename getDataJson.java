package com.hof.test_mailchimp;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class getDataJson 
{
    public static void main( String[] args ) throws ClientProtocolException, IOException, java.text.ParseException
    {
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	HttpGet httpGet = new HttpGet("https://us10.api.mailchimp.com/3.0/lists?since_date_created=2016-06-10T00:00:00+00:00");
        httpGet.addHeader("Authorization", "apikey d5aaea35b00f4b85fec2e00b14b2be0d-us10");
        
        /*MultipartEntityBuilder builder = MultipartEntityBuilder.create();
             
        HttpEntity multipart = builder.build();

        uploadFile.setEntity(multipart);*/

        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity responseEntity = response.getEntity();
       // System.out.println(EntityUtils.toString(responseEntity));
        
        JSONObject obj = new JSONObject(EntityUtils.toString(responseEntity));
        //System.out.println(obj.toString());
        JSONArray arr = obj.getJSONArray("lists"); 
        int length = arr.length();
        /*for(int i=0;i<length;i++)
        {
        	System.out.println(arr.getJSONObject(i).get("send_time").toString());
        	String tme = arr.getJSONObject(i).get("send_time").toString();
        	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date d = null;
			try {
				d = formatter.parse(tme);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date res= new java.sql.Date(d.getTime());
			//data[i][j]= res;
			System.out.println(res);
        }*/
        for(int i=0;i<length;i++)
        {
	        String name= arr.getJSONObject(i).getString("name");
	        System.out.println(name);
        }
        
        httpClient.close();
    }
}