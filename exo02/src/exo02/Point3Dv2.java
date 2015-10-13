package exo02;

public class Point3Dv2 {

	Point2D p;
	private int z;

	public Point3Dv2() {
		this(0, 0, 0);
	}

	/**
	 * @param z
	 */
	public Point3Dv2(int x, int y, int z) {
		this.p = new Point2D(x, y);
		this.setZ(z);
	}

	/**
	 * @return
	 * @see exo02.Point2D#getY()
	 */

	public int getY() {
		return this.p.getY();
	}

	/**
	 * @param y
	 * @see exo02.Point2D#setY(int)
	 */

	public void setY(int y) {
		this.p.setY(y);
	}

	/**
	 * @return
	 * @see exo02.Point2D#getXx()
	 */

	public int getXx() {
		return this.p.getXx();
	}

	/**
	 * @param valX
	 * @see exo02.Point2D#setXx(int)
	 */

	public void setXx(int valX) {
		this.p.setXx(valX);
	}

	/**
	 * @return the z
	 */
	public int getZ() {
		return this.z;
	}

	/**
	 * @param z
	 *            the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String toString (){
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.p.toString());
		buffer.delete(buffer.length() - 1, buffer.length());
		buffer.append(", z = ").append(this.getZ());
		buffer.append(']');
		return buffer.toString();
	}

	// @Override
	// public void afficher() {
	// System.out.println(this.toString());
	// }

	public void translater (int dX, int dY, int dZ){
		this.p.translater(dX, dY);
		this.setZ(this.getZ() + dZ);
	}
}

