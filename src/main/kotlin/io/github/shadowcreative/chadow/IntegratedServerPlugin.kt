package io.github.shadowcreative.chadow

import io.github.shadowcreative.chadow.component.Prefix
import io.github.shadowcreative.chadow.handler.Activator
import java.io.File

interface IntegratedServerPlugin
{
    fun getMessageHandler(): MessageHandler

    fun getHandleInstance(): Any?

    fun getServerPrefix(): Prefix?

    fun getRegisterHandlers(): List<Activator<*>>

    fun reload()

    fun getDataFolder() : File

    fun getPluginName() : String
}
