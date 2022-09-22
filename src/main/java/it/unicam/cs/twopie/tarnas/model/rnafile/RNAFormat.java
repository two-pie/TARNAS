package it.unicam.cs.twopie.tarnas.model.rnafile;

/**
 * An RNAFormat can be:
 * <ul>
 *   <li>{@link #AAS}<br>
 *   Arc Annotated Sequence, similar to the Extended Dot-Bracket Notation format
 *   in which the weak bonds are expressed as a list
 * <p>
 * (i_1,j_1);(i_2,j_2); ... ;(i_m,j_m)
 * <p>
 * where each index i_k, j_k belongs to the interval [1,n] (n is the length
 * of the primary sequence) and i_k < j_k + 1 for all k.
 *     </li>
 *  <li>{@link #AAS_NO_SEQUENCE}<br>
 *   It is like {@link #AAS} but without the sequence.
 *   </li>
 *   <li>{@link #BPSEQ}<br>
 *   The structural information in the bpseq format is denoted in three columns.<br>
 *   The first column contains the sequence position, starting at one.<br>
 *   The second column contains the base in one-letter notation.<br>
 *   The third column contains the pairing partner of the base if the base is paired.<br>
 *   If the base is unpaired, the third column is zero.
 *   </li>
 *   <li>{@link #CT}<br>
 *   The connect format is column based.<br>
 *   The first column specified the sequence index, starting at one.<br>
 *   Columns 3, 4, and 6 redundantly give sequence indices (plus/minus one).<br>
 *   The second column contains the base in one-letter notation.<br>
 *   Column 4 specifies the pairing partner of this base if it involved in a base pair.<br>
 *   If the base is unpaired, this column is zero.<br>
 *   The parser expects one header line containing the word "ENERGY", "Energy", or "dG".
 *   </li>
 *   <li>{@link #DB}<br>
 *   <h1>Dot-Bracket Notation (a.k.a. Dot-Parenthesis Notation)</h1>
 *   The Dot-Bracket notation as introduced already in the early times of the ViennaRNA
 *   Package denotes base pairs by matching pairs of parenthesis () and unpaired nucleotides by dots ..<br>
 *   As a simple example, consider a helix of size 4 enclosing a hairpin of size 4.<br>
 *   In dot-bracket notation, this is annotated as ((((....))))
 *
 *   <h1>Extended Dot-Bracket Notation</h1>
 *   A more generalized version of the original Dot-Bracket notation may use additional pairs of brackets,
 *   such as <>, {}, and [], and matching pairs of uppercase/lowercase letters.<br>
 *   This allows for anotating pseudo-knots, since different pairs of brackets are not required to be nested.<br>
 *   The following annotations of a simple structure with two crossing helices of size 4 are equivalent:<br>
 *   <<<<[[[[....>>>>]]]]<br>
 *   ((((AAAA....))))aaaa<br>
 *   AAAA{{{{....aaaa}}}}<br>
 *   </li>
 *   <li>{@link #DB_NO_SEQUENCE}<br>
 *   It is like {@link #DB} but without the sequence.
 *   </li>
 *   <li>{@link #FASTA}<br>
 *    The description line (defline) or header/identifier line, which begins with '>', gives a name and/or a unique
 *    identifier for the sequence, and may also contain additional information.<br>
 *    In a deprecated practice, the header line sometimes contained more than one header, separated by a ^A (Control-A)
 *    character.<br>
 *    In the original Pearson FASTA format, one or more comments, distinguished by a semi-colon at the beginning of the line,
 *    may occur after the header.<br>
 *    Some databases and bioinformatics applications do not recognize these comments and follow the NCBI FASTA specification.<br>
 *    An example of a multiple sequence FASTA file follows:<br><br>
 *
 *    >SEQUENCE_1<br>
 *    MTEITAAMVKELRESTGAGMMDCKNALSETNGDFDKAVQLLREKGLGKAAKKADRLAAEG<br>
 *    LVSVKVSDDFTIAAMRPSYLSYEDLDMTFVENEYKALVAELEKENEERRRLKDPNKPEHK<br>
 *    IPQFASRKQLSDAILKEAEEKIKEELKAQGKPEKIWDNIIPGKMNSFIADNSQLDSKLTL<br>
 *    MGQFYVMDDKKTVEQVIAEKEKEFGGKIKIVEFICFEVGEGLEKKTEDFAAEVAAQL<br><br>
 *    >SEQUENCE_2<br>
 *    SATVSEINSETDFVAKNDQFIALTKDTTAHIQSNSLQSVEELHSSTINGVKFEEYLKSQI<br>
 *    ATIGENLVVRRFATLKAGANGVVNGYIHTNGRVGVVIAAACDSAEVASKSRDLLRQICMH<br>
 *   </li>
 *   </ul>
 * <p>
 *
 *   Refer to: <a href="https://github.com/bdslab/aspralign">ASPRALIGN</a>,
 *   <a href="https://www.ibi.vu.nl/programs/k2nwww/static/data_formats.html">Accepted Data Formats</a>,
 *   <a href="https://www.tbi.univie.ac.at/RNA/ViennaRNA/doc/html/rna_structure_notations.html">RNAlib</a>,
 *   <a href="https://en.wikipedia.org/wiki/FASTA_format">Fasta on Wikipedia</a>
 *
 *   @author Piero Hierro, Piermichele Rosati
 */
public enum RNAFormat {
    AAS,
    AAS_NO_SEQUENCE,
    BPSEQ,
    CT,
    DB,
    DB_NO_SEQUENCE,
    FASTA;
}
