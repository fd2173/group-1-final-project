package bakeryplanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import numberlist.IndexException;
import numberlist.objectlist.Copiable;
import numberlist.objectlist.Money;
import numberlist.objectlist.NumericArrayList;
import numberlist.objectlist.Temperature;
import numberlist.primitivelist.IntegerArrayList;

/**
 * This is the business layer of our program
 *
 * @author Octavia Stappart
 * @author Robert Crocker
 * @author Feny Dai
 * @version 03/18/21
 */
public class BakedGoods {

    private ArrayList<String> names;
    private IntegerArrayList batches;
    private NumericArrayList temps;
    private IntegerArrayList durations;
    private NumericArrayList costs;

    /**
     * Constructor
     */
    public BakedGoods() {
        this.names = new ArrayList<>();
        this.batches = new IntegerArrayList();
        this.temps = new NumericArrayList();
        this.durations = new IntegerArrayList();
        this.costs = new NumericArrayList();
        readCollection();
    }

    /**
     * Adds a new instance of BakedGoods to the end of BakedGoods collection.
     *
     * @param name the name of the baked good
     * @param batches the number of batch(es) of the baked good
     * @param temp the temperature needed for to make the baked good
     * @param duration the duration needed for to make the baked good
     * @param cost the cost of the baked good
     */
    public void addGood(String name, int batches, Temperature temp, int duration, Money cost) {
        names.add(name);
        this.batches.add(batches);
        temps.add(temp);
        durations.add(duration);
        costs.add(cost);
        writeCollection();
    }

    /**
     * Updates information of a BakedGoods instance at an index in the
     * BakedGoods collection.
     *
     * @param index the index of our array lists
     * @param name the name of the baked good
     * @param batches the number of batch(es) of the baked good
     * @param temp the temperature needed for to make the baked good
     * @param duration the duration needed for to make the baked good
     * @param cost the cost of the baked good
     * @throws IndexException
     */
    public void updateGood(int index, String name, int batches, Temperature temp, int duration, Money cost) throws IndexException {
        names.set(index, name);
        this.batches.set(index, batches);
        temps.set(index, temp);
        durations.set(index, duration);
        costs.set(index, cost);
        writeCollection();
    }

    /**
     * Deletes a baked good at an index.
     *
     * @param index the index of the BakedGoods object
     * @throws IndexException
     */
    public void deleteGood(int index) throws IndexException {
        names.remove(index);
        this.batches.remove(index);
        temps.remove(index);
        durations.remove(index);
        costs.remove(index);
        writeCollection();
    }

    /**
     * Returns a formatted string representation of a BakedGoods object. The
     * returned string has the following format displaying the object name,
     * quantity, temperature, minutes, and cost, respectively: "NAME - ##x ###°
     * , ##min. $##.##"
     *
     * @param index the index of the BakedGoods object
     * @return the string representation
     * @throws IndexException
     */
    public String getGoodString(int index) throws IndexException {
        return this.getName(index) + " - "
                + this.getBatch(index) + "x  "
                + this.getTemp(index).toString() + " , "
                + this.getDuration(index) + "min. "
                + this.getCost(index).toString();
    }

    /**
     * Returns an array of String objects containing the fields of a BakedGoods
     * object at specified indices. In order, the fields are placed one per
     * index as follows: name, batches, temperature value, temperature unit,
     * duration in minutes, cost.
     *
     * @param index the index of the BakedGoods object
     * @return the array of String objects
     * @throws IndexException
     */
    public String[] getGoodArray(int index) throws IndexException {
        String[] arr = new String[6];
        arr[0] = this.getName(index);
        arr[1] = Long.toString(this.getBatch(index));
        arr[2] = Double.toString(this.getTemp(index).getValue());
        arr[3] = Character.toString(this.getTemp(index).getUnit());
        arr[4] = Long.toString(this.getDuration(index));
        arr[5] = String.format("%.2f", (this.getCost(index).getDollars() + this.getCost(index).getCents() * (.010)));
        return arr;
    }

    /**
     * Provides access to the array of baked goods names.
     *
     * @return the array of baked goods names
     */
    public String[] getNames() {
        String[] arr = new String[this.getCount()];
        for (int i = 0; i < this.getCount(); i++) {
            arr[i] = this.getName(i);
        }
        return arr;
    }

    /**
     * Provides access to the name of a baked good at that index.
     *
     * @param index the index of the BakedGoods object
     * @return the name of the baked good at that index
     */
    public String getName(int index) {
        return names.get(index);
    }

