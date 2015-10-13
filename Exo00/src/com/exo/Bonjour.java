package com.exo;

import java.util.Date;

public class Bonjour {

	/**
	 * I say hello world
	 *
	 *
	 * @param args : not useful
	 */
	public static void main(String[] args) {

		System.out.println("Hello World ");		// comment
		/*
		 * line 1
		 * line 2
		 */
		Date d = new Date();
		java.sql.Date d2 = new java.sql.Date(System.currentTimeMillis());

		System.out.println("date normale : " + d.toString());
		System.out.println("date sql     : " + d2.toString());
	}
}
