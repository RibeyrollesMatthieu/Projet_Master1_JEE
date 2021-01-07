<%@tag description="Base template for all the app pages" pageEncoding="UTF-8" %>

<html>
  <body class="bg-gray-50 dark:bg-gray-800 dark:text-white">
    <div id="root" class="pb-14 pt-14 px-5">
      <jsp:doBody />
    </div>

    <footer>
      <div class="py-3 px-4 text-center fixed left-0 bottom-0 right-0 z-50
        bg-gray-300 dark:bg-gray-900
        text-gray-700 dark:text-white">

        &copy; 2021 <a href="${pageContext.request.contextPath}/">covid-tracker.com</a>
      </div>
    </footer>
  </body>
</html>