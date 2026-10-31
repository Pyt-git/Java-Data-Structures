Basic assumption: in many two-player games, assume a Max player trying to maximize the payoff and a MIN player trying to minimize it. 

Example: 

          MAX
        /     \
       3       5  
MAX chooses 5 while MIN chooses 3. 

In multi-level situations, work bottom-up instead of top-down by computing sublevel leaf outcomes first. 
Invariant: The value of a MAX node is the maximum value of its children. The value of a MIN node is their minimum. 

References: 

