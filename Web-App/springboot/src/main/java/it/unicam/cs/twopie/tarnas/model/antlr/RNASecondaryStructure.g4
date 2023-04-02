/*
 * ANTLR 4 grammar for reading files containing RNA Secondary Structures
 * in:
 *
 * Dot-Bracket Notation (DBN) Format  - with optional sequence of
 *                                                nucleotides
 *
 * Arc-Annotated Sequence (AAS) Format - with optional sequence of nucleotides
 *
 * Bpseq Format - with optional four initial lines
 *
 * Ct Format - with optional four initial lines
 *
 * Fasta format - with sequence of nucleotides
 *
 * @author Luca Tesei, Piero Hierro, Piermichele Rosati
 *
 */
grammar RNASecondaryStructure;

@header{
package it.unicam.cs.twopie.tarnas.model.antlr;
}

// Grammar rules
rna_format:
    aas | ct | edbn | bpseq | fasta
;

aas:
    COMMENT* sequence? bonds
;

edbn:
    COMMENT* sequence? edbn_structure
;

fasta:
    COMMENT* sequence
;

bpseq:
    (BPSEQCTLINES? | COMMENT*) bpseq_structure
;

ct:
    (BPSEQCTLINES? | COMMENT*) LINECT ct_structure
;

edbn_structure:
	EDBN edbn_structure #edbnStructureContinue
	| EDBN # edbnStructureEnd
;

sequence:
    NUCLEOTIDE sequence # sequenceContinue
    | NUCLEOTIDE # sequenceEnd
;

bonds:
	BOND SEP? bonds #bondsContinue
	| # bondsEnd
;

ct_structure:
    ct_line ct_structure # ctSeq
	| ct_line # ctLast
;

ct_line:
	INDEX                       // First column: sequence index
	NUCLEOTIDE                  // Second column: base in one-letter notation
	( ZERO_INDEX | INDEX )      // Third column: sequence indices (plus/minus one)
	( ZERO_INDEX | INDEX )      // Fourth column: sequence indices (plus/minus one)
	ZERO_INDEX                  // Fifth column: pairing partner of this base if it involved in a base pair
	INDEX # ctLineUnpaired      // Sixth column: sequence indices (plus/minus one)
	|
	INDEX
	NUCLEOTIDE
	( ZERO_INDEX | INDEX )
	( ZERO_INDEX | INDEX )
	INDEX                       // Fifth column: no pairing partner of this base if it not involved in a base pair
	INDEX # ctLineBond
;

bpseq_structure:
    bpseq_line bpseq_structure # bpseqSeq
	| bpseq_line # bpseqLast
;

bpseq_line:
	INDEX NUCLEOTIDE ZERO_INDEX # bpseqLineUnpaired
	| INDEX NUCLEOTIDE INDEX # bpseqLineBond
;

// Lexer tokens
INDEX:
    [1-9] [0-9]*
;

ZERO_INDEX:
    '0'
;

SEP:
    ',' | ';'
;

BOND:
    '(' INDEX SEP INDEX ')'
;

BPSEQCTLINES:
    LINE1BPSEQCT LINE2BPSEQCT LINE3BPSEQCT LINE4BPSEQCT
;

LINECT:
	NONEWLINE*?
	(
		'ENERGY'
		| 'Energy'
		| 'dG'
	) .*? '\r'? '\n'
;

fragment NONEWLINE:
	~( '\r' | '\n' )
;

fragment IUPAC_CODE:
	[ACGUacguTtRrYysSWwKkMmBbDdHhVvNn-]
;

NUCLEOTIDE:
	(
		IUPAC_CODE
		| NON_STANDARD_CODE
	)+
;

fragment NON_STANDARD_CODE:
	'"'
	| '?'
	| ']'
	| '~'
	| '['
	| '_'
	| '+'
	| '='
	| '/'
	| '4'
	| '7'
	| 'P'
	| 'O'
	| 'I'
;

fragment EDBN_CODE:
	'.'
	| '('
	| ')'
	| '['
	| ']'
	| '{'
	| '}'
	| '<'
	| '>'
	| [a-zA-Z]
;

EDBN:
	EDBN_CODE+
;

LINE1BPSEQCT:
	'Filename' .*? '\r'? '\n'
;

LINE2BPSEQCT:
	'Organism' .*? '\r'? '\n'
;

LINE3BPSEQCT:
	'Accession' .*? '\r'? '\n'
;

LINE4BPSEQCT:
	'Citation' .*? '\r'? '\n'
;

COMMENT:
	('#' | '>') .*? '\r'? '\n'
;

WS:
	[ \t\r\n]+ -> skip
; // skip spaces, tabs, newlines