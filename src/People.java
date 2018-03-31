
public class People {
	String CID;
	String FirstName;
	String LastName;
	String City;
	String AddressLine1;
	String SocialSecurityNumber;

	public People(String cID, String firstName, String lastName, String city, String addressLine1,
			String socialSecurityNumber) {
		super();
		CID = cID;
		FirstName = firstName;
		LastName = lastName;
		City = city;
		AddressLine1 = addressLine1;
		SocialSecurityNumber = socialSecurityNumber;
	}

	public String get(String x) {
		if (x.equals("FirstName"))
			return this.FirstName;
		else if (x.equals("LastName"))
			return this.LastName;
		else if (x.equals("City"))
			return this.City;
		else if (x.equals("CID"))
			return this.CID;
		else if (x.equals("SocialSecurityNumber"))
			return this.SocialSecurityNumber;
		else
			return this.AddressLine1;
	}

}
