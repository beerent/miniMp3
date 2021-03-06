package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConfigManager {
	private final String FILENAME = "src/data/brentmp3.conf"; // config file name
	private final String startKey = "<start>";
	private final String endKey = "<end>";
	
	private File configFile;
	private PrintStream out; // config file writer

	public ConfigManager() {
		configFile = null;
		loadConfig();
		
	}
	
	public void loadConfig(){
		File f = new File(FILENAME);
		if(!f.exists()){
			try {
				out = new PrintStream(f);
				writeNewConfigFile();
				report("config file created.");
			} catch (FileNotFoundException e) {e.printStackTrace();}
		}else{				
		}
		setConfigFile(f);
	}
	
	public File getConfigFile(){
		return configFile;
	}
	
	private void setConfigFile(File configFile){
		this.configFile = configFile;
	}

	public void writeNewConfigFile() {
		report("creating new configFile");
		out.println("#file for saved preferences for BrentMp3");
		out.println();

		out.println("#Song Folder Locations");
		out.println("sf <------------------->");
		out.println(endKey);
		out.close();
	}
	
	//report a string to the console. updates/ 
	private void report(String s){
		File f = new File("src/data/report");
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(f, true));
			out.write(s + "\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//adds a folder to list of folders where music is stored
	public void addSourceFolder(File folder){
		Scanner sc;
		try {
			sc = new Scanner(new File(FILENAME));
			File finalFile = new File(FILENAME);
			PrintWriter finalOut = new PrintWriter("temp");
			String next;
			boolean flag = false;
			while(sc.hasNextLine()){
				next = sc.nextLine();
				//System.out.println(next);
				finalOut.println(next);
				if(next.length()>=2 && !flag && next.substring(0,2).equals("sf")){
					finalOut.println(folder.getPath());
					flag = true;
				}
			}
			finalOut.close();
			new File("temp").renameTo(new File(FILENAME));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
