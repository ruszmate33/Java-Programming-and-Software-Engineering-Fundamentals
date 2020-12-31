
/**
 * Write a description of Debugging here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Debugging {
   public void findAbc(String input){
       int index = input.indexOf("abc");
       while (true){
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           String found = input.substring(index+1, index+4);
           System.out.println("index before update: " + index + ": " + found);
           //index = input.indexOf("abc",index+4);
           index = input.indexOf("abc",index+3);
           //System.out.println("index after update:" + index);
       }
   }

   public void test(){
       findAbc("abcabcabcabca");
       //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
   }
}
