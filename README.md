WRS: Waiting Room Sampling for Accurate Triangle Counting in Real Graph Streams
========================

**ThinkD** is a streaming algorithm for triangle counting in a fully dynamic graph stream with edge additions and deletions. 
**ThinkD** estimates the counts of global triangles and local triangles by making a single pass over the stream. 
**ThinkD** has the following advantages: 
 * *Accurate*: **ThinkD** is up to 4.3X more accurate than its best competitors within the same memory budget
 * *Fast*: **ThinkD** is up to 2.2X faster than its best competitors for the same accuracy requirements
 * *Theoretically Sound*: **ThinkD** always maintains unbiased estimates

Datasets
========================
The download links for the datasets used in the paper are [here](http://dmlab.kaist.ac.kr/~kijungs/codes/thinkd/)

Building and Running WRS
========================
Please see [User Guide](user_guide.pdf)

Running Demo
========================
For demo, please type 'make'

Reference
========================
If you use this code as part of any published research, please acknowledge the following papers.
```
@inproceedings{shin2018think,
  title={Think before you discard: Accurate triangle counting in graph streams with deletions},
  author={Shin, Kijung and Kim, Jisu and Hooi, Bryan and Faloutsos, Christos},
  booktitle={Joint European Conference on Machine Learning and Knowledge Discovery in Databases},
  pages={141--157},
  year={2018},
  organization={Springer}
}
```
```
@article{shin2020fast,
  title={Fast, Accurate and Provable Triangle Counting in Fully Dynamic Graph Streams},
  author={Shin, Kijung and Oh, Sejoon and Kim, Jisu and Hooi, Bryan and Faloutsos, Christos},
  journal={ACM Transactions on Knowledge Discovery from Data (TKDD)},
  volume={},
  number={},
  pages={},
  year={},
  publisher={ACM}
}
```
