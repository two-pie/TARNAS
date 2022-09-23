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
    header_line+=(COMMENT | LINE1BPSEQCT| LINE2BPSEQCT | LINE3BPSEQCT | LINE4BPSEQCT)* bpseq_structure
;

ct:
    header_line+=(COMMENT | LINE1BPSEQCT| LINE2BPSEQCT | LINE3BPSEQCT | LINE4BPSEQCT)* LINECT ct_structure
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
	bond ';' bonds #bondsContinue
	| bond # bondsEnd
;

bond:
	'(' INDEX ',' INDEX ')'
;

ct_structure:
     ct_line ct_structure # ctSeq
	| ct_line # ctLast
;

ct_line:
	INDEX                       // First column: sequence index
	IUPAC_CODE                  // Second column: base in one-letter notation
	( ZERO_INDEX | INDEX )      // Third column: sequence indices (plus/minus one)
	( ZERO_INDEX | INDEX )      // Fourth column: sequence indices (plus/minus one)
	ZERO_INDEX                  // Fifth column: pairing partner of this base if it involved in a base pair
	INDEX # ctLineUnpaired      // Sixth column: sequence indices (plus/minus one)
	|
	INDEX
	IUPAC_CODE
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
	INDEX IUPAC_CODE ZERO_INDEX # bpseqLineUnpaired
	| INDEX IUPAC_CODE INDEX # bpseqLineBond
;


// Lexer tokens
INDEX:
    [1-9] [0-9]*
;

ZERO_INDEX:
    '0'
;

LINECT:
	NONEWLINE*?
	(
		'ENERGY'
		| 'Energy'
		| 'dG'
	) .*? '\r'? '\n'
;

fragment
NONEWLINE
:
	~( '\r' | '\n' )
;

IUPAC_CODE:
	[ACGUacguTtRrYysSWwKkMmBbDdHhVvNnOPI-] | '_' // TODO: fare non standard
;

NUCLEOTIDE:
	IUPAC_CODE+
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

LINE1BPSEQCT
:
	'Filename' .*? '\r'? '\n'
;

LINE2BPSEQCT
:
	'Organism' .*? '\r'? '\n'
;

LINE3BPSEQCT
:
	'Accession' .*? '\r'? '\n'
;

LINE4BPSEQCT
:
	'Citation' .*? '\r'? '\n'
;

COMMENT
:
	('#' | '>') .*? '\r'? '\n'
;

WS
:
	[ \t\r\n]+ -> skip
; // skip spaces, tabs, newlines


