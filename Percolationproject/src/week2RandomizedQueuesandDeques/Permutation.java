/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2RandomizedQueuesandDeques;

import edu.princeton.cs.algs4.StdIn;


public class Permutation {

    public static void main(String[] args) {
        String[] strings = StdIn.readAllStrings();

        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        for (String string : strings) {
            randomizedQueue.enqueue(string);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(randomizedQueue.dequeue());
        }
    }

}