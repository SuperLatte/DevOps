/**
 * Created by puddingtea07 on 11/6/16.
 */
storage = window.localStorage

$(document).ready(function () {

    $('#login').click(function () {
        let username = $('input[placeholder="Username"]').val();
        let password = $('input[placeholder="Password"]').val();

        $.post('./loginAction', {username: username, password: password}, function (data) {
            storage.setItem("username", data.username);
            storage.setItem("riskList", data.riskList);
            window.location.href = data.url;
        } )
    });

});