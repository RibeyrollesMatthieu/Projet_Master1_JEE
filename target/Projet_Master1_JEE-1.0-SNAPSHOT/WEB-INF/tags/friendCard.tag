<%@tag description="Base template for all the app pages" pageEncoding="UTF-8" %>
<%@attribute name="firstname" required="true" %>
<%@attribute name="lastname" required="true" %>
<%@attribute name="covided" required="true" %>

<html>
  <tr class="hover:bg-gray-300 <%--cursor-pointer--%>">
    <td class="px-6 py-4 whitespace-nowrap">
      <div class="flex items-center">
        <div class="flex-shrink-0 h-10 w-10">
          <img class="h-10 w-10 rounded-full noselect" src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=facearea&amp;facepad=4&amp;w=256&amp;h=256&amp;q=60" alt="">
        </div>

        <div class="ml-4">
          <div class="text-sm font-medium text-gray-900 noselect"> ${firstname} </div>
          <div class="text-sm text-gray-500 noselect"> ${lastname}</div>
        </div>
      </div>
    </td>

<%--    <td class="px-6 py-4 whitespace-nowrap">--%>
<%--      <div class="text-sm text-gray-900 noselect">Regional Paradigm Technician</div>--%>
<%--      <div class="text-sm text-gray-500 noselect">Optimization</div>--%>
<%--    </td>--%>

    <td class="px-6 py-4 whitespace-nowrap">
      <span class="px-2 inline-flex leading-5 noselect ${covided ? "text-red-600" : "text-green-600"}">
        ${covided ? "Covided" : "Safe"}
      </span>
    </td>

    <td class="px-6 py-4 whitespace-nowrap text-right">
      <a href="#" class="text-blue-600 noselect"> See profile </a>
    </td>

    <td class="px-6 py-4 whitespace-nowrap text-right">
      <a href="#" class="text-red-600 noselect"> Remove friend </a>
    </td>

  </tr>
</html>