package validation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnection.DBConnection;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginValidation() {
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
		Connection con = DBConnection.getConnection();
		String Email = request.getParameter("email");
		String Password = request.getParameter("pwd");
		try {
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select Name,email,password from user_registration");
			while(rs.next()) {
				if(Email.equalsIgnoreCase(rs.getString(2))) {
					if(Password.equals(rs.getString(3))) {
						RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
						request.setAttribute("Name", rs.getString(1)); // set your String value in the attribute
						dispatcher.forward( request, response );
					}
				}
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
