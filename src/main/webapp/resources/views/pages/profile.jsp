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
            <div class="relative inline-flex">
              <svg class="w-2 h-2 absolute top-0 right-0 m-4 pointer-events-none" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 412 232"><path d="M206 171.144L42.678 7.822c-9.763-9.763-25.592-9.763-35.355 0-9.763 9.764-9.763 25.592 0 35.355l181 181c4.88 4.882 11.279 7.323 17.677 7.323s12.796-2.441 17.678-7.322l181-181c9.763-9.764 9.763-25.592 0-35.355-9.763-9.763-25.592-9.763-35.355 0L206 171.144z" fill="#648299" fill-rule="nonzero"/></svg>
              <select id="place" name="place" class="
                rounded-full text-gray-900 h-10 pl-5 pr-10 bg-white hover:border-gray-400
                focus:outline-none
                focus:ring-2
                focus:ring-yellow-600
                focus:border-transparent
                rounded
                p-2
                appearance-none
                shadow">
                <option value="-1">Place of the event</option>
                <c:forEach items="${sessionScope.places}" var="place">
                  <option value="${place.getId()}">${place.getName()}</option>
                </c:forEach>

                <option value="-2">New place</option>
              </select>

              <input type="hidden" id="placeId" name="placeId">

            </div>
          </div>


          <div class="mb-4">
            <button id="submit-new-event" class="bg-yellow-600 font-bold py-2 px-4 rounded opacity-50 cursor-not-allowed text-white" type="submit">
              Add event
            </button>
          </div>

        </form>

        <form action="places" method="post">
          <div id="new-place-form" class="text-gray-900 mb-4 bg-blue-300 p-5 hidden">
            <div class="mb-4">
              <t:input id="new-place-name" type="text" placeholder="New place's name"/>
            </div>
            <div class="mb-4">
              <t:input id="new-place-address" type="text" placeholder="New place's address"/>
            </div>
            <button id="new-place-submit" class="bg-yellow-600 font-bold py-2 px-4 rounded opacity-50 cursor-not-allowed text-white">
              Create new place
            </button>
          </div>
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
          const newPlace = document.getElementById("new-place-form");

          document.getElementById("new-place-submit").onclick = (event) => {
            event.preventDefault();

            const name = document.getElementById("new-place-name").value;
            const address = document.getElementById("new-place-address").value;

            $.post('places?name=' + name + '&address=' + address);
            window.location = window.location;
            //
            // console.log('coucou');

            document.getElementById("new-place-form").classList.toggle("hidden");
          }

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

          // //FIXME button not changing back color when we erase a text
          const checkIfEventFormIsValid = () => {
            toggleNewPLace();

            // for (const input of eventInputs) {
            //   console.log(input);
              if (document.getElementById("place").value === "-1" || document.getElementById("place").value === "-2") {
                eventSubmit.classList.add('cursor-not-allowed', 'opacity-50');
                return;
              }
              else if (! eventSubmit.classList.contains("cursor-not-allowed")) {
                eventSubmit.classList.add('cursor-not-allowed', 'opacity-50');
              }
            // }

            eventSubmit.classList.remove('cursor-not-allowed', 'opacity-50');
          }

          const checkForNewIsValid = () => {
            for (const input of newPlace.getElementsByTagName('input')) {
              if (input.value === '' || document.getElementById("place").value === "Place of the event") return;
              else if (! document.getElementById("new-place-submit").classList.contains("cursor-not-allowed")) {
                document.getElementById("new-place-submit").classList.add('cursor-not-allowed', 'opacity-50');
              }
            }

            document.getElementById("new-place-submit").classList.remove('cursor-not-allowed', 'opacity-50');
          }

          const toggleNewPLace = () => {
            if (document.getElementById("place").value != '-2') {
              if (! newPlace.classList.contains("hidden")) newPlace.classList.add("hidden");
            } else {
              if (newPlace.classList.contains("hidden")) newPlace.classList.remove("hidden");
            }
          }

          form.addEventListener('input', () => allowButton(submitButton));
          newPlace.addEventListener('input', checkForNewIsValid);
          addEventForm.addEventListener('input', checkIfEventFormIsValid);
      </script>
    </t:page>
  </body>
</html>
