
public class FamilyRoom extends Room {
	
	
	public FamilyRoom(String wing, Integer roomNumber, String status, Integer accomodation, Integer hoursToClean) {
		super(wing, roomNumber, status, accomodation, hoursToClean);
		
	}
	
	public String toString() {
		String str = super.toString(); 
		return str+" Type - Family Room Accomodation - "+this.accomodation+" Hours to clean - "+this.hoursToClean;
	}
}
