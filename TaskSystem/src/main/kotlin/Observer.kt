// Observer
interface Observer {
    fun update(taskId: Int)
}

open class Observable {
    private val observers: MutableList<Observer> = mutableListOf()

    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    fun notifyObservers(taskId: Int) {
        observers.forEach { it.update(taskId) }
    }
}