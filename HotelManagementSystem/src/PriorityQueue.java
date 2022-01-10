//A special case which will have only two priorities
public class PriorityQueue implements Comparable{
	private  class  PriorityPair  implements  Comparable{
		public  Object  element; 
		public  Comparable  priority;
		
		public  PriorityPair(Object  element , Comparable  priority){
			this.element = element;
			this.priority = priority;
		}
		public  int  compareTo(Object o){
			PriorityPair  p2 = (PriorityPair)o;
			return  (( Comparable)priority ). compareTo(p2.priority );
		}
		
		public Comparable getPriority() {
			return this.priority;
		}
		
		public String toString() {
			return element.toString();
		}
		
		public Object getElement(){
			return this.element;
		}
	}
	
	private  LinkedList  data;
	
	public  PriorityQueue (){
		data = new  LinkedList ();
	}
	
	public  void  push(Object o, Object  priority){
		// make a pair of o and  priority
		// add  this  pair to the  sorted  linked  list.
		//handling empty priority queue  
		PriorityPair p = new PriorityPair(o,(Comparable)priority);
		this.data.addSorted(p);
		
	}
	
	public  Object  pop(){
		if(!this.data.isEmpty()) {
			PriorityPair o = (PriorityPair)this.data.getFirst();
			this.data.removeFirst();
			return o.getElement();
		}
		else {
			return null;
		}
		
	}
	
	public String toString() {
		return this.data.toString();
	}
	
	public boolean isEmpty() {
		if(this.data.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Object getFirst() {
		if(this.data.size()>=1) {
			PriorityPair firstPair = (PriorityPair)this.data.getFirst();
			return firstPair.getElement();
		}
		else {
			return null;
		}
		
	}
	
	public  int  compareTo(Object o){
		return 0;
	}
	
	public Object getLast() {
		if(this.data.size()>=1) {
			PriorityPair lastPair = (PriorityPair)this.data.getLast();
			return lastPair.getElement();
		}
		else {
			return null;
		}
	}
	
	public Object getValue(int index) {
		PriorityPair p = (PriorityPair)this.data.get(index);
		return p.getElement();
	}
	
	public int size() {
		return this.data.size();
	}

}

