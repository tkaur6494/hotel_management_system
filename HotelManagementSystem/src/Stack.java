
public class Stack implements Comparable {
	
	private LinkedList data;

	public Stack() {
		data = new LinkedList();
	}

	public void push(Comparable o) {
		data.addFirst(o);
	}

	public Object pop() {
		Object o = data.getFirst();
		data.removeFirst();
		return o;
	}

	public Object top() {
		return data.getFirst();
	}

	public int size() {
		return data.size();
	}

	public boolean empty() {
		return data.isEmpty();
	}
	
	public String toString() {
		return data.toString();
	}
	
	public int compareTo(Object o) {
		return this.data.compareTo(o);
	}
}
