import java.time.LocalDate

open class Task(var id: Int, var description: String, var assignedTo: Employee? = null) {
    val completed: Boolean = false
    open val start: LocalDate = LocalDate.now()
    open val finish: LocalDate = LocalDate.now()

    init {
        TaskReport.allTasks.add(this)
    }

    override fun toString(): String {
        return "$id, $description"
    }
}

// Composite
open class Project(id: Int, description: String) : Task(0, description) {
    val tasks: MutableList<Task> = mutableListOf()
    override val start: LocalDate = LocalDate.now()
    override val finish: LocalDate = LocalDate.now()

    init {
        TaskReport.allProjects.add(this)
    }

    fun add(task: Task) {
        tasks.add(task)
        println("Task with id $id added to project $description")
    }
}