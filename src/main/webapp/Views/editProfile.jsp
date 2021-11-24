
<body>
	<%@ page import="model.MemberModel" %>
	<%@ page import="dao.MemberDAO" %>
	<%@ page import="common.Constants" %>
	<%
		MemberModel member = MemberDAO.getMemberById((Integer)request.getSession().getAttribute("userID"));
	%>
	<div class = "d-flex">
		<div class = "p-4 myEditForm">
	        <h1>Edit Profile</h1>
	        <hr />
	        <h5 class = "border bg-light p-2 px-4 mb-0">Profile Form Elements</h5>
	        <form class="border p-2" action = "EditMemberController"  method = "post">
	        
	            <div class = "myFrame">
	                <label class = "mb-2">First Name</label>
	                <input onkeyup="setValue()" id = "firstName" name = "firstName" type="text" pattern=".{3,30}" title="3 to 30 characters" maxlength="50" required class="form-control myInput" placeholder="Enter the first name" aria-describedby="basic-addon1" value = <%=member.getFirstName() %>>
	            </div>
	
	            <div class = "mt-2 myFrame">
	                <label class = "mb-2">Last Name</label>
	                <input name = "lastName" type="text" pattern=".{3,30}" title="3 to 30 characters" maxlength="50" required class="form-control myInput" placeholder="Enter the last name" aria-describedby="basic-addon1" value = <%=member.getLastName() %>>
	            </div>
	
	            <div class = "mt-2 myFrame">
	                <label class = "mb-2">Email</label>
	                <p><%=member.getEmail() %></p>
	            </div>
	
	            <div class = "mt-2 myFrame">
	                <label class = "mb-2">Phone</label>
	                <input name = "phone" type="text" pattern=".{9,13}" title="9 to 13 characters" required class="form-control myInput" placeholder="Enter your phone number" aria-describedby="basic-addon1" value = <%=member.getPhone() %>>
	            </div>
	
	            <div class = "mt-2 myFrame">
	                <label class = "mb-2">Description</label>
	                <textarea name = "description" maxlength="200" class="form-control myInput" id="exampleFormControlTextarea1" rows="3"><%=member.getDescription() %></textarea>
	            </div>
	
	            <div class = "mt-2 myFrame">
	                <input class = "myBtn" type = "submit" value = "Submit Button"/>
	                <button type="button" class = "myBtn"><a class = "text-decoration-none text-dark" href = "editProfile.tiles">Reset Button</a></button>
	                
	            </div>
	        </form>
	    </div>
	</div>
	
	<script>
    function resetFunc() {
        document.getElementById("firstName").value = "";
        document.getElementById("lastName").value = "";
        document.getElementById("phone").value = "";
        document.getElementById("description").value = "";
    }
    
    const setValue = () => {
    	const test = document.getElementById("firstName").value;
    	console.log(test);
    	
    }
    
	</script>
    
</body>
