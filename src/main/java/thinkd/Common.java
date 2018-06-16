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

import java.io.*;
import java.util.Map;

/**
 * Commonly used modules
 *
 * @author kijungs (kijungs@cs.cmu.edu)
 */
class Common {

    public static int[] parseEdge(String line, String delim) {

        String[] tokens = line.split(delim);
        int src = Integer.valueOf(tokens[0]);
        int dst = Integer.valueOf(tokens[1]);
        int sign = Integer.valueOf(tokens[2]);

        return new int[]{src, dst, sign};
    }

    public static void runBatch(ThinkD module, String inputPath, String delim) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(inputPath));

        int count = 0;


        while(true) {

            final String line = br.readLine();
            if(line == null) {
                break;
            }

            int[] edge = parseEdge(line, delim);
            if(edge[2] > 0) {
                module.processAddition(edge[0], edge[1]);
            }
            else {
                module.processDeletion(edge[0], edge[1]);
            }
            if((++count) % 10000 == 0) {
                System.out.println("Number of Elements Processed: " + count +", Estimated Number of Global Triangles: " + module.getGlobalTriangle());
            }

        }

        br.close();
    }

    public static void writeOutputs(final ThinkD module, final String outputPath, final int trialNum) throws IOException {

        System.out.println("writing outputs...");

        File dir = new File(outputPath);
        try{
            dir.mkdir();
        }
        catch(Exception e){

        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath + "/global" + trialNum + ".txt"));
        bw.write(String.valueOf(module.getGlobalTriangle()));
        bw.newLine();
        bw.close();

        bw = new BufferedWriter(new FileWriter(outputPath + "/local" + trialNum + ".txt"));
        Map<Integer, Double> localCounts = module.getLocalTriangle();
        for(int node : localCounts.keySet()) {
            bw.write(node+"\t"+localCounts.get(node));
            bw.newLine();
        }
        bw.close();

    }



}
