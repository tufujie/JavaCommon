window.onload = function () {
    var obtn = document.getElementById('btn');
    //获取页面可视区的高度
    var clientHeight = document.documentElement.clientHeight;
    var timer = null;
    var isTop = true;

    window.onscroll = function () {
        var osTop = document.documentElement.scrollTop || document.body.scrollTop;
        if (osTop >= clientHeight) {
            obtn.style.display = "block"; //显示按钮
        } else {
            obtn.style.display = "none"; //隐藏按钮
        }
        //非顶部时，清除定时器
        if (!isTop) {
            clearInterval(timer);
        }
        //产生此事件之后设置成false，方便上面清除定时器
        isTop = false;
    };
    obtn.onclick = function () {
        //设置定时器
        timer = setInterval(function () {
            //获取距离顶部的距离，考虑到IE和谷歌获取方式不一样
            var osTop = document.documentElement.scrollTop || document.body.scrollTop;
            //设置每次要上移的长度，因为osTop会慢慢降低，所以对应的osTop/6也会慢慢降低
            var ispeed = Math.floor(-osTop / 6);
            //再次获取滚动条距离顶部的高度
            document.documentElement.scrollTop = document.body.scrollTop = osTop + ispeed;
            //防止还没到达顶部时滚动鼠标或者是点击
            isTop = true;
            //到达顶部时取消定时器
            if (osTop == 0) {
                clearInterval(timer);
            }
        }, 30);
    }

}