package Model;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Node {
	private Task task;
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
	
	public void addParent(Node p){
		parents.add(p);
		p.addChildren(this);
	}
	
	public void removeParent(Node p){
		parents.remove(p);
	}
	
	public void addChildren(Node p){
		children.add(p);
	}
	
	public void removeChildren(Node p){
		children.remove(p);
	}
	
	public int getNumParents() {
		return parents.size();
	}
	
	public int getNumChildren() {
		return children.size();
	}
	
	public Node(Task task, Set<Node> parents) throws Exception{
		this.setTask(task);
		this.parents = parents;
	}
	
	public Node(Task task) throws Exception{
		this.setTask(task);
		this.parents = new TreeSet<Node>();
	}
	
	public Status runTask(){
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
	
}