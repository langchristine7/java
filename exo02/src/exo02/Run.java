package exo02;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Point2D p1 = new Point2D();
		// Point2D p2 = new Point2D();
		//
		// p1.setX(12);
		// p1.setY(48);
		// p2.setX(3);
		// p2.setY(45);
		// Run.print("p1 : ");
		// p1.afficher();
		// Run.print("p2 : ");
		// p2.afficher();
		//
		// System.out.println("Translation de 3, 2");
		//
		// p1.translater(3, 2);
		// p2.translater(3, 2);
		//
		// Run.print("p1 : ");
		// p1.afficher();
		// Run.print("p2 : ");
		// p2.afficher();
		//
		// Point2D p3 = new Point2D(15, 59);
		// Run.print("p3 = ");
		// p3.afficher();
		//
		// p2 = null;
		// p1 = null;
		//
		// System.gc();

		// Marchandise m1 = new Marchandise();
		// Marchandise m2 = new Marchandise();
		// Passager p1 = new Passager();
		// Passager p2 = new Passager();
		// Avion<Marchandise> avion = new Avion<Marchandise>();
		// avion.charger(0, m1);
		// avion.charger(1, m2);
		// // avion.charger(2, p1);
		// // avion.charger(3, p2);
		//
		// // Passager p3 = (Passager) avion.decharger(2);
		// Marchandise m3 = avion.decharger(0);

		Point3D p10 = new Point3D();
		Point3D p11 = new Point3D(15, 45, 17);

		p10.afficher();
		p11.afficher();
		p11.translater(10, 15, 20);
		p11.afficher();
		Point2D p4 = new Point3D(55, 55, 55);
		p4.afficher();

		Object[] tab = new Object[10];
		tab[0] = new Point2D();
		tab[1] = new Point3D();
		tab[2] = new Point2D();
		tab[3] = new Point3D();

		Point3Dv2 p13 = new Point3Dv2();
		System.out.println("p13.x = " + p13.getXx());

		Calendar c1 = new GregorianCalendar();
		System.out.println("Calendar.getTime() : " + c1.getTime());
		c1.set(Calendar.DAY_OF_YEAR, c1.get(Calendar.DAY_OF_YEAR) + 3);
		System.out.println(c1.getTime());
		System.out.println(c1.get(Calendar.DAY_OF_WEEK));
	}

	static void print(String s) {
		System.out.print(s);
	}

}
