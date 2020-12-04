package display;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnection.DBConnection;

/**
 * Servlet implementation class DisplayTable
 */
@WebServlet("/DisplayTable")
public class DisplayTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayTable() {
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
		String Name = request.getParameter("userbox");
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select SNo from list_creator");
			int serialno= 0;
			while(rs.next()) {
				serialno = rs.getInt(1);
			}
			serialno = serialno + 1;
			Statement s=con.createStatement();
			int result = s.executeUpdate("INSERT INTO list_creator(SNo,Name) VALUE ("+serialno+",'"+Name+"')");
			if(result == 1) {
				response.sendRedirect("welcome.jsp");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
