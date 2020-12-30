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
  <jsp:include page="../../includer.jsp"/>

  <t:page title="login">
    <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
      <div class="mb-4">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="username">
          Username
        </label>
        <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="username" type="text" placeholder="Username">
      </div>
      <div class="mb-6">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="password">
          Password
        </label>
        <input class="shadow appearance-none border border-red-500 rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" id="password" type="password" placeholder="******************">
        <p class="text-red-500 text-xs italic">Please choose a password.</p>
      </div>
      <div class="flex items-center justify-between">
        <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button">
          Sign In
        </button>
        <a class="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800" href="#">
          Forgot Password?
        </a>
      </div>
    </form>
    <p class="text-center text-gray-500 text-xs">
      &copy;2020 Acme Corp. All rights reserved.
    </p>
  </t:page>
</html>

<%--<html>--%>
<%--  <head>--%>
<%--    <title> Login </title>--%>

<%--    <link href="resources/css/tailwind_css/tailwind.css" rel="stylesheet">--%>
<%--  </head>--%>

<%--  <body>--%>
<%--    <div id="root" class="w-full max-w-xs m-auto">--%>
<%--      <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">--%>
<%--        <div class="mb-4">--%>
<%--          <label class="block text-gray-700 text-sm font-bold mb-2" for="username">--%>
<%--            Username--%>
<%--          </label>--%>
<%--          <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="username" type="text" placeholder="Username">--%>
<%--        </div>--%>
<%--        <div class="mb-6">--%>
<%--          <label class="block text-gray-700 text-sm font-bold mb-2" for="password">--%>
<%--            Password--%>
<%--          </label>--%>
<%--          <input class="shadow appearance-none border border-red-500 rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" id="password" type="password" placeholder="******************">--%>
<%--          <p class="text-red-500 text-xs italic">Please choose a password.</p>--%>
<%--        </div>--%>
<%--        <div class="flex items-center justify-between">--%>
<%--          <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button">--%>
<%--            Sign In--%>
<%--          </button>--%>
<%--          <a class="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800" href="#">--%>
<%--            Forgot Password?--%>
<%--          </a>--%>
<%--        </div>--%>
<%--      </form>--%>
<%--      <p class="text-center text-gray-500 text-xs">--%>
<%--        &copy;2020 Acme Corp. All rights reserved.--%>
<%--      </p>--%>
<%--    </div>--%>
<%--  </body>--%>
<%--</html>--%>
