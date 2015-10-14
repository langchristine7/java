package abs;

import java.util.Date;

public class Run {

	public static void main(String[] args) {
		Chien c1 = new Chien();
		c1.setAge(3);
		c1.setNom("Sam");
		c1.setAdoption(new Date());

		Chat c2 = new Chat();
		c2.setAge(1);
		c2.setNom("Arthur");
		c2.setPelage("long");

		c1.crier();
		c2.crier();

		Animal tab[] = new Animal[5];
		tab[0] = new Chien();
		tab[1] = c1;
		tab[2] = c2;
		tab[3] = new Chat();
		for (Animal animal : tab) {
			if (animal != null) {
				animal.crier();
			}
		}
	}

}
