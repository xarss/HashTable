package com.hash;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        int[] hashSizes = { 1_000, 5_000, 10_000, 100_000, 1_000_000 };
        int[] arraySizes = { 20_000, 100_000, 500_000, 1_000_000, 5_000_000 };
        for (int index = 0; index < 5; index++) {
            System.out.println("Array size: " + arraySizes[index]);
            System.out.println("Hash Size: " + hashSizes[index]);
            Hash[] hashes = new Hash[3];
            hashes[0] = new Hash(hashSizes[index], "module");
            hashes[1] = new Hash(hashSizes[index], "multiplication");
            hashes[2] = new Hash(hashSizes[index], "folding");

            long seed = 12345;
            Random random = new Random(seed);
            int[] values = new int[arraySizes[index]];

            for (int i = 0; i < arraySizes[index]; i++) {
                values[i] = random.nextInt(900000000) + 100000000;
            }

            for (Hash hash : hashes) {
                long insertTime = 0;
                int collisions = 0;
                System.out.println("Hash Type: " + hash.getType());
                for (int value : values) {
                    long insertStart = System.nanoTime();
                    collisions += hash.insert(value);
                    insertTime += (System.nanoTime() - insertStart);
                }
                System.out.println("\tTime for insert: " + insertTime);
                System.out.println("\tCollisions: " + collisions);

                long searchStart = System.nanoTime();
                int comparisions = 0;
                for (int i = 0; i < 5; i++) {
                    comparisions += hash.search(random.nextInt(900000000) + 100000000);
                }
                long searchTime = (System.nanoTime() - searchStart);
                System.out.println("\tTime for search: " + searchTime);
                System.out.println("\tComparisions: " + comparisions);
            }

        }
    }
}
