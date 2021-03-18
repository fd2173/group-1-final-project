package bakeryplanner;

import javafx.application.Application;
import numberlist.IndexException;
import numberlist.objectlist.Money;
import numberlist.objectlist.Temperature;

/**
 * This the launcher class
 *
 * @author Octavia Stappart
 * @author Robert Crocker
 * @author Feny Dai
 * @version 03/18/21
 */
public class BakeryPlanner {

    public static void main(String[] args) {
        BakeryUI app = new BakeryUI();
        Application.launch(BakeryUI.class);
    }
}
