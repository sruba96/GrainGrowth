package domain;

/**
 * Created by robert on 23.04.16.
 */
public enum StructureType {

    MOORA("Moora"),
    VONNEUMANA("von Neumana"),
    HEXAGONLLEFT("Hexagonalne lewe"),
    HEXAGONLRIGHT("Hexagonalne prawe"),
    HEXAGONLRAND("Hexagonalne losowe"),
    PENTAGONAL("Pentagonal"),
    RANDOMAUTOMAT("Random");

    private final String text;

    StructureType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
