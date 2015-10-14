package com.exo.entite;

interface IPersonne {

	/**
	 * @return the prenom
	 */
	String getPrenom();

	/**
	 * @param prenom
	 *            the prenom to set
	 */
	void setPrenom(String prenom);

	/**
	 * @return the age
	 */
	int getAge();

	/**
	 * @param age
	 *            the age to set
	 */
	void setAge(int age);

	String getNom();

	void setNom(String unNom);

	void inverser(IPersonne a);

}