import java.util.ArrayList;

public class Instruction 
{
	String type;
	ArrayList<String> registers;
	int init;
	String def;
	public Instruction(String s)
	{
		if(s.contains("if"))
		{
			registers = new ArrayList<String>();
			StringBuilder sb = new StringBuilder(s);
			//System.out.println("should be R1 " + sb.substring(2,4));
			registers.add(sb.substring(2,4));
			type = "if";//check 
			def = sb.substring(5);
			System.out.println("type: " + type);
			return;
		}
		if(s.contains("while"))
		{
			registers = new ArrayList<String>();
			StringBuilder sb = new StringBuilder(s);
			//System.out.println("should be R1 " + sb.substring(5,7));
			registers.add(sb.substring(5,7));
			type = "while";
			def = sb.substring(8);
		}
		else
		{
			registers = new ArrayList<String>();
			StringBuilder sb = new StringBuilder(s);
			registers.add(sb.substring(0,2));
			sb.delete(0, 1);
			if(sb.indexOf("0") < sb.indexOf("R")  && sb.indexOf("0") != -1)
				type = sb.substring(1,sb.indexOf("0"));
			else if(sb.indexOf("R") != -1)
				type = sb.substring(1,sb.indexOf("R"));
			else
				type = sb.substring(1,2);
			if(type.equals("="))
			{
				init = Integer.parseInt(sb.substring(2,sb.length()));
			}
			else {
				while(sb.indexOf("R") != -1 || sb.indexOf("0") != -1)
				{
					if(sb.indexOf("0") < sb.indexOf("R") && sb.indexOf("0") != -1) //0 comes first
					{
						int index = sb.indexOf("0");
						registers.add("0");
						sb.delete(0, index+1);
					}
					else
					{
						int index = sb.indexOf("R");
						registers.add(sb.substring(index,index+2));
						sb.delete(0, index+2);
					}
				}
			}
		}
	}
	public Instruction(ArrayList<String> registerList, String typeC, int initC)
	{
		registers = registerList;
		type = typeC;
		init = initC;
	}
	public String getType()
	{
		return type;
	}
	public ArrayList<String> getRegisters()
	{
		return registers;
	}
	public int getInit()
	{
		return init;
	}
}
