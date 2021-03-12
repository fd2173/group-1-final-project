package numberlist.objectlist;

import java.io.Serializable;

/**
 *
 * @author crocker
 */
public class Temperature implements Copiable, Comparable<Temperature>, Serializable {

    //fields
    private double value;
    private char unit;

    /**
     * Default constructor. Creates a new Temperature object. Sets value to 0
     * and unit to Fahrenheit 'F'.
     */
    public Temperature() {
        value = 0;
        unit = 'F';
    }

    /**
     * Full constructor. Creates a new Temperature object.
     *
     * @param value the numeric temperature value
     * @param unit the temperature scale used
     */
    public Temperature(double value, char unit) {
        this.value = value;
        this.unit = unit;
    }

    /**
     * Provides access to the numeric value of a Temperature object.
     *
     * @return the numeric value of the temperature object
     */
    public double getValue() {
        return value;
    }

    /**
     * Provides access to the temperature unit of a Temperature object.
     *
     * @return the temperature unit of the temperature object
     */
    public char getUnit() {
        return unit;
    }

    /**
     * Adds the current and specified Temperature objects together, and stores
     * the sum in a new Temperature object. If the calling Temperature object
     * and the specified object have different units, the specified object is
     * converted to match that of the calling object.
     *
     * @param other the specified Temperature object to add
     * @return the Temperature object containing the results of this addition
     */
    public Temperature add(Temperature other) {
        Temperature temp = new Temperature(this.value, this.unit);
        if (temp.getUnit() != other.getUnit()) {
            other = other.convertTo(temp.getUnit());
        }
        double newValue = temp.getValue() + other.getValue();
        Temperature added = new Temperature(newValue, this.unit);
        return added;
    }

    /**
     * Subtracts the specified Temperature object from the current, and stores
     * the result in a new Temperature object. If the calling Temperature object
     * and the specified object have different units, the specified object is
     * converted to match that of the calling object.
     *
     * @param other the specified Temperature object to subtract
     * @return the Temperature object containing the results of this subtraction
     */
    public Temperature subtract(Temperature other) {
        Temperature temp = new Temperature(this.value, this.unit);
        if (temp.getUnit() != other.getUnit()) {
            other = other.convertTo(temp.getUnit());
        }
        double newValue = temp.getValue() - other.getValue();
        Temperature added = new Temperature(newValue, this.unit);
        return added;
    }

    public Temperature divide(int divisor) {
        double result = value / divisor;
        return new Temperature(result, unit);
    }

    /**
     * Uses unit conversion to convert a Temperature object of one scale to a
     * Temperature object of another scale by calling the appropriate conversion
     * method.
     *
     * @param unit the temperature scale the calling object is converted to
     * @return the Temperature object mathematically equivalent in the specified
     * scale
     */
    public Temperature convertTo(char unit) {
        Temperature temp = new Temperature(this.value, this.unit);
        if (unit == 'F' && temp.getUnit() != 'F') {
            if (temp.getUnit() == 'C') {
                return convertCelsiusAndFahrenheit(temp);
            } else {
                return convertKelvinAndFahrenheit(temp);
            }
        } else if (unit == 'C' && temp.getUnit() != 'C') {
            if (temp.getUnit() == 'F') {
                return convertCelsiusAndFahrenheit(temp);
            } else {
                return convertKelvinAndCelsius(temp);
            }
        } else if (unit == 'K' && temp.getUnit() != 'K') {
            if (temp.getUnit() == 'F') {
                return convertKelvinAndFahrenheit(temp);
            } else {
                return convertKelvinAndCelsius(temp);
            }
        } else {
            return temp;
        }
    }

    /**
     * Converts a Temperature object on a Celsius scale into the equivalent
     * value on a Fahrenheit scale.
     *
     * @param temp the Temperature object in Celsius
     * @return the equivalent value in Fahrenheit
     */
    public Temperature convertCelsiusAndFahrenheit(Temperature temp) {
        Temperature newTemp;
        if (temp.getUnit() == 'F') {
            double newValue = (5.0 / 9.0) * (temp.getValue() - 32);
            newTemp = new Temperature(newValue, 'C');
            return newTemp;
        } else {
            double newValue = ((temp.getValue() * 9.0 / 5.0)) + 32;
            newTemp = new Temperature(newValue, 'F');
            return newTemp;
        }
    }

