
package javaapplication49;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class JavaApplication49 {

    
    public static void main(String[] args) throws FileNotFoundException {
        
         Scanner s = null;
         ArrayList<String> data = new ArrayList<>();
         
         
            try {
            s = new Scanner(new BufferedReader(new FileReader("tiny_sample_code.txt")));
            while (s.hasNext()) {
                String inputString = s.next();
                data.add(inputString);
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
        
        
        
        
        
    }
    
}
