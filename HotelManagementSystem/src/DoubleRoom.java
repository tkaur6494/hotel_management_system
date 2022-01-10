//inherits the Room class
public class DoubleRoom extends Room {

	
	
	public DoubleRoom(String wing, Integer roomNumber, String status, Integer accomodation, Integer hoursToClean) {
		super(wing, roomNumber, status, accomodation, hoursToClean);
	}
	
	public String toString() {
		String str = super.toString(); 
		return str+" Type - Double Room Accomodation - "+this.accomodation+" Hours to clean - "+this.hoursToClean;
	}
}
