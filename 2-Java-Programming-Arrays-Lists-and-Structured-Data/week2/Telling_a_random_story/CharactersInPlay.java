
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.File;
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> nameCharacter;
    private ArrayList<Integer> freqCharacter;
    
    public CharactersInPlay() {
        nameCharacter = new ArrayList<String>();
        freqCharacter = new ArrayList<Integer>();
    }
    
    private void charactersWithNumParts(int num1, int num2) {
        for (int k=0; k<nameCharacter.size(); k++) {
            int frequency = freqCharacter.get(k);
            if (frequency >= num1 && num2 >= frequency ) {
                System.out.println(frequency + "\t" + nameCharacter.get(k));
            }
        }
                        
    }
    
    public void tester() {
        findAllCharacters();
        for (int k=0; k<nameCharacter.size(); k++) {
            if (freqCharacter.get(k) > 20) {
                System.out.println(freqCharacter.get(k) + "\t" + nameCharacter.get(k));
            }
        }
        System.out.println(" ");
        charactersWithNumParts(10, 15);
    }
    
    private void findAllCharacters() {
        FileResource resource = new FileResource();
             	
        for (String line : resource.lines()) {
            int positionPeriod = line.indexOf(".");
            if (positionPeriod != -1) {
                String name = line.substring(0, positionPeriod);
                update(name);
            }
        }
        
    }
    
    private void update(String person) {
        int index = nameCharacter.indexOf(person);
        if (index == -1) {
            nameCharacter.add(person);
            freqCharacter.add(1);
        } else {
            int value = freqCharacter.get(index);
            freqCharacter.set(index, value+1);
        }
    }
    

}
