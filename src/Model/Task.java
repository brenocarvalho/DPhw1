package Model;

//TODO make Task implements Observable

public class Task {
	private String name;
	private Float extimatedTime;
	private boolean concluded = false;
	private Code code;
	
	public Status run(Object obj){
		concluded = false;
		code.run(obj);
		concluded = true;
		return Status.SUCCESS;
	}

	public void notifyConclusion(){}
}
