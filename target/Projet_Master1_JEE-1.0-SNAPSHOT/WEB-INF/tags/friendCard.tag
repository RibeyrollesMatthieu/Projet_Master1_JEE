<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Base template for all the app pages" pageEncoding="UTF-8" %>
<%@attribute name="firstname" required="true" %>
<%@attribute name="lastname" required="true" %>
<%@attribute name="covided" required="false" %>
<%@attribute name="isFriend" required="true" %>
<%@attribute name="id" required="true" %>
<%@attribute name="img" required="true" %>
<%@attribute name="requestFromMe" required="false" %>
<%@attribute name="searchResult" required="false" %>

<html>
  <tr class="hover:bg-gray-300 <%--cursor-pointer--%>">
    <td class="px-6 py-4 whitespace-nowrap">
      <div class="flex items-center">
        <div class="flex-shrink-0 h-10 w-10">
          <% System.out.println(img); %>
          <img class="h-10 w-10 rounded-full noselect" src="${img}" alt="${img}">
        </div>

        <div class="ml-4">
          <div class="text-sm font-medium text-gray-900 noselect"> ${firstname} </div>
          <div class="text-sm text-gray-500 noselect"> ${lastname}</div>
        </div>
      </div>
    </td>

    <% if (isFriend.equals("true")) { %>
      <td class="px-6 py-4 whitespace-nowrap">
        <span class="px-2 inline-flex leading-5 noselect ${covided ? "text-red-600" : "text-green-600"}">
          ${covided ? "Covided" : "Safe" }
        </span>
      </td>

      <td class="px-6 py-4 whitespace-nowrap text-right">
        <a href="#" class="text-blue-600 hover:text-blue-800 noselect">
          <span class="hidden sm:block"> See profile </span>
          <span class="block sm:hidden"> <i class="fas fa fa-lg fa-user"></i> </span>
        </a>
      </td>

      <td class="px-6 py-4 whitespace-nowrap text-right">
        <a class="cursor-pointer text-red-600 hover:text-red-800 noselect" onclick="deleteFriend(${id})">
          <span class="hidden sm:block"> Remove friends </span>
          <span class="block sm:hidden"> <i class="fas fa fa-lg fa-user-times"></i> </span>
        </a>
      </td>

    <% } else if (requestFromMe == null) { %>
      <% if (searchResult.equals("true")) { %>
        <td class="px-6 py-4 whitespace-nowrap text-right"></td>

        <td class="px-6 py-4 whitespace-nowrap text-right">
          <a href="#" class="text-blue-600 hover:text-blue-800 noselect">
            <span class="hidden sm:block"> See profile </span>
            <span class="block sm:hidden"> <i class="fas fa fa-lg fa-user"></i> </span>
          </a>
        </td>

        <td class="px-6 py-4 whitespace-nowrap text-right">
          <a class="cursor-pointer text-green-600 hover:text-green-800 noselect" onclick="sendFriendRequest(${id})">
            <span class="hidden sm:block"> Send request </span>
            <span class="block sm:hidden"> <i class="fas fa fa-lg fa-plus"></i> </span>
          </a>
        </td>


      <% } else { %>
        <td class="px-6 py-4 whitespace-nowrap text-right"></td>
      <% } %>

    <% } else if (requestFromMe.equals("false")) { %>
      <td class="px-6 py-4 whitespace-nowrap text-right">
        <a href="#" class="text-blue-600 hover:text-blue-800 noselect">
          <span class="hidden sm:block"> See profile </span>
          <span class="block sm:hidden"> <i class="fas fa fa-lg fa-user"></i> </span>
        </a>
      </td>

      <td class="px-6 py-4 whitespace-nowrap text-right">
        <a class="cursor-pointer text-green-600 hover:text-green-800 noselect" onclick="acceptFriendRequest(${id})">
          <span class="hidden sm:block"> Accept </span>
          <span class="block sm:hidden"> <i class="fas fa fa-lg fa-check"></i> </span>
        </a>
      </td>

      <td class="px-6 py-4 whitespace-nowrap text-right">
        <a class="cursor-pointer text-red-600 hover:text-red-800 noselect" onclick="declineFriendRequest(${id})">
          <span class="hidden sm:block"> Decline </span>
          <span class="block sm:hidden"> <i class="fas fa fa-lg fa-times"></i> </span>
        </a>
      </td>

    <% } else { %>

      <td class="px-6 py-4 whitespace-nowrap text-right">
        <span class="hidden sm:block px-2 inline-flex leading-5 noselect text-blue-600">
          You sent an invite
        </span>
      </td>

      <td class="px-6 py-4 whitespace-nowrap text-right">
        <a href="#" class="text-blue-600 hover:text-blue-800 noselect">
          <span class="hidden sm:block"> See profile </span>
          <span class="block sm:hidden"> <i class="fas fa fa-lg fa-user"></i> </span>
        </a>
      </td>

      <td class="px-6 py-4 whitespace-nowrap text-right">
        <a class="cursor-pointer text-red-600 hover:text-red-800 noselect" onclick="cancelFriendRequest(${id})">
          <span class="hidden sm:block"> Cancel </span>
          <span class="block sm:hidden"> <i class="fas fa fa-lg fa-times"></i> </span>
        </a>
      </td>
    <% } %>
  </tr>
</html>