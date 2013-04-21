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
				mp.stop();
			}
		}
	}

	public static String listCommands() {
		String response = "";
		response +="1) play random song\n";
		response +="2) stop current song\n";
		return response;
	}
}
