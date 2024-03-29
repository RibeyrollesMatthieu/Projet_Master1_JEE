<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Template for a base connection form input" pageEncoding="UTF-8" %>

<div id="full-bar" class="w-full py-2 px-5 bg-white mb-9 rounded-xl flex align-center justify-between text-gray-900">
  <input  id="friends-search-bar" class="focus:outline-none flex-1" type="text" autofocus placeholder="Look for someone.."
          onfocusin="ableFocus()" onfocusout="disableFocus()">
  <a class="ml-2 cursor-pointer" id="search-icon">  <i class="fa fas fa-2x fa-search text-gray-900"></i> </a>

  <script>
    const fullBar = document.getElementById("full-bar");
    const searchInput = document.getElementById("friends-search-bar");
    const searchIcon = document.getElementById("search-icon");

    const ableFocus = () => {
      fullBar.classList.add("ring-2", "ring-yellow-600", "border-transparent");
    }

    const disableFocus = () => {
        fullBar.classList.remove("ring-2", "ring-yellow-600", "border-transparent");
    }

    searchInput.addEventListener('input', () => searchUser(searchInput.value));
  </script>
</div>