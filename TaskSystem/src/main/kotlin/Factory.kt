// Factory
interface Action {
    fun doAction(task: Task)
}

class CreateTask : Action {
    override fun doAction(task: Task) {
        print("Input task description: ")
        val description = readlnOrNull()
        task.id = TaskReport.allTasks.size - 1
        if (description != null) {
            task.description = description
        }
        print("Do you want to assign task? y/n ")
        when (readlnOrNull()) {
            "y" -> {
                val assignTask= AssignTask()
                assignTask.doAction(task)
                //TaskReport.allTasks.add(task)
                return
            }
            "n" -> {
                TaskReport.allTasks.add(task)
                return
            }
            else -> {
                println("Invalid option. Please try again.")
            }
        }
    }
}

class AssignTask : Action {
    override fun doAction(task: Task) {
        println(TaskReport.allEmployees)
        print("Choose employee id:")
        val employeeId = readlnOrNull()?.toIntOrNull()
        if (employeeId != null && employeeId <= TaskReport.allEmployees.size) {
            val employee: Employee = TaskReport.allEmployees[employeeId]
            employee.tasks.add(task)
            println("Task assigned to ${employee.name}.")
            TaskReport.notifyObservers(task.id)
        } else {
            println("Invalid number. Please try again.")
        }
    }
}

class ActionFactory {
    fun createAction(action: () -> Unit) {
        return action()
    }
}