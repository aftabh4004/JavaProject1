package bubbleSortGraphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.*;
public class MyPanel extends JPanel{
	final int pw = 600 ,  ph = 300 , fw = 1210, fh = 700;
	int x = 0 , w , h , c = 0 , n;
	Myframe f;
	Image offimage;
    Graphics offg ;
    Color col;
    
	
	MyPanel(Myframe f){
		colorPanel();
		this.f = f; 
	}
	public void paint(Graphics g) {
		update(g);
	}
	
	void set_n(int n) {
		this.n = n;
		w = pw/n;
	}
	
	void colorPanel() {
		x = 0;
		w = pw;
		h = ph;
		col = Color.BLACK;
		paintImmediately(0, 0, pw, ph);
	}
	public void update(Graphics g) {
		
		if (offimage == null) {
	        offimage = createImage(getWidth(), getHeight());
	        offg = offimage.getGraphics();
	    }
	    Graphics2D g2d = (Graphics2D)offg;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(col);
		g2d.fillRect(x, 0, w, h);
		
		g.drawImage(offimage, 0, 0, this);
	}
	
	void swap(int x1 ,int y1 , int x2 , int y2 ) {
		
		x = x1;
		h = y1;
		col = Color.green;
		paintImmediately(0, 0, pw, ph);
		//repaint();
		x = x2;
		h = y2;
		paintImmediately(0, 0, pw, ph);
		
		try {
			Thread.sleep(30);			
		}
		catch(InterruptedException i) {
			System.out.println(i);
		}
		col = Color.black;
		h = 300;
		x = x1;
		paintImmediately(0, 0, pw, ph);
		x = x2;
		paintImmediately(0, 0, pw, ph);

		col = Color.white;
		x = x1 ;
		h = y2;
		paintImmediately(0, 0, pw, ph);
		
		x = x2 ;
		h = y1;
		paintImmediately(0, 0, pw, ph);
			
		
	}
	
	public void paintCaller(Integer[] ar , int n) {
		try {
			//System.out.println(Thread.currentThread().getName());
			for(int data : ar) {
				h = data;
				col = Color.red;
				paintImmediately(0, 0, pw, ph);
				Thread.sleep(30);
				col = Color.white;
				paintImmediately(0, 0, pw, ph);
				x += w;
			}
		}
		catch(InterruptedException i) {
			System.out.println(i);
		}
	}
	
	void checkit(Integer[] arr) {
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
		paintImmediately(0, 0, pw, ph);
	}
}
