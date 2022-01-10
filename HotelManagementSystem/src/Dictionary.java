public class Dictionary implements Comparable{

	private  class DictionaryPair implements Comparable {
		private Comparable key;
		private Comparable value;

		public DictionaryPair(Comparable key, Comparable value) {
			this.key = key;
			this.value = value;
		}

		public Object getKey() {
			return this.key;
		}

		public void setKey(Comparable key) {
			this.key = key;
		}

		public Comparable getValue() {
			return this.value;
		}

		public void setValue(Comparable value) {
			this.value = value;
		}

		public int compareTo(Object o) {
			DictionaryPair dp = (DictionaryPair)o;
			return this.key.compareTo(dp.getKey());
		}

		
		
		public String toString() {
			String str = this.value.toString();
			return str;
		}

	}

	private Tree data;

	public Dictionary() {
		this.data = new Tree();
	}

	public void add(Comparable key, Comparable value) {
		
		DictionaryPair dp = new DictionaryPair(key,value);
		this.data.insert(dp);
		
	}

	public Object findPosition(Comparable key) {
		DictionaryPair dp = new DictionaryPair(key,null);
		DictionaryPair dp_find = (DictionaryPair)this.data.find(dp);
		if(dp_find==null) {
			return "Key not found";
		}
		return dp_find.getValue();
	}
	
	
	
	public Object find(Comparable key) {
		DictionaryPair dp = new DictionaryPair(key,null);
		DictionaryPair dp_find = (DictionaryPair)this.data.find(dp);
		return dp_find.getValue();
	}

	public void print() {
		this.data.print();
	}

	public int compareTo(Object o) {
		if(this.data.find((Comparable)o)!=null) {
			return 0;
		}
		else {
			return -1;
		}
		
	}
	
	public String toString() {
		return this.data.toString();
	}
	
	public Comparable getFirstValue() {
		DictionaryPair dp = (DictionaryPair)this.data.getRootValue();
		return dp.getValue();
	}
	
}
