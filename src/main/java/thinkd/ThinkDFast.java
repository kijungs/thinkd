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


import it.unimi.dsi.fastutil.ints.Int2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;

import java.util.Random;

/**
 * Implementation of ThinkD (Fast Ver.)
 *
 * @author kijungs (kijungs@cs.cmu.edu)
 */
public class ThinkDFast extends ThinkD {

    private Int2ObjectOpenHashMap<IntOpenHashSet> srcToDsts = new Int2ObjectOpenHashMap(); // graph composed of the sampled edges
    private Int2DoubleOpenHashMap nodeToTriangles = new Int2DoubleOpenHashMap(); // local triangle counts
    private double globalTriangle = 0; // global triangle count

    private final double r; // probability that each added edge is sampled
    private final double inc; // amount of increase for each discovered triangle

    private final Random random;
    private final boolean lowerBound; // whether to lower bound the estimate by zero


    /**
     * @param samplingRatio probability that each added edge is sampled
     * @param seed random seed
     */
    public ThinkDFast(final double samplingRatio, final int seed) {
        this(samplingRatio, seed, true);
    }

    public ThinkDFast(final double samplingRatio, final int seed, final boolean lowerBound) {
        random = new Random(seed);
        this.r = samplingRatio;
        this.inc = 1.0 / r / r;
        this.lowerBound = lowerBound;
        nodeToTriangles.defaultReturnValue(0);
    }

    @Override
    public void processAddition(final int src, final int dst) {
        processEdge(src, dst, true);
    }

    @Override
    public void processDeletion(final int src, final int dst) {
        processEdge(src, dst, false);
    }

    private void processEdge(int src, int dst, final boolean add) {

        if(src == dst) { //ignore self loop
            return;
        }

		if (src > dst) {
			int temp = src;
			src = dst;
			dst = temp;
		}

        count(src, dst, add);  //count the added or deleted triangles

        if(add) {

            if(random.nextDouble() < r) {
                addEdge(src, dst); // store the sampled edge
            }
        }
        else {

            if(srcToDsts.containsKey(src)) {
                if(srcToDsts.get(src).contains(dst)) {
                    deleteEdge(src, dst); // remove the edge from the samples
                }
            }
        }

        return;
    }

    /**
     * Store a sampled edge
     * @param src source node of the given edge
     * @param dst destination node of the given edge
     */
    private void addEdge(final int src, final int dst) {

        if(!srcToDsts.containsKey(src)) {
            srcToDsts.put(src, new IntOpenHashSet());
        }
        srcToDsts.get(src).add(dst);

        if(!srcToDsts.containsKey(dst)) {
            srcToDsts.put(dst, new IntOpenHashSet());
        }
        srcToDsts.get(dst).add(src);
    }

    /**
     * Remove an edge from the samples
     * @param src source node of the given edge
     * @param dst destination node of the given edge
     */
    private void deleteEdge(final int src, final int dst) {

        IntOpenHashSet map = srcToDsts.get(src);
        map.remove(dst);
        if(map.isEmpty()) {
            srcToDsts.remove(src);
        }
        map = srcToDsts.get(dst);
        map.remove(src);
        if(map.isEmpty()) {
            srcToDsts.remove(dst);
        }
    }

    @Override
    public double getGlobalTriangle() {
        return globalTriangle;
    }

    @Override
    public Int2DoubleOpenHashMap getLocalTriangle() {
        return nodeToTriangles;
    }

    /**
     * Counts triangles with the given edge and update estimates
     * @param src the source node of the given edge
     * @param dst the destination node of the given edge
     * @param add true for addition and false for deletion
     */
    private void count(final int src, final int dst, boolean add) {

        // if this edge has a new node, there cannot be any triangles
        if(!srcToDsts.containsKey(src) || !srcToDsts.containsKey(dst)) {
            return;
        }

        IntOpenHashSet srcSet = srcToDsts.get(src);
        IntOpenHashSet dstSet = srcToDsts.get(dst);

        if(srcSet.size() > dstSet.size()) {
            IntOpenHashSet temp = srcSet;
            srcSet = dstSet;
            dstSet = temp;
        }

        if(add) { // process the addition

            int count = 0; // number of added triangles

            for (int neighbor : srcSet) {
                if (dstSet.contains(neighbor)) {
                    count++;
                    nodeToTriangles.addTo(neighbor, inc);
                }
            }

            if (count > 0) {
                double countSum = inc * count;
                nodeToTriangles.addTo(src, countSum);
                nodeToTriangles.addTo(dst, countSum);
                globalTriangle += countSum;
            }

        }

        else if(lowerBound){ // process the deletion with lower bounding

            int count = 0; // number of deleted triangles

            for (int neighbor : srcSet) {
                if (dstSet.contains(neighbor)) {
                    count ++;
                    double value = nodeToTriangles.addTo(neighbor, - inc);
                    if(value < inc) {
                        nodeToTriangles.put(neighbor, 0); //lower bounding
                    }
                }
            }

            if (count > 0) {
                final double countSum = inc * count;
                double value = nodeToTriangles.addTo(src, - countSum);
                if(value < countSum) {
                    nodeToTriangles.put(src, 0); //lower bounding
                }
                value = nodeToTriangles.addTo(dst, - countSum);
                if(value < countSum) {
                    nodeToTriangles.put(dst, 0); //lower bounding
                }
                globalTriangle -= countSum;
                globalTriangle = Math.max(0, globalTriangle); //lower bounding
            }
        }

        else { // process the deletion without lower bounding
            int count = 0; // number of deleted triangles

            for (int neighbor : srcSet) {
                if (dstSet.contains(neighbor)) {
                    count ++;
                    nodeToTriangles.addTo(neighbor, - inc);
                }
            }

            if (count > 0) {
                double countSum = inc * count;
                nodeToTriangles.addTo(src, - countSum);
                nodeToTriangles.addTo(dst, - countSum);
                globalTriangle -= countSum;
            }
        }

    }

}
