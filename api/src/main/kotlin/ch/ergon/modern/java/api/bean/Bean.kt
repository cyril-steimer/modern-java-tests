package ch.ergon.modern.java.api.bean

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

data class Notification<T>(val feature: String, val oldValue: T, val newValue: T)

interface Subscriber {
    fun notify(notification: Notification<*>)
}

abstract class Bean {
    private val subscribers = mutableListOf<Subscriber>()
    private val properties = mutableListOf<NotifyingPropertyDelegate<*>>()

    fun subscribe(subscriber: Subscriber) {
        subscribers += subscriber
    }

    fun setValue(property: String, value: Any?) {
        getProperty(property)?.setValue(value)
    }

    fun getValue(property: String): Any? {
        return getProperty(property)?.value
    }

    private fun notifyAll(notification: Notification<*>) {
        subscribers.forEach { it.notify(notification) }
    }

    private fun getProperty(name: String) = properties.find { it.property.name == name }

    // Is there a way to make this class less visible?
    class NotifyingPropertyDelegateProvider<T>(private val initialValue: T) {
        operator fun provideDelegate(thisRef: Bean, property: KProperty<*>): ReadWriteProperty<Bean, T> {
            val delegate = NotifyingPropertyDelegate(initialValue, thisRef, property)
            thisRef.properties += delegate
            return delegate
        }
    }

    private class NotifyingPropertyDelegate<T>(
        initialValue: T, val bean: Bean, val property: KProperty<*>
    ) : ReadWriteProperty<Bean, T> {
        private var _value: T = initialValue

        val value: T
            get() = _value

        override fun getValue(thisRef: Bean, property: KProperty<*>): T {
            return value
        }

        override fun setValue(thisRef: Bean, property: KProperty<*>, value: T) {
            setValue(value)
        }

        fun setValue(value: Any?) {
            val oldValue = this.value
            this._value = value as T // Is there any way to make this a checked cast?
            bean.notifyAll(Notification(feature = property.name, oldValue = oldValue, newValue = value))
        }
    }
}

fun <T> beanProperty() = Bean.NotifyingPropertyDelegateProvider<T?>(initialValue = null)

fun <T> beanProperty(initialValue: T) = Bean.NotifyingPropertyDelegateProvider(initialValue = initialValue)