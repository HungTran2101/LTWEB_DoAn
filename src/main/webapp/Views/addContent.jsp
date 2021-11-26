
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
	                <input id = "title" name = "title" pattern=".{10,200}" title="10 to 200 characters" maxlength="50" type="text" class="form-control myInput" placeholder="Enter the title" aria-describedby="basic-addon1" required>
            	</div>
            	<span class="form-message"></span>
        	</div>
            
			<div class = "formInput">
				<div>
	                <label class = "mb-2">Brief</label>
	                <textarea name = "brief" minlength="30" maxlength="150" class="form-control myInput" id="brief" rows="3" required></textarea>
            	</div>
            	<span class="form-message"></span>
			</div>
            

            
			<div class = "formInput">
				<div>
	                <label class = "mb-2">Content</label>
	                <textarea name = "content" minlength="50" maxlength="1000" class="form-control myInput" id="content" rows="5" required></textarea>
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
	
	<script src = "utils/assets/valid.js"></script>
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
              
              function resetFunc() {
      	        document.getElementById("title").value = "";
      	        document.getElementById("brief").value = "";
      	        document.getElementById("content").value = "";
      	    }
    	</script>
</body>
