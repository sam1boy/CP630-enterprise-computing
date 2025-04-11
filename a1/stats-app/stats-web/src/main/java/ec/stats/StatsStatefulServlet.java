package ec.stats;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ec.stats.sb.*;

@WebServlet("/insert-data")
public class StatsStatefulServlet extends HttpServlet {
    private static final long serialVersionUID = 2L;

    @EJB
    private StatsStatefulRemote statsStateful;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // 1. 参数存在性检查
            String valueParam = request.getParameter("value");
            if (valueParam == null || valueParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameter: value");
                return;
            }

            // 2. 数值格式验证
            double value;
            try {
                value = Double.parseDouble(valueParam);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format for parameter: value");
                return;
            }

            // 3. 执行业务逻辑
            statsStateful.insertData(value);
            statsStateful.createModel();

            // 4. 输出结果
            out.println((new Date()).toString() + "<br>");
            out.println("Inserted value: " + value + "<br>");
            String statsResult = statsStateful.getStats();
            out.println(statsResult);

        } catch (Exception e) {
            // 5. 全局异常处理
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
        } finally {
            // 6. 关闭资源
            if (out != null) {
                out.close();
            }
        }
    }
}