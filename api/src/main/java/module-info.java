module ch.ergon.modern.java.api {
    exports ch.ergon.modern.java.api.formula;
    exports ch.ergon.modern.java.api.formula.kotlin;
    exports ch.ergon.modern.java.api.shape;
    exports ch.ergon.modern.java.api.shape.kotlin;
    exports ch.ergon.modern.java.api.type;

    requires transitive kotlin.stdlib;
}