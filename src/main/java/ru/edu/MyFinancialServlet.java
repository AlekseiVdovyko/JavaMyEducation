package ru.edu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class MyFinancialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter writer = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");

        writer.write("<html>");
        writer.write("<head>");
        writer.write("<title>Calculator</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<h1>Deposit yield calculator</h1>");
        writer.write("<form method=\"POST\" action=\"\"");
        writer.write("<p>Amount at the time of opening <input name=\"sum\"> </p>");
        writer.write("<p>Interest rate <input name=\"percentage\"> </p>");
        writer.write("<p>Number of years <input name=\"years\"> </p>");
        writer.write("<input type=\"submit\" value=\"Calculate\"");
        writer.write("</body>");
        writer.write("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String strSum = req.getParameter("sum");
            int sum = Integer.parseInt(strSum);

            String strPercentage = req.getParameter("percentage");
            int percentage = Integer.parseInt(strPercentage);

            String strYears = req.getParameter("years");
            int years = Integer.parseInt(strYears);

            int result = (((sum * percentage)/100) * years) + sum;

            if (sum < 50000) {
                Writer writer = resp.getWriter();
                writer.write("<html>");
                writer.write("<head>");
                writer.write("<title>Error</title>");
                writer.write("</head>");
                writer.write("<body>");
                writer.write("<h1>Error</h1>");
                writer.write("<span>Minimum amount at the time</span>");
                writer.write("<br>");
                writer.write("<span>opening a deposit of 50,000 rubles</span>");
                writer.write("<hr>");
                writer.write("<a href=\"finance\">Back</a>");
                writer.write("</body>");
                writer.write("</html>");

            } else {
                Writer writer = resp.getWriter();
                writer.write("<html>");
                writer.write("<head>");
                writer.write("<title>Result</title>");
                writer.write("</head>");
                writer.write("<body>");
                writer.write("<h1>Result</h1>");
                writer.write("Total amount " + result + " rubles");
                writer.write("<hr>");
                writer.write("<a href=\"finance\">Back</a>");
                writer.write("</body>");
                writer.write("</html>");
            }
        } catch (NumberFormatException ex) {
            Writer writer = resp.getWriter();
            writer.write("<html>");
            writer.write("<head>");
            writer.write("<title>Result</title>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<h1>Result</h1>");
            writer.write("<span>Invalid data format.</span>");
            writer.write("<br>");
            writer.write("<span>Correct the values</span>");
            writer.write("<hr>");
            writer.write("<a href=\"finance\">Back</a>");
            writer.write("</body>");
            writer.write("</html>");
        }
    }
}
