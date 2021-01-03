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
    <div class="text-center w-3/4 m-auto">
      <h1 class="title text-7xl uppercase text-yellow-700 my-24 noselect">Covid Tracker</h1>

      <div id="content" class="flex flex-wrap content-center justify-between items-center mt-9 space-x-3">
        <h2 class="p-5 text-3xl flex-1 w-full"> Create your account and help us prevent the covid spreading! </h2>

        <form id="register-form" class="flex-1 rounded shadow shadow-white p-5 bg-gray-200 dark:bg-gray-700 text-black"
              action="" method="post">
          <div class="mb-4"> <t:input id="firstname" type="text" placeholder="Firstname" autofocus='true'/> </div>

          <div class="mb-4"> <t:input id="lastname" type="text" placeholder="Lastname" /> </div>

          <div class="mb-4"> <t:input id="password" type="password" placeholder="Password" /> </div>
          <div class="mb-4"> <t:input id="confirm-password" type="password" placeholder="Confirm password" /> </div>

          <div class="mb-4"> <t:input id="email" type="email" placeholder="Email address" /> </div>

          <div class="mb-4"> <t:input id="date" type="text" placeholder="Birthdate" /> </div>

          <button id="register-submit-button" class="bg-yellow-600 font-bold py-2 px-4 rounded opacity-50 cursor-not-allowed text-white" type="submit">
            Create account
          </button>
        </form>
      </div>
    </div>

    <script>
        const fakeDate = document.getElementById('date');
        const submitButton = document.getElementById('register-submit-button');
        const form = document.getElementById('register-form');
        const inputs = form.getElementsByTagName('input');

        const changeToDate = () => fakeDate.type = 'date';

        fakeDate.onclick = changeToDate;
        fakeDate.onfocus = changeToDate;

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