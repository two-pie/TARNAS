package it.unicam.cs.twopie.tarnas.model.rnastructure;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAInputFileParserException;

/**
 * Simple class whose objects contain a pair of indexes that identify a weak
 * bond in a secondary structure.
 *
 * @author Luca Tesei, Piero Hierro, Piermichele Rosati
 *
 */
public class WeakBond implements Comparable<WeakBond> {

    private final int left; // first member of the bond
    private final int right; // second member of the bond

    /**
     * Construct a pair of indexes representing the weak bond.
     *
     * @param left  left index (starting with 1)
     *
     * @param right right index (starting with 1)
     *
     * @throws RNAInputFileParserException if the left position is less than 1 or if
     *                                  the left position is greater than or equal
     *                                  to the right position.
     */
    public WeakBond(int left, int right) throws RNAInputFileParserException {
        // index checks
        if (left < 1)
            throw new RNAInputFileParserException("Weak Bond: left index: " + left + " less than 1.");
        if (left >= right)
            throw new RNAInputFileParserException(
                    "Weak Bond: left index: " + left + " greater than or equal to right index: " + right);
        // checks passed
        this.left = left;
        this.right = right;
    }

    /**
     * @return the left index
     */
    public int getLeft() {
        return left;
    }

    /**
     * @return the right index
     */
    public int getRight() {
        return right;
    }

    /**
     * Determine if this WeakBond crosses with a given other WeakBond.
     *
     * @param wb the WeakBond to check for crossing with this WeakBond
     * @return true if this WeakBond crosses with the WeakBond {@code wb}
     * @throws NullPointerException     if the passed WeakBond is null
     * @throws IllegalArgumentException if the passed WeakBond is equal to this
     *                                  WeakBond
     */
    public boolean crossesWith(WeakBond wb) {
        if (wb == null)
            throw new NullPointerException("Passed Weak Bond was null");
        if (this.equals(wb))
            throw new IllegalArgumentException("Passed Weak Bond was equal to this one");
        if (wb.left < this.left && this.left < wb.right)
            return true;
        if (this.left < wb.left && wb.left < this.right)
            return true;
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WeakBond [left=" + left + ", right=" + right + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + left;
        result = prime * result + right;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WeakBond)) {
            return false;
        }
        WeakBond other = (WeakBond) obj;
        if (left != other.left) {
            return false;
        }
        if (right != other.right) {
            return false;
        }
        return true;
    }

    /*
     * The greater the right index the greater the WeakBond.
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(WeakBond o) {
        Integer right, oright;
        right = this.right;
        oright = o.right;
        return right.compareTo(oright);
    }

}