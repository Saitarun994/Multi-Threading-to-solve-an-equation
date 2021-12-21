import java.math.*;

public class sqrtThread extends Thread {
	int upper;
	int lower;
	BigDecimal product;
	
	/**
	 * A thread that computes the equation for values between the upper and lower limit
	 * @param upper	the last number to compute
	 * @param lower	the 1st number to compute
	 */
	public sqrtThread(int upper, int lower)
	{
		this.upper=upper;
		this.lower= lower;
		this.product=BigDecimal.ZERO;
	}
	
	@Override
	public void run()
	{	
		BigDecimal d= BigDecimal.ZERO;
		 BigDecimal product= BigDecimal.ONE;
		 MathContext mc = new MathContext(33,RoundingMode.FLOOR) ;
		 for(int i=this.lower;i<this.upper;i++)
		 {	
			 BigDecimal x= new BigDecimal((double)Math.pow((4*i+2),2));
			 d= BigDecimal.ONE.subtract(BigDecimal.ONE.divide(x,33,RoundingMode.HALF_UP)).stripTrailingZeros();
			 product= product.multiply(d,mc);
		 }
		 this.product=product;
	}
}
