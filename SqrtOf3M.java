/**
 * A program to implemet multithreading to solve an equation faster
 */
import java.math.*;
/**
 * @author Sai Tarun Sathyan
 * @author Aarti
 *
 */
public class SqrtOf3M {

	/**
	 * The main function creates a certain number of threads to compute the equation and
	 * splits the work between all threads and runs them simultaneously
	 * @param args number of threads and number of computations
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		int threadCount=4;
		int computations=200000;
		if(args.length!=0)
		{
			threadCount=Integer.parseInt(args[0]);
			computations=Integer.parseInt(args[1]);
		}
		int increment=(int) Math.floor(computations/threadCount);
		sqrtThread threadList[]= new sqrtThread[threadCount];
		int lower=0;
		int upper=increment;
		
		double startTime= System.currentTimeMillis();
		
		for(int i=0;i<threadCount;i++)
		{
			threadList[i]=new sqrtThread(upper, lower);
			threadList[i].start();
			lower=upper+1;
			upper=lower+increment;
			if(i+2== threadCount)
			{
				upper=computations;
			}
		}
		
		
		MathContext mc = new MathContext(33,RoundingMode.HALF_UP);
		BigDecimal product=BigDecimal.ONE;
		for(int i=0;i<threadCount;i++)
		{	
			threadList[i].join();
			//System.out.println(i+":   "+threadList[i].product);
			product=product.multiply(threadList[i].product);
			//System.out.println(i+" "+product);
		}
		double endTime =System.currentTimeMillis();
		System.out.println("Sqrt(2) :-   "+product.add(product,mc));
		System.out.println("Total time taken: "+(endTime-startTime)+" Milli seconds");
	}

}
