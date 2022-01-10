public class LinkedList implements Comparable {
	
	private class ListElement implements Comparable{
		private Comparable el1;
		private ListElement el2;

		public ListElement(Comparable el, ListElement nextElement) {
			el1 = el;
			el2 = nextElement;
		}

		public ListElement(Comparable el) {
			this(el, null);
		}

		public Comparable first() {
			return el1;
		}

		public ListElement rest() {
			return el2;
		}

		public void setFirst(Comparable value) {
			el1 = value;
		}

		public void setRest(ListElement value) {
			el2 = value;
		}
		
		public int compareTo(Object o) {
			ListElement le = (ListElement)o;
			return this.el1.compareTo(le.first());
		}
	}
	
	private ListElement head;

	public LinkedList() {
		head = null;
	}

	public void addFirst(Comparable o) {
		head = new ListElement(o, head);
	}

	public Object getFirst() {
		return head.first();
	}

	public Object get(int n) {
		ListElement d = head;
		while (n > 0) {
			d = d.rest();
			n--;
		}
		return d.first();
	}
	
	public String toString() {
		String s = "";
		ListElement d = head;
		while (d != null) {
			s += d.first().toString();
			s += " \n";
			d = d.rest();
		}
		s += "";
		return s;
	}
	
	public int size() {
		int count = 0;
		ListElement d = head;
		while(d!=null) {
			d = d.rest();
			count++;
		}
		return count;
	}
	
	public void set(int n, Comparable o) {
		ListElement d = head;
		for(int i = 0; i<=(n-1); ++i) {
			
			if(i==n-1) {
				d.setFirst(o);
				break;
			}
			else {
				d = d.rest();
			}
		}
		
	}
	
	public Object getLast() {
		ListElement d = head;
		while(d.rest()!=null) {
			d=d.rest();
		}
		return d.first();
	}
	
	public void addLast(Comparable o) {
		ListElement d = head;
		while(d.rest()!=null) {
			d = d.rest();
		}
		ListElement newListElement = new ListElement(o,null);
		d.setRest(newListElement);
	}

	public Object contains(Comparable o) {
		ListElement d = head;
		while(d!=null) {
			if(d.first().compareTo(o)==0) {
				return d.first();
			}
			d= d.rest();
		}
		return null;
	}
	
	public boolean isEmpty() {
		ListElement d = head;
		if(d==null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void removeLast() {
		ListElement d = head;
		if(d.rest()==null) {
			head=null;
		}
		else if(d!=null && d.first()!=null) {
			while(d.rest().rest()!=null) {
				d = d.rest();
				
			}
			d.setRest(null);
		}
		
	}
	
	public void removeFirst() {
		ListElement d = head;
		head = d.rest();
	}
	
	public void fropple() {
		ListElement d = head;
		while(d.rest()!=null) {
			Object temp = new Object();
			temp = (Comparable)d.rest().first();
			d.rest().setFirst((Comparable)d.first());
			d.setFirst((Comparable)temp);
			d = d.rest().rest();
		}
		
		
	}
	
	public void appendList(LinkedList l2) {
		ListElement d = head;
		while(d.rest()!=null) {
			d = d.rest();
		}
		ListElement d2 = l2.head;
		d.setRest(d2);
	}
	
	public  void  addSorted(Comparable o){
		// an  empty list , add  element  in front
		if(head == null) {
			head = new  ListElement(o,null);
		}
		else if(head.first (). compareTo(o) > 0){
			// we have to add  the  element  in front
			head = new  ListElement(o,head);}
		else{
			// we have to find  the  first  element  which  is  bigger
			ListElement d = head;
			while((d.rest() != null )&&(d.rest (). first (). compareTo(o) < 0)){
				d = d.rest ();
			}
			ListElement  next = d.rest ();
			d.setRest(new  ListElement(o,next ));
			}
		//count ++;
		
		
	}

	public void remove(Comparable o) {
		ListElement ll = new ListElement(o);
		ListElement d = head;
		if(d.compareTo(ll)==0) {
			this.removeFirst();
		}
		else {
			this.removeElement(head, d.rest(),o);
		}
	}
	
	private void removeElement(ListElement previousElement,ListElement currentElement,Comparable o) {
		ListElement ll = new ListElement(o);
		if(currentElement!=null) {
			if(currentElement.compareTo(ll)==0) {
				previousElement.setRest(currentElement.rest());
			}
			else {
				this.removeElement(currentElement,currentElement.rest(), o);
			}
		}
		
		
	}
	
	public int compareTo(Object o) {
		ListElement d = head;
		while(d.rest()!=null) {
			d = d.rest();
			return d.compareTo(o);
		}
		return -1;
	}
}
