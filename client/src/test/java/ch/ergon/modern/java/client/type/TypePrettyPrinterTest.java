package ch.ergon.modern.java.client.type;

import ch.ergon.modern.java.api.type.ListType;
import ch.ergon.modern.java.api.type.SimpleType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypePrettyPrinterTest {
    private final TypePrettyPrinter prettyPrinter = new TypePrettyPrinter();

    @Test
    public void testSimpleTypes() {
        assertEquals("int", prettyPrinter.print(SimpleType.INTEGER));
        assertEquals("string", prettyPrinter.print(SimpleType.STRING));
    }

    @Test
    public void testListTypes() {
        var intList = new ListType(SimpleType.INTEGER);
        assertEquals("[int]", prettyPrinter.print(intList));
        assertEquals("[[int]]", prettyPrinter.print(new ListType(intList)));
    }
}
