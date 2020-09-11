package com.mikami.aad2020leaderboard;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiUtil {
    private ApiUtil (){}
    public static final String BASE_API_URL = "https://gadsapi.herokuapp.com";
    public static final String LEARNING_PARAM = "/api/hours";
    public static final String SKILL_IQ_PARAM = "/api/skilliq";


    public static URL buildUrl (String param){
        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL + param).buildUpon()
                .build();
        try{
            url = new URL(uri.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }


    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try{
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if(hasData){
                return scanner.next();
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            Log.d("Error", e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }
    }

    public static ArrayList<Learner> getLearnersFromJson(String json){
        final String NAME = "name";
        final String HOUR = "hours";
        final String SCORE = "score";
        final String COUNTRY = "country";
        final String BADGEURL = "badgeUrl";

        ArrayList<Learner> learners = new ArrayList<Learner>();
        try{
            JSONArray arrayLearners = new JSONArray(json);
            int numberOfLearners = arrayLearners.length();
            for (int i = 0; i<numberOfLearners; i++){
                JSONObject learnerJSON = arrayLearners.getJSONObject(i);
                Learner learner = new Learner(
                        learnerJSON.getString(NAME),
                        (learnerJSON.isNull(HOUR)?0 : learnerJSON.getInt(HOUR)),
                        (learnerJSON.isNull(SCORE)?0 : learnerJSON.getInt(SCORE)),
                        learnerJSON.getString(COUNTRY),
                        learnerJSON.getString(BADGEURL));
                learners.add(learner);

            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return learners;
    }
}
