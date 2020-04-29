package bubbleSortGraphics;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import sun.security.jca.GetInstance;



public class Myframe extends JFrame implements ActionListener , Runnable{
	private static final long serialVersionUID = 1L;
	
	final int pw = 600 ,  ph = 300 , fw = 1210, fh = 700 ;
	int n;
	//int x = 0 , w , h , c = 0 ;
	Scanner scan = null;
	Image offimage;
    Graphics offg ;
    Color col;
    SortingAlgo sort1;
    SortingAlgo sort2;
    SortingAlgo sort3;
    SortingAlgo sort4;
    String[] str = {"Bubbel Sort" , "Merg Sort"};
	ArrayList<Integer> ar = new ArrayList<Integer>();
	Integer[] arr1 , arr2 , arr3 , arr4;
	Thread t1 , t2 , t3 , t4;
	
	MyPanel jp1 , jp2 , jp3 , jp4;
	JComboBox cbox;
	JLabel jlabel , valueOfn;
	JButton suffel , start , fetch;
	JTextField jt; 
	
	Myframe(){
		t1 = new Thread(this , "t1");
		t2 = new Thread(this , "t2");
		t3 = new Thread(this , "t3");
		t4 = new Thread(this , "t4");
		arr1 = new Integer[ar.size()];
		arr2 = new Integer[ar.size()];
		arr3 = new Integer[ar.size()];
		arr4 = new Integer[ar.size()];
		createComponent();
		placeAndConfigComponent();
		addComponent();
		addActionListeners(); 
		
	}
	
	
	void createComponent() {
		jp1 = new MyPanel(this);
		jp2 = new MyPanel(this);
		jp3 = new MyPanel(this);
		jp4 = new MyPanel(this);
		
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
		sort1 = new SortingAlgo();
		sort2 = new SortingAlgo();
		sort3 = new SortingAlgo();
		sort4 = new SortingAlgo();
		if(e.getSource() == suffel || e.getSource() == fetch) {
			n = Integer.parseInt(jt.getText());
			set_n();
			jumble();
			arr1 = ar.toArray(arr1);
			arr2 = ar.toArray(arr2);
			arr3 = ar.toArray(arr3);
			arr4 = ar.toArray(arr4);
			t1.start();
			t2.start();
			t3.start();
			t4.start();
		}
		else if (e.getSource() == start) {
				sort1.bubblesort(arr1 , jp1);
				sort2.mergsort(arr2, 0, arr2.length - 1 , jp2);
				sort3.bubblesort(arr3 , jp3);
				sort4.mergsort(arr4, 0, arr2.length - 1 , jp4);
	}
	}
	
	void set_n() {
		jp1.set_n(n);
		jp2.set_n(n);
		jp3.set_n(n);
		jp4.set_n(n);
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
	
	public void run() {
		if (Thread.currentThread().getName().equals("t1")) {
			jp1.paintCaller(arr1 , n);
			sort1.bubblesort(arr1 , jp1);
			jp1.checkit(arr1);
		}
		else if (Thread.currentThread().getName().equals("t2")){
			jp2.paintCaller(arr2 , n);
			sort2.mergsort(arr2, 0, arr2.length - 1 , jp2);
			jp2.checkit(arr2);
		}
		else if (Thread.currentThread().getName().equals("t3")){
			jp3.paintCaller(arr3 , n);
			sort3.bubblesort(arr3 , jp3);
			jp3.checkit(arr3);
		}		
		else{
			jp4.paintCaller(arr4 , n);
			sort4.mergsort(arr4, 0, arr4.length - 1 , jp4);
			jp4.checkit(arr4);
		}	
	}
	 
	public static void main(String[] args) {
		new Myframe();
	}

}
