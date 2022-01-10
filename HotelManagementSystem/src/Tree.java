

public class Tree {
	/*
	private class NaturalComparator implements Comparator
	{
		public int compare(Object a, Object b)
		{
			return ((Comparable)a).compareTo(b);
		}
	}
	*/
	// the class for implementing a node in the tree.
	// contains a value, a pointer to the left child and a pointer to the right child
	
	public class TreeNode implements Comparable
	{
	 protected Comparable value;
	 protected TreeNode leftNode;
	 protected TreeNode rightNode;
	 public TreeNode(Comparable v)
	 {
	  value = v;
	  leftNode = null;
	  rightNode = null;
	 }
	  
	 public TreeNode(Comparable v, TreeNode left, TreeNode right)
	 {
	  value = v;
	  leftNode = left;
	  rightNode = right;
	 }
	 public TreeNode getLeftTree()
	 {
	  return leftNode;
	 }
	 
	 public TreeNode getRightTree()
	 {
	  return rightNode;
	 }
	 
	 
	 public Comparable getValue()
	 {
		 return value;
	 }

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	 

	}
	
	public abstract class TreeAction
	{
		public abstract void run(Tree.TreeNode n);
	}
		
	public class TreePrinter extends TreeAction
	{
		public void run(TreeNode n) {
			System.out.println(n.value);
		}
	}
	// start of the actual tree class
	
	// the root of our tree
	protected TreeNode root;
	
	public Tree()
	{
		root = null;
	}
	
	public Comparable getRootValue() {
		return this.root.value;
	}
	
	public void traverse(TreeAction action)
	{
		//QueueVector t = new QueueVector();
		Stack t = new Stack();
		t.push(root);
		while(!t.empty())
		{
			TreeNode n = (TreeNode)t.pop();
			action.run(n);
			 
			if(n.getLeftTree() != null) t.push(n.getLeftTree());
			if(n.getRightTree() != null) t.push(n.getRightTree());
		}
	}
	
	public void traverseNode(TreeNode n,TreeAction action)
	{
		if(n != null)
		{
			if(n.getLeftTree() != null) traverseNode(n.getLeftTree(),action); 
			action.run(n);
			if(n.getRightTree() != null) traverseNode(n.getRightTree(),action);
		}
	}
	
	public void traverseInOrder(TreeAction action)
	{
		traverseNode(root,action);
	} 
	
	public void print()
	{
		this.traverse(new TreeAction() {
			public void run(TreeNode n) {
				System.out.println(n.value);
			}
		});
	}
		
	public void print_queue() 
	{
		Queue t = new Queue();
		t.push(root);
		while (!t.empty ())
		{
			//System.out.println(t);
			TreeNode n2 = (TreeNode)t.pop();
			System.out.println(n2.getValue() );
			if(n2.getRightTree () != null)
				t.push(n2.getRightTree ());
				
			if(n2.getLeftTree () != null)
				t.push(n2.getLeftTree ());
		}
	}

	
	public void insert(Comparable element)
	{
		insertAtNode(element,root,null);
	}	
	
	public Comparable find(Comparable element) {
		return findAtNode(element, root);
	}
	
	// we traverse the tree.
	// Current holds the pointer to the TreeNode we are currently checking
	// Parent holds the pointer to the parent of the current TreeNode
	private void insertAtNode(Comparable element,TreeNode current,TreeNode parent)
	{
		// if the node we check is empty
		if(current == null)
		{
			TreeNode newNode = new TreeNode(element);
			// the current node is empty, but we have a parent
			if(parent != null)
			{
				// do we add it to the left?
				if(element.compareTo(parent.value) < 0)
				{
					parent.leftNode = newNode;
				}
				// or do we add it to the right?
				else
				{
					parent.rightNode = newNode;
				}
			}
			// the current node is empty and it has no parent, we actually have an empty tree
			else root = newNode;
		}
		else if(element.compareTo(current.value) == 0)
		{
			//update the value of an already existing key
			current.value = element;
		}
		// if the element is smaller than current, go left
		else if(element.compareTo(current.value) < 0)
		{
			insertAtNode(element,current.getLeftTree(),current);
		}
		// if the element is bigger than current, go right
		else insertAtNode(element,current.getRightTree(),current);
	}
	
	
	
