package it.unicam.cs.twopie.tarnas.model.antlr;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAInputFileParserException;
import it.unicam.cs.twopie.tarnas.model.rnastructure.RNASecondaryStructure;
import it.unicam.cs.twopie.tarnas.model.rnastructure.WeakBond;

import java.nio.file.Path;
import java.util.*;

public class RNAFileListener extends RNASecondaryStructureBaseListener {

    private RNAFile rnaFile;
    private RNASecondaryStructure s;
    private StringBuffer sequenceBuffer;
    private StringBuffer edbnsBuffer;
    private List<String> header;
    private String fileName;

    public RNAFileListener() {

    }

    public void setFileName(String fileName) {
        this.s = new RNASecondaryStructure();
        this.sequenceBuffer = new StringBuffer();
        this.edbnsBuffer = new StringBuffer();
        this.header = new ArrayList<>();
        this.fileName = fileName;
    }

    public RNAFile getRnaFile() {
        return this.rnaFile;
    }

    //BPSEQ
    @Override
    public void enterBpseq(RNASecondaryStructureParser.BpseqContext ctx) {
        if (ctx.COMMENT() != null)
            ctx.COMMENT().forEach(line -> this.header.add(line.getText().trim()));
        if (ctx.CTLINES() != null)
            this.header.addAll(Arrays.stream(ctx.CTLINES().getText().split("\n")).map(String::trim).toList());
    }

    @Override
    public void enterBpseqLineUnpaired(RNASecondaryStructureParser.BpseqLineUnpairedContext ctx) {
        // add the current nucleotide to the sequence
        this.sequenceBuffer.append(ctx.IUPAC_CODE().getText().trim());
    }

    @Override
    public void enterBpseqLineBond(RNASecondaryStructureParser.BpseqLineBondContext ctx) {
        // add the current nucleotide to the sequence
        this.sequenceBuffer.append(ctx.IUPAC_CODE().getText().trim());
        // determines the indexes of this bond
        int left = Integer.parseInt(ctx.INDEX(0).getText());
        int right = Integer.parseInt(ctx.INDEX(1).getText());
        if (left < right) {
            // only add the bond once, when it is first introduced
            this.s.addBond(new WeakBond(left, right));
        }
    }

    @Override
    public void exitBpseq(RNASecondaryStructureParser.BpseqContext ctx) {
        // assign the whole sequence to the RNASecondaryStructure
        this.s.setSequence(this.sequenceBuffer.toString());
        // set the size of the structure to the length of the sequence
        this.s.setSize(this.s.getSequence().length());
        // everything has been added to the structure, finalise it
        this.s.finalise();
        // create rnafile object
        this.rnaFile = new RNAFile(fileName, this.header, this.s, RNAFormat.BPSEQ);
    }

    //CT
    @Override
    public void enterCt(RNASecondaryStructureParser.CtContext ctx) {
        if (ctx.COMMENT() != null)
            ctx.COMMENT().forEach(line -> this.header.add(line.getText().trim()));
        if (ctx.CTLINES() != null)
            this.header.add(ctx.CTLINES().getText());
    }

    @Override
    public void enterCtLineUnpaired(RNASecondaryStructureParser.CtLineUnpairedContext ctx) {
        // add the current nucleotide to the sequence
        this.sequenceBuffer.append(ctx.IUPAC_CODE().getText().trim());
    }

    @Override
    public void enterCtLineBond(RNASecondaryStructureParser.CtLineBondContext ctx) {
        // add the current nucleotide to the sequence
        this.sequenceBuffer.append(ctx.IUPAC_CODE().getText().trim());
        // determines the indexes of this bond
        int left = Integer.parseInt(ctx.INDEX(0).getText());
        int right = Integer.parseInt(ctx.getChild(4).getText());
        if (left < right) {
            // only add the bond once, when it is first introduced
            this.s.addBond(new WeakBond(left, right));
        }
    }

    @Override
    public void exitCt(RNASecondaryStructureParser.CtContext ctx) {
        // assign the whole sequence to the RNASecondaryStructure
        this.s.setSequence(this.sequenceBuffer.toString());
        // set the size of the structure to the length of the sequence
        this.s.setSize(this.s.getSequence().length());
        // everything has been added to the structure, finalise it
        this.s.finalise();
        // create rnafile object
        this.rnaFile = new RNAFile(fileName, this.header, this.s, RNAFormat.CT);
    }

    //AAS
    @Override
    public void enterAas(RNASecondaryStructureParser.AasContext ctx) {
        ctx.COMMENT().forEach(line -> this.header.add(line.getText().trim()));
    }

