// Generated from java-escape by ANTLR 4.11.1

package it.unicam.cs.twopie.tarnas.model.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class RNASecondaryStructureLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, INDEX=5, ZERO_INDEX=6, LINECT=7, IUPAC_CODE=8, 
		NUCLEOTIDE=9, EDBN=10, LINE1BPSEQCT=11, LINE2BPSEQCT=12, LINE3BPSEQCT=13, 
		LINE4BPSEQCT=14, COMMENT=15, WS=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "INDEX", "ZERO_INDEX", "LINECT", "NONEWLINE", 
			"IUPAC_CODE", "NUCLEOTIDE", "EDBN_CODE", "EDBN", "LINE1BPSEQCT", "LINE2BPSEQCT", 
			"LINE3BPSEQCT", "LINE4BPSEQCT", "COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'", null, "'0'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "INDEX", "ZERO_INDEX", "LINECT", "IUPAC_CODE", 
			"NUCLEOTIDE", "EDBN", "LINE1BPSEQCT", "LINE2BPSEQCT", "LINE3BPSEQCT", 
			"LINE4BPSEQCT", "COMMENT", "WS"
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


	public RNASecondaryStructureLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RNASecondaryStructure.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0010\u00cc\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0005\u00040\b\u0004"+
		"\n\u0004\f\u00043\t\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0005\u0006"+
		"8\b\u0006\n\u0006\f\u0006;\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"K\b\u0006\u0001\u0006\u0005\u0006N\b\u0006\n\u0006\f\u0006Q\t\u0006\u0001"+
		"\u0006\u0003\u0006T\b\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\t\u0004\t]\b\t\u000b\t\f\t^\u0001\n\u0003"+
		"\nb\b\n\u0001\u000b\u0004\u000be\b\u000b\u000b\u000b\f\u000bf\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0005\fs\b\f\n\f\f\fv\t\f\u0001\f\u0003\fy\b\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0005\r\u0087\b\r\n\r\f\r\u008a\t\r\u0001\r\u0003\r\u008d\b\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0005\u000e\u009c\b\u000e\n\u000e\f\u000e\u009f\t\u000e\u0001\u000e\u0003"+
		"\u000e\u00a2\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0005\u000f\u00b0\b\u000f\n\u000f\f\u000f\u00b3\t\u000f"+
		"\u0001\u000f\u0003\u000f\u00b6\b\u000f\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u00bc\b\u0010\n\u0010\f\u0010\u00bf\t\u0010\u0001"+
		"\u0010\u0003\u0010\u00c2\b\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0004"+
		"\u0011\u00c7\b\u0011\u000b\u0011\f\u0011\u00c8\u0001\u0011\u0001\u0011"+
		"\u00079Ot\u0088\u009d\u00b1\u00bd\u0000\u0012\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\u0000\u0011"+
		"\b\u0013\t\u0015\u0000\u0017\n\u0019\u000b\u001b\f\u001d\r\u001f\u000e"+
		"!\u000f#\u0010\u0001\u0000\u0006\u0001\u000019\u0001\u000009\u0002\u0000"+
		"\n\n\r\r\r\u0000--ADGHKKMNRWYYadghkkmnrwyy\b\u0000()..<<>>A[]]a{}}\u0003"+
		"\u0000\t\n\r\r  \u00dc\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000"+
		"\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000"+
		"\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000"+
		"\u0001%\u0001\u0000\u0000\u0000\u0003\'\u0001\u0000\u0000\u0000\u0005"+
		")\u0001\u0000\u0000\u0000\u0007+\u0001\u0000\u0000\u0000\t-\u0001\u0000"+
		"\u0000\u0000\u000b4\u0001\u0000\u0000\u0000\r9\u0001\u0000\u0000\u0000"+
		"\u000fW\u0001\u0000\u0000\u0000\u0011Y\u0001\u0000\u0000\u0000\u0013\\"+
		"\u0001\u0000\u0000\u0000\u0015a\u0001\u0000\u0000\u0000\u0017d\u0001\u0000"+
		"\u0000\u0000\u0019h\u0001\u0000\u0000\u0000\u001b|\u0001\u0000\u0000\u0000"+
		"\u001d\u0090\u0001\u0000\u0000\u0000\u001f\u00a5\u0001\u0000\u0000\u0000"+
		"!\u00b9\u0001\u0000\u0000\u0000#\u00c6\u0001\u0000\u0000\u0000%&\u0005"+
		";\u0000\u0000&\u0002\u0001\u0000\u0000\u0000\'(\u0005(\u0000\u0000(\u0004"+
		"\u0001\u0000\u0000\u0000)*\u0005,\u0000\u0000*\u0006\u0001\u0000\u0000"+
		"\u0000+,\u0005)\u0000\u0000,\b\u0001\u0000\u0000\u0000-1\u0007\u0000\u0000"+
		"\u0000.0\u0007\u0001\u0000\u0000/.\u0001\u0000\u0000\u000003\u0001\u0000"+
		"\u0000\u00001/\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u00002\n\u0001"+
		"\u0000\u0000\u000031\u0001\u0000\u0000\u000045\u00050\u0000\u00005\f\u0001"+
		"\u0000\u0000\u000068\u0003\u000f\u0007\u000076\u0001\u0000\u0000\u0000"+
		"8;\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u000097\u0001\u0000\u0000"+
		"\u0000:J\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000<=\u0005E\u0000"+
		"\u0000=>\u0005N\u0000\u0000>?\u0005E\u0000\u0000?@\u0005R\u0000\u0000"+
		"@A\u0005G\u0000\u0000AK\u0005Y\u0000\u0000BC\u0005E\u0000\u0000CD\u0005"+
		"n\u0000\u0000DE\u0005e\u0000\u0000EF\u0005r\u0000\u0000FG\u0005g\u0000"+
		"\u0000GK\u0005y\u0000\u0000HI\u0005d\u0000\u0000IK\u0005G\u0000\u0000"+
		"J<\u0001\u0000\u0000\u0000JB\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000"+
		"\u0000KO\u0001\u0000\u0000\u0000LN\t\u0000\u0000\u0000ML\u0001\u0000\u0000"+
		"\u0000NQ\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000OM\u0001\u0000"+
		"\u0000\u0000PS\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000RT\u0005"+
		"\r\u0000\u0000SR\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000TU\u0001"+
		"\u0000\u0000\u0000UV\u0005\n\u0000\u0000V\u000e\u0001\u0000\u0000\u0000"+
		"WX\b\u0002\u0000\u0000X\u0010\u0001\u0000\u0000\u0000YZ\u0007\u0003\u0000"+
		"\u0000Z\u0012\u0001\u0000\u0000\u0000[]\u0003\u0011\b\u0000\\[\u0001\u0000"+
		"\u0000\u0000]^\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000^_\u0001"+
		"\u0000\u0000\u0000_\u0014\u0001\u0000\u0000\u0000`b\u0007\u0004\u0000"+
		"\u0000a`\u0001\u0000\u0000\u0000b\u0016\u0001\u0000\u0000\u0000ce\u0003"+
		"\u0015\n\u0000dc\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fd\u0001"+
		"\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000g\u0018\u0001\u0000\u0000"+
		"\u0000hi\u0005F\u0000\u0000ij\u0005i\u0000\u0000jk\u0005l\u0000\u0000"+
		"kl\u0005e\u0000\u0000lm\u0005n\u0000\u0000mn\u0005a\u0000\u0000no\u0005"+
		"m\u0000\u0000op\u0005e\u0000\u0000pt\u0001\u0000\u0000\u0000qs\t\u0000"+
		"\u0000\u0000rq\u0001\u0000\u0000\u0000sv\u0001\u0000\u0000\u0000tu\u0001"+
		"\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000ux\u0001\u0000\u0000\u0000"+
		"vt\u0001\u0000\u0000\u0000wy\u0005\r\u0000\u0000xw\u0001\u0000\u0000\u0000"+
		"xy\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000z{\u0005\n\u0000\u0000"+
		"{\u001a\u0001\u0000\u0000\u0000|}\u0005O\u0000\u0000}~\u0005r\u0000\u0000"+
		"~\u007f\u0005g\u0000\u0000\u007f\u0080\u0005a\u0000\u0000\u0080\u0081"+
		"\u0005n\u0000\u0000\u0081\u0082\u0005i\u0000\u0000\u0082\u0083\u0005s"+
		"\u0000\u0000\u0083\u0084\u0005m\u0000\u0000\u0084\u0088\u0001\u0000\u0000"+
		"\u0000\u0085\u0087\t\u0000\u0000\u0000\u0086\u0085\u0001\u0000\u0000\u0000"+
		"\u0087\u008a\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000"+
		"\u0088\u0086\u0001\u0000\u0000\u0000\u0089\u008c\u0001\u0000\u0000\u0000"+
		"\u008a\u0088\u0001\u0000\u0000\u0000\u008b\u008d\u0005\r\u0000\u0000\u008c"+
		"\u008b\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d"+
		"\u008e\u0001\u0000\u0000\u0000\u008e\u008f\u0005\n\u0000\u0000\u008f\u001c"+
		"\u0001\u0000\u0000\u0000\u0090\u0091\u0005A\u0000\u0000\u0091\u0092\u0005"+
		"c\u0000\u0000\u0092\u0093\u0005c\u0000\u0000\u0093\u0094\u0005e\u0000"+
		"\u0000\u0094\u0095\u0005s\u0000\u0000\u0095\u0096\u0005s\u0000\u0000\u0096"+
		"\u0097\u0005i\u0000\u0000\u0097\u0098\u0005o\u0000\u0000\u0098\u0099\u0005"+
		"n\u0000\u0000\u0099\u009d\u0001\u0000\u0000\u0000\u009a\u009c\t\u0000"+
		"\u0000\u0000\u009b\u009a\u0001\u0000\u0000\u0000\u009c\u009f\u0001\u0000"+
		"\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009d\u009b\u0001\u0000"+
		"\u0000\u0000\u009e\u00a1\u0001\u0000\u0000\u0000\u009f\u009d\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a2\u0005\r\u0000\u0000\u00a1\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a4\u0005\n\u0000\u0000\u00a4\u001e\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a6\u0005C\u0000\u0000\u00a6\u00a7\u0005i\u0000\u0000\u00a7\u00a8"+
		"\u0005t\u0000\u0000\u00a8\u00a9\u0005a\u0000\u0000\u00a9\u00aa\u0005t"+
		"\u0000\u0000\u00aa\u00ab\u0005i\u0000\u0000\u00ab\u00ac\u0005o\u0000\u0000"+
		"\u00ac\u00ad\u0005n\u0000\u0000\u00ad\u00b1\u0001\u0000\u0000\u0000\u00ae"+
		"\u00b0\t\u0000\u0000\u0000\u00af\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b3"+
		"\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b1\u00af"+
		"\u0001\u0000\u0000\u0000\u00b2\u00b5\u0001\u0000\u0000\u0000\u00b3\u00b1"+
		"\u0001\u0000\u0000\u0000\u00b4\u00b6\u0005\r\u0000\u0000\u00b5\u00b4\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0005\n\u0000\u0000\u00b8 \u0001\u0000"+
		"\u0000\u0000\u00b9\u00bd\u0005#\u0000\u0000\u00ba\u00bc\t\u0000\u0000"+
		"\u0000\u00bb\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bf\u0001\u0000\u0000"+
		"\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000"+
		"\u0000\u00be\u00c1\u0001\u0000\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000"+
		"\u0000\u00c0\u00c2\u0005\r\u0000\u0000\u00c1\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c3\u00c4\u0005\n\u0000\u0000\u00c4\"\u0001\u0000\u0000\u0000\u00c5"+
		"\u00c7\u0007\u0005\u0000\u0000\u00c6\u00c5\u0001\u0000\u0000\u0000\u00c7"+
		"\u00c8\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c8"+
		"\u00c9\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca"+
		"\u00cb\u0006\u0011\u0000\u0000\u00cb$\u0001\u0000\u0000\u0000\u0014\u0000"+
		"19JOS^aftx\u0088\u008c\u009d\u00a1\u00b1\u00b5\u00bd\u00c1\u00c8\u0001"+
		"\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}