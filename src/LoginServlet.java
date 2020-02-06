import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/html");
        PrintWriter out= res.getWriter();

        String n= req.getParameter("username");
        String p= req.getParameter("password");
        HttpSession session= req.getSession(false);

        if(session != null){
            session.setAttribute("name",n);
            if(LoginDao.validate(n,p)){
                RequestDispatcher rd=req.getRequestDispatcher("welcome.jsp");
                rd.forward(req,res);
            }else{
                out.println("<p style=\"color:red\"> Invalid Username or Password</p>");
            RequestDispatcher rd=req.getRequestDispatcher("login.jsp");
            rd.forward(req,res);
            }
        }
        out.close();
    }
}
