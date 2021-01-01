<%--
  Created by IntelliJ IDEA.
  User: Matthieu
  Date: 30/12/2020
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<html>
  <jsp:include page="../../../includer.jsp"> <jsp:param name="title" value="Login"/></jsp:include>

  <t:page>
    <div id="login-form-container w-full h-full" style="position: relative">
      <form class="rounded px-8 pt-6 pb-8 mb-4 text-center w-2/3 md:w-1/3 shadow min-w-max" style="position: fixed; top: 50%; left:50%; transform: translate(-50%, -50%)">

        <p class="text-5xl"> Welcome to Covid tracker ! </p>
        <p> Create your account and help us prevent the covid spreading! </p>

        <div class="mb-4">
          <t:connection_input id="firstname" type="text" placeholder="Firstname" />
        </div>

        <div class="mb-4">
          <t:connection_input id="password" type="password" placeholder="Password" />
        </div>

        <div class="mb-4">
          <t:connection_input id="email" type="email" placeholder="Email address" />
        </div>
      </form>
    </div>
  </t:page>
</html>