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
	}
	public void compute()//carries out instructions
	{
		System.out.println("----------------------------");
		System.out.println("Math Check...");
		System.out.println("----------------------------");
		int x = 2, y = 4;
		int z = add(x,y);
		System.out.println(x + " + " +  y + " = " + z);
		x = 10;
		y = 72;
		z = multiply(x,y);
		System.out.println(x + " * " +  y + " = " + z);
		int[] a = new int[2];
		x = 99;
		y = 12;
		a = divide(x,y);
		System.out.println(x + " / " +  y + " = " + a[0] + " with remainder " + a[1]);
		x = 2;
		if(checkIfPrime(x) == 1)
			System.out.println(x + " is prime");
		else
			System.out.println(x + " is not prime");
		System.out.println("next prime after " + 17 + " is " + nextPrime(17));
		x = 100;
		int b = nthPrime(x);
		System.out.println(x + "th prime is " + b);
		
		System.out.println("\n----------------------------");
		System.out.println("Memory initialized at 1");
		System.out.println("Executing Instructions...");
		System.out.println("----------------------------\n");
		while(!instructions.isEmpty())
		{
			Instruction i = instructions.remove(0);
			switch(i.getType())
			{
			case "="://initialize a register
				int register = Integer.parseInt(i.getRegisters().get(0).substring(1));//gets argument register identity
				init(register,i.getInit());
				System.out.println("Setting register " + register + " to " + i.getInit());
				System.out.println("Memory state after instruction: " + mem.getValue());
				break;
			case "=="://test for equality
				int register1 = Integer.parseInt(i.getRegisters().get(0).substring(1));
				int register2 = Integer.parseInt(i.getRegisters().get(1).substring(1));
				int register3 = Integer.parseInt(i.getRegisters().get(2).substring(1));
				int registerOneValue = retrieve(register1);
				int registerTwoValue = retrieve(register2);
				int registerThreeValue = testEqual(registerOneValue,registerTwoValue);
				set(register3,registerThreeValue);
				System.out.println("Testing for equality register " + register1 + " value (" + registerOneValue + ") with register " + register2 + " value (" + registerTwoValue + ")");
				System.out.println("Result put in register " + register3 + " (" + registerThreeValue + ")");
				System.out.println("Memory state after instruction: " + mem.getValue());
				break;
			case "+"://add given register 1 with given register 2, store result in given register 3
				register1 = Integer.parseInt(i.getRegisters().get(0).substring(1));
				register2 = Integer.parseInt(i.getRegisters().get(1).substring(1));
				register3 = Integer.parseInt(i.getRegisters().get(2).substring(1));
				registerOneValue = retrieve(register1);
				registerTwoValue = retrieve(register2);
				registerThreeValue = add(registerOneValue,registerTwoValue);
				set(register3,registerThreeValue);
				System.out.println("Adding register " + register1 + " value (" + registerOneValue + ") to register " + register2 + " value (" + registerTwoValue + ")");
				System.out.println("Result put in register " + register3 + " (" + registerThreeValue + ")");
				System.out.println("Memory state after instruction: " + mem.getValue());
				break;
			case "*":
				register1 = Integer.parseInt(i.getRegisters().get(0).substring(1));
				register2 = Integer.parseInt(i.getRegisters().get(1).substring(1));
				register3 = Integer.parseInt(i.getRegisters().get(2).substring(1));
				registerOneValue = retrieve(register1);
				registerTwoValue = retrieve(register2);
				registerThreeValue = multiply(registerOneValue,registerTwoValue);
				set(register3,registerThreeValue);
				System.out.println("Muliplying register " + register1 + " value (" + registerOneValue + ") with register " + register2 + " value (" + registerTwoValue + ")");
				System.out.println("Result put in register " + register3 + " (" + registerThreeValue + ")");
				System.out.println("Memory state after instruction: " + mem.getValue());
				break;
			case "/":
				register1 = Integer.parseInt(i.getRegisters().get(0).substring(1));
				register2 = Integer.parseInt(i.getRegisters().get(1).substring(1));
				register3 = Integer.parseInt(i.getRegisters().get(2).substring(1));
				int register4 = Integer.parseInt(i.getRegisters().get(3).substring(1));
				registerOneValue = retrieve(register1);
				registerTwoValue = retrieve(register2);
				int[] registersOut = divide(registerOneValue,registerTwoValue);
				set(register3,registersOut[0]);
				set(register4,registersOut[1]);
				System.out.println("Dividing register " + register1 + " value (" + registerOneValue + ") with register " + register2 + " value (" + registerTwoValue + ")");
				System.out.println("Quotient put in register " + register3 + " (" + registersOut[0] + ") and remainder put in register " + register4 + " (" + registersOut[1] + ")");
				System.out.println("Memory state after instruction: " + mem.getValue());
				break;
			case "if":
				register1 = Integer.parseInt(i.getRegisters().get(0).substring(1));
				registerOneValue = retrieve(register1);
				System.out.println("Truth value is in register " + register1 +" ("+ registerOneValue +")");
				if(!testZero(registerOneValue))
				{
					//create new instruction
					Instruction newI = new Instruction(i.def);
					instructions.add(0, newI);//insert at beginning
				}
				break;
			case "while":
				register1 = Integer.parseInt(i.getRegisters().get(0).substring(1));
				registerOneValue = retrieve(register1);
				System.out.println("Truth value is in register " + register1 +" ("+ registerOneValue +")");
				if(!testZero(registerOneValue))
				{
					//create new instruction
					Instruction newI = new Instruction(i.def);
					instructions.add(0, newI);//insert at beginning
					instructions.add(1,i);
				}
			}
			System.out.println("Instruction complete.\n");
		}
		System.out.println("Computation complete. Final memory state: " + mem.getValue());
	}
	public void init(int register, int init)
	{
		int registerOneValue = add(0,init);
		set(register,registerOneValue);
	}
	public boolean testZero(int value) //returns true if value is 0
	{
		if(value != 0)
		{
			return false;
		}
		return true;
	}
	public int testEqual(int x, int y)
	{
		while(true)
		{
			if(testZero(x) && testZero(y))
			{
				return 1;
			}
			else if(testZero(x) || testZero(y))
				return 0;
			x--;
			y--;
		}
	}

	public int add(int x, int y)
	{
		int result = 0;
		while(!testZero(x))
		{
			x--;
			result++;
		}
		while(!testZero(y))
		{
			y--;
			result++;
		}
		return result;
	}
	
	
	public int multiply(int x, int y)
	{
		int result = 0;
		do
		{
			y--;
			result = add(result,x);
		}while(!testZero(y));
		return result;
	}
	
	public int lessThanOrEqual(int x, int y)
	{
		if(testEqual(x,y) == 1)
		{
			return 1;
		}
		while(!testZero(x))
		{
			x--;
			if(testZero(x))
				return 1;
			else
			{
				y--;
				if(testZero(y))
				return 0;
			}
		}
		return 0;
	}
	public int[] divide(int x, int y)
	{
		int[] result = new int[2];
		result[0] = 1;//R3
		result[1] = 0;//R4
		int temp5 = 0;
		int temp6;
		int temp7;
		int temp8;
		do {
			temp6 = multiply(y,result[0]);
			temp7 = lessThanOrEqual(temp6,x);
			if(!testZero(temp7))
			{
				result[0]++;
			}
		}while(!testZero(temp7));
		result[0]--; //result[0] is now quotient
		temp6 = multiply(y,result[0]);
		do {
			temp8 = testEqual(temp6,x);
			if(!testZero(temp8))
			{
				return result; //result[1] is remainder
			}
			result[1]++;
			temp6++;
		}while(testZero(temp8));
		return result;
		
	}
	
	public int checkIfPrime(int i)
	{
		if(i == 1)
			return 0;
		if(i == 2)
			return 1;
		int r2 = 0;
		int r3 = 2;
		int r6;
		while(true)
		{
			int[] div = divide(i,r3);
			if(testZero(div[1]))
				return 0;
			r3++;
			r6 = testEqual(r3,i);
			if(!testZero(r6))
			{
				return 1;//is prime
			}
		}
	}
	public int nextPrime(int i)
	{
		int r3 = 0;
		while(testZero(r3))
		{
			i++;
			r3 = checkIfPrime(i);
		}
		return i;
	}
	public int nthPrime(int i)
	{
		int r2 = 0;
		int x = 0;
		while(!testZero(i))
		{
			x++;
			r2 = nextPrime(r2);
			i--;
		}
		return r2;
	}
	public void set(int register, int value)
	{
		if(mem.getValue() > 1)
			reset(register);
		if(testZero(value))
			return;
		int prime = nthPrime(register);
		int toMult = prime;
		value--;
		while(!testZero(value))
		{
			toMult = multiply(toMult,prime);
			value--;
		}
		int memV = mem.getValue();
		mem.setValue(multiply(memV,toMult));
	}
	public int retrieve(int register)
	{
		//get register'th prime
		int prime = nthPrime(register);
		//divide mem value by prime until there is a remainder
		int memV = mem.getValue();
		int[] div = new int[2];
		int count = 0;//division count
		do//while remainder is 0
		{
			count++;
			if(memV == 1)
				break;
			if(memV < prime)
				break;
			div = divide(memV,prime);
			memV = div[0];
		}while(testZero(div[1]));
		count--;
		return count;
	}
	public void reset(int register)
	{
		int prime = nthPrime(register);
		//divide mem value by prime until there is a remainder
		int memV = mem.getValue();
		int[] div = new int[2];
		int count = 0;//division count
		int resetVal = 0;
		do//while remainder is 0
		{
			resetVal = memV;
			count++;
			if(memV == 1)
				break;
			if(memV < prime)
				break;
			div = divide(memV,prime);
			memV = div[0];
		}while(testZero(div[1]));
		count--;
		if(testZero(count))
			mem.setValue(mem.getValue());
		else
			mem.setValue(resetVal);
	}
	//TODO Test for 0, test for equality, addition, multiplication, division, checkPrime, nextPrime, nthPrime
}
