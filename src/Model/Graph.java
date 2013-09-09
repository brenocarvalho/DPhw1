package Model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collection;
import java.util.HashSet;

public class Graph {
	private Collection<Node> nodes, orphanNodes;
	private int numEdges;
	
	public Graph(){
		nodes = new LinkedList<Node>();
		orphanNodes = new LinkedList<Node>();
		numEdges = 0;
	}

	//TODO complete this method in order to be able to make a schedule without destroying the graph
	/*
	public Graph duplicateGraph(){
		Graph out = new Graph();
		for(Node node: nodes){
			out.nodes.add(null)
		}
		
		return out;
	}
	*/
	
	public int getNumEdges(){
		return numEdges;
	}
	
	public synchronized void addNode(Node node){
		nodes.add(node);
		if(node.getNumParents() == 0){
			orphanNodes.add(node);
		}
	}
	
	public synchronized void removeNode(Node node){
		Node child;
		Iterator<Node> childIt = node.childrenIterator();
		while(childIt.hasNext()){
			child = childIt.next();
		//for(Node child: node.childrenIterator()){
			child.removeParent(node);
			if(child.getNumParents() == 0 && !this.orphanNodes.contains(child)) this.addOrphan(child);
			//System.out.println("NEW ORPHAN!"+child.getTaskName());
			childIt = node.childrenIterator();
		}
		//TODO should destroy all the edges of excluded node
		nodes.remove(node);
		orphanNodes.remove(node);
	}
	
	public int getNumNodes(){
		return nodes.size();
	}
	
	public boolean contains(Node node){
		return (nodes.contains(node));
	}

	protected synchronized void addOrphan(Node node) {
		orphanNodes.add(node);
	}
	
	protected synchronized void removeOrphan(Node node) {
		orphanNodes.remove(node);
	}

	public synchronized boolean hasOrphan() {
		return !orphanNodes.isEmpty();
	}
	
	public synchronized Node getOrphan(){
		return orphanNodes.iterator().next();
	}
	
	public synchronized Node popOrphan(){
		Node out = getOrphan();
		//this.removeNode(out);
		this.orphanNodes.remove(out);
		return out;
	}

	public String toString(){
		String out = "";
		for(Node n: nodes){
			out += n.toString() + "\n";
		}
		return out;
	}
}
