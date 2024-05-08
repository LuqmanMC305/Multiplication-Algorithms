import java.math.BigInteger;
import java.util.Random;

class SimpleMultiplicationBigInt{
    // Counter for operations (optional)
    public static long counter = 0;

    public static int countDigits(BigInteger num) {
        int count = 0;
        while (!num.equals(BigInteger.ZERO)) {
            counter++; //Comparison

            num = num.divide(BigInteger.TEN);
            count++;
            
            counter += 2; // 1 Division, 1 Assignment

        }

        counter++; //Return 
        return count;
    }

    // Function to multiply two BigIntegers using the simple multiplication algorithm
    public static BigInteger multiply(BigInteger multiplicand, BigInteger multiplier) {
        int numDigitsMultiplier = countDigits(multiplier);
        int numDigitsMultiplicand = countDigits(multiplicand);

        counter += 2; //Assign 2 values to 2 variables
        
        BigInteger[] partialProds = new BigInteger[numDigitsMultiplier + numDigitsMultiplicand];
        for (int i = 0; i < partialProds.length; i++) {
            partialProds[i] = BigInteger.ZERO;
        }

        counter += 2; //Assign & addition

        // Iterate through each digit of the multiplier (Right to Left)
        counter++; //Initialise i variable
        for (int i = 0; i < numDigitsMultiplier; i++) {
            counter++; // Comparison (i < numDigitsMultiplier)

            BigInteger multiplierDigit = multiplier.mod(BigInteger.TEN);
            counter+=2; //Modulo & assignment

            multiplier = multiplier.divide(BigInteger.TEN);
            counter+=2; //Division & assignment

            // Initial carry and shift values
            BigInteger carry = BigInteger.ZERO;
            int shift = i;
            counter+=2; //Assign 2 values to 2 variables

            // Iterate through each digit of the multiplicand (Right to Left)
            counter++; // Initialise j variable
            for (BigInteger j = multiplicand; j.compareTo(BigInteger.ZERO) > 0; j = j.divide(BigInteger.TEN)) {
                counter++; // Comparison (j > 0)

                BigInteger multiplicandDigit = j.mod(BigInteger.TEN);
                counter+=2; //Modulo & assignment


                //Print partial product of each digit
                BigInteger eachPartialProdDigit = multiplicandDigit.multiply(multiplierDigit).mod(BigInteger.TEN);
                counter+=3; //Assignment, Multiplication & Modulo

                System.out.println("\nPARTIAL PRODUCT FOR " + multiplicandDigit + " * " + multiplierDigit + " = " + eachPartialProdDigit);
                counter++; //Print

                // Calculate the product and add carry
                BigInteger product = multiplicandDigit.multiply(multiplierDigit).add(carry);
                counter+=3; //Multiplication, Addition & Assignment

                // Calculate carry and partial product
                carry = product.divide(BigInteger.TEN);
                counter+=2; //Division & assignment

                System.out.println("CARRY FOR " + multiplicandDigit + " * " + multiplierDigit +  " = " + carry); 
                counter++;

                BigInteger partialProd = product.mod(BigInteger.TEN).multiply(BigInteger.TEN.pow(shift));
                counter +=4; //Assignment, multiplication, casting, exponent

                // Add the partial product to the correct index in the array
                partialProds[shift] = partialProds[shift].add(partialProd);
                counter+=3; //Assign, Addition, Array Access

                // Move to the next shift position
                shift++;
                counter++; //Addition

                counter+= 2; // Assignment & Division for inner loop ( j /= 10)
            }

            // Add the remaining carry
            counter++; //Comparison
            if (carry.compareTo(BigInteger.ZERO) > 0) {
                partialProds[shift] = partialProds[shift].add(carry.multiply(BigInteger.TEN.pow(shift)));
                counter +=6; //Assignment, Multiplication, Casting, Exponent, Addition, Array Access
            }

            counter++; // Increment for outer loop (i++)
        }

        // Sum all the partial products to get the final result
        BigInteger result = BigInteger.ZERO;
        counter++; //Assign value to variable

        for (BigInteger partialProduct : partialProds) {
            counter++; //Count loop iteration
            result = result.add(partialProduct);

            counter+=2; //Assign & Addition
        }
        
        counter++; //Return a value
        return result;
    }

    // Check if the expected and actual results are equal
    public static boolean checkEqual(BigInteger expectedRes, BigInteger actualRes) {
        return expectedRes.equals(actualRes);
    }

    public static void generateTwoNums(int digitLen, int maxIteration){       
        Random random = new Random();
        
        for(int i = 0; i < maxIteration; i++){

            System.out.println("DIGIT LENGTH: " + digitLen);

            // Ensure multiplier and multiplicand lengths are the same when randomly generating them
            BigInteger lowerBound = BigInteger.TEN.pow(digitLen - 1); // If digitLen = 3, lowerBound = 10^(3 - 1) = 100
            BigInteger upperBound = BigInteger.TEN.pow(digitLen).subtract(BigInteger.ONE); // If digitLen = 3, upperBound = (10^3) - 1 = 999

            //System.out.println("Range: " + (upperBound - lowerBound + 1));

            // Generate two random numbers to multiply within the bound
            BigInteger randomNum1 = lowerBound.add(new BigInteger(upperBound.subtract(lowerBound).add(BigInteger.ONE).bitLength(), random));
            BigInteger randomNum2 = lowerBound.add(new BigInteger(upperBound.subtract(lowerBound).add(BigInteger.ONE).bitLength(), random));

            // Print the randomly generated multiplicand and multiplier
            System.out.println("Multiplicand: " + randomNum1 + " Multiplier: " + randomNum2);
            
            // Calculate expected result using standard multiplication
            BigInteger expectedRes = randomNum1.multiply(randomNum2);

            // Call the multiply function to get the actual result
            BigInteger actualRes = multiply(randomNum1, randomNum2);

            // Print expected and actual results
            System.out.println("\nExpected Res: " + expectedRes);
            System.out.println("Actual Res: " + actualRes);

            // Check if the actual result matches the expected result
            System.out.println(checkEqual(expectedRes, actualRes) ? "\nCORRECT COMPARISON FOR EXPECTED & ACTUAL RESULTS" : "\nWRONG COMPARISON FOR EXPECTED & ACTUAL RESULTS");
            System.out.println();
            System.out.println("COUNT OPERATIONS FOR N = " + digitLen + " IS " + counter);
            System.out.println("\n---------------------------------------------");


            // Increment the digit length by 1 for the next iteration
            digitLen += 1;
            System.out.println();
        }
    }

    // Main function
    public static void main(String[] args) {

        System.out.println();

        int digitLen = 1; // Initial digit length
        int MaxDigit = 12; // Max digit length is 9 to prevent overflowing

        generateTwoNums(digitLen, MaxDigit);
        
    }
}
