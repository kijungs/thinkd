package thinkd;

/**
 * =================================================================================
 * Think before You Discard: Accurate Triangle Counting in Graph Streams with Deletions
 * Authors: Kijung Shin, Jisu Kim, Bryan Hooi, and Christos Faloutsos
 *
 * Version: 1.0
 * Date: March 12, 2018
 * Main Contact: Kijung Shin (kijungs@cs.cmu.edu)
 *
 * This software is free of charge under research purposes.
 * For commercial purposes, please contact the author.
 =================================================================================
 */

import java.io.IOException;
import java.util.Random;

/**
 * Batch Process of ThinkD (Accurate Ver.)
 *
 * @author kijungs (kijungs@cs.cmu.edu)
 */
public class BatchAcc {

    public static void main(String[] args) throws IOException {

        if(args.length < 4) {
            printError();
            System.exit(-1);
        }

        final String inputPath = args[0];
        System.out.println("input_path: " + inputPath);
        final String outputPath = args[1];
        System.out.println("output_path: " + outputPath);
        final int memroyBudget = Integer.valueOf(args[2]);
        System.out.println("memory_budget: " + memroyBudget);
        final int numberOfTrials = Integer.valueOf(args[3]);
        System.out.println("number_of_trials: " + numberOfTrials);

        for(int trial = 0; trial < numberOfTrials; trial++) {
            final ThinkDAcc module = new ThinkDAcc(memroyBudget, new Random().nextInt());

            System.out.println("start running ThinkD (Accurate Ver.)...(" + trial + "/" + numberOfTrials + ")");
            Common.runBatch(module, inputPath, "\t");
            System.out.println("ThinkD (Accurate Ver.) terminated ...");
            System.out.println("Estimated number of global triangles: " + module.getGlobalTriangle());

            Common.writeOutputs(module, outputPath, trial);
        }
        return;
    }

    private static void printError() {
        System.err.println("Usage: run_acc.sh input_path output_path memory_budget number_of_trials");
        System.err.println("- memory_budget should be an integer greater than or equal to 2.");
    }

}
