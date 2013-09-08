package Model;

public class CountCode extends Code{
	
	public Status run(Object obj)  throws Exception{
		int value = ((Integer) obj).intValue(),
			sum = 0;
		while(value > 0){
			sum += value--;
		}
		System.out.print(String.format("Sum: %d\n", sum));
		return Status.SUCCESS;
	}
}
