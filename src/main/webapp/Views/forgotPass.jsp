<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Login Account</title>
    <style>
        .myForm{
            margin: auto auto;
            width: 40vw;
            margin-top: 15vh;
            background-color: white;
        }

        input[type="submit"]
        {
            width: 100%;
            background-color: rgb(62, 179, 62);
            border-radius: 10px;
        }
        h4{
            background-image: linear-gradient(-90deg, rgb(179, 177, 177), white);
            padding: 5px;
            margin-bottom: 0;
        }

        body{
            margin: 0 0;
            background-color: rgb(230, 221, 221);
        }
    </style>
</head>
<body>
    <form class = "myForm" action = "GetPassController" method = "post">
        <div class = "border border-dark">
            <h4>Enter Your Email</h4>
        </div>
        <div class = "border border-dark p-4">
            <div>
                <div class="form-floating mb-3  mt-3">
                    <input name = "email" type="email" class="form-control" placeholder="name@example.com">
                    <label>Email address</label>
                </div>
            </div>
            <div class = "mt-3">
                <input class = "text-light p-2" type ="submit" value = "Get Back My Password"/>
            </div>
            <div class = "mt-3">
                <a href = "login.tiles" class = "text-decoration-none">Click here to Login</a>
            </div>
        </div>
        
    </form>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>