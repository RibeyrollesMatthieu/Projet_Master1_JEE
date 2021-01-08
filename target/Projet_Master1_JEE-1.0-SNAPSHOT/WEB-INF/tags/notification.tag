<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Template for notifications" pageEncoding="UTF-8" %>

<%@attribute name="title" required="true" %>
<%@attribute name="content" required="true" %>
<%@attribute name="id" required="true" %>
<%@attribute name="status" required="true" %>
<%@attribute name="ownerId" required="true" %>
<%@attribute name="concernedId" required="true" %>


<div class="max-w-sm rounded overflow-hidden shadow-lg shadow-white bg-gray-300 m-4">
  <div class="px-6 py-4">
    <div class="relative font-bold text-xl mb-2 text-gray-900">
      ${title}
      <a id="erase-notification" class="absolute cursor-pointer" onclick="eraseNotif(${id})" style="top:-3px; right:-10px;"> <i class="fa fas fa-times fa-lg text-red-800"></i> </a>
    </div>
    <p class="text-gray-700 text-base"> ${content} </p>
  </div>

  <div class="px-6 py-4 text-right">
    <c:choose>
      <c:when test="${status.equals('sentRequest')}">
        <a id="cancel-request-notif-button" onclick="cancelFriendRequestFromNotif(${ownerId}, ${concernedId}, ${id})" class="cursor-pointer inline-block bg-red-200 hover:bg-red-300 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 ml-auto"> Cancel </a>
      </c:when>

      <c:when test="${status.equals('acceptableRequest')}">
        <a id="accept-request-notif-button" onclick="acceptFriendRequestFromNotif(${ownerId}, ${concernedId}, ${id})" class="cursor-pointer inline-block bg-red-200 hover:bg-red-300 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 ml-auto"> Accept </a>
        <a id="decline-request-notif-button" onclick="cancelFriendRequestFromNotif(${ownerId}, ${concernedId}, ${id})" class="cursor-pointer inline-block bg-red-200 hover:bg-red-300 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 ml-auto"> Decline </a>
      </c:when>
    </c:choose>
  </div>
</div>