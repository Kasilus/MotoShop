<%-- 
    Document   : test
    Created on : Jan 17, 2017, 11:52:11 PM
    Author     : Stanislav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="testjdbc.TestJdbc"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>    
            HI!
            <%
                TestJdbc test = new TestJdbc();
                test.check();
            %>
        </h1>
    </body>
</html>
