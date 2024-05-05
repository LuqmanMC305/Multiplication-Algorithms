## Multiplication Algorithms

### Simple Multiplication Algorithm

This is a multiplication algorithm that is used in schools due to its simplicity. Multiplicand are being multiplied by each digit multiplier to get partial products and carries and then add up all the properly shifted results.

The time complexity of this code is $O(n^2)$ if the lengths of both multiplicand and multiplier are the same but if they are different, it will become $O(n * m)$, where $n$ and $m$ are the lengths of multiplicand and multiplier respectively.


### Karatsuba Algorithm

This is an efficient multiplication algorithm. It uses divide and conquer method and recursion to merge the sub calculations. 

The time complexity of this algorithm is $O(n^{\log_2 3}) \approx O(n^{1.585})$ which is more efficent than $O(n^2)$. However, this algorithm can be slower than Simple Multiplication one for small values of $n$. 





