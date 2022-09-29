// Generated from java-escape by ANTLR 4.11.1

package it.unicam.cs.twopie.tarnas.model.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RNASecondaryStructureParser}.
 */
public interface RNASecondaryStructureListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RNASecondaryStructureParser#rna_format}.
	 * @param ctx the parse tree
	 */
	void enterRna_format(RNASecondaryStructureParser.Rna_formatContext ctx);
	/**
	 * Exit a parse tree produced by {@link RNASecondaryStructureParser#rna_format}.
	 * @param ctx the parse tree
	 */
	void exitRna_format(RNASecondaryStructureParser.Rna_formatContext ctx);
	/**
	 * Enter a parse tree produced by {@link RNASecondaryStructureParser#aas}.
	 * @param ctx the parse tree
	 */
	void enterAas(RNASecondaryStructureParser.AasContext ctx);
	/**
	 * Exit a parse tree produced by {@link RNASecondaryStructureParser#aas}.
	 * @param ctx the parse tree
	 */
	void exitAas(RNASecondaryStructureParser.AasContext ctx);
	/**
	 * Enter a parse tree produced by {@link RNASecondaryStructureParser#edbn}.
	 * @param ctx the parse tree
	 */
	void enterEdbn(RNASecondaryStructureParser.EdbnContext ctx);
	/**
	 * Exit a parse tree produced by {@link RNASecondaryStructureParser#edbn}.
	 * @param ctx the parse tree
	 */
	void exitEdbn(RNASecondaryStructureParser.EdbnContext ctx);
	/**
	 * Enter a parse tree produced by {@link RNASecondaryStructureParser#fasta}.
	 * @param ctx the parse tree
	 */
	void enterFasta(RNASecondaryStructureParser.FastaContext ctx);
	/**
	 * Exit a parse tree produced by {@link RNASecondaryStructureParser#fasta}.
	 * @param ctx the parse tree
	 */
	void exitFasta(RNASecondaryStructureParser.FastaContext ctx);
	/**
	 * Enter a parse tree produced by {@link RNASecondaryStructureParser#bpseq}.
	 * @param ctx the parse tree
	 */
	void enterBpseq(RNASecondaryStructureParser.BpseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link RNASecondaryStructureParser#bpseq}.
	 * @param ctx the parse tree
	 */
	void exitBpseq(RNASecondaryStructureParser.BpseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link RNASecondaryStructureParser#ct}.
	 * @param ctx the parse tree
	 */
	void enterCt(RNASecondaryStructureParser.CtContext ctx);
	/**
	 * Exit a parse tree produced by {@link RNASecondaryStructureParser#ct}.
	 * @param ctx the parse tree
	 */
	void exitCt(RNASecondaryStructureParser.CtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code edbnStructureContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#edbn_structure}.
	 * @param ctx the parse tree
	 */
	void enterEdbnStructureContinue(RNASecondaryStructureParser.EdbnStructureContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code edbnStructureContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#edbn_structure}.
	 * @param ctx the parse tree
	 */
	void exitEdbnStructureContinue(RNASecondaryStructureParser.EdbnStructureContinueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code edbnStructureEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#edbn_structure}.
	 * @param ctx the parse tree
	 */
	void enterEdbnStructureEnd(RNASecondaryStructureParser.EdbnStructureEndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code edbnStructureEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#edbn_structure}.
	 * @param ctx the parse tree
	 */
	void exitEdbnStructureEnd(RNASecondaryStructureParser.EdbnStructureEndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sequenceContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#sequence}.
	 * @param ctx the parse tree
	 */
	void enterSequenceContinue(RNASecondaryStructureParser.SequenceContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sequenceContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#sequence}.
	 * @param ctx the parse tree
	 */
	void exitSequenceContinue(RNASecondaryStructureParser.SequenceContinueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sequenceEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#sequence}.
	 * @param ctx the parse tree
	 */
	void enterSequenceEnd(RNASecondaryStructureParser.SequenceEndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sequenceEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#sequence}.
	 * @param ctx the parse tree
	 */
	void exitSequenceEnd(RNASecondaryStructureParser.SequenceEndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bondsContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#bonds}.
	 * @param ctx the parse tree
	 */
	void enterBondsContinue(RNASecondaryStructureParser.BondsContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bondsContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#bonds}.
	 * @param ctx the parse tree
	 */
	void exitBondsContinue(RNASecondaryStructureParser.BondsContinueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bondsEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#bonds}.
	 * @param ctx the parse tree
	 */
	void enterBondsEnd(RNASecondaryStructureParser.BondsEndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bondsEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#bonds}.
	 * @param ctx the parse tree
	 */
	void exitBondsEnd(RNASecondaryStructureParser.BondsEndContext ctx);
	/**
	 * Enter a parse tree produced by {@link RNASecondaryStructureParser#bond}.
	 * @param ctx the parse tree
	 */
	void enterBond(RNASecondaryStructureParser.BondContext ctx);
	/**
	 * Exit a parse tree produced by {@link RNASecondaryStructureParser#bond}.
	 * @param ctx the parse tree
	 */
	void exitBond(RNASecondaryStructureParser.BondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ctSeq}
	 * labeled alternative in {@link RNASecondaryStructureParser#ct_structure}.
	 * @param ctx the parse tree
	 */
	void enterCtSeq(RNASecondaryStructureParser.CtSeqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ctSeq}
	 * labeled alternative in {@link RNASecondaryStructureParser#ct_structure}.
	 * @param ctx the parse tree
	 */
	void exitCtSeq(RNASecondaryStructureParser.CtSeqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ctLast}
	 * labeled alternative in {@link RNASecondaryStructureParser#ct_structure}.
	 * @param ctx the parse tree
	 */
	void enterCtLast(RNASecondaryStructureParser.CtLastContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ctLast}
	 * labeled alternative in {@link RNASecondaryStructureParser#ct_structure}.
	 * @param ctx the parse tree
	 */
	void exitCtLast(RNASecondaryStructureParser.CtLastContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ctLineUnpaired}
	 * labeled alternative in {@link RNASecondaryStructureParser#ct_line}.
	 * @param ctx the parse tree
	 */
	void enterCtLineUnpaired(RNASecondaryStructureParser.CtLineUnpairedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ctLineUnpaired}
	 * labeled alternative in {@link RNASecondaryStructureParser#ct_line}.
	 * @param ctx the parse tree
	 */
	void exitCtLineUnpaired(RNASecondaryStructureParser.CtLineUnpairedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ctLineBond}
	 * labeled alternative in {@link RNASecondaryStructureParser#ct_line}.
	 * @param ctx the parse tree
	 */
	void enterCtLineBond(RNASecondaryStructureParser.CtLineBondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ctLineBond}
	 * labeled alternative in {@link RNASecondaryStructureParser#ct_line}.
	 * @param ctx the parse tree
	 */
	void exitCtLineBond(RNASecondaryStructureParser.CtLineBondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bpseqSeq}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseq_structure}.
	 * @param ctx the parse tree
	 */
	void enterBpseqSeq(RNASecondaryStructureParser.BpseqSeqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bpseqSeq}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseq_structure}.
	 * @param ctx the parse tree
	 */
	void exitBpseqSeq(RNASecondaryStructureParser.BpseqSeqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bpseqLast}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseq_structure}.
	 * @param ctx the parse tree
	 */
	void enterBpseqLast(RNASecondaryStructureParser.BpseqLastContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bpseqLast}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseq_structure}.
	 * @param ctx the parse tree
	 */
	void exitBpseqLast(RNASecondaryStructureParser.BpseqLastContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bpseqLineUnpaired}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseq_line}.
	 * @param ctx the parse tree
	 */
	void enterBpseqLineUnpaired(RNASecondaryStructureParser.BpseqLineUnpairedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bpseqLineUnpaired}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseq_line}.
	 * @param ctx the parse tree
	 */
	void exitBpseqLineUnpaired(RNASecondaryStructureParser.BpseqLineUnpairedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bpseqLineBond}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseq_line}.
	 * @param ctx the parse tree
	 */
	void enterBpseqLineBond(RNASecondaryStructureParser.BpseqLineBondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bpseqLineBond}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseq_line}.
	 * @param ctx the parse tree
	 */
	void exitBpseqLineBond(RNASecondaryStructureParser.BpseqLineBondContext ctx);
}