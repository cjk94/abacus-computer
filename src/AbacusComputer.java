import java.util.ArrayList;

public class AbacusComputer 
{
	ArrayList<Instruction> instructions;
	static AssociativeMemory mem;
	public AbacusComputer()
	{
		instructions = new ArrayList<Instruction>();
		mem = new AssociativeMemory();
	}
	public void addInstructions(ArrayList<Instruction> iList)
	{
		instructions.addAll(iList);
		System.out.println("\ntype: " + instructions.get(0).type);
		System.out.println("first register: " + instructions.get(0).registers.get(0));

		System.out.println("\ntype: " + instructions.get(1).type);
		System.out.println("first register: " + instructions.get(1).registers.get(0));
		System.out.println("second register: " + instructions.get(1).registers.get(1));
		System.out.println("third register: " + instructions.get(1).registers.get(2));
		
		System.out.println("\ntype: " + instructions.get(2).type);
		System.out.println("first register: " + instructions.get(2).registers.get(0));
		System.out.println("second register: " + instructions.get(2).registers.get(1));
		System.out.println("third register: " + instructions.get(2).registers.get(2));
		
		System.out.println("\ntype: " + instructions.get(3).type);
		System.out.println("first register: " + instructions.get(3).registers.get(0));
		System.out.println("second register: " + instructions.get(3).registers.get(1));
		System.out.println("third register: " + instructions.get(3).registers.get(2));
		System.out.println("second register: " + instructions.get(3).registers.get(3));

	}
	public void compute()//carries out instructions
	{
		while(!instructions.isEmpty())
		{
			Instruction i = instructions.remove(0);
			switch(i.getType())
			{
			case "="://initialize a register
				init(i.getRegisters(),i.getInit());
				break;
			case "=="://test for equality
				testEqual(i.getRegisters());
				break;
			case "+"://add given register 1 with given register 2, store result in given register 3
				add(i.getRegisters());
				break;
			}
			System.out.println("instruction complete");
		}
	}
	public void init(ArrayList<String> registers, int init)
	{
		int registerOne = Integer.parseInt(registers.get(0).substring(1));//gets argument register identity
		mem.set(registerOne, init);
	}
	public boolean testZero(int value)
	{
		if(value != 0)
		{
			return false;
		}
		return true;
	}
	public void testEqual(ArrayList<String> registers)
	{
		int result = 0; //value to store in argument register 3, 0 if false, 1 if true
		int registerOne = Integer.parseInt(registers.get(0).substring(1)); //gets argument registers' identities
		int registerTwo = Integer.parseInt(registers.get(1).substring(1));
		int registerOnePrime = nthPrime(registerOne);
		int registerOneValue = mem.get(registerOnePrime);
		
		int registerTwoPrime = nthPrime(registerTwo);
		int registerTwoValue = mem.get(registerTwoPrime);
		while(true)
		{
			registerOneValue--;
			if(testZero(registerOneValue))
			{
				registerTwoValue--;
				if(testZero(registerTwoValue))
				{
					result++;
					//END store result
					break;
				}
				else
				{
					result = 0;
					//END store result
					break;
				}
			}
			else 
			{
				registerTwoValue--;
				if(testZero(registerTwoValue))
				{
					result = 0;
					//END store result
					break;
				}
			}
		}
		System.out.println("result after testing for equality = " + result);
	}
	public void add(ArrayList<String> registers)
	{
		int result = 0; //value to store in argument register 3, 0 if false, 1 if true
		int registerOne = Integer.parseInt(registers.get(0).substring(1)); //gets argument registers' identities
		int registerTwo = Integer.parseInt(registers.get(1).substring(1));
		int registerOnePrime = nthPrime(registerOne);
		int registerOneValue = mem.get(registerOnePrime);
		
		int registerTwoPrime = nthPrime(registerTwo);
		int registerTwoValue = mem.get(registerTwoPrime);
		while(!testZero(registerOneValue))
		{
			result++;
		}
		while(!testZero(registerTwoValue))
		{
			result++;
		}
		System.out.println("result after adding = " + result);
	}
	public void multiply(ArrayList<String> registers)
	{
		int result = 0; //value to store in argument register 3, 0 if false, 1 if true
		int registerOne = Integer.parseInt(registers.get(0).substring(1)); //gets argument registers' identities
		int registerTwo = Integer.parseInt(registers.get(1).substring(1));
		int registerThree = Integer.parseInt(registers.get(2).substring(1));
		int registerOnePrime = nthPrime(registerOne);
		int registerOneValue = mem.get(registerOnePrime);
		
		int registerTwoPrime = nthPrime(registerTwo);
		int registerTwoValue = mem.get(registerTwoPrime);
		
		//NEED TO ZERO OUT THREEVALUE BEFORE
		int registerThreePrime = nthPrime(registerThree);
		int registerThreeValue = mem.get(registerThreePrime);
		
		while(!testZero(registerTwoValue))
		{
			ArrayList<String> tempReg = new ArrayList<String>();
			tempReg.add(registers.get(0));
			tempReg.add(registers.get(2));
			tempReg.add(registers.get(2));
			add(tempReg);
			registerTwoValue--;
		}
	}
	public void divide(ArrayList<String> registers)
	{
		int result = 0; //value to store in argument register 3, 0 if false, 1 if true
		int registerOne = Integer.parseInt(registers.get(0).substring(1)); //gets argument registers' identities
		int registerTwo = Integer.parseInt(registers.get(1).substring(1));
		int registerThree = Integer.parseInt(registers.get(2).substring(1));
		int registerOnePrime = nthPrime(registerOne);
		int registerOneValue = mem.get(registerOnePrime);
		
		int registerTwoPrime = nthPrime(registerTwo);
		int registerTwoValue = mem.get(registerTwoPrime);
	}
	public void lessThanOrEqual(ArrayList<String> registers)
	{
		
	}
	public int checkIfPrime(int i)
	{
		return i;
	}
	public int nextPrime(int i)
	{
		return i;
	}
	public int nthPrime(int i)
	{
		return i;
	}
	//TODO Test for 0, test for equality, addition, multiplication, division, checkPrime, nextPrime, nthPrime
}
