import Model.*;

public class Main {
	public static void main(String args[]) throws Exception{
		Graph g = new Graph();
		Node p,q;
		p = new Node(new Task("T1", 100), g);
		for(int i = 2; i < 7; i++){
			q = new Node(new Task("T"+i, 100), g);
			q.addParent(p);
			//System.out.print("["+q.toString()+" " +g.contains(q)+"]\n");
			p = q;
		}
		q = new Node(new Task("T5.5", 100), g);
		p.addParent(q); // T5.5 must run first than T6
		//System.out.println(g);
		Scheduler sc = new Scheduler(1, g);
		sc.execute();
		System.out.println(sc);
	}
}
