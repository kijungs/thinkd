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

import it.unimi.dsi.fastutil.ints.Int2DoubleMap;

/**
 * Interface of ThinkDFast and ThinkDAcc
 *
 * @author kijungs (kijungs@cs.cmu.edu)
 */
public abstract class ThinkD {

    /**
     * Process the addition of an edge
     * @param src source node of the given edge
     * @param dst destination node of the given edge
     */
    public abstract void processAddition(int src, int dst);

    /**
     * Process the deletion of an edge
     * @param src source node of the given edge
     * @param dst destination node of the given edge
     */
    public abstract void processDeletion(int src, int dst);

    /**
     * Get estimated global triangle count
     * @return estimate of global triangle count
     */
    public abstract double getGlobalTriangle();

    /**
     * Get estimated local triangle counts
     * @return map from nodes to counts
     */
    public abstract Int2DoubleMap getLocalTriangle();

}
