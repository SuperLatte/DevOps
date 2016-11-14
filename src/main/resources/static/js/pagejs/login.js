/**
 * Created by puddingtea07 on 11/6/16.
 */

$(document).ready(function () {

    $('#login').click(function () {
        let username = $('input[placeholder="Username"]').val();
        let password = $('input[placeholder="Password"]').val();

        $.post('./loginAction', {username: username, password: password}, function (data) {
            if (data.loginResponse == 'SUCCESS') {
                window.localStorage.setItem("user", data.user);
                window.location.href = data.url;
            } else {
                alert('Username/Password is wrong');
            }
        } )
    });

});