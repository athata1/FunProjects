import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordleHelper{
    static ArrayList<String> permutations = new ArrayList<>();
    static ArrayList<String> possibleWords = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        Scanner fileScan = new Scanner(new File("WordleGuessList.txt"));
        possibleWords = new ArrayList<>();
        while(fileScan.hasNextLine())
        {
            possibleWords.add(fileScan.nextLine());
        }
        fileScan.close();
        Scanner fileScan2 = new Scanner(new File("WordleAnswers.txt"));
        while(fileScan2.hasNextLine())
        {
            possibleWords.add(fileScan2.nextLine());
        }
        fileScan2.close();
        Scanner scan = new Scanner(System.in);
        do {
            HashSet<Character> blackLetters = new HashSet<>();
            boolean invalidWordle = false;
            char[] letters = new char[5];
            Arrays.fill(letters, ' ');

            int countLetters = 0;
            permutations = new ArrayList<>();
            printAllPermutations(letters, "");

            ArrayList<WordleWord> words = new ArrayList<>();

            int numWords = 0;
            while (true) {
                if (invalidWordle)
                    break;
                String option = "";
                int val = 0;
                do {
                    System.out.println("Select option:");
                    System.out.printf("1. Enter word #%d\n", numWords + 1);
                    System.out.println("2. Print current list");
                    System.out.println("3. Add letter manually");
                    System.out.println("4. Print valid words");
                    System.out.println("5. End Wordle");
                    option = scan.nextLine();
                    try {
                        val = Integer.parseInt(option);
                        if (val < 1 || val > 5) {
                            System.out.println("Error: Enter a number between 1 and 3 inclusive.");
                            option = "";
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a number");
                        option = "";
                    }
                } while (option.equals(""));

                boolean isDone = false;
                switch (val) {
                    case 1:
                        System.out.println("Enter word");
                        String word = scan.nextLine();
                        System.out.println("Enter pattern");
                        String pattern = scan.nextLine();
                        if (word.length() == 5 && pattern.length() == 5) {
                            words.add(new WordleWord(word, pattern));
                            for (int i = 0; i < word.length(); i++) {
                                if (invalidWordle)
                                    break;
                                char c = word.charAt(i);
                                boolean letterExists = false;
                                //Add letters to confirmed letters
                                if (pattern.charAt(i) != 'b') {
                                    //Check if letter already exists in word
                                    for (int j = 0; j < countLetters; j++) {
                                        if (letters[j] == c) {
                                            letterExists = true;
                                        }
                                    }
                                    //Add letter to list if already in word
                                    if (!letterExists) {
                                        if (countLetters == letters.length)
                                        {
                                            System.out.println("Error: Invalid Wordle");
                                            invalidWordle = true;
                                            break;
                                        }
                                        letters[countLetters] = c;
                                        countLetters++;
                                    }
                                    //If letter is in word, check and see if it appears twice.
                                    else {
                                        for (int j = 0; j < pattern.length(); j++) {
                                            if (i == j)
                                                continue;
                                            if (pattern.charAt(j) != 'b') {
                                                if (word.charAt(j) == c) {
                                                    if (countLetters == letters.length)
                                                    {
                                                        System.out.println("Error: Invalid Wordle");
                                                        invalidWordle = true;
                                                        break;
                                                    }
                                                    letters[countLetters] = c;
                                                    countLetters++;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                //Add Letters to confirmed not in word
                                else
                                {
                                    if (!arrayContains(c,letters))
                                    {
                                        blackLetters.add(c);
                                    }
                                }
                            }
                            permutations = new ArrayList<>();
                            printAllPermutations(letters, "");
                            decreaseList(words);
                            numWords++;
                        } else {
                            System.out.println("Error: pattern or word's length does not equal 5");
                        }
                        break;
                    case 2:
                        System.out.printf("CurrentList: {%c,%c,%c,%c,%c}\n", letters[0], letters[1], letters[2], letters[3], letters[4]);
                        printAllPermutations(letters,"");
                        decreaseList(words);
                        printList(permutations);
                        break;
                    case 3:
                        if (countLetters == letters.length)
                        {
                            System.out.println("Error: List is full");
                            break;
                        }
                        System.out.println("Enter a letter to add to list manually:");
                        String letter = scan.nextLine();
                        if (letter.length() != 1) {
                            System.out.println("Error: Please select only one letter");
                            break;
                        }
                        if (!Character.isAlphabetic(letter.charAt(0)))
                        {
                            System.out.println("Error: Character is not alphabetic");
                            break;
                        }
                        letters[countLetters] = letter.charAt(0);
                        break;
                    case 4:
                        printList(getAllPossibleWords(permutations,blackLetters));
                        break;
                    case 5:
                        isDone = true;
                        break;
                }
                if (isDone)
                    break;
            }
            System.out.println("Would you like to play again?(Y/N)");
            String s = scan.nextLine().toUpperCase();
            if (!s.equals("Y"))
                break;
        } while (true);
        System.out.println("Thank you for using the WordleHelper");
    }
    public static void printList(ArrayList<String> permutations)
    {
        for (String permutation : permutations) {
            System.out.println(permutation);
        }
        System.out.println("Total: " + permutations.size());
    }
    public static ArrayList<String> getAllPossibleWords(ArrayList<String> permutations, HashSet<Character> blackLetters)
    {
        ArrayList<String> allWords = new ArrayList<>();
        for (String s: possibleWords)
        {
            allWords.add(s);
        }
        for (int i = allWords.size() - 1; i >= 0; i--)
        {
            for (Character c: blackLetters)
            {
                if (allWords.get(i).contains(c + ""))
                {
                    allWords.remove(i);
                    break;
                }
            }
        }
        for (int i = allWords.size() - 1; i >= 0; i--)
        {
            boolean found = false;
            for (String s: permutations)
            {
                s = s.replace(" ","[a-z]");
                Pattern pattern = Pattern.compile(s);
                Matcher matcher = pattern.matcher(allWords.get(i));
                if (matcher.find())
                {
                    found = true;
                    break;
                }
            }
            if (!found)
                allWords.remove(i);
        }
        return allWords;
    }
    public static void decreaseList(ArrayList<WordleWord> words) {
        for (int i = permutations.size() - 1; i >= 0; i--)
        {
            String permWord = permutations.get(i);
            for (int w = 0; w < words.size(); w++)
            {
                String word = words.get(w).getWord();
                String pattern = words.get(w).getPattern();
                boolean removed = false;
                for (int j = 0; j < word.length(); j++)
                {
                    if (pattern.charAt(j) != 'b')
                    {
                        if (pattern.charAt(j) == 'y' && permWord.charAt(j) == word.charAt(j))
                        {
                            permutations.remove(i);
                            removed = true;
                            break;
                        }
                        else if (pattern.charAt(j) == 'g' && permWord.charAt(j) != word.charAt(j))
                        {
                            permutations.remove(i);
                            removed = true;
                            break;
                        }
                    }
                }
                if (removed)
                    break;
            }
        }
    }
    public static boolean arrayContains(char ch, char[] letters)
    {
        for (char c: letters)
        {
            if (c == ch)
                return true;
        }
        return false;
    }
    public static void printAllPermutations(char[] letters, String s)
    {
        if (s.length() == 5)
        {
            permutations.add(s);
            return;
        }
        for (int i = 0; i < letters.length; i++)
        {
            char currLetter = letters[i];
            if (currLetter != 0)
            {
                s += currLetter;
                letters[i] = 0;
                printAllPermutations(letters,s);
                letters[i] = currLetter;
                s = s.substring(0,s.length()-1);
            }
        }
    }
}