    @Override
    public void enterSequenceContinue(RNASecondaryStructureParser.SequenceContinueContext ctx) {
        /*
         * add this line of nucleotides to the already existing sequence
         * because of the right recursion of the parse tree
         */
        this.sequenceBuffer.append(ctx.NUCLEOTIDE().getText());
    }

    @Override
    public void enterSequenceEnd(RNASecondaryStructureParser.SequenceEndContext ctx) {
        /*
         * add this line of nucleotides to the already existing sequence
         * because of the right recursion of the parse tree and the call to
         * the exit method
         */
        this.sequenceBuffer.append(ctx.NUCLEOTIDE().getText());
        // assign the whole sequence to the RNASecondaryStructure
        this.s.setSequence(this.sequenceBuffer.toString());
        // set the size of the structure to the length of the sequence
        this.s.setSize(this.s.getSequence().length());
    }

    @Override
    public void enterBondsContinue(RNASecondaryStructureParser.BondsContinueContext ctx) {
        // take the bond and add it to the structure
        int left = Integer.parseInt(ctx.bond().INDEX(0).getText());
        int right = Integer.parseInt(ctx.bond().INDEX(1).getText());
        this.s.addBond(new WeakBond(left, right));
    }

    @Override
    public void enterBondsEnd(RNASecondaryStructureParser.BondsEndContext ctx) {
        // take the bond and add it to the structure
        int left = Integer.parseInt(ctx.bond().INDEX(0).getText());
        int right = Integer.parseInt(ctx.bond().INDEX(1).getText());
        this.s.addBond(new WeakBond(left, right));
    }

    @Override
    public void exitAas(RNASecondaryStructureParser.AasContext ctx) {
        // everything has been added to the structure, finalise it
        this.s.finalise();
        // create rnafile object
        this.rnaFile = new RNAFile(fileName, this.header,
                this.s,
                this.s.getSequence() == null ? RNAFormat.AAS_NO_SEQUENCE : RNAFormat.AAS);
    }

    // FASTA
    @Override
    public void enterFasta(RNASecondaryStructureParser.FastaContext ctx) {
        ctx.COMMENT().forEach(line -> this.header.add(line.getText().trim()));
    }

    @Override
    public void exitFasta(RNASecondaryStructureParser.FastaContext ctx) {
        // create rnafile object
        this.rnaFile = new RNAFile(fileName, this.header, this.s, RNAFormat.FASTA);
    }

    // EDBN
    @Override
    public void enterEdbnStructureContinue(RNASecondaryStructureParser.EdbnStructureContinueContext ctx) {
        String edbn = ctx.EDBN().getText();
        /*
         * Control if this part of string has been classified wrongly as EDBN
         * while originally it was a nucleotide part with non-recognised
         * codes; in this case throw an exception
         */
        if (!edbn.contains(".")) {
            // there are no dots, check if there is at least one bracket
            if (!edbn.contains("(") && !edbn.contains(")")
                    && !edbn.contains("[") && !edbn.contains("]")
                    && !edbn.contains("{") && !edbn.contains("}")) {
                // there are no brackets, check if the string is very short
                if (edbn.length() >= 5) {
                    // ok, it is not considered edbn, the exception is thrown
                    String m = "Line " + ctx.start.getLine() + " Character "
                            + (ctx.start.getCharPositionInLine() + 1) + ": "
                            + "unrecognised nucleotide code in " + edbn;
                    throw new RNAInputFileParserException(m);
                }
            }
        }
        /*
         * add this line of edbn to the already existing ones because of the
         * right recursion of the parse tree
         */
        this.edbnsBuffer.append(edbn);
    }

