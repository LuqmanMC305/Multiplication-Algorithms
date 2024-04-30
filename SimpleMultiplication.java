import java.util.Random;

class SimpleMultiplication {
    // Counter for operations (optional)
    public static long count = 0;

    public static int countDigits(long num) {
        int count = 0;
        while (num != 0) {
            num /= 10;
            count++;
        }
        return count;
    }

    // Function to multiply two long integers using the simple multiplication algorithm
    public static long multiply(long multiplicand, long multiplier) {
        int numDigitsMultiplier = countDigits(multiplier);
        int numDigitsMultiplicand = countDigits(multiplicand);
        
        long[] partialProds = new long[numDigitsMultiplier + numDigitsMultiplicand];

        // Iterate through each digit of the multiplier (Right to Left)
        for (int i = 0; i < numDigitsMultiplier; i++) {
            long multiplierDigit = multiplier % 10;
            multiplier /= 10;

            // Initial carry and shift values
            long carry = 0;
            int shift = i;

            // Iterate through each digit of the multiplicand (Right to Left)
            for (long j = multiplicand; j > 0; j /= 10) {
                long multiplicandDigit = j % 10;

                // Calculate the product and add carry
                long product = multiplicandDigit * multiplierDigit + carry;

                // Calculate carry and partial product
                carry = product / 10;
                long partialProd = (product % 10) * (long) Math.pow(10, shift);

                // Add the partial product to the correct index in the array
                partialProds[shift] += partialProd;

                // Move to the next shift position
                shift++;
            }

            // Add the remaining carry, if any
            if (carry > 0) {
                partialProds[shift] += carry * (long) Math.pow(10, shift);
            }
        }

        // Sum all the partial products to get the final result
        long result = 0;
        for (long partialProduct : partialProds) {
            result += partialProduct;
        }

        return result;
    }

    // Check if the expected and actual results are equal
    public static boolean checkEqual(long expectedRes, long actualRes) {
        return expectedRes == actualRes;
    }

    public static void generateTwoNums(int digitLen, int maxIteration){

        Random random = new Random();
        
        for(int i = 0; i < maxIteration; i++){
            // Ensure multiplier and multiplicand lengths are the same when randomly generating them
            long lowerBound = (long) Math.pow(10, digitLen - 1); // If digitLen = 3, lowerBound = 10^(3 - 1) = 100
            long upperBound = (long) Math.pow(10, digitLen) - 1; // If digitLen = 3, upperBound = (10^3) - 1 = 999

            System.out.println("Range: " + (upperBound - lowerBound + 1));

            // Generate two random numbers to multiply within the bound
            long randomNum1 = lowerBound + random.nextLong((long) (upperBound - lowerBound + 1));
            long randomNum2 = lowerBound + random.nextLong((long) (upperBound - lowerBound + 1));

            // Print the randomly generated multiplicand and multiplier
            System.out.println(randomNum1 + " " + randomNum2);
            
            // Calculate expected result using standard multiplication
            long expectedRes = randomNum1 * randomNum2;

            // Call the multiply function to get the actual result
            long actualRes = multiply(randomNum1, randomNum2);

            // Print expected and actual results
            System.out.println("Expected Res: " + expectedRes);
            System.out.println("Actual Res: " + actualRes);

            // Check if the actual result matches the expected result
            System.out.println(checkEqual(expectedRes, actualRes) ? "CORRECT COMPARISON" : "WRONG COMPARISON");
            System.out.println();
            System.out.println("Count Operations: " + count);
            System.out.println();


            // Increment the digit length for the next iteration
            digitLen += 2;
        }
    }

    // Main function
    public static void main(String[] args) {
        
        int digitLen = 1; // Initial digit length
        int maxIteration = 5; // Max iteration (loop) of increasing their digit length by 2

        //generateTwoNums(digitLen, maxIteration);
        generateTwoNums(digitLen, maxIteration);
        
    }
}
