package Model;

import java.util.Observable;

//TODO make Task implements Observable

public class Task extends Observable {
	private String name;
	private double extimatedTime;
	private boolean concluded;
	private Object parameters;
	private Code code;
	
	public Object getParameters(){
		return parameters;
	}
	
	public void setParameters(Object obj){
		parameters = obj;
	}
	
	public double getExtimatedTime() {
		return extimatedTime;
	}

	public void setExtimatedTime(double extimatedTime) throws Exception {
		if (extimatedTime < 0) throw new Exception("Invalid extimated time.");
		this.extimatedTime = extimatedTime;
	}

	public boolean isConcluded() {
		return concluded;
	}

	public void setConcluded(boolean concluded) {
		this.concluded = concluded;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public Task(String name, double extimatedTime, Code code) throws Exception{
		this.name = name;
		this.setExtimatedTime(extimatedTime);
		this.setCode(code);
		this.setConcluded(false);
	}

	public Task(String name, double extimatedTime){
		this.name = name;
		this.extimatedTime = extimatedTime;
		this.code = new SleepCode();
		this.concluded = false;
	}
	
	public Status run(){
		concluded = false;
		Status result = code.run(this.parameters);
		concluded = true;
		return result;
	}
	
	public String toString(){
		return String.format("Task: %s \nEstimatedTime: %6.2f \n\n", this.name);
	}
}
