<%@tag description="Base template for all the app pages" pageEncoding="UTF-8" %>

<%@attribute name="title" required="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<html>
  <body>
    <header>
      <jsp:invoke fragment="header" />
    </header>

    <div id="root" class="dark:bg-gray-500">
      <jsp:doBody />
    </div>

    <footer>
      <jsp:invoke fragment="footer" />
    </footer>
  </body>
</html>