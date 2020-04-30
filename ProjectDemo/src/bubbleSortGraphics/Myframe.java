package bubbleSortGraphics;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;




public class Myframe extends JFrame implements ActionListener , Runnable{
	private static final long serialVersionUID = 1L;
	final int pw = 600 ,  ph = 300 , fw = 1210, fh = 700; // Fixed size of frame and panels
	final int MAX_FIT = 300;						//max value this fits into panel
	int n;
	Image offimage;
    Graphics offg ;
    Color col;
    SortingAlgo sort1 , sort2 , sort3 , sort4;		//To avoid clashes
    Integer[] arr1 , arr2 , arr3 , arr4;			//To avoid clashes , since array is mutated during sorting
    Thread t1 , t2 , t3 , t4;						//each thread use to handle each panel
    ArrayList<Integer> ar = new ArrayList<Integer>();
	
	MyPanel jp1 , jp2 , jp3 , jp4;
	JLabel  valueOfn , jlab1 , jlab2 , jlab3 , jlab4;
	JButton  fetch , reset;
	JTextField jt; 
	
	Myframe(){
		
		arr1 = new Integer[ar.size()];
		arr2 = new Integer[ar.size()];
		arr3 = new Integer[ar.size()];
		arr4 = new Integer[ar.size()];
		createComponent();
		placeAndConfigComponent();
		addComponent();
		addActionListeners(); 
		
	}
	
	//Creating thread and SortingAlgo instances
	void createThread() {
		t1 = new Thread(this , "t1");
		t2 = new Thread(this , "t2");
		t3 = new Thread(this , "t3");
		t4 = new Thread(this , "t4");
		
		sort1 = new SortingAlgo();
		sort2 = new SortingAlgo();
		sort3 = new SortingAlgo();
		sort4 = new SortingAlgo();
	}
	
	
	void createComponent() {
		jp1 = new MyPanel(this);
		jp2 = new MyPanel(this);
		jp3 = new MyPanel(this);
		jp4 = new MyPanel(this);
		
		fetch = new JButton("Fetch");
		reset = new JButton("Reset");
		jt = new JTextField(10);
		valueOfn = new JLabel("For N = ");
		jlab1 = new JLabel("Bubbel Sort");
		jlab2 = new JLabel("Merge Sort");
		jlab3 = new JLabel("Insertion Sort");
		jlab4 = new JLabel("Selection Sort");
	}
	
	void addComponent() {
		add(valueOfn);
		add(jt);
		add(fetch);
		add(reset);
		add(jlab1);
		add(jlab2);
		add(jlab3);
		add(jlab4);
		add(jp1);
		add(jp2);
		add(jp3);
		add(jp4);
		
	}
	
	void placeAndConfigComponent() {
		this.getContentPane().setBackground(Color.GRAY);
		this.setLayout(null);
		setBounds(100, 0, fw, fh);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jlab1.setBounds(0, 30, 150, 30);
	    jlab2.setBounds(0, 360, 150, 30);
	    jlab3.setBounds(610, 30, 150, 30);
	    jlab4.setBounds(610, 360, 150, 30);
		valueOfn.setBounds(100, 0, 60, 30);
		jt.setBounds(155, 0, 100, 30);
		fetch.setBounds(260, 0, 100, 30);
		reset.setBounds(370, 0, 100, 30);
		jp1.setBounds(0, 60, pw, ph);
		jp1.setBackground(Color.black);
	    jp2.setVisible(true);
	    jp2.setBounds(0, 390, pw, ph);
	    jp2.setBackground(Color.black);
	    jp2.setVisible(true);
	    jp3.setBounds(610, 60, pw, ph);
	    jp3.setBackground(Color.black);
	    jp3.setVisible(true);
	    jp4.setBounds(610 ,390, pw, ph);
	    jp4.setBackground(Color.black);
	    jp4.setVisible(true);
	    valueOfn.setForeground(Color.white);
	    jlab1.setForeground(Color.white);
	    jlab2.setForeground(Color.white);
	    jlab3.setForeground(Color.white);
	    jlab4.setForeground(Color.white);
	    this.setVisible(true);
	}
	
	void addActionListeners() {
	    fetch.addActionListener(this);
	    reset.addActionListener(this);
	}
	
	//Implementing method of ActionListener
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == fetch) {
			createThread();
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
			
			try {
				t1.join();
				t2.join();
				t3.join();
				t4.join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == reset) {
			jp1.colorPanel();
			jp2.colorPanel();
			jp3.colorPanel();
			jp4.colorPanel();
			jt.setText("");
		}
		
	}
	
	//To export the value of n to MyPanel objects
	void set_n() {
		jp1.set_n(n);
		jp2.set_n(n);
		jp3.set_n(n);
		jp4.set_n(n);
	}
	
	//Generating an array and initialized with Random integer upto the size of array
	void jumble() {
		ar.clear();
		Random r = new Random();
		for (int i = 0 ; i < n ; i++) {
			int data = 10 + r.nextInt(MAX_FIT);
			ar.add(data);
			
		}
				
	}
	
	//Implementing run method of Runnable 
	public void run() {
		
		//Each thread doing one sorting on one panel
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
			sort3.insertion_sort(arr3 , jp3);
			jp3.checkit(arr3);
		}		
		else{
			jp4.paintCaller(arr4 , n);
			sort4.selection_sort(arr4 , jp4);
			jp4.checkit(arr4);
		}	
	}
	

	public static void main(String[] args) {
		new Myframe();
	}

}
