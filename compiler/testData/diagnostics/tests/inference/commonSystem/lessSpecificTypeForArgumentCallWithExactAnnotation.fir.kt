// !LANGUAGE: -NewInference
// !DIAGNOSTICS: -UNUSED_PARAMETER

interface A
interface B : A
interface C : A

@Suppress("INVISIBLE_REFERENCE")
fun <K> select(x: K, y: K): <!HIDDEN, HIDDEN!>@kotlin.internal.Exact<!> K = x

fun foo(a: Any) {}

fun test(b: B, c: C) {
    foo(
        select(b, c)
    )
}
