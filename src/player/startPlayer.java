package player;

import java.util.Scanner;

public class startPlayer extends Thread {
	private static miniPlayer mp;
	public static void main(String[] args) {
		brentmp3 player = new brentmp3();
		player.start();
		
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("enter command: ");
			System.out.println(listCommands());
			String command = sc.next();
			int op;
			try {
				op = Integer.parseInt(command);
			} catch (Exception e) {
				continue;
			}
			if(op == 1){
				if( mp != null) mp.stop();
				mp = new miniPlayer(player.getRandomSong());
				mp.start();
				//mp.play();
			}else if (op == 2){
				//make a temporary playlist
				System.out.print("enter song to search for: ");
				player.makePlayList(sc.next());
			}else if(op == 3){
				//stop current song
				mp.stop();
			}
			else if(op==4){
				//quit program
				System.exit(0);
			}else if(op == 5){
				//list current playlist
				player.listCurrentPlaylist();
			}else if(op == 6){
				player.loadMainPlaylist();
			}
		}
	}

	public static String listCommands() {
		String response = "";
		response +="1) play random song\n";
		response +="2) create temporary playlist\n";
		response +="3) stop current song\n";
		response +="4) close program\n";
		response +="5) list current playlist\n";
		response +="6) build original playlist\n";
		return response;
	}
}
