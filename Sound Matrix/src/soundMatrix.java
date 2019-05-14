import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;
import java.security.KeyStore.TrustedCertificateEntry;
public class soundMatrix extends JFrame implements Runnable, ActionListener, AdjustmentListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int columnCount = 36;
	JToggleButton buttons[][];
	JPanel panel=new JPanel();
	JPanel topPanel = new JPanel();
	JMenu menu;
	JMenu save;
	JPanel labels;
	JMenuBar menuBar;
	JMenuBar menuBar2;
	JMenuItem item1;
	JMenuItem item2;																																												
	JMenuItem item3;
	JMenuItem clearL;
	JMenuItem saveL;
	JMenuItem loadL;
	boolean userSave[][];
	JLabel temp[] = new JLabel[36];
	
	
	
	JScrollBar beats;
	JLabel bpmLabel;
	
	String[] noteNames;
	
	JButton plusOne;
	JButton minusOne;
	JButton reset;
	JButton random;
	
	URL[] musicNotes;
	
	Thread timing;
	int bpmMilli = 1000;
	int bpm = 60000/bpmMilli;
	

	AudioClip soundClip[]=new AudioClip[36];
	boolean notStopped=true;
	JFrame frame=new JFrame();

	public soundMatrix()
	{
		menuBar = new JMenuBar();
		menuBar2 = new JMenuBar();
		labels = new JPanel();
		menu = 	new JMenu("PreLoaded Songs");
		item1 = new JMenuItem("Song One: Mary Had A Little Lamb"); 
		item2 = new JMenuItem("Song Two: Despacito");
		item3 = new JMenuItem("Song Three: Despacito 2");
		save = new JMenu("Save your song");
		saveL = new JMenuItem("Save Load");
		loadL = new JMenuItem("Load the Song");
		clearL = new JMenuItem("Clear Load");
		plusOne = new JButton("\u2191");
		plusOne.setOpaque(false);
		plusOne.setContentAreaFilled(false);
		plusOne.setBorderPainted(false);
		plusOne.setForeground(Color.white);
		minusOne = new JButton("\u2193");
		minusOne.setForeground(Color.white);
		minusOne.setOpaque(false);
		minusOne.setContentAreaFilled(false);
		minusOne.setBorderPainted(false);
		beats = new JScrollBar(JScrollBar.HORIZONTAL, 60, 0, 30, 200);
		beats.setBackground(Color.black);
		beats.setPreferredSize(new Dimension(500, 20));
		beats.addAdjustmentListener(this);
		beats.setUnitIncrement(1);
		bpmLabel = new JLabel("BPM: "+bpm);
		bpmLabel.setForeground(Color.white);
		reset = new JButton("Reset");
		reset.setOpaque(false);
		reset.setContentAreaFilled(false);
		reset.setBorderPainted(false);
		reset.setForeground(Color.white);
		random = new JButton("Random");
		random.setOpaque(false);
		random.setContentAreaFilled(false);
		random.setBorderPainted(false);
		random.setForeground(Color.white);
		
		userSave = new boolean[columnCount][36];
		
		
		noteNames = new String[36];
		noteNames[0] = ("C3");
		noteNames[1] = ("CSharp3");
		noteNames[2] = ("D3");
		noteNames[3] = ("DSharp3");
		noteNames[4] = ("E3");
		noteNames[5] = ("F3");
		noteNames[6] = ("FSharp3");
		noteNames[7] = ("G3");
		noteNames[8] = ("GSharp3");
		noteNames[9] = ("A3");
		noteNames[10] = ("ASharp3");
		noteNames[11] = ("B3");
		noteNames[12] = ("C4");
		noteNames[13] = ("CSharp4");
		noteNames[14] = ("D4");
		noteNames[15] = ("DSharp4");
		noteNames[16] = ("E4");
		noteNames[17] = ("F4");
		noteNames[18] = ("FSharp4");
		noteNames[19] = ("G4");
		noteNames[20] = ("GSharp4");
		noteNames[21] = ("A4");
		noteNames[22] = ("ASharp4");
		noteNames[23] = ("B4");
		noteNames[24] = ("C5");
		noteNames[25] = ("CSharp5");
		noteNames[26] = ("D5");
		noteNames[27] = ("DSharp5");
		noteNames[28] = ("E5");
		noteNames[29] = ("F5");
		noteNames[30] = ("FSharp5");
		noteNames[31] = ("G5");
		noteNames[32] = ("GSharp5");
		noteNames[33] = ("A5");
		noteNames[34] = ("ASharp5");
		noteNames[35] = ("B5");
		
		
		musicNotes = new URL[36];
		labels.setLayout(new GridLayout(36, 1,2,4));
		for( int x = 0; x < 36; x++) {
			musicNotes[x] = URLmaker(noteNames[x]);
			temp[x]= new JLabel(noteNames[x]);
			temp[x].setForeground(new Color(255, 255, 255));
			labels.add(temp[x]);	
		}
		labels.setBackground(Color.black);
		
		for(int x =0; x<musicNotes.length; x++) {
			soundClip[x] = JApplet.newAudioClip(musicNotes[x]);
		}
		
		
		frame.add(labels, BorderLayout.WEST);
		
		initialInstantiator();
		menu.setForeground(Color.white);
		menu.setBackground(Color.black);
		menu.add(item1);
		item1.setForeground(Color.white);
		item1.setBackground(Color.BLACK);
		menu.add(item2);
		item2.setForeground(Color.white);
		item2.setBackground(Color.BLACK);
		menu.add(item3);
		item3.setForeground(Color.white);
		item3.setBackground(Color.BLACK);
		
		save.setForeground(Color.white);
		save.setBackground(Color.black);
		save.add(saveL);
		saveL.setForeground(Color.white);
		saveL.setBackground(Color.BLACK);
		save.add(loadL);
		loadL.setForeground(Color.white);
		loadL.setBackground(Color.BLACK);
		save.add(clearL);
		clearL.setForeground(Color.white);
		clearL.setBackground(Color.BLACK);
	
	
			
			
		
		item1.addActionListener(this);		
		item2.addActionListener(this);
		item3.addActionListener(this);
		saveL.addActionListener(this);
		loadL.addActionListener(this);
		clearL.addActionListener(this);
		plusOne.addActionListener(this);
		minusOne.addActionListener(this);
		reset.addActionListener(this);
		random.addActionListener(this);
		menuBar.add(menu);
		menuBar.add(minusOne);
		menuBar.add(plusOne);
		menuBar.add(bpmLabel);
		menuBar.add(beats, BorderLayout.NORTH);
		menuBar.setBackground(Color.black);
		
		menuBar2.add(reset);
		menuBar2.add(random);
		menuBar2.add(save);
		menuBar2.setBackground(Color.BLACK);
		panel.setBackground(Color.BLACK);
		topPanel.setLayout(new GridLayout(2, 5,0,0));
		topPanel.add(menuBar, BorderLayout.NORTH);
		topPanel.add(menuBar2, BorderLayout.SOUTH);
		frame.add(topPanel, BorderLayout.NORTH);
		frame.setBackground(Color.black);
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
		buttons = new JToggleButton[columnCount][36];
		panel.setLayout(new GridLayout(36, columnCount, 5, 5));
		for(int y=0;y<36;y++) {
			for(int x=0;x<columnCount;x++)
			{
				buttons[x][y]=new JToggleButton();
				buttons[x][y].setBackground(Color.DARK_GRAY);
				panel.add(buttons[x][y]);
			}
		}
		
		frame.add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.BLACK);
		frame.setVisible(true);
		frame.revalidate();
		
	}


	public void run(){
		
		do{
			try{
				for(int y =0; y<columnCount;y++) {
					for(int x=0; x<buttons.length;x++) {
						soundClip[x].stop();
						if(buttons[y][x].isSelected())
						{
							soundClip[x].stop();
							
							soundClip[x].play();
						}
					}
				Thread.sleep(bpmMilli);
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
		buttons[0][30].setSelected(true);
		buttons[1][28].setSelected(true);
		buttons[2][26].setSelected(true);
		buttons[3][28].setSelected(true);
		buttons[4][30].setSelected(true);
		buttons[5][30].setSelected(true);
		buttons[6][30].setSelected(true);
		buttons[8][28].setSelected(true);
		buttons[9][28].setSelected(true);
		buttons[10][28].setSelected(true);
		buttons[12][28].setSelected(true);
		buttons[13][30].setSelected(true);
		buttons[14][30].setSelected(true);
		buttons[16][30].setSelected(true);
		buttons[17][28].setSelected(true);
		buttons[18][26].setSelected(true);
		buttons[19][28].setSelected(true);
		buttons[20][30].setSelected(true);
		buttons[21][30].setSelected(true);
		buttons[22][30].setSelected(true);
		buttons[23][30].setSelected(true);
		buttons[24][28].setSelected(true);
		buttons[25][28].setSelected(true);
		buttons[26][30].setSelected(true);
		buttons[27][28].setSelected(true);
		buttons[28][26].setSelected(true);
		
		//harmony
		buttons[0][11].setSelected(true);
		buttons[0][14].setSelected(true);
		buttons[2][11].setSelected(true);
		buttons[2][18].setSelected(true);
		buttons[4][11].setSelected(true);
		buttons[4][14].setSelected(true);
		buttons[6][11].setSelected(true);
		buttons[6][14].setSelected(true);
		buttons[7][11].setSelected(true);
		buttons[7][14].setSelected(true);
		buttons[8][13].setSelected(true);
		buttons[8][19].setSelected(true);
		buttons[10][22].setSelected(true);
		buttons[10][6].setSelected(true);
		buttons[11][22].setSelected(true);
		buttons[11][6].setSelected(true);
		buttons[12][11].setSelected(true);
		buttons[12][14].setSelected(true);
		buttons[12][23].setSelected(true);
		buttons[14][26].setSelected(true);
		buttons[14][11].setSelected(true);
		buttons[15][26].setSelected(true);
		buttons[15][11].setSelected(true);
		buttons[16][14].setSelected(true);
		buttons[16][23].setSelected(true);
		buttons[18][11].setSelected(true);
		buttons[18][18].setSelected(true);
		buttons[20][11].setSelected(true);
		buttons[20][14].setSelected(true);
		buttons[22][14].setSelected(true);
		buttons[22][23].setSelected(true);
		buttons[23][13].setSelected(true);
		buttons[23][18].setSelected(true);
		buttons[24][10].setSelected(true);
		buttons[24][18].setSelected(true);
		buttons[26][10].setSelected(true);
		buttons[26][18].setSelected(true);
		buttons[27][22].setSelected(true);
		buttons[27][6].setSelected(true);
		buttons[28][11].setSelected(true);
		buttons[28][23].setSelected(true);
		
	}
	
	public void randomBtn() {
		resetBtn();
		for(int y=0;y<36;y++) {
			for(int x=0;x<columnCount;x++){
				int r = (int)(Math.random()*4)+1;
				if(r == 1) {
					buttons[x][y].setSelected(true);
				}
			}
		}
	}
	public void resetBtn() {
		for(int y=0;y<36;y++) {
			for(int x=0;x<columnCount;x++){
				buttons[x][y].setSelected(false);
			}
		}
	}
	public void clearLoad() {
		for(int x = 0; x< userSave.length; x++) {
			for(int y = 0; y<userSave[x].length; y++) {
				userSave[x][y] = false; 
			}
		}
	}
	public void saveLoad() {
		clearLoad();
		for(int x = 0; x< userSave.length; x++) {
			for(int y = 0; y<userSave[x].length; y++) {
				if(buttons[x][y].isSelected()) {
					userSave[x][y]= true;
					System.out.print("t");
				}
				else {
					userSave[x][y] = false;
					System.out.print("f");
				}
				
			}
			System.out.println();
		}
	}
	
	public void loadSave() {
		for(int x = 0; x< userSave.length; x++) {
			for(int y = 0; y<userSave[x].length; y++) {
				if(userSave[x][y] == true) {
					buttons[x][y].setSelected(true);;
				}
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == item1) {
			
			marySong();
			
			System.out.println(columnCount);
			
		}
		else if (e.getSource() == item2) {System.out.println("TEST");}
		else if(e.getSource() == item3) {System.out.println("TEST");}
		else if(e.getSource() == plusOne) {
			
			panel.removeAll();
			columnCount++;
			initialInstantiator();
			
		}else if(e.getSource() == minusOne) {
			panel.removeAll();
			columnCount--;
			initialInstantiator();
		}
		else if(e.getSource() == reset) {resetBtn();}
		else if(e.getSource() == random) {randomBtn();}
		else if(e.getSource() == saveL) {saveLoad();}
		else if(e.getSource() == clearL) {clearLoad();}
		else if(e.getSource() == loadL) {loadSave();}
		frame.revalidate();
		
	}
	public void adjustmentValueChanged(AdjustmentEvent e) {
		if(e.getSource() == beats) {
			bpm = beats.getValue();
		}
		bpmLabel.setText("BPM: "+bpm);
		bpmMilli = 60000/bpm;
		System.out.println(""+bpmMilli);
		
		frame.revalidate();
	}
}
