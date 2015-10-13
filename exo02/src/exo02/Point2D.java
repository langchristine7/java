package exo02;

public class Point2D {
	private int xx, y;

	/**
	 * @param xx
	 * @param y
	 */

	public Point2D() {
	}

	public Point2D(int x, int y) {
		this.setXx(x);
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
	public int getXx() {
		return this.xx;
	}

	public void setXx(int valX) {
		this.xx = valX;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[ x=");
		builder.append(this.getY());
		builder.append(", y=");
		builder.append(this.getXx());
		builder.append(" ]");
		return builder.toString();
	}

	public void afficher() {
		System.out.println(this.toString());
	}

	public void translater(int dX, int dY) {
		//
		// this.x += dX; this.y += dY;
		//
		// prefer :
		this.setXx(this.getXx() + dX);
		this.setY(this.getY() + dY);
	}
}
