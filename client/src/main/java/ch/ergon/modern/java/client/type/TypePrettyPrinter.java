package ch.ergon.modern.java.client.type;

import ch.ergon.modern.java.api.type.ListType;
import ch.ergon.modern.java.api.type.SimpleType;
import ch.ergon.modern.java.api.type.Type;

public class TypePrettyPrinter {

    public String print(Type type) {
        return switch (type) {
            case SimpleType s -> switch (s) {
                case STRING -> "string";
                case INTEGER -> "int";
            };
            case ListType l -> "[" + print(l.elementType()) + "]";
        };
    }
}
