// Part 2- Karatsuba

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;

class Karatsuba
{
    public static long count = 0; // Counter for primitive operations

    public static long mult(long x, long y) // recursive approach and increments (x and y)
    {
        count++; // Increment for method call

        if (x < 10 && y < 10) 
        {
            count++; // Increment for comparison
            return x * y;
        }

        long noOneLength = numLength(x);
        long noTwoLength = numLength(y);
        count += 2; // Increment for method calls

        long maxNumLength = (long) Math.max(noOneLength, noTwoLength);
        count++; // Increment for Math.max

        long halfMaxNumLength = (maxNumLength / 2) + (maxNumLength % 2);
        count += 2; // Increment for arithmetic operations

        long maxNumLengthTen = (long) Math.pow(10, halfMaxNumLength);
        count++; // Increment for Math.pow

        long a = x / maxNumLengthTen;
        long b = x % maxNumLengthTen;
        long c = y / maxNumLengthTen;
        long d = y % maxNumLengthTen;
        count += 4; // Increment for arithmetic operations

        long z0 = mult(a, c);
        count++; // Increment for method call
        long z1 = mult(a + b, c + d);
        count += 2; // Increment for arithmetic operation and method call
        long z2 = mult(b, d);
        count++; // Increment for method call

        long ans = (z0 * (long) Math.pow(10, halfMaxNumLength * 2) +
                ((z1 - z0 - z2) * (long) Math.pow(10, halfMaxNumLength) + z2));
        count += 5; // Increment for arithmetic operations and Math.pow calls

        return ans;
    }

    public static long numLength(long n) 
    {
        long noLen = 0;
        while (n > 0) 
        {
            noLen++;
            n /= 10;
            count++; // Increment for arithmetic operation
        }
        return noLen;
    }

    public static void main(String[] args) 
    {
        int maxDigits = 19; // Maximum number of digits 19 to prevent overflowing
        List<Long> digitLengths = new ArrayList<>();      // Array
        List<Long> operationCounts = new ArrayList<>();      // // Array

        Random random = new Random();

        for (long digitLen = 1; digitLen <= maxDigits; digitLen++) {
            long lowerBound = (long) Math.pow(10, digitLen - 1);
            long upperBound = (long) Math.pow(10, digitLen) - 1;
            long rangeSize = (long) (upperBound - lowerBound + 1);

            for (int i = 0; i < 10; i++) 
            { // This part will be generate 10 random for each digit length.
                long randomNum1 = random.nextLong(rangeSize) + lowerBound;
                long randomNum2 = random.nextLong(rangeSize) + lowerBound;

                count = 0; // Reset the counter
                long actualProduct = mult(randomNum1, randomNum2);
                long expectedProduct = randomNum1 * randomNum2; //ADDED EXPECTED OUTPUT
                String output = (actualProduct == expectedProduct) ? "CORRECT COMPARISON" : "WRONG COMPARISON"; //ADDED COMPARISON

                System.out.println(output + " FOR " + randomNum1 + " * " +  randomNum2); //EDITED

                digitLengths.add(digitLen);
                operationCounts.add(count);
            }
        }

        System.out.println();

        // Use for the graph plotting purpose.
        for (int i = 0; i < digitLengths.size(); i++) 
        {
            System.out.println("Digit Length: " + digitLengths.get(i) + ", Operation Count: " + operationCounts.get(i));
        }
    }
}

/*
Output supposed be:
Digit Length: 2, Operation Count: 14
Digit Length: 4, Operation Count: 59
Digit Length: 6, Operation Count: 124
Digit Length: 8, Operation Count: 209
Digit Length: 10, Operation Count: 314
... (lines continue for higher digit lengths up to 100)
 */