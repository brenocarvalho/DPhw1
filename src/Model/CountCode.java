package Model;

public class CountCode {

	public Status run(Object obj){
		try {
			Thread.sleep((Integer) obj);
		} catch (InterruptedException e) {
			return Status.FAIL;
		}
		return Status.SUCCESS;
	}
}