    @Override
    public void enterEdbnStructureEnd(RNASecondaryStructureParser.EdbnStructureEndContext ctx) {
        var edbn = ctx.EDBN().getText();
        System.out.println("EDBN: " + edbn);
        /*
         * Control if this part of string has been classified wrongly as EDBN
         * while originally it was a nucleotide part with non-recognised
         * codes; in this case throw an exception
         */
        if (!edbn.contains(".")) {
            // there are no dots, check if there is at least one bracket
            if (!edbn.contains("(") && !edbn.contains(")")
                    && !edbn.contains("[") && !edbn.contains("]")
                    && !edbn.contains("{") && !edbn.contains("}")) {
                // there are no brackets, check if the string is very short
                if (edbn.length() >= 5) {
                    // ok, it is not considered edbn, the exception is thrown
                    String m = "Line " + ctx.start.getLine() + " Character "
                            + (ctx.start.getCharPositionInLine() + 1) + ": "
                            + "unrecognised nucleotide code in " + edbn;
                    throw new RNAInputFileParserException(m);
                }
            }
        }
        /*
         * add this line of edbn to the already existing ones because of the
         * right recursion of the parse tree
         */
        this.edbnsBuffer.append(edbn);
        // check length of edbns wrt the size of the structure
        if (this.s.getSequence() == null)
            // set the size of the structure using the length of the edbns
            this.s.setSize(this.edbnsBuffer.length());
        else // the structure has a sequence, check if the length are the same
            if (this.edbnsBuffer.length() != this.s.getSize())
                throw new RNAInputFileParserException(
                        "Extended Dot-Bracket Notation Structure is of length "
                                + this.edbnsBuffer.length()
                                + " while the sequence of nucleotides is of length "
                                + this.s.getSize());
        // parse edbn and create weak bonds in the structure
        var bonds = parseEDBN(this.edbnsBuffer.toString());
        // add all the bonds to the structure
        for (var wb : bonds)
            this.s.addBond(wb);
    }

    @Override
    public void exitEdbn(RNASecondaryStructureParser.EdbnContext ctx) {
        // create rnafile object
        this.s.finalise();
        // create rnafile object
        this.rnaFile = new RNAFile(fileName, this.header,
                this.s,
                this.s.getSequence() == null ? RNAFormat.DB_NO_SEQUENCE : RNAFormat.DB);
    }

    /*
     * Parse an Extended Dot-Bracket Notation string and transform it into a
     * list of weak bonds.
     *
     * @param extendedDotBracketNotation the string of extended dot-bracket
     * notation to convert
     *
     * @return a list of the bonds in the given extended dot-bracket notation
     *
     * @throws RNAInputFileParserException if the extended dot-bracket
     * notation contains errors
     */
    private static List<WeakBond> parseEDBN(String extendedDotBracketNotation) {
        var bonds = new ArrayList<WeakBond>();
        /*
         * Parse the edbn string using stacks to push opening symbols and
         * match them with closing ones
         */
        var stacks = new HashMap<Character, Stack<Integer>>();
        for (int i = 0; i < extendedDotBracketNotation.length(); i++) {
            var c = extendedDotBracketNotation.charAt(i);
            Character oc = c;
            if (isOpeningChar(c)) {
                if (!stacks.containsKey(oc))
                    stacks.put(oc, new Stack<>());
                stacks.get(oc).push(i);
            }
            if (isClosingChar(c)) {
                Character opening = getCorrespondingOpening(c);
                if (stacks.get(opening) == null
                        || stacks.get(opening).isEmpty()) {
                    throw new RNAInputFileParserException(
                            "Extended dot-bracket notation parsing: closing character at position "
                                    + (i + 1)
                                    + " does not have a corresponding opening character");
                }
                int leftPosition = stacks.get(opening).pop();
                // add this weak bond to bonds
                bonds.add(new WeakBond(leftPosition + 1, i + 1));
            }
            // skip the "."
        }
        // check mismatched closing symbols
        var ks = stacks.keySet();
        for (var c : ks)
            if (!stacks.get(c).isEmpty()) {
                var msg = new StringBuilder("Extended dot-bracket notation parsing: "
                        + stacks.get(c).size()
                        + " missing closing occurrence(s) of " + c
                        + " symbol, left opening symbol(s) at position(s) ");
                for (Integer i : stacks.get(c))
                    msg.append(i + 1).append(" ");
                throw new RNAInputFileParserException(msg.toString());
            }
        // return
        return bonds;
    }

    /*
     * Determine if the given character is a correct opening character of an
     * extended dot-bracket notation string.
     */
    private static boolean isOpeningChar(char c) {
        return c == '(' || c == '[' || c == '{' || c == '<'
                || Character.isUpperCase(c);
    }

    /*
     * Determine if the given character is a correct closing character of an
     * extended dot-bracket notation string.
     */
    private static boolean isClosingChar(char c) {
        return c == ')' || c == ']' || c == '}' || c == '>'
                || Character.isLowerCase(c);
    }

    /*
     * Given a closing character of an extended dot-bracket notation string,
     * returns the corresponding opening character.
     */
    private static char getCorrespondingOpening(char c) {
        return switch (c) {
            case ')' -> '(';
            case ']' -> '[';
            case '}' -> '{';
            case '>' -> '<';
            default -> Character.toUpperCase(c);
        };
    }
}