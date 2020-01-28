/**
 * Created by root on 2017-11-25.
 */
// 系统配置
var config = {
    //baseUrl:"http://121.42.165.44/ksd-app/",
    // baseUrl:"http://47.94.22.93:8080/ksd-app1/",
    baseAddress:"",
    verify:function (value, msg) {
        debugger
        if (value == null || value == "") {
            alert(msg);
            return false;
        }
    },
    commPost:function (url,params,onSuccess) {
        $.ajax({url:url,
            data:params,
            type:"post",
            success:function (data) {
                if(data.status==1){
                    onSuccess(data.data,data.data);
                }else {
                    layer.alert(data.message);
                }
            }
        })
    },
    //同步post请求
    commSynPost:function (url,params,onSuccess) {
        $.ajax({url:url,
            data:params,
            type:"post",
            async: false,
            success:function (data) {
                if(data.status==1){
                    onSuccess(data.data,data.info);
                }else {
                    layer.alert(data.info);
                }
            }
        })
    },
    commParentPost:function (url,params,onSuccess) {
        $.ajax({url:url,
            data:params,
            type:"post",
            success:function (data) {
                if(data.status==1){
                    onSuccess(data.data);
                }else {
                    parent.layer.alert(data.info);
                }
            }
        })
    },
    commPostStatus:function (url,params,onSuccess) {
        $.ajax({url:config.baseUrl+url,
            data:params,
            type:"post",
            success:function (data) {
                onSuccess(data.status,data.data);
            }
        })
    },
    commGet:function (url,params,onSuccess) {
        $.ajax({url:url,
            data:params,
            type:"get",
            success:function (data) {
                if(data.status==1){
                    onSuccess(data.data);
                }else {
                    layer.msg(data.info);
                }
            }
        })
    },
    initTable:function(table,id,urls,colss,where){

        table.render({
            elem: id
            ,url: urls //数据接口
            ,page: true //开启分页
            ,where: where //参数
            ,response: {
                statusName: 'status' //规定数据状态的字段名称，默认：code
                ,statusCode: 1 //规定成功的状态码，默认：0
                ,msgName: 'info' //规定状态信息的字段名称，默认：msg
                ,countName: 'info' //规定数据总数的字段名称，默认：count
                ,dataName: 'data' //规定数据列表的字段名称，默认：data
            }
            ,limits: [10]
            ,cols: [colss]
        });
    },
    initTable1:function(id,url,colss,where,exportOptions){
        $(''+id).bootstrapTable({
            url: url,         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            showExport: true,//显示导出按钮
            exportDataType:'basic',  //导出文件类型  当前页数据:basic, 所有数据:all, 选中的数据:selected
            exportTypes:['csv', 'txt', 'doc', 'excel'],  //导出的格式
            exportOptions:{
                ignoreColumn: [0],  //忽略某一列的索引
                fileName: exportOptions,  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: '报警记录',
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'],
            },
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            // queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 15, 20, 25],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            //height: 1500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: colss,
            queryParams:function(params){
                // console.log("params",params)
                // console.log("where",where)
                var pageNo=1;
                var pageSize=10000;
                if(params.limit){
                    pageNo = parseInt(params.offset/params.limit) + 1;
                    pageSize=params.limit;
                }
                return $.extend(where, {
                    pageSize: pageSize,
                    page: pageNo
                });
            },
            onLoadSuccess: function (result) {  //加载成功时执行
                 //console.log("加载成功",result);
            },

        });
    },
    initTableNoRefresh:function(id,urls,colss,where){
        $(''+id).bootstrapTable({
            url: urls,         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
           // sortable: false,                     //是否启用排序
           // sortOrder: "asc",                   //排序方式
            // queryParams: oTableInit.queryParams,//传递参数（*）
           // sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
           // pageNumber:1,                       //初始化加载第一页，默认第一页
           // pageSize: 10,                       //每页的记录行数（*）
           // pageList: [10, 15, 20, 25],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            //height: 1500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: colss,
            queryParams:function(params){
                var pageNo = parseInt(params.offset/params.limit) + 1;
                return $.extend(where, {
                    pageSize: params.limit,
                    page: pageNo
                });
            }
        });
    },
    tableRefresh:function(id){
        $("#"+id).bootstrapTable("refresh","{silent: true}");
    },
    closeWin:function(id){
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
        parent.$("#"+id).bootstrapTable("refresh","{silent: true}");
    },
    formData:function(form){
        var serialize = $(form).serializeArray();
        var values = {};
        for(var key in serialize){
            values[serialize[key].name] = serialize[key].value;
        }
        return values;
    },
    tableQuery:function(id,param){
        $("#"+id).bootstrapTable("refresh",{query: param})
    },
    getNowTime:function(){
        var today=new Date();

        var s=today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();
         return s;
        },
    getCheckIds:function(name){
      var  checkIds="";
        $('input[name="'+name+'"]:checked').each(function() {
            checkIds+=$(this).attr("id")+",";
        });
        if(checkIds.length>0)
        return checkIds.substring(0,checkIds.length-1);
        else
            return "";
    },
    isPc:function() {
        var userAgentInfo = navigator.userAgent;
        var Agents = ["Android", "iPhone",
            "SymbianOS", "Windows Phone",
            "iPad", "iPod"];
        var flag = true;
        for (var v = 0; v < Agents.length; v++) {
            if (userAgentInfo.indexOf(Agents[v]) > 0) {
                flag = false;
                break;
            }
        }
        return flag;
    },
    formatDateTime:function (date) {
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h=h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        minute = minute < 10 ? ('0' + minute) : minute;
        var second=date.getSeconds();
        second=second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    },
    getTime:function (time) {
        var  date=new Date(time);
        return config.formatDateTime(date);
    },
    openPageLayer:function (title,url,area,callback,table,autoClose) {
        layer.open({
            type: 2,
            title:title,
            content: url //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            ,area: area
            ,maxmin:true
            ,btn: ['保存', '取消']
            ,btnAlign: 'r'
            ,yes: function (index, layero) {
                var iframeWin = window[layero.find('iframe')[0]['name']];
                var res=iframeWin.ApplicationSave();
                if(autoClose!=false){
                    layer.close(index);
                    setTimeout(function () {
                        table.bootstrapTable('refresh');
                    },500);
                }
              if(callback)
                  callback(index,layero,res);

            },
            btn2: function (index, layero) {
                layer.close(index);
            }

        });
    },
    openPageLayer2:function (title,url,area) {
        layer.open({
            type: 2,
            title:title,
            content: "/page/"+url,
            area: area,
            maxmin:true
        });
    },
    openNobtnLayer:function (title,url,area,max) {
        var index=layer.open({
            type: 2,
            title:title,
            maxmin:true,
            closeBtn: 2,
            content: "/page/"+url //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            ,area: area
        });
        if(max==true){
            layer.full(index);
        }
    },
    openConfirmLayer:function (title,url,data,callback) {
        layer.confirm(title, function(index){
            $.ajax({
                url: url,
                type: "POST",
                data:data,
                dataType: "json",
                success: function(data){
                    callback(data);
                }
            });
        });
    },
    openLayer:function (title,url) {
        layer.open({
            type: 2,
            title:title,
            content: url //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            ,area: ['500px', '350px']
            ,cancel: function(index, layero){
                location.reload();
            }
        });
    },
    openIdLayer:function (id,title,url) {
        parent.layer.open({
            id:id,
            type: 2,
            title:title,
            content: url //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            ,area: ['500px', '350px']
            ,cancel: function(index, layero){
                location.reload();
            }
        });
    },
    getNowDate:function()
    {
    	var date1 = new Date(),
        time1=date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate();//time1表示当前时间
    	return time1;
    },
    addInputDate:function(date){
    	 var date1 = new Date(),
         time1=date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate();//time1表示当前时间
         var date2 = new Date(date1);
         date2.setDate(date1.getDate()+date);
         var time2 = date2.getFullYear()+"-"+(date2.getMonth()+1)+"-"+date2.getDate();
         return time2;
    },
    getQueryString:function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    },
    timestampToTime:function(timestamp) {
        var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        h = date.getHours() + ':';
        m = (date.getMinutes() < 10 ? '0'+(date.getMinutes()) : date.getMinutes()) + ':';
        s = (date.getSeconds() < 10 ? '0'+(date.getSeconds()) : date.getSeconds());
        return Y+M+D+h+m+s;
    },
    formatDate:function(fmt){
        var date = new Date();
        var o = {
            "M+" : date.getMonth()+1, //月份
            "d+" : date.getDate(), //日
            "h+" : date.getHours(), //小时
            "m+" : date.getMinutes(), //分
            "s+" : date.getSeconds(), //秒
            "q+" : Math.floor((date.getMonth()+3)/3), //季度
            "S" : date.getMilliseconds() //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    },
    //时间戳的处理
    toDateString:function(d, format){
        var date = new Date(d || new Date())
            ,ymd = [
            this.digit(date.getFullYear(), 4)
            ,this.digit(date.getMonth() + 1)
            ,this.digit(date.getDate())
        ]
            ,hms = [
            this.digit(date.getHours())
            ,this.digit(date.getMinutes())
            ,this.digit(date.getSeconds())
        ];

        format = format || 'yyyy-MM-dd HH:mm:ss';

        return format.replace(/yyyy/g, ymd[0])
            .replace(/MM/g, ymd[1])
            .replace(/dd/g, ymd[2])
            .replace(/HH/g, hms[0])
            .replace(/mm/g, hms[1])
            .replace(/ss/g, hms[2]);
    },
    //数字前置补零
    digit:function(num, length, end){
        var str = '';
        num = String(num);
        length = length || 2;
        for(var i = num.length; i < length; i++){
            str += '0';
        }
        return num < Math.pow(10, length) ? str + (num|0) : num;
    }, //转正整数
    parse2Int:function(value){
        value = value.replace(/[^\d]/g,'');
        if(''!=value){
            value = parseInt(value);
        }
        return value;
    },
    getDateTime:function(){
        return this.formatDate('yyyy-MM-dd hh:mm:ss');
    }
}




