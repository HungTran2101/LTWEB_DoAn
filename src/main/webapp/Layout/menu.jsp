
<body>
		<%
			String search = request.getParameter("search");
			if (search == null)
				search = "";
		%>
        <ul class="list-group bg-light myMenu w3-animate-left border-right">
            <li class="list-group-item bg-light py-3 border-top-0">
                <div class="input-group bg-light">
                	<form action = "viewContents.tiles" method = "get" class = "d-flex">
                		<input name = "search" type="text" class="form-control mySearchInput" placeholder="Search..." aria-label="Search..." aria-describedby="button-addon2" value = "<%=search%>">
	                    <button class="btn btn-outline-secondary mySearchBtn" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                	</form>
                </div>
            </li>
            
            <li class="list-group-item list-group-item-action bg-light py-3">
                <a class = "text-decoration-none blue fs-6" href= "viewContents.tiles"><i class="fa fa-table" aria-hidden="true"></i> View contents</a>
            </li>
            
            <li class="list-group-item list-group-item-action bg-light py-3">
                <a class = "text-decoration-none blue fs-6" href= "addContent.tiles"><i class="fa fa-edit" aria-hidden="true"></i> Form content</a>
            </li>
        </ul>

    
</body>
