package player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

import utils.ConfigManager;

import javazoom.jl.player.Player;

public class miniPlayer {

	private String filename; 
	private Player player;

	public miniPlayer(String filename) {
		this.filename = filename;		
	}

	public void play() {
		try {
			BufferedInputStream buffer = new BufferedInputStream(
					new FileInputStream(filename));
			player = new Player(buffer);
			player.play();
		} catch (Exception e) {

			System.out.println(e);
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Song Name: ");
		miniPlayer mp3 = new miniPlayer(sc.next());
		mp3.play();

	}

}