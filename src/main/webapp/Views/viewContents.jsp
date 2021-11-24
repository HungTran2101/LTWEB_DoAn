<body>
	
	
	<%@ page import="model.ContentModel" %>
	<%@ page import="dao.ContentDAO" %>
	<%@ page import="dao.MemberDAO" %>
	<%@ page import="java.util.List" %>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="java.text.SimpleDateFormat" %>
	<%@ page import="common.Constants" %>
	<%
		String pageNumber = request.getParameter("pageNumber");
		String search = request.getParameter("search");
		
		
		
		boolean role = MemberDAO.getRoleById();
		
		
		int pagination = 1;
		if (pageNumber != null)
			pagination = Integer.parseInt(pageNumber);
		if (search == null)
			search = "";
		
		
		int count = 0;
		
		List<ContentModel> lstContentsByPage;
	
		
		if (role==true){
			count = ContentDAO.getAllContents(search);
			lstContentsByPage = ContentDAO.getAllContentsByAdmin(search, (pagination-1)*10);
		}
		else{
			count = ContentDAO.getAllContentsBySearch(search);
			lstContentsByPage = ContentDAO.searchContents(search, (pagination-1)*10);
		}
			
		int endPage = count/10;
		if (count%10 != 0)
			endPage++;

	%>
	
	<div id = "mySpinner">
		 	Loading
	</div>


	<div id = "myView" class = "w3-animate-bottom">
		<div class = "p-4 myView">
	        <h1>View Contents</h1>
	        <hr />
	        <h5 class = "border bg-light p-2 px-3 mb-0 rounded-top">View Content List</h5>
	        <div class="p-3 border border-top-0 rounded-bottom">
		        <table class = "table table-striped border">
		            <thead>
		            	<tr>
			            	<th class = "border">#</th>
			                <th class = "border">Title</th>
			                <th class = "border">Brief</th>
			                <th class = "border">Created Date</th>
			                <%if (role == true){ %>
			                <th class = "border">Username</th>
			                <%} %>
			                <th class = "border">Actions</th>
		            	</tr>
		            </thead>
		            <tbody>
						<%for (int i =0; i<lstContentsByPage.size(); ++i) {%>
							<tr>
								<td class = "border"><%= lstContentsByPage.get(i).getId() %></td>
								<td class = "border"><%= lstContentsByPage.get(i).getTitle() %></td>
								<td class = "border"><%= lstContentsByPage.get(i).getBrief() %></td>
								<td class = "border"><%= new SimpleDateFormat("dd/MM/yyyy HH:mm").format(lstContentsByPage.get(i).getCreateDate()) %></td>
								<%if (role == true) {%>
								<td class = "border"><%= lstContentsByPage.get(i).getUsername() %></td>
								<%} %>
								<td class = "border">
									<button class = "green px-3 border-0"><a class = "text-decoration-none text-light" href = "editContent.tiles?id=<%=lstContentsByPage.get(i).getId()%>">Edit</a></button>
									<button class = "green border-0"><a class = "text-decoration-none text-light" href = "delete?id=<%=lstContentsByPage.get(i).getId()%>">Delete</a></button>
								</td>
							</tr>
						<%} %>
		            </tbody>
		        </table>
	        </div>
	        <div class = "myPage">
				<nav aria-label="Page navigation example text-center">
				  <ul class="pagination">
				  	<%if (pagination == 1) {%>
					    <li class="page-item disabled">
					      <a class="page-link" href="#" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					        <span class="sr-only">Previous</span>
					      </a>
					    </li>
				    <%}else{ %>
				    	<li class="page-item">
					      <a class="page-link" href="viewContents.tiles?pageNumber=<%=pagination - 1%>&search=<%=search %>" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					        <span class="sr-only">Previous</span>
					      </a>
					    </li>
					<%} %>
		        <%for (int i=1; i<=endPage; ++i) {%>
		        	<%if (i == pagination) {%>
		        		<li class="page-item active"><a class = "page-link" href = "viewContents.tiles?pageNumber=<%=i%>&search=<%=search %>"><%=i %></a></li>
		        	<%}else {%>
		        		<li class="page-item"><a class = "page-link" href = "viewContents.tiles?pageNumber=<%=i%>&search=<%=search %>"><%=i %></a></li>
		        <%}} %>
		        <%if (pagination == endPage) { %>
	     			<li class="page-item disabled">
				      <a class="page-link" href="#" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				        <span class="sr-only">Next</span>
				      </a>
				    </li>
				 <%}else { %>
				 	<li class="page-item">
				      <a class="page-link" href="viewContents.tiles?pageNumber=<%=pagination + 1%>&search=<%=search %>" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				        <span class="sr-only">Next</span>
				      </a>
				    </li>
				<%} %>
				  </ul>
			</nav>
	    </div>
	</div>
</div>
	
	<script>
		setTimeout(function(){
			 document.getElementById("mySpinner").style.display = "none";
			 document.getElementById("myView").style.display = "flex";
		}, 5000)
	</script>
	
</body>