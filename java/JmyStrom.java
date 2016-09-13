/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeddsan.jrequests.mystromtest;

import android.annotation.TargetApi;
import android.os.Build;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class JmyStrom {
    
    private String ip = "";
    private double power = 0.0;
    private boolean state = false;
    private URL url = null;
    private String result = "";
    private JSONObject obj;
    
    public JmyStrom(String ip){
        this.ip = ip;
    }
    
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public double getPower(){
        try {
            url = new URL("http://"+ip+"/report");
        } catch (MalformedURLException ex) {
            Logger.getLogger(JmyStrom.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            assert url != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null;) {
                    result+=line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            obj = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            power=Double.parseDouble(obj.getString("power"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return power;
    }
    
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public boolean getState(){
        try {
            url = new URL("http://"+ip+"/report");
        } catch (MalformedURLException ex) {
            Logger.getLogger(JmyStrom.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            assert url != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null;) {
                    result+=line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            obj = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            state=Boolean.parseBoolean(obj.getString("relay"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return state;
    }
    
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setState(int state){
        try {
            url = new URL("http://"+ip+"/relay?state="+state);
        } catch (MalformedURLException ex) {
            Logger.getLogger(JmyStrom.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // get stream and read from it
        InputStream is = null;
        try {
            is = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // read from is
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void toggleState(){
        try {
            url = new URL("http://"+ip+"/toggle");
        } catch (MalformedURLException ex) {
            Logger.getLogger(JmyStrom.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // get stream and read from it
        InputStream is = null;
        try {
            is = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // read from is
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
