<%@ page import ="java.sql.*"%>
<%@ page import ="dbconnection.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<style>
input[type=text]{
  width: 40%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin-left: 8px;
  border: none;
  cursor: pointer;
  width: 10%;
}

input[type=Submit] {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin-left: 8px;
  border: none;
  cursor: pointer;
  width: 10%;
}
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 50%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 40%;
}

/* The Close Button */
.close {
  color: #aaaaaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

</style>
</head>
<body>
	<h2>Welcome <%=request.getAttribute("Name")%></h2>
	
	<button id="myBtn">Admin Grid</button>
	
	<span><h3>List Creator:</h3></span>
	<table>
  <tr>
    <th>S.No</th>
    <th>Name</th>
  </tr>
  <%
        Connection con = DBConnection.getConnection();	
        Statement stmt=con.createStatement();  
         
        ResultSet rs=stmt.executeQuery("select SNo,Name from list_creator");
        while(rs.next())
        {
            %>
                <tr>             
                     <td>
                     <%= rs.getString(1)%>
                     </td>
                     <td>
                     <%= rs.getString(2)%>
                     </td>                  
                </tr>
            <% 
        }
        Statement stmt2=con.createStatement(); 
        ResultSet rs1=stmt2.executeQuery("select Name,email,number from user_registration");
    %>
</table>
<br>
<br>
<form action="DisplayTable" method="post">
<span>Enter the Name:</span><br>
<input type="text" name="userbox" required>
<input type="submit" value="Enter">
</form>


<div id="myModal" class="modal">

  
  <div class="modal-content">
    <span class="close">&times;</span>
    <table>
  <tr>
    <th>Name</th>
    <th>Email</th>
    <th>Number</th>
  </tr>
  <% 
        while(rs1.next())
        {
            %>
                <tr>             
                     <td>
                     <%= rs1.getString(1)%>
                     </td>
                     <td>
                     <%= rs1.getString(2)%>
                     </td>   
                     <td>
                     <%= rs1.getString(3)%>
                     </td>                
                </tr>
            <% 
        }
    %>
</table>
  </div>

</div>


<script>

var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn");
var span = document.getElementsByClassName("close")[0];
btn.onclick = function() {
  modal.style.display = "block";
}
span.onclick = function() {
  modal.style.display = "none";
}
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
</script>
</body>
</html>