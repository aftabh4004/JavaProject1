package bubbleSortGraphics;

import java.awt.Color;

public class SortingAlgo {
	
	Thread t1 ;
	Thread t2 ;
	void bubblesort(Integer[] arr , Myframe f) {

		//bubble sort
		for (int i = 1; i < arr.length ; i++) {
			for (int j = 0 ; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					swapit(arr , j  , j+1 , f);
				}	
			}
			

		}
		
		for (Integer i:arr) {
			System.out.println(i);
		}
	}
	
	
	
	void mergsort(Integer arr[], int l, int r , Myframe f) 
	{ 
		if (l < r) 
		{ 
			// Find the middle point 
			int m = (l+r)/2; 
			mergsort(arr, l, m ,f); 
			mergsort(arr , m+1, r , f); 
			// Sort first and second halves 
			/*t1 =  new Thread(new Runnable() {
				public void run() {
					
				}
			});
			t1.start();
					
			
			 t2 = new Thread(new Runnable() {
				public void run() {
					
				}
			});
			t2.start();
			 
			 try {
			 this.t1.join();
			 this.t2.join();
			 }catch(InterruptedException e) {
				 System.out.println(e);
			 }*/
			 
			// Merge the sorted halves 
			merge(arr, l, m, r ,f); 
		} 
	}
	
	private void merge(Integer arr[], int l, int m, int r , Myframe f) 
	{ 
		
		int n1 = m - l + 1; 
		int n2 = r - m; 

		
		int L[] = new int [n1]; 
		int R[] = new int [n2]; 

		
		for (int i=0; i<n1; ++i) 
			L[i] = arr[l + i]; 
		for (int j=0; j<n2; ++j) 
			R[j] = arr[m + 1+ j]; 

		int i = 0, j = 0; 

		int k = l; 
		while (i < n1 && j < n2) 
		{ 
			if (L[i] <= R[j]) 
			{ 
				arr[k] = L[i]; 
				swapmerg(k , L[i] , f);
				i++; 
			} 
			else
			{ 
				arr[k] = R[j];
				swapmerg(k , R[j] , f);
				j++; 
			} 
			k++; 
		} 

		while (i < n1) 
		{ 
			arr[k] = L[i]; 
			swapmerg(k , L[i] , f);
			i++; 
			k++; 
		} 

		while (j < n2) 
		{ 
			arr[k] = R[j];
			swapmerg(k , R[j] , f);
			j++; 
			k++; 
		} 
	} 
	
	void swapit(Integer[] arr  , int i , int j , Myframe f){
		f.swap(i*f.w , arr[i] , j*f.w , arr[j]);
		//System.out.println("i = " + i +  " j = " + j);
		Integer temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	void swapmerg(int ind , int val , Myframe f) {
		f.x = f.w*ind;
		f.h = val;
		
		f.col = Color.green;
		f.paintImmediately(0, 0, 800, 300);
		try {
			Thread.sleep(30);
		}catch(InterruptedException e) {
			
		}
		f.h = 300;
		f.col = Color.black;
		f.paintImmediately(0, 0, 800, 300);
		
		
		f.h = val;
		f.col = Color.white;
		f.paintImmediately(0, 0, 800, 300);
		
	}
}
