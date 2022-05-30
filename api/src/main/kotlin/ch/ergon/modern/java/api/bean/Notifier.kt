package ch.ergon.modern.java.api.bean

import kotlin.reflect.KProperty

data class Notification(val feature: String, val oldValue: Any?, val newValue: Any?)

interface Subscriber {
    fun notify(notification: Notification)
}

abstract class Notifier {
    private val subscribers = mutableListOf<Subscriber>()

    fun subscribe(subscriber: Subscriber) {
        subscribers += subscriber
    }

    // Maybe there is a way to avoid this method having to be public?
    fun notifyAll(notification: Notification) {
        subscribers.forEach { it.notify(notification) }
    }
}

class NotifyingPropertyDelegate<T>(initialValue: T) {
    private var value: T = initialValue

    operator fun getValue(thisRef: Notifier, property: KProperty<*>): T {
        return value
    }

    operator fun setValue(thisRef: Notifier, property: KProperty<*>, value: T) {
        val oldValue = this.value
        this.value = value
        thisRef.notifyAll(Notification(feature = property.name, oldValue = oldValue, newValue = value))
    }
}

fun <T> notifying() = NotifyingPropertyDelegate<T?>(initialValue = null)
fun <T> notifying(initialValue: T) = NotifyingPropertyDelegate(initialValue = initialValue)