import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;
import java.security.KeyStore.TrustedCertificateEntry;
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
	
	Thread timing;

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
		
		
		
		initialInstantiator();
		
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timing = new Thread(this);
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
	public void initialInstantiator() {
		frame.setSize((columnCount*28),1000);
		frame.setBackground(Color.black);
		frame.remove(panel);
		panel = new JPanel();
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
		panel.setBackground(Color.BLACK);
		frame.setVisible(true);
		frame.revalidate();
	}
	public void recallableInstantiator() {
		
	}

	public void run(){
		new Thread();
		do{
			try{
				for(int y =0; y<columnCount;y++) {
					for(int x=0; x<button.length;x++) {
						soundClip[x].stop();
						if(button[y][x].isSelected())
						{
							
							
							soundClip[x].play();
						}
					}
				Thread.sleep(700);
				}
				
			}catch(InterruptedException e){}
		}while(notStopped);
		

	}

	public static void main(String args[])
	{
		soundMatrix app=new soundMatrix();

	}
	
	public void marySong() {
		columnCount = 32;
		initialInstantiator();
		//melody
		button[0][30].setSelected(true);
		button[1][28].setSelected(true);
		button[2][26].setSelected(true);
		button[3][28].setSelected(true);
		button[4][30].setSelected(true);
		button[5][30].setSelected(true);
		button[6][30].setSelected(true);
		button[8][28].setSelected(true);
		button[9][28].setSelected(true);
		button[10][28].setSelected(true);
		button[12][28].setSelected(true);
		button[13][30].setSelected(true);
		button[14][30].setSelected(true);
		button[16][30].setSelected(true);
		button[17][28].setSelected(true);
		button[18][26].setSelected(true);
		button[19][28].setSelected(true);
		button[20][30].setSelected(true);
		button[21][30].setSelected(true);
		button[22][30].setSelected(true);
		button[23][30].setSelected(true);
		button[24][28].setSelected(true);
		button[25][28].setSelected(true);
		button[26][30].setSelected(true);
		button[27][28].setSelected(true);
		button[28][26].setSelected(true);
		
		//harmony
		button[0][11].setSelected(true);
		button[0][14].setSelected(true);
		button[2][11].setSelected(true);
		button[2][18].setSelected(true);
		button[4][11].setSelected(true);
		button[4][14].setSelected(true);
		button[6][11].setSelected(true);
		button[6][14].setSelected(true);
		button[7][11].setSelected(true);
		button[7][14].setSelected(true);
		button[8][13].setSelected(true);
		button[8][19].setSelected(true);
		button[10][22].setSelected(true);
		button[10][6].setSelected(true);
		button[11][22].setSelected(true);
		button[11][6].setSelected(true);
		button[12][11].setSelected(true);
		button[12][14].setSelected(true);
		button[12][23].setSelected(true);
		button[14][26].setSelected(true);
		button[14][11].setSelected(true);
		button[15][26].setSelected(true);
		button[15][11].setSelected(true);
		button[16][14].setSelected(true);
		button[16][23].setSelected(true);
		button[18][11].setSelected(true);
		button[18][18].setSelected(true);
		button[20][11].setSelected(true);
		button[20][14].setSelected(true);
		button[22][14].setSelected(true);
		button[22][23].setSelected(true);
		button[23][13].setSelected(true);
		button[23][18].setSelected(true);
		button[24][10].setSelected(true);
		button[24][18].setSelected(true);
		button[26][10].setSelected(true);
		button[26][18].setSelected(true);
		button[27][22].setSelected(true);
		button[27][6].setSelected(true);
		button[28][11].setSelected(true);
		button[28][23].setSelected(true);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == item1) {
			
			marySong();
			
			System.out.println(columnCount);
			
			
		}else if (e.getSource() == item2) {
			System.out.println("TEST");
		}else if(e.getSource() == item3) {
			System.out.println("TEST");
		}else if(e.getSource() == plusOne) {
			
			panel.removeAll();
			columnCount++;
			initialInstantiator();
			
		}else if(e.getSource() == minusOne) {
			
			panel.removeAll();
			columnCount--;
			initialInstantiator();
		}
		
		frame.revalidate();
		
	}
}
