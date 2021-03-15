package bakeryplanner;

import javafx.application.Application;
import numberlist.IndexException;
import numberlist.objectlist.Money;
import numberlist.objectlist.Temperature;

/**
 *
 * @author feny4
 */
public class BakeryPlanner {

    public static void main(String[] args) {
        BakeryUI app = new BakeryUI();
        Application.launch(BakeryUI.class);
    }
}
