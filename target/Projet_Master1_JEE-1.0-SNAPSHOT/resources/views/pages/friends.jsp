<%@ page import="server.database.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: Matthieu
  Date: 04/01/2021
  Time: 19:24
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
      <div> <h1 class="text-5xl text-center mt-5"> My friends </h1> </div>

      <div class="flex flex-column mt-9 justify-center">

        <div class="-my-2 <%--overflow-x-auto--%> sm:-mx-6 lg:-mx-8">
          <div class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
            <div> <t:search_bar /> </div>

            <div class="shadow overflow-hidden border-b border-gray-200 rounded-lg">

              <table class="min-w-full divide-y divide-gray-200">
                <div class="text-gray-900 bg-gray-200 flex flex-wrap justify-between text-center">
                  <div id="search-display" class="hidden flex-1 p-2"> Search result </div>
                  <div id="friends-display" class="people-display flex-1 p-2 hover:bg-gray-300 cursor-pointer"> Friends </div>
                  <div id="pending-display" class="people-display flex-1 p-2 hover:bg-gray-300 cursor-pointer"> Pending </div>
                </div>
                <tbody id="friends-changing-content"  class="bg-white divide-y divide-gray-200">
                  <c:choose>
                    <c:when test="${sessionScope.userSearch != null}">
                      <c:forEach items="${sessionScope.userSearch.getSearchResultList()}" var="userSearchResult">

                        <c:choose>
                          <c:when test="${sessionScope.user.getFriends().contains(userSearchResult)}">
                            <t:friendCard
                              firstname="${userSearchResult.getFirstname()}"
                              lastname="${userSearchResult.getLastname()}"
                              id="${userSearchResult.getId()}"
                              isFriend="true" />
                          </c:when>

<%--                          FIXME: contains not working--%>
                          <c:when test="${sessionScope.user.isPending(userSearchResult)}">
                            <c:set var="isfromMe" value="${sessionScope.user.getPendingStatus(userSearchResult)}" />
                            <t:friendCard
                              firstname="${userSearchResult.getFirstname()}"
                              lastname="${userSearchResult.getLastname()}"
                              id="${userSearchResult.getId()}"
                              isFriend="false"
                              requestFromMe="${isfromMe}"
                              />
                          </c:when>

                          <c:otherwise>
                            <t:friendCard
                              firstname="${userSearchResult.getFirstname()}"
                              lastname="${userSearchResult.getLastname()}"
                              id="${userSearchResult.getId()}"
                              isFriend="false"
                              searchResult="true" />
                          </c:otherwise>
                        </c:choose>
                      </c:forEach>
                    </c:when>

                    <c:otherwise>
                      <c:choose>
                        <c:when test="${param.get('cat') == 'pending'}">
                          <c:forEach items="${sessionScope.user.getPending()}" var="pendingBean">
                            <t:friendCard
                              firstname="${pendingBean.getFirstname()}"
                              lastname="${pendingBean.getLastname()}"
                              id="${pendingBean.getId()}"
                              isFriend="false"
                              requestFromMe='${pendingBean.isRequestSentFromCurrentUser()}'/>
                          </c:forEach>
                        </c:when>

                        <c:otherwise>
                          <c:forEach items="${sessionScope.user.getFriends()}" var="friendBean">
                            <t:friendCard
                              firstname="${friendBean.getFirstname()}"
                              lastname="${friendBean.getLastname()}"
                              id="${friendBean.getId()}"
                              isFriend="true" />
                          </c:forEach>
                        </c:otherwise>
                      </c:choose>
                    </c:otherwise>
                  </c:choose>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </t:page>
  </body>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

  <script>
    const bg = "bg-gray-300";
    const pending = document.getElementById("pending-display");
    const friends = document.getElementById("friends-display");
    const searchDisplay = document.getElementById("search-display");
    const parent = document.getElementById("friends-changing-content");

    const changeStatus = (target, toActivate) => {
      if (toActivate) target.classList.add('active', bg);
      else target.classList.remove('active', bg);
    }

    const reload = () => $("#friends-changing-content").load(window.location.href + " #friends-changing-content");

    const changeTab = (isFriends) => {
      const active = (isFriends ? friends : pending).classList.contains('active');
      if (isFriends) {
          if (active) {
            if (pending.classList.contains('active')) changeStatus(friends, false);
          } else {  // swap side, friends is now active
            window.history.pushState({}, '', '?cat=friends');
            reload();
            changeStatus(friends, true);
            changeStatus(pending, false);
          }
      } else {
          if (active) {
            if (friends.classList.contains('active')) changeStatus(pending, false);
          } else {  //swap sides, pending is ow active
            window.history.pushState({}, '', '?cat=pending');
            reload();
            changeStatus(pending, true);
            changeStatus(friends, false);
          }
      }
    }

    async function postAcceptRequest(id) { $.post('relation-update?accept=' + id); }
    async function postDelete(id) { $.post('relation-update?delete=' + id); }
    async function postCancelRequest(id) { $.post('relation-update?cancel=' + id); }
    async function postDeclineRequest(id) { $.post('relation-update?decline=' + id); }
    async function postSendRequest(id) { $.post('relation-update?add=' + id); }

    const acceptFriendRequest = (id) => {
      postAcceptRequest(id)
        .then(reload)
        .catch(() => console.log("Cannot post the accept friend request"));
    }

    const deleteFriend = (id) => {
      postDelete(id)
        .then(reload)
        .catch(() => console.log("Cannot post the delete friend request."));
    }

    const cancelFriendRequest = (id) => {
      postCancelRequest(id)
        .then(reload)
        .catch(() => console.log("Cannot post the cancel friend request."));
    }

    const declineFriendRequest = (id) => {
        postDeclineRequest(id)
            .then(reload)
            .catch(() => console.log("Cannot post the decline friend request"));
    }

    const sendFriendRequest = (id) => {
        postSendRequest(id)
            .then(reload)
            .catch(() => console.log("Cannot post the send friend request"));
    }

    //FIXME back to last tab when we do an action on a searched user
    const searchUser = (user) => {
        if (user.trim().length === 0) {
          searchDisplay.classList.add('hidden');
          friends.classList.remove('hidden');
          pending.classList.remove('hidden');
        } else {
            if (searchDisplay.classList.contains('hidden')) searchDisplay.classList.remove('hidden');
            if (! friends.classList.contains('hidden')) {
                friends.classList.add('hidden');
                pending.classList.add('hidden');
            }
            $.get('friends?search=' + user);
        }

        reload();
    }

    changeTab(true);

    pending.onclick = () => changeTab(false);
    friends.onclick = () => changeTab(true);
    document.onload = () => changeTab(true);

  </script>
</html>
