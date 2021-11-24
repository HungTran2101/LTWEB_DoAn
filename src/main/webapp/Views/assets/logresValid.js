/**
 * 
 */
function myRegister(){
	let email = document.getElementById('email').value;
	let password = document.getElementById('password').value;
	let username = document.getElementById('username').value;
	let repassword = document.getElementById('repassword').value;
	let warning = document.getElementById('warning');
	let warning2 = document.getElementById('warning2');
	let form = document.getElementById('resform');
	
	if(warning2 != null){
		warning2.style.display = 'none';
	}
	
	if(email == '' || password == '' || username == '' || repassword == ''){
		warning.style.display = 'block';
		warning.innerText = 'Please fill up all the fields!';
	}
	else if(username.length < 3 || username.length > 30){
		warning.style.display = 'block';
		warning.innerText = 'Your username must between 3 and 30 characters!';
	}
	else if(email.length < 5 || email.length > 50){
		warning.style.display = 'block';
		warning.innerText = 'Your email must between 5 and 50 characters!';
	}
	else if(!validateEmail(email)){
		warning.style.display = 'block';
		warning.innerText = 'Your email is not valid!';
	}
	else if(password.length < 8 || password.length > 30){
		warning.style.display = 'block';
		warning.innerText = 'Your password must between 8 and 30 characters!';
	}
	else if(repassword.length < 8 || repassword.length > 30){
		warning.style.display = 'block';
		warning.innerText = 'Your Re Password must between 8 and 30 characters!';
	}
	else if(password !== repassword){
		warning.style.display = 'block';
		warning.innerText = 'Your Re Password not match!';
	}
	else{
		form.submit();
	}
	
}

function myLogin(){
	let email = document.getElementById('email').value;
	let password = document.getElementById('password').value;
	let warning = document.getElementById('warning');
	let form = document.getElementById('logform');
	let warning2 = document.getElementById('warning2');
	
	if(warning2 != null){
		warning2.style.display = 'none';
	}
	
	if(email == '' || password ==''){
		warning.style.display = 'block';
		warning.innerText = 'Please fill up all the fields!';
	}
	else if(email.length < 5 || email.length > 50){
		warning.style.display = 'block';
		warning.innerText = 'Your email must between 5 and 50 characters!';
	}
	else if(password.length < 8 || password.length > 30){
		warning.style.display = 'block';
		warning.innerText = 'Your password between 8 and 30 characters!';
	}
	else if(!validateEmail(email)){
		warning.style.display = 'block';
		warning.innerText = 'Your email is not valid!';
	}
	else{
		form.submit();
	}
}

function validateEmail(email) {
  const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}