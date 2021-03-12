package bakeryplanner;

import java.util.ArrayList;
import numberlist.IndexException;
import numberlist.objectlist.Copiable;
import numberlist.objectlist.Money;
import numberlist.objectlist.NumericArrayList;
import numberlist.objectlist.Temperature;
import numberlist.primitivelist.IntegerArrayList;

/**
 *
 * @author feny4
 * @version 03/12/21 3:58 am
 */
public class BakedGoods {

    private ArrayList<String> names;
    private IntegerArrayList batches;
    private NumericArrayList temps;
    private IntegerArrayList durations;
    private NumericArrayList costs;

    /**
     *
     */
    public BakedGoods() {
        this.names = new ArrayList<String>();
        this.batches = new IntegerArrayList();
        this.temps = new NumericArrayList();
        this.durations = new IntegerArrayList();
        this.costs = new NumericArrayList();
    }

    /**
     *
     * @param name
     * @param batches
     * @param temp
     * @param duration
     * @param cost
     */
    public void addGood(String name, int batches, Temperature temp, int duration, Money cost) {
        names.add(name);
        this.batches.add(batches);
        temps.add(temp);
        durations.add(duration);
        costs.add(cost);
    }

    /**
     *
     * @param index
     * @param name
     * @param batches
     * @param temp
     * @param duration
     * @param cost
     * @throws IndexException
     */
    public void updateGood(int index, String name, int batches, Temperature temp, int duration, Money cost) throws IndexException {
        names.set(index, name);
        this.batches.set(index, batches);
        temps.set(index, temp);
        durations.set(index, duration);
        costs.set(index, cost);
    }
    
    /**
     *
     * @param index
     * @throws IndexException
     */
    public void deleteGood(int index) throws IndexException {
        names.remove(index);
        this.batches.remove(index);
        temps.remove(index);
        durations.remove(index);
        costs.remove(index);
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getNames() {
        return names;
    }

    /**
     *
     * @return
     */
    public IntegerArrayList getBatches() {
        return batches;
    }

    /**
     *
     * @return
     */
    public NumericArrayList getTemps() {
        return temps;
    }

    /**
     *
     * @return
     */
    public IntegerArrayList getDurations() {
        return durations;
    }

    /**
     *
     * @return
     */
    public NumericArrayList getCosts() {
        return costs;
    }

    /**
     *
     */
    public void sortByName() {
        try {
            ArrayList<String> sortedList = new ArrayList<String>();
            for (int i = 1; i < names.size(); i++) {
                int position = i;
                while (position > 0 && (names.get(position - 1).compareTo(names.get(position)) > 0)) {
                    swapStringArrayList(names, position, position - 1);
                    swapIntegerArrayList(batches, position, position - 1);
                    swapNumericArrayList(temps, position, position - 1);
                    swapIntegerArrayList(durations, position, position - 1);
                    swapNumericArrayList(costs, position, position - 1);
                    position--;
                }
            }
        } catch (IndexException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     */
    public void sortByBatches() {
        try {
            for (int i = 1; i < batches.getCount(); i++) {
                int position = i;
                while (position > 0 && (batches.getValue(position - 1) > (batches.getValue(position)))) {
                    swapStringArrayList(names, position, position - 1);
                    swapIntegerArrayList(batches, position, position - 1);
                    swapNumericArrayList(temps, position, position - 1);
                    swapIntegerArrayList(durations, position, position - 1);
                    swapNumericArrayList(costs, position, position - 1);
                    position--;
                }
            }
        } catch (IndexException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     */
    public void sortByTemps() {
        try {
            for (int i = 1; i < temps.getCount(); i++) {
                int position = i;
                while (position > 0 && ((Temperature) temps.getValue(position - 1)).compareTo((Temperature) temps.getValue(position)) > 0) {
                    swapStringArrayList(names, position, position - 1);
                    swapIntegerArrayList(batches, position, position - 1);
                    swapNumericArrayList(temps, position, position - 1);
                    swapIntegerArrayList(durations, position, position - 1);
                    swapNumericArrayList(costs, position, position - 1);
                    position--;
                }
            }
        } catch (IndexException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     */
    public void sortByDuration() {
        try {
            for (int i = 1; i < durations.getCount(); i++) {
                int position = i;
                while (position > 0 && (durations.getValue(position - 1) > (durations.getValue(position)))) {
                    swapStringArrayList(names, position, position - 1);
                    swapIntegerArrayList(batches, position, position - 1);
                    swapNumericArrayList(temps, position, position - 1);
                    swapIntegerArrayList(durations, position, position - 1);
                    swapNumericArrayList(costs, position, position - 1);
                    position--;
                }
            }
        } catch (IndexException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     */
    public void sortByCosts() {
        try {
            for (int i = 1; i < costs.getCount(); i++) {
                int position = i;
                while (position > 0 && ((Money) costs.getValue(position - 1)).compareTo((Money) costs.getValue(position)) > 0) {
                    swapStringArrayList(names, position, position - 1);
                    swapIntegerArrayList(batches, position, position - 1);
                    swapNumericArrayList(temps, position, position - 1);
                    swapIntegerArrayList(durations, position, position - 1);
                    swapNumericArrayList(costs, position, position - 1);
                    position--;
                }
            }
        } catch (IndexException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param names
     * @param i
     * @param j
     */
    public void swapStringArrayList(ArrayList<String> names, int i, int j) {
        String temp = names.get(i);
        names.set(i, names.get(j));
        names.set(j, (temp));
    }

    /**
     *
     * @param list
     * @param i
     * @param j
     * @throws IndexException
     */
    public void swapNumericArrayList(NumericArrayList list, int i, int j) throws IndexException {
        Copiable temp = list.getValue(i);
        list.set(i, list.getValue(j));
        list.set(j, (temp));
    }

    /**
     *
     * @param list
     * @param i
     * @param j
     * @throws IndexException
     */
    public void swapIntegerArrayList(IntegerArrayList list, int i, int j) throws IndexException {
        long temp = list.getValue(i);
        list.set(i, list.getValue(j));
        list.set(j, (temp));
    }

    /**
     *
     * @return
     */
    public Money totalCost() {
        Money total = new Money(0, (byte) 0);
        try {
            for (int i = 0; i < costs.getCount(); i++) {
                total = total.add((Money) costs.getValue(i));
            }
        } catch (IndexException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    /**
     *
     * @return
     */
    public Money calcAveCost() {
        Money averageCost = this.totalCost().divide(costs.getCount());
        return averageCost;
    }

    /**
     *
     * @return
     */
    public Temperature totalTemp() {
        Temperature total = new Temperature();
        try {
            Temperature temp = (Temperature) temps.getValue(0);
            total = new Temperature(0, temp.getUnit());
            for (int i = 0; i < temps.getCount(); i++) {
                total = total.add((Temperature) temps.getValue(i));
            }
        } catch (IndexException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    /**
     *
     * @return
     */
    public Temperature calcAveTemp() {
        Temperature averageTemp = this.totalTemp().divide(temps.getCount());
        return averageTemp;
    }

    
    public int getCount() {
        int count = names.size();
        return count;
    }
}
