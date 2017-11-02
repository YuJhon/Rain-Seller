<html>
<#include "../common/header.ftl">

<body>
<script>
    function preview(_this){
        document.getElementById("previewPic").src = _this.value;
    }

</script>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/seller/product/save">
                        <div class="form-group col-sm-6">
                            <label class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-10">
                                <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!''}"/>
                            </div>
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="col-sm-2 control-label">价格</label>
                            <div class="col-sm-10">
                                <input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!''}"/>
                            </div>
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="col-sm-2 control-label">库存</label>
                            <div class="col-sm-10">
                                <input name="productStock" type="number" class="form-control" value="${(productInfo.productStock)!''}"/>
                            </div>
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="col-sm-2 control-label">描述</label>
                            <div class="col-sm-10">
                                <input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!''}" />
                            </div>
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="col-sm-2 control-label">图片</label>
                            <#--<div class="col-sm-2">
                                <img height="100" width="100" src="${(productInfo.productIcon)!''}" alt="">
                            </div>-->
                            <div class="col-sm-10">
                                <input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!''}" onblur="preview(this);"/>
                            </div>
                        </div>
                        <div class="form-group col-sm-6">
                            <label class="col-sm-2 control-label">类目</label>
                            <div class="col-sm-10">
                                <select name="categoryType" class="form-control">
                                    <#list categoryList as category>
                                        <option value="${category.categoryType}"
                                                <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                                    selected
                                                </#if>
                                            >${category.categoryName}
                                        </option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-12">
                            <label class="col-sm-2 control-label">预览图</label>
                            <div class="col-sm-2">
                                <img height="100" width="100" id="previewPic" src="${(productInfo.productIcon)!''}" alt="预览图" onerror="this.hide();">
                            </div>
                        </div>
                        <div class="form-group">
                            <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                            <div class="col-sm-4">
                                <button type="submit" class="btn btn-primary">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>