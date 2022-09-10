package it.unicam.cs.twopier.tarnas;

@FunctionalInterface
public interface RNAFormatTranslatorF<S,O> {

    O translate(S src);

}
