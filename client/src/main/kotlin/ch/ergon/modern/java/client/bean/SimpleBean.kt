package ch.ergon.modern.java.client.bean

import ch.ergon.modern.java.api.bean.Bean
import ch.ergon.modern.java.api.bean.beanProperty

class SimpleBean(initialValue: Double) : Bean() {
    var canBeNull: Double? by beanProperty()
    var neverNull: Double by beanProperty(initialValue = initialValue)
}