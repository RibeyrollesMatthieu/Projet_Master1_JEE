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
  <jsp:include page="../../../includer.jsp"> <jsp:param name="title" value="Register"/></jsp:include>

  <%--  TODO: change display of register form because it is fixed on center if the screen, whenr resizing height, title and form fuse
  --%>
  <t:page>
    <div  class="login-form-container text-center w-2/3 md:w-1/3 min-w-max min-h-max m-auto"
          style="position:absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);">

      <p class="title text-5xl md:text-7xl lg:text-8xl font-bold p-5"> Covid Tracker </p>
      <p class="p-5"> Create your account and help us prevent the covid spreading! </p>


      <form class="rounded shadow p-5" style="">
        <div class="mb-4">
          <t:connection_input id="firstname" type="text" placeholder="Firstname" />
        </div>

        <div class="mb-4">
          <t:connection_input id="lastname" type="text" placeholder="Lastname" />
        </div>

        <div class="mb-4">
          <t:connection_input id="password" type="password" placeholder="Password" />
        </div>

        <div class="mb-4">
          <t:connection_input id="email" type="email" placeholder="Email address" />
        </div>

        <div class="mb-4">
          <t:connection_input id="date" type="text" placeholder="Birthdate" />
        </div>

        <label class="inline-flex items-center mt-3">
          <input type="checkbox" class="form-checkbox h-5 w-5 text-red-600" checked><span class="ml-2 text-gray-700">label</span>
        </label>


       <button id="register-submit-button" class="bg-yellow-600 font-bold py-2 px-4 rounded opacity-50 cursor-not-allowed" type="submit">Create account</button>
      </form>
    </div>

    <script type="text/javascript">
        const fakeDate = document.getElementById('date');
        const submitButton = document.getElementById('register-submit-button');

        const changeToDate = () => fakeDate.type = 'date';

        fakeDate.onclick = changeToDate;
        fakeDate.onfocus = changeToDate;

        submitButton.onclick = (event) => {
            if (submitButton.classList.contains("cursor-not-allowed")) event.preventDefault();
            else alert("lol");
        }
    </script>
  </t:page>
</html>