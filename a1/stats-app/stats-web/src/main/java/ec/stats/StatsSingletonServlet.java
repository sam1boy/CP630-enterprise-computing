package ec.stats;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import ec.stats.sb.*;

@WebServlet("/add-data")
public class StatsSingletonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private StatsSingletonRemote statsSingleton;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String valueParam = request.getParameter("value");
            if (valueParam == null || valueParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameter: value");
                return;
            }
            double value;
            try {
                value = Double.parseDouble(valueParam);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format for parameter: value");
                return;
            }
            statsSingleton.addData(value);
            out.println((new Date()).toString() + "<br>");
            out.println("Add data: <br>");
            out.println("Value: " + value + "<br>");
            out.println("Count: " + statsSingleton.getCount() + "<br>");

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            statsSingleton.saveModel();
        }
    }
}