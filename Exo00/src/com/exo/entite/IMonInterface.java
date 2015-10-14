package com.exo.entite;

import java.io.Serializable;

public interface IMonInterface extends Cloneable, Serializable
{

	public final static String VALEUR_CONSTANTE = "Bonjour";

	void faireQQChose(); // public abstract par defaut

	int calculerUnNombre(int unA); // public abstract par defaut

}
