package it.unicam.cs.twopie.tarnas.model.utils;

import it.unicam.cs.twopie.tarnas.model.rnastructure.WeakBond;

/**
 * Region class used in the pseudo algorithm https://www.ncbi.nlm.nih.gov/pmc/articles/PMC5905660/
 */
public class Region {
    private final WeakBond weakBond;
    private int order;

    public Region(WeakBond weakBond) {
        this.weakBond = weakBond;
        this.order = 0;
    }

    public WeakBond getWeakBond() {
        return this.weakBond;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
