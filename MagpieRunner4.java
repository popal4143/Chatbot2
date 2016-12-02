import java.util.Scanner;

/**
 * A simple class to run the Magpie class.
 * @author Laurie White
 * @version April 2012
 */
public class MagpieRunner4
{
    public static void SimulateHuman(String s) {
        for (int i=0;i<s.length();i++){
            System.out.print(s.substring(i,i+1));
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       }
       System.out.println("");
    }

	/**
	 * Create a Magpie, give it user input, and print its replies.
	 */
	public static void main(String[] args)
	{
		Magpie4 maggie = new Magpie4();
		
		System.out.println (maggie.getGreeting());
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();
		
		while (!statement.equals("Bye") || !statement.equals("Goodbye"))
		{
		    String response = maggie.getResponse(statement);
			SimulateHuman(response);
			statement = in.nextLine();
		}
	}

}
