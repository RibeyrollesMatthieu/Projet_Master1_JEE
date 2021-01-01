<%@tag description="Template for a base connection form input" pageEncoding="UTF-8" %>

<%@attribute name="id" required="true" %>
<%@attribute name="type" required="true" %>
<%@attribute name="placeholder" required="true" %>

<html>
  <input
    class="
      focus:outline-none
      focus:ring-2
      focus:ring-yellow-600
      focus:border-transparent
      rounded
      p-2
      appearance-none
      shadow"

    id=${id}
    type=${type}
    placeholder=${placeholder}
  >
</html>