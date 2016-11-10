/**
 * Created by puddingtea07 on 11/6/16.
 */
storage = window.localStorage;

function dataRender() {
    $('h2[data-aria="username"]').text(storage.getItem("username"));
    $('a[data-aria="username"]').text(storage.getItem("username"));

    let riskList = JSON.parse(storage.getItem("riskList"));
    riskList.forEach(function (risk) {
        let tr = $('<tr>');
        let rid = $('<td>').text(risk.rid)
        let ct = new Date(parseInt(risk.createTime) * 1000);
        let brief_depiction = $('<td>').append($('<a>').text(risk.name), $('<br>'), $('<small>').text(ct));

        let tm = risk.traceUserList;
        let litm = $('<ul>').addClass('list-inline');
        tm.forEach(function (member) {
            $('<li>').append($('<a>').append($('<image>').attr({
                src: "./images/user.png",
                title: member.username,
                class: "avatar"
            }))).appendTo(litm);
        });

        let tracingMembers = $('<td>').append(litm);

        let status = $('<td>');
        if (risk.status == 1) {
            status.append($('<button>').attr({
                type: 'button',
                class: 'btn btn-info btn-xs'
            }).html('In progress'))
        } else {
            status.append($('<button>').attr({
                type: 'button',
                class: 'btn btn-success btn-xs'
            }).html('Resolved'))
        }
        let operations = $('<td>').attr('rid', risk.rid).append($('<a>').attr({
            href: "javascript: void 0",
            class: "btn btn-primary btn-xs",
            target: "_blank",
            role: "viewDetails"
        }).html('<i class="fa fa-folder"></i> View'), $('<a>').attr({
            href: "javascript: void 0",
            class: "btn btn-success btn-xs",
            role: "mar"
        }).html('<i class="fa fa-pencil"></i> Marked As Resolved'), $('<a>').attr({
            href: "javascript: void 0",
            class: "btn btn-danger btn-xs",
            role: "delete"
        }).html('<i class="fa fa-trash-o"></i> Delete'));

        tr.append(rid, brief_depiction, tracingMembers, status, operations);
        tr.appendTo($('tbody'));
    })

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