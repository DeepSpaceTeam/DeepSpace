package ru.myitschool.nasa_bootcamp.lookbeyond.managers

import java.util.*

interface Manager {
    var model: AbstractPointing?
    fun start()
    fun stop()
}


class Managers internal constructor(
    sensorController: SensorManager, gpsManager: GpsManager
) : Manager {
    private val controllers = ArrayList<Manager>()
    private val sensorController: SensorManager

    override var model: AbstractPointing? = null
        set(value) {
            for (controller in controllers) {
                controller.model = value
            }
            field = value
        }


    override fun start() {
        for (controller in controllers) {
            controller.start()
        }
    }

    override fun stop() {
        for (controller in controllers) {
            controller.stop()
        }
    }

    fun addController(manager: Manager) {
        controllers.add(manager)
    }

    init {
        addController(gpsManager)
        this.sensorController = sensorController
        addController(sensorController)
    }
}