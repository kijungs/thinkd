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
 * =================================================================================
 */

import java.io.*;
import java.util.Map;
import java.util.Random;

/**
 * Batch Process of ThinkD (Fast Ver.)
 *
 * @author kijungs (kijungs@cs.cmu.edu)
 */
public class BatchFast {

    public static void main(String[] args) throws IOException {

        if(args.length < 4) {
            printError();
            System.exit(-1);
        }

        final String inputPath = args[0];
        System.out.println("input_path: " + inputPath);
        final String outputPath = args[1];
        System.out.println("output_path: " + outputPath);
        final double samplingRatio = Double.valueOf(args[2]);
        System.out.println("sampling_ratio: " + samplingRatio);
        final int numberOfTrials = Integer.valueOf(args[3]);
        System.out.println("number_of_trials: " + numberOfTrials);

        for(int trial = 0; trial < numberOfTrials; trial++) {
            final ThinkDFast module = new ThinkDFast(samplingRatio, new Random().nextInt());

            System.out.println("start running ThinkD (Fast Ver.)...");
            Common.runBatch(module, inputPath, "\t");
            System.out.println("ThinkD (Fast Ver.) terminated ...");
            System.out.println("Estimated number of global triangles: " + module.getGlobalTriangle());

            Common.writeOutputs(module, outputPath, trial);
        }
        return;
    }

    private static void printError() {
        System.err.println("Usage: run_fast.sh input_path output_path sampling_ratio number_of_trials");
        System.err.println("- sampling_ratio should be a real number between 0 (exclusive) and 1 (inclusive)");
    }

}
