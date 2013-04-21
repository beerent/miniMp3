package player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

import utils.ConfigManager;

/*TODO:
 * [ ] networking
 * [ ] gui
 * [ ] app
 */
public class brentmp3 extends Thread {
	private String programName = "BrentMp3";
	private ConfigManager loader;
	private String[] songTitles;
	private int songCount;
	private File configFile;
	private int songMaxSize;
	private boolean ready;

	public brentmp3() {
		this.ready = false;
		songMaxSize = 100;
	}

	public void run() {
		loadPlayer();
		loadSongs();
		this.ready = true;
	}

	public boolean playerReady() {
		return this.ready;
	}

	public String toString() {
		if (this.ready)
			return "\n" + "Songs: " + songCount;
		else
			return "player not ready";
	}

	private void loadSongs() {
		this.songTitles = new String[songMaxSize]; // default amount of songs to
													// 1,000
		Scanner sc = null;
		this.songCount = 0;
		try {
			sc = new Scanner(configFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String next;
		while (sc.hasNextLine()) {
			next = sc.nextLine();
			if (next.length() >= 2 && next.substring(0, 2).equals("sf")) {
				next = sc.nextLine();
				while (!next.equals("<end>")) {
					addMp3s(new File(next));
					next = sc.nextLine();
				}
			}
		}
	}

	private void addMp3s(File fold) {
		if (!fold.exists()) {
			report("skipping file " + fold);
			return;
		}
		ArrayList<File> songs;

		songs = new ArrayList<File>(Arrays.asList(fold.listFiles()));
		report("adding files from " + fold.getName());
		File tempFile;
		String fileName;
		for (int i = 0; i < songs.size(); i++) {
			tempFile = songs.get(i);
			fileName = songs.get(i).getAbsolutePath();
			if (tempFile.isDirectory()) {
				addMp3s(tempFile);
			} else if (fileName.substring(fileName.length() - 4).equals(".mp3")) {
				addSong(tempFile.getAbsolutePath());
			} else {
				report("skipping " + fileName);
			}

		}

	}

	private void report(String s) {
		File f = new File("src/data/report");
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(f, true));
			out.write(s + "\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getRandomSong() {
		if (!playerReady()) {
			try {
				// TODO fix this
				this.wait(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!playerReady())
				return null;
		}
		int song = new Random().nextInt(songCount);
		return songTitles[song];

	}

	// attempts to load saved settings from previous program use.
	private void loadPlayer() {
		loader = new ConfigManager(); // handles config file
		this.configFile = loader.getConfigFile();
	}

	public void addSong(String file) {
		report("adding " + file + " to library");
		songTitles[songCount] = new File(file).getAbsolutePath();
		songCount++;
		if (songCount == songMaxSize) {
			songMaxSize *= 2;
			String[] temp = new String[songMaxSize];
			for (int i = 0; i < songTitles.length; i++) {
				temp[i] = songTitles[i];
			}
			songTitles = temp;
		}
	}

	public void addFolder(String file) throws Exception {
		loader = new ConfigManager();
		File f = new File(file);
		if (!f.exists()) {
			throw new Exception("folder location " + file + " does not exist");
		}
		loader.addSourceFolder(new File(file));
	}
}
