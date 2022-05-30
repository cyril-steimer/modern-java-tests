package ch.ergon.modern.java.deserialization

import ch.ergon.modern.java.api.bean.Bean
import ch.ergon.modern.java.api.bean.beanProperty
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BeanDeserializerTest {

    class Bean1 : Bean() {
        var string: String? by beanProperty()
        var bean2: Bean2? by beanProperty()
    }

    class Bean2 : Bean() {
        var double: Double by beanProperty(initialValue = 10.0)
    }

    private val json = """
        {
            "string": "foo",
            "bean2": {
                "double": 1.23
            }
        }
    """.trimIndent()

    @Test
    fun testDeserializer() {
        val jsonObject = Gson().fromJson(json, JsonObject::class.java)
        val actual = BeanDeserializer.deserialize(jsonObject, Bean1::class)

        val expected = Bean1()
        expected.string = "foo"
        expected.bean2 = Bean2()
        expected.bean2?.double = 1.23

        assertEquals(expected, actual)
    }

}