	private Comparable findAtNode(Comparable element, TreeNode current) {
		// if the node we check is empty
		if(current == null){
			return null;
		}
		else if(element.compareTo(current.value) == 0)
		{
				return current.value;
		}
				// if the element is smaller than current, go left
		else if(element.compareTo(current.value) < 0)
		{
			return findAtNode(element,current.getLeftTree());
		}
				// if the element is bigger than current, go right
		else return findAtNode(element,current.getRightTree());
	}
	
	public int getTreeDepth() {
		int level = getTreeLevel(root);
		return level;
	}
	
	public int getTreeLevel(TreeNode current) {
		//For empty tree height is zero
		if(current==null) {
			return 0;
		}
		//When tree has some height
		else {
			//When leaf nodes are reached we return 1 height
			if(current.getLeftTree()==null && current.getRightTree()==null) {
				return 1;
			}
			//We find the left and right height of subtrees and compare them and set height of whichever is larger as that will be the dept of the tree
			else {
				int leftLevel = 1+getTreeLevel(current.getLeftTree());
				int rightLevel = 1+getTreeLevel(current.getRightTree());
				int height;
				if(leftLevel>rightLevel) {
					height = leftLevel;
				}
				else{
					height = rightLevel;
				};
				return height+1;
				
			}
		}
		
		
		
	}

	public Object getBiggestELement() {
		Object o = getMaxElement(root);
		return o;
	}
	
	public Object getMaxElement(TreeNode current) {
		//empty tree returns null
		if(current==null) {
			return null;
		}
		
		else {
			//reaching the leaf node which will be the largest
			if(current.getRightTree()==null) {
				return current.getValue();
			}
			//traversing only the right side of the tree as all large elements will be on the right
			else {
				current = current.getRightTree();
				return getMaxElement(current);
				//return current.value;
			}
		}
		
		
	}
	
	public void swapTreeParent() {
		swapTree(root);
	}

	public void swapTree(TreeNode current) {
		if(current == null) {
			//tree is empty
			//do nothing;
		}
		
		else {
			TreeNode temp = current.getLeftTree();
			current.leftNode = current.getRightTree();
			current.rightNode = temp;
			if(current.getLeftTree()!=null) {
				swapTree(current.getLeftTree());
			}
			if(current.getRightTree()!=null) {
				swapTree(current.getRightTree());
			}
			
		}
			
		
	}
	
	//median of a well sorted balance Tree
	public Object getMedian() {
		return root.getValue();
	}
	
	public Object getSmallestElement() {
		Object o = getMinElement(root);
		return o;
	}
	
	//finding minimum
	public Object getMinElement(TreeNode current) {
		//empty tree returns null
		if(current==null) {
			return null;
		}
		
		else {
			//reaching the leaf node which will be the smallest
			if(current.getLeftTree()==null) {
				return current.getValue();
			}
			//traversing only the left side of the tree as all small elements will be on the left
			else {
				current = current.getLeftTree();
				return getMinElement(current);
				//return current.value;
			}
		}
		
		
	}

	
	
	public String toString() {
		final LinkedList str = new LinkedList();
		this.traverse(new TreeAction() {
			public void run(TreeNode n) {
				 str.addFirst(n.getValue().toString());
			}
		});
		return str.toString();
	}
//	public int findAverage() {
//		Integer sum = 0;
//		this.traverse(new TreeAction() {
//			public void run(TreeNode n) {
//				sum = sum + (int)(n.getValue());
//			}
//
//			
//		});
//		return 0;
//	}
	
	

}

