<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form  action="${ctx}/alg/equip/edit" method="post">
		<div class="box box-body">
			
			<div class="row">
				<div class="col-xs-6 form-group">
				<input type="hidden"  name="id"
				value="${equip.id}">
					<label for="code"><spring:message code="code" /></label> 
					<input type="text"
						class="form-control" 
						name="code" value="${equip.code }">
				</div>
				<div class="col-xs-6 form-group">
					<label for="barcode"><spring:message code="barcode" /></label> 
					<input type="text"
						class="form-control" 
						name="barcode" value="${equip.barcode }">
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 form-group">
					<label for="type"><spring:message code="equip_type" /></label>
					<select name="type" class="form-control">
					<c:forEach items="${equipType }" var="item">
						<c:choose>
						<c:when test="${item.id eq equip.type }">
							<option selected="selected" value="${item.id }">${item.name }</option>
						</c:when>
						<c:otherwise>
							<option value="${item.id }">${item.name }</option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
				</div>
				<div class="col-xs-6 form-group">
					<label for="type"><spring:message code="position" /></label>
					<select name="algEquipOfficeId" class="form-control">
					<c:forEach items="${equipOfficeType }" var="item">
						<c:choose>
						<c:when test="${item.id eq equip.alg_equip_office_id }">
							<option selected="selected" value="${item.id }">${item.name }</option>
						</c:when>
						<c:otherwise>
							<option value="${item.id }">${item.name }</option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 form-group">
					<label for="posx"><spring:message code="coordinatex" /></label> 
					<input type="text"
						class="form-control" 
						name="posx" value="${equip.posx }">
				</div>
				<div class="col-xs-6 form-group">
					<label for="posy"><spring:message code="coordinatey" /></label> 
					<input type="text"
						class="form-control" 
						name="posy" value="${equip.posy }">
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 form-group">
					<label for="inputCode"><spring:message code="monitor_region" /></label> 
					<select name="posRegion" class="form-control">
					<option  value="D2-2">D2-2<spring:message code="region" /></option>
					<option  value="D1-1">D1-1<spring:message code="region" /></option>
					<option  value="D1-3">D1-3<spring:message code="region" /></option>
					</select>
				</div>
				<div class="col-xs-6 form-group">
					<label><spring:message code="purchase_date"/></label> 
					<input type="text"
						class="form-control" 
						name="extend1" value="${equip.extend1 }">
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 form-group">
					<label><spring:message code="manufacturer"/></label> 
					<input type="text"
						class="form-control" 
						name="extend2" value="${equip.extend2 }">
				</div>
				<div class="col-xs-6 form-group">
					<label><spring:message code="maintenance_period"/></label> 
					<input type="text"
						class="form-control" 
						name="extend3" value="${equip.extend3 }">
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 form-group">
					<label><spring:message code="certificate_number"/></label> 
					<input type="text"
						class="form-control" 
						name="extend4" value="${equip.extend4 }">
				</div>
				<div class="col-xs-6 form-group">
					<label><spring:message code="state"/></label> 
					<select name="extend5" class="form-control">
					<option  value="1"><spring:message code="in_use" /></option>
					<option  value="0"><spring:message code="abandoned" /></option>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 form-group">
					<label><spring:message code="category"/></label> 
					<input type="text"
						class="form-control" 
						name="extend6" value="${equip.extend6 }">
				</div>
				<div class="col-xs-6 form-group">
					<label><spring:message code="specifications"/></label> 
					<input type="text"
						class="form-control" 
						name="extend7" value="${equip.extend7 }">
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 form-group">
					<label><spring:message code="notes"/></label> 
					<input type="text"
						class="form-control" 
						name="extend8" value="${equip.extend8 }">
				</div>
				
			</div>
			<div class="row">
				<div class="col-xs-12 form-group">
					<label><spring:message code="picture"/></label> 
					<input id="upload" class="form-control" type="file">
					<c:if test="${empty equip.image }">
						<img id="imgEquip" width="60" height="60" src="${ctxStatic}/img/noimg.png">
					</c:if>
					<c:if test="${ not empty equip.image}">
						<img id="imgEquip" width="60" height="60" src="${ctxStatic}/../upload/thumb/${equip.image}">
					</c:if>
					
					
			</div>
			<div class="box-footer">
				<button type="button" class="btn pull-right" onclick="parent.layer.closeAll()"><spring:message code="cancel" /></button>
				<button type="submit" class="btn btn-primary pull-right"><spring:message code="save" /></button>
			</div>

		</div>
	</form>
<script type = "text/javascript" > 
$(function() {
	
	$('[name="posRegion"]').val('${equip.pos_region}');
	$('[name="extend5"]').val('${equip.extend5}');
	$('form').bootstrapValidator({
		fields: {
			code: {
				validators: {
					notEmpty: {
						message: '<spring:message code="msg_not_null" />'
					}
				}
			},
			barcode: {
				validators: {
					notEmpty: {
						message: '<spring:message code="msg_not_null" />'
					}
				}
			},
			posx: {
				validators: {
					notEmpty: {
						message: '<spring:message code="msg_not_null" />'
					},
					digits:{
						message:'<spring:message code="msg_digit" />'
					}
				}
			},
			posy: {
				validators: {
					notEmpty: {
						message: '<spring:message code="msg_not_null" />'
					},
					digits:{
						message:'<spring:message code="msg_digit" />'
					}
				}
			}
		}
	}).on('success.form.bv', function(e) {
		//防止表单以老式的方法提交  
		  e.preventDefault();  
		  var $form=$(this);  
		  postMethod=$form.attr('method');
		  postURL=$form.attr('action');  
		  var postData=$form.serialize();
		  $.ajax({   
		    url: postURL,   
		    type: postMethod,  
		    data: postData,  
		    success: function(data){  
		    	layer.msg(data.text);
		    	if (data.code=='success') {
					setTimeout(function () {
						if (parent.document.getElementById('tbContent')!=null) {
							parent.$('#tbContent').bootstrapTable(('refresh'));
						}
						
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引  
						parent.layer.close(index);//关闭弹出的子页面窗口 
					}, 500);
				}
		     } 
		  });   
	});
	$('#upload').change(function() {
			if (this.files.length==0) {
				return false;
			}
            var fileObj = this.files[0];
            var formData = new FormData();
            var url =  "${ctx}/file/upload"; // 接收上传文件的后台地址
            formData.append("equipid","${equip.id}");
            formData.append("file",fileObj);
            
            xhr = new XMLHttpRequest();  // XMLHttpRequest 对象
            xhr.open("post", url, true); //post方式，url为服务器请求地址，true 该参数规定请求是否异步处理。
            xhr.onload = uploadComplete; //请求完成
            xhr.onerror =  uploadFailed; //请求失败
            xhr.send(formData); //开始上传，发送form数据
	})
	//上传成功响应
        function uploadComplete(evt) {
            //服务断接收完文件返回的结果
            var data = JSON.parse(evt.target.responseText);
            layer.msg(data.text);
            console.log(data);
            $('#imgEquip').attr('src','${ctxStatic}/../upload/thumb/'+data.extend);
        }
        //上传失败
        function uploadFailed(evt) {
        	console.log(evt);
        }
}) 
</script>
</body>
</html>