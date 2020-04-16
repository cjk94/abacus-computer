import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Wrapper {

	public static void main(String[] args) throws IOException
		{
			String input = args[0];
			Scanner scanny = new Scanner(new FileInputStream(input));
			String s;
			ArrayList<Instruction> instructions = new ArrayList<Instruction>();
			while(scanny.hasNext())
			{
				s = scanny.nextLine();
				//System.out.println(s);
				Instruction i = new Instruction(s);
				instructions.add(i);
			}
			AbacusComputer ac = new AbacusComputer();
			ac.addInstructions(instructions);
			ac.compute();
		}
}
