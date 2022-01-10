//Client class for storing client details
public class Client implements Comparable {
	private String name;
	private String emailAddress;
	private static Integer count = 0;								//for keeping count of clients and for asisgning unique id 
	private Integer uniqueId;
	private Room room;
	
	public Client(String name, String emailAdd) {
		this.name = name;
		this.emailAddress = emailAdd;
		count = count + 1;
		uniqueId = count;
	}
	
	public Integer getUniqueId() {
		return this.uniqueId;
	}
	
	public Object getRoom() {
		return this.room;
	}
	
	public String toString() {
		//Adding room details which are allocated to the client
		if(this.room!=null) {
			return "Name - "+this.name+" Email - "+this.emailAddress+" Unique Id - "+this.uniqueId+" Room Details - "+this.room.toString();
		}
		else {
			return "Name - "+this.name+" Email - "+this.emailAddress+" Unique Id - "+this.uniqueId ;
		}
		
	}

	@Override
	public int compareTo(Object o) {
		Client c = (Client)o;
		return this.uniqueId.compareTo(c.uniqueId);						//comparing based on client id
		
	}
	
	public void setRoom(Object r) {
		this.room = (Room)r;											//assigning a particular room to client 
	}
	
	
	
}
