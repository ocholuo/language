

# Count trailing zeroes in factorial of a number

1. simple method: calculate factorial of n, then count trailing 0s in the result
   - count trailing 0s by repeatedly dividing the factorial by 10 till the remainder is 0.
   - can cause overflow for a slightly bigger numbers as factorial of a number is a big number (See factorial of 20 given in above examples)
2. The idea is to consider prime factors of a factorial n. A trailing zero is always produced by prime factors 2 and 5. 
   - count the number of 5s and 2s, task is done

n = 5: There is one 5 and 3 2s in prime factors of 5! So count of trailing 0s is 1.

```
2*3*4*5 = 120
=2*3* 2*2 *5
=2^3*3*5
```

n = 11: There are two 5s and eight 2s in prime factors of 11! So count of trailing 0s is 2.

```
2*3*4*5*6*7*8*9*10*11
=2*3* 2*2 *5* 2*3 *7* 2*2*2 * 3*3 * 2*5 * 11
=2^8*3^4*5^2*7
```

n = 25, 0s is 6.

```
2*3*4*5*6*7*8*9*10*11*12*13*14*15*16*17*18*19*20*22*22*23*24*25
=2*3* 2*2 *5* 2*3 *7* 2*2*2 * 3*3 * 2*5 * 11 * 2*2*3 * 13 * 2*7 * 3*5 * 2*2*2*2 * 17 * 2*3*3 * 19 * 2*2*5 * 11*2 * 23 * 2*2*2*3 * 5*5
=2^22 *...* 5^6
```

n = 28, 0s is 6.

```
2*3*4*5*6*7*8*9*10*11*12*13*14*15*16*17*18*19*20*22*22*23*24*25*26*27*28
=2*3* 2*2 *5* 2*3 *7* 2*2*2 * 3*3 * 2*5 * 11 * 2*2*3 * 13 * 2*7 * 3*5 * 2*2*2*2 * 17 * 2*3*3 * 19 * 2*2*5 * 11*2 * 23 * 2*2*2*3 * 5*5 * 2*13 * 3*9 * 2*2*9
=2^25 *...* 5^6
```


```
Trailing 0s in n! = Count of 5s in prime factors of n!
                  = floor(n/5) + floor(n/25) + floor(n/125) + ....
```

---

## solution

```java
public int findzero(int n){
   if(n/5 != 0 && n%5 !=0)
}
```




















.