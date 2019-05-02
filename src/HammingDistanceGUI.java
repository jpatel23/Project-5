import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class HammingDistanceGUI extends JFrame {
	
	private ArrayList<String> stids = readToArray("Mesonet.txt");
	private JLabel hamDistInfo = new JLabel("Enter Hamming Dist:");
	private JTextField distance = new JTextField(10);
	private JLabel compareInfo = new JLabel("Compare with:");
	private JLabel distOne = new JLabel("Distance 1");
	private JTextField one = new JTextField(10);
	private JLabel distTwo = new JLabel("Distance 2");
	private JTextField two = new JTextField(10);
	private JLabel distThree = new JLabel("Distance 3");
	private JTextField three = new JTextField(10);
	private JLabel distFour = new JLabel("Distance 4");
	private JTextField four = new JTextField(10);
	
	private JButton show = new JButton("Show Station");
	private JButton calculate = new JButton("Calculate HD");
	private JButton add = new JButton("Add Station");
	
	private JTextField addStation = new JTextField(10);
	
	private JComboBox list = new JComboBox(stids.toArray());
	
	private JTextArea matches = new JTextArea(30, 30);
	
	private JSlider slide = new JSlider(1, 4, 1);
	
	private JLabel compareInfo2 = new JLabel("Compare two STIDs:");
	private JComboBox compareOne = new JComboBox(stids.toArray());
	private JComboBox compareTwo = new JComboBox(stids.toArray());
	private JTextField comparison = new JTextField(10);
	private JButton compare = new JButton("Compare");
	
	private JLabel averageInfo = new JLabel("Find average:");
	private JComboBox averageList = new JComboBox(stids.toArray());
	private JTextField answer = new JTextField(10);
	private JButton average = new JButton("Average");	
	
	public HammingDistanceGUI() throws IOException {
		this.setLayout(new GridLayout(0, 2));
		this.setSize(800, 800);
		
		JPanel left = new JPanel(new GridLayout(12, 1));
		JPanel right = new JPanel(new GridLayout(12, 1));
		
		JPanel panel1 = new JPanel();
		panel1.add(hamDistInfo);
		distance.setText("1");
		distance.setEditable(false);
		panel1.add(distance);
		left.add(panel1);
		
		
		JPanel panel2 = new JPanel();
		slide.setMajorTickSpacing(1);
		slide.setPaintTicks(true);
		slide.setSnapToTicks(true);
		slide.setPaintLabels(true);
		panel2.add(slide);
		left.add(panel2);
		
		JPanel panel3 = new JPanel();
		panel3.add(show);
		left.add(panel3);
		
		JPanel panel4 = new JPanel();
		panel4.add(matches);
		left.add(panel4);
		
		JPanel panel5 = new JPanel();
		panel5.add(compareInfo);
		list.setEditable(false);
		panel5.add(list);
		left.add(panel5);
		
		JPanel panel6 = new JPanel();
		panel6.add(calculate);
		left.add(panel6);
		
		JPanel panel7 = new JPanel();
		panel7.add(distOne);
		one.setEditable(false);
		panel7.add(one);
		left.add(panel7);
		
		JPanel panel8 = new JPanel();
		panel8.add(distTwo);
		two.setEditable(false);
		panel8.add(two);
		left.add(panel8);
		
		JPanel panel9 = new JPanel();
		panel9.add(distThree);
		three.setEditable(false);
		panel9.add(three);
		left.add(panel9);
		
		JPanel panel10 = new JPanel();
		panel10.add(distFour);
		four.setEditable(false);
		panel10.add(four);
		left.add(panel10);
		
		JPanel panel11 = new JPanel();
		panel11.add(add);
		panel11.add(addStation);
		left.add(panel11);
		
		JPanel panel12 = new JPanel();
		panel12.add(compareInfo2);
		panel12.add(compareOne);
		panel12.add(compareTwo);
		right.add(panel12);
		
		JPanel panel13 = new JPanel();
		panel13.add(compare);
		comparison.setEditable(false);
		panel13.add(comparison);
		right.add(panel13);
		
		JPanel panel14 = new JPanel();
		panel14.add(averageInfo);
		panel14.add(averageList);
		right.add(panel14);
		
		JPanel panel15 = new JPanel();
		panel15.add(average);
		answer.setEditable(false);
		panel15.add(answer);
		right.add(panel15);
		
		
		
		
		this.add(left);
		this.add(right);
		
		
		slide.addChangeListener((e) -> {
			String value = "" + slide.getValue();
			distance.setText(value);
			distance.repaint();
		});
		
		show.addActionListener((e) -> {
			
			int hamming = slide.getValue();
			
			String current = (String) list.getSelectedItem();
			
			ArrayList<String> hammingWords = findList(current, hamming);
			
			if(hammingWords.size() == 0) {
				matches.setText("");
				matches.repaint();
			}
			
			else {
			matches.setText(hammingWords.get(0) + "\n");
			
			for (int index = 1; index < hammingWords.size(); ++index) {
				matches.append(hammingWords.get(index) + "\n");
			}
			
			matches.repaint();
			}
		});
		
		calculate.addActionListener((e) -> {
			String selected = (String) list.getSelectedItem();
			try {
				ArrayList<Integer> nodes = measureNode(selected);
				String oneN = "" + nodes.get(0);
				String twoN = "" + nodes.get(1);
				String threeN = "" + nodes.get(2);
				String fourN = "" + nodes.get(3);
				
				one.setText(oneN);
				two.setText(twoN);
				three.setText(threeN);
				four.setText(fourN);
				
				one.repaint();
				two.repaint();
				three.repaint();
				four.repaint();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		add.addActionListener((e) -> {
			String word = addStation.getText();
			Object newWord = word; 
			list.addItem(newWord);
			list.repaint();
		});
		
		compare.addActionListener((e) -> {
			String one = (String) compareOne.getSelectedItem();
			String two = (String) compareTwo.getSelectedItem();
			
			comparison.setText("" + compareTo(one, two));
		});
		
		average.addActionListener((e) -> {
			String wanted = (String) averageList.getSelectedItem();
			answer.setText("" + calAverage(wanted));
		});
		
		this.setTitle("STID Interactive Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pack();
		this.setVisible(true);
		
	}
	
	public static ArrayList<String> readToArray(String fileName) throws IOException  {
		String strg;
		String onlyStid;
		
		ArrayList<String> stids = new ArrayList<String>();
		
		//Creates the reader
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		//Reads first real line and cuts the rest of the line, adding it to the ArrayList
		strg = br.readLine();
		while (strg != null) {
		onlyStid = strg.substring(0, 4);
		stids.add(onlyStid);
		strg = br.readLine();
		}
		
		br.close();
		
		return stids;
	}
	
	
	public ArrayList<Integer> measureNode(String stdiType) throws IOException{
		int ones = 0;
		int twos = 0;
		int threes = 0;
		int fours = 0;
		int matches = 0;
		
		//Contains the amount of STIDs that match each Hamming Distance
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		
		//Goes through the full STID list and compares each word to the given word
		for(int i = 0; i < stids.size(); ++i) {
			matches = 0;
			for (int g = 0; g < 4; ++g) {
				if (stdiType.charAt(g) != (stids.get(i)).charAt(g)) {
					matches++;
				}
			}
			
			if (matches == 1) {
				ones++;
			}
			
			if (matches == 2) {
				twos++;
			}
			
			if (matches == 3) {
				threes++;
			}
			
			if (matches == 4) {
				fours++;
			}
		}
		
		
		
		//Adds the amount of STIDs that match the given Hamming Distance
		nodes.add(ones);
		nodes.add(twos);
		nodes.add(threes);
		nodes.add(fours);
		return nodes;
	}
	
	
	public ArrayList<String> findList(String stid, int hD) {
			ArrayList<String> distancedWords = new ArrayList<String>();
			
			int matches;
			
			for(int i = 0; i < stids.size(); ++i) {
				matches = 0;
				for (int g = 0; g < 4; ++g) {
					if (stid.charAt(g) != (stids.get(i)).charAt(g)) {
						matches++;
					}
				}
				
				if (matches == hD) {
					distancedWords.add(stids.get(i));
				}
				
			}
			
			
			return distancedWords;
		}
	
	
		public int compareTo(String stidOne, String stidTwo) {
		int counter = 0;
		for (int index = 0; index < 4; ++index) {
			if (stidOne.charAt(index) != stidTwo.charAt(index)) {
				counter++;
			}
		}
		return counter;
	}
		
		
		public int calAverage(String stid) {
			char letter1 = stid.charAt(0);
			char letter2 = stid.charAt(1);
			char letter3 = stid.charAt(2);
			char letter4 = stid.charAt(3);
			//Adds 4 letter values together
			double addy = letter1 + letter2 + letter3 + letter4;
			
			//Divides the added four letters to give average with decimals
			double dubAvg = addy / 4.0;
			
			//Rounds double average to create the final Integer value
			int average = (int)Math.round(dubAvg);
			
			return average;
		}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new HammingDistanceGUI();

	}

}
