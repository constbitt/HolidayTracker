// Facade
class TaskManagementFacade {
    private val actionFactory = ActionFactory()
    init {
        val employee1 = Employee(0, "John")
        val employee2 = Employee(1, "Alice")
        val employee3 = Employee(2, "Dmitri")
        Task(0, "Check email", employee1)
        Task(1, "Discuss project", employee1)
        Task(2, "Sign documents", employee2)
        Task(3, "Create report", employee3)
    }
    fun createEmployee() {
        print("Input employee's name: ")
        val name = readln()
        val id = TaskReport.allEmployees.size
        Employee (id, name)
        println("Employee $name added.")
    }

    fun createTask() {
        val task = Task(0, "")
        actionFactory.createAction {
            CreateTask().doAction(task)
        }
    }

    fun assignTask() {
        println(TaskReport.allTasks)
        print("Choose task id: ")
        val id = readln().toInt()
        actionFactory.createAction { AssignTask().doAction(TaskReport.allTasks[id])}
    }

    fun createReport() {
        TaskReport.saveReport()
    }
}

class UserFacade {
    fun menu() {
        val facade = TaskManagementFacade()
        try {
            while (true) {
                println("\n1. Add employee\n2. Add task\n" +
                        "3. Assign task\n4. Create report\n5. Exit")
                print("Choose an option: ")
                when (readlnOrNull()?.toIntOrNull()) {
                    1 -> {
                        facade.createEmployee()
                    }

                    2 -> {
                        facade.createTask()
                    }

                    3 -> {
                        facade.assignTask()
                    }

                    4 -> {
                        facade.createReport()
                    }

                    5 -> {
                        println("Exiting...")
                        return
                    }

                    else -> println("Invalid option. Please try again.")
                }
            }
        }
        catch (e: Exception) {
            println("Sorry, something went wrong.")
        }
    }
}