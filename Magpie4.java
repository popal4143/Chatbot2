import java.util.ArrayList;
/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 *      Uses advanced search for keywords 
 *</li><li>
 *      Will transform statements as well as react to keywords
 *      Specific focus as an FMP Mentor for freshmen to ask questions
 *</li></ul>
 * @author Lemar Popal and Rafael Gamboa
 * @version December 2, 2016
 * ******
 */
public class Magpie4
{
    //Initializing to and from lists. If any of these 
    //strings appear in a user's response or question, the
    //string will be replaced with its appropriate reciprocal. 
    ArrayList<String> fromList = new ArrayList<String>(); 
    ArrayList<String> toList = new ArrayList<String>();
    public Magpie4(){
    //fromList.add("Can I"); toList.add("What if you do not"); // Chatbot replaces this with "What if you do not"
    fromList.add("My");  toList.add("Your");                   // but then replaces "you" with "I". Possible 
    fromList.add("Are");  toList.add("Am");                    // future improvement for chatbot to differentiate.
    fromList.add("are");  toList.add("am");
    fromList.add("Am");  toList.add("Are");
    fromList.add("am");  toList.add("are");
    fromList.add("Were");  toList.add("Was");
    fromList.add("were");  toList.add("was");
    fromList.add("Was");  toList.add("WERE");
    fromList.add("was");  toList.add("WERE");
    fromList.add("I");  toList.add("YOU");
    fromList.add("You");  toList.add("I");
    fromList.add("you");  toList.add("I");
    fromList.add("Your");  toList.add("My");
    fromList.add("your");  toList.add("my");
    fromList.add("My");  toList.add("Your");
    fromList.add("my");  toList.add("your");
    //fromList.add("I've");  toList.add("YOU have"); //These do not work because chatbot first sees
    //fromList.add("You've");  toList.add("I've");   //the "I" or "You" than the "I'm" or "You've".
    //fromList.add("you've");  toList.add("I've");   // Possible future improvement for chatbot to
    //fromList.add("I'm");  toList.add("YOU are");   // differentiate between these.
    //fromList.add("You're");  toList.add("I'm");
    //fromList.add("you're");  toList.add("I'm");
   }
   
   public static boolean stringContainsItemFromList(String inputString, ArrayList<String> items)
   {
    for(int i =0; i < items.size(); i++)
    {
        if(inputString.contains(items.get(i)))
        {
            return true;
        }
    }
    return false;
   }
    
    
    public String transformStr(String s){
        for (int i = 0; i <fromList.size(); i++){
            String f=fromList.get(i);
            String t=toList.get(i);
    
            if (s.indexOf(fromList.get(i))>=0){
                s=s.replaceAll(fromList.get(i),toList.get(i));
            }
        }
        return s;
    }
        
