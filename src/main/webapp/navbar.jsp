<div class="nav">
    <a class="nav" href="/EmployeeReimbursementP1" <%=request.getSession().getAttribute("name") != null && !request.getSession().getAttribute("name").equals("")  ? "" : "hidden"%> >Home</a>
    <a class="nav" href="add" <%=request.getSession().getAttribute("name") != null && !request.getSession().getAttribute("name").equals("")  ? "" : "hidden"%> >Submit Request</a>
    <a class="nav" href="view" <%=request.getSession().getAttribute("name") != null && !request.getSession().getAttribute("name").equals("")  ? "" : "hidden"%> >View Requests</a>
    <a class="nav" href="profile" <%=request.getSession().getAttribute("name") != null && !request.getSession().getAttribute("name").equals("")  ? "" : "hidden"%> >View Profile</a>
    <a class="nav" href="update" <%=request.getSession().getAttribute("name") != null && !request.getSession().getAttribute("name").equals("")  ? "" : "hidden"%> >Update Info</a>
    <a class="nav" href="create" <%=request.getSession().getAttribute("manager") != null && request.getSession().getAttribute("manager").equals(true)  ? "" : "hidden"%> >Create Employee</a>
    <a class="nav" href="employee" <%=request.getSession().getAttribute("manager") != null && request.getSession().getAttribute("manager").equals(true)  ? "" : "hidden"%> >View Employees</a>

    <div style="display: flex; margin-left: auto;">
        <a class="nav" href="login" <%=request.getSession().getAttribute("name") != null && !request.getSession().getAttribute("name").equals("")  ? "hidden" : ""%> >Login</a>
        <a class="nav" href="logout" <%=request.getSession().getAttribute("name") != null && !request.getSession().getAttribute("name").equals("")  ? "" : "hidden"%> >Logout</a>
    </div>



</div>