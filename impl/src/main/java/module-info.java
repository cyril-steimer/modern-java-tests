module ch.ergon.modern.java.impl {
    requires transitive ch.ergon.modern.java.api;
    opens ch.ergon.modern.java.impl.shape to ch.ergon.modern.java.api;
}