Think before You Discard: Accurate Triangle Counting in Graph Streams with Deletions
========================

**ThinkD (Think before you Discard)** is a streaming algorithm for triangle counting in a fully dynamic graph stream with edge additions and deletions.  
**ThinkD** estimates the counts of global triangles and local triangles by making a single pass over the stream.  
**ThinkD** has the following advantages:  
 - *Accurate*: **ThinkD** is up to 4.3X more accurate than its best competitors within the same memory budget  
 - *Fast*: **ThinkD** is up to 2.2X faster than its best competitors for the same accuracy requirements  
 - *Theoretically Sound*: **ThinkD** always maintains unbiased estimates.

Datasets
========================
The download links for the datasets used in the paper are [here](http://www.cs.cmu.edu/~kijungs/codes/thinkd/)

Building and Running ThinkD
========================
Please see [User Guide](user_guide.pdf)

Running Demo
========================
For demo, please type 'make'

Reference
========================
If you use this code as part of any published research, please acknowledge the following paper.
```
@inproceedings{shin2018think,
  author       = {Shin, Kijung and Kim, Jisu and Hooi, Bryan and Faloutsos, Christos},
  title        = {Think before You Discard: Accurate Triangle Counting in Graph Streams with Deletions},
  booktitle    = {ECML/PKDD},
  year         = {2018},
  publisher    = {Springer}
}
```
