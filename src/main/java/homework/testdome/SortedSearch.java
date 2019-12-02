package homework.testdome;

public class SortedSearch {
    public static int countNumbers(int[] arr, int key) {
    	int low = 0;
		int high = arr.length - 1;
		int middle = 0;
		
		if(key < arr[low] || key > arr[high] || low > high){
			return -1;				
		}else if(key == arr[low]) {
			//TODO: think about the case there are dubplicated value in array.
			return low + 1;
		}else if(key == arr[high]) {
			return high + 1;
		}
		
		while(low <= high){
			middle = (low + high) / 2;
			if(arr[middle] > key){
				high = middle - 1;
			}else if(arr[middle] < key){
				low = middle + 1;
			}else{
				return middle;
			}
		}
		
		return middle;

    }
    
    public static void main(String[] args) {
        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4));
    }
}