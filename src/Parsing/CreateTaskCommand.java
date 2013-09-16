package Parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Code;
import Model.CountCode;
import Model.Graph;
import Model.Node;
import Model.SleepCode;
import Model.Task;

public class CreateTaskCommand implements Command{
	private Graph graph;
	
	public CreateTaskCommand(Graph graph) {
		this.graph = graph;
	}
	
	public Code getTaskType(String arg) throws Exception{
		if( arg.equalsIgnoreCase("sleep")){
			return new SleepCode();
		}
		else{
			if(arg.equalsIgnoreCase("count")){
				return new CountCode();
			}
		}
		throw new Exception("Invalid task type");
	}
	
	@Override
	public void run(String arg) {
		//format: "TaskName := typeName integer"
		Pattern r = Pattern.compile("(\\w+)\\s*:=\\s*(sleep|count)\\s*(\\d*)");
		Matcher m = r.matcher(arg);
		//System.out.println(":"+m.groupCount()+"\n");
			//System.out.println(m.group(1)+" "+m.group(2)+" "+m.group(3));
		try {
			if(m.find()){
				int value = Integer.valueOf(m.group(3)).intValue();
				Code taskType = getTaskType(m.group(2));
				Node newNode = new Node(new Task(m.group(1), value, taskType), graph);
				//System.out.println(newNode);
			}
			else{
				throw new Exception("Invalid Format when creating new task");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
