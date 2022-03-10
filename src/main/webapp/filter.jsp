<div>
    <div <%=request.getSession().getAttribute("manager") != null && request.getSession().getAttribute("manager").equals(true)  ? "" : "hidden"%> >
        <label for="search">Search for Employee</label><input type="text" id="search" onkeyup="myfunction()">
    </div>
    <label for="all" style="display: inline;">All </label> <input type="radio" name="filter" id="all" value="all" onchange="myfunction()" checked>
    <label for="pending" style="display: inline;">Pending </label> <input type="radio" name="filter" id="pending" value="pending" onchange="myfunction()">
    <label for="resolved" style="display: inline;">Resolved </label><input type="radio" name="filter" id="resolved" value="resolved" onchange="myfunction()">
</div>
<script>
function myfunction() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue, search, searchValue;
  search = document.getElementById("search").value;
  const radioButtons = document.querySelectorAll('input[name="filter"]');
  for(const radioButton of radioButtons){
    if(radioButton.checked){
        filter = radioButton.value;
    }
  }
  if(filter == "all"){
    filter = "";
  }
  if(filter=="resolved"){
    filter = "ed";
  }
  table = document.getElementsByTagName("tbody")[0];
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    searchValue = tr[i].getElementsByTagName("td")[1].innerText;
    td = tr[i].getElementsByTagName("td")[3];
    if (td) {
      txtValue = td.textContent || td.innerText;
      console.log(search);
      console.log(searchValue);
      console.log();
      if (txtValue.toUpperCase().includes(filter.toUpperCase()) && searchValue.toUpperCase().includes(search.toUpperCase())) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}
</script>