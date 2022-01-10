
public class HotelManagementSystem implements IManagementSystem {
	private Dictionary clientList;												//using dictionary as a tree  because main function is to find client based on key
	private Stack availableRoomListDr;											//using stack for available room list  as the first room can always be allotted
	private LinkedList occupiedRoomListDr; 										//creating separate stacks based on type of room to avoid looping while pushing and popping 
	private Stack availableRoomListFr;											//on adding a new room it can be pushed getting O(1) for both push and pop
	private LinkedList occupiedRoomListFr;										//using linked list for occupied rooms as during check out need to traverse the list based on client id
																				//also there is no fixed order for removing the clients
	private LinkedList checkedOutAssignedFr;									//storing the rooms which have been assigned but whose status is checked out
	private LinkedList checkedOutAssignedDr;									//so as to update their status to Occupied when cleaning is finished
	private Graph wingPlan;														//using graph for storing the wing plan
	
	public HotelManagementSystem() {
		this.clientList = new Dictionary(); 								
		this.availableRoomListDr = new Stack(); 							
		this.occupiedRoomListDr = new LinkedList();							
		this.availableRoomListFr = new Stack();
		this.occupiedRoomListFr = new LinkedList();
		this.checkedOutAssignedDr = new LinkedList();
		this.checkedOutAssignedFr = new LinkedList();
		this.wingPlan = new Graph();										
		
	}
	
	@Override
	public int addClient(String name, String emailAddress) {
		Client client = new Client(name, emailAddress);							//creating object of new client
		this.clientList.add(client.getUniqueId(),(Comparable)client);			//adding the client to the client list based on the unique id being generated
		return client.getUniqueId();
		
	}
	
	@Override
	public void printClients() {
		System.out.println("Client Details");									//printing details of the clients also including assigned room details if any 
		System.out.println(this.clientList);
	}
	
	@Override
	public int addDoubleRoom(String wing, int roomNumber, String status) {
		DoubleRoom dr = new DoubleRoom(wing, roomNumber, status,2,1);			//Creating Object of double room - takes 2 people and 1 hour to clean
		if(status=="READY") {													//adding double room to the room stack depending on status
			this.availableRoomListDr.push(dr);
		}
		else if(status=="OCCUPIED") {
			this.occupiedRoomListDr.addFirst(dr);								//using add first of linked list to get O(1) while adding new rooms
		}
		else {
			Dictionary dict_sample = new Dictionary();							//add in priority queue
			dict_sample.add(wing, null);										//getting the node value for this wing so that room can be pushed
			Dictionary d = (Dictionary)this.wingPlan.findNodeValue(dict_sample);
			PriorityQueue rooms_in_wing = (PriorityQueue)d.find(wing);
			rooms_in_wing.push(dr, 2);											//priority of double rooms is 2
			
		}
		
		return dr.getUniqueId();												//generating unique id of the room
		
	}
	
	@Override
	public int addFamilyRoom(String wing, int roomNumber, String status) {
		FamilyRoom fr = new FamilyRoom(wing, roomNumber, status,4,2);			//Creating Object of family room takes 4 people and 2 hours to clean
		if(status=="READY") {													//adding family room to the room stack depending on status
			this.availableRoomListFr.push(fr);
		}
		else if(status=="OCCUPIED") {
			this.occupiedRoomListFr.addFirst(fr);
		}
		else {
			Dictionary dict_sample = new Dictionary();							//add in priority queue
			dict_sample.add(wing, null);
			Dictionary d = (Dictionary)this.wingPlan.findNodeValue(dict_sample);//getting the node value for this wing so that room can be pushed
			PriorityQueue rooms_in_wing = (PriorityQueue)d.find(wing);
			rooms_in_wing.push(fr, 1);											//priority of double rooms is 1
		}
		
		return fr.getUniqueId();
		
	}
	
	public void printRooms() {
		System.out.println("Room Details");										//printing details of all rooms - available, occupied, checked out
		System.out.println(this.availableRoomListDr);							//all rooms - double and family printed
		System.out.println(this.occupiedRoomListDr);
		System.out.println(this.availableRoomListFr);
		System.out.println(this.occupiedRoomListFr);
		System.out.println(this.wingPlan);
	}

	
	

