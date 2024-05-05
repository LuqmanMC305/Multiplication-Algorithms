import java.util.Random;

class SimpleMultiplication {
    // Counter for operations (optional)
    public static long counter = 0;

    public static int countDigits(long num) {
        int count = 0;
        while (num != 0) {
            counter++; //Comparison

            num /= 10;
            count++;
            
            counter += 2; // 1 Division, 1 assignment

        }

        counter++; //Return 
        return count;
    }

    // Function to multiply two long integers using the simple multiplication algorithm
    public static long multiply(long multiplicand, long multiplier) {
        int numDigitsMultiplier = countDigits(multiplier);
        int numDigitsMultiplicand = countDigits(multiplicand);

        counter += 2; //Assign 2 values to 2 variables
        
        long[] partialProds = new long[numDigitsMultiplier + numDigitsMultiplicand];

        counter += 2; //Assign & addition

        // Iterate through each digit of the multiplier (Right to Left)
        counter++; //Initialise i variable
        for (int i = 0; i < numDigitsMultiplier; i++) {
            counter++; // Comparison (i < numDigitsMultiplier)

            long multiplierDigit = multiplier % 10;
            counter+=2; //Modulo & assignment

            multiplier /= 10;
            counter+=2; //Division & assignment

            // Initial carry and shift values
            long carry = 0;
            int shift = i;
            counter+=2; //Assign 2 values to 2 variables

            // Iterate through each digit of the multiplicand (Right to Left)
            counter++; // Initialise j variable
            for (long j = multiplicand; j > 0; j /= 10) {
                counter++; // Comparison (j > 0)

                long multiplicandDigit = j % 10;
                counter+=2; //Modulo & assignment


                //Print partial product of each digit
                long eachPartialProdDigit = (multiplicandDigit * multiplierDigit) % 10;
                counter+=3; //Assignment, Multiplication & Modulo

                System.out.println("\nPARTIAL PRODUCT FOR " + multiplicandDigit + " * " + multiplierDigit + " = " + eachPartialProdDigit);
                counter++; //Print

                // Calculate the product and add carry
                long product = multiplicandDigit * multiplierDigit + carry;
                counter+=3; //Multiplication, Addition & Assignment

                // Calculate carry and partial product
                carry = product / 10;
                counter+=2; //Division & assignment

                System.out.println("CARRY FOR " + multiplicandDigit + " * " + multiplierDigit +  " = " + carry); 
                counter++;

                long partialProd = (product % 10) * (long) Math.pow(10, shift); //KIV
                counter +=4; //Assignment, multiplication, casting, exponent

                // Add the partial product to the correct index in the array
                partialProds[shift] += partialProd;
                counter+=3; //Assign, Addition, Array Access

                // Move to the next shift position
                shift++;
                counter++; //Addition

                counter+= 2; // Assignment & Division for inner loop ( j /= 10)
            }

            // Add the remaining carry
            counter++; //Comparison
            if (carry > 0) {
                partialProds[shift] += carry * (long) Math.pow(10, shift);
                counter +=6; //Assignment, Multiplication, Casting, Exponent, Addition, Array Access
            }

            counter++; // Increment for outer loop (i++)
        }

        // Sum all the partial products to get the final result
        long result = 0;
        counter++; //Assign value to variable

        for (long partialProduct : partialProds) {
            counter++; //Count loop iteration
            result += partialProduct;

            counter+=2; //Assign & Addition
        }
        
        counter++; //Return a value
        return result;
    }

    // Check if the expected and actual results are equal
    public static boolean checkEqual(long expectedRes, long actualRes) {
        return expectedRes == actualRes;
    }

    public static void generateTwoNums(int digitLen, int maxIteration){       
        Random random = new Random();
        
        for(int i = 0; i < maxIteration; i++){

            System.out.println("DIGIT LENGTH: " + digitLen);

            // Ensure multiplier and multiplicand lengths are the same when randomly generating them
            long lowerBound = (long) Math.pow(10, digitLen - 1); // If digitLen = 3, lowerBound = 10^(3 - 1) = 100
            long upperBound = (long) Math.pow(10, digitLen) - 1; // If digitLen = 3, upperBound = (10^3) - 1 = 999

            //System.out.println("Range: " + (upperBound - lowerBound + 1));

            // Generate two random numbers to multiply within the bound
            long randomNum1 = lowerBound + random.nextLong((long) (upperBound - lowerBound + 1));
            long randomNum2 = lowerBound + random.nextLong((long) (upperBound - lowerBound + 1));

            // Print the randomly generated multiplicand and multiplier
            System.out.println("Multiplicand: " + randomNum1 + " Multiplier: " + randomNum2);
            
            // Calculate expected result using standard multiplication
            long expectedRes = randomNum1 * randomNum2;

            // Call the multiply function to get the actual result
            long actualRes = multiply(randomNum1, randomNum2);

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
        int MaxDigit = 9; // Max digit length is 9 to prevent overflowing

        generateTwoNums(digitLen, MaxDigit);
        
    }
}
