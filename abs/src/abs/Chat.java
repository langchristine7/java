package abs;

public class Chat extends Animal {

	private String pelage;

	/**
	 * @return the pelage
	 */
	public String getPelage() {
		return this.pelage;
	}

	/**
	 * @param pelage
	 *            the pelage to set
	 */
	public void setPelage(String pelage) {
		this.pelage = pelage;
	}

	public Chat() {
	}

	@Override
	public void crier() {
		System.out.println("Miaou ! ");
	}

}
