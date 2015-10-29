package fr.jmx;

public class Calculatrice implements CalculatriceMBean {
	private double dernierResultat;

	/* (non-Javadoc)
	 * @see fr.jmx.CalculatriceMBean#getDernierResultat()
	 */
	@Override
	public double getDernierResultat() {
		return this.dernierResultat;
	}

	/* (non-Javadoc)
	 * @see fr.jmx.CalculatriceMBean#setDernierResultat(double)
	 */
	@Override
	public void setDernierResultat(double dernierResultat) {
		this.dernierResultat = dernierResultat;
	}

	/* (non-Javadoc)
	 * @see fr.jmx.CalculatriceMBean#add(double, double)
	 */
	@Override
	public double add(double c1, double c2) {
		this.dernierResultat = c1 + c2;
		return this.dernierResultat;

	}

	/* (non-Javadoc)
	 * @see fr.jmx.CalculatriceMBean#sub(double, double)
	 */
	@Override
	public double sub(double c1, double c2) {
		this.dernierResultat = c1 - c2;
		return this.dernierResultat;

	}

	/* (non-Javadoc)
	 * @see fr.jmx.CalculatriceMBean#mult(double, double)
	 */
	@Override
	public double mult(double c1, double c2) {
		this.dernierResultat = c1 * c2;
		return this.dernierResultat;

	}

	/* (non-Javadoc)
	 * @see fr.jmx.CalculatriceMBean#div(double, double)
	 */
	@Override
	public double div(double c1, double c2) {
		this.dernierResultat = c1 / c2;
		return this.dernierResultat;

	}
}
