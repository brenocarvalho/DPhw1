package Model;

import java.util.LinkedList;
import java.util.List;

public class Scheduler {
	private List<Pair<Task, Integer>> executionLog;
	private List<Thread> threads;
	private Graph graph;
	private double timeElapsed;
	
	public void addExecution(int thread, Task task){
		executionLog.add(new Pair(task, thread));
	}
	
	//TODO make it work for many threads
	public void execute() throws Exception {
		if(graph == null) return;
		long begin = System.currentTimeMillis(),
			  end;
		while(graph.hasOrphan()){
			Node next = graph.popOrphan();
			Status status = next.runTask();
			if(status == Status.FAIL){
				System.out.print("A task failed!");
				return;
			}
			this.addExecution(1, next.getTask());
		}
		end = System.currentTimeMillis();
		timeElapsed += (end-begin)/1000.;
	}
	
	public Thread getThread(int index){
		return this.threads.get(index);
	}
	
	public Graph getGraph(){
		return graph;
	}
	
	public int getNumThreads(){
		return this.threads.size();
	}
	
	public double getTimeElapsed(){ return timeElapsed;}
	
	public Scheduler(int numThreads, Graph g){
		executionLog = new LinkedList();
		this.graph = g;
		this.threads = new LinkedList();
		this.timeElapsed = 0;
		for(int i = 0; i < numThreads; i++){
			this.threads.add(new Thread());
		}
	}
	
	public Scheduler(int numThreads){
		this(numThreads, null);
	}
	
	@Override
	public String toString(){
		String out = "Schedule:\n";
		for(Pair<Task, Integer> order: executionLog){
			out += String.format("\tTask: %s\t Thread: %s\n", order.getKey().getName(), order.getValue());
		}
		out += String.format("----------------\nElapsed: %7.3f sec\n", timeElapsed);
		return out;
	}
}
