public class Graph
{
    public class Node implements Comparable
    {
        private Comparable info;
        private LinkedList edges;
        private boolean isVisited;
        
        public Node(Comparable label)
        {
            info = label;
            edges = new LinkedList();
        }
        
        public void addEdge(Edge e)
        {
            edges.addFirst(e);
        }
        
        public int compareTo(Object o)
        {
            // two nodes are equal if they have the same label
            Node n = (Node)o;
            return n.info.compareTo(info);
        }
        
        public Comparable getLabel()
        {
            return info;
        }
        
        public String toString() {
        	if(this.edges.size()>0) {
        		return "\n Node - "+this.info + this.edges.toString()+"\n";
        	}
        	else {
        		return "Node - "+this.info;
        	}
        	
        }
        
        public LinkedList getEdges() {
        	return edges;
        }
        
        public void setVisited(boolean visit) {
        	this.isVisited = visit;
        }
        
    }
    
    private class Edge implements Comparable
    {
        private Node toNode;
        private Comparable weight;
        
        public Edge(Node to,Comparable w)
        {
            toNode = to;
            weight = w;
        }
        
        public int compareTo(Object o)
        {
            // two edges are equal if they point
            // to the same node.
            // this assumes that the edges are
            // starting from the same node !!!
            Edge n = (Edge)o;
            return n.toNode.compareTo(toNode);
        }
        
        public String toString() {
        	return this.toNode.getLabel()+" Weight - "+this.weight.toString() + " | " ;
        }
        
        public Comparable getWeight() {
        	return this.weight;
        }
        
        
    }
    
    private LinkedList nodes;
    
    public Graph()
    {
        nodes = new LinkedList();
    }
    
    public void addNode(Comparable label)
    {
        
    	nodes.addFirst(new Node(label));
    }
    
    private Node findNode(Comparable nodeLabel)
    {
        Node res = null;
        Node n1 = new Node(nodeLabel);
        for (int i=0; i<nodes.size(); i++)
        {
            Node n = (Node)nodes.get(i);
            if(n.compareTo(n1)==0)
            {
                res = n;
                break;
            }
        }
        return res;
    }
    
    public Comparable findNodeValue(Comparable nodeLabel) {
    	return this.findNode(nodeLabel).getLabel();
    }
    
    public void addEdge(Comparable nodeLabel1,Comparable nodeLabel2, Comparable w)
    {
        Node n1 = findNode(nodeLabel1);
        
        Node n2 = findNode(nodeLabel2);
        
        n1.addEdge(new Edge(n2,w));
    }
    
    public void print() {
    	String str = "";
    	String str_edges = "";
    	for (int i=0;i<this.nodes.size();++i) {
    		Node n = (Node)this.nodes.get(i);
    		str = "Node " + " "+ n.getLabel();
    		str_edges = "Edge "+ n.getEdges();
    		System.out.println("\n");
    		System.out.println(str);
        	System.out.println(str_edges);
    	}
    	
    	
    }
    
    public  boolean  findPath(Comparable  nodeLabel1 ,Comparable  nodeLabel2){
    	Node  startState = findNode(nodeLabel1 );
    	Node  endState = findNode(nodeLabel2 );
    	Stack  toDoList = new Stack();
    	toDoList.push(startState );
    	while (! toDoList.empty ()){
    		Node  current = (Node)toDoList.pop ();
    		if (current  ==  endState) 
    			return  true;
    		else{
    			for (int i=0; i<current.edges.size (); i++){
    				Edge e = (Edge)current.edges.get(i);
    				toDoList.push(e.toNode );
    				}
    			}
    		}
    	return  false;
    }
    
    
    
    public void printPaths(Comparable  nodeLabel1 ,Comparable  nodeLabel2) {
    	Node  startState = findNode(nodeLabel1 );
    	Node  endState = findNode(nodeLabel2 );
    	Stack s = new Stack();
    	s.push(startState.info);
    	for(int i=0;i<startState.edges.size();++i) {
    		Edge e = (Edge)(startState.edges.get(i));
    		Node n = e.toNode;
    		if(findPath(n.info,endState.info)) {
    			s.push(n.info);
    		}
    	}
    	System.out.println(s);
    	
    	
    }
    
    public int countOfNodes() {
    	return this.nodes.size();
    }
    
    //to get the shortest path computing the the next node with the smallest path cost
    
    public Object getShortestPath_new(Node startNode) {
    	//taking a temporary vector for storing the weight and the corresponding smallest path to be returned to the
    	//calling function
    	Vector v = new Vector(2);
    	startNode.setVisited(true);
    	Stack s = new Stack();
    	LinkedList q = new LinkedList();
    	//q.push(startNode);
    	Double cost = 0.0;
    	s.push(startNode);
    	while(!s.empty()) {
    		Node topNode = (Node)s.pop();
    		topNode.setVisited(true);
    		q.addFirst(topNode.getLabel());
    		LinkedList edgesList = topNode.getEdges();
    		Edge nextEdge  = (Edge)edgesList.getFirst();
    		Double minWeight = 9999999999.99999999;
    		for(int i =0;i<edgesList.size();++i) {   
    			Edge next = (Edge)edgesList.get(i);
    			if(next.toNode.isVisited==false && (Double)next.getWeight()<=minWeight ) {
    				nextEdge = next;
    				minWeight = (Double)next.getWeight();
    			}
    			
    		}
    		
    		if(nextEdge.toNode.isVisited==false) {
    			cost = cost + minWeight;
    			s.push(nextEdge.toNode);
        		
    		}
    		
    	}
    	v.addFirst(cost);
    	v.addLast(q);
    	//set all nodes visited to false for the next iteration
    	for(int i=0;i<this.nodes.size();++i) {
    		//System.out.println(nodes_set.get(i));
    		Node n = (Node)this.nodes.get(i);
    		n.setVisited(false);
    	}
    	
    	//System.out.println(q);
    	
    	return v;
    	
    }
    
    //Finding the minimum value from a vector
    public Comparable calculateMinWeight(Vector v) {
    	Comparable min = (Comparable)v.getFirst();
    	for(int i=1;i<v.size();++i) {
    		if(min.compareTo(v.get(i))>0) {
    			min = v.get(i);
    		}
    	}
    	return min;
    }
    
    
    //computing shortest path from all nodes to identify which path should be taken
    public Comparable findMinPath() {
    	LinkedList nodes_set = this.nodes;
    	Vector minPathsList = new Vector(this.nodes.size());
    	Vector minWeightList = new Vector(this.nodes.size());
    	for(int i=0;i<nodes_set.size();++i) {
    		//storing the minimum possible path starting from every node
    		Vector v = (Vector)this.getShortestPath_new((Node)nodes_set.get(i));
    		minPathsList.addLast(v.get(1));
    		minWeightList.addLast(v.get(0));
    		
    	}
    	//Computing the minimum weight from the list of all possible shortest paths computed above
    	Comparable minWeight = this.calculateMinWeight(minWeightList);
    	//smallest possible path
    	return minPathsList.get(minWeightList.getIndex(minWeight));
    	
    }
    
    public String toString() {
    	String str = "";
    	for(int i = 0;i<this.nodes.size();++i) {
    		Node n = (Node)this.nodes.get(i);
    		str = str + n.info.toString();
    	}
    	return str;
    	
    }
   
}