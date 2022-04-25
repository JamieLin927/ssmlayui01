<%--
  Created by IntelliJ IDEA.
  User: jamie
  Date: 2022/4/8
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">用户姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="userName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">部门</label>
                            <div class="layui-input-inline">
                                <select name="deptId">
                                    <option value=""></option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn "  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                        </div>
                </form>
                    </div>

        </fieldset>

<%--        表格头部工具栏--%>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i class="layui-icon">&#xe654;</i>添加 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"><i class="layui-icon">&#xe640;</i>删除 </button>
            </div>
        </script>

<%--        表格--%>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
<%--       表格操作工具栏--%>
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-radius data-count-delete" lay-event="authrole"><i class="layui-icon">&#xe613;</i>分配角色</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i class="layui-icon">&#x1007;</i>删除</a>
        </script>

    </div>
</div>
<form class="layui-form layuimini-form"  lay-filter="dataForm"  id="AddUpdatePage" style="display: none" >

    <div class="layui-form-item">
        <div class="layui-input-block">
            <input name="id" type="hidden">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="userName" lay-verify="required" lay-reqtext="用户名不能为空" placeholder="请输入用户名" value="" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">真实名</label>
        <div class="layui-input-block">
            <input type="text" name="trueName" lay-verify="required" lay-reqtext="用户名不能为空" placeholder="请输入用户名" value="" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">部门</label>
        <div class="layui-input-block">
            <select name="deptId" lay-verify="required" lay-reqtext="部门不能为空" class="layui-select">
                <option value=""></option>
            </select>
        </div>

    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">邮箱</label>
        <div class="layui-input-block">
            <input type="email" name="email" placeholder="请输入邮箱" lay-verify="required"  lay-reqtext="邮箱不能为空" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">手机</label>
        <div class="layui-input-block">
            <input type="number" name="phone" lay-verify="required" lay-reqtext="手机不能为空" placeholder="请输入手机" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</form>

