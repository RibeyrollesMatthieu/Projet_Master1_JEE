<%--
  Created by IntelliJ IDEA.
  User: Matthieu
  Date: 03/01/2021
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<%--FIXME: when updating, cannot activate covid button--%>
  <header id="notif-badge">
    <nav class="flex py-3 px-4 fixed top-0 left-0 right-0 items-center justify-between
          bg-gray-300 dark:bg-gray-900 z-50">

      <a href="${pageContext.request.contextPath}/profile" class="text-xl text-bold hover:text-yellow-700">
        <i class="fa fa-lg fas fa-user"></i>
      </a>

      <a href="${pageContext.request.contextPath}/notifications" class="text-xl text-bold hover:text-yellow-700 relative">
        <i class="fa fa-lg far fa-comment"></i>

        <%-- TODO: can be replaced by a if --%>
        <c:choose>
          <c:when test="${sessionScope.user.getNotificationsBean().size() != 0}">
            <span class="absolute top-0 right-0 inline-flex items-center justify-center px-2 py-1 text-xs font-bold leading-none text-red-100 transform translate-x-1/2 -translate-y-1/2 bg-red-600 rounded-full">
                ${sessionScope.user.getNotificationsBean().size()}
            </span>
          </c:when>
        </c:choose>
      </a>


      <a href="${pageContext.request.contextPath}/"
         class="text-2xl text-bold"> Covid Tracker </a>

      <a id="covid-button" class="text-xl text-bold hover:text-yellow-700 cursor-pointer">
        <i class="fa fa-lg fas fa-exclamation-triangle"></i>
      </a>

      <a href="${pageContext.request.contextPath}/friends" class="text-xl text-bold hover:text-yellow-700">
        <i class="fa fa-lg fas fa-users"></i>
      </a>
    </nav>
  </header>

  <t:covided_alert iscovided="${sessionScope.user.isCovided()}"/>


  <script>
      const covidButton = document.getElementById("covid-button");
      const popupCross = document.getElementById("popup-cross");
      const popup = document.getElementById("covid-popup");
      const closeButton = document.getElementById("popup_close_button");
      const submitCovid = document.getElementById("covidButtonSubmit");

      const popupAction = () => {
          if (popup.classList.contains("hidden")) {
            document.getElementsByTagName('body')[0].classList.add('overflow-hidden');
            popup.classList.remove("hidden");
          }
          else{
            document.getElementsByTagName('body')[0].classList.remove('overflow-hidden');
            popup.classList.add("hidden");
          }
      }

      covidButton.onclick = popupAction;
      closeButton.onclick = popupAction;
      popupCross.onclick = popupAction;
  </script>
</html>
