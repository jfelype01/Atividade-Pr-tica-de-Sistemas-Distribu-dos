package Trabalho_SD;

class RacerExtended extends Thread {
	private String threadName;
	
	RacerExtended (String name) {
		threadName = name;
		System.out.println("Creating " + threadName);
	}
	
	public void run() {
		//System.out.println("Running " + threadName);
		try {
//			while(true) {
			for (int i = 0; i < 1000; i++) {
				System.out.println("Racer: " + threadName + " - corrida " + (i+ 1));
//				System.out.println("Racer " + threadName + " - imprimindo");
				
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("Thread " + threadName + "interrupted.");
		}
		//System.out.println("Thread " + threadName + "exiting.");
	}
}

class Race_2 {
	private int n_racers;
	private RacerExtended[] racers;
	
	Race_2(int qtde_racers) {
		n_racers = qtde_racers;
	}

	public void createRacers() {
		try {
			racers = new RacerExtended[n_racers];
			for (int i = 0; i < n_racers; i++) {
				racers[i] = new RacerExtended("Racer " + (i + 1));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void executeRace() {
		try {
//			for (int i = 0; i < n_racers; i++) {
//				racers[i].start();
//				racers[i].setPriority(i % 10 + 1);
//			}
			
			for (int i = 0; i < n_racers; i += 2) {
				racers[i].start();
			}
			
			for (int i = 0; i < n_racers; i += 2) {
				racers[i].join();
			}
			
			for (int i = 1; i < n_racers; i += 2) {
				racers[i].start();
			}
			
			for (int i = 1; i < n_racers; i += 2) {
				racers[i].join();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

public class ExtendedThread_Q01 {

	public static void main(String[] args) {
//		RacerExtended racer = new RacerExtended("01");
//		
//		racer.start();
		
		Race_2 race = new Race_2(10);
		
		race.createRacers();
		race.executeRace();
	}

}
