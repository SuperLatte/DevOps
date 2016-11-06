/**
 * Created by puddingtea07 on 11/6/16.
 */
storage = window.localStorage;

function dataRender(){
    $('h2[data-aria="username"]').text(storage.getItem("username"));
    $('a[data-aria="username"]').text(storage.getItem("username"));
}

$(document).ready(function () {
   dataRender();
});