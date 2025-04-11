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

@WebServlet("/get")
public class StatsStatelessServlet extends HttpServlet {
    private static final long serialVersionUID = 3L;

    @EJB
    private StatsStatelessRemote statsStateless;
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println((new Date()).toString() + "<br>");
            out.println("Statistics: <br>");

            String query = request.getParameter("value");
            if (query == null || query.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameter: value");
                return;
            }
            switch (query) {
                case "count":
                    out.println("Count: " + statsStateless.getCount() + "<br>");
                    break;
                case "min":
                    out.println("Min: " + statsStateless.getMin() + "<br>");
                    break;
                case "max":
                    out.println("Max: " + statsStateless.getMax() + "<br>");
                    break;
                case "mean":
                    out.println("Mean: " + statsStateless.getMean() + "<br>");
                    break;
                case "std":
                    out.println("Standard deviation: " + statsStateless.getSTD() + "<br>");
                    break;
                case "summary":
                    out.println("Count = " + statsStateless.getCount() + "<br>");
                    out.println("Min = " + statsStateless.getMin() + "<br>");
                    out.println("Max = " + statsStateless.getMax() + "<br>");
                    out.println("Mean = " + statsStateless.getMean() + "<br>");
                    out.println("Standard Deviation = " + statsStateless.getSTD() + "<br>");
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter value: " + query);
                    return;
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
        } finally {
            out.close();
        }
    }
}
