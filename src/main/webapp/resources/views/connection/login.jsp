<%--
  Created by IntelliJ IDEA.
  User: Matthieu
  Date: 03/01/2021
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<html>
  <jsp:include page="../../../includer.jsp"> <jsp:param name="title" value="Login"/></jsp:include>

  <t:page>
    <div class="text-center w-3/4 m-auto">
      <h1 class="title text-7xl uppercase text-yellow-700 my-24 noselect">Covid Tracker</h1>

      <div id="content" class="flex flex-wrap content-center justify-between items-center mt-9 space-x-3">
        <h2 class="p-5 text-3xl flex-1 w-full"> Connect to your account and inform your friends! </h2>

        <form id="register-form" class="flex-1 rounded shadow shadow-white p-5 bg-gray-200 dark:bg-gray-700 text-black"
              action="" method="post">

          <div class="mb-4">
            <t:input id="email" type="email" placeholder="Email address" autofocus="true" length="${sessionScope.usersColumnsLength.get('email')}" />
          </div>
          <div class="mb-4">
            <t:input id="password" type="password" placeholder="Password" length="${sessionScope.usersColumnsLength.get('password')}"/>
          </div>

          <div class="mb-4">
            <button id="register-submit-button" class="bg-yellow-600 font-bold py-2 px-4 rounded opacity-50 cursor-not-allowed text-white" type="submit">
              Log in
            </button>
          </div>

          <a class="hover:underline dark:text-white" href="${pageContext.request.contextPath}/register"> Or create a new account </a>
        </form>
      </div>
    </div>

    <script>
        const submitButton = document.getElementById('register-submit-button');
        const form = document.getElementById('register-form');
        const inputs = form.getElementsByTagName('input');

        submitButton.onclick = (event) => {
            if (submitButton.classList.contains("cursor-not-allowed")) event.preventDefault();
        }

        const checkIfFormValid = () => {
            for (const input of inputs) {
                if (input.value === '') return;
                else if (! submitButton.classList.contains("cursor-not-allowed")) {
                    submitButton.classList.add('cursor-not-allowed', 'opacity-50');
                }
            }

            submitButton.classList.remove('cursor-not-allowed', 'opacity-50');
        }

        form.addEventListener('input', checkIfFormValid);
    </script>
  </t:page>
</html>