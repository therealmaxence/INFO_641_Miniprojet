package Model;

public class Location {
	private String adress;
	private String preciseLoc;
	private int criticity; 	// Max criticity is 10
	
	
	public Location(String adress, int criticity) {
		this(adress, null, criticity);
	}
	
	
	public Location(String adress, String preciseLoc, int criticity) {
		this.adress = adress;
		this.preciseLoc = preciseLoc;
		this.setCriticity(criticity);
	}
	
	public void setCriticity(int criticity) {
		if (criticity < 0 || criticity > 10) {
			throw new IllegalArgumentException("Criticity value must be between 0 and 10!");
		}
		this.criticity = criticity;
	}


	public int getCriticity() {
		return criticity;
	}


	public String getAdress() {
		// TODO Auto-generated method stub
		return adress;
	}
}
