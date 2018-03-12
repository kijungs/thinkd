package thinkd;

/**
 * =================================================================================
 * Think Before You Discard: Accurate Triangle Counting in Graph Streams with Deletions
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Example using ThinkD (Fast Ver.)
 *
 * @author kijungs (kijungs@cs.cmu.edu)
 */
public class ExampleFast {

    /**
     * Example Code for ThinkD (Fast Ver.)
     * @throws IOException
     */
    public static void main(String[] ar) throws IOException {

        final String dataPath = "example_graph.txt";
        final String delim = "\t";
        final ThinkDFast module = new ThinkDFast(0.3, 0);

        BufferedReader br = new BufferedReader(new FileReader(dataPath));

        int count = 0;
        while(true) {

            final String line = br.readLine();
            if(line == null) {
                break;
            }

            int[] edge = Common.parseEdge(line, delim);
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

        return;
    }


}
