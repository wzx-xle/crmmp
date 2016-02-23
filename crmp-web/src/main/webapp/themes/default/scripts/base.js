base = {};

base.attr = {};
base.func = {};
base.event = {};

base.docWidth = 0;
base.docHeight = 0;

$(function () {
	base.func.init();
});

/**
 * 初始化
 */
base.func.init = function () {
	base.func.getDocRim();
}

/**
 * 获取文档的高和宽
 */
base.func.getDocRim = function () {
	if (document.documentElement.clientWidth === 0) {
		if (document.body) {
			base.docWidth = document.body.clientWidth;
		}
	} else {
		base.docWidth = document.documentElement.clientWidth;
	}

	if (document.documentElement.clientWidth === 0) {
		if (document.body) {
			base.docHeight = document.body.clientHeight;
		}
	} else {
		base.docHeight = document.documentElement.clientHeight;
	}
};

/**
 * 简单封装jQuery的ajax方法
 * @param url 请求的地址
 * @param type
 * @param params 请求的参数对象
 * @param onSuccess 成功时的回调函数
 */
base.func.ajax = function (url, type, params, onSuccess) {
	$.ajax({
		url: url,
		type: type,
		cache: false,
		timeout: 5000,
		dataType: 'json',
		data: params,
		success: onSuccess,
		error: function () {
			alert('访问异常！');
		}
	});
};