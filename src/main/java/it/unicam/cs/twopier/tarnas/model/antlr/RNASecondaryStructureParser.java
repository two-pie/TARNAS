// Generated from /home/piermuz/Documenti/github/TARNAS/src/main/java/it/unicam/cs/twopier/antlr/RNASecondaryStructure.g4 by ANTLR 4.10.1

package it.unicam.cs.twopier.tarnas.model.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RNASecondaryStructureParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, INDEX=5, ZERO_INDEX=6, IUPAC_CODE=7, NUCLEOTIDE=8, 
		DBN=9, LINE1BPSEQCT=10, LINE2BPSEQCT=11, LINE3BPSEQCT=12, LINE4BPSEQCT=13, 
		LINE5CT=14, COMMENT=15, HASH=16, WS=17;
	public static final int
		RULE_rna_format = 0, RULE_aas = 1, RULE_db = 2, RULE_sequence = 3, RULE_db_structure = 4, 
		RULE_bonds = 5, RULE_bond = 6, RULE_ct = 7, RULE_ct_structure = 8, RULE_ct_line = 9, 
		RULE_bpseq = 10, RULE_bpseq_structure = 11, RULE_bpseq_line = 12, RULE_fasta = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"rna_format", "aas", "db", "sequence", "db_structure", "bonds", "bond", 
			"ct", "ct_structure", "ct_line", "bpseq", "bpseq_structure", "bpseq_line", 
			"fasta"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'", null, "'0'", null, null, null, null, 
			null, null, null, null, null, "'#'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "INDEX", "ZERO_INDEX", "IUPAC_CODE", "NUCLEOTIDE", 
			"DBN", "LINE1BPSEQCT", "LINE2BPSEQCT", "LINE3BPSEQCT", "LINE4BPSEQCT", 
			"LINE5CT", "COMMENT", "HASH", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "RNASecondaryStructure.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RNASecondaryStructureParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Rna_formatContext extends ParserRuleContext {
		public AasContext aas() {
			return getRuleContext(AasContext.class,0);
		}
		public CtContext ct() {
			return getRuleContext(CtContext.class,0);
		}
		public DbContext db() {
			return getRuleContext(DbContext.class,0);
		}
		public BpseqContext bpseq() {
			return getRuleContext(BpseqContext.class,0);
		}
		public FastaContext fasta() {
			return getRuleContext(FastaContext.class,0);
		}
		public Rna_formatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rna_format; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterRna_format(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitRna_format(this);
		}
	}

	public final Rna_formatContext rna_format() throws RecognitionException {
		Rna_formatContext _localctx = new Rna_formatContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_rna_format);
		try {
			setState(33);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				aas();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(29);
				ct();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(30);
				db();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(31);
				bpseq();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(32);
				fasta();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AasContext extends ParserRuleContext {
		public BondsContext bonds() {
			return getRuleContext(BondsContext.class,0);
		}
		public SequenceContext sequence() {
			return getRuleContext(SequenceContext.class,0);
		}
		public AasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterAas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitAas(this);
		}
	}

	public final AasContext aas() throws RecognitionException {
		AasContext _localctx = new AasContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_aas);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUCLEOTIDE) {
				{
				setState(35);
				sequence();
				}
			}

			setState(38);
			bonds();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DbContext extends ParserRuleContext {
		public Db_structureContext db_structure() {
			return getRuleContext(Db_structureContext.class,0);
		}
		public List<TerminalNode> HASH() { return getTokens(RNASecondaryStructureParser.HASH); }
		public TerminalNode HASH(int i) {
			return getToken(RNASecondaryStructureParser.HASH, i);
		}
		public TerminalNode LINE1BPSEQCT() { return getToken(RNASecondaryStructureParser.LINE1BPSEQCT, 0); }
		public TerminalNode LINE2BPSEQCT() { return getToken(RNASecondaryStructureParser.LINE2BPSEQCT, 0); }
		public TerminalNode LINE3BPSEQCT() { return getToken(RNASecondaryStructureParser.LINE3BPSEQCT, 0); }
		public TerminalNode LINE4BPSEQCT() { return getToken(RNASecondaryStructureParser.LINE4BPSEQCT, 0); }
		public SequenceContext sequence() {
			return getRuleContext(SequenceContext.class,0);
		}
		public DbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_db; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterDb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitDb(this);
		}
	}

	public final DbContext db() throws RecognitionException {
		DbContext _localctx = new DbContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_db);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==HASH) {
				{
				setState(40);
				match(HASH);
				setState(41);
				match(LINE1BPSEQCT);
				setState(42);
				match(HASH);
				setState(43);
				match(LINE2BPSEQCT);
				setState(44);
				match(HASH);
				setState(45);
				match(LINE3BPSEQCT);
				setState(46);
				match(HASH);
				setState(47);
				match(LINE4BPSEQCT);
				}
			}

			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUCLEOTIDE) {
				{
				setState(50);
				sequence();
				}
			}

			setState(53);
			db_structure();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SequenceContext extends ParserRuleContext {
		public SequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sequence; }
	 
		public SequenceContext() { }
		public void copyFrom(SequenceContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SequenceContinueContext extends SequenceContext {
		public TerminalNode NUCLEOTIDE() { return getToken(RNASecondaryStructureParser.NUCLEOTIDE, 0); }
		public SequenceContext sequence() {
			return getRuleContext(SequenceContext.class,0);
		}
		public SequenceContinueContext(SequenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterSequenceContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitSequenceContinue(this);
		}
	}
	public static class SequenceEndContext extends SequenceContext {
		public TerminalNode NUCLEOTIDE() { return getToken(RNASecondaryStructureParser.NUCLEOTIDE, 0); }
		public SequenceEndContext(SequenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterSequenceEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitSequenceEnd(this);
		}
	}

	public final SequenceContext sequence() throws RecognitionException {
		SequenceContext _localctx = new SequenceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_sequence);
		try {
			setState(58);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new SequenceContinueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				match(NUCLEOTIDE);
				setState(56);
				sequence();
				}
				break;
			case 2:
				_localctx = new SequenceEndContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				match(NUCLEOTIDE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Db_structureContext extends ParserRuleContext {
		public Db_structureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_db_structure; }
	 
		public Db_structureContext() { }
		public void copyFrom(Db_structureContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DbStructureEndContext extends Db_structureContext {
		public TerminalNode DBN() { return getToken(RNASecondaryStructureParser.DBN, 0); }
		public DbStructureEndContext(Db_structureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterDbStructureEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitDbStructureEnd(this);
		}
	}
	public static class DbStructureContinueContext extends Db_structureContext {
		public TerminalNode DBN() { return getToken(RNASecondaryStructureParser.DBN, 0); }
		public Db_structureContext db_structure() {
			return getRuleContext(Db_structureContext.class,0);
		}
		public DbStructureContinueContext(Db_structureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterDbStructureContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitDbStructureContinue(this);
		}
	}

	public final Db_structureContext db_structure() throws RecognitionException {
		Db_structureContext _localctx = new Db_structureContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_db_structure);
		try {
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new DbStructureContinueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				match(DBN);
				setState(61);
				db_structure();
				}
				break;
			case 2:
				_localctx = new DbStructureEndContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				match(DBN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BondsContext extends ParserRuleContext {
		public BondsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bonds; }
	 
		public BondsContext() { }
		public void copyFrom(BondsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BondsEndContext extends BondsContext {
		public BondContext bond() {
			return getRuleContext(BondContext.class,0);
		}
		public BondsEndContext(BondsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterBondsEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitBondsEnd(this);
		}
	}
	public static class BondsContinueContext extends BondsContext {
		public BondContext bond() {
			return getRuleContext(BondContext.class,0);
		}
		public BondsContext bonds() {
			return getRuleContext(BondsContext.class,0);
		}
		public BondsContinueContext(BondsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterBondsContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitBondsContinue(this);
		}
	}

	public final BondsContext bonds() throws RecognitionException {
		BondsContext _localctx = new BondsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_bonds);
		try {
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new BondsContinueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				bond();
				setState(66);
				match(T__0);
				setState(67);
				bonds();
				}
				break;
			case 2:
				_localctx = new BondsEndContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				bond();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BondContext extends ParserRuleContext {
		public List<TerminalNode> INDEX() { return getTokens(RNASecondaryStructureParser.INDEX); }
		public TerminalNode INDEX(int i) {
			return getToken(RNASecondaryStructureParser.INDEX, i);
		}
		public BondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterBond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitBond(this);
		}
	}

	public final BondContext bond() throws RecognitionException {
		BondContext _localctx = new BondContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_bond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__1);
			setState(73);
			match(INDEX);
			setState(74);
			match(T__2);
			setState(75);
			match(INDEX);
			setState(76);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CtContext extends ParserRuleContext {
		public TerminalNode LINE5CT() { return getToken(RNASecondaryStructureParser.LINE5CT, 0); }
		public TerminalNode LINE1BPSEQCT() { return getToken(RNASecondaryStructureParser.LINE1BPSEQCT, 0); }
		public TerminalNode LINE2BPSEQCT() { return getToken(RNASecondaryStructureParser.LINE2BPSEQCT, 0); }
		public TerminalNode LINE3BPSEQCT() { return getToken(RNASecondaryStructureParser.LINE3BPSEQCT, 0); }
		public TerminalNode LINE4BPSEQCT() { return getToken(RNASecondaryStructureParser.LINE4BPSEQCT, 0); }
		public List<TerminalNode> COMMENT() { return getTokens(RNASecondaryStructureParser.COMMENT); }
		public TerminalNode COMMENT(int i) {
			return getToken(RNASecondaryStructureParser.COMMENT, i);
		}
		public CtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterCt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitCt(this);
		}
	}

	public final CtContext ct() throws RecognitionException {
		CtContext _localctx = new CtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LINE1BPSEQCT:
				{
				{
				setState(78);
				match(LINE1BPSEQCT);
				setState(79);
				match(LINE2BPSEQCT);
				setState(80);
				match(LINE3BPSEQCT);
				setState(81);
				match(LINE4BPSEQCT);
				}
				}
				break;
			case COMMENT:
				{
				setState(83); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(82);
					match(COMMENT);
					}
					}
					setState(85); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COMMENT );
				}
				break;
			case LINE5CT:
				break;
			default:
				break;
			}
			setState(89);
			match(LINE5CT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ct_structureContext extends ParserRuleContext {
		public Ct_structureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ct_structure; }
	 
		public Ct_structureContext() { }
		public void copyFrom(Ct_structureContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CtLastContext extends Ct_structureContext {
		public Ct_lineContext ct_line() {
			return getRuleContext(Ct_lineContext.class,0);
		}
		public CtLastContext(Ct_structureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterCtLast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitCtLast(this);
		}
	}
	public static class CtSeqContext extends Ct_structureContext {
		public Ct_lineContext ct_line() {
			return getRuleContext(Ct_lineContext.class,0);
		}
		public Ct_structureContext ct_structure() {
			return getRuleContext(Ct_structureContext.class,0);
		}
		public CtSeqContext(Ct_structureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterCtSeq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitCtSeq(this);
		}
	}

	public final Ct_structureContext ct_structure() throws RecognitionException {
		Ct_structureContext _localctx = new Ct_structureContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ct_structure);
		try {
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new CtSeqContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				ct_line();
				setState(92);
				ct_structure();
				}
				break;
			case 2:
				_localctx = new CtLastContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				ct_line();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ct_lineContext extends ParserRuleContext {
		public Ct_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ct_line; }
	 
		public Ct_lineContext() { }
		public void copyFrom(Ct_lineContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CtLineUnpairedContext extends Ct_lineContext {
		public List<TerminalNode> INDEX() { return getTokens(RNASecondaryStructureParser.INDEX); }
		public TerminalNode INDEX(int i) {
			return getToken(RNASecondaryStructureParser.INDEX, i);
		}
		public TerminalNode IUPAC_CODE() { return getToken(RNASecondaryStructureParser.IUPAC_CODE, 0); }
		public List<TerminalNode> ZERO_INDEX() { return getTokens(RNASecondaryStructureParser.ZERO_INDEX); }
		public TerminalNode ZERO_INDEX(int i) {
			return getToken(RNASecondaryStructureParser.ZERO_INDEX, i);
		}
		public CtLineUnpairedContext(Ct_lineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterCtLineUnpaired(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitCtLineUnpaired(this);
		}
	}
	public static class CtLineBondContext extends Ct_lineContext {
		public List<TerminalNode> INDEX() { return getTokens(RNASecondaryStructureParser.INDEX); }
		public TerminalNode INDEX(int i) {
			return getToken(RNASecondaryStructureParser.INDEX, i);
		}
		public TerminalNode IUPAC_CODE() { return getToken(RNASecondaryStructureParser.IUPAC_CODE, 0); }
		public List<TerminalNode> ZERO_INDEX() { return getTokens(RNASecondaryStructureParser.ZERO_INDEX); }
		public TerminalNode ZERO_INDEX(int i) {
			return getToken(RNASecondaryStructureParser.ZERO_INDEX, i);
		}
		public CtLineBondContext(Ct_lineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterCtLineBond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitCtLineBond(this);
		}
	}

	public final Ct_lineContext ct_line() throws RecognitionException {
		Ct_lineContext _localctx = new Ct_lineContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ct_line);
		int _la;
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new CtLineUnpairedContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				match(INDEX);
				setState(98);
				match(IUPAC_CODE);
				setState(99);
				_la = _input.LA(1);
				if ( !(_la==INDEX || _la==ZERO_INDEX) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(100);
				_la = _input.LA(1);
				if ( !(_la==INDEX || _la==ZERO_INDEX) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(101);
				match(ZERO_INDEX);
				setState(102);
				match(INDEX);
				}
				break;
			case 2:
				_localctx = new CtLineBondContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(INDEX);
				setState(104);
				match(IUPAC_CODE);
				setState(105);
				_la = _input.LA(1);
				if ( !(_la==INDEX || _la==ZERO_INDEX) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(106);
				_la = _input.LA(1);
				if ( !(_la==INDEX || _la==ZERO_INDEX) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(107);
				match(INDEX);
				setState(108);
				match(INDEX);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BpseqContext extends ParserRuleContext {
		public Bpseq_structureContext bpseq_structure() {
			return getRuleContext(Bpseq_structureContext.class,0);
		}
		public TerminalNode COMMENT() { return getToken(RNASecondaryStructureParser.COMMENT, 0); }
		public TerminalNode LINE1BPSEQCT() { return getToken(RNASecondaryStructureParser.LINE1BPSEQCT, 0); }
		public TerminalNode LINE2BPSEQCT() { return getToken(RNASecondaryStructureParser.LINE2BPSEQCT, 0); }
		public TerminalNode LINE3BPSEQCT() { return getToken(RNASecondaryStructureParser.LINE3BPSEQCT, 0); }
		public TerminalNode LINE4BPSEQCT() { return getToken(RNASecondaryStructureParser.LINE4BPSEQCT, 0); }
		public BpseqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bpseq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterBpseq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitBpseq(this);
		}
	}

	public final BpseqContext bpseq() throws RecognitionException {
		BpseqContext _localctx = new BpseqContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_bpseq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LINE1BPSEQCT:
				{
				{
				setState(111);
				match(LINE1BPSEQCT);
				setState(112);
				match(LINE2BPSEQCT);
				setState(113);
				match(LINE3BPSEQCT);
				setState(114);
				match(LINE4BPSEQCT);
				}
				}
				break;
			case COMMENT:
				{
				setState(115);
				match(COMMENT);
				}
				break;
			case INDEX:
				break;
			default:
				break;
			}
			setState(118);
			bpseq_structure();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bpseq_structureContext extends ParserRuleContext {
		public Bpseq_structureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bpseq_structure; }
	 
		public Bpseq_structureContext() { }
		public void copyFrom(Bpseq_structureContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BpseqSeqContext extends Bpseq_structureContext {
		public Bpseq_lineContext bpseq_line() {
			return getRuleContext(Bpseq_lineContext.class,0);
		}
		public Bpseq_structureContext bpseq_structure() {
			return getRuleContext(Bpseq_structureContext.class,0);
		}
		public BpseqSeqContext(Bpseq_structureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterBpseqSeq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitBpseqSeq(this);
		}
	}
	public static class BpseqLastContext extends Bpseq_structureContext {
		public Bpseq_lineContext bpseq_line() {
			return getRuleContext(Bpseq_lineContext.class,0);
		}
		public BpseqLastContext(Bpseq_structureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterBpseqLast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitBpseqLast(this);
		}
	}

	public final Bpseq_structureContext bpseq_structure() throws RecognitionException {
		Bpseq_structureContext _localctx = new Bpseq_structureContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_bpseq_structure);
		try {
			setState(124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new BpseqSeqContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				bpseq_line();
				setState(121);
				bpseq_structure();
				}
				break;
			case 2:
				_localctx = new BpseqLastContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				bpseq_line();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bpseq_lineContext extends ParserRuleContext {
		public Bpseq_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bpseq_line; }
	 
		public Bpseq_lineContext() { }
		public void copyFrom(Bpseq_lineContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BpseqLineUnpairedContext extends Bpseq_lineContext {
		public TerminalNode INDEX() { return getToken(RNASecondaryStructureParser.INDEX, 0); }
		public TerminalNode IUPAC_CODE() { return getToken(RNASecondaryStructureParser.IUPAC_CODE, 0); }
		public TerminalNode ZERO_INDEX() { return getToken(RNASecondaryStructureParser.ZERO_INDEX, 0); }
		public BpseqLineUnpairedContext(Bpseq_lineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterBpseqLineUnpaired(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitBpseqLineUnpaired(this);
		}
	}
	public static class BpseqLineBondContext extends Bpseq_lineContext {
		public List<TerminalNode> INDEX() { return getTokens(RNASecondaryStructureParser.INDEX); }
		public TerminalNode INDEX(int i) {
			return getToken(RNASecondaryStructureParser.INDEX, i);
		}
		public TerminalNode IUPAC_CODE() { return getToken(RNASecondaryStructureParser.IUPAC_CODE, 0); }
		public BpseqLineBondContext(Bpseq_lineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterBpseqLineBond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitBpseqLineBond(this);
		}
	}

	public final Bpseq_lineContext bpseq_line() throws RecognitionException {
		Bpseq_lineContext _localctx = new Bpseq_lineContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_bpseq_line);
		try {
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new BpseqLineUnpairedContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				match(INDEX);
				setState(127);
				match(IUPAC_CODE);
				setState(128);
				match(ZERO_INDEX);
				}
				break;
			case 2:
				_localctx = new BpseqLineBondContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				match(INDEX);
				setState(130);
				match(IUPAC_CODE);
				setState(131);
				match(INDEX);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FastaContext extends ParserRuleContext {
		public SequenceContext sequence() {
			return getRuleContext(SequenceContext.class,0);
		}
		public FastaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fasta; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterFasta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitFasta(this);
		}
	}

	public final FastaContext fasta() throws RecognitionException {
		FastaContext _localctx = new FastaContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_fasta);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			sequence();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0011\u0089\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0003\u0000\"\b\u0000\u0001\u0001\u0003\u0001%\b\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00021\b\u0002"+
		"\u0001\u0002\u0003\u00024\b\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003;\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004@\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005G\b\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0004\u0007T\b\u0007\u000b\u0007"+
		"\f\u0007U\u0003\u0007X\b\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0003\b`\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\tn\b\t"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\nu\b\n\u0001\n\u0001\n"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b}\b\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0085\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0000\u0000\u000e\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u0000\u0001\u0001\u0000\u0005\u0006"+
		"\u008d\u0000!\u0001\u0000\u0000\u0000\u0002$\u0001\u0000\u0000\u0000\u0004"+
		"0\u0001\u0000\u0000\u0000\u0006:\u0001\u0000\u0000\u0000\b?\u0001\u0000"+
		"\u0000\u0000\nF\u0001\u0000\u0000\u0000\fH\u0001\u0000\u0000\u0000\u000e"+
		"W\u0001\u0000\u0000\u0000\u0010_\u0001\u0000\u0000\u0000\u0012m\u0001"+
		"\u0000\u0000\u0000\u0014t\u0001\u0000\u0000\u0000\u0016|\u0001\u0000\u0000"+
		"\u0000\u0018\u0084\u0001\u0000\u0000\u0000\u001a\u0086\u0001\u0000\u0000"+
		"\u0000\u001c\"\u0003\u0002\u0001\u0000\u001d\"\u0003\u000e\u0007\u0000"+
		"\u001e\"\u0003\u0004\u0002\u0000\u001f\"\u0003\u0014\n\u0000 \"\u0003"+
		"\u001a\r\u0000!\u001c\u0001\u0000\u0000\u0000!\u001d\u0001\u0000\u0000"+
		"\u0000!\u001e\u0001\u0000\u0000\u0000!\u001f\u0001\u0000\u0000\u0000!"+
		" \u0001\u0000\u0000\u0000\"\u0001\u0001\u0000\u0000\u0000#%\u0003\u0006"+
		"\u0003\u0000$#\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000%&\u0001"+
		"\u0000\u0000\u0000&\'\u0003\n\u0005\u0000\'\u0003\u0001\u0000\u0000\u0000"+
		"()\u0005\u0010\u0000\u0000)*\u0005\n\u0000\u0000*+\u0005\u0010\u0000\u0000"+
		"+,\u0005\u000b\u0000\u0000,-\u0005\u0010\u0000\u0000-.\u0005\f\u0000\u0000"+
		"./\u0005\u0010\u0000\u0000/1\u0005\r\u0000\u00000(\u0001\u0000\u0000\u0000"+
		"01\u0001\u0000\u0000\u000013\u0001\u0000\u0000\u000024\u0003\u0006\u0003"+
		"\u000032\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u000045\u0001\u0000"+
		"\u0000\u000056\u0003\b\u0004\u00006\u0005\u0001\u0000\u0000\u000078\u0005"+
		"\b\u0000\u00008;\u0003\u0006\u0003\u00009;\u0005\b\u0000\u0000:7\u0001"+
		"\u0000\u0000\u0000:9\u0001\u0000\u0000\u0000;\u0007\u0001\u0000\u0000"+
		"\u0000<=\u0005\t\u0000\u0000=@\u0003\b\u0004\u0000>@\u0005\t\u0000\u0000"+
		"?<\u0001\u0000\u0000\u0000?>\u0001\u0000\u0000\u0000@\t\u0001\u0000\u0000"+
		"\u0000AB\u0003\f\u0006\u0000BC\u0005\u0001\u0000\u0000CD\u0003\n\u0005"+
		"\u0000DG\u0001\u0000\u0000\u0000EG\u0003\f\u0006\u0000FA\u0001\u0000\u0000"+
		"\u0000FE\u0001\u0000\u0000\u0000G\u000b\u0001\u0000\u0000\u0000HI\u0005"+
		"\u0002\u0000\u0000IJ\u0005\u0005\u0000\u0000JK\u0005\u0003\u0000\u0000"+
		"KL\u0005\u0005\u0000\u0000LM\u0005\u0004\u0000\u0000M\r\u0001\u0000\u0000"+
		"\u0000NO\u0005\n\u0000\u0000OP\u0005\u000b\u0000\u0000PQ\u0005\f\u0000"+
		"\u0000QX\u0005\r\u0000\u0000RT\u0005\u000f\u0000\u0000SR\u0001\u0000\u0000"+
		"\u0000TU\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000UV\u0001\u0000"+
		"\u0000\u0000VX\u0001\u0000\u0000\u0000WN\u0001\u0000\u0000\u0000WS\u0001"+
		"\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000"+
		"YZ\u0005\u000e\u0000\u0000Z\u000f\u0001\u0000\u0000\u0000[\\\u0003\u0012"+
		"\t\u0000\\]\u0003\u0010\b\u0000]`\u0001\u0000\u0000\u0000^`\u0003\u0012"+
		"\t\u0000_[\u0001\u0000\u0000\u0000_^\u0001\u0000\u0000\u0000`\u0011\u0001"+
		"\u0000\u0000\u0000ab\u0005\u0005\u0000\u0000bc\u0005\u0007\u0000\u0000"+
		"cd\u0007\u0000\u0000\u0000de\u0007\u0000\u0000\u0000ef\u0005\u0006\u0000"+
		"\u0000fn\u0005\u0005\u0000\u0000gh\u0005\u0005\u0000\u0000hi\u0005\u0007"+
		"\u0000\u0000ij\u0007\u0000\u0000\u0000jk\u0007\u0000\u0000\u0000kl\u0005"+
		"\u0005\u0000\u0000ln\u0005\u0005\u0000\u0000ma\u0001\u0000\u0000\u0000"+
		"mg\u0001\u0000\u0000\u0000n\u0013\u0001\u0000\u0000\u0000op\u0005\n\u0000"+
		"\u0000pq\u0005\u000b\u0000\u0000qr\u0005\f\u0000\u0000ru\u0005\r\u0000"+
		"\u0000su\u0005\u000f\u0000\u0000to\u0001\u0000\u0000\u0000ts\u0001\u0000"+
		"\u0000\u0000tu\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000vw\u0003"+
		"\u0016\u000b\u0000w\u0015\u0001\u0000\u0000\u0000xy\u0003\u0018\f\u0000"+
		"yz\u0003\u0016\u000b\u0000z}\u0001\u0000\u0000\u0000{}\u0003\u0018\f\u0000"+
		"|x\u0001\u0000\u0000\u0000|{\u0001\u0000\u0000\u0000}\u0017\u0001\u0000"+
		"\u0000\u0000~\u007f\u0005\u0005\u0000\u0000\u007f\u0080\u0005\u0007\u0000"+
		"\u0000\u0080\u0085\u0005\u0006\u0000\u0000\u0081\u0082\u0005\u0005\u0000"+
		"\u0000\u0082\u0083\u0005\u0007\u0000\u0000\u0083\u0085\u0005\u0005\u0000"+
		"\u0000\u0084~\u0001\u0000\u0000\u0000\u0084\u0081\u0001\u0000\u0000\u0000"+
		"\u0085\u0019\u0001\u0000\u0000\u0000\u0086\u0087\u0003\u0006\u0003\u0000"+
		"\u0087\u001b\u0001\u0000\u0000\u0000\u000e!$03:?FUW_mt|\u0084";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}