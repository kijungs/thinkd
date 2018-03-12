=================================================================================

 Think Before You Discard: Accurate Triangle Counting in Graph Streams with Deletions
 Authors: Kijung Shin, Jisu Kim, Bryan Hooi, and Christos Faloutsos

 Version: 1.0
 Date: March 12, 2018
 Main Contact: Kijung Shin (kijungs@cs.cmu.edu)

 This software is free of charge under research purposes.
 For commercial purposes, please contact the author.

 =================================================================================

ThinkD is a streaming algorithm for triangle counting in a fully dynamic graph stream with edge additions and deletions. 
ThinkD estimates the counts of global triangles and local triangles incident to each node by making a single pass over the stream. 
ThinkD has the following advantages: 
- Accurate: ThinkD is up to 4.3X more accurate than its best competitors within the same memory budget
- Fast: ThinkD is up to 2.2X faster than its best competitors for the same accuracy requirements
- Theoretically Sound: ThinkD always maintains unbiased estimates

For detailed information, see 'user_guide.pdf'

For demo, type 'make'