<div id="userrole" style="display: none"></div>
<script src="${pageContext.request.contextPath}/static/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table','jquery','transfer'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            transfer=layui.transfer;

       var tableIns= table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/user/finduseranddeptname',
             response:{
             statusCode:200//重新规定成功的状态码为 200，table 组件默认为 0
         },
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID', sort: true,align: "center"},
                {field: 'userName', width: 80, title: '用户名',align: "center"},
                {field: 'trueName', width: 120, title: '真实姓名', sort: true,align: "center"},
                {field: 'dept',templet: function (data) {return data.dept.deptName;}, width: 120, title: '部门', sort: true,align: "center"},
                {field: 'email', width: 180, title: '邮箱',align: "center"},
                {field: 'phone', width: 180, title: '电话', sort: true,align: "center"},

                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true,
            skin: 'line'

        });

        //监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            // var result = JSON.stringify(data.field);
            // layer.alert(result, {
            //     title: '最终的搜索信息'
            // });
            //执行搜索重载
            tableIns.reload({
                where: data.field,
                page: {
                    curr: 1
                }
            });
            return false;
        });


        /**
         * 表格头部工具栏监听事件
         */
        table.on("toolbar(currentTableFilter)", function(obj){
            var checkStatus = table.checkStatus('currentTableId'),
                data=checkStatus.data;
            switch(obj.event){
                case 'add':
                    addAndupdateWindow();

                    break;
                case 'delete':
                    deleteByids(data);
                    break;

            };
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        /**
         * 监听操作栏工具
         */
        table.on('tool(currentTableFilter)', function(obj){
            var data=obj.data;
            switch (obj.event){
                case 'edit':
                    edituserWindow(data);
                    break;
                case 'delete':
                    layer.msg("删除");
                    deleteByid(data);
                    break;
                case  'authrole':
                    authuserrole(data);
                    break;
            }
        });


        var maxIndex ;
        var url;
        /**
         * 打开添加窗口
         */
        function  addAndupdateWindow(){
            maxIndex= layer.open({
                type: 1,
                title: "添加用户",
                shade: 0.2,
                maxmin: true,
                anim: 2,
                shadeClose: true,
                area: ['100%', '100%'],
                content: $('#AddUpdatePage'),
                success: function(){

                    $("#AddUpdatePage")[0].reset();

                    url= '/user/insertuser';
                }
            })
        };




        function authuserrole(data){
            maxIndex = layer.open({
                type: 1,
                title: "正在为<font color='red'>["+data.trueName+"]</font>分配角色",
                shade: 0.2,
                maxmin: true,
                anim: 2,
                shadeClose: true,
                area: ['500px', '600px'],
                content: $('#userrole'),
                success: function () {
                    $.ajax({
                        type: 'post',
                        url:  "role/getallrole",
                        dataType: 'json',
                        success: function(res) {
                            var zuo=res;
                            var you=[];
                            $.ajax({
                                type: 'post',
                                url: "role/getroleByuId",
                                dataType: 'json',
                                data: {"userId": data.id},
                                success: function (res) {

                                    if(res.data.length>0){
                                        for (var i = 0; i <res.data.length ; i++) {
                                            you.push(res.data[i].id)
                                        }
                                    }
                                    console.log(JSON.stringify(you))
                                    //渲染
                                    transfer.render({
                                        elem: '#userrole',  //绑定元素
                                        title: ['系统可选角色', '当前用户的角色'],
                                        data: zuo.data,
                                        value: you,
                                        parseData: function(udata) {
                                            return {
                                                "value": udata.id, //数据值
                                                "title": udata.roleName //数据标题
                                            }
                                        },
                                        onchange: function(obj, index) {
                                           if(index==0){
                                               console.log(obj[0].id);
                                               for (var i = 0; i <obj.length ; i++) {
                                                   $.ajax({
                                                       type: 'post',
                                                       url: "user/adduserRole",
                                                       dataType: 'json',
                                                       data: {
                                                           "userId": data.id,
                                                           "roleId": obj[i].value
                                                       },

                                                       success: function (res) {
                                                           console.log(obj[i].id);
                                                           layer.msg(res.msg);

                                                       }
                                                   })
                                               }
                                           }
                                           else{
                                               for (var i = 0; i <obj.length ; i++) {
                                                   $.ajax({
                                                       type: 'post',
                                                       url: "user/deleteuserRole",
                                                       dataType: 'json',
                                                       data: {
                                                           "userId": data.id,
                                                           "roleId": obj[i].value
                                                       },
                                                       success: function (res) {
                                                           layer.msg(res.msg);
                                                       }
                                                   })
                                               }

                                           }
                                        }
                                        , id: 'demo1' //定义索引
                                    });

                                }


                            })

                        }
                    });



                }
            })


        };

        /**
         * 批量删除方法
         */
        function deleteByids(data) {
            if (data.length < 1) {
                layer.msg("请选择要删除的数据")
            }
            else {
                layer.confirm('确定删除选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
                    var ids = [];
                    for (var i in data) {
                        ids.push(data[i].id);

                    }
                    $.ajax({
                        type: "post",
                        url: "/user/deleteByIds",
                        //注意！通过json传输时需设置contentType并且传一个字符串（JSON.stringfy）
                        data: JSON.stringify(ids),
                        contentType: "application/json",
                        success: function (data) {
                            layer.msg(data.msg);
                            tableIns.reload({
                                //重新加载时的回调
                                done: function (res, curr, count) {
                                    //注意避免整页删除后仍停留在空白页的情况，再次重载表格，回退到上一页
                                    if (res.data.length == 0) {
                                        curr = curr - 1;
                                        tableIns.reload({
                                            page: {
                                                curr: curr
                                            }
                                        })
                                    }
                                }
                            })
                            layer.close(index);
                        }
                    })

                })


            }
        };

        /**
         * 打开编辑窗口
         */
        function edituserWindow(data){
            maxIndex= layer.open({
                type: 1,
                title: "编辑用户",
                shade: 0.2,
                maxmin: true,
                anim: 2,
                shadeClose: true,
                area: ['100%', '100%'],
                content: $('#AddUpdatePage'),
                success: function(){

                    form.val('dataForm',data)

                    url= '/user/updateuser';
                }
            })
        };

        /**
         * 根据id删除方法
         */
        function deleteByid(data){
            layer.confirm("确定删除<font color='red'>"+data.trueName+"</font>的数据吗",{icon: 3, title: '提示信息'}, function (index) {
                $.post("/user/deleteById", {id:data.id}, function (res) {
                    if (res.code == 200) {
                        tableIns.reload();
                    }
                    layer.msg(res.msg);
                })
                layer.close(index);
            });
        }

        /**
         * 添加/修改页面部门的form表单监听
         */
        form.on("submit(saveBtn)", function (data) {
            $.post(url, data.field, function (res) {
                if (res.code == 200) {
                    //添加成功刷新表格，这样才能显示刚刚添加成功的数据
                    tableIns.reload();
                    //成功后还要把添加窗口关闭
                    layer.close(maxIndex);
                }
                layer.msg(res.msg);


            })


            //返回false是因为防止页面刷新
            return false;

        });

        /**
         * 页面加载时去初始化部门的下拉选项框
         */
        $.get("/dept/initallDept",function(res){
            console.log(res);
             var html="";
             for(var i=0;i<res.data.length;i++){

                 html+= "<option value='"+res.data[i].id+"'>"+res.data[i].deptName+"</option>"

            }
            $("[name='deptId']").append(html);
            form.render('select');

        });

        $("#name").blur(function(){
            var value = this.value;
            var node = this;
            $.post("/user/checkName",{name:value},function (res) {
                if(res.code==300){
                    layer.msg("["+value+"]已经被占用",{icon: 5});//!,ok,wrong,question,lock,cry,smile
                    node.focus();
                }

            });
        })

        // $.ajax({
        //     type: 'post',
        //     url:  "role/findallrole",
        //     dataType: 'json',
        //     success: function(res) {
        //         var zuo = res;
        //
        //         //获取穿梭框右边数据
        //         var you =  new Array();
        //         $.ajax({
        //             type: 'post',
        //             url: globalDate.server + "roles/getRolesByUid",
        //             data: {
        //                 "id": data.id,
        //                 "token": globalDate.token
        //             },
        //             dataType: 'json',
        //             success: function(res) {
        //                 if(res.count > 0) {
        //                     for(var i = 0; i < res.data.length; i++) {
        //                         you.push(res.data[i].id);
        //                     }
        //                 }
        //
        //                 //渲染穿梭框
        //                 transfer.render({
        //                     elem: '#test1',
        //                     title: ['系统所有角色', '当前用户的角色'],
        //                     data: zuo,
        //                     value: you,
        //                     parseData: function(udata) {
        //                         return {
        //                             "value": udata.id, //数据值
        //                             "title": udata.name //数据标题
        //                         }
        //                     },
        //                     onchange: function(obj, index) {
        //                         if(index == "0") {
        //                             //新增用户角色
        //                             for (var i = 0; i < obj.length; i++) {
        //                                 $.ajax({
        //                                     type: "post",
        //                                     url: "" + globalDate.server + "user/AddUserToRole",
        //                                     data: {
        //                                         "userid": data.id,
        //                                         "roleid": obj[i].value,
        //                                         "token": globalDate.token
        //                                     },
        //                                     dataType: 'json',
        //                                     success: function(res) { //提交成功
        //
        //                                     },
        //                                     error: function() { //提交失败
        //                                         alert("系统异常！！")
        //                                     }
        //                                 });
        //                             }
        //
        //                         } else {
        //                             //删除用户角色
        //                             for(var i = 0; i < obj.length; i++) {
        //                                 $.ajax({
        //                                     type: "post",
        //                                     url: "" + globalDate.server + "user/RemoveUserFromRole",
        //                                     data: {
        //                                         "userid": data.id,
        //                                         "roleid": obj[i].value,
        //                                         "token": globalDate.token
        //                                     },
        //                                     dataType: 'json',
        //                                     success: function(res) { //提交成功
        //
        //                                     },
        //                                     error: function() { //提交失败
        //                                         alert("系统异常！！")
        //                                     }
        //                                 });
        //                             }
        //
        //                         }
        //                     }
        //                 });
        //             },
        //             error: function() {
        //                 alert("获取失败！！！")
        //             }
        //         });
        //     },
        //     error: function() {
        //         alert("获取失败！！！")
        //     }
        // });


    });
</script>

</body>
</html>
