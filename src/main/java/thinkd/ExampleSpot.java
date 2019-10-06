package thinkd;

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

import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Example using ThinkD-Spot
 *
 * @author kijungs (kijungs@cs.cmu.edu)
 */
public class ExampleSpot {

    /**
     * Example Code for ThinkD-Spot
     * @throws IOException
     */
    public static void main(String[] ar) throws IOException {

        final String dataPath = "example_graph_with_timestamps.txt";
        final String delim = "\t";
        final ThinkDSpot module = new ThinkDSpot(2000, 86400, 150, new Random().nextInt());

        final BufferedReader br = new BufferedReader(new FileReader(dataPath));
        final Date date = new Date();
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        while(true) {

            final String line = br.readLine();
            if(line == null) {
                break;
            }

            final String[] tokens = line.split(delim);
            int src = Integer.valueOf(tokens[0]);
            int dst = Integer.valueOf(tokens[1]);
            long timestamp = Long.valueOf(tokens[2]);

            if(module.process(src, dst, timestamp)) {
                double currentGlobalCount = module.getGlobalTriangle();
                date.setTime(timestamp);
                System.out.println("Alert at " + format.format(date) + " with " + currentGlobalCount + " triangles!");
            }
        }
        br.close();

        return;
    }


}
