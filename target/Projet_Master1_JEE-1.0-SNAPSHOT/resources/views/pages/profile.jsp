<%--
  Created by IntelliJ IDEA.
  User: Matthieu
  Date: 03/01/2021
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
  <jsp:include page="../../../includer.jsp"> <jsp:param name="title" value="Covid Tracker"/></jsp:include>

  <body>
    <jsp:include page="../header.jsp" />

    <t:page>
      <div class="text-center w-3/4 m-auto">
        <h1 class="text-7xl uppercase text-yellow-700 my-24 noselect"> My profile </h1>

        <div id="content" class="flex flex-wrap content-center justify-between items-center mt-9 space-x-3">
          <h2 class="p-5 text-3xl flex-1 w-full"> You can change any information you'd like to! </h2>

          <form id="register-form" class="flex-1 rounded shadow shadow-white p-5 bg-gray-200 dark:bg-gray-700 text-black"
                action="" method="post">
            <div class="mb-4"> <t:input id="firstname" type="text" placeholder="Firstname" autofocus='true' label="true"/> </div>

            <div class="mb-4"> <t:input id="lastname" type="text" placeholder="Lastname" label="true"/> </div>

            <div class="mb-4"> <t:input id="password" type="password" placeholder="Password" label="true"/> </div>

            <div class="mb-4"> <t:input id="email" type="email" placeholder="Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" label="true"/> </div>

            <div class="mb-4"> <t:input id="date" type="text" placeholder="Birthdate" label="true"/> </div>

            <button id="register-submit-button" class="bg-yellow-600 font-bold py-2 px-4 rounded opacity-50 cursor-not-allowed text-white" type="submit">
              Create account
            </button>
          </form>
        </div>
      </div>
    </t:page>
  </body>
</html>
