
/**
 * Write a description of SelectionSort here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class SelectionSort {
    public void testSelectionSort() {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(33, 31, 5, 1, 78, 4));
        System.out.println(numbers);
        ArrayList<Integer> sortedNumbers = selectionSort(numbers);
        System.out.println(sortedNumbers);
    }
    
    public ArrayList<Integer>selectionSort(ArrayList<Integer> inList) {
        ArrayList<Integer> outList = new ArrayList<Integer>();
        
        while (!inList.isEmpty()) {
            int minIndex = getIndexOfMin(inList);
            outList.add(inList.get(minIndex));
            inList.remove(minIndex);
        }
        return outList;
    }
    
    private int getIndexOfMin(ArrayList<Integer> inList) {
        if (!inList.isEmpty()) {
            int min = inList.get(0);
            int minIndex = 0;
            for (int k=0; k<inList.size(); k++) {
                int curr = inList.get(k);
                if (curr < min) {
                    min = curr;
                    minIndex = k;
                }
            }
            return minIndex;
        }
        return -1;
    }
}
