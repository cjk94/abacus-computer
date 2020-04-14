import java.math.BigInteger;

public class AssociativeMemory 
{
	int v;
	public AssociativeMemory()//default constructor
	{
		int v = 1;
	}
	public void set(int register, int value)
	{
		System.out.println("mem v = " + v);
		if(register == 1)
			v = (int) (v + Math.pow(2, value+1));
		System.out.println("mem v = " + v);
	}
	public int get(int prime)
	{
		int tempV = v;
		int value;
		while(tempV % prime != 0)
		{
			
		}
		return value = 0;
	}
}