	@Override
	public int checkInDoubleRoom(int client) {
		// TODO Auto-generated method stub
		Client c = (Client)this.clientList.find(client);							//checking in the linked list to see if client is available
		if(c==null) {																//check to handle incorrect client id
			System.out.println("Client does not exist");
			return -1;
		}
		else {
			if(!this.availableRoomListDr.empty()) {									//first checking if any available rooms are available
				Room r = (Room)this.availableRoomListDr.pop();						//allotting an available room
				r.setStatus("OCCUPIED");
				this.occupiedRoomListDr.addFirst(r);
				c.setRoom(r);
				return r.getUniqueId();
			}
			else {
				LinkedList minPathList = (LinkedList)this.wingPlan.findMinPath(); 
																					//checking in checked out rooms. to traverse all nodes using the minPath function
																					//to be able to traverse in minimum time
				for(int i = 0;i<minPathList.size();++i) {
					Dictionary d = (Dictionary)minPathList.get(i);    				//Dictionary key - wing and value - priority queue of rooms
					PriorityQueue pq = (PriorityQueue)d.getFirstValue(); 			//getting the first value in the dictionary as dictionary will only have one key value pair
					int j = pq.size()-1;											//since double rooms will be at the end parsing from back
					while(j>0 && (Room)pq.getValue(j) instanceof DoubleRoom) {		//looping through all checked out rooms to check which one is not yet assigned
						Room r = (Room)pq.getValue(j); 								
						if(r instanceof DoubleRoom && r.getStatus()!="OCCUPIED") {	//since it is a priority queue with only two values if there are
							System.out.println("Room "+r.getUniqueId()+" has been assigned but has to be cleaned");
							c.setRoom(r);
							r.setStatus("OCCUPIED");								//assigning that value to a client
							this.checkedOutAssignedDr.addFirst(r);					
							return r.getUniqueId();
						}
						j=j-1;
						
					}
				}
				System.out.println("No double rooms are available");
				return -1;															//no rooms in available and checked out list
			}
		}
	}

	@Override
	public int checkInFamilyRoom(int client) {
		// TODO Auto-generated method stub
		Client c = (Client)this.clientList.find(client);							//checking in the dictionary to see if client is available
		if(c==null) {																//check to handle incorrect client id	
			System.out.println("Client does not exist");
			return -1;
		}
		else {
			if(!this.availableRoomListFr.empty()) {									//first checking if any available rooms are available
				Room r = (Room)this.availableRoomListFr.pop();						//allotting an available room
				r.setStatus("OCCUPIED");
				this.occupiedRoomListFr.addFirst(r);
				c.setRoom(r);
				return r.getUniqueId();
			}
			else {
				LinkedList minPathList = (LinkedList)this.wingPlan.findMinPath(); 
																					//checking in checked out rooms. to traverse all nodes using the minPath function
																					//to be able to traverse in minimum time
				for(int i = 0;i<minPathList.size();++i) {
					Dictionary d = (Dictionary)minPathList.get(i);    				//Dictionary key - wing and value - priority queue of rooms
					PriorityQueue pq = (PriorityQueue)d.getFirstValue(); 			//getting the first value in the dictionary as dictionary will only have one key value pair
					int j = 0;														//looping from start as family rooms will be at the beginning
					while(j<pq.size() && (Room)pq.getValue(j) instanceof FamilyRoom) {
						Room r = (Room)pq.getValue(j); 
						if(r instanceof FamilyRoom && r.getStatus()!="OCCUPIED") {	
							System.out.println("Room "+r.getUniqueId()+" has been assigned but has to be cleaned");
							c.setRoom(r);
							r.setStatus("OCCUPIED");
							this.checkedOutAssignedFr.addFirst(r);					
							return r.getUniqueId();
						}
						j=j+1;
					}
				}
				System.out.println("No family rooms are available");
				return -1;															//no rooms in available and checked out list
			}
		}
		
	}

	@Override
	public boolean checkOutRoom(int client) {
		// TODO Auto-generated method stub
		Client c = (Client)this.clientList.find((client)); 
		Room r = (Room)c.getRoom();
		if(r==null) {																	//check if client has been assigned a room or not
			System.out.println("Client has not been assigned a room");					//handling exception in case check out is done on a client who does not have a room yet assigned
			return false;
		}
		else {
			String wing = r.getWing();			
			Dictionary d_sample = new Dictionary();										//creating a sample dictionary so that nodes of the graph can be compared
			d_sample.add(wing, null);
			Dictionary d = (Dictionary)this.wingPlan.findNodeValue(d_sample);				//finding the wing in the graph
			PriorityQueue rooms_in_wing = (PriorityQueue)d.find(wing);					//getting the queued rooms of that wing
			r.setStatus("CHECKED OUT");
			c.setRoom(null); 															//since client has checked out setting room associated to client as null
			if(r instanceof DoubleRoom) {
				this.occupiedRoomListDr.remove(r);										//removing the room from occupied list
				rooms_in_wing.push(r, 2);												//adding the room to the corresponding wing's priority queue for cleaning
			}
			else {
				this.occupiedRoomListFr.remove(r);
				rooms_in_wing.push(r, 1); 												//adding room to the queue
			}
			return true;
		}
	}

