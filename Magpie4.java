/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 *      Uses advanced search for keywords 
 *</li><li>
 *      Will transform statements as well as react to keywords
 *</li></ul>
 * @author Laurie White
 * @version April 2012
 * ******
 */
public class Magpie4
{
    /**
     * Get a default greeting   
     * @return a greeting
     */ 
    public String getGreeting()
    {
        return "Hello, let's talk. I am your FMP Mentor. Ask me questions and I'll do my best to answer!"
        + "So, what's your name?"; //Find a way to get their name. Then say "Hello (name)".
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (statement.length() == 0)
        {
            response = "Say something, please.";
        }

        else if (findKeyword(statement, "lanyard") >= 0)
        {
            response = "Make sure to always wear your lanyard! If you do not wear it you may receive a detention. If you lost it, you can order a new one at student activities.";
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
                    && findKeyword(statement, "AP", psn) >= 0)
            {
                response = "Be careful, AP classes are very difficult. Only take the class if it’s really something you love to do."+
                "I would recommend that you visit this website for more information on what classes to take:" +
                "http:www.studypoint.com/ed/ap-classes/";
            }
            else if (psn >= 0
                    && findKeyword(statement, "sport", psn) >= 0)
            {
                response ="Yes. Sports are great ways to make "+
                "friends and learn more about yourself. Remember "+
                "though, sports take a lot of time and " +
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
                response = "Please visit the HUB and ask a tutor. They’re always available and glad to help. ";
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
                response = "The school would like you to succeed in your first year in high school without distractions." +
                "That’s why DHS created the Freshman Mentor Program to help you in your first magical year of high school!";
            }
            else if (psn >= 0
                    && findKeyword(statement, "have FMP", psn) >= 0)
            {
                response = "Here at Dublin High, we understand that your first year of High School can be scary. That is why we created FMP to ease you into you experience.";
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
                response = "My name is Justin Symmank";
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
                    && findKeyword(statement, "taco-truck", psn) >= 0)
            {
                response = "You are not allowed to go there";
            }
            else if (psn >= 0
                    && findKeyword(statement, "lockers", psn) >= 0)
            {
                response ="Here at Dublin High, we do not have traditional lockers. There are, however, PE lockers located in the Old Gym";
            }
            else if (psn >= 0
                    && findKeyword(statement, "building", psn) >= 0)
            {
                response ="A map of the school can be found in your Student Planner";
            }
             else if (psn >= 0
                    && findKeyword(statement, "lanyard", psn) >= 0)
            {
                response ="Make sure to always wear your lanyard! If you do not wear it you may receive a detention. If you lost it, you can order a new one at student activities. ";
            }
            else
            {
                response = getRandomResponse();
            }
        }
        //"How" questions are here
        else if (findKeyword(statement, "How", 0) >= 0) //finish responses for these and make sure works
        {
            int psn = findKeyword(statement, "How", 0);
            int psn2 = findKeyword(statement, "Where", 0);
            int psn3 = findKeyword(statement, "find", 0);
            if (psn >= 0
                    && findKeyword(statement, "print", psn) >= 0)
            {
                response = "Please visit this website for instructions on how to print: "
                + "https://sites.google.com/a/dublinusd.org/the-dhs-hub/ "
                + "You can also find out more about the Dublin High School HUB on this website.";
            }
            else if (psn >= 0
                    && findKeyword(statement, "printing", psn) >= 0)
            {
                response = "Please visit this website for instructions on how to print: "
                + "https://sites.google.com/a/dublinusd.org/the-dhs-hub/ "
                + "You can also find out more about the Dublin High School HUB on this website.";
            }
            else if (psn2 >= 0
                    && findKeyword(statement, "printing", psn2) >= 0)
            {
                response = "Please visit this website for instructions on how to print: "
                + "https://sites.google.com/a/dublinusd.org/the-dhs-hub/ "
                + "You can also find out more about the Dublin High School HUB on this website.";
            }
            else
            {
                if (psn2 >= 0
                    && findKeyword(statement, "print", psn2) >= 0)
                {
                    response = "Please visit this website for instructions on how to print: "
                    + "https://sites.google.com/a/dublinusd.org/the-dhs-hub/ "
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
                response = "Get out there! Join clubs, join a sport, participate in student activities. Find people that have similar interests as you and just hang out with them.";         
            }
            if (psn >= 0
                    && findKeyword(statement, "college", psn) >= 0)
            {
                response = "You're a freshman, stop worrying about college and have fun while you can";         
            }
            if (psn >= 0
                    && findKeyword(statement, "driving", psn) >= 0)
            {
                response = "You can take your permit test at fifteen and a half. After that, you need to wait six months and take three behind the wheel lessons to take your driving lesson. But remember, all upperclassmen hate lowerclassmen that can drive.";         
            }
            if (psn >= 0
                    && findKeyword(statement, "drive", psn) >= 0)
            {
                response = "You can take your permit test at fifteen and a half. After that, you need to wait six months and take three behind the wheel lessons to take your driving lesson. But remember, all upperclassmen hate lowerclassmen that can drive.";         
            }
            if (psn3>=0 && findKeyword(statement, "activities", psn) >= 0)
            {
                response = "Activities are announced during the morning announcements and the Video Bulletin. Make sure to pay attention so you don’t miss anything.";
            }
        }
       //"I __ you" transform statement 
        else
        {
            // Look for a two word (you <something> me)
            // pattern
            int psn = findKeyword(statement, "you", 0);
            int psn2 = findKeyword(statement, "I", 0);

            if (psn >= 0
                    && findKeyword(statement, "me", psn) >= 0)
            {
                response = transformYouMeStatement(statement);
            }
            else if (psn2 >= 0
                    && findKeyword(statement, "you", psn) >= 0)
            {
                response = transformISomethingYouStatement(statement);
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
        //  Remove the final period, if there is one
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
        return "Why do you " + restOfStatement + " me?"; //fix this
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
    private String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 4;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        
        if (whichResponse == 0)
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
        }

        return response;
    }

}
