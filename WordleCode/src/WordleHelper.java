import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WordleHelper {
    static ArrayList<String> permutations = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        do {
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
                    System.out.println("3. End Wordle");
                    option = scan.nextLine();
                    try {
                        val = Integer.parseInt(option);
                        if (val < 1 || val > 3) {
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
                                if (pattern.charAt(i) != 'b') {
                                    for (int j = 0; j < countLetters; j++) {
                                        if (letters[j] == c) {
                                            letterExists = true;
                                        }
                                    }
                                    if (!letterExists) {
                                        if (countLetters == letters.length)
                                        {
                                            System.out.println("Error: Invalid Wordle");
                                            invalidWordle = true;
                                            break;
                                        }
                                        letters[countLetters] = c;
                                        countLetters++;
                                    } else {
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
                        printList(permutations);
                        break;
                    case 3:
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
