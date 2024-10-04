package fvtc.edu.fileiodemo;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileIO {
    public static final String TAG = "FileIO";
    public ArrayList<String> readFile(String filename, AppCompatActivity activity){
        Log.d(TAG, "readFile: start");
        ArrayList<String> items = new ArrayList<String>();
        try{
            InputStream inputStream = activity.openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null){
                items.add(line);
            }
            bufferedReader = null;
            inputStreamReader.close();
            inputStream.close();

        }catch (Exception e){
            Log.d(TAG, "readFile: " + e.getMessage());
        }
        Log.d(TAG, "readFile: end");
        Log.d(TAG, "readFile: " + items);
        return items;
    }
    public void writeFile(String filename, AppCompatActivity activity, String[] items) /*throws FileNotFoundException if not in try catch block*/ {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(activity.openFileOutput(filename, Context.MODE_PRIVATE));
            Log.d(TAG, "writeFile: " + filename);
            String line = "";
            for(int counter = 0; counter < items.length; counter++){
                line = items[counter];
                if(counter < items.length - 1) {line += "\n";}
                writer.write(line);
                Log.d(TAG, "writeFile: " + line);
                writer.close();
            }

        } catch (FileNotFoundException e) {
            Log.d(TAG, "WriteFile: FileNotFoundException" + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "WriteFile: IOException" + e.getMessage());
        }
        catch (Exception e){
            Log.i(TAG, "WriteFile: " + e.getMessage());
        }
    }

    public ArrayList<Actor> readXMLFile(String filename, AppCompatActivity activity){
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Log.d(TAG, "readXMLFile: start");
        try{
            InputStream inputStream = activity.openFileInput(filename);
            XmlPullParser xmlPullParser = Xml.newPullParser();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            xmlPullParser.setInput(inputStreamReader);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT){
                if(xmlPullParser.getEventType() == XmlPullParser.START_TAG){
                    if(xmlPullParser.getName().equals("actor")){
                        int id = Integer.parseInt(xmlPullParser.getAttributeValue(null, "id"));
                        String firstName = xmlPullParser.getAttributeValue(null, "firstname");
                        String lastName = xmlPullParser.getAttributeValue(null, "lastname");
                        Actor actor = new Actor(id, firstName, lastName);
                        actors.add(actor);
                        Log.d(TAG, "readXMLFile: " + actor.toString());
                    }
                }
                xmlPullParser.next();
            }
            bufferedReader = null;
            inputStreamReader.close();
            inputStream.close();

        }catch (Exception e){
            Log.d(TAG, "readFile: " + e.getMessage());
        }
        Log.d(TAG, "readXMLFile: end");
        return actors;
    }
    public void writeXMLFile(String filename, AppCompatActivity activity, ArrayList<Actor> actors) {
        Log.d(TAG, "writeXMLFile: start");
        XmlSerializer serializer = Xml.newSerializer();
        File file = new File(filename);
        try {
            file.createNewFile();
            OutputStreamWriter writer = new OutputStreamWriter(activity.getApplicationContext().openFileOutput(filename,Context.MODE_PRIVATE));

            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("","actors");
            serializer.attribute("","number", String.valueOf(actors.size()));

            for (Actor actor : actors){
                serializer.startTag("", "actor");
                    serializer.attribute("","id", String.valueOf(actor.getId()));
                    serializer.attribute("", "firstname", String.valueOf(actor.getFirstName()));
                    serializer.attribute("", "lastname", String.valueOf(actor.getLastName()));
                serializer.endTag("", "actor");
                Log.d(TAG, "writeXMLFile: " + actor.toString());
            }

            serializer.endTag("", "actors");
            serializer.endDocument();
            serializer.flush();
            writer.close();
            Log.d(TAG, "writeXMLFile: " + actors.size() + " actors written.");
        }catch (Exception e){
            Log.d(TAG, "writeXMLFile: " + e.getMessage());
        }
        Log.d(TAG, "writeXMLFile: end");
    }
}
