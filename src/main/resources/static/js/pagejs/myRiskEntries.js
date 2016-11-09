/**
 * Created by puddingtea07 on 11/6/16.
 */
storage = window.localStorage;

function dataRender() {
    $('h2[data-aria="username"]').text(storage.getItem("username"));
    $('a[data-aria="username"]').text(storage.getItem("username"));
}



$(document).ready(function () {
   dataRender();

    $('a[role="viewDetails"]').click(function () {
        let rid = $(this).parent('td').attr('rid');
        $.post('./viewDetails', {rid: rid}, function (data) {
            window.location.href = data.url;
        })
    })
});