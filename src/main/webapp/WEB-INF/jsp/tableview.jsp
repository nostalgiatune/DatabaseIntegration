<%-- 
    Document   : tableview
    Created on : 8.12.2015, 10:03:21
    Author     : Tuoppi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <c:forEach items="${albums}" var="album">
            
            <b><c:out value="${album.name}"/></b>
            <table border="1">
                <tr>
                    <th>Track</th>
                    <th>Duration</th>
                </tr>
                <c:forEach items="${album.tracks}" var="track">
                    <tr>
                        <td><c:out value="${track.name}"/></td>
                        <td><c:out value="${track.duration}"/></td>
                    </tr>
                </c:forEach>
                    
            </table>
            <br/>
            
        </c:forEach>
    
    </body>
</html>
