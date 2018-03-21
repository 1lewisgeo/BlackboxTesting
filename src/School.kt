/*
Running my code:
A: Download the standalone Kotlin compiler from https://github.com/JetBrains/kotlin/releases/tag/v1.2.21
and follow instructions here https://kotlinlang.org/docs/tutorials/command-line.html
the .kt will compile to .class and can be run on the JVM
B: Install https://www.jetbrains.com/idea/download and run the code in there
C: Stitch my code together on an online repl or at https://try.kotlinlang.org
D: Don't run it and trust that I got it all right and it works :^)
 */

data class Textbook(val name: String, val version: Int, val id: String)

data class Student(val name: String, val studentNumber: String, val courses: MutableList<Course> = mutableListOf(), var textbook: Textbook? = null) {

    fun enroll(course: Course) = courses.add(course)

    fun drop(course: Course) = courses.remove(course)

    fun assignTextbook(textbook: Textbook): Boolean = this.textbook?.let { false } ?: run { this.textbook = textbook; true }

}

data class Course(var instructor: Teacher, var room: String, val period: Int, val students: MutableList<Student> = mutableListOf())

data class Teacher(val name: String, var department: String, var office: String, val teaches: MutableList<Course> = mutableListOf())

fun main(args: Array<String>) {

    val rivard = Teacher("Rivard","Math", "247")

    val compsci = Course(rivard, "255", 2)

    rivard.teaches.add(compsci)

    val calculus = Course(rivard, "226", 4)

    rivard.teaches.add(calculus)

    val bob = Student("Bob", "45389243218984")

    compsci.students.add(bob)

    bob.enroll(compsci)

}

/*
Notes:
A: Course.addStudent(Student) is really just Course.students.add(Student)
B: Student.enroll(Course) is actually Student.courses.add(Course) in disguise but I added this one to humour you (the function itself is an alias)
C: Getters in Kotlin are handled differently, you do not directly invoke getters and setters they exist automatically
and are invoked with the access operator . and assignments
ex: Student.getName() is just Student.name
ex: Teacher.setOffice(String) is just Teacher.office = String
This holds true even for custom getters and setters, they *will* be invoked
D: val denotes a read-only property and var is mutable
E: data classes have getters, setters, equals, hashcode, and toString implemented automatically
F: Multiple constructors are solved here with default arguments in the constructor
 */