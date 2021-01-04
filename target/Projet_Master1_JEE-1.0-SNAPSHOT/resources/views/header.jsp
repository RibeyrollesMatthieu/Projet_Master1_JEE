<%--
  Created by IntelliJ IDEA.
  User: Matthieu
  Date: 03/01/2021
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <header>
    <nav class="flex py-3 px-4 fixed top-0 left-0 right-0 items-center justify-between
          bg-gray-300 dark:bg-gray-900">


      <a href="${pageContext.request.contextPath}/profile" class="text-xl text-bold hover:text-yellow-700">
        <i class="fa fa-lg fas fa-user"></i>
      </a>

      <a href="${pageContext.request.contextPath}/notifications" class="text-xl text-bold hover:text-yellow-700">
        <i class="fa fa-lg far fa-comment"></i>
      </a>

      <a href="${pageContext.request.contextPath}/"
         class="text-2xl text-bold"> Covid Tracker </a>

      <a href="${pageContext.request.contextPath}/covided" class="text-xl text-bold hover:text-yellow-700">
        <i class="fa fa-lg fas fa-exclamation-triangle"></i>
      </a>

      <a href="${pageContext.request.contextPath}/friends" class="text-xl text-bold hover:text-yellow-700">
        <i class="fa fa-lg fas fa-users"></i>
      </a>
    </nav>
  </header>
</html>
