package ch.ergon.modern.java.client.bean

import ch.ergon.modern.java.api.bean.Notifier
import ch.ergon.modern.java.api.bean.notifying

class SimpleBean(initialValue: Double) : Notifier() {
    var canBeNull: Double? by notifying()
    var neverNull: Double by notifying(initialValue = initialValue)
}