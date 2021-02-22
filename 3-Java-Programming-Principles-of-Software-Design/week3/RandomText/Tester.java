
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.Random;
import java.util.ArrayList;

public class Tester {
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //                                                                                                                                                        st = "this is a test yes a testt";
        // h, e, " ", e, t
        MarkovOne markov = new MarkovOne();
        markov.setRandom(42);
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("he");
        //System.out.println(follows);
        System.out.println(follows.size());
    }
}
