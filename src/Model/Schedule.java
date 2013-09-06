package Model;

import java.util.List;
import java.util.Map;

public class Schedule {
	private List<Map<Integer, Task>> executionPlan;
	
	public void addExecution(int thread, Task task){}
	
	@Override
	public String toString(){
		return "";
	}
}
