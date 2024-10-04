package fvtc.edu.fileiodemo;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import fvtc.edu.fileiodemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static final String TAG = "MainActivity";
    public static final String FILENAME = "data.txt";
    public static final String XMLFILENAME = "data.xml";
    ArrayList<Actor> actors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });
        createActors();
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
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
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void ReadTextFile() {
        try {
            FileIO fileIO = new FileIO();
            ArrayList<String> strData = fileIO.readFile(FILENAME, this);

            actors = new ArrayList<Actor>();

            for(String s : strData){
                //actors.add(s);

                String[] data = s.split("\\|");
                actors.add(new Actor(Integer.parseInt(data[0]), data[1], data[2]));
                Log.d(TAG, "ReadTextFile: " + actors.get(actors.size()-1).getFirstName());
                //Log.d(TAG, "ReadTextFile: " + actors.size());
            }
            Log.d(TAG, "ReadTextFile: " + actors.size());
        }catch (Exception e){
            Log.d(TAG, "ReadTextFile: " + e.getMessage());
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
            Log.d(TAG, "WriteTextFile: " + e.getMessage());
        }
    }
    private void ReadXMLFile() {
    }
    private void WriteXMLFile() {
    }
}