//main class for implementing the methods in HotelManagementSystem
public class Hotel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HotelManagementSystem im = new HotelManagementSystem();
		im.addWing("A");
		im.addWing("B");
		im.addWing("C");
		im.addWing("D");
		//wing plan
		im.connectWings("A", "B", 10);
		im.connectWings("A", "C", 5);
		im.connectWings("B", "C", 30);
		im.connectWings("D", "A", 2);
		im.connectWings("D", "B", 12);
		im.connectWings("D", "C", 20);
		
		im.addClient("Abc", "abc@abc.com");
		im.addClient("Def", "def@abc.com");
		im.addClient("Ghi", "ghi@abc.com");
		im.addClient("Jkl", "jkl@abc.com");
		im.addClient("Lmn", "lmn@abc.com");
		System.out.println("Adding clients");
		im.printClients();
		im.addFamilyRoom("A", 101, "READY");
		im.addFamilyRoom("A", 140, "READY");
		im.addDoubleRoom("C", 105, "READY");
		im.addDoubleRoom("C", 115, "READY");
		im.addFamilyRoom("C", 121, "READY");
		im.addFamilyRoom("B", 107, "READY");
		im.addDoubleRoom("A", 109, "READY");
		im.addDoubleRoom("D", 120, "READY");
		im.addFamilyRoom("D", 117, "READY");
		System.out.println("Adding rooms");
		im.printRooms();
		System.out.println("Performing check in");
		System.out.println(im.checkInFamilyRoom(3));
		System.out.println(im.checkInFamilyRoom(1));
		System.out.println(im.checkInDoubleRoom(2));
		System.out.println(im.checkInDoubleRoom(4));
		System.out.println(im.checkInDoubleRoom(5));
		System.out.println("Status of rooms");
		im.printRooms();
		im.addClient("Opq", "opq@abc.com");
		System.out.println(im.checkInDoubleRoom(6));
		System.out.println("Status of clients");
		im.printClients();
		System.out.println("Performing checkout");
		im.checkOutRoom(1);
		im.checkOutRoom(2);
		im.checkOutRoom(3);
		im.addClient("Rst", "rst@abc.com");
		im.printRooms();
		System.out.println("Perfroming check in on a checked out room");
		im.checkInDoubleRoom(7);
		im.checkInFamilyRoom(2);
		System.out.println("After Wing cleaning");
		im.organizeCleaning();
		im.printRooms();
		System.out.println("Checking status after cleaning has been performed");
		im.printClients();

	}

}
