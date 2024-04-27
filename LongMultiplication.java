class LongMultiplication{
    public static int count = 0;
    public static void main(String[] args) {
        String num1 = "123", num2 = "456";
        System.out.println(multiply(num1, num2));

        System.out.println(count);

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
