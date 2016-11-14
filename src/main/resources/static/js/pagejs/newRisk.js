/**
 * Created by puddingtea07 on 11/7/16.
 */

//let members = JSON.parse(storage.getItem("members"));

function dataRender(members) {
    let user = JSON.parse(window.localStorage.getItem("user"));

    $('#wizard').smartWizard();
    $('.buttonNext').addClass('btn btn-success');
    $('.buttonPrevious').addClass('btn btn-primary');
    $('.buttonFinish').addClass('btn btn-default').click(addNewRisk);

    $('h2[data-aria="username"]').text(user.name);
    $('a[data-aria="username"]').text(user.name);

    let block_memberList = $('ul.memberList');
    members.forEach(function (member) {
        let temp = $('<li>').append($('<input>').attr({
                        type: 'checkbox',
                        id: member.uid
                    }), $('<label>').attr({
                        for: member.uid
                    }).html(member.name));
        temp.appendTo(block_memberList);
    })

    $('input[type="checkbox"]').iCheck({
        checkboxClass: 'icheckbox_flat-green'
    });

}

function addNewEntry() {

    let block_step_2 = $('#step-2');
    let currentEntries = parseInt(block_step_2.attr('currentEntries'));

    let newEntry = $('<div>').addClass('riskEntry').attr('number', currentEntries);
    let newrow = $('<div>').addClass('col-md-11 col-sm-11');

    let div1 = $('<div>').addClass('col-md-3 col-sm-3').append($('<div>').addClass('form-group').append($('<label>').attr('for', 'entryName_' + currentEntries).html('Entry Name'), $('<input>').addClass('form-control').attr({ type: "text", id: "entryName_" + currentEntries })));
    let div2 = $('<div>').addClass('col-md-3 col-sm-3').append($('<div>').addClass('form-group').append($('<label>').attr('for', 'possibility_' + currentEntries).html('Possibility'), $('<select>').addClass('form-control').attr({id: "possibility_" + currentEntries }).append($('<option>').attr('value', '2').html('High'), $('<option>').attr('value', '1').html('Medium'), $('<option>').attr('value', '0').html('Low'))));
    let div3 = $('<div>').addClass('col-md-3 col-sm-3').append($('<div>').addClass('form-group').append($('<label>').attr('for', 'importance_' + currentEntries).html('Importance'), $('<select>').addClass('form-control').attr({id: "importance_" + currentEntries }).append($('<option>').attr('value', '2').html('High'), $('<option>').attr('value', '1').html('Medium'), $('<option>').attr('value', '0').html('Low'))));
    let div4 = $('<div>').addClass('col-md-3 col-sm-3').append($('<div>').addClass('form-group').append($('<label>').attr('for', 'trigger_' + currentEntries).html('Trigger/Threshold'), $('<input>').addClass('form-control').attr({ type: "text", id: "trigger_" + currentEntries })));
    let div5 = $('<div>').addClass('col-md-1 col-sm-1').addClass('removeEntry').append($('<div>').append($('<a>').attr({ href: "javascript:void(0)", id: "re_" + currentEntries }).html('<i class="fa fa-times fa-2x" aria-hidden="true"></i>').click(function () {
        let number = parseInt($(this).attr('id').split('_')[1]);
        $('.riskEntry[number="' + number.toString() + '"]').remove();

        $('#entryName_' + (number - 1 )).focus();

        let block = $('#step-2');
        block.attr('currentEntries', parseInt(block.attr('currentEntries')) - 1);
    })));

    newEntry.append(newrow.append(div1, div2, div3, div4));
    newEntry.append(div5);
    newEntry.appendTo(block_step_2);

    block_step_2.attr('currentEntries', currentEntries + 1);
    $('#entryName_' + currentEntries).focus();
}

function addNewRisk (tid) {

    let proj_name = $('#project-name').val();
    let risk_depiction = $('#risk-description').val();

    let content = $('#content').val();
    let possibility = parseInt($('#possibility').val());
    let importance = parseInt($('#importance').val());
    let trigger = $('#trigger').val();

    let tracing_members = [];
    $('div.checked input').each(function () {
        tracing_members.push({
            uid: $(this).attr('id')
        });
    })

    let obj_risk = {
        tid: window.localStorage.getItem("tid"),
        name: proj_name,
        description: risk_depiction,
        traceUserList: tracing_members
    };

    $.ajax({
        type: 'POST',
        url: "./risk/create",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(obj_risk),
        success: function (data) {
            if (data.success == true) {
                let obj_record = {
                    rid: data.data.rid,
                    content: content,
                    possibility: possibility,
                    affection: importance,
                    trigger: trigger
                }
                $.ajax({
                    type: "POST",
                    url: "./risk/record/create",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(obj_record),
                    success: function (data) {
                        if (data.success == true) {
                            window.location.href = './myProjects';
                        } else {
                            console.log(data.success);
                        }
                    }
                });
            } else {
                console.log(data.success);
            }
        }
    })



}

$(document).ready(function () {
    let user = JSON.parse(window.localStorage.getItem("user"));

    $.get('./teamByMid/' + user.uid, {}, function (data) {
        let teammates = JSON.parse(data.teammates);
        window.localStorage.setItem("tid", data.tid);
        dataRender(teammates);
    });

    $('a[role="newRisk"]').click(function (data) {
        if (user.level == 0) {
            alert("You have no access");
        } else {
            location.reload();
        }
    });

    $('a[role="profile"]').click(function () {
        alert('waiting for updating');
    });


    $('a[role="logout"]').click(function () {
        storage.clear();
        $.post('./logout', {}, function (data) {
            window.location.href = data.url;
        });

    });
})