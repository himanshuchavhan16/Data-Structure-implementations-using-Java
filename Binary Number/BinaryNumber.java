/**
 * 
 */

/**
 * @author Himanshu Chavan
 *
 */
public class BinaryNumber {
	
	private int data[];
	
	private boolean overflow;
	
	public BinaryNumber(int length){
		//a constructor that initializes the length of the array 
		this.data = new int[length];
	}
	
	public BinaryNumber(String str){
		//a constructor that initializes the instance variable data
		this.data = new int[str.length()];
		for(int i = 0; i < str.length(); i++) {
			this.data[i] = Character.getNumericValue(str.charAt(i));
		}
	}	
	
	public int getLength(){
		// gives the length of the data
		return this.data.length;
	}
	
	public int getDigit(int index){
		// returns the digit at that location
		return this.data[index];
	}
	
	public void shiftR(int amount){
	// shift the data to right by the given amount 
		
		int len = amount + this.data.length;
		BinaryNumber arr = new BinaryNumber(len);
		for(int i = 0; i < this.data.length; i++) {
			arr.data[i + amount] = this.data[i];
		}
		this.data = arr.data;
	}
	
	public void add(BinaryNumber aBinaryNumber){
		//Adds two binary numbers with equal length
		int carry = 0, result = 0, len = this.data.length;
		if(len != aBinaryNumber.data.length) {
			System.out.println("The length of the two numbers is not equal");
		}else {
			for(int i = 0; i < len; i++) {
				result = this.data[i] + aBinaryNumber.data[i] + carry;
				if(result < 2) {
					this.data[i] = result;
					carry = 0;
				}else {
					this.data[i] = result % 2;
					carry = 1;
				}
			}
			
			if(carry == 1) {
				this.overflow = true;
			}
		}
	}
	
	public String toString(){
		//returns the string value of the binary number
		String result = "";
		int len = this.data.length;
		for(int i = 0; i < len; i++) {
			result += this.data[i];
		}
		
		if(this.overflow) return "Overflow";
		
		return result;
	}
	
	public int toDecimal(){
		//converts binary to decimal number
		int sum = 0, len = this.data.length;
		for(int i = 0; i < len; i++){
			sum += this.data[i] * (int) Math.pow(2, i); 
		}
		if(this.overflow){
			return sum + (int)Math.pow(2, len);
		}
		return sum;
	}
	
	public void clearOverflow(){
		//clears the overflow by switching the boolean flag 
		this.overflow = false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}
