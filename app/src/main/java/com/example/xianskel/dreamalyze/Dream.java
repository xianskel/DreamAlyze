package com.example.xianskel.dreamalyze;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Dream {
    //GET ALL DREAMS METHOD THAT WILL RETURN THE DATE OF THE DREAM AND THE SUBJECTS
    //Context is got by going Context context = getApplicationContext(); in the activity
    public static String getAllDreams(Context context){
        try {
            File f = new File(context.getFilesDir() + "/" + "dreams.json");
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String mResponse = new String(buffer);
            return mResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error";
    }

    public static boolean clearAllDreams(Context context){
        File f = new File(context.getFilesDir() + "/" + "dreams.json");
        //returning a boolean is completely optional - may change this to void
        return f.delete();
    }

    public static String getDreamByDate(String date, Context context){
        try{
            //convert JSON String to JSON Object Array
            String allDreams = getAllDreams(context);
            JSONArray dreams = new JSONArray(allDreams);

            for(int i = 0; i < dreams.length(); i++){
                JSONObject dream = dreams.getJSONObject(i);
                String dreamDate = (String)dream.get("date");

                if(dreamDate.equals(date)){
                    return (String)dream.get("dream");
                }
            }
        }
        catch(JSONException j){
            j.printStackTrace();
        }

        return "No dream found";

    }

    public static void addDream(String date, String newDream, Context context) throws IOException, JSONException {
        //so i create an empty JSON Array
        JSONArray allDreams = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        //create the JSON dream object - create the name pairs
        //if there is already a dream for this date
        if(!getDreamByDate(date,context).equals("No dream found")){
            allDreams.put(jsonObj);

            if(Dream.getAllDreams(context).length() > 5){
                JSONArray dreams = new JSONArray(Dream.getAllDreams(context));
                //add previous dreams to the new array
                for(int i = 0; i < dreams.length(); i++){
                    JSONObject dream = dreams.getJSONObject(i);
                    String dreamDate = (String)dream.get("date");

                    if(dreamDate.equals(date)){
                        dream.put("dream", dream);
                    }
                }

                Dream.clearAllDreams(context);
            }
        }
        else{
            jsonObj.put("date", date);
            jsonObj.put("dream", newDream);
            allDreams.put(jsonObj);
            //i then add all the previous dreams to it to overcome the problem
            //of array storing objects for a short length of time
            //first make sure that this isn't the first dream being entered
            if(Dream.getAllDreams(context).length() > 5){
                JSONArray dreams = new JSONArray(Dream.getAllDreams(context));
                //add previous dreams to the new array
                for(int i = 0; i < dreams.length(); i++){
                    allDreams.put(dreams.getJSONObject(i));
                }

                Dream.clearAllDreams(context);
            }
            //remove all the previous dreams as allDreams will already have them
            //--- as what will happen is the JSON array will have some stored in short term
            //memory and it will essentially duplicate the array
            //passing the boolean true means we can append to the file
            File file = new File(context.getFilesDir(), "dreams.json");
            FileOutputStream outF = new FileOutputStream(file , true);
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(outF);
            //append the new dream onto previous dreams
            outStreamWriter.append(allDreams.toString());
            //clear the writer to save data
            outStreamWriter.flush();
        }

    }

}
