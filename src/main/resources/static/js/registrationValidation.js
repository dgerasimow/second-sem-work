$(document).ready(function ()
{
    $('#registration').submit( function(event)
    {
        $('.error').remove();
        var registerData = {
            firstName: $("#firstname").val(),
            lastName: $("#lastname").val(),
            email: $("#register-login").val(),
            password: $("#register-password").val()
        }

        $.post(
            "/registration/validation",
            registerData,
            func,
            "json"
        );

        function func(responseData)
        {
            console.log(responseData);
            if (!responseData.success) {
                if(responseData.errors.errors.firstNameEmpty) {
                    $("#firstName-from-register-form").append(
                        '<div class="error">' + responseData.errors.errors.firstNameEmpty + '</div>'
                    )
                }
                if(responseData.errors.errors.secondNameEmpty) {
                    $("#secondName-from-register-form").append(
                        '<div class="error">' + responseData.errors.errors.secondNameEmpty + '</div>'
                    )
                }
                if(responseData.errors.errors.loginEmpty) {
                    $("#login-from-register-form").append(
                        '<div class="error">' + responseData.errors.errors.loginEmpty + '</div>'
                    )
                }
                if(responseData.errors.errors.passwordEmpty) {
                    $("#password-from-register-form").append(
                        '<div class="error">' + responseData.errors.errors.passwordEmpty + '</div>'
                    )
                }
                if(responseData.errors.errors.passwordRegexp) {
                    $("#password-from-register-form").append(
                        '<div class="error">' + responseData.errors.errors.passwordRegexp + '</div>'
                    )
                }
                event.preventDefault();
            } else {
                $("#registration").append(
                    '<div class="success">' + "Вы успешно зарегистрированы!" + '</div>'
                )
            }
        }
    });
});