import java.lang.Math;
import java.util.Random;
class LongMultiplication{
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
            long randomNum1 = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            long randomNum2 = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            
            System.out.println(randomNum1 + " " + randomNum2);
            //int randomNumber = 10 + (int) (Math.random() * 90);

            long expectedRes = randomNum1 * randomNum2;

            String num1 = Long.toString(randomNum1);
            String num2 = Long.toString(randomNum2);

            long actualRes = Long.parseLong(multiply(num1, num2));

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

    
    public static String multiply(String num1, String num2){
        int n1 = num1.length(), n2 = num2.length();
        count += 2;

        //Since the result's size will be n1 + n2
        int[] res = new int[n1 + n2]; //This array will be initialised with the value 0
        count += 2;

        // Iterate over each digit of multiplicand (n1)
        count++;
        for(int i = n1 - 1; i >= 0; i--){
            int carry = 0;
            count++;

            int digit1 = num1.charAt(i) - '0';
            count += 2;
            
            // Iterate over each digit of multiplier (n2)
            count++;
            for(int j = n2 - 1; j >= 0; j--){
                int digit2 = num2.charAt(j) - '0';
                count += 2;
                
                //Multiply the both of the digits and add the carry
                int prod = digit1 * digit2 + res[i + j + 1] + carry;
                count += 6;
                
                //Store the unit digit in result
                res[i + j + 1] = prod % 10;
                count += 5;
                
                //Update the carry
                carry = prod / 10;
                count++;

                count += 2;
            }
            
            //Store the remaining carry in result
            res[i] += carry;
            count += 2;


            count += 2;
        }
        
        //Create a string builder to remove leading zeroes
        StringBuilder sb = new StringBuilder();
        count++;
        
        count++;
        for(int i = 0; i < res.length; i++){
            sb.append(res[i]);
            count++;

            count += 2;
        }

        while(sb.length() != 0 && sb.charAt(0) == '0'){
            count += 3;

            sb.deleteCharAt(0);
            count++;

        }
           
        
        count += 2;
        return sb.length() == 0 ? "0" : sb.toString();
    }
       
    }
