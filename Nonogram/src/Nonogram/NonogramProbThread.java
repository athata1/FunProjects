package Nonogram;

import java.util.ArrayList;
import java.util.Arrays;

public class NonogramProbThread implements Runnable{
    double[] probabilities;
    int total;
    String[] arr;
    int[] rule;
    public NonogramProbThread(String[] arr, int[] rule)
    {
        total = 0;
        this.arr = arr;
        this.rule = rule;
    }

    public void run()
    {
        findProbabilityOfOccurrence(arr,rule);
    }
    public double[] getProbabilities() {
        return probabilities;
    }

    private void findProbabilityOfOccurrence(String[] arr, int[] rule)
    {
        probabilities = new double[arr.length];
        //helper(arr,rule);
        helper2(0,new ArrayList<Integer>(),arr,rule);
        for(int i = 0; i < probabilities.length; i++)
        {
            probabilities[i] = probabilities[i]/total;
        }
    }
    private void helper2(int depth, ArrayList<Integer> currentIndexes, String[] arr, int[] rule) {
        if (currentIndexes.size() == rule.length) {
            int[] row = new int[arr.length];
            Arrays.fill(row,0);
            for (int i = 0; i < currentIndexes.size(); i++) {
                for (int j = currentIndexes.get(i); j < rule[i] + currentIndexes.get(i); j++) {
                    row[j]++;
                }
            }
            for (int i = 0; i < arr.length; i++) {
                if (row[i] == 0 && arr[i].equals("S") || row[i] == 1 && arr[i].equals("X"))
                    return;
            }
            total++;
            for (int i = 0; i < arr.length; i++) {
                if (row[i] == 1)
                    probabilities[i]++;
            }
            return;
        }
        int longestVal = 0;
        for (int i = depth; i < rule.length; i++) {
            longestVal += rule[i];
            longestVal++;
        }
        longestVal--;
        int start = 0;
        if (currentIndexes.size() != 0)
            start = currentIndexes.get(currentIndexes.size() - 1) + rule[depth - 1] + 1;
        for (int i = start; i < arr.length - longestVal + 1; i++) {
            currentIndexes.add(i);
            helper2(depth + 1, currentIndexes, arr, rule);
            currentIndexes.remove(currentIndexes.size() - 1);
        }
    }
    private void helper(String[] arr, int[] rule)
    {
        if (isFull(arr))
        {
            if (ruleMatches(arr,rule))
            {
                total++;
                for(int i = 0; i < arr.length; i++)
                {
                    char c = arr[i].charAt(0);
                    if (c == 'S')
                        probabilities[i]++;
                }
            }
            return;
        }
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i].equals(""))
            {
                String[] stringArray = {"X","S"};
                for(String s: stringArray)
                {
                    arr[i] = s;
                    helper(arr,rule);
                }
                arr[i] = "";
                break;
            }
        }
    }

    //Determine if board is completely filled
    public static boolean isFull(String[] arr)
    {
        for (String s: arr)
        {
            if (s.equals(""))
                return false;
        }
        return true;
    }

    //Determine if rule matches given String
    private boolean ruleMatches(String[] arr, int[] rule)
    {
        int[] currentRule = getRule(arr);

        if (currentRule.length != rule.length)
            return false;

        for (int i = 0; i < rule.length; i++)
        {
            if (rule[i] != currentRule[i])
                return false;
        }
        return true;
    }

    //Covert a String array into the Nonogram Rule for the String array
    private int[] getRule(String[] arr)
    {
        int count = 0;
        ArrayList<Integer> currentRule = new ArrayList<Integer>();
        for (String s: arr)
        {
            char c = s.charAt(0);
            if (c == 'S')
                count++;
            else
            {
                if (count != 0)
                {
                    currentRule.add(count);
                    count = 0;
                }
            }
        }
        if (count != 0)
            currentRule.add(count);

        if (currentRule.size() == 0)
            return new int[]{0};

        int[] output = new int[currentRule.size()];
        for (int i = 0; i < output.length; i++)
        {
            output[i] = currentRule.get(i);
        }
        return output;
    }
}