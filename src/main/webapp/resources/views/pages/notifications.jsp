<%--
  Created by IntelliJ IDEA.
  User: Matthieu
  Date: 07/01/2021
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <jsp:include page="../../../includer.jsp"> <jsp:param name="title" value="Covid Tracker"/></jsp:include>

  <body>
    <jsp:include page="../header.jsp" />

    <t:page>
      <div> <h1 class="text-5xl text-center my-8 flex-1"> My notifications </h1> </div>

      <div id="notifs" class="m-auto justify-center flex flex-wrap pt-2">
        <c:choose>
          <c:when test="${sessionScope.user.getNotificationsBean().size() == 0}">
            <div> <p class="text-3xl text-center my-8"> Nothing to see.. </p> </div>
          </c:when>

          <c:otherwise>
            <c:forEach items="${sessionScope.user.getNotificationsBean()}" var="notification">
              <t:notification
                title="${notification.getTitle()}"
                content="${notification.getContent()}"
                id="${notification.getId()}"
                status="${notification.getStatus()}"
                ownerId="${notification.getOwnerUser().getId()}"
                concernedId="${notification.getConcernedUser().getId()}"/>
            </c:forEach>
          </c:otherwise>
        </c:choose>
      </div>
    </t:page>
  </body>

  <script>
    const eraseNotif = (id) => {
      $.get("notifications?erase=" + id);
      reloadNotifs();
    }

    const reloadNotifs = () => {
        $("#notifs").load(window.location.href + " #notifs");
        $("#notif-badge").load(window.location.href + " #notif-badge");
    }

    const cancelRequestNotifButton = document.getElementById("cancel-request-notif-button");

    const cancelFriendRequestFromNotif = (idOwner, idConcerned, idNotif) => {
      postCancelRequest(idOwner)
        .then(() => eraseNotif(idNotif))
        .catch( () =>  console.log("Cannot post the cancel friend request."));
    }
  </script>
</html>
