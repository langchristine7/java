package fr.db;

public class Run {

	public Run() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Db db = null;
		try {
			db = new Db();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			System.exit(-1);
		}
		db.seConnecter();

		// recherche du client id = 1
		System.out.println("\nrecupererClient --- Recherche du client no 1");

		db.recupererClient(1);
	}

}
