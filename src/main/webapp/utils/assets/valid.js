const validate = (inputElement, rule) => {
    var errorMessage = rule.test(inputElement.value);
    var errorElement = inputElement.parentElement.parentElement.querySelector('.form-message');   
    console.log(errorElement)   
    if (errorMessage)
    {
        errorElement.innerText = errorMessage;
        inputElement.classList.remove('valid');
        inputElement.classList.add('invalid');
    }
    else {
        errorElement.innerText = '';
        inputElement.classList.remove('invalid');
        inputElement.classList.add('valid');
    }
}

const Validator = (options) => {
    var formElement = document.querySelector(options.form);
    if (formElement){
        options.rules.forEach(rule =>{
            var inputElement = formElement.querySelector(rule.selector);
            //console.log(inputElement);
            //console.log(inputElement);
            if (inputElement){
                    inputElement.onblur = () => {
                        validate(inputElement, rule);
                    inputElement.oninput = () => {
                        var errorElement = inputElement.parentElement.querySelector('.form-message'); 
                        errorElement.innerText = '';
                        inputElement.classList.remove('invalid');
                    }
                }
            }
        })
    }
}



Validator.isRequired = (selector) => {
    return {
        selector,
        test: function(value){
            return value.trim() ? undefined : "Please enter your username";
        }
    }
}

Validator.isEmail = (selector) => {
    return {
        selector,
        test: function(value){
			if (!value.trim())
                return 'Please enter your email'
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : 'This field must be email';
        }
    };
}

Validator.requiredPass = (selector) =>
{
    return {
        selector,
        test: function(value){
            return value.trim() ? undefined : "Please enter your password";
        }
    };
}


Validator.confirmPassword = (selector, getPassword) =>{
    return {
        selector,
        test: function(value)
        {
            if (!value.trim())
                return 'Please comfirm your password'
            return value === getPassword() ? undefined: 'Your password is not matched';
        }
    }
}

Validator.requiredUsername = (selector) =>{
    return {
        selector,
        test: function(value){
            return value.trim() ? undefined : "Please enter your username";
        }
    }
}

Validator.requiredTitle = (selector) =>{
    return {
        selector,
        test: function(value){
            return value.trim() ? undefined : "Please enter title";
        }
    }
}


Validator.requiredBrief = (selector) =>{
    return {
        selector,
        test: function(value){
            return value.trim() ? undefined : "Please enter brief";
        }
    }
}

Validator.requiredContent = (selector) =>{
    return {
        selector,
        test: function(value){
            return value.trim() ? undefined : "Please enter content";
        }
    }
}


Validator.minLength = (selector, min, message) => {
    return {
        selector: selector,
        test: function (value) {
            return value.length >= min ? undefined :  message || `Vui lòng nhập tối thiểu ${min} kí tự`;
        }
    };
}

Validator.maxLength = (selector, max, message) => {
    return {
        selector: selector,
        test: function (value) {
            return value.length <= max ? undefined :  message || `Vui lòng nhập tối đa ${max} kí tự`;
        }
    };
}