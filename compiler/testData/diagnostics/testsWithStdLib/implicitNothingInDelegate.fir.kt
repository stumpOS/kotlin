// FIR_DUMP
fun foo() {
    val m1: Map<String, Any>  = mapOf("foo" to "bar")
    val m2: Map<String, *>  = mapOf("baz" to "bat")
    val foo: String by m1
    val baz: String by m2
    println(foo) // bar
    println(baz) // kotlin.KotlinNothingValueException
}
