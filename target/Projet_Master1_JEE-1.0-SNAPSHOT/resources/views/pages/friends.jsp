<%--
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
        <div class="-my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
          <div class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
            <div> <t:search_bar /> </div>

            <div class="shadow overflow-hidden border-b border-gray-200 rounded-lg">

              <table class="min-w-full divide-y divide-gray-200">
                <tbody class="bg-white divide-y divide-gray-200">
                <c:forEach items="${sessionScope.user.getFriends()}" var="friendBean">
                    <t:friendCard
                      firstname="${friendBean.getFirstname()}"
                      lastname="${friendBean.getLastname()}"
                      covided="${friendBean.isCovided()}"/>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </t:page>
  </body>
</html>
