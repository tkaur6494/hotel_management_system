//contains only get and set methods for assigning and updating values
public abstract class Room implements Comparable {

	private String wing;
	private Integer roomNumber;
	private String status;
	private static Integer count = 0;
	private Integer uniqueId;
	private Integer roomType; 									//double room type - 2, family room type - 1
	protected Integer accomodation;
	protected Integer hoursToClean; 							//declaring protected as they are being used in child classes
	
	public Room(String wing, Integer roomNumber, String status, Integer accomodation, Integer hoursToClean) {
		this.wing = wing;
		this.roomNumber = roomNumber;
		this.status = status;
		this.accomodation = accomodation;
		this.hoursToClean = hoursToClean;
		count = count + 1;
		this.uniqueId = count;
	}
	
	public Integer getUniqueId() {
		return this.uniqueId;
	}
	
	public String toString() {
		return "Wing - "+this.wing+" Room Number - "+this.roomNumber+" Status - "+this.status+" Unique Id - "+this.uniqueId;
	}

	@Override
	public int compareTo(Object o) {
		Room room_new = (Room)o;
		return this.getUniqueId().compareTo(room_new.getUniqueId());
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getWing() {
		return this.wing;
	}
	
	public String getStatus() {
		return this.status;
	}
	
}

