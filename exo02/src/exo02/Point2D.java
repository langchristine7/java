package exo02;

public class Point2D {
	private int x, y;

	/**
	 * @param x
	 * @param y
	 */

	public Point2D() {
	}

	public Point2D(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return this.x;
	}

	public void setX(int valX) {
		this.x = valX;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("x=");
		builder.append(this.getY());
		builder.append(", y=");
		builder.append(this.getX());
		return builder.toString();
	}

	public void afficher() {
		System.out.print("x = " + this.getX());
		System.out.println(" y = " + this.getY());
	}

	public void translater(int dX, int dY) {
		//
		// this.x += dX; this.y += dY;
		//
		// prefer :
		this.setX(this.getX() + dX);
		this.setY(this.getY() + dY);
	}
}
