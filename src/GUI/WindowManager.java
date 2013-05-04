package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class WindowManager extends JFrame implements WindowListener,
		ActionListener {
	private static int windowX = 600;
	private static int windowY = 600;
	// TextField text = new TextField(20);
	
	public WindowManager() {
		
		EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    WindowManager.this.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Brent MP3");
        this.setName("BRENT MP3");
        // this needs to be changed if button/wall size changes
        setBounds(100, 100, 565, 600);
        this.setResizable(false);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setName("MainMenuBar");
        
        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);
        mnFile.setName("File");
        
        setButtons();
	}	

	public static void main(String[] args) {
		new WindowManager();
	}
	
	private void setButtons() {
		Button quitButton = new Button("quit");
		quitButton.setLocation(250, 250);
		this.add(quitButton);
		quitButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		System.exit(0);
		// text.setText("Button Clicked " + numClicks + " times");
	}

	public void windowClosing(WindowEvent e) {
		dispose();
		System.exit(0);
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

}