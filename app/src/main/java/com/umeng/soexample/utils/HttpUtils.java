package com.umeng.soexample.utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by W on 2018/12/10.
 */

public class HttpUtils {
    public static String getStringFromHttp(String urlString){
        String result = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.connect();
            if (connection.getResponseCode() == 200){
                InputStream is = connection.getInputStream();
                result = getStringFromInputStream(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
    public static String post(String urlStr,String phone,String pass) throws Exception {
        String result = "";
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setConnectTimeout(5000);
        OutputStream outputStream = connection.getOutputStream();
        String params = "phone="+phone+"&pwd=" +pass;
        outputStream.write(params.getBytes());
        if (connection.getResponseCode() == 200){
            InputStream inputStream = connection.getInputStream();
            result = getStringFromInputStream(inputStream);
            Log.e("post: zzz", result);

        }

        return result;


    }

    private static String getStringFromInputStream(InputStream is) {
        String result = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int length = -1;
        byte[] buffer = new byte[1024];
        try {
            while ((length = is.read(buffer,0,buffer.length))!= -1){
                baos.write(buffer,0,length);
                baos.flush();
            }
            result = baos.toString();
            baos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
