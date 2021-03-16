package bakeryplanner;

import javafx.application.Application;
import numberlist.IndexException;
import numberlist.objectlist.Money;
import numberlist.objectlist.Temperature;

/**
 * @author Octavia Stappart
 * @author Robert Crocker
 * @author Feny Dai
 * @version 03/16/21
 */
public class BakeryPlanner {

    public static void main(String[] args) {
        BakeryUI app = new BakeryUI();
        Application.launch(BakeryUI.class);
    }
}
