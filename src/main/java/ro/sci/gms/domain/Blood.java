package ro.sci.gms.domain;

public enum Blood {
	A, B, AB, O, unspecified;

	public String toString() {
		// switch this.values(): Did not know how to implement switch
		// instruction here, so I used the plain old IF

		if (this.equals(A)) return "A";
		if (this.equals(B)) return "B";	
		if (this.equals(AB)) return "AB";
		if (this.equals(O)) return "A";
		
		return "U";
		
	}

}
