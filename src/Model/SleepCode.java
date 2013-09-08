package Model;

public class SleepCode extends Code{

	public Status run(Object obj){
		Task a = new Task("1", 100.);

		try {
			Thread.sleep((Integer) obj);
		} catch (InterruptedException e) {
			return Status.FAIL;
		}
		return Status.SUCCESS;
	}
}
