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
                              isFriend="true"
                              img="${userSearchResult.getProfilePic()}"/>
                          </c:when>

                          <c:when test="${sessionScope.user.isPending(userSearchResult)}">
                            <c:set var="isfromMe" value="${sessionScope.user.getPendingStatus(userSearchResult)}" />
                            <t:friendCard
                              firstname="${userSearchResult.getFirstname()}"
                              lastname="${userSearchResult.getLastname()}"
                              id="${userSearchResult.getId()}"
                              isFriend="false"
                              requestFromMe="${isfromMe}"
                              img="${userSearchResult.getProfilePic()}"
                              />
                          </c:when>

                          <c:otherwise>
                            <t:friendCard
                              firstname="${userSearchResult.getFirstname()}"
                              lastname="${userSearchResult.getLastname()}"
                              id="${userSearchResult.getId()}"
                              isFriend="false"
                              searchResult="true"
                              img="${userSearchResult.getProfilePic()}"/>
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
                              img="${pendingBean.getProfilePic()}"
                              requestFromMe='${pendingBean.isRequestSentFromCurrentUser()}'/>
                          </c:forEach>
                        </c:when>

                        <c:otherwise>
                          <c:forEach items="${sessionScope.user.getFriends()}" var="friendBean">
                            <t:friendCard
                              firstname="${friendBean.getFirstname()}"
                              lastname="${friendBean.getLastname()}"
                              id="${friendBean.getId()}"
                              covided="${friendBean.isCovided()}"
                              isFriend="true"
                              img="${friendBean.getProfilePic()}"/>
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

    const reloadFriendSearch = () => $("#friends-changing-content").load(window.location.href + " #friends-changing-content");

    changeTab(true);

    pending.onclick = () => changeTab(false);
    friends.onclick = () => changeTab(true);
    document.onload = () => changeTab(true);

  </script>
</html>
