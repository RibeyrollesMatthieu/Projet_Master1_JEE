<%@tag description="" pageEncoding="UTF-8" %>
<%@attribute name="id" required="true" %>
<%@attribute name="label" required="true" %>

<label class="text-white" for="${id}">${label}&nbsp;</label>
<input id="${id}" type="time" name="${id}" class="
      focus:outline-none
      focus:ring-2
      focus:ring-yellow-600
      focus:border-transparent
      rounded
      p-2
      appearance-none
      shadow"/>

<script>
  document.getElementById(${id}).addEventListener("input", function(e) {
    const reTime = /^([0-1][0-9]|2[0-3]):[0-5][0-9]$/;
    const time = this.value;
    if (reTime.exec(time)) {
      const minute = Number(time.substring(3,5));
      const hour = Number(time.substring(0,2)) % 12 + (minute / 60);
    }
  });
</script>