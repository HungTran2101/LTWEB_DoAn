
<body>
	<div class = "d-flex w3-animate-right myFrame">
		<div class = "p-4 myAddForm">
        <h1 class = "">Add Content</h1>
        <hr />
        <div class = "border rounded">
        	<h5 class = "border bg-light p-2 mb-0 px-4 border-0 border-bottom rounded-top">Content Form Elements</h5>
        	<form class="border-0 p-2" action = "AddContentController" method = "post" id = "myForm">
        
        	<div class = "formInput">
        		<div>
	                <label class = "mb-2">Title</label>
	                <input id = "title" name = "title" type="text" class="form-control myInput" placeholder="Enter the title" aria-describedby="basic-addon1" required>
            	</div>
            	<span class="form-message"></span>
        	</div>
            
			<div class = "formInput">
				<div>
	                <label class = "mb-2">Brief</label>
	                <textarea name = "brief" class="form-control myInput" id="brief" rows="3" required></textarea>
            	</div>
            	<span class="form-message"></span>
			</div>
            

            
			<div class = "formInput">
				<div>
	                <label class = "mb-2">Content</label>
	                <textarea name = "content" class="form-control myInput" id="content" rows="5" required></textarea>
            	</div>
            	<span class="form-message"></span>
			</div>
            

            <div class = "mt-2 myFrame">
                <input class = "myBtn" type = "submit" value = "Submit Button"/>
                <button type = "button" onclick ="resetFunc()" class = "myBtn">Reset Button</button>
            </div>

        </form>
        </div>
        
        
        
        
    </div>
	</div>
	
	<script src = "Views/assets/valid.js"></script>
       	<script>
              Validator({
                form: '#myForm',
                errorSelector: '.form-massage',
                rules:[
                    Validator.requiredTitle("#title"),
                    Validator.requiredBrief("#brief"),
                    Validator.requiredContent("#content")
                ]
              });              
    	</script>
</body>
