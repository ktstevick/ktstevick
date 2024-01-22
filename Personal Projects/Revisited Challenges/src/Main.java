import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    // This program exists only to further explore the ideas and concepts I was challenged by during the JPMorgan
    // code evaluation. I don't have access to the unit tests and so will be confirming manually. In addition, I don't have
    // exact copies of the problem, so I will be recreating them to the best of my memory. I think it makes the most sense to organize
    // them as individual methods of this Main class, but that may change as I work.

    public static void main(String[] args) {
        // This is where we'll adjust the parameters from.

        // firstParam is a String, at least three characters long, always lowercase, and is always a palindrome. There is some ridiculously large
        // ceiling for maximum String size, but the exact reach escapes me and seems irrelevant at the moment.

        // secondParam is a List of numbers greater than zero. The problem stated them as standard integers, but they're in the wrapper class
        // Integer for flexibility within the ArrayList. Same deal with the max size, but the test cases were usually between 3 and 10 ints or so.

        String firstParam = "acca";
        List<Integer> secondParam = new ArrayList<>();

        secondParam.add(3);
        secondParam.add(1);
        secondParam.add(2);
//        secondParam.add(10);
//        secondParam.add(5);
//        secondParam.add(3);

        // CLI displays results for confirmation.

        // FIRST PROBLEM
        System.out.println();
        System.out.println("********** FIRST PROBLEM **********");

        String brokenPalindrome = breakPalindrome(firstParam);

        System.out.println("INPUT: " + firstParam);
        System.out.println("OUTPUT: " + brokenPalindrome);

        // SECOND
        System.out.println();
        System.out.println("********** SECOND PROBLEM **********");

        int lowCost = findLowestTotalCost(secondParam);

        // INPUT: line handled by method call
        System.out.println("OUTPUT: " + lowCost);

    }

    // The first challenge was to break a palindrome by changing a single letter within the provided string. You are only
    // allowed to change a single letter, to any value within ASCII, but the resulting string MUST no longer be a palindrome,
    // MUST be lower in value alphabetically than the provided palindrome, and MUST return "IMPOSSIBLE" if the provided palindrome
    // cannot be reconstructed in such a way.

    // INPUT: "aaabbaaa"
    // OUTPUT: "aaaabaaa"

    // INPUT: "bab"
    // OUTPUT: "aab"

    // INPUT: "acca"
    // OUTPUT: "aaca"

    public static String breakPalindrome(String palindromeStr) {
        // INITIAL THOUGHTS: when I first saw this problem, I thought it would be pretty straight forward. Split the string a few times,
        // change a letter to A, and then move on with the test. As I worked I realized not only was I unfamiliar with the organizational
        // logic required to properly assess HOW to split the string, but also, that a String object has less in common with an array
        // than I initially though. I believed that I COULD convert it if necessary, but by the time I made that call, half of the
        // allotted test time had passed, and I simply had to cut and run. I spent a lot of time trying to understand the English of
        // the problem itself - it was my first time assessing strings alphabetically beyond the first letter.

        // Upon further reflection: Converting the string to an array should make it easier to measure and manipulate in the first place, so
        // I'm going to start there. We should never NEED to assess or measure the second half of the word (since it's a palindrome,
        // it would just match the first half anyway, and changing a letter in the second half never nets more displacement than the first half).
        // Can most likely consolidate into a single loop or two depending on how accessible the string char is.

        String returnString = "IMPOSSIBLE";
        int breakPoint = (palindromeStr.length() / 2) - 1; // Abusing integer truncation
        boolean breakPossible = false;

        char[] palindromeStrArray = new char[palindromeStr.length()];
        int indexOfFirstNonA = 0;

        // Manual conversion from String to char[]
        for (int i = 0; i < palindromeStr.length(); i++) {
            // Not the clean typecast I was hoping for, but this works.
            String singleLetter = palindromeStr.substring(i, i + 1);
            char newLetter = singleLetter.charAt(0);

            palindromeStrArray[i] = newLetter;

            // System.out.println(palindromeStrArray);
        }

        // Checks first half for a letter we can set to A, sets index and updates boolean
        for (int i = 0; i <= breakPoint; i++) {
            if(palindromeStrArray[i] != 'a') {
                indexOfFirstNonA = i;
                breakPossible = true;
                break;
            }
        }

        // Actual string adjustments and reformation, skips entirely if palindrome is impossible to break
        if(breakPossible) {
            palindromeStrArray[indexOfFirstNonA] = 'a';
            returnString = "";

            for (int i = 0; i < palindromeStrArray.length; i++) {
                returnString += palindromeStrArray[i];
            }
        }

        return returnString;
    }

    // The second challenge was a little more math heavy. Presented with an array of integers, your goal is to reduce this arrays
    // size to ONE. Numbers can only be removed from the array by making a "move" - this involves removing two numbers, adding
    // them together, and adding the resulting integer to the end of the array. This is referred to as the "cost" of the move, and the crux of the problem
    // is that the total cost of reducing a given array to size one CHANGES based on the order in which its members are "moved". The
    // true goal of this challenge (that is to say, the int you are ultimately expected to return) is the LOWEST POSSIBLE COST of
    // reducing the provided array to size one with these moves.

    // INPUT: [3, 1, 2]
    // OUTPUT: 9

    // INPUT: [3, 1, 2, 3]
    // OUTPUT: 18

    // INPUT: [4, 6, 8]
    // OUTPUT: 28

    public static int findLowestTotalCost(List<Integer> num) {
        // INITIAL THOUGHTS: Arriving at this problem after passing 8/13 tests from the previous, I did not feel particularly super.
        // I believe I misinterpreted the problem as being more akin to finding the sum total of all array members, and coded accordingly.
        // I actually panicked a bit and threw for loops at the problem, without taking the time to properly understand the assignment.
        // I think my logic was actually pretty sound, but coding in an unfamiliar environment and already anxious worked against me.

        // Upon further reflection: Spent some time with a pen and paper, going through arrays by hand until I understood the math behind it.
        // I still need to think about the logic and how it'll be executed, but now that I understand how the lowest cost is found
        // in the first place, I feel like the path forward is well lit. Assessing against other possible outcomes should no longer
        // be necessary - I intend to add the two lowest numbers together indefinitely. I used Collections.min() initially, but I'm not
        // confident I'd have had the ability to import that kind of functionality on the test, so I've used more traditional loops to find
        // the necessary values.

        System.out.println("INPUT: " + num); // Displayed before altering

        int currentTotal = 0;
        int currentCost = 0;

        while (num.size() != 1) {
            // Finding the lowest two ints, adding, removing, and updating the appropriate fields. This may seem clunky since
            // .remove() can work by Object, but since we're dealing with ints it seems to remove the Object at the index
            // that the lowestInt variable is assigned to, rather than its actual position on the List. This work around is functional

            int currentInt;
            int lowestInt = num.get(0); // Collections.min(num);

            for(int i = 0; i < num.size(); i++) {
                currentInt = num.get(i);

                if(currentInt < lowestInt) {
                    lowestInt = currentInt;
                }
            }

            int lowestIntIndex = num.indexOf(lowestInt);
            num.remove(lowestIntIndex);

            int nextLowestInt = num.get(0); // Collections.min(num);

            for(int i = 0; i < num.size(); i++) {
                currentInt = num.get(i);

                if(currentInt < nextLowestInt) {
                    nextLowestInt = currentInt;
                }
            }

            int nextLowestIntIndex = num.indexOf(nextLowestInt);
            num.remove(nextLowestIntIndex);

            currentCost = lowestInt + nextLowestInt;

            num.add(currentCost);
            currentTotal += currentCost;

            // DEBUG, illustrates each individual move
//            System.out.println();
//            System.out.println("Lowest int: " + lowestInt);
//            System.out.println("Next Lowest: " + nextLowestInt);
//            System.out.println("Current Total Cost: " + currentTotal);
//            System.out.println("--- --- --- --- --- --- ---");
//            System.out.println("num: " + num);

        }

        return currentTotal;
    }
}