
public class Queue implements Comparable  {
	
	private LinkedList data;
	
	public Queue() {
		this.data = new LinkedList();
	}

	public void push(Comparable o) {
		this.data.addFirst(o);
	}

	public Object pop() {
		Object o = this.data.getLast();
		
		this.data.removeLast();
		return o;
	}

	public Object top() {
		return this.data.getLast();
	}

	public int size() {
		return this.data.size();
	}

	public boolean empty() {
		return this.data.isEmpty();
	}
	
	public String toString() {
		return this.data.toString();
	}
	
	public int compareTo(Object o) {
		return this.data.compareTo(o);
	}
	
}
