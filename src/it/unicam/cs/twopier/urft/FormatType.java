package it.unicam.cs.twopier.urft;

public enum FormatType {
    DB_NO_SEQUENCE(0),
    DB(1),
    BPSEQ(2),
    FASTA(3),
    AAS(4),
    AAS_NO_SEQUENCE(5),
    CT(6);

    private final int index;

    FormatType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
