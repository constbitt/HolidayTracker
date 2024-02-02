// Iterator
interface Iterator<T> {
    fun hasNext(): Boolean
    fun next(): T
}

class EmployeeIterator(private val tasks: List<Task>) : Iterator<Task> {
    private var index = 0

    override fun hasNext(): Boolean {
        return index < tasks.size
    }

    override fun next(): Task {
        return if (hasNext()) {
            val task = tasks[index]
            index++
            task
        } else {
            throw NoSuchElementException()
        }
    }
}


data class Employee(val id: Int, val name: String) : Observer {
    val tasks: MutableList<Task> = mutableListOf()

    init {
        TaskReport.allEmployees.add(this)
        TaskReport.addObserver(this)
    }

    override fun update(taskId: Int) {
        val iterator = iterator()
        while (iterator.hasNext()) {
            val task = iterator.next()
            if (task.id == taskId) {
                println("$name is notified about a new task with id $taskId.")
                break
            }
        }
    }

    override fun toString(): String {
        return "id: $id, name: $name"
    }

    fun iterator(): Iterator<Task> {
        return EmployeeIterator(tasks)
    }
}