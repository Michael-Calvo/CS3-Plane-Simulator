/**
 * 
 */

/**
 * @author Mike Calvo
 *
 */
public class Averager {
	int count;
	double sum;
	
	public Averager() {
		count=0;
		sum=0;
	}
	
	public void addNumber(double num) {
	count++;
	sum+=num;
	}
	
	public double average() {
		if(count==0) {
			return Double.NaN;
		}
		else
			return sum/count;
	}
	
	public int howManyNumbers() {
		return count;
	}
}
