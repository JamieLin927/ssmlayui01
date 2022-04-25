<%--
  Created by IntelliJ IDEA.
  User: jamie
  Date: 2022/4/12
  Time: 10:44
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
                            <label class="layui-form-label">部门名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="deptName" autocomplete="off" class="layui-input">
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
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>

<%--添加修改页面--%>
    <form class="layui-form layuimini-form" lay-filter="dataForm"  id="AddUpdatePage" style="display:none;">
    <div class="layui-form-item">
            <div class="layui-input-block">
               <input name="id" type="hidden">
            </div>
        </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">部门名称</label>
        <div class="layui-input-block">
            <input type="text" name="deptName" lay-verify="required" lay-reqtext="部门名称不能为空" placeholder="请输入部门名称" value="" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">部门地址</label>
        <div class="layui-input-block">
            <input type="text" name="deptAdd" lay-verify="required" lay-reqtext="部门地址不能为空" placeholder="请输入部门地址" value="" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">备注</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="remark"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</form>


<script src="${pageContext.request.contextPath}/static/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['layer','form','table'],function(){
        var $=layui.$
           ,layer=layui.layer
           ,form=layui.form
           ,table=layui.table;

        /**
         * 渲染table的数据
         */
        var tableIns=table.render({
            elem:'#currentTableId'
           ,url :'/dept/findallDept'
           ,toolbar: '#toolbarDemo'
           ,defaultToolbar: ['filter', 'exports', 'print', {
                    title: '提示',
                    layEvent: 'LAYTABLE_TIPS',
                    icon: 'layui-icon-tips'
                }]
           ,cols:
                [[
                     {type: "checkbox", width: 50},
                    ,{field: 'id', title: 'ID', width: 80,sort:true}
                    ,{field: 'deptName', title: '部门名称', width: 120}
                    ,{field: 'deptAdd', title: '部门地址', width: 120}
                    ,{field: 'remark', title: '备注', width: 120}
                    ,{title: '操作',  minWidth: 150, toolbar: '#currentTableBar', align: "center"}
                ]]
            ,page:true
            ,limits: [10, 15, 20, 25, 50, 100]
            ,limit: 10
            ,skin: 'line'
            ,response:{
                statusCode:200
                      }
                     });

        /**
         * 条件搜索的表单监听
         */
        form.on('submit(data-search-btn)',function (data){
            console.log(data.field)
            tableIns.reload({
                 where: data.field
                ,page: {
                     curr:1
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

        table.on('tool(currentTableFilter)', function(obj){
            var data=obj.data;
            switch (obj.event){
                case 'edit':
                    editdept(data);
                    break;
                case 'delete':
                    layer.msg("删除");
                    deleteByid(data);
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
               title: "添加部门",
               shade: 0.2,
               maxmin: true,
               anim: 2,
               shadeClose: true,
               area: ['800px', '500px'],
               content: $('#AddUpdatePage'),
                success: function(){

                    $("#AddUpdatePage")[0].reset();

                    url= '/dept/adddept';
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
                        url: "/dept/deleteByIds",
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
        function editdept(data){
            maxIndex= layer.open({
                type: 1,
                title: "编辑部门",
                shade: 0.2,
                maxmin: true,
                anim: 2,
                shadeClose: true,
                area: ['100%', '100%'],
                content: $('#AddUpdatePage'),
                success: function(){

                    form.val('dataForm',data)

                    url= '/dept/updatedept';
                }
            })
        };

        /**
         * 根据id删除方法
         */
        function deleteByid(data){
            layer.confirm("确定删除<font color='red'>"+data.deptName+"</font>的数据吗",{icon: 3, title: '提示信息'}, function (index) {
                $.post("/dept/deleteById", {id:data.id}, function (res) {
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

        });









</script>
</body>
</html>
