var mobile = (/mmp|symbian|smartphone|midp|wap|phone|xoom|iphone|ipad|ipod|android|blackberry|mini|windows\sce|palm/i.test(navigator.userAgent.toLowerCase())),
    lazyOffset = $(window).height(),
    documentReady = false,
    appStaticResources = [],
    appReady = {},
    delayLazyLoad,
    delayRespon,
    devicePixelRatio = $.type(window.devicePixelRatio) !== 'undefined' ? window.devicePixelRatio : 1,
    imgQuality = 90,
    openLazyLoad = appSys=='web'?true:false,
    scrollWrapper=$(window);


/**
 *延迟加载
 */
;
function lazyLoad(el, callBack) {
    var redelayLazyLoad = function () {
        delayLazyLoad = setTimeout(function () {
            el = el ? el : 'body img.responsiveImage';
            $(el).each(function () {
                if (!$(this).data('canLoad')) {
                    $(this).responsiveImage();
                }
                ;
            });
        }, 50);
    };
    if (delayLazyLoad) {
        clearTimeout(delayLazyLoad);
    }
    ;
    redelayLazyLoad();
}
;

/**
 *延迟执行图片响应式
 */

function delayResponsiveImage(){
    var redelayLazyLoad = function () {
        delayRespon = setTimeout(function(){
            lazyLoad();
        }, 500);
    };
    if (delayRespon) {
        clearTimeout(delayRespon);
        delayRespon=null;
    }
    ;
    redelayLazyLoad();
};



/**
 *判断图片是否可加载
 */

;
$.fn.imageCheckLoad = function () {
    return this.each(function () {
        if (!$(this).data('canLoad')) {
            var img = $(this),
                imgTop = img.offset().top,
                contentWindow=$('#scrollWrapper').length?$('#scrollWrapper').parent():$(window);
            if (contentWindow.height() + scrollWrapper.scrollTop() + lazyOffset >= imgTop && imgTop + img.height() + lazyOffset >= scrollWrapper.scrollTop()) {
                img.data('canLoad', 1);
            }
        }
        ;
    });
};


/**
 *根据屏幕大小响应图片
 */
$.fn.responsiveImage = function (reLoad) {

    ;
    return this.each(function (i) {

        var $this=$(this),
            parent = $this.parents(':not(a):first');

        if (!$this.data('src')) {
            var dsrc=$this.attr('src')?$this.attr('src').split('?')[0]:'';
            $this.attr('data-src', dsrc);
        };
        if(!openLazyLoad){
            $this.data('canLoad',1);
        };
        if($this.data('lazyload')!='no'){
            if(!$this.attr('src')){
                $this.attr('src',loadSize(lazyLoadSrc));
            }else if($this.data('load')&&$this.attr('src').indexOf(staticLoadSrc)>0){
                $this.attr('src',loadSize(lazyLoadSrc));
                $this.data('load',0);
            };
        };

        //生成固定尺寸的图片
        function loadSize(src){
            var AH=Math.floor(maxImgSize/devicePixelRatio),
                autoHeight=$this.data('autoHeight')||'',
                w=$this.data('iw')?$this.data('iw'):parent.width(),
                h=autoHeight?AH:parent.height(),
                crop=parent.data('crop')||$this.data('crop'),
                ratio=parent.data('ratio')||$this.data('ratio');

            if(ratio){
                h=Math.floor(w*ratio);
            }else{
                if(!h){
                    if(crop){
                        h=w;
                    }else{
                        h=AH;
                    };
                };
            };

            var nSrc=crop?getCropImageView(src,w,h):getThumbImageView(src,w,h);

            return nSrc;
        };


        var $this = $(this),
            kws = this.style.width,
            parent = $this.parents(':not(a):first'),
            iw = $this.data('ww'),
            ww = parent.width() || $(window).width();

        if(kws){
            kws=kws.replace('px','');
        };

        if (kws && !isNaN(kws) && ww > kws) {
            ww = kws;
        }
        ;

        if ($this.parents('td').length && !$this.parents('td:first').data('responsive')) {
            if (!$this.data('inline')) {
                $this.parents('table:first').css({display: 'inline-block'});
                $this.data('inline', 1);
            }
            ;
            var tlength = $this.parents('tr:first').find('td').length,
                twidth = Math.floor($this.parents('table:first').width() / tlength),
                //yushu=$this.parents('table:first').width()%tlength,
                rdiv = $this.parents('div[role="responsiveDiv"]:first');
            if (!rdiv.length) {
                rdiv = $('<div style="width:' + twidth + 'px;overflow:hidden; display:inline-block;" role="responsiveDiv"></div>').insertBefore($this).append($this);
            } else {
                rdiv.width(twidth);
            }
            ;
            //$this.parents('tr:first').find('td:last div[role="responsiveDiv"]').width(twidth+yushu);

        }
        ;

        if (!iw || iw < ww-100 || !$this.data('canLoad') || reLoad) {
            if (openLazyLoad) {
                $this.imageCheckLoad();
            } else {
                $this.data('canLoad', 1);
            }
            ;

            $this.data('ww', ww);


            if ($this.data('canLoad')) {

                var parent=$this.parents(':not(a):first');
                if(!($this.data('iw')||parent.width())){
                    $this.removeData('canLoad');
                    return;
                };
                var	loaded=function(){
                        lazyImg.remove();
                        $this.loadImg(function () {
                            fireHandler(resizeScroll);
                            delayResponsiveImage();

                        }).setThumbImageView(true);
                    },
                    lazyImg=$('<img data-src="'+$this.data('src')+'" />').data({
                        ratio:$this.data('ratio'),
                        crop:$this.data('crop')
                    });
                lazyImg.loadImg(loaded).on('error',loaded).setThumbImageView({autoHeight:true,parent:parent});

            }
        }
        ;


    });

};