	@Override
	public Vector searchAvailableRooms() {
		// TODO Auto-generated method stub
		Vector availableRooms = new Vector(100); 										//need to specify number of elements in vector so hard coding a general number
		Stack s_dr = new Stack(); 														//creating new stack to avoid object referencing to the same memory
		s_dr = this.availableRoomListDr;
		while(!s_dr.empty()) {
			availableRooms.addLast((Comparable)s_dr.top());								//adding available rooms of type double in the vector
			s_dr.pop();
		}
		Stack s_fr = new Stack();
		s_fr = this.availableRoomListFr;
		while(!s_fr.empty()) {
			availableRooms.addLast((Comparable)s_fr.top());								//adding available rooms of type family in the vector
			s_fr.pop();
		}
		
		return availableRooms;
	}

	@Override
	public void printAvailableRooms() {
		// TODO Auto-generated method stub
		System.out.println(this.availableRoomListDr);									//printing the list of all available rooms
		System.out.println(this.availableRoomListFr);									//double and family
	}

	@Override
	public void printOccupiedRooms() {
		// TODO Auto-generated method stub
		System.out.println(this.occupiedRoomListDr);									//printing the list of occupied rooms
		System.out.println(this.occupiedRoomListFr);									//double and family
	}

	@Override
	public void addWing(String wingName) {
		// TODO Auto-generated method stub
		Dictionary d = new Dictionary();												//storing the nodes in a graph in the form of key value dictionaries with one element
		PriorityQueue q = new PriorityQueue();											//the key will be the wing name and their corresponding values will be priority queues for storing family rooms and double rooms	 
		d.add(wingName, q);
		this.wingPlan.addNode((Comparable)d);											//adding nodes in graph which will contain wings and rooms
	}

	@Override
	public void connectWings(String wing1, String wing2, double distance) {
		// TODO Auto-generated method stub
		Dictionary d_wing1 = new Dictionary();
		d_wing1.add(wing1,null);
		Dictionary d_wing2 = new Dictionary();
		d_wing2.add(wing2, null);
		this.wingPlan.addEdge(d_wing1, d_wing2, distance);								//adding edges with by connecting various wings with their weights
		this.wingPlan.addEdge(d_wing2, d_wing1, distance);								//considering unidirectional graphs only
		
	}

	@Override
	public void organizeCleaning() {
		// TODO Auto-generated method stub
		
		LinkedList minPathList = (LinkedList)this.wingPlan.findMinPath();				//getting the minimum cost path for traversal
		for(int i = 0;i<minPathList.size();++i) {
			Dictionary d = (Dictionary)minPathList.get(i);    							//Dictionary key - wing and value - priority queue of rooms
			PriorityQueue pq = (PriorityQueue)d.getFirstValue(); 						//getting the first value in the dictionary as dictionary will only
			while((Room)pq.getFirst() instanceof FamilyRoom) {      					//getting the first room to check if it is a family or double room
				Room room_checked_out_fr = (Room)pq.pop();
				if(this.checkedOutAssignedFr.contains(room_checked_out_fr)!=null) {		//checking if room has been assigned to a client
					this.checkedOutAssignedFr.remove(room_checked_out_fr);				//if it has setting its status to Occupied
					room_checked_out_fr.setStatus("OCCUPIED");
					this.occupiedRoomListFr.addFirst(room_checked_out_fr); 				//adding room to occupied list
				}
				else {
					this.availableRoomListFr.push(room_checked_out_fr);    				//performing first round of cleaning only for family rooms
					room_checked_out_fr.setStatus("READY");								//else pushing it to ready list rooms
				}
				
				
			}	
		}
		//performing the second round of cleaning for double rooms
		for(int i = 0;i<minPathList.size();++i) {
			Dictionary d = (Dictionary)minPathList.get(i);    							//Dictionary key - wing and value - priority queue of rooms
			PriorityQueue pq = (PriorityQueue)d.getFirstValue(); 						//getting the first value in the dictionary as dictionary will only
																						//contain one value
			while(!pq.isEmpty()) {                                  					//since all family rooms have been cleaned above only double rooms left
				Room room_checked_out_dr = (Room)pq.pop();
				if(this.checkedOutAssignedDr.contains(room_checked_out_dr)!=null) {		//checking if room has been assigned to a client
					this.checkedOutAssignedDr.remove(room_checked_out_dr);				//if it has setting its status to Occupied
					room_checked_out_dr.setStatus("OCCUPIED");
					this.occupiedRoomListDr.addFirst(room_checked_out_dr); 				//adding room to occupied list
				}
				else {
					this.availableRoomListDr.push(room_checked_out_dr);    				//performing first round of cleaning only for family rooms
					room_checked_out_dr.setStatus("READY");								//else pushing it to ready list rooms
				}    
			}	
		}
	}
	
}