    /**
     * Get a default greeting   
     * @return a greeting
     */ 
    public String getGreeting()
    {
        return "Hello, let's talk. I am your FMP Mentor. \nAsk me questions and I'll do my best to answer!"
        + " \nSo, is there anything you would like to know?"; //Find a way to get their name. Then say "Hello (name)".
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement) //Context Based Responses
    {
        String response = "";
        
        if (statement.length() == 0)
        {
            response = "Say something, please.";
        }

        else if (findKeyword(statement, "lanyard") >= 0) //Context Based Response #1
        {
            response = "Make sure to always wear your lanyard! If you do not wear it you may \nreceive a detention. If you lost it, you can order a new one at \nstudent activities.";
        }
        /*else if (findKeyword(statement, "mother") >= 0
                || findKeyword(statement, "father") >= 0
                || findKeyword(statement, "sister") >= 0
                || findKeyword(statement, "brother") >= 0)
        {
            response = "Tell me more about your family.";
        } */

        // Responses which require transformations
        else if (findKeyword(statement, "I want to", 0) >= 0)
        {
            response = transformIWantToStatement(statement);
        }
        
        else if (findKeyword(statement, "I want", 0) >= 0)
        {
            response = transformIWantStatement(statement);
        }
        else if (findKeyword(statement, "Hi", 0) >= 0)
        {
            response = "Hi";
        }
        else if (findKeyword(statement, "Hello", 0) >= 0)
        {
            response = "Hi";
        }
        //any questions that start with "should" are here
        else if (findKeyword(statement, "Should", 0) >= 0) 
        {
            int psn = findKeyword(statement, "Should", 0);
            
            if (psn >= 0
                    && findKeyword(statement, "AP", psn) >= 0) //Context Based Response #2
            {
                response = "Be careful, AP classes are very difficult. Only take the class \nif it’s really something you love to do."+
                " I would recommend that you visit \nthis website for more information on what classes to take: \n" +
                "http:www.studypoint.com/ed/ap-classes/";
            }
            else if (psn >= 0
                    && findKeyword(statement, "sport", psn) >= 0) //Context Based Response #3
            {
                response ="Yes. Sports are great ways to make "+
                "friends and learn more about yourself. \nRemember "+
                "though, sports take a lot of time and \n" +
                "dedication so make sure you are serious about it.";
            }
            else
            {
                response = getRandomResponse();
            }
        }
        //Questions with "could" are here
        else if (findKeyword(statement, "Could", 0) >= 0)
        {
            int psn = findKeyword(statement, "Could", 0);
            if (psn >= 0
                    && findKeyword(statement, "Homework", psn) >= 0)
            {
                response = "Please visit the HUB and ask a tutor. They’re always available \nand glad to help. "; //Context Based Response #4
            }
            
            else
            {
                response = getRandomResponse();
            }
        }
        //"Why" questions are here
        else if (findKeyword(statement, "Why", 0) >= 0)
        {
            int psn = findKeyword(statement, "Why", 0);
            if (psn >= 0
                    && findKeyword(statement, "off-campus", psn) >= 0)
            {
                response = "The school would like you to succeed in your first year in \nhigh school without distractions." +
                "That’s why DHS created the Freshman Mentor Program \nto help you in your first magical year of high school!"; //Context Based Response #5
            }
            else if (psn >= 0
                    && findKeyword(statement, "have FMP", psn) >= 0)
            {
                response = "Here at Dublin High, we understand that your first year of High School \ncan be scary. That is why we created FMP to ease you \ninto your experience."; //Context Based Response #6
            }
            else
            {
                response = getRandomResponse();
            }
        }
        else if (findKeyword(statement, "What", 0) >= 0)
        {
            int psn = findKeyword(statement, "What", 0);
            if (psn >= 0
                    && findKeyword(statement, "name", psn) >= 0)
            {
                response = "My name is Justin Symmank"; //Context Based Response #7
            }
            else
            {
                response = getRandomResponse();
            }
        }
        //"Where questions are here
        else if (findKeyword(statement, "Where", 0) >= 0)
        {
            int psn = findKeyword(statement, "Where", 0);
            if (psn >= 0
                    && findKeyword(statement, "taco truck", psn) >= 0)
            {
                response = "You are not allowed to go there"; //Context Based Response #8
            }
            else if (psn >= 0
                    && findKeyword(statement, "lockers", psn) >= 0)
            {
                response ="Here at Dublin High, we do not have traditional lockers. \nThere are, however, PE lockers located in the Old Gym."; //Context Based Response #9
            }
            else if (psn >= 0
                    && findKeyword(statement, "building", psn) >= 0)
            {
                response ="A map of the school can be found in your Student Planner."; //Context Based Response #10
            }
             else if (psn >= 0
                    && findKeyword(statement, "lanyard", psn) >= 0)
            {
                response ="Make sure to always wear your lanyard! If you do not wear \nit you may receive a detention. If you lost it, you can order a \nnew one at student activities. "; //Context Based Response #11
            }
            else
            {
                response = getRandomResponse();
            }
        }
        //"How" questions are here
        else if (findKeyword(statement, "How", 0) >= 0) 
        {
            int psn = findKeyword(statement, "How", 0);
            int psn2 = findKeyword(statement, "Where", 0);
            int psn3 = findKeyword(statement, "find", 0);
            if (psn >= 0
                    && findKeyword(statement, "print", psn) >= 0)
            {
                response = "Please visit this website for instructions on how to print: \n"
                + "https://sites.google.com/a/dublinusd.org/the-dhs-hub/ \n"
                + "You can also find out more about the Dublin High School HUB on this website."; //Context Based Response #12
            }
            else if (psn >= 0
                    && findKeyword(statement, "printing", psn) >= 0)
            {
                response = "Please visit this website for instructions on how to print: \n"
                + "https://sites.google.com/a/dublinusd.org/the-dhs-hub/ \n"
                + "You can also find out more about the Dublin High School HUB on this website.";
            }
            else if (psn2 >= 0
                    && findKeyword(statement, "print", psn2) >= 0)
            {
                response = "Please visit this website for instructions on how to print: \n"
                + "https://sites.google.com/a/dublinusd.org/the-dhs-hub/ \n"
                + "You can also find out more about the Dublin High School HUB on this website.";
            }
            else
            {
                if (psn2 >= 0
                    && findKeyword(statement, "printing", psn2) >= 0)
                {
                    response = "Please visit this website for instructions on how to print: \n"
                    + "https://sites.google.com/a/dublinusd.org/the-dhs-hub/ \n"
                    + "You can also find out more about the Dublin High School HUB on this website.";
                }
                else 
                {
                    response = getRandomResponse();
                }
            }
            if (psn >= 0
                    && findKeyword(statement, "make friends", psn) >= 0)
            {
                response = "Get out there! Join clubs, join a sport, participate in student \nactivities. Find people that have similar interests as you and just hang \nout with them."; //Context Based Response #13
            }
            if (psn >= 0
                    && findKeyword(statement, "college", psn) >= 0)
            {
                response = "You're a freshman, stop worrying about college and have fun while you can."; //Context Based Response #14
            }
            if (psn >= 0
                    && findKeyword(statement, "driving", psn) >= 0)
            {
                response = "You can take your permit test at fifteen and a half. \nAfter that, you need to wait six months and take three behind the wheel lessons \nto take your driving lesson. But remember, all upperclassmen hate lowerclassmen \nthat can drive."; //Context Based Response #15     
            }
            if (psn >= 0
                    && findKeyword(statement, "drive", psn) >= 0)
            {
                response = "You can take your permit test at fifteen and a half. \nAfter that, you need to wait six months and take three behind the wheel lessons \nto take your driving lesson. But remember, all upperclassmen hate lowerclassmen \nthat can drive.";         
            }
            if (psn3>=0 && findKeyword(statement, "activities", psn) >= 0)
            {
                response = "Activities are announced during the morning announcements \nand the Video Bulletin. Make sure to pay attention so you don’t \nmiss anything."; //Context Based Response #16
            }
        }
       //Transposition Statements
        else
        {
            // Look for a two word "you __ me" pattern or 
            // "I __ you" transform statement 
            int psn = findKeyword(statement, "you", 0);
            int psn2 = findKeyword(statement, "I", 0);

            if (psn >= 0
                    && findKeyword(statement, "me", psn) >= 0)
            {
                response = transformYouMeStatement(statement); //Context Based Response #17 hullo
            }
            else if (psn2 >= 0
                    && findKeyword(statement, "you", psn2) >= 0)
            {
                response = transformISomethingYouStatement(statement); //Context Based Response #18
            }
            else if (findKeyword(statement, "want", psn2) >= 0) {
                int psn3 = findKeyword(statement, "want", 0);
                response = "Would you really be happy if you had " + statement.substring(findKeyword(statement, "want", psn3 + 4)); //Context Based Response #19
            }
            else if (findKeyword(statement, "want to", psn2) >= 0) {
                int psn4 = findKeyword(statement, "want to", 0);
                response = "Would you really like to " + statement.substring(findKeyword(statement, "want to", psn4 + 7)); //Context Based Response #19
            }
            else if (stringContainsItemFromList(statement, fromList) == true) { //working on this right now that's why it probably won't compile. Comment this out to check if your stuff works. 
                response = transformStr(statement);
            }            
            else
            {
                response = getRandomResponse();
            }
        }
        return response;
    }
    
    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    private String transformIWantToStatement(String statement) 
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "What would it mean to " + restOfStatement + "?";
    }
    
    private String transformIWantStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want", 0);
        String restOfStatement = statement.substring(psn + 6).trim();
        return "Would you really be happy if you had " + restOfStatement + "?";
    }
    
    private String transformISomethingYouStatement(String statement)
    {
        // Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        
        int psnOfYou = findKeyword (statement, "I", 0);
        int psnOfMe = findKeyword (statement, "you", psnOfYou + 1);
        
        String restOfStatement = statement.substring(psnOfYou + 1, psnOfMe).trim();
        return "Why do you " + restOfStatement + " me?"; 
    }

    
    
    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    private String transformYouMeStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        
        int psnOfYou = findKeyword (statement, "you", 0);
        int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
        
        String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
        return "What makes you think that I " + restOfStatement + " you?";
    }
    
    

    
    
    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  
     * @param statement the string to search
     * @param goal the string to search for
     * @param startPos the character of the string to begin the search at
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal, int startPos)
    {
        String phrase = statement.trim();
        //  The only change to incorporate the startPos is in the line below
        int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
        
        //  Refinement--make sure the goal isn't part of a word 
        while (psn >= 0) 
        {
            //  Find the string of length 1 before and after the word
            String before = " ", after = " "; 
            if (psn > 0)
            {
                before = phrase.substring (psn - 1, psn).toLowerCase();
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
            }
            
            //  If before and after aren't letters, we've found the word
            if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
                    && ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
            {
                return psn;
            }
            
            //  The last position didn't work, so let's find the next, if there is one.
            psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
            
        }
        
        return -1;
    }
    
    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
     * @param statement the string to search
     * @param goal the string to search for
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal)
    {
        return findKeyword (statement, goal, 0);
    }
    


    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse() //10 Random Responses
    {
        final int NUMBER_OF_RESPONSES = 10;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        ArrayList<String> randomResponse = new ArrayList<String>();
        randomResponse.add(0,"Interesting, tell me more."); //Random Response #2
        randomResponse.add(1,"Hmmm."); //Random Response #2
        randomResponse.add(2,"Do you really think so?"); //Random Response #3
        randomResponse.add(3,"You don't say."); //Random Response #4
        randomResponse.add(4,"Yeah, I know."); //Random Response #5
        randomResponse.add(5,"Cool!"); //Random Response #6
        randomResponse.add(6,"Go on..."); //Random Response #7
        randomResponse.add(7,"No Way!"); //Random Response #8
        randomResponse.add(8,"Huh? Try saying something else."); //Random Response #9
        randomResponse.add(9,"Hmm. I'm not sure what you mean. Perhaps a human mentor could answer you."); //Random Response #10
        String response = "";
        
        /*if (whichResponse == 0) //Old way of generating random responses. We're using ArrayLists now. 
        {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1)
        {
            response = "Hmmm.";
        }
        else if (whichResponse == 2)
        {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3)
        {
            response = "You don't say.";
        }*/

        return randomResponse.get(whichResponse);
    }

}
