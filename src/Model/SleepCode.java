package Model;

public class SleepCode {

	public Status run(Object obj){
		int value = ((Integer) obj).intValue(),
			sum = 0;
		while(value > 0){
			sum += value--;
		}
		return Status.SUCCESS;
	}
}
