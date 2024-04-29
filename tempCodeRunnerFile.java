import java.lang.Math;
import java.util.Random;

class SimpleMultiplication{
    public static long count = 0;
    public static void main(String[] args) {

        Random random = new Random();
      
        int digitLen = 1; //Initial digit length

        for(int i = 0; i < 5; i++)
        {
            //Ensure multiplier & multiplicand lengths are same when randomly generate them
            int lowerBound = (int)Math.pow(10, digitLen - 1); //If digitLen = 3, upperBound = 10^(3-1) = 100
            int upperBound = (int) Math.pow(10, digitLen) - 1;// If digitLen = 3, upperBound = (10^3) - 1 = 999
            
            //Generate two random numbers to multiply within the bound    
            int rangeSize = upperBound - lowerBound + 1;

            long randomNum1 = random.nextInt(rangeSize) + lowerBound;
            long randomNum2 = random.nextInt(rangeSize) + lowerBound;
            
            System.out.println(randomNum1 + " " + randomNum2);

            long expectedRes = randomNum1 * randomNum2;

            long actualRes = multiply(randomNum1, randomNum2);

            System.out.println("Expected Res: " + expectedRes);
            System.out.println("Actual Res: " + actualRes);

            System.out.println(checkEqual(expectedRes, actualRes) ? "CORRECT COMPARISON" : "WRONG COMPARISON");

            System.out.println();

            System.out.println("Count Operations: " + count);
            
            System.out.println();
            digitLen +=2; //Increment the digit length by 2
        }


    }

    public static boolean checkEqual(long expectedRes, long actualRes){
        return expectedRes == actualRes;
    }

    public static int countDigits(long num){
        
        if(num == 0) return 1;
        int count = 0;

        while(num != 0){
            num /= 10;
            ++count;
        }

        return count;

    }

    
    public static long multiply(long multiplicand, long multiplier){
          //Calculate num of digits of multiplier
          int numDigitsMultiplier = countDigits(multiplier);

          System.out.println(numDigitsMultiplier);

          int[] partialProds = new int[numDigitsMultiplier];

          //Iterate through each digit of multiplier
          for(int i = 0; i < numDigitsMultiplier; i++){

              //Get the current multiplier digit
              long multiplierDigit = multiplier % 10;

              //Remove the last digit of multiplier
              multiplier /= 10;

              //Initialise carry & shift values
              long carry = 0;
              long shift = i;

              //Iterate through each digit of multiplicant (Right to Left)
              for(long j = multiplicand; j > 0; j /= 10){
                  
                //Get the multiplicant current digit
                long multiplicantDigit =  j % 10;

                //Calculate the product & add carry
                long product = multiplicantDigit * multiplierDigit + carry;

                //Update carry for next iteration
                carry = product / 10;

                //Calculate partial product at current shift
                long partialProd = (product % 10) * (int)Math.pow(10, shift);

                //Add partial product to the right position
                partialProds[numDigitsMultiplier - 1 - i] += partialProd;

                 
              }

              //If there's carry, add it to the partial prod
              if(carry > 0) partialProds[numDigitsMultiplier - 1 - i] += carry *(int) Math.pow(10, shift + 1);

              //System.out.println("Partial products for (" + multiplicand + " x " + multiplicantDigit + "): " + partialProds[numDigitsMultiplier - 1 - i]);

          }

          //Sum all of the partial products
          long res = 0;

          for(long partialProduct : partialProds){
              res += partialProduct;
          }  

          return res;
        
    }
}