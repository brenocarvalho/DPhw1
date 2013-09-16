package Parsing;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Graph;
import Model.Node;
import Model.Task;

public class CreateTaskCommandTest {
	@Test
	public void testRun() {
		try{
			Graph g1 = new Graph();
			Graph g2 = new Graph();
			for(int i = 0; i < 5; i++)
				new Node(new Task("t"+i, 100), g1);
			Command c = new CreateTaskCommand(g2);
			c.run("t0:= sleep 100");
			c.run("t1:= sleep 100");
			c.run("t2:= sleep 100");
			c.run("t3:= sleep 100");
			c.run("t4:= sleep 100");
			assertTrue("Graphs doesnt match!", g1.toString().equals(g2.toString()));
		}catch (Exception e){
			fail("I couldn't create the nodes!");
		}
	}

}
