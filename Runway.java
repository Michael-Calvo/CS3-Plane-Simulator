/**
 * 
 */

/**
 * @author Mike Calvo
 *
 */
public class Runway {
	private int timeToTakeOff;
	private int timeToLand;
	private int timeLeft;

	public Runway(int take, int land) {
		timeToTakeOff=take;
		timeToLand =land;
		timeLeft=0;
	}
	
	//checking if the runway is in use
	public boolean isBusy() {
		return (timeLeft>0);
	}
	public int timeLeft() {
		return timeLeft;
	}
	
	//decrementing the remaining time
	public void reduceTime() {
		if(timeLeft>0) {
			timeLeft--;
		}
	}
	
	public void StartTakeOff(){
		if(timeLeft>0) {
			System.out.println("Runway is in use");
		}
		
		timeLeft = timeToTakeOff;
	}
	
	public void StartLanding() {
		if(timeLeft>0) {
			System.out.println("Runway is in use");
		}
		timeLeft=timeToLand;
	}
}
