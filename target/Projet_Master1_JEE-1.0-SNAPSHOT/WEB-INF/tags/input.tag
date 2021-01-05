<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Template for a base connection form input" pageEncoding="UTF-8" %>

<%@attribute name="id" required="true" %>
<%@attribute name="type" required="true" %>
<%@attribute name="length" required="false" %>
<%@attribute name="autofocus" required="false" %>
<%@attribute name="placeholder" required="false" %>
<%@attribute name="value" required="false" %>
<%@attribute name="label" required="false" %>
<%@attribute name="label_text" required="false" %>


<%--TODO: remove all the length verifications methods--%>
<html>
  <c:if test="${label == 'true'}">
    <span class="text-left"><label class="dark:text-white" for="${id}">
      <c:choose>
        <c:when test="${label_text != null}"> ${label_text} </c:when>
        <c:otherwise> ${placeholder} </c:otherwise>
      </c:choose>
    </label></span>
  </c:if>

  <input
    class="
      focus:outline-none
      focus:ring-2
      focus:ring-yellow-600
      focus:border-transparent
      rounded
      p-2
      appearance-none
      shadow
      w-auto
      lg:w-2/5"

    id="${id}"
    name="${id}"
    type="${type}"
    placeholder="${placeholder}"
    autofocus="${autofocus}"
    value="${value}"
    maxlength="${length}"
  >
</html>