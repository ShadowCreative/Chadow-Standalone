package io.github.shadowcreative.chadow.concurrent

interface TaskManager
{
    fun cancel()

    fun start(serviceTakenListener: Runnable)

    fun start(serviceTakenListener: () -> Unit)

    fun setRuntimeTaskId(_ : Int)

    fun getRuntimeTaskId(): Any
}