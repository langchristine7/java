package fr;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Reflexion {


	public static void main(String[] args) {

		try {
			Class maClassDate = Class.forName("java.util.Date");
			Object monInstanceDeDate = maClassDate.newInstance();
			Constructor[] mesConstructeurs = maClassDate.getConstructors();
			Object monInstanceDeDate2 = mesConstructeurs[4].newInstance(System.currentTimeMillis() + 1000);
			Method maMethodeGetTime = maClassDate.getMethod("getTime");
			Object resultatAppel = maMethodeGetTime.invoke(monInstanceDeDate2);
			System.out.println(resultatAppel);
			System.out.println(monInstanceDeDate);

			System.out.println(monInstanceDeDate2);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
