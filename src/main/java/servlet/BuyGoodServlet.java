package servlet;

import dao.CodeDao;
import model.Code;
import model.User;
import service.MailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BuyGoodServlet", value = "/buy")
public class BuyGoodServlet extends HttpServlet {

    private static final MailService MAIL_SERVICE = new MailService();
    private static final CodeDao CODE_DAO = new CodeDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long goodId = Long.valueOf(req.getParameter("good_id"));
        String codeValue = req.getParameter("code");
        User user = (User) req.getSession().getAttribute("user");
        Code code = new Code(codeValue, user.getId(), goodId);
        if (CODE_DAO.checkCode(code)) {
            resp.getWriter().println("Success");
        } else {
            resp.getWriter().println("Failed");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("user");
        String codeValue = MAIL_SERVICE.sendMail(user.getEmail());
        Code code = new Code(codeValue, user.getId(), id);
        CODE_DAO.addCode(code);
        req.setAttribute("good_id", id);
        req.getRequestDispatcher("entercode.jsp").forward(req, resp);
    }
}
