Todo Project

This is a Java project based on Maven that provides a web application (WAR) for managing a todo-list of tasks.
Description

The Todo app is a simple web application for managing a list of pending tasks. It is built using standard Java web technologies, including Java Servlets, JavaServer Pages (JSP), and a MySQL database to store tasks.
Requirements

    Java Development Kit (JDK) 17
    Apache Maven
    MySQL Server

Compile the project using Maven:


    mvn clean package

    Deploy the application to a web server such as Apache Tomcat.

Usage

    Access the application from a web browser:

    http://localhost:8080/todo

    From the web application, you can add, edit, and delete tasks from your list.

Technologies Used

    Java Servlets
    JavaServer Pages (JSP)
    Maven
    MySQL

Dependencies

    JUnit 3.8.1 (for testing)
    MySQL Connector/J 8.3.0 (JDBC connector for MySQL)
    Jakarta Servlet API 5.0.0
    Jakarta Servlet JSP API 4.0.0-M2
    Jakarta Servlet JSP JSTL API 3.0.0
    Jakarta Servlet JSP JSTL 3.0.1

Contribution

If you wish to contribute to this project, please follow these steps:

    Fork the repository.
    Create a new branch (git checkout -b feature/new-feature).
    Make your changes.
    Commit your changes (git commit -am 'Add new feature').
    Push your branch (git push origin feature/new-feature).
    Create a pull request.
