/*************************************************************************
 *  Compilation:  javac CheckDigit.java
 *  Execution:    java CheckDigit 020131452
 *
 *  @author: Anushka Singh, as3711@scarletmail.rutgers.edu, as3711
 *
 *  Takes a 12 or 13 digit integer as a command line argument, then computes
 *  and displays the check digit
 *
 *  java CheckDigit 048231312622
 *  0
 *
 *  java CheckDigit 9780470458310
 *  0
 * 
 *  java CheckDigit 9780470454310
 *  8
 * 
 *  Print only the check digit character, nothing else.
 *
 *************************************************************************/

public class CheckDigit {

    public static void main (String[] args) {

        long x = Long.parseLong(args[0]);
        int sum1 = 0;
        long temp = x;
        
        while (temp > 0)
        {
            sum1 += temp % 10;
            temp /= 100;
            //System.out.println(sum1 + " " + temp);
        }
        //DISCARD the TENS digit & same thing with SECOND TO LAST digit
        sum1 = sum1 % 10;
        //System.out.println(sum1);
        
        temp = x / 10;
        //System.out.println(temp);

        int sum2 = 0;
        while (temp > 0)
        {
            sum2 += temp % 10;
            temp /= 100;
            //System.out.println(sum2 + " " + temp);
        }
        //DISCARD the TENS digit & MULTIPLY by 3
        //DISCARD the TENS digit
        sum2 = ((sum2 % 10) * 3) % 10;
        //System.out.println(sum2);

        //ADD BOTH NUMBERS && DISCARD the TENS digit & PRINT the digit
        System.out.println((sum1 + sum2) % 10);
    }
}