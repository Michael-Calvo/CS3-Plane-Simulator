import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 */

/**
 * *******************************
 * Name:		Michael Gene Calvo
 * Student ID: 	N03158914
 * Class:		CPS 315 
 * *******************************
 *
 *
 *Averager and Boolean Source modeled and provide by book.
 */
public class PlaneSim {
	// time in Minutes.
	private static int TAKE_OFF_TIME = 2;
	private static int TIME_TO_LAND = 4;

	private static double TAKE_OFF_PROBABILITY = 0.1;
	private static double LANDING_PROBABILITY = 0.1;

	private static int TOTAL_TIME_SIMULATED = 6000;
	private static int TIME_UNTIL_CRASH = 5;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int next;
		int crash = 0;
		// The Queues to keep track of who is taking off and who is landing.
		Queue<Integer> LandingQueue = new LinkedList<>();
		Queue<Integer> TakeOffQueue = new LinkedList<>();
		Averager LandingWait = new Averager();
		Averager TakeOffWait = new Averager();

		// Currently only parameter is take off time;
		Runway runway = new Runway(TAKE_OFF_TIME, TIME_TO_LAND);

		System.out.println("Time to take off in minutes: " + TAKE_OFF_TIME);
		System.out.println("Time to land in minutes: " + TIME_TO_LAND);
		System.out.println("Take off probability: " + TAKE_OFF_PROBABILITY);
		System.out.println("Probability of Landing: " + LANDING_PROBABILITY);
		System.out.println("Total Simulated Time: " + TOTAL_TIME_SIMULATED);
		System.out.println("Time Until Crash: " + TIME_UNTIL_CRASH);

		// for loop to go over total simulated time.
		for (int current = 0; current < TOTAL_TIME_SIMULATED; current++) {

			if (IsTakingOff(TAKE_OFF_PROBABILITY)) {
				TakeOffQueue.add(current);
			}
			

			if (IsLanding(LANDING_PROBABILITY)) {
				LandingQueue.add(current);
			}

			// If the current time, minus the time it arrived is greater than 5, crash.
			// HOWEVER crash amount way too large
			if ( !(LandingQueue.isEmpty())&& (current-LandingQueue.peek()>TIME_UNTIL_CRASH ) ) {
				crash++;
				LandingQueue.remove();
			}
			if (!(runway.isBusy()) &&( (!(TakeOffQueue.isEmpty())|| !(LandingQueue.isEmpty())))) {

				// if the landing queue isnt empty, start that.
				// else do the take off.
				if (!(LandingQueue.isEmpty())) {
					next = LandingQueue.remove();
					LandingWait.addNumber(current - next);
					runway.StartLanding();
				} else {
					next = TakeOffQueue.remove();
					TakeOffWait.addNumber(current - next);
					runway.StartTakeOff();

				}

			}
			runway.reduceTime();

		}

		System.out.println("Planes taking off: " + TakeOffWait.howManyNumbers());
		System.out.println("Planes Landing: " + LandingWait.howManyNumbers());
		System.out.println("Planes crashed: " + crash);
		System.out.println("Average wait to land: " + LandingWait.average() + " minutes");
		System.out.println("Average wait to take off: " + TakeOffWait.average() + " minutes");

	}

	// Boolean source to check of a new plane is taking off.
	public static boolean IsTakingOff(double probTakeOff) {
		if ((probTakeOff < 0) || (probTakeOff > 1)) {
			throw new IllegalArgumentException("Illegal Probablilty.");
		}
		return Math.random() < probTakeOff;
	}

	public static boolean IsLanding(double prob) {
		if ((prob < 0) || prob > 1) {
			throw new IllegalArgumentException("Illegal Probability");
		}
		return Math.random() < prob;
	}

}
