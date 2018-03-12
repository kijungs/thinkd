Think Before You Discard: Accurate Triangle Counting in Graph Streams with Deletions
========================

**ThinkD (Think before you Discard)** is a streaming algorithm for triangle counting in a fully dynamic graph stream with edge additions and deletions.  
**ThinkD** estimates the counts of global triangles and local triangles incident to each node by making a single pass over the stream.  
**ThinkD** has the following advantages:  
 * *Accurate*: **ThinkD** is up to 4.3X em more accurate than its best competitors within the same memory budget  
 * *Fast*: **ThinkD** is up to 2.2X faster for the same accuracy requirements  
 * *Theoretically Sound*: **ThinkD** always maintains unbiased estimates.

Datasets
========================
The download links for the datasets used in the paper are [here](http://www.cs.cmu.edu/~kijungs/thinkd/)

Building and Running WRS
========================
Please see [User Guide](user_guide.pdf)

Running Demo
========================
For demo, please type 'make'


