public class Vector 
{
	private Comparable data[];
	private int count;
	
	
	public Vector(int capacity)
	{
		data = new Comparable[capacity];
		count = 0;
	}

	public int size()
	{
		return this.count;
	}
 
	public boolean isEmpty()
	{
		return size() == 0;
	}

	public Comparable get(int index)
	{
		return this.data[index];
	}

	public void set(int index, Comparable obj)
	{
		this.data[index] = obj;
	}

	public boolean contains(Comparable obj)
	{
		for(int i=0;i<this.count;i++)
		{
			if(this.data[i].compareTo(obj)==0) return true;
		}
		return false;
	}
	
	public int getIndex(Comparable obj) {
			for(int i=0;i<this.count;++i) {
				if(this.data[i].compareTo(obj)==0) return i;
			}

		return -1;
		
	}
	
	public void addFirst(Comparable item)
	{
		for(int i = this.count; i>=0; i--) {
			this.data[i+1] = this.data[i];
		}
		this.data[0] = item;
		this.count++;
	}

	public void addLast(Comparable o)
	{
		if(this.data.length == this.count) {
			this.extendCapacity();
			this.data[this.count] = o;
			this.count++;
		}
		else {
			this.data[this.count] = o;
			this.count++;
		}
		
	}
	
	/*
	public boolean binarySearch(Object key)
	{
	int start = 0;
	int end = count - 1;
	while(start <= end)
	{
		int middle = (start + end + 1) / 2;
		if(key < data[middle]) end = middle -1;
		else if(key > data[middle]) start = middle + 1;
		else return true;
	}
	return false;
	}
	*/

	public Object getFirst()
	{
		return this.data[0];
	}

	public Object getLast()
	{
		return this.data[this.count-1];
	}

	public void removeLast()
	{
		this.count--;
	} 

	public void removeFirst()
	{
		for(int i = 0;i<this.count;++i) {
			this.data[i] = this.data[i+1];
		}
		this.count--;
	}
	
	public String toString() {
		String str = "";
		for(int i =0;i<this.count;++i) {
			str = str + " " + this.data[i]+"\n";
		}
		return str;
	}
	
	public void reverse() {
		int start = 0;
		int end = this.count-1;
		while(start<end) {
			Comparable temp; 
			temp = this.data[start];
			this.data[start] = this.data[end];
			this.data[end] = temp;
			start++;
			end--;
		}
	}
	
	public Vector repeat() {
		Vector newList = new Vector(2*(this.count));
		for(int i = 0; i<this.count; ++i) {
			newList.addLast(this.data[i]);
			newList.addLast(this.data[i]);
		}
		return newList;
	}
	
	public Vector interleave(Vector v) {
		//doubling the size of the new vector since it will have values from two vectors of same length
		Vector interleave = new Vector(2*(this.count));
		for(int i = 0; i<this.count; ++i) {
			interleave.addLast(this.data[i]);
			interleave.addLast(v.get(i));
		}
		return interleave;
	}
	
	public Vector interleaveDiff(Vector v) {
		int newLength = this.count + v.size();
		Vector interleave = new Vector(newLength);
		for(int i = 0; i<newLength; ++i) {
			//adding conditions to avoid array out of bounds exception
			if(i<this.count) {
				interleave.addLast(this.data[i]);
			}
			if(i<v.size()) {
				interleave.addLast(v.get(i));
			}
			
		}
		return interleave;
	}
	
	private void extendCapacity() {
		Comparable data2[] = new Comparable[2*this.count];
		for(int i = 0; i< this.count; ++i) {
			data2[i] = this.data[i];
		}
		this.data = data2;
	}
	
	

	
	
}