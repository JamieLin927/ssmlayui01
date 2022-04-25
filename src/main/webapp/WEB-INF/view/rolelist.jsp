<%--
  Created by IntelliJ IDEA.
  User: jamie
  Date: 2022/4/13
  Time: 19:48
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui_ext/dtree/font/dtreefont.css">
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
                            <label class="layui-form-label">角色名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="roleName" autocomplete="off" class="layui-input">
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
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-radius data-count-delete" lay-event="authmenu"><i class="layui-icon">&#xe672;</i>授权</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
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
        <label class="layui-form-label required">角色名称</label>
        <div class="layui-input-block">
            <input type="text" name="roleName" id="rname" lay-verify="required" lay-reqtext="用户名不能为空" placeholder="请输入用户名" value="" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">备注</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="roleRemark"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</form>
<div id="menulist" hidden="hidden">
    <ul id="demoTree" class="dtree" data-id="0" data-value="001"></ul>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.extend({
        dtree: '${pageContext.request.contextPath}/static/layui_ext/dtree/dtree'   // {/}的意思即代表采用自有路径，即不跟随 base 路径
    }).use(['form', 'table','jquery','dtree'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            dtree = layui.dtree;
 
        var tableIns= table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/role/findallrole',
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
                {field: 'roleName', width: 80, title: '部门名称',align: "center"},
                {field: 'roleRemark', width: 120, title: '备注', sort: true,align: "center"},

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
                case  'authmenu':
                    authrolemenu(data);
                    break;
            }
        });


        var maxIndex ;
        var url;
        var tip;
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

                    url= '/role/insertrole';
                    tip=1;
                }
            })
        };



        function  authrolemenu(data){
            maxIndex= layer.open({
                type: 1,
                title: "正在为<font color='red'>"+data.roleName+"</font>授权",
                shade: 0.2,
                maxmin: true,
                anim: 2,
                shadeClose: true,
                area: ['400px', '600px'],
                content: $('#menulist'),
                btnAlign: 'c',
                btn: ['确定',  '取消'],
                yes: function(index, layero){
                    var params = dtree.getCheckbarNodesParam("demoTree");
                    if (params.length>0){
                        var ids=[];
                        for (var i in params) {
                            ids.push(params[i].nodeId);
                        }
                        $.post("/role/authrolemenu", { "ids":ids,"roleId": data.id}, function (res) {
                            layer.msg(res.msg);
                        },"json");
                        layer.close(maxIndex);
                        parent.location.replace("${pageContext.request.contextPath}/index")
                    }
                    else {
                        layer.msg("请选择要授权的菜单")
                    }
                }

               ,btn2: function(index, layero){
                //按钮【按钮三】的回调

                //return false 开启该代码可禁止点击该按钮关闭
                },
                success: function(){
                    // 初始化树

                    dtree.render({
                        elem: "#demoTree",
                        url: "${pageContext.request.contextPath}/role/initroleMenu?roleId="+data.id,
                        dataStyle: "layuiStyle",  //使用layui风格的数据格式
                        dataFormat: "list",
                        checkbar: true,
                        response:{message:"msg",statusCode:200} ,

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
                        url: "/role/deleteroleByIds",
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

                    url= '/role/updaterole';
                    tip=2;
                }
            })
        };

        /**
         * 根据id删除方法
         */
        function deleteByid(data){
            layer.confirm("确定删除<font color='red'>"+data.roleName+"</font>的数据吗",{icon: 3, title: '提示信息'}, function (index) {
                $.post("/role/deleteroleById", {id:data.id}, function (res) {
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
            $.post("/role/checkName",{roleName: data.field.roleName}, function (res) {

                if (res.code == 300) {
                    layer.tips("<span style='color:orangered;'>"+data.field.roleName+"这个角色已存在"+"</span>","#rname",{tips:[1,'#fff'],time:4000,area: 'auto',maxWidth:500});
                    //layer.msg("[" + data.field.roleName + "]这个角色已经被存在", {icon: 5});//!,ok,wrong,question,lock,cry,smile
                    $("#rname").focus();
                }
                else{
                    $.post(url, data.field, function (res) {
                        if (res.code == 200) {
                            //添加成功刷新表格，这样才能显示刚刚添加成功的数据
                            tableIns.reload();
                            //成功后还要把添加窗口关闭
                            layer.close(maxIndex);
                        }
                        layer.msg(res.msg);


                    })
                }
            });



            //返回false是因为防止页面刷新
            return false;

        });


        // $("#rname").blur(function(){
        //     var value = this.value;
        //     var node = this;
        //     if(tip==1) {
        //         $.post("/role/checkName", {rolename: value}, function (res) {
        //             if (res.code == 300) {
        //                 layer.msg("[" + value + "]这个角色已经被存在", {icon: 5});//!,ok,wrong,question,lock,cry,smile
        //                 $("#rname").focus();
        //             }
        //         });
        //
        //     }
        //
        // })




    });
</script>

</body>
</html>
