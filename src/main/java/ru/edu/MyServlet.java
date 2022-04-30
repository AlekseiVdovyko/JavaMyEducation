package ru.edu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        writer.write("<html>");
        writer.write("<head>");
        writer.write("<meta charset=\"UTF-8\">");
        writer.write("<title>About</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<h1>About author</h1>");
        writer.write("<p>Family name: Vdovyko</p>");
        writer.write("<p>First name: Aleksei</p>");
        writer.write("<p>Patronymic: Igorevich</p>");
        writer.write("<p>Hobby: computers, mountain tourism</p>");
        writer.write("<p>Bitbucket url: <a href=\"https://bitbucket.org/alekseivdovyko/homework/src/master/\">https://bitbucket.org/alekseivdovyko</a> </p>");
        writer.write("</body>");
        writer.write("</html>");

    }


}
