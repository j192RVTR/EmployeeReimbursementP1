<div class="form-wrapper">
    <h1>
        <%=request.getAttribute("header")!=null
        ? request.getAttribute("header")
        : "No Header Loaded!"%>
    </h1>

    <form action="<%=request.getAttribute("action")%>" method="post">
        <div>
                <label for="username">Username </label>
                <input type="text" name="username" id="username" required <%=request.getAttribute("username") != null ? "value=" + request.getAttribute("username") : ""%> <%=!"".equals(request.getAttribute("action")) ? "" : "disabled"%>>
        </div>
        <div>
            <label for="name">Name </label>
            <input type="text" name="name" id="name" required <%=request.getAttribute("name") != null ? "value=" + request.getAttribute("name") : ""%> <%=!"".equals(request.getAttribute("action")) ? "" : "disabled"%>>
        </div>
        <div><label for="email">Email </label> <input type="email" name="email" id="email" required <%=request.getAttribute("email") != null ? "value=" + request.getAttribute("email") : ""%> <%=!"".equals(request.getAttribute("action")) ? "" : "disabled"%>></div>
        <div>
            <label for="male" style="display: inline;">Male </label> <input type="radio" name="gender" id="male" value="Male" required <%="Male".equals(request.getAttribute("gender")) ? "checked" : ""%> <%=!"".equals(request.getAttribute("action")) ? "" : "disabled"%>>
            <label for="female" style="display: inline;">Female </label><input type="radio" name="gender" id="female" value="Female" <%="Female".equals(request.getAttribute("gender")) ? "checked" : ""%> <%=!"".equals(request.getAttribute("action")) ? "" : "disabled"%>>
        </div>
        <div><label for="country">Country </label> <select name="country" id="country" <%=!"".equals(request.getAttribute("action")) ? "" : "disabled"%>>
            <option value="USA" <%="USA".equals(request.getAttribute("country")) ? "selected" : ""%>>USA</option>
            <option value="UK" <%="UK".equals(request.getAttribute("country")) ? "selected" : ""%>>UK</option>
            <option value="France" <%="France".equals(request.getAttribute("country")) ? "selected" : ""%>>France</option>
            <option value="Japan" <%="Japan".equals(request.getAttribute("country")) ? "selected" : ""%>>Japan</option>
            <option value="Brazil" <%="Brazil".equals(request.getAttribute("country")) ? "selected" : ""%>>Brazil</option>
            <option value="India" <%="India".equals(request.getAttribute("country")) ? "selected" : ""%>>India</option>
        </select></div>

        <div><label for="manager" style="display: inline;">Manager</label><input type="checkbox" name="manager" id="manager" <%="create".equals(request.getAttribute("action")) ? "" : "disabled"%> <%="true".equals(request.getAttribute("manager")) ? "checked" : ""%>></div>

        <div class="center">
            <input class="continue" type="submit" value="Submit" <%=!"".equals(request.getAttribute("action")) ? "" : "hidden"%>>
        </div>
    </form>
</div>