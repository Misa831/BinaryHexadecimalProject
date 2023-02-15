//import java.lang.*;
import java.lang.Math;

class Operation{

  public static boolean hexCheck(char[] argArray){
    if(argArray[0] != '0'){
      return false;
    }
    if(argArray[1] != 'x'){
      return false;
    }
    else{
      for(/*char c: argArray*/int i = 2; i<argArray.length; i++){
        char c = argArray[i];
        if(!Character.isDigit(c)){
          c = Character.toLowerCase(c);
          switch(c){
            case('a'):
            case('b'):
            case('c'):
            case('d'):
            case('e'):
            case('f'):
            break;
            default:
            System.out.println("Error: Non-hexadecimal Character encountered: " + c);
            System.exit(1);
        }
      }
    }
  }
  
    return true;
  }
  public static boolean decCheck(char[] argArray){
      for(char c: argArray){
        if(!Character.isDigit(c)){
          if(c=='x' || c=='b'){
            return false;
          }
          else if(c != 'b' || c!= 'x'){
          System.out.println("Error: non-digit encountered");
          System.exit(1);
          }
        }
      }
      return true;
  }
  
  public static boolean binCheck(char[] argArray){
    if(argArray[1] != 'b'){
      return false;
    }
    if(argArray[0] != '0'){
      return false;
    }
    for(int i = 2; i<argArray.length; i++){
      if(argArray[i] != '0'){
        if(argArray[i] != '1'){
          System.out.println("Error: Non-Binary digit encountered");
          System.exit(1);
        }
      }
    }
    return true;
  }

  public static void identify(String argNum){ //task 2
    char[] argNumArray = argNum.toCharArray();
    if(decCheck(argNumArray)){
      System.out.println(argNum + " = Decimal");
    }
    else if(binCheck(argNumArray)){
      System.out.println(argNum + " = Binary");
    }
    else if(hexCheck(argNumArray)){
      System.out.println(argNum + " = Hexadecimal");
    }
    else{
      System.out.println("This number is not in any identifiable format");
      System.exit(1);
    }
  }
//tested with hex, decimal, and binary, works. 
  public static int toDecimal(String argNum){
    int result = 0;
    int exponent=0;
    int place;
    int integral;
    char hexVal;
    char[]argNumArray = argNum.toCharArray();
    if(decCheck(argNumArray)){
      for(int i = argNumArray.length-1; i>=0; i--){
        place = (int)Math.pow(10,exponent);
        integral = argNumArray[i] - 48; 
        result = result + (integral * place);
        exponent++;
      }
    }
    else if(hexCheck(argNumArray)){
      for(int i = argNumArray.length-1; i>=2; i--){
        place = (int)Math.pow(16,exponent);
        integral = 0;
        if(argNumArray[i] -'0' <10){
        integral = argNumArray[i] - '0';
        }
        else{
          hexVal = argNumArray[i]; 
          hexVal=Character.toUpperCase(hexVal);
          switch(hexVal){
            case('A'):
            integral = 10;
            break;
            case('B'):
            integral = 11;
            break;
            case('C'):
            integral = 12;
            break;
            case('D'):
            integral = 13;
            break;
            case('E'):
            integral = 14;
            break;
            case('F'):
            integral = 15;
            break;
            default: 
            System.out.println("Error in hexvalue: " + hexVal);
          }
        }
        result = result + (integral * place);
        exponent++;
      }
    }
    else if(binCheck(argNumArray)){
      for(int i = argNumArray.length-1; i>=2; i--){ 
        place = (int)Math.pow(2, exponent);
        integral = argNumArray[i] - '0';
        result = result + (integral *place);
        exponent++;
      }
    }
    else{
      System.out.println("Error: Unrecognized number format");
    }
    return result;
  }

  public static String toHexidecimal(String argNum){
    int argValue = toDecimal(argNum);
    int remainder;
    String result = "";
    char[] hexValues = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    while(argValue>0){
      remainder = argValue % 16;
      result = hexValues[remainder] + result;
      argValue = argValue/16;
    }
    return "0x" + result;
  }

