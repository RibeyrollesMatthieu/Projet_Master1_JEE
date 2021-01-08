<%--
  Created by IntelliJ IDEA.
  User: Matthieu
  Date: 03/01/2021
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <jsp:include page="../../../includer.jsp"> <jsp:param name="title" value="Profile"/></jsp:include>

  <body>
    <jsp:include page="../header.jsp" />

    <t:page>
      <div class="text-center w-3/4 m-auto">
        <h1 class="text-7xl uppercase text-yellow-700 my-24 noselect"> My profile </h1>

        <div id="form-content" class="flex flex-wrap content-center justify-between items-center mt-9 space-x-3">
          <h2 class="p-5 text-3xl flex-1 w-full">
            You can change any information you'd like to!
            Or <a class="text-yellow-700 hover:underline" href="${pageContext.request.contextPath}/logout"> disconnect. </a>
          </h2>

          <form id="register-form" class="flex-1 rounded shadow shadow-white p-5 bg-gray-200 dark:bg-gray-700 text-black"
                action="" method="post">
            <div class="mb-4">
              <t:input id="firstname" type="text" placeholder="Firstname&nbsp;" autofocus='true' label="true" value="${sessionScope.user.getFirstname()}" length="${sessionScope.columnsLength.get('firstname')}"/>
            </div>
            <div class="mb-4">
              <t:input id="lastname" type="text" placeholder="Lastname&nbsp;" label="true" value="${sessionScope.user.getLastname()}" length="${sessionScope.columnsLength.get('lastname')}"/>
            </div>
            <div class="mb-4">
              <t:input id="password" type="password" placeholder="New password&nbsp;" label="true" label_text="Password&nbsp;" length="${sessionScope.columnsLength.get('password')}"/>
            </div>
            <div class="mb-4">
              <t:input id="email" type="email" placeholder="Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" label="true" value="${sessionScope.user.getEmail()}" length="${sessionScope.columnsLength.get('email')}"/>
            </div>
            <div class="mb-4">
              <t:input id="date" type="text" placeholder="Birthdate&nbsp;" label="true" value="${sessionScope.user.getBdate()}" length="${sessionScope.columnsLength.get('birthdate')}"/>
            </div>

            <button id="register-submit-button" class="bg-yellow-600 font-bold py-2 px-4 rounded opacity-50 cursor-not-allowed text-white" type="submit">
              Save modifications
            </button>
          </form>
        </div>

        <h1 class="text-7xl uppercase text-yellow-700 my-24 noselect"> My events <i id="event-form-displayer" class="cursor-pointer hover:underline fas fa fa-plus"></i> </h1>

        <form id="add-event-form" class="hidden flex-1 rounded shadow shadow-white p-5 bg-gray-200 dark:bg-gray-700 text-black"
              action="?add-event=true" method="post">

          <div class="mb-4">
            <t:input id="title" type="text" placeholder="Event title&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" label="true"/>
          </div>
          <div class="mb-4">
            <t:input id="content" type="text" placeholder="Event content&nbsp;&nbsp;" label="true"/>
          </div>
          <div class="mb-4">
            <t:input id="date-event" type="text" label="true" placeholder="Date of the event" label_text="Event date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"/>
          </div>
          <div class="mb-4">
            <t:timePicker id="start" label="Event start"/>
          </div>
          <div class="mb-4">
            <t:timePicker id="end" label="Event end&nbsp;&nbsp;"/>
          </div>
          <div class="mb-4">
            <t:dropdown id="place" text="Place of the event"/>
          </div>

          <button id="submit-new-event" class="bg-yellow-600 font-bold py-2 px-4 rounded opacity-50 cursor-not-allowed text-white" type="submit">
            Save modifications
          </button>
        </form>

        <div class="flex flex-wrap justify-center align-center mt-9 space-y-3">
          <c:forEach items="${sessionScope.user.getEvents()}" var="event">
            <t:eventCard
              imageSrc="${event.getImage()}"
              title="${event.getTitle()}"
              content="${event.getContent()}"
              date="${event.getDate()}"
            />
          </c:forEach>
        </div>
      </div>

      <script>
          const fakeDate = document.getElementById('date');
          const submitButton = document.getElementById('register-submit-button');
          const eventSubmit = document.getElementById("submit-new-event");
          const form = document.getElementById('register-form');
          const inputs = form.getElementsByTagName('input');
          const eventDisplayer = document.getElementById("event-form-displayer");
          const addEventForm = document.getElementById("add-event-form");
          const fakeDateEvent = document.getElementById("date-event");
          const eventInputs = addEventForm.getElementsByTagName('input');
          const changeToDate = (input) => input.type = 'date';

          fakeDate.onclick = () => changeToDate(fakeDate);
          fakeDate.onfocus = () => changeToDate(fakeDate);
          fakeDateEvent.onclick = () => changeToDate(fakeDateEvent);
          fakeDateEvent.onfocus = () => changeToDate(fakeDateEvent);

          eventDisplayer.onclick = () => {
            if (addEventForm.classList.contains("hidden")){
              addEventForm.classList.remove("hidden");
              eventDisplayer.classList.remove("fa-plus");
              eventDisplayer.classList.add("fa-minus");
            } else {
              addEventForm.classList.add("hidden");
              eventDisplayer.classList.remove("fa-minus");
              eventDisplayer.classList.add("fa-plus");
            }
          }

          submitButton.onclick = (event) => {
              if (submitButton.classList.contains("cursor-not-allowed")) event.preventDefault();
          }

          eventSubmit.onclick = (event) => {
            if (eventSubmit.classList.contains("cursor-not-allowed")) event.preventDefault();
          }

          const allowButton = (button) => {
            if (! button.classList.contains("cursor-not-allowed")) {
              button.classList.add('cursor-not-allowed', 'opacity-50');
            }

            button.classList.remove('cursor-not-allowed', 'opacity-50');
          }

          //TODO duplicate code with login
          //FIXME button not changing back color when we erase a text
          const checkIfEventFormIsValid = () => {
            for (const input of eventInputs) {
              if (input.value === '' || document.getElementById("place").value === "Place of the event") return;
              else if (! eventSubmit.classList.contains("cursor-not-allowed")) {
                eventSubmit.classList.add('cursor-not-allowed', 'opacity-50');
              }
            }

            eventSubmit.classList.remove('cursor-not-allowed', 'opacity-50');
          }

          form.addEventListener('input', () => allowButton(submitButton));
          addEventForm.addEventListener('input', checkIfEventFormIsValid);

      </script>
    </t:page>
  </body>
</html>
