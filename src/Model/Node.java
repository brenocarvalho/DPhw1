package Model;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
//TODO make impossible to use a node from another graph as a parent
public class Node implements Comparable{
	private Task task;
	private Graph graph;
	private Set<Node> parents, children;
	
	public Task getTask() {
		return task;
	}
	
	public void setTask(Task task) throws Exception{
		if(task == null) throw new Exception("Invalid task");
		this.task = task;
	}
	
	public String getTaskName() {
		return task.getName();
	}
	
	public Set<Node> getParents() {
		return parents;
	}
	
	public void addParent(Node p) throws Exception{
		if(p == null) throw new Exception("Invalid node");
		if(!graph.contains(p)) throw new Exception("Node belongs to another graph.");
		parents.add(p);
		p.children.add(this);
		graph.removeOrphan(this);
	}
	
	public void removeParent(Node p){
		parents.remove(p);
		p.children.remove(this);
		if(this.getNumParents() == 0) graph.addOrphan(this);
	}
	
	public int getNumParents() {
		return parents.size();
	}
	
	public int getNumChildren() {
		return children.size();
	}
	
	public Graph getGraph(){
		return graph;
	}
	
	public Node(Task task, Graph graph, Set<Node> parents, Set<Node> children) throws Exception{
		this.setTask(task);
		this.parents = parents;
		this.children = children;
		this.graph = graph;
		this.graph.addNode(this);
	}
	
	public Node(Task task, Set<Node> parents) throws Exception{
		this(task, new Graph(), parents, new TreeSet<Node>());
	}

	public Node(Task task, Graph graph) throws Exception{
		this(task, graph, new TreeSet<Node>(), new TreeSet<Node>());
	}
	
	public Node(Task task) throws Exception{
		this(task, new Graph(), new TreeSet<Node>(), new TreeSet<Node>());
	}
	
	public Status runTask() throws Exception{
		return task.run();
	}
	
	public String toString(){
		Iterator<Node> parentsI = parents.iterator();
		String parentsNames = "";
		if (parentsI.hasNext()){
			parentsNames = parentsI.next().getTaskName();
		}
		while(parentsI.hasNext()){
			parentsNames += ", "+parentsI.next().getTaskName();
		}
		
		return String.format("Node<%s | %s>", task.getName(), parentsNames);
	}

	public Iterator<Node> childrenIterator() {
		return children.iterator();
	}

	@Override
	public int compareTo(Object n) {
		Node node = (Node) n;
		return this.getNumParents() - node.getNumParents();
	}
	
}