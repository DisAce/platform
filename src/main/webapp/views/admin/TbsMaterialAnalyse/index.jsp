<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../commons/head.jsp"%>
<body class="no-skin">
	<div id="navbar" class="navbar navbar-default" style="display:none">
			<script type="text/javascript">
				//try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>
		</div>
	<div id="main-container" class="main-container">
	<script type="text/javascript">
	try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>
	<div class="col-sm-12 widget-container-col">
				<div class="widget-box transparent collapsed">
					<div class="widget-header">
						<h4 class="widget-title lighter">
						<i class="fa fa-search orange"></i> 搜索管理列表</h4>
						<div class="widget-toolbar no-border">
							<a href="#" data-action="collapse">
								<i class="ace-icon fa fa-chevron-down"></i>
							</a>
						</div>
					</div>
					<div class="widget-body" style="display: none;">
						<div class="widget-main padding-6 no-padding-left no-padding-right">
						<div class="">
						</div>
						</div>
					</div>
				</div>
			</div>
		<div class="col-xs-12">
			<div class="page-content">
				<div class="widget-box transparent">
					<div class="widget-header widget-header-flat">
						<h4 class="lighter">
							<i class="fa fa-line-chart orange"></i> 进货单数据分析
						</h4>
						<div class="widget-toolbar"></div>
					</div>
					<div class="widget-body">
						<div class="widget-body-inner" style="display: block;">
							<div class="widget-box">
								<div class="widget-body" id="container" >
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="../../commons/foot.jsp"%>
<script type="text/javascript">
	jQuery(document).ready(function() {
	T.U.AjaxRequest({
			url : T.U.RewriteAjaxAction('admin/materialAnalyse/data'),type:"POST",
			callback : function(data) {
				   if (typeof (data)  != 'undefined') {
                 	if(data.success){
					var categories = []; var total = []; var num = []; var singleTotal = []; var weight = [];
					 $.each(data.categories,function(i, item){
			                 categories.push(item);
			                });
			         $.each(data.total,function(i, item){
			                 total.push(item);
			                });
			         $.each(data.num,function(i, item){
			                 num.push(item);
			                });
			         $.each(data.singleTotal,function(i, item){
			                 singleTotal.push(item);
			                });
			         $.each(data.weight,function(i, item){
			                 weight.push(item);
			                });
                 	$('#container').highcharts({
				        chart: {
				            zoomType: 'xy'
				        },
				        credits : {
								enabled : true,
								href : "http://www.coiol.com/",
								text : '统一认证管理平台'
							},
				        title: {
				            text: '<a style="red" href="javascript:void(0);">进货单   数据    分析  报表</>',
				            useHTML:true 
				        },
				        subtitle: {
				            text: 'Source: Coiol.com'
				        },
				        xAxis: [{
				            categories: categories
				        }],
				        yAxis: [{ // Primary yAxis
				            labels: {
				                formatter: function() {
				                    return this.value +'只';
				                },
				                style: {
				                    color: '#89A54E'
				                }
				            },
				            title: {
				                text: '只数',
				                style: {
				                    color: '#89A54E'
				                }
				            },
				            opposite: true
				
				        }, 
				         { // Secondary yAxis
				           
				            title: {
				                text: '总重量',
				                style: {
				                    color: '#666666'
				                }
				            },
				            labels: {
				                formatter: function() {
				                    return this.value +' 斤';
				                },
				                style: {
				                    color: '#666666'
				                }
				            }
				        },
				        { // Secondary yAxis
				            gridLineWidth: 0,
				            title: {
				                text: '总金额',
				                style: {
				                    color: '#4572A7'
				                }
				            },
				            labels: {
				                formatter: function() {
				                    return this.value +' 元';
				                },
				                style: {
				                    color: '#4572A7'
				                }
				            }
				        },{ // Tertiary yAxis
				            gridLineWidth: 0,
				            title: {
				                text: '平均重量',
				                style: {
				                    color: '#AA4643'
				                }
				            },
				            labels: {
				                formatter: function() {
				                    return this.value +' 斤';
				                },
				                style: {
				                    color: '#AA4643'
				                }
				            },
				            opposite: true
				        }],
				        tooltip: {
				            shared: true
				        },
				        series: [{
				            name: '总金额',
				            color: '#4572A7',
				            type: 'column',
				            yAxis: 2,
				            data: total,
				            tooltip: {
				                valueSuffix: ' 元'
				            }
				
				        },{
				            name: '总重量',
				            color: '#808080',
				            type: 'column',
				            yAxis: 1,
				            data: weight,
				            tooltip: {
				                valueSuffix: ' 斤'
				            }
				
				        }, {
				            name: '总只数',
				            color: '#89A54E',
				            type: 'spline',
				            data: num,
				            tooltip: {
				                valueSuffix: ' 只'
				            }
				        }, {
				            name: '平均重量',
				            type: 'spline',
				            color: '#AA4643',
				            yAxis: 3,
				            data: singleTotal,
				            marker: {
				                enabled: false
				            },
				            dashStyle: 'shortdot',
				            tooltip: {
				                valueSuffix: ' 斤'
				            }
				        }]
				    });
					
                 	return;
 					}
 					if(!data.success){
 					T.Box.Tips('操作提示',"获取订货单数据信息错误、请稍后再试！",T.C.I.WarningClass,T.C.I.WarningIoc,T.C.I.style);
 						return;
 					}
                 }else {
                 	T.Box.Tips('操作提示','未知错误！',T.C.I.ErrorClass,T.C.I.ErrorIoc,T.C.I.style);
						return;
                 }	
			}
			});
	    			return false;
		
    });
</script>
</body>
</html>
