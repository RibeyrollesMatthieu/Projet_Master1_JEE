<%--
  Created by IntelliJ IDEA.
  User: Matthieu
  Date: 31/12/2020
  Time: 01:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
  <jsp:include page="../../../includer.jsp"> <jsp:param name="title" value="Whoops.."/></jsp:include>

  <t:page>
    <div style="
      height: 100%;
      background-image: url('https://images.unsplash.com/photo-1472566316349-bce77aa2a778?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80');
      background-repeat: no-repeat;
      background-size: cover;
      background-position: center;
      text-align: end;
      margin: auto; ">

      <div style="padding: 50px 100px">
        <p class="dark:text-white" style="
          font-size: 15vmin;
          font-family: fantasy;
          color: white;"> 404 </p>

        <p class="dark:text-white" style="
          font-size: 5vmin;
          font-family: fantasy;
          color: white;"> Take a break, we'll try to fix it asap &#128521;</p>

        <a href="/"> Back to home page </a>
      </div>

    </div>
  </t:page>
</html>
