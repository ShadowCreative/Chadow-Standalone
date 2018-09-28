package io.github.shadowcreative.chadow.engine

import com.google.common.collect.ArrayListMultimap
import io.github.shadowcreative.chadow.IntegratedServerPlugin
import io.github.shadowcreative.chadow.handler.Activator
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.scheduler.BukkitTask

abstract class RuntimeTaskScheduler : SustainableHandler(), Listener, Activator<IntegratedServerPlugin?>
{
    companion object {

        // Registers a RuntimeTaskScheduler object. It cans keep some work sustainable through that list.
        //
        private val registeredFramework : ArrayListMultimap<IntegratedPlugin, RuntimeTaskScheduler> = ArrayListMultimap.create()

        fun getRegisterFramework(handlePlugin : IntegratedPlugin) : List<RuntimeTaskScheduler> = registeredFramework.get(handlePlugin)
    }

    // Specify the delay before starting the operation.
    var delay  : Long        = 0L;    protected set

    // Specify the work cycle time.
    var period : Long        = 0L;    protected set

    // Determines this task is synchronized.
    var isSync : Boolean     = true;  protected set

    // Specifies the Task type of job scheduler.
    var task   : BukkitTask? = null;  private set

    // Specifies the ID of the job scheduler.
    val taskId : Int get() = if(this.task == null) -1 else this.task!!.taskId

    // Specifies the plugin to manage the scheduler.
    var activePlugin: IntegratedServerPlugin? = null; private set;
    fun hasActivePlugin(): Boolean = this.activePlugin != null
    fun setPlugin(plugin: IntegratedServerPlugin)
    {
        if (this.hasActivePlugin()) return
        this.activePlugin = plugin
    }

    override fun setEnabled(handleInstance: IntegratedServerPlugin?)
    {
        this.activePlugin = handleInstance
        this.setEnabled(handleInstance != null)
    }

    override fun isEnabled(): Boolean
    {
        for (core in registeredFramework[this.activePlugin]) {
            if (core == this)
            {
                return true
            }
        }
        return false
    }

    override fun setEnabled(active: Boolean)
    {
        this.preLoad(active)
        this.loadRegisterListener(active)
        this.setActivationTask(active)
        this.finLoad(active)

        if (active) {
            if (!this.isActivated()) registeredFramework.put(this.activePlugin, this)
        }
        else {
            if (this.isActivated())  registeredFramework.remove(this.activePlugin, this)
        }
    }

    override fun equals(other: Any?): Boolean
    {
        if (other == null) return false

        if (other is RuntimeTaskScheduler) {
            return other.task === this.task && other.activePlugin === this.activePlugin
        }
        return false
    }

    fun isActivated(): Boolean = registeredFramework[this.activePlugin].contains(this)

    fun setActivationTask(active: Boolean)
    {
        if (active) {
            if (this.activePlugin!!.isEnabled)
                if (this.isSync) {
                    this.task = Bukkit.getScheduler().runTaskTimer(this.activePlugin, this, this.delay, this.period)
                }
                else {
                    this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(this.activePlugin, this, this.delay, this.period)
                }
        }
        else {
            if (this.task != null) {
                this.task!!.cancel()
                this.task = null
            }
        }
    }

    private fun loadRegisterListener(active: Boolean)
    {
        if (active) {
            val plugin : IntegratedPlugin = this.activePlugin!!
            if (plugin.isEnabled) Bukkit.getPluginManager().registerEvents(this, this.activePlugin)
        }
        else {
            HandlerList.unregisterAll(this)
        }
    }

    protected open fun preLoad(active: Boolean) {}


    protected open fun finLoad(active: Boolean) {}

    /**
     * Call the action method synchronously.
     */
    @Synchronized fun sync() = this.run()

    override fun hashCode(): Int
    {
        var result = delay.hashCode()
        result = 31 * result + period.hashCode()
        result = 31 * result + isSync.hashCode()
        result = 31 * result + (task?.hashCode() ?: 0)
        result = 31 * result + (activePlugin?.hashCode() ?: 0)
        return result
    }
}