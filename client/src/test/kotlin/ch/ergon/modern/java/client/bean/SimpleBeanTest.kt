package ch.ergon.modern.java.client.bean

import ch.ergon.modern.java.api.bean.Notification
import ch.ergon.modern.java.api.bean.Subscriber
import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class SimpleBeanTest {

    private val bean = SimpleBean(initialValue = -10.0)
    private val subscriber = mockk<Subscriber>(relaxed = true)

    @Test
    fun testSetNullableValue() {
        assertNull(bean.canBeNull)
        bean.canBeNull = 10.0
        assertEquals(10.0, bean.canBeNull)
    }

    @Test
    fun testNotificationsForNullableValue() {
        bean.subscribe(subscriber)

        bean.canBeNull = 10.0
        bean.canBeNull = 20.0
        bean.canBeNull = null

        verifySequence {
            subscriber.notify(Notification(feature = "canBeNull", oldValue = null, newValue = 10.0))
            subscriber.notify(Notification(feature = "canBeNull", oldValue = 10.0, newValue = 20.0))
            subscriber.notify(Notification(feature = "canBeNull", oldValue = 20.0, newValue = null))
        }
    }

    @Test
    fun testSetNotNullableValue() {
        assertEquals(-10.0, bean.neverNull)
        bean.neverNull = 20.0
        assertEquals(20.0, bean.neverNull)
    }

    @Test
    fun testNotificationsForNotNullableValue() {
        bean.subscribe(subscriber)

        bean.neverNull = 20.0
        bean.neverNull = 10.0

        verifySequence {
            subscriber.notify(Notification(feature = "neverNull", oldValue = -10.0, newValue = 20.0))
            subscriber.notify(Notification(feature = "neverNull", oldValue = 20.0, newValue = 10.0))
        }
    }
}