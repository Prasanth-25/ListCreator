package registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dbconnection.DBConnection;
/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Name = request.getParameter("yourname");
		String Email = request.getParameter("email");
		String Number = request.getParameter("number");
		String Pwd = request.getParameter("password");
		String Dob = request.getParameter("dob");
		Connection con = DBConnection.getConnection();
		System.out.println(Pwd);
		try {
			Statement s=con.createStatement();
			int result = s.executeUpdate("INSERT INTO user_registration(Name,email,number,password,dateofbirth) VALUE ('"+Name+"','"+Email+"','"+Number+"','"+Pwd+"','"+Dob+"')");
			if(result == 1) {
				response.sendRedirect("index.html");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
