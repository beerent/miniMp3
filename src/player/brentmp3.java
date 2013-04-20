package player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;

import utils.ConfigManager;

/*TODO:
 * [ ] networking
 * [ ] gui
 * [ ] app
 */
public class brentmp3 {
	private String programName = "BrentMp3";
	private ConfigManager loader;
	private String [] songTitles;
	private int songCount;
	private File configFile;
	private int songMaxSize;

	public brentmp3() {
		songMaxSize = 100;
		loadPlayer();
		loadSongs();
	}
	
	private void loadSongs(){
		this.songTitles = new String[songMaxSize]; //default amount of songs to 1,000
		Scanner sc = null;
		this.songCount = 0;
		try {
			sc = new Scanner(configFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String next;
		while(sc.hasNextLine()){
			next = sc.nextLine();
			if(next.length()>=2 && next.substring(0,2).equals("sf")){
				next = sc.nextLine();
				while(!next.equals("<end>")){
					addMp3s(next);
					next = sc.nextLine();
				}
			}
		}
	}
	
	private void addMp3s(String folder){
		File fold = new File(folder);
		ArrayList<String> songs;
		songs = new ArrayList<String>(Arrays.asList(fold.list()));
		String songName;
		for(int i = 0; i < songs.size(); i++){
			songName = songs.get(i);
			if(songName.substring(songName.length() - 4).equals(".mp3")){
				addSong(songName);
			}
		}
		
	}

	private void report(String string) {
		System.out.println("[System] " + string);
	}

	// attempts to load saved settings from previous program use.
	private void loadPlayer() {
		loader = new ConfigManager(); // handles config file
		this.configFile = loader.getConfigFile();
	}

	public void addSong(String file){
		report("adding " + file + " to library");
		songTitles[songCount] = file;
		songCount++;	
		if(songCount == songMaxSize){
			songMaxSize *=2;
			String [] temp = new String [songMaxSize];
			for(int i = 0; i < songTitles.length; i++){
				temp[i] = songTitles[i];
			}
			songTitles = temp;
		}
	}
	
	public void addFolder(String file) {
		loader = new ConfigManager();
		loader.addSourceFolder(new File(file));
	}
}
