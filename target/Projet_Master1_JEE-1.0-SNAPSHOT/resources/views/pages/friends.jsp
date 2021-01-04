<%--
  Created by IntelliJ IDEA.
  User: Matthieu
  Date: 04/01/2021
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<html>
<jsp:include page="../../../includer.jsp"> <jsp:param name="title" value="Friends"/></jsp:include>

<t:page>
 <form action="
        ${pageContext.request.contextPath}/relation-update?from=${sessionScope.id}&to=1&status=P"
       method="post">
   <button type="submit">Add friand au fromage parce que c'est bon et que ça fait un bail que je n'en ai pas mangé un.</button>
 </form>
</t:page>
</html>