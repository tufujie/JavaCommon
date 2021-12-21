jQuery(document).ready(function() {
	soundObj.init();
});
var soundObj = new function() {
	this.audio_less = !-[ 1, ]; // 利用ie9以下版本与IE 9以上版本和非IE版本浏览器中，JS处理数组最后一个逗号“,”的差异
	this.init = function() {
		/** 提示音 */
		if (!this.audio_less) this.audio_less = (navigator.userAgent.indexOf('MSIE') > -1)
				|| (navigator.userAgent.indexOf('Trident') > -1); // 兼容IE11判断，未验证
		if (!this.audio_less) this.audio_less = navigator.userAgent.indexOf("MSIE") > -1
				|| navigator.appVersion.indexOf("MSIE") > -1; // 其他浏览器判断，未验证
		$(
				this.audio_less ? "<bgsound id=hint_voice src='' autostart=true loop=false></embed>"
						: "<audio id='hint_voice' src='' autoplay autobuffer preload='auto'></audio>").appendTo('body');
		// $("<embed id=hint_voice src='' hidden=true autostart=true loop=false></embed>").appendTo('body');
	};
	this.warn = function() {
		this.play("media/warn.mp3");
	};
	this.finish = function() {
		this.play("media/hint.mp3");
	};
	this.error = function() {
		this.play("media/error.wav");
	};
	this.soundalike = function() {
		this.play("media/soundalike.wav");
	};
	this.submit = function() {
		this.play("media/submit.wav");
	};
	this.careful = function() {
		this.play("media/careful.mp3");
	};
	this.play = function(sound_src) {
		// if (this.audio_less) $("#hint_voice").removeAttr["volume"];
		$("#hint_voice").attr("src", sound_src);
	};
};