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

import it.unimi.dsi.fastutil.ints.Int2DoubleOpenHashMap;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of ThinkD-Spot
 *
 * @author kijungs (kijungs@cs.cmu.edu)
 */
public class ThinkDSpot {

    /**
     * size of the time window in seconds during which edges are maintained
     */
    private int timeWindow;

    /**
     * triangle counting module
     */
    private final ThinkDAcc module;

    /**
     * list of (timestamp when an edge should be removed, the source and destination of the edge)
     */
    private Queue<Pair<Long, int[]>> deleteQueue = new LinkedList();

    /**
     * threshold on the estimated count of global triangles
     */
    private int threshold;

    /**
     * @param memoryBudget maximum number of sampled edges
     * @param timeWindow size of the time window in seconds during which edges are maintained
     * @param threshold threshold on the estimated count of global triangles
     * @param seed random seed
     */
    public ThinkDSpot(final int memoryBudget, final int timeWindow, final int threshold, final int seed){
        this.module = new ThinkDAcc(memoryBudget, seed);
        this.timeWindow = timeWindow;
        this.threshold = threshold;
    }

    /**
     * Process an edge with a timestamp
     * @param src source node of the given edge
     * @param dst destination node of the given edge
     * @param timestamp timestamp of the edge in milliseconds
     * @return whether the current estimated count of global triangle exceeds the given threshold
     */
    public boolean process(int src, int dst, long timestamp) {

        while(!deleteQueue.isEmpty() && deleteQueue.peek().getKey() <= timestamp) {
            Pair<Long, int[]> pair = deleteQueue.poll();
            int[] entryToDelete = pair.getValue();
            module.processEdge(entryToDelete[0], entryToDelete[1], false);
        }

        module.processEdge(src, dst, true);
        deleteQueue.add(new Pair<Long, int[]>(timestamp + timeWindow, new int[]{src, dst}));

        return module.getGlobalTriangle() > threshold;
    }

    /**
     * Get estimated global triangle count in the maintained graph.
     * Spikes in this estimated count tend to indicate the occurrence of sudden dense subgraphs
     * @return estimate of global triangle count
     */
    public double getGlobalTriangle() {
        return module.getGlobalTriangle();
    }

    /**
     * Get estimated local triangle counts in the maintained graph.
     * These estimated counts tend to indicate the contribution of each node to sudden dense subgraph
     * @return estimates of local triangle counts
     */
    public Int2DoubleOpenHashMap getLocalTriangle() {
        return module.getLocalTriangle();
    }

}