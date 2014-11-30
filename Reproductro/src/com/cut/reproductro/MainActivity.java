package com.cut.reproductro;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

class Mp3Filter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		return (name.endsWith(".mp3"));
	}
	
}

public class MainActivity extends ListActivity {
	@SuppressLint("SdCardPath") private static final String SD_PATH = new String ("/sdcard/");
	private List<String> songs = new ArrayList<String>();
	private MediaPlayer mp = new MediaPlayer();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updatePlayList();
        
        Button stopPlay = (Button) findViewById(R.id.btnStop);
        stopPlay.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				mp.stop();
				
				
			}
        	
        	
        });
    }


    private void updatePlayList() {
		File home = new File(SD_PATH);
		if (home.listFiles(new Mp3Filter()).length > 0) {
			for (File file : home.listFiles(new Mp3Filter())){
				songs.add(file.getName());
			}
			
			ArrayAdapter<String> songList = new ArrayAdapter<String>(this,R.layout.song_item,songs);
			setListAdapter(songList);
		}
    	
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
