package Model;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Graph {
	private Set<Node> nodes, orphanNodes;
	private int numEdges;
	
	public Graph(){
		nodes = new TreeSet<Node>();
		numEdges = 0;
	}
	
	public int getNumEdges(){
		return numEdges;
	}
	
	public void addNode(Node node){
		nodes.add(node);
		if(node.getNumParents() == 0){
			orphanNodes.add(node);
		}
	}
	
	public void removeNode(Node node){
		nodes.remove(node);
		orphanNodes.remove(node);
		Node child;
		Iterator<Node> childIt = node.childrenIterator();
		while(childIt.hasNext()){
			child = childIt.next();
		//for(Node child: node.childrenIterator()){
			child.removeParent(node);
			if(child.getNumParents() == 0) this.addOrphan(child);
		}
		if(node.getNumParents() == 0){
			orphanNodes.remove(node);
		}
	}
	
	public int getNumNodes(){
		return nodes.size();
	}
	
	public Iterator<Node> orphanNodesIterator(){
		//TODO implement this iterator
		return null;
	}
	
	public boolean contains(Node node){
		return (nodes.contains(node));
	}

	protected void addOrphan(Node node) {
		orphanNodes.add(node);
	}
	
	protected void removeOrphan(Node node) {
		orphanNodes.remove(node);
	}
}
