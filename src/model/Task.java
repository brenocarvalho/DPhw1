package model;

import java.util.Observable;

//TODO make Task implements Observable

public class Task{
	private String name;
	private double extimatedTime;
	private Status status;
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

	public Status getStatus() {
		return status;
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

	public Task(String name, long extimatedTime, Code code) throws Exception{
		this.name = name;
		this.setExtimatedTime(extimatedTime);
		this.setCode(code);
		this.parameters = extimatedTime;
		this.status = Status.SUCCESS;
	}

	public Task(String name, long extimatedTime) throws Exception{
		this(name, extimatedTime, new SleepCode());
	}
	
	public Status run() throws Exception{
		status = Status.RUNNING;
		status = code.run(parameters);
		return status;
	}
	
	public String toString(){
		return String.format("Task: %s \nEstimatedTime: %6.2f \n\n", this.name, this.extimatedTime);
	}
}
