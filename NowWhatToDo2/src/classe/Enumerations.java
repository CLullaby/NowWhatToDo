package classe;

public enum Enumerations {
	Administrateur("Administrateur"), Tuteur("Tuteur"), Utilisateur("Utilisateur");
	
	private String value;
	
	private Enumerations(String value)
	{
		this.value = value;
	}
	
	public String returnValue()
	{
		switch(this) {
		case Administrateur:
			return this.value;
		case Tuteur:
			return this.value;
		case Utilisateur:
			return this.value;
		default:
			return null;
		}
	}
}