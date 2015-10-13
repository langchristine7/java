package exo02;

public class Point3D extends Point2D {

	private int z;

	public Point3D() {
		this(0, 0, 0);
	}

	/**
	 * @param z
	 */
	public Point3D(int x, int y, int z) {
		super(x, y);
		this.setZ(z);
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
		buffer.append(super.toString());
		buffer.append(", z = ").append(this.getZ());
		return buffer.toString();
	}

	@Override
	public void afficher() {
		System.out.println(this.toString());
	}

	public void translater (int dX, int dY, int dZ){
		super.translater(dX, dY);
		this.setZ(this.getZ() + dZ);
	}
}

