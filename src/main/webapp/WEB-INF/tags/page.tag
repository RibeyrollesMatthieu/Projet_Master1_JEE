<%@tag description="Base template for all the app pages" pageEncoding="UTF-8" %>

<html>
  <body class="bg-gray-50 dark:bg-gray-800 dark:text-white">
    <header>

    </header>

    <div id="root" class="pb-14">
      <jsp:doBody />
    </div>

    <footer>
      <div class="py-3 px-4 text-center fixed left-0 bottom-0 right-0 z-40
        bg-gray-800 text-white
        dark:bg-gray-900">

        &copy; 2021 <a href="${pageContext.request.contextPath}/">covid-tracker.com</a>
      </div>
    </footer>
  </body>
</html>