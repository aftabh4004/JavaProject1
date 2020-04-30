package bubbleSortGraphics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileHandler {
	RandomAccessFile raf;
	FileHandler(){
		openfile();
	}
	void openfile() {
		try {
			raf = new RandomAccessFile("Record.txt", "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	void closeFile() {
		try {
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void writeString(String s , boolean flag) {
		String temp = null;
		try {
			while(raf.readLine() != null);
			if(flag) {
				temp = "Unsorted Array ";
				raf.writeBytes("******************************************************************************************\n");
				raf.writeBytes(temp + s + "\n\n");
			}
			else {
				temp = "Sorted Array";
				raf.writeBytes(temp + s + "\n\n");
				raf.writeBytes("*******************************************************************************************\n");
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			
		}
	}
	
	void writeSortInstance(SortingAlgo s) {
		try {
			while(raf.readLine() != null);
			
			raf.writeBytes("Algorithm :" + s.algo + "\n");
			raf.writeBytes("Time Complexity :" + s.tc + "\n");
			raf.writeBytes("No of Comparision :" + s.no_of_cmp + "\n");
			raf.writeBytes("No of Swap :" + s.no_of_swap + "\n\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			
		}
	}
	
}
