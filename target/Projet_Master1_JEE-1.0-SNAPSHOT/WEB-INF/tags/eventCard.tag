<%@tag description="Base template for all the app pages" pageEncoding="UTF-8" %>
<%@attribute name="imageSrc" required="true" %>
<%@attribute name="title" required="true" %>
<%@attribute name="content" required="true" %>
<%@attribute name="date" required="true" %>
<%@attribute name="user" required="false" %>

<html>
  <div class="max-w-sm w-full m-auto lg:max-w-full lg:flex justify-center">
    <div class="h-48 lg:h-auto lg:w-48 flex-none bg-cover rounded-t lg:rounded-t-none lg:rounded-l text-center overflow-hidden" style="background-image: url('${imageSrc}')">
    </div>
    <div class="border-r border-b border-l border-gray-400 lg:border-l-0 lg:border-t lg:border-gray-400 bg-white rounded-b lg:rounded-b-none lg:rounded-r p-4 flex flex-col justify-between leading-normal">
      <div class="mb-8">
        <div class="text-gray-900 font-bold text-xl mb-2"> ${title} </div>
        <p class="text-gray-700 text-base"> ${content} </p>
      </div>
      <div class="flex items-center">
        <img class="w-10 h-10 rounded-full mr-4" src="${user.getProfilePic()}" alt="Avatar of Jonathan Reinink">
        <div class="text-sm">
          <p class="text-gray-900 leading-none"> ${user.getFirstname()} ${user.getLastname()}</p>
          <p class="text-gray-600">${date}</p>
        </div>
      </div>
    </div>
  </div>
</html>