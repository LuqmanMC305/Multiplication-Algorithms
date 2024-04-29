public class Test {
    public static void main(String[] args) {
        int multiplicand = 52301;
        int multiplier = 380;
        int result = multiplyUsingSimpleAlgorithm(multiplicand, multiplier);
        System.out.println("Result: " + result);
    }

    public static int multiplyUsingSimpleAlgorithm(int multiplicand, int multiplier) {
        int result = 0;
        int shift = 0;

        // Iterate through each digit of the multiplier from right to left
        while (multiplier > 0) {
            // Extract the current digit of the multiplier
            int currentDigit = multiplier % 10;
            // Remove the last digit from the multiplier
            multiplier /= 10;

            // Calculate partial product and shift it
            int partialProduct = multiplicand * currentDigit;
            partialProduct *= (int) Math.pow(10, shift);

            // Add the partial product to the final result
            result += partialProduct;

            // Increment the shift for the next digit
            shift++;
        }

        return result;
    }
}

