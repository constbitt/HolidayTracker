import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Singleton
object TaskReport : Observable() {
    val allTasks: MutableList<Task> = mutableListOf()
    val allProjects: MutableList<Project> = mutableListOf()
    val allEmployees: MutableList<Employee> = mutableListOf()

    fun saveReport() {
        while (true) {
            try {
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                print("Input start date in format: year-month-day: ")
                val start = LocalDate.parse(readln(), formatter)
                print("Input finish date in format: year-month-day: ")
                val finish = LocalDate.parse(readln(), formatter)

                var data = allTasks.filter { task: Task -> (task.start >= start && task.finish <= finish)}
                var file = File("reports/Tasks.csv")
                var writer = BufferedWriter(FileWriter(file))
                for (task in data) {
                    val line = "${task.id}, ${task.description}"
                    writer.write(line)
                    writer.newLine()
                }
                writer.close()

                data = allProjects.filter { project: Project -> (project.start >= start && project.finish <= finish)}
                file = File("reports/Projects.csv")
                writer = BufferedWriter(FileWriter(file))
                for (project in data) {
                    val line = "${project.id}, ${project.description}"
                    writer.write(line)
                    writer.newLine()
                }
                writer.close()
                println("Report saved successfully.")
                return

            } catch (e: Exception) {
                println("Invalid input. Please, try again.")
            }
        }
    }
}