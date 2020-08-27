
public class Complexity {
	private static int counter = 0;
	
	public static void main(String[] args) {
		method1(256);
		method2(256);
		method3(256);
		method4(256);
		method5(256);
		method6(3);
	}
	
// Complexity: O(N²)	
	public static void method1(int n) {
		int  count = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				count++;
			}
		}
		System.out.println("Operations for O(N²): " + count);
	}
	
// Complexity: O(N³)
	public static void method2(int n) {
		int count = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; k++) {
					count++;
				}
			}
		}
		System.out.println("Operations for O(N³): " + count);
	}
	

// Complexity: O(log n)	
	public static void method3(int n) {
		int  count = 0;
		for(int i = 1; i < n; i = i*2) {
			count++;
		}
		System.out.println("Operations for O(log n): " + count);
	}
	
	
//Complexity: O(n log n) 	
	public static void method4(int n) {
		int count = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 1; j < n; j = j*2) {
				count++;
			}
		}
		System.out.println("Operations for O(n log n): " + count);
	}

	
//Complexity: O(log log n)	
	public static void method5(int n) {
		int count = 0, constant = 2;
		for(int i = 2; i < n; i = (int)Math.pow(i, constant)) {
			count++;
		}
		System.out.println("Operations for O(log log n): " + count);
	}
	
	
//Complexity: O(2^N)
//Using fibonacci Series  	
	public static void method6(int n) {
		fibo(n);
		System.out.println("Operations for O(2^N): " + getCount());
	}

	public static long fibo(int n) {
		counter++;
		if(n <= 1) {
			return n;
		}		
		return fibo(n-1) + fibo(n-2);
	}
	
	public static int getCount() {
		return counter;
	}
}
