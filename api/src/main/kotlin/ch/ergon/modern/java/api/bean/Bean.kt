package ch.ergon.modern.java.api.bean

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

data class Notification<T>(val feature: String, val oldValue: T, val newValue: T)

interface Subscriber {
    fun notify(notification: Notification<*>)
}

data class BeanProperty(val name: String, val isNullable: Boolean, val type: KClass<*>) {
    fun checkAssignable(value: Any?) {
        when {
            !isNullable && value == null -> throw NullPointerException("Null not supported for '$name'")
            value != null && !type.isInstance(value) -> throw ClassCastException("Invalid value for '$name'")
        }
    }
}

abstract class Bean {
    private val subscribers = mutableListOf<Subscriber>()
    private val properties = mutableListOf<NotifyingPropertyDelegate<*>>()

    fun subscribe(subscriber: Subscriber) {
        subscribers += subscriber
    }

    operator fun set(property: BeanProperty, value: Any?) = set(property.name, value)

    operator fun set(propertyName: String, value: Any?) {
        getProperty(propertyName)?.setValue(value)
    }

    operator fun get(property: BeanProperty): Any? = get(property.name)

    operator fun get(propertyName: String): Any? = getProperty(propertyName)?.value

    fun getBeanProperty(name: String): BeanProperty? = getProperty(name)?.beanProperty

    private fun notifyAll(notification: Notification<*>) {
        subscribers.forEach { it.notify(notification) }
    }

    private fun getProperty(name: String) = properties.find { it.beanProperty.name == name }

    override fun toString(): String {
        val properties = properties.joinToString(separator = ",") { "${it.beanProperty.name} = ${it.value}" }
        return "${javaClass.simpleName}($properties)"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other::class == this::class) {
            val otherBean = other as Bean
            return propertyValues.contentEquals(otherBean.propertyValues)
        }
        return false
    }

    override fun hashCode(): Int = propertyValues.contentHashCode()

    private val propertyValues: Array<Any?>
        get() = properties.map { it.value }.toTypedArray()

    // Is there a way to make this class less visible?
    class NotifyingPropertyDelegateProvider<T>(private val initialValue: T) {
        operator fun provideDelegate(thisRef: Bean, property: KProperty<*>): ReadWriteProperty<Bean, T> {
            val beanProperty = property.beanProperty ?: throw AssertionError("Invalid property '${property.name}'")
            val delegate = NotifyingPropertyDelegate(initialValue, thisRef, property, beanProperty)
            thisRef.properties += delegate
            return delegate
        }
    }

    private class NotifyingPropertyDelegate<T>(
            initialValue: T, private val bean: Bean, private val property: KProperty<*>, val beanProperty: BeanProperty
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

        @Suppress("UNCHECKED_CAST")
        fun setValue(value: Any?) {
            beanProperty.checkAssignable(value)
            val oldValue = this.value
            this._value = value as T // Cast is safe thanks to 'checkAssignable'
            bean.notifyAll(Notification(feature = property.name, oldValue = oldValue, newValue = value))
        }
    }
}

private val KProperty<*>.beanProperty: BeanProperty?
    get() = (returnType.classifier as? KClass<*>)?.let {
        BeanProperty(name, returnType.isMarkedNullable, it)
    }

fun <T> beanProperty() = Bean.NotifyingPropertyDelegateProvider<T?>(initialValue = null)

fun <T> beanProperty(initialValue: T) = Bean.NotifyingPropertyDelegateProvider(initialValue = initialValue)