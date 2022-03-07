<div class="form-wrapper">
    <h1>
        <%=request.getAttribute("header")!=null
        ? request.getAttribute("header")
        : "No Header Loaded!"%>
    </h1>

    <form action=<%=request.getAttribute("action")%> method="post">
        <div>
            <label for="name">Name </label>
            <input type="text" name="name" id="name" required <%=request.getAttribute("name") != null ? "value=" + request.getAttribute("name") : ""%> <%="\"Delete\"".equals(request.getAttribute("submit")) ? "disabled" : ""%>>
        </div>
        <div><label for="email">Email </label> <input type="email" name="email" id="email" required <%=request.getAttribute("email") != null ? "value=" + request.getAttribute("email") : ""%> <%="\"Delete\"".equals(request.getAttribute("submit")) ? "disabled" : ""%>></div>
        <div>
            <label for="male" style="display: inline;">Male </label> <input type="radio" name="gender" id="male" value="male" required <%="male".equals(request.getAttribute("gender")) ? "checked" : ""%> <%="\"Delete\"".equals(request.getAttribute("submit")) ? "disabled" : ""%>>
            <label for="female" style="display: inline;">Female </label><input type="radio" name="gender" id="female" value="female" <%="female".equals(request.getAttribute("gender")) ? "checked" : ""%> <%="\"Delete\"".equals(request.getAttribute("submit")) ? "disabled" : ""%>>
        </div>
        <div><label for="country">Country </label> <select name="country" id="country" <%="\"Delete\"".equals(request.getAttribute("submit")) ? "disabled" : ""%>>
            <option value="USA" <%="USA".equals(request.getAttribute("country")) ? "selected" : ""%>>USA</option>
            <option value="UK" <%="UK".equals(request.getAttribute("country")) ? "selected" : ""%>>UK</option>
            <option value="France" <%="France".equals(request.getAttribute("country")) ? "selected" : ""%>>France</option>
            <option value="Japan" <%="Japan".equals(request.getAttribute("country")) ? "selected" : ""%>>Japan</option>
            <option value="Brazil" <%="Brazil".equals(request.getAttribute("country")) ? "selected" : ""%>>Brazil</option>
            <option value="India" <%="India".equals(request.getAttribute("country")) ? "selected" : ""%>>India</option>
        </select></div>

        <div class="center">
            <input class="continue" type="submit" value=<%=request.getAttribute("submit") != null ? request.getAttribute("submit") : "\"Submit\""%>>
        </div>
        <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
        <input type="hidden" name="hidden_name" value=<%=request.getAttribute("name")%>>
        <input type="hidden" name="hidden_email" value=<%=request.getAttribute("email")%>>
        <input type="hidden" name="hidden_gender" value=<%=request.getAttribute("gender")%>>
        <input type="hidden" name="hidden_country" value=<%=request.getAttribute("country")%>>


    </form>
</div>