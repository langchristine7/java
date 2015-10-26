package ProjetDB;

public class Run {

	public Run() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Db connexion = new Db();
		connexion.seConnecter();
		connexion.execute(null);
		// recherche du client id = 1
		System.out.println("\nrecupererClient --- Recherche du client no 1");
		connexion.recupererClient(1);
	}

}
