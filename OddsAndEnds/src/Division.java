
public class Division {

	public static class QR{
		int quotient, remainder;
		
		public QR(int q, int r){
			quotient = q;
			remainder = r;
		}
	}
	
	public static QR divide(int dividend, int divisor){
		int count = 0, sum = 0, remainder = dividend;
		if(dividend < divisor){
			return new QR(count,remainder);
		}
		while(sum < dividend){
			System.out.println("sum "+sum);
			sum += divisor;
			count++;
		}
		System.out.println("sum: "+sum);
		if(sum == dividend) remainder = 0;
		else if(sum > dividend){
			remainder = dividend - (sum - divisor);
		}
		return new QR(count, remainder);
	}
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		QR qr = divide(3,4);
		System.out.println("quotient = "+qr.quotient+" remainder: "+qr.remainder);
	}*/

}