  public static String toBinary(String argNum){
    //need dec to bin and hex to bin.
    int argValue = toDecimal(argNum);
    int remainder; 
    String result = "";
    char[] binValues = {'0','1'};
    while(argValue>0){
      remainder = argValue%2;
      result = binValues[remainder] + result;
      argValue = argValue/2;
    }
    return "0b"+result;
  }
  public static String oneCompliment(String argNum){
    char [] binArray = argNum.toCharArray();
    for(int i = 2; i<binArray.length; i++){
      if(binArray[i] == '1'){
        binArray[i] = '0';
      }
      else if(binArray[i] == '0'){
        binArray[i] = '1';
      }
      else{
        System.out.println("Error: Number not in binary format." + binArray[i]);
      }
    }
    String result = "";
    for(int i = 0; i<binArray.length; i++){
      result = result + binArray[i]; 
    }

    return result;
  }

  public static String twoCompliment(String argNum){
    String wordToArray = oneCompliment(argNum);
    char [] twosArray = wordToArray.toCharArray();
    int lastIndex = twosArray.length-1;
    char carry = '1';
    for(int i = lastIndex; i>1; i--){
        if(carry =='1'){
          if(twosArray[i] == '0'){
            twosArray[i] = '1';
            carry = '0';
          }
          else if(twosArray[i] == '1'){
            twosArray[i] = '0';
          }
        }
    }
    String result = "";
    for(int i = 0; i<twosArray.length; i++){
      result = result + twosArray[i]; 
    }
    return result;

  }

  public static String arrayEqualizer(String numString){
   String trim = numString.substring(2,numString.length());
   while(trim.length() != 8){
    trim = "0" + trim;
   }

   
    return trim;
  }

  public static String operationXOR(String arg1, String arg2, String arg3){
    String num1ToArray = arrayEqualizer(arg1);
    String num2ToArray = arrayEqualizer(arg2);
    String num3ToArray = arrayEqualizer(arg3);

    char[] num1Array = num1ToArray.toCharArray();
    char[] num2Array = num2ToArray.toCharArray();
    char[] num3Array = num3ToArray.toCharArray();
    char[] resultArray = new char[8]; 

    //if the number of '1's among the binary numbers is odd, then result of xor is 1. 

    for(int i = num1Array.length-1; i>=0; i--){
      if(num1Array[i] == '1'){ // xor 1 - 0 - 0 is true. 
        if(num2Array[i] == '0' && num3Array[i] == '0'){
          resultArray[i] = '1';
        }
        else if(num2Array[i] == '1' && num3Array[i] == '1'){ //xor 1 - 1 - 1 is false;
          resultArray[i] = '1'; 
      }
        else if((num2Array[i] == '1' && num3Array[i] == '0')||(num2Array[i] == '0' && num3Array[i] == '1')){
          resultArray[i] = '0';
        }
      }
      else if(num1Array[i] == '0'){ 
        if(num2Array[i] == '1' && num3Array[i] == '1'){
          resultArray[i] = '0';
        }
        else if(num2Array[i] == '0' && num3Array[i] == '0'){ 
          resultArray[i] = '0';
      }
      else if(num2Array[i] == '1' && num3Array[i] == '0'||num2Array[i] == '0' && num3Array[i] == '1'){ //xor 0 - 0 - 0 is false; 
        resultArray[i] = '1';
      }
    }
    }

    String result = "";
    for(int i = 0; i<resultArray.length; i++){
      result = result + resultArray[i];
    }
   
    return result;
  }
  public static String operationOR(String arg1, String arg2, String arg3){
    String num1ToArray = arrayEqualizer(arg1);
    String num2ToArray = arrayEqualizer(arg2);
    String num3ToArray = arrayEqualizer(arg3);

    char[] num1Array = num1ToArray.toCharArray();
    char[] num2Array = num2ToArray.toCharArray();
    char[] num3Array = num3ToArray.toCharArray();
    char[] resultArray = new char[8]; 

    for(int i = num1Array.length-1; i>=0; i--){
      if(num1Array[i] == '1'){ 
          resultArray[i] = '1';
      }
      else if(num1Array[i] == '0'){ 
        if(num2Array[i] == '1' || num3Array[i] == '1'){
          resultArray[i] = '1';
        }
        else if(num2Array[i] == '0' && num3Array[i] == '0'){ 
          resultArray[i] = '0';
      }
    }
    }
    String result = "";
    for(int i = 0; i<resultArray.length; i++){
      result = result + resultArray[i];
    }
    return result;
  }


