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
            return value.trim() ? undefined : "Vui lòng nhập tên đầy đủ";
        }
    }
}

Validator.isEmail = (selector) => {
    return {
        selector,
        test: function(value){
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : 'Trường này phải là email';
        }
    };
}

Validator.requiredPass = (selector) =>
{
    return {
        selector,
        test: function(value){
            return value.trim() ? undefined : "Vui lòng nhập mật khẩu";
        }
    };
}


Validator.confirmPassword = (selector, getPassword) =>{
    return {
        selector,
        test: function(value)
        {
            if (!value.trim())
                return 'Vui lòng xác nhận lại mật khẩu'
            return value === getPassword() ? undefined: 'Mật khẩu không khớp';
        }
    }
}

Validator.requiredUsername = (selector) =>{
    return {
        selector,
        test: function(value){
            return value.trim() ? undefined : "Vui lòng nhập username";
        }
    }
}

Validator.minLength = function (selector, min, message) {
    return {
        selector: selector,
        test: function (value) {
            return value.length >= min ? undefined :  message || `Vui lòng nhập tối thiểu ${min} kí tự`;
        }
    };
}

Validator.maxLength = function (selector, max, message) {
    return {
        selector: selector,
        test: function (value) {
            return value.length <= max ? undefined :  message || `Vui lòng nhập tối đa ${max} kí tự`;
        }
    };
}