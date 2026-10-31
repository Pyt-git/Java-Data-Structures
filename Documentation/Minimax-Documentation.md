Basic assumption: in many two-player games, assume a Max player trying to maximize the payoff and a MIN player trying to minimize it. 

Example: 

          MAX
        /     \
       3       5  
MAX chooses 5 while MIN chooses 3. 

In multi-level situations, work bottom-up instead of top-down by computing sublevel leaf outcomes first. 
Invariant: The value of a MAX node is the maximum value of its children. The value of a MIN node is their minimum. 

References: 
1. SAKOS, Iosif; "Lecture 13 - Foundations of Extensive Form Games"; November 6, 2025.
2. KROER, Christian; "Lecture Note 6: Extensive-Form Games"; February 6, 2022.
3. National Taiwan University; "ECON 4037 Chapter 2 The Extensive Form".
