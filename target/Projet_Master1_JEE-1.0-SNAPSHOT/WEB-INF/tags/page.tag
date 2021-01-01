<%@tag description="Base template for all the app pages" pageEncoding="UTF-8" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<html>
  <body class="dark:bg-gray-800">
    <header>
      <jsp:invoke fragment="header" />
    </header>

    <div id="root">
      <jsp:doBody />
    </div>

    <footer>
      <jsp:invoke fragment="footer" />
    </footer>
  </body>
</html>