  public static String operationAND(String arg1, String arg2, String arg3){
    String num1ToArray = arrayEqualizer(arg1);
    String num2ToArray = arrayEqualizer(arg2);
    String num3ToArray = arrayEqualizer(arg3);

    char[] num1Array = num1ToArray.toCharArray();
    char[] num2Array = num2ToArray.toCharArray();
    char[] num3Array = num3ToArray.toCharArray();
    char[] resultArray = new char[8]; 


    for(int i = num1Array.length-1; i>=0; i--){
      if(num1Array[i] == '1'){ 
        if(num2Array[i] == '0' && num3Array[i] == '0'){
          resultArray[i] = '0';
        }
        else if(num2Array[i] == '1' && num3Array[i] == '1'){ //xor 1 - 1 - 1 is false;
          resultArray[i] = '1'; 
      }
        else if((num2Array[i] == '1' && num3Array[i] == '0')||(num2Array[i] == '0' && num3Array[i] == '1')){
          resultArray[i] = '0';
        }
      }
      else if(num1Array[i] == '0'){ 
        if(num2Array[i] == '1' && num3Array[i] == '1'){
          resultArray[i] = '0';
        }
        else if(num2Array[i] == '0' && num3Array[i] == '0'){ 
          resultArray[i] = '0';
      }
      else if(num2Array[i] == '1' && num3Array[i] == '0'||num2Array[i] == '0' && num3Array[i] == '1'){ //xor 0 - 0 - 0 is false; 
        resultArray[i] = '0';
      }
    }
    }


    String result = "";
    for(int i = 0; i<resultArray.length; i++){
      result = result + resultArray[i];
    }
    return result;
  }

  public static String leftShift(String arg){
    String result = arg.substring(2,arg.length());
    result = result + "00";
    return result;
  }

  public static String rightShift(String arg){
    String result = arg.substring(2, arg.length()-2);
    return result;
  }


    public static void main(String[] args) {
      String num1 = "";
      String num2 = "";
      String num3 = "";

       if(args.length != 3){
        System.out.println("Error: INVALID NUMBER OF ARGUMENTS");
            System.exit(1); //error code exit status 1, exit if no args. 
       }
       else{
        System.out.println("TASK 1");
        System.out.println("The correct Number of arguments were given.");
        num1 = args[0];
        num2 = args[1];
        num3 = args[2];
       }

        System.out.println("");
        System.out.println("TASK 2");
        identify(num1);
        identify(num2);
        identify(num3);
        System.out.println("");

        System.out.println("TASK 4");
        System.out.println("Binary Value: " + toBinary(num1));
        System.out.println("Hex Value: " + toHexidecimal(num1));
        System.out.println("Decimal: " + toDecimal(num1));
        System.out.println("");
        System.out.println("Binary Value: " + toBinary(num2));
        System.out.println("Hex Value: " + toHexidecimal(num2));
        System.out.println("Decimal: " + toDecimal(num2));
        System.out.println("");
        System.out.println("Binary Value: " + toBinary(num3));
        System.out.println("Hex Value: " + toHexidecimal(num3));
        System.out.println("Decimal: " + toDecimal(num3));

        System.out.println("");
        System.out.println("TASK 5");
        String argOneBin = toBinary(num1);
        System.out.println(num1 + " Binary: " + argOneBin + " ones compliment: " + oneCompliment(argOneBin));
        String argTwoBin = toBinary(num2);
        System.out.println(num2 + " Binary: " + argTwoBin + " ones compliment: " + oneCompliment(argTwoBin));
        String argThreeBin = toBinary(num3);
        System.out.println(num3 + " Binary: " + argThreeBin + " ones compliment: " + oneCompliment(argThreeBin));

        System.out.println("");
        System.out.println("TASK 6");
        System.out.println("Binary: " + argOneBin + " Twos compliment: " + twoCompliment(argOneBin));
        System.out.println("Binary: " + argTwoBin + " Twos compliment: " + twoCompliment(argTwoBin));
        System.out.println("Binary: " + argThreeBin + " Twos compliment: " + twoCompliment(argThreeBin));

        
        System.out.println("");
        System.out.println("TASK 7");
        System.out.println("XOR: " + operationXOR(argOneBin, argTwoBin, argThreeBin));
        System.out.println("OR: " + operationOR(argOneBin, argTwoBin, argThreeBin));
        System.out.println("AND: " + operationAND(argOneBin, argTwoBin, argThreeBin));

        System.out.println("");
        System.out.println("TASK 8");
        System.out.println("Left Shift: " + leftShift(argOneBin));
        System.out.println("Right Shift: " +  rightShift(argOneBin));

    }
}