    /**
     * Converts a Temperature object on a Kelvin scale into the equivalent value
     * on a Fahrenheit scale.
     *
     * @param temp the Temperature object in Kelvin
     * @return the equivalent value in Fahrenheit
     */
    public Temperature convertKelvinAndFahrenheit(Temperature temp) {
        Temperature newTemp;
        if (temp.getUnit() == 'F') {
            double newValue = ((5.0 / 9.0) * (temp.getValue() - 32)) + 273.15;
            newTemp = new Temperature(newValue, 'K');
            return newTemp;
        } else {
            double newValue = (temp.getValue() * (9.0 / 5.0)) - 459.67;
            //double newValue = ((9.0 / 5.0) * (temp.getValue() - 273)) + 32;
            newTemp = new Temperature(newValue, 'F');
            return newTemp;
        }
    }

    /**
     * Converts a Temperature object on a Kelvin scale into the equivalent value
     * on a Celsius scale.
     *
     * @param temp the Temperature object in Kelvin
     * @return the equivalent value in Celsius
     */
    public Temperature convertKelvinAndCelsius(Temperature temp) {
        Temperature newTemp;
        if (temp.getUnit() == 'C') {
            double newValue = temp.getValue() + 273.15;
            newTemp = new Temperature(newValue, 'K');
            return newTemp;
        } else {
            double newValue = temp.getValue() - 273.15;
            newTemp = new Temperature(newValue, 'C');
            return newTemp;
        }
    }

    /**
     * This method overrides the hashCode method due to the use of the
     * overridden Object equals method. This method determines if two equal
     * objects return the same hash code.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.value)
                ^ (Double.doubleToLongBits(this.value) >>> 32));
        hash = 83 * hash + this.unit;
        return hash;
    }

    /**
     * Tests whether two Temperature objects are equal. This method determines
     * if two distinct Temperature objects have both the same value and unit
     * scale.
     *
     * @param obj the Temperature to evaluate in comparison
     * @return true if the objects have identical content, else false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Temperature other = (Temperature) obj;
        if (this.value != other.value) {
            return false;
        }
        if (this.unit != other.unit) {
            return false;
        }
        return true;
    }

    /**
     * Provides a string representation of the current Temperature object in
     * whole number form. The value is rounded to the closest integer value. If
     * the fractional portion of the value is greater than or equal to 0.5, the
     * value is rounded up. Otherwise, it is rounded down. Fahrenheit and
     * Celsius values are displayed with a degree symbol. Kelvin values are
     * displayed without a degree symbol.
     *
     * @return the string representation of the Temperature object
     */
    @Override
    public String toString() {
        if (this.unit != 'K') {
            return (int) Math.round(this.value) + "\u00B0" + " " + this.unit;
        } else {
            return (int) Math.round(this.value) + " " + this.unit;
        }
    }

    /**
     * Returns a deep copy of a Temperature object. Implements the copy() method
     * from the Copiable interface.
     *
     * @return a deep copy of the Temperature object
     */
    @Override
    public Temperature copy() {
        Temperature t = new Temperature(this.value, this.unit);
        return t;
    }

    /**
     * Compares two Temperature objects. If the calling Temperature object and
     * the specified object have different units, the specified object is
     * converted to match that of the calling object.
     *
     * @param other Temperature value to compare to
     * @return <1 if less than, 0 if equal to, >1 if greater than
     */
    @Override
    public int compareTo(Temperature other) {
        Temperature temp = new Temperature(this.value, this.unit);
        if (temp.getUnit() != other.getUnit()) {
            other = other.convertTo(temp.getUnit());
        }
        return Double.compare(temp.getValue(), other.getValue());
    }

}
