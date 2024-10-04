package fvtc.edu.fileiodemo;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static final String FILENAME = "data.txt";
    public static final String XMLFILENAME = "data.xml";
    ArrayList<Actor> actors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            createActors();

            return insets;
        });
    }

    private void createActors() {
        actors = new ArrayList<Actor>();
        actors.add(new Actor(1, "Jennifer", "Aniston"));
        actors.add(new Actor(2, "Matthew", "Perry"));
        actors.add(new Actor(3, "Matt", "LeBlanc"));
        actors.add(new Actor(4, "Courtney", "Cox"));
        actors.add(new Actor(5, "David", "Schwimmer"));
        actors.add(new Actor(6, "Lisa", "Kudrow"));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_ReadText)
        {
            Log.d(TAG, "onOptionsItemSelected: " + item.getTitle());
            ReadTextFile();
        } else if (id == R.id.action_WriteText) {
            Log.d(TAG, "onOptionsItemSelected: " + item.getTitle());
            WriteTextFile();
        } else if (id == R.id.action_ReadXML) {
            Log.d(TAG, "onOptionsItemSelected: " + item.getTitle());
            ReadXMLFile();
        } else{
            Log.d(TAG, "onOptionsItemSelected: " + item.getTitle());
            WriteXMLFile();
        }
        return super.onOptionsItemSelected(item);
    }
/*    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
    private void ReadTextFile() {
        Log.d(TAG, "ReadTextFile: " + FILENAME);
        try {
            Log.d(TAG, "ReadTextFile: start");
            FileIO fileIO = new FileIO();
            ArrayList<String> strData = fileIO.readFile(FILENAME, this);
            Log.d(TAG, "ReadTextFile: " + strData);
            actors = new ArrayList<Actor>();

            for(String s : strData){
                //actors.add(s);
                Log.d(TAG, "ReadTextFile: for loop start");

                String[] data = s.split("\\|");
                Log.d(TAG, "ReadTextFile: split string");
                actors.add(new Actor(Integer.parseInt(data[0]), data[1], data[2]));
                Log.d(TAG, "ReadTextFile: add actors");

                Log.d(TAG, "ReadTextFile: " + actors.get(actors.size()-1).getFirstName());
                Log.d(TAG, "ReadTextFile: get data start");
                Log.d(TAG, "ReadTextFile: " + data[0]);
                Log.d(TAG, "ReadTextFile: " + data[1]);
                Log.d(TAG, "ReadTextFile: " + data[2]);
                Log.d(TAG, "ReadTextFile: get data end");
                //Log.d(TAG, "ReadTextFile: " + actors.size());
            }
            Log.d(TAG, "ReadTextFile: " + actors.size());
        }catch (Exception e){
            Log.d(TAG, "ReadTextFile: bla " + e.getMessage());
        }
    }
    private void WriteTextFile() {
        try {
            FileIO fileIO = new FileIO();
            int counter = 0;
            String[] data = new String[actors.size()];
            for(Actor actor : actors){
                //data[counter++] = s;
                data[counter++] = actors.toString();
            }
            fileIO.writeFile(FILENAME, this, data);
        }catch (Exception e){
            Log.e(TAG, "WriteTextFile: " + e.getMessage());
        }
    }
    private void ReadXMLFile() {
    }
    private void WriteXMLFile() {
        try {
            FileIO fileIO = new FileIO();
            fileIO.writeXMLFile(XMLFILENAME,this, actors);
            Log.d(TAG, "WriteXMLFile: finished");
        }catch (Exception e){
            Log.e(TAG, "WriteXMLFile: " +  e.getMessage());
        }


    }
}