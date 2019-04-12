import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;
public class soundMatrix extends JFrame implements Runnable, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int columnCount = 36;
	JToggleButton button[][];
	boolean maryHad[][];
	JPanel panel=new JPanel();
	JMenu menu;
	JMenuBar menuBar;
	JMenuItem item1;
	JMenuItem item2;																																												
	JMenuItem item3;
	
	JButton plusOne;
	JButton minusOne;
	
	URL[] musicNotes;

	AudioClip soundClip[]=new AudioClip[36];
	boolean notStopped=true;
	JFrame frame=new JFrame();

	public soundMatrix()
	{
		menuBar = new JMenuBar();
		menu = 	new JMenu("PreLoaded Songs");
		item1 = new JMenuItem("Song One: Mary Had A Little Lamb"); 
		item2 = new JMenuItem("Song Two: Despacito");
		item3 = new JMenuItem("Song Three: Despacito 2");
		plusOne = new JButton("\u2191");
		plusOne.setOpaque(false);
		plusOne.setContentAreaFilled(false);
		plusOne.setBorderPainted(false);
		minusOne = new JButton("\u2193");
		minusOne.setOpaque(false);
		minusOne.setContentAreaFilled(false);
		minusOne.setBorderPainted(false);
		
		musicNotes = new URL[36];
		musicNotes[0] = URLmaker("C3");
		musicNotes[1] = URLmaker("CSharp3");
		musicNotes[2] = URLmaker("D3");
		musicNotes[3] = URLmaker("DSharp3");
		musicNotes[4] = URLmaker("E3");
		musicNotes[5] = URLmaker("F3");
		musicNotes[6] = URLmaker("FSharp3");
		musicNotes[7] = URLmaker("G3");
		musicNotes[8] = URLmaker("GSharp3");
		musicNotes[9] = URLmaker("A3");
		musicNotes[10] = URLmaker("ASharp3");
		musicNotes[11] = URLmaker("B3");
		musicNotes[12] = URLmaker("C4");
		musicNotes[13] = URLmaker("CSharp4");
		musicNotes[14] = URLmaker("D4");
		musicNotes[15] = URLmaker("DSharp4");
		musicNotes[16] = URLmaker("E4");
		musicNotes[17] = URLmaker("F4");
		musicNotes[18] = URLmaker("FSharp4");
		musicNotes[19] = URLmaker("G4");
		musicNotes[20] = URLmaker("GSharp4");
		musicNotes[21] = URLmaker("A4");
		musicNotes[22] = URLmaker("ASharp4");
		musicNotes[23] = URLmaker("B4");
		musicNotes[24] = URLmaker("C5");
		musicNotes[25] = URLmaker("CSharp5");
		musicNotes[26] = URLmaker("D5");
		musicNotes[27] = URLmaker("DSharp5");
		musicNotes[28] = URLmaker("E5");
		musicNotes[29] = URLmaker("F5");
		musicNotes[30] = URLmaker("FSharp5");
		musicNotes[31] = URLmaker("G5");
		musicNotes[32] = URLmaker("GSharp5");
		musicNotes[33] = URLmaker("A5");
		musicNotes[34] = URLmaker("ASharp5");
		musicNotes[35] = URLmaker("B5");
		
		for(int x =0; x<musicNotes.length; x++) {
			soundClip[x] = JApplet.newAudioClip(musicNotes[x]);
		}
		
		
		
		goddamnit();
		
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		
		item1.addActionListener(this);		
		item2.addActionListener(this);
		item3.addActionListener(this);
		plusOne.addActionListener(this);
		minusOne.addActionListener(this);
		menuBar.add(menu);
		menuBar.add(minusOne);
		menuBar.add(plusOne);
		panel.setBackground(Color.BLACK);
		frame.add(menuBar, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);
		frame.setSize(1000,1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Thread timing = new Thread(this);
		timing.start();
	}

	private URL URLmaker(String note) {
		URL urls = null;
		try {
			 urls = new URL("file:Music Box Notes/" + note + ".wav");
		}catch(MalformedURLException mue)
		{
			System.out.println("File not found");
		}
		return urls;
	}
	public void goddamnit() {
		frame.setSize((columnCount*40),1000);
		button = new JToggleButton[columnCount][36];
		panel.setLayout(new GridLayout(36, columnCount, 5, 5));
		for(int y=0;y<36;y++)
		for(int x=0;x<columnCount;x++)
		{
			button[x][y]=new JToggleButton();
			button[x][y].setBackground(Color.DARK_GRAY);
			panel.add(button[x][y]);
		}
		frame.add(panel, BorderLayout.CENTER);
		frame.revalidate();
	}

	public void run(){
		new Thread();
		do{
			try{
				for(int y =0; y<columnCount;y++) {
					for(int x=0; x<button.length;x++) {
						if(button[y][x].isSelected())
						{
							soundClip[x].stop();
							soundClip[x].play();
						}
					}
				Thread.sleep(300);
				}
				
			}catch(InterruptedException e){}
		}while(notStopped);
		

	}

	public static void main(String args[])
	{
		soundMatrix app=new soundMatrix();

	}
	
	public void marySong() {
		columnCount = 29;
		goddamnit();
		button[0][18].setSelected(true);
		button[1][16].setSelected(true);
		button[2][14].setSelected(true);
		button[3][16].setSelected(true);
		button[4][18].setSelected(true);
		button[5][18].setSelected(true);
		button[6][18].setSelected(true);
		button[8][16].setSelected(true);
		button[9][16].setSelected(true);
		button[10][16].setSelected(true);
		button[12][16].setSelected(true);
		button[13][18].setSelected(true);
		button[14][18].setSelected(true);
		button[16][18].setSelected(true);
		button[17][16].setSelected(true);
		button[18][14].setSelected(true);
		button[19][16].setSelected(true);
		button[20][18].setSelected(true);
		button[21][18].setSelected(true);
		button[22][18].setSelected(true);
		button[23][18].setSelected(true);
		button[24][16].setSelected(true);
		button[25][16].setSelected(true);
		button[26][18].setSelected(true);
		button[27][16].setSelected(true);
		button[28][14].setSelected(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == item1) {
			
			panel.removeAll();
			marySong();
			goddamnit();
			
			frame.revalidate();
		}else if (e.getSource() == item2) {
			System.out.println("TEST");
		}else if(e.getSource() == item3) {
			System.out.println("TEST");
		}else if(e.getSource() == plusOne) {
			
			panel.removeAll();
			columnCount++;
			
		}else if(e.getSource() == minusOne) {
			
			panel.removeAll();
			columnCount--;
			
		}
		goddamnit();
		frame.revalidate();
		
	}
}
