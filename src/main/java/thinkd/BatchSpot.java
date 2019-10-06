package thinkd;


import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * =================================================================================
 * Think before You Discard: Accurate Triangle Counting in Graph Streams with Deletions
 * Authors: Kijung Shin, Jisu Kim, Bryan Hooi, and Christos Faloutsos
 *
 * Version: 2.0
 * Date: August 4, 2018
 * Main Contact: Kijung Shin (kijungs@cs.cmu.edu)
 *
 * This software is free of charge under research purposes.
 * For commercial purposes, please contact the author.
 * =================================================================================
 */

public class BatchSpot {

    public static void main(String[] args) throws IOException {

        if(args.length < 5) {
            printError();
            System.exit(-1);
        }

        final String inputPath = args[0];
        System.out.println("input_path: " + inputPath);
        final String outputPath = args[1];
        System.out.println("output_path: " + outputPath);
        final int memroyBudget = Integer.valueOf(args[2]);
        System.out.println("memory_budget: " + memroyBudget);
        final int timeWindow = Integer.valueOf(args[3]);
        System.out.println("time_window: " + timeWindow);
        final int threshold = Integer.valueOf(args[4]);
        System.out.println("threshold: " + threshold);

        System.out.println("start running ThinkD-Spot...");

        final ThinkDSpot module = new ThinkDSpot(memroyBudget, timeWindow, threshold, new Random().nextInt());
        final Long2DoubleOpenHashMap timeToMaxTriangleCount = new Long2DoubleOpenHashMap();

        final Date date = new Date();
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        final BufferedReader br = new BufferedReader(new FileReader(inputPath));
        while(true) {

            final String line = br.readLine();
            if(line == null) {
                break;
            }

            final String[] tokens = line.split("\t");
            int src = Integer.valueOf(tokens[0]);
            int dst = Integer.valueOf(tokens[1]);
            long timestamp = Long.valueOf(tokens[2]);

            if(module.process(src, dst, timestamp)) {
                double currentGlobalCount = module.getGlobalTriangle();
                if(timeToMaxTriangleCount.containsKey(timestamp)) {
                    currentGlobalCount = Math.max(timeToMaxTriangleCount.get(timestamp), currentGlobalCount);
                }
                else {
                    date.setTime(timestamp);
                    System.out.println("Alert at " + format.format(date) + " with " + currentGlobalCount + " triangles!");
                }
                timeToMaxTriangleCount.put(timestamp, currentGlobalCount);
            }
        }
        br.close();

        System.out.println("ThinkD-Spot terminated...");
        System.out.println("writing outputs...");

        File dir = new File(outputPath);
        try{
            dir.mkdir();
        }
        catch(Exception e){

        }

        final BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath+"/time_to_global.txt"));
        final long[] timestamps = timeToMaxTriangleCount.keySet().toLongArray();
        Arrays.sort(timestamps);
        bw.write("Timestamp"+"\t"+"Estimated Global Triangle Count");
        bw.newLine();
        for(final long timestamp : timestamps) {
            bw.write(timestamp + "\t" + timeToMaxTriangleCount.get(timestamp));
            bw.newLine();
        }
        bw.close();

        System.out.println("done.");
    }

    private static void printError() {
        System.err.println("Usage: run_spot.sh input_path output_path memory_budget time_window threshold");
        System.err.println("- memory_budget should be an integer greater than or equal to 2.");
        System.err.println("- time_window should be an integer greater than 0.");
        System.err.println("- threshold should be an integer greater than or equal to 0.");
    }

}
