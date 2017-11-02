<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/seller/category/save">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-2 control-label">名字</label>
                            <div class="col-sm-10">
                                <input name="categoryName" type="text" class="form-control" value="${(category.categoryName)!''}"/>
                            </div>
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="col-sm-2 control-label">类型</label>
                            <div class="col-sm-10">
                                <input name="categoryType" type="number" class="form-control" value="${(category.categoryType)!''}"/>
                            </div>
                        </div>
                        <input hidden type="text" name="categoryId" value="${(category.categoryId)!''}">
                        <button type="submit" class="btn btn-primary">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>