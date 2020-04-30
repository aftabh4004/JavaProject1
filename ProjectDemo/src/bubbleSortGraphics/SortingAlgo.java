package bubbleSortGraphics;

import java.awt.Color;

public class SortingAlgo {
	
/*****************************************************************************************************************/
/************************************** Bubble Sort **************************************************************/
	void bubblesort(Integer[] arr , MyPanel jp) {

		//bubble sort
		for (int i = 1; i < arr.length ; i++) {
			for (int j = 0 ; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					swapit(arr , j  , j+1 , jp);
				}	
			}
		}
	}
	
/***************************************************************************************************************/
	
/*****************************************************************************************************************/
/************************************** MergSort **************************************************************/
	
	//Recursive method for merge sort 
	void mergsort(Integer arr[], int l, int r , MyPanel jp) 
	{ 
		if (l < r) 
		{ 
			// Find the middle point 
			int m = (l+r)/2; 
			mergsort(arr, l, m ,jp); 
			mergsort(arr , m+1, r , jp); 
			merge(arr, l, m, r ,jp); 
		} 
	}
	//merging
	private void merge(Integer arr[], int l, int m, int r , MyPanel jp) 
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
				swapmerg(k , L[i] , jp);
				i++; 
			} 
			else
			{ 
				arr[k] = R[j];
				swapmerg(k , R[j] , jp);
				j++; 
			} 
			k++; 
		} 

		while (i < n1) 
		{ 
			arr[k] = L[i]; 
			swapmerg(k , L[i] , jp);
			i++; 
			k++; 
		} 

		while (j < n2) 
		{ 
			arr[k] = R[j];
			swapmerg(k , R[j] , jp);
			j++; 
			k++; 
		} 
	} 
	
	
	//for swap in merge sort , because in merge sort there is no any simple swap but comparison between to sub array
	void swapmerg(int ind , int val , MyPanel jp) {
		jp.x = jp.w*ind;
		jp.h = val;
		
		jp.col = Color.green;
		jp.paintImmediately(0, 0, jp.pw, jp.ph);
		try {
			Thread.sleep(30);
		}catch(InterruptedException e) {
			
		}
		jp.h = jp.pw;
		jp.col = Color.black;
		jp.paintImmediately(0, 0, jp.pw, jp.ph);
		
		
		jp.h = val;
		jp.col = Color.white;
		jp.paintImmediately(0, 0, jp.pw, jp.ph);
		
	}
	
/***************************************************************************************************************/
	
/********************************************************************************************************************/
/************************************** Insertion Sort **************************************************************/
	
	void insertion_sort(Integer[] arr , MyPanel jp) {
		for (int i = 1 ; i < arr.length ; i++) {
			int j = i;
			while(j > 0 && arr[j -1] > arr[j]) {
				swapit(arr , j - 1 , j , jp);
				j--;
			}
		}
	}
	
/********************************************************************************************************************/

/********************************************************************************************************************/
/************************************** Selection Sort **************************************************************/

	void selection_sort(Integer arr[] , MyPanel jp) 
    { 
        int n = arr.length; 
  
        for (int i = 0; i < n-1; i++) 
        { 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arr[j] < arr[min_idx]) 
                    min_idx = j; 
  
         swapit(arr , min_idx , i , jp);
        } 
    }

/********************************************************************************************************************/
	//For common swap
	void swapit(Integer[] arr  , int i , int j , MyPanel jp){
		jp.swap(i*jp.w , arr[i] , j*jp.w , arr[j] );
		Integer temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
