package player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

import utils.ConfigManager;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class miniPlayer extends Thread {

	private String filename;
	private Player player;
	private boolean ready = false;

	public miniPlayer(String filename) {
		this.filename = filename;
	}

	public miniPlayer() {
		this.filename = null;
	}

	public void run() {
		try {
			BufferedInputStream buffer = new BufferedInputStream(
					new FileInputStream(filename));
			player = new Player(buffer);
			ready = true;
			play();
		} catch (Exception e) {

			System.out.println(e);
		}
	}

	public void play() {
		try {
			if(ready) player.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Song Name: ");
		miniPlayer mp3 = new miniPlayer(sc.next());
		mp3.play();
	}
}