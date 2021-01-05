<%@tag description="Base template for all the app pages" pageEncoding="UTF-8" %>

<body style="overflow: hidden">
  <div id="covid-popup" class="hidden h-screen w-screen py-6 flex flex-col justify-center sm:py-12 text-center min-w-min min-h-min"
       style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); z-index:9000; background-color: rgba(72, 72, 72, .9)">
    <div class="py-3 sm:max-w-xl sm:mx-auto">
      <div class="bg-white border-4 border-red-900 text-gray-900  min-w-1xl flex flex-col rounded-xl shadow-lg divide-y divide-gray-300">
        <div class="px-12 py-5 pl-9 relative text-center">
          <h2 class="text-3xl font-semibold"> Are you sure? </h2>
          <a id="popup-cross" class="absolute cursor-pointer" style="top:10px; right:10px;"> <i class="fa fas fa-times fa-2x text-red-800"></i> </a>
        </div>
        <div class="w-full flex flex-col items-center">
          <div class="flex flex-col items-center py-6 space-y-3">
            <span class="text-lg text-gray-800"> You pressed the " I am covided " button, we just want to be sure you pressed it on purpose. </span>
          </div>
        </div>
        <div class="h-20 flex items-center justify-center between-5 space-x-6">
          <a id="covidButtonSubmit" class="cursor-pointer bg-green-200 rounded-full px-4 py-3 transition duration-300 ease-in-out hover:bg-green-300 mr-6">
            Yes, I am covided
          </a>
          <a id="popup_close_button" class="bg-red-200 cursor-pointer rounded-full px-4 py-3 transition duration-300 ease-in-out hover:bg-red-300 mr-6">
            No, I am not covided
          </a>
        </div>
      </div>
    </div>
  </div>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

  <script>
    const covidSubmitButton = $("#covidButtonSubmit");

    $(covidSubmitButton).click(() => {
        $.post('covided');
        popupAction();
    })
  </script>
</body>
