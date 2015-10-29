package lesThreads;

public class Run {
	private volatile int a;

	public static void main(String[] args) {
		Exemple r1 = new Exemple();
		Exemple r2 = new Exemple();

		// NE JAMAIS FAIRE
		// r1.run();

		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);

		// lancer les threads
		t1.start();
		System.out.println("thread 1");
		t2.start();
		System.out.println("thread 2");

		// t2.join(); attend l'autre
	}
}