    /**
     * Provides access to the array of the number of batch(es) of baked goods.
     *
     * @return the array of baked goods batches
     */
    public long[] getBatches() {
        long[] arr = new long[this.getCount()];
        for (int i = 0; i < this.getCount(); i++) {
            try {
                arr[i] = this.getBatch(i);
            } catch (IndexException ex) {
                Logger.getLogger(BakedGoods.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arr;
    }

    /**
     * Provides access to a baked good's batch number at that index.
     *
     * @param index the index of the BakedGoods object
     * @return the number of batch(es) the baked good at that index
     * @throws numberlist.IndexException
     */
    public long getBatch(int index) throws IndexException {
        return batches.getValue(index);
    }

    /**
     * Calculates the total batches of baked good that have the same name.
     *
     * @param name the name of the baked good
     * @return total batch(es) of baked goods with the same name
     */
    public long getTotalBatchesByName(String name) {
        long total = 0;
        try {
            for (int i = 0; i < batches.getCount(); i++) {
                if (name.equals(names.get(i))) {
                    total += batches.getValue(i);
                }
            }
        } catch (IndexException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    /**
     * Provides access to the array of temperature of baked goods.
     *
     * @return the array of baked goods temperatures
     */
    public Temperature[] getTemps() {
        Temperature[] arr = new Temperature[this.getCount()];
        for (int i = 0; i < this.getCount(); i++) {
            try {
                arr[i] = this.getTemp(i);
            } catch (IndexException ex) {
                Logger.getLogger(BakedGoods.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arr;
    }

    /**
     * Provides access to the temperature of a baked good at that index.
     *
     * @param index the index of the BakedGoods object
     * @return the temperature the baked good at that index
     * @throws numberlist.IndexException
     */
    public Temperature getTemp(int index) throws IndexException {
        return (Temperature) temps.getValue(index);
    }

    /**
     * Provides access to the array of durations of baked goods.
     *
     * @return the array of baked goods durations
     */
    public long[] getDurations() {
        long[] arr = new long[this.getCount()];
        for (int i = 0; i < this.getCount(); i++) {
            try {
                arr[i] = this.getDuration(i);
            } catch (IndexException ex) {
                Logger.getLogger(BakedGoods.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arr;
    }

    /**
     * Provides access to the duration of a baked good at that index.
     *
     * @param index the index of the BakedGoods object
     * @return the duration the baked good at that index
     * @throws numberlist.IndexException
     */
    public long getDuration(int index) throws IndexException {
        return durations.getValue(index);
    }

    /**
     * Provides access to the array of costs of baked goods.
     *
     * @return the array of baked goods costs
     */
    public Money[] getCosts() {
        Money[] arr = new Money[this.getCount()];
        for (int i = 0; i < this.getCount(); i++) {
            try {
                arr[i] = this.getCost(i);
            } catch (IndexException ex) {
                Logger.getLogger(BakedGoods.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arr;
    }

    /**
     * Provides access to the cost of a baked good at that index.
     *
     * @param index the index of the BakedGoods object
     * @return the cost the baked good at that index
     * @throws numberlist.IndexException
     */
    public Money getCost(int index) throws IndexException {
        return (Money) costs.getValue(index);
    }

    /**
     * Provides access to the count of how many baked goods there are in the
     * collection.
     *
     * @return the total of of BakedGoods objects
     */
    public int getCount() {
        int count = names.size();
        return count;
    }

    /**
     * (Insertion sorting) sorts the baked goods by name A-Z
     */
    public void sortByName() {
        try {
            ArrayList<String> sortedList = new ArrayList<>();
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
     * (Insertion sorting) sorts the baked goods by batch(es)
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
     * (Insertion sorting) sorts the baked goods by temperatures
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
     * (Insertion sorting) sorts the baked goods by durations
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
     * (Insertion sorting) sorts the baked goods by costs
     */
    public void sortByCosts() {
        try {
            for (int i = 1; i < costs.getCount(); i++) {
                int position = i;
                while (position > 0 && ((Money) costs.getValue(position - 1)).compareTo((Money) costs.getValue(position)) >= 0) {
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
     * Helper method for the sorting algorithm to swap names at index i and j.
     *
     * @param names the array list of names
     * @param i at index i
     * @param j at index j
     */
    private void swapStringArrayList(ArrayList<String> names, int i, int j) {
        String temp = names.get(i);
        names.set(i, names.get(j));
        names.set(j, (temp));
    }

    /**
     * Helper method for the sorting algorithm to swap temps and costs at index
     * i and j.
     *
     * @param list the NumericArrayList
     * @param i at index i
     * @param j at index j
     * @throws IndexException
     */
    private void swapNumericArrayList(NumericArrayList list, int i, int j) throws IndexException {
        Copiable temp = list.getValue(i);
        list.set(i, list.getValue(j));
        list.set(j, (temp));
    }

    /**
     * Helper method for the sorting algorithm to swap batches and durations at
     * index i and j.
     *
     * @param list the IntegerArrayList
     * @param i at index i
     * @param j at index j
     * @throws IndexException
     */
    private void swapIntegerArrayList(IntegerArrayList list, int i, int j) throws IndexException {
        long temp = list.getValue(i);
        list.set(i, list.getValue(j));
        list.set(j, (temp));
    }

    /**
     * Calculates the total durations of all baked goods.
     *
     * @return the total durations of all baked goods
     */
    public double totalDuration() {
        double total = 0;
        try {
            for (int i = 0; i < costs.getCount(); i++) {
                total += this.getDuration(i);
            }
        } catch (IndexException ex) {
            System.out.println(ex.getMessage());
        }
        return Math.round(total);
    }

    /**
     * Calculates the total costs of all baked goods.
     *
     * @return the total costs of all baked goods
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
     * Calculates the average costs of all baked goods.
     *
     * @return the average costs of all baked goods
     */
    public Money averageCost() {
        Money averageCost = new Money(0, (byte) 0);
        if (this.getCount() == 0) {
            return averageCost;
        }
        averageCost = this.totalCost().divide(costs.getCount());
        return averageCost;
    }

    /**
     * Calculates the average costs of baked goods that have the same name.
     *
     * @param name the name of the baked goods
     * @return the average costs of baked goods that have the same name
     */
    public Money averageCostByName(String name) {
        Money averageCost = new Money(0, (byte) 0);
        if (this.getCount() == 0) {
            return averageCost;
        }

        Money total = new Money(0, (byte) 0);
        int count = 0;
        try {
            for (int i = 0; i < costs.getCount(); i++) {
                if (name.equals(names.get(i))) {
                    total = total.add((Money) costs.getValue(i));
                    count++;
                }
            }
        } catch (IndexException ex) {
            System.out.println(ex.getMessage());
        }

        if (count == 0) {
            return total;
        }
        averageCost = total.divide(count);
        return averageCost;
    }

    /**
     * Calculates the average durations of baked goods that have the same name.
     *
     * @param name the name of the baked goods
     * @return the average duration of baked goods that have the same name
     */
    public double averageDurationByName(String name) {
        double averageDuration = 0;
        if (this.getCount() == 0) {
            return averageDuration;
        }
        double total = 0;
        int count = 0;
        try {
            for (int i = 0; i < durations.getCount(); i++) {
                if (name.equals(names.get(i))) {
                    total += durations.getValue(i);
                    count++;
                }
            }
        } catch (IndexException ex) {
            System.out.println(ex.getMessage());
        }
        if (count == 0) {
            return total;
        }
        averageDuration = total / count;
        return averageDuration;
    }

    /**
     * Calculates the total temperatures of all baked goods
     *
     * @return the total temperatures of all baked goods
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
     * Calculates the average temperatures of all baked goods
     *
     * @return the average temperatures of all baked goods
     */
    public Temperature averageTemp() {
        Temperature averageTemp = this.totalTemp().divide(temps.getCount());
        return averageTemp;
    }

    /**
     * Calculates the average temperatures of all baked goods that have the same
     * name.
     *
     * @param name the name of the baked goods
     * @return the average temperature of baked goods that have the same name
     */
    public Temperature averageTempByName(String name) {
        Temperature averageTemperature = new Temperature(0, 'F');
        if (this.getCount() == 0) {
            return averageTemperature;
        }
        Temperature total = new Temperature(0, 'F');
        int count = 0;
        try {
            for (int i = 0; i < temps.getCount(); i++) {
                if (name.equals(names.get(i))) {
                    total = total.add((Temperature) temps.getValue(i));
                    total.convertCelsiusAndFahrenheit(total);
                    count++;
                }
            }
        } catch (IndexException ex) {
            System.out.println(ex.getMessage());
        }
        if (count == 0) {
            return total;
        }
        averageTemperature = total.divide(count);
        return averageTemperature;
    }

    /**
     * Writes data to the "bakedGoods.ser" file.
     *
     * @return Boolean, success as true.
     */
    public boolean writeCollection() {
        boolean success = true;
        try (FileOutputStream fos = new FileOutputStream("bakedGoods.ser");
                ObjectOutputStream output = new ObjectOutputStream(fos)) {
            output.writeObject(names);
            output.writeObject(batches);
            output.writeObject(temps);
            output.writeObject(durations);
            output.writeObject(costs);
        } catch (Exception ex) {
            System.out.println("Cannot write to file: \n" + ex.getMessage());
            success = false;
        }
        return success;
    }

    /**
     * Reads data for the "bakedGoods.ser" file
     *
     * @return Boolean, success as true.
     */
    public boolean readCollection() {
        boolean success = true;
        File ser = new File("bakedGoods.ser");
        if (ser.exists()) {
            try (FileInputStream fis = new FileInputStream("bakedGoods.ser");
                    ObjectInputStream input = new ObjectInputStream(fis)) {
                // NOTE: MUST check if there's anything in the file before trying to read, or our Lists stop working correctly for some reason.
                ArrayList<String> tempNames = (ArrayList<String>) input.readObject();
                if (tempNames.size() > 0) {
                    names = tempNames;
                    batches = (IntegerArrayList) input.readObject();
                    temps = (NumericArrayList) input.readObject();
                    durations = (IntegerArrayList) input.readObject();
                    costs = (NumericArrayList) input.readObject();
                }
            } catch (Exception ex) {
                System.out.println("Cannot read from file: \n" + ex.getMessage());
                success = false;
            }
        }
        return success;
    }

}
