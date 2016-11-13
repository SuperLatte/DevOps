/**
 * Created by puddingtea07 on 11/6/16.
 */

function dataRender(riskList) {
    let user = JSON.parse(window.localStorage.getItem('user'));

    $('h2[data-aria="username"]').text(user.name);
    $('a[data-aria="username"]').text(user.name);


    riskList.forEach(function (risk) {
        let tr = $('<tr>');
        let rid = $('<td>').text(risk.rid)
        let ct = new Date(parseInt(risk.createTime) * 1000);
        let brief_depiction = $('<td>').append($('<a>').text(risk.name), $('<br>'), $('<small>').text(ct));

        let tm = risk.traceUserList;
        let litm = $('<ul>').addClass('list-inline');
        tm.forEach(function (member) {
            litm.append($('<li>').append($('<a>').append($('<image>').attr({
                src: "./images/user.png",
                title: member.name,
                class: "avatar"
            }))));
        });

        let tracingMembers = $('<td>').append(litm);

        let status = $('<td>');
        if (risk.status == 1) {
            status.append($('<button>').attr({
                type: 'button',
                class: 'btn btn-info btn-xs'
            }).prop('disabled', true).html('In progress'));
        } else {
            status.append($('<button>').attr({
                type: 'button',
                class: 'btn btn-success btn-xs'
            }).prop('disabled', true).html('Resolved'));
        }
        let operations = $('<td>').attr('rid', risk.rid).append($('<a>').attr({
            href: "javascript: void 0",
            class: "btn btn-primary btn-xs",
            target: "_blank",
            role: "viewDetails"
        }).html('<i class="fa fa-folder"></i> View').click(function () {
            window.localStorage.setItem("rid", $(this).parent('td').attr('rid'));
            window.open('./riskDetail') ;
        }), $('<button>').attr({
            type: "button",
            class: "btn btn-success btn-xs",
            role: "mar"
        }).prop('disabled', (user.level == 0 || risk.status == 0)? true : false).html('<i class="fa fa-pencil"></i> Marked As Resolved'), $('<button>').attr({
            type: "button",
            class: "btn btn-danger btn-xs",
            role: "delete"
        }).prop('disabled', (user.level == 0)? true : false).html('<i class="fa fa-trash-o"></i> Delete'));

        tr.append(rid, brief_depiction, tracingMembers, status, operations);
        tr.appendTo($('tbody'));


    })

}





$(document).ready(function () {
    let user = JSON.parse(window.localStorage.getItem('user'));


    $.post('./myrisk', {}, function (data) {
        //riskList = JSON.parse(data.riskList);
        if (data.success == true) {
            dataRender(data.data);
        } else {
            alert("There is something wrong in the page.")
        }

    });



    $('a[role="newRisk"]').click(function () {
        if (user.level == 0) {
            alert("You have no access");
        } else {
            window.location.href = './newRisk';
            //$.get('/teamByMid/' + user.uid, {uid: user.uid}, function (data) {
            //    storage.setItem("members", data.team_members);
            //    window.location.href = './newRisk';
            //})
        }
    });

    $('a[role="profile"]').click(function () {
        alert('waiting for updating');
    })

    $('a[role="viewDetails"]').click(function () {
        let rid = $(this).parent('td').attr('rid');
        $.post('./viewDetails', {rid: rid}, function (data) {
            window.location.href = data.url;
        })
    });

    $('button[role="mar"]').click(function () {
        let rid = $(this).parent('td').attr('rid');
        $.post('./markAsResolved', {rid: rid}, function (data) {
            window.location.href = data.url;
        })
    });

    $('button[role="delete"]').click(function () {
        let rid = $(this).parent('td').attr('rid');
        $.post('./markAsResolved', {rid: rid}, function (data) {
            window.location.href = data.url;
        })
    });


    $('a[role="logout"]').click(function () {
        window.localStorage.clear();
        $.post('./logout', {}, function (data) {
            window.location.href = data.url;
        });

    });
});
