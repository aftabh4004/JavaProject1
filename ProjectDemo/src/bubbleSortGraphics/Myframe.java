package bubbleSortGraphics;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import sun.security.jca.GetInstance;



public class Myframe extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	int n;
	JFrame jf;
	JComboBox cbox;
	JLabel jlabel , valueOfn;
	JButton button , start , fetch;
	JTextField jt; 
	String[] str = {"Bubbel Sort" , "Merg Sort"};
	ArrayList<Integer> ar = new ArrayList<Integer>();
	Integer[] arr;
	Scanner scan = null;
	int x = 0 , w , h , c = 0 ;
	boolean flag = false;
	Image offimage;
    Graphics offg ;
    Color col;
    SortingAlgo sort;
    
	Myframe(){
		arr = new Integer[ar.size()]; 
	    arr = ar.toArray(arr);
		createComponent();
		addComponent();
		placeAndConfigComponent();
		addActionListeners(); 
	}
	
	void createComponent() {
		jf = new JFrame();
		button = new JButton("Suffel");
		start = new JButton("Start");
		fetch = new JButton("Fetch");
		jlabel = new JLabel("Choose any Sorting Algorithm   ");
		valueOfn = new JLabel("N ");
		jt = new JTextField(10);
		cbox = new JComboBox(str);
	}
	
	void addComponent() {
		jf.add(valueOfn);
		jf.add(jt);
		jf.add(fetch);
		jf.add(jlabel);
		jf.add(cbox);
		jf.add(start);
		jf.add(button);
		jf.add(this);
	}
	
	void placeAndConfigComponent() {
		jf.setLayout(new FlowLayout());
		jf.setPreferredSize(new Dimension(800, 300));
		setPreferredSize(new Dimension(800, 250));
		cbox.setBackground(Color.white);
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf.setBackground(Color.black);
	    jf.pack();
	    jf.setVisible(true);
	}
	
	void addActionListeners() {
		cbox.addActionListener(this);
	    start.addActionListener(this);
	    button.addActionListener(this);
	    fetch.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Myframe cur= this;
		sort = new SortingAlgo();
		
		if(e.getSource() == button || e.getSource() == fetch) {
			n = Integer.parseInt(jt.getText());
			w = 800/n;
			flag = true;
			paintImmediately(0 , 0 , 800 , 300);
			flag = false;
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
				paintImmediately(0, 0, 800, 300);
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
		paintImmediately(0, 0, 800, 300);
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
		paintImmediately(0, 0, 800, 300);
		//repaint();
		x = x2;
		h = y2;
		paintImmediately(0, 0, 800, 300);
		
		try {
			Thread.sleep(30);			
		}
		catch(InterruptedException i) {
			System.out.println(i);
		}
		col = Color.black;
		h = 300;
		x = x1;
		paintImmediately(0, 0, 800, 300);
		x = x2;
		paintImmediately(0, 0, 800, 300);

		col = Color.white;
		x = x1 ;
		h = y2;
		paintImmediately(0, 0, 800, 300);
		
		x = x2 ;
		h = y1;
		paintImmediately(0, 0, 800, 300);
			
		
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
				paintImmediately(0, 0, 800, 300);
				Thread.sleep(30);
				col = Color.white;
				paintImmediately(0, 0, 800, 300);
				x += w;
			}
		}
		catch(InterruptedException i) {
			System.out.println(i);
		}
	}
	
		public void paint(Graphics g) {
			if (flag) {
				x = 0;
				h = 300;
				w = 800;
				col = Color.black;
				update(g);
				w = 800/n;
			}
			else
				update(g);
		}
	
	
	public void update(Graphics g) {
		
		if (offimage == null) {
	        offimage = jf.createImage(getWidth(), getHeight());
	        offg = offimage.getGraphics();
	    }
	    Graphics2D g2d = (Graphics2D)offg;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(col);
		g2d.fillRect(x, 0, w, h);
		
		g.drawImage(offimage, 0, 0, this);
	}
	public static void main(String[] args) {
		new Myframe();
	}

}
