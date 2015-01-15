package enumerations;

public enum Domaines {
	Administration("administration"), Travail("travail"), Transport("transport"), Alimentation("alimentation"), Sante("sante"), Services("services"), Logement("logement"), Loisir("loisir");
	
	private String value;
	
	private Domaines(String value)
	{
		this.value = value;
	}
	
	public String returnValue()
	{
		switch(this) {
		case Administration:
			return this.value;
		case Travail:
			return this.value;
		case Transport:
			return this.value;
		case Alimentation:
			return this.value;
		case Sante:
			return this.value;
		case Services:
			return this.value;
		case Logement:
			return this.value;
		case Loisir:
			return this.value;
		default:
			return null;
		}
	}
}
