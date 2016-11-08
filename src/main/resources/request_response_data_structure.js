/**************************************************************/
/* Request Data Structure */

// create/modify risk
{
	tid:1,
	name:'hhh',
	tracingUsers:[
		{
			uid:'1'
		},{
			uid:'2'
		}
	]
}

// add risk record
{
	rid:1,
	content:'ddfsf',
	possibility:1,
	affection:1,
	trigger:'random'
}



/****************************************************************/
/* Response Data Structure */
// create/modify risk  success
{
	success: true ,
	description: 'success',
	data:{
		rid:2,
		tid:1,
		name:'hhh',
		createTime:'2016-11-08 10:42:33',
		updateTime:'2016-11-08 10:42:33',
		tracingUsers:[
			{
				uid:'1',
				name:'xiaoming',
				username:'xiaoming123'
			},{
				uid:'2',
				name:'xiaohong',
				username:'xiaohong123'
			}
		]
	}
}

// create/modify risk  error
{
	success: false ,
	description: 'Please enter risk name',
	data:{}  //the origin data submitted by user
}

// get single risk, success
{
	success: true ,
	description: 'success',
	data:{
		rid:2,
		tid:1,
		name:'hhh',
		createTime:'2016-11-08 10:42:33',
		updateTime:'2016-11-08 10:42:33',
		tracingUsers:[
			{
				uid:'1',
				name:'xiaoming',
				username:'xiaoming123'
			},{
				uid:'2',
				name:'xiaohong',
				username:'xiaohong123'
			}
		]
	}
}

//get single risk, error
{
	success: false ,
	description: 'No risk matches the query condition',
	data:{}
}


//get risk list
{
	success: true ,
	description: 'success',
	data:[
		{
			rid:2,
			tid:1,
			name:'risk2',
			createTime:'2016-11-08 10:42:33',
			updateTime:'2016-11-08 10:42:33',
			tracingUsers:[
				{
					uid:'1',
					name:'xiaoming',
					username:'xiaoming123'
				},{
					uid:'2',
					name:'xiaohong',
					username:'xiaohong123'
				}
			]
		},
		{
			rid:3,
			tid:1,
			name:'risk3',
			createTime:'2016-11-08 10:42:33',
			updateTime:'2016-11-08 10:42:33',
			tracingUsers:[
				{
					uid:'1',
					name:'xiaoming',
					username:'xiaoming123'
				},{
					uid:'2',
					name:'xiaohong',
					username:'xiaohong123'
				}
			]
		}
	]

}


// add risk record,success
{
	success:true,
	description:'success',
	data:{
		rrid:1,
		rid:1,
		createTime:'2016-11-08 10:42:33',
		content:'ddfsf',
		possibility:1,
		affection:1,
		trigger:'random',
		traceUser:{
			uid:'2',
			name:'xiaohong',
			username:'xiaohong123'
		}
	}
}

//add risk record, error
{
	success:false,
	description:'Please enter trigger',
	data:{} //the origin data submitted by user
}