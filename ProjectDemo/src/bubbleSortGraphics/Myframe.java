package bubbleSortGraphics;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import sun.security.jca.GetInstance;



public class Myframe extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	final int pw = 600 ,  ph = 300 , fw = 1210, fh = 700;
	int x = 0 , w , h , c = 0 , n;
	Scanner scan = null;
	Image offimage;
    Graphics offg ;
    Color col;
    SortingAlgo sort;
    String[] str = {"Bubbel Sort" , "Merg Sort"};
	ArrayList<Integer> ar = new ArrayList<Integer>();
	Integer[] arr;
	
	MyPanel jp1 , jp2 , jp3 , jp4;
	JComboBox cbox;
	JLabel jlabel , valueOfn;
	JButton suffel , start , fetch;
	JTextField jt; 
	
	Myframe(){
		arr = new Integer[ar.size()]; 
	    arr = ar.toArray(arr);
		createComponent();
		colorPanel();
		placeAndConfigComponent();
		addComponent();
		addActionListeners(); 
		
	}
	
	void colorPanel() {
		x = 0;
		w = pw;
		h = ph;
		col = Color.BLACK;
		jp1.paintImmediately(0, 0, pw, ph);
		jp2.paintImmediately(0, 0, pw, ph);
		jp3.paintImmediately(0, 0, pw, ph);
		jp4.paintImmediately(0, 0, pw, ph);
		
	}
	void createComponent() {
		jp1 = new MyPanel(this);
		jp2 = new MyPanel(this);
		jp3 = new MyPanel(this);
		jp4 = new MyPanel(this);
		/*
		jp1 = new MyPanel();
		jp2 = new MyPanel();
		jp3 = new MyPanel();
		jp4 = new MyPanel();*/
		suffel = new JButton("Suffel");
		start = new JButton("Start");
		fetch = new JButton("Fetch");
		jlabel = new JLabel("Choose any Sorting Algorithm   ");
		valueOfn = new JLabel("N ");
		jt = new JTextField(10);
		cbox = new JComboBox(str);
	}
	
	void addComponent() {
		add(valueOfn);
		add(jt);
		add(fetch);
		add(jlabel);
		add(cbox);
		add(start);
		add(suffel);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		
	}
	
	void placeAndConfigComponent() {
		this.setLayout(null);
		setBounds(0, 0, 1210, 700);
		cbox.setBackground(Color.white);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		valueOfn.setBounds(0, 0, 20, 30);
		jt.setBounds(25, 0, 100, 30);
		fetch.setBounds(130, 0, 100, 30);
		jlabel.setBounds(235, 0, 230, 30);
		cbox.setBounds(465, 0, 100, 30);
		start.setBounds(570, 0, 100, 30);
		suffel.setBounds(675, 0, 100, 30);
		jp1.setBounds(0, 30, 600, 300);
		jp1.setBackground(Color.black);
	    jp2.setVisible(true);
	    jp2.setBounds(0, 350, 600, 300);
	    jp2.setBackground(Color.black);
	    jp2.setVisible(true);
	    jp3.setBounds(610, 30, 600, 300);
	    jp3.setBackground(Color.black);
	    jp3.setVisible(true);
	    jp4.setBounds(610 ,350, 600, 300);
	    jp4.setBackground(Color.black);
	    jp4.setVisible(true);
	    this.setVisible(true);
	}
	
	void addActionListeners() {
		cbox.addActionListener(this);
	    start.addActionListener(this);
	    suffel.addActionListener(this);
	    fetch.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Myframe cur= this;
		sort = new SortingAlgo();
		
		if(e.getSource() == suffel || e.getSource() == fetch) {
			n = Integer.parseInt(jt.getText());
			w = 600/n;
			jumble();
			arr = ar.toArray(arr);
			paintCaller();
		}
		else if (e.getSource() == start) {
			if(cbox.getSelectedIndex() == 0) {
				sort.bubblesort(arr , cur);
			}
			else if(cbox.getSelectedIndex() == 1) {
				sort.mergsort(arr, 0, arr.length - 1 , cur);
			}
	    
		checkit();
	}
	}
	
	void checkit() {
		x = 0;
		int i;
		col = Color.yellow;
		for(i = 0 ; i < arr.length - 1; i++) {
			if(arr[i] <= arr[i+1]) {
				h = arr[i];
				jp1.paintImmediately(0, 0, 800, 300);
				try {
					Thread.sleep(30);			
				}
				catch(InterruptedException e) {
					System.out.println(i);
				}
			}else {
				return;
			}
			x += w;
		}
		h = arr[i];
		jp1.paintImmediately(0, 0, pw, ph);
	}
	
	void jumble() {
		ar.clear();
		Random r = new Random();
		for (int i = 0 ; i < n ; i++) {
			int data = 10 + r.nextInt(200);
			System.out.println(data);
			ar.add(data);
			
		}
				
	}
	
	void swap(int x1 ,int y1 , int x2 , int y2) {
	
		x = x1;
		h = y1;
		col = Color.green;
		jp1.paintImmediately(0, 0, pw, ph);
		//repaint();
		x = x2;
		h = y2;
		jp1.paintImmediately(0, 0, pw, ph);
		
		try {
			Thread.sleep(30);			
		}
		catch(InterruptedException i) {
			System.out.println(i);
		}
		col = Color.black;
		h = 300;
		x = x1;
		jp1.paintImmediately(0, 0, pw, ph);
		x = x2;
		jp1.paintImmediately(0, 0, pw, ph);

		col = Color.white;
		x = x1 ;
		h = y2;
		jp1.paintImmediately(0, 0, pw, ph);
		
		x = x2 ;
		h = y1;
		jp1.paintImmediately(0, 0, pw, ph);
			
		
	}
	
	void fetchData() {
		
		try {
			scan = new Scanner(new File("data1.txt"));
			while (scan.hasNextInt()) {
				int data = scan.nextInt();
				ar.add(100 + data);
		
			}
		}catch(IOException e) {
			System.out.println(e);
		}
		finally {
			scan.close();
		}
	}
	 
	public void paintCaller() {
		try {
			//System.out.println(Thread.currentThread().getName());
			for(int data : ar) {
				h = data;
				col = Color.red;
				jp1.paintImmediately(0, 0, pw, ph);
				Thread.sleep(30);
				col = Color.white;
				jp1.paintImmediately(0, 0, pw, ph);
				x += w;
			}
		}
		catch(InterruptedException i) {
			System.out.println(i);
		}
	}
	
		
	
	
	public void update(Graphics g) {
		
		if (offimage == null) {
	        offimage = jp1.createImage(getWidth(), getHeight());
	        offg = offimage.getGraphics();
	    }
	    Graphics2D g2d = (Graphics2D)offg;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(col);
		g2d.fillRect(x, 0, w, h);
		
		g.drawImage(offimage, 0, 0, jp1);
	}
	public static void main(String[] args) {
		new Myframe();
	}

}
