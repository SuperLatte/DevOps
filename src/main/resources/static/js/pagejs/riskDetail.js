/**
 * Created by puddingtea07 on 11/10/16.
 */
$(document).ready(function () {
    $('input[type="checkbox"]').iCheck({
        checkboxClass: 'icheckbox_flat-green'
    });

    var riskId = 18;

    refreshRecordtable(riskId);

    $('#addRecord').click(function () {
        let content = $('input[name="content"]').val();
        let possibility = $('select[name="possibility"]').val();
        let affection = $('select[name="affection"]').val();
        let trigger = $('input[name="trigger"]').val();

        var req_data = {
            rid: riskId,
            content: content,
            possibility: possibility,
            affection: affection,
            trigger: trigger
        };


        $.ajax({
            type: "POST",
            url: "./risk/record/create",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(req_data),
            success: function (data) {
                if (data.loginResponse == 'SUCCESS') {
                    refreshRecordtable(riskId);
                } else {
                    console.log('Login failed');
                }
            }
        });


        // let username = $('input[placeholder="Username"]').val();
        // let password = $('input[placeholder="Password"]').val();

        // $.post('./loginAction', {username: username, password: password}, function (data) {
        //     if (data.loginResponse == 'SUCCESS') {
        //         storage.setItem("user", data.user);
        //         storage.setItem("riskList", data.riskList);
        //         window.location.href = data.url;
        //     } else {
        //         console.log('Login failed');
        //     }
        // } )
    });
});

function refreshRecordtable(riskid) {
    $.get('./risk/'+riskid+'/detail', function(data){
    var records = data.data;
    var result = "";
    records.forEach(function (record, index) {
        result = result.concat('<tr>');

        result = result.concat(
            '<td>' + parseInt(index+1) +'</td>' +
            '<td>' + record.content +'</td>' +
            '<td>' + tranLevel(record.possibility) +'</td>' +
            '<td>' + tranLevel(record.affection) +'</td>' +
            '<td>' + record.trigger +'</td>' +
            '<td>' + record.traceUserName +'</td>' +
            '<td>' + tranTime(record.createTime) +'</td>'
        );

        result = result.concat('</tr>');
    });
    $('#riskRecordsTable').html(result);
});
}

function tranLevel(level) {
    switch (level) {
        case 1:
            return "Low";
        case 2:
            return "Middle";
        case 3:
            return "High";
    }
}

function tranTime(timestamp) {
    var unixTimestamp = new Date(timestamp * 1000);
    var time = unixTimestamp.getFullYear() + '.'
            + appendZero(unixTimestamp.getMonth()+1) + '.'
            + appendZero(unixTimestamp.getDate());
    return time;
}

function appendZero(data) {
    if (data < 10) {
        return "0" + data;
    } else {
        return data;
    }
}