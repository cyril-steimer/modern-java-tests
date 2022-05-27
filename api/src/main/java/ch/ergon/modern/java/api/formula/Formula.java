package ch.ergon.modern.java.api.formula;

public sealed interface Formula permits BinaryFormula, Constant, UnaryFormula {
}
