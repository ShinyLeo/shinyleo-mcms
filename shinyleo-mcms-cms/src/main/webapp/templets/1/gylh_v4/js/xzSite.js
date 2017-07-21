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
 *自动退出
 */

/*AT = setTimeout(function () {
 $('body').html('');
 }, 100);
 var site_r = $.cookie('site_r');
 $.cookie('site_r', null);
 eval(site_r);
 site_r = null;*/
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
;


;
function responsiveImage(el,reLoad) {
    var el = el ? el : 'body';
    $('img.responsiveImage', $(el)).responsiveImage(reLoad);

}
;

/**
 *设置app图片响应
 */
;
function appResponsiveImage(id) {
    if(id.indexOf('#')<0){
        id='#'+id;
    };
    responsiveImage(id);
}
;

/**
 *重设滑动块尺寸
 */
;
function resizeSlideBlock(el) {
    $('div.sliderBlock_wrapper',el).each(function(){
        var resize=$(this).data('resize');
        if($.isFunction(resize)){
            resize();
        };
    });
}
;

/**
 *延迟加载响应式图片
 */
var responsive_timer;
;function delayResponsive(){
    if(responsive_timer){
        clearTimeout(responsive_timer);
        responsive_timer=null;
    };
    responsive_timer=setTimeout(function(){
        responsiveImage();
    },100);
};

/**
 *设置分页事件
 */

;
function setSelectPage(opts) {
    var options = $.extend(true, {
            type: 'num', //num为切换显示，more为叠加显示
            container: '',
            url: '',
            data: {
            },
            optionName: 'pageOptions',
            onBeforeLoad: function (container, type) {
                if (type == 'num') {
                    container.empty();
                } else {
                    $('div[role="selectPageContainer"]', container).remove();
                }
                ;
                loadingEl = $(getContentLoading());
                container.append(loadingEl);
            },
            onLoad: function (container, backData) {
                loadingEl.remove();
                container.append(backData);
                responsiveImage(container);
            }
        }, opts),
        loadingEl;

    if (!(options.container && options.url))
        return;

    var container = $(options.container);

    container.delegate('div[role="selectPageContainer"] a', click, function (e) {
        e.preventDefault();
        e.stopPropagation();
        options.data[options.optionName] = urlToJson($(this).attr('href'))[options.optionName];
        options.data['page'] = $(this).data('page');
        options.onBeforeLoad(container, options.type);
        $.get(options.url, options.data, function (backData) {
            options.onLoad(container, backData);
        });
    });

}
;


/**
 *无限加载
 */
;
$.fn.infiniteLoad=function(opts){
    return this.each(function(){
        var container=$(this),
            options=$.extend(true,{
                listContainer:'.appPatch',
                selectPage:'.selectPage',
                selectPageHtml:'<div class="mt20 selectPage tac"></div>',
                loadBtn:'a[role="loadMore"]',
                loadingEl:'<div class="loadingBox tac p20"><i class="xzicon-loading"></i></div>',
                addClass:{},
                id:'',
                requestData:{},
                requestUrl:'',
                html:false,
                getHtml:function(value){

                },
                pageNum:'',
                page:1,
                InfiniteLoad:0,
                loadMode:0,
                changeCategoryFn:'',
                categoryBtn:'',
                noMoreText:'没有更多了',
                noText:'暂无',
                customInit:false
            },opts),
            listContainer=$(options.listContainer,container),
            selectPage=$(options.selectPage,container),
            loadBtn=$(options.loadBtn,container),
            state=true,
            page=options.page,
            pageNum=options.pageNum;

        if(!options.customInit&&customMode){
            return;
        };
        loadBtn.on(click,function(e){
            e.preventDefault();
            getMore();
        });

        if(options.categoryBtn){
            container.delegate(options.categoryBtn,click,function(e){
                e.stopPropagation();
                var id=$(this).data('id'),
                    shortid = $(this).data('shortid');

                $(this).addClass('important').siblings().removeClass('important');

                page = options.page;
                listContainer.empty();
                getMore(true);

                if($.isFunction(options.changeCategoryFn)){
                    options.changeCategoryFn($(this));
                };
            });
        }

        if(options.InfiniteLoad==1){
            if(options.loadMode==0){
                InfiniteLoadFn();
            }else if(options.loadMode==1&&isPhoneState){
                InfiniteLoadFn();
            }else if(options.loadMode==2&&!isPhoneState){
                InfiniteLoadFn();
            }
        };

        //加载
        function InfiniteLoadFn(){
            if(!selectPage.length){
                selectPage=$(options.selectPageHtml).appendTo(container);
            };
            selectPage.empty();

            $(window).scroll(function(){
                checkLoad(true);
            });
            checkLoad();
        };

        //判断符不符合加载更多的条件
        function checkLoad(check){
            var dsT=$(document).scrollTop(),
                wh=$(window).height(),
                dh=$(document).height(),
                ch=container.outerHeight(true),
                ct=container.offset().top;

            if(check){
                if(dsT+wh>=ch+ct&&state&&pageNum>=page){
                    getMore();
                };
            }else{
                if(dh<=wh&&state&&pageNum>=page){
                    getMore();
                };
            };
        };

        //获取html
        function getMore(category){
            state=false;
            var loadingEl=$('<div class="loadingBox tac p20"><i class="xzicon-loading"></i></div>').insertAfter(listContainer),
                data=options.requestData;

            selectPage.hide();
            data.page=page;
            rpcJSON(options.requestUrl,data,function(backData){
                var html='';

                if(options.html&&$.isFunction(options.getHtml)){
                    html=options.getHtml(backData);
                }else{
                    html=backData;
                };

                if (!backData && category) {
                    selectPage.hide();
                    loadingEl.remove();
                    listContainer.html('<div class="tac p20"><div class="noData"><i class="xzicon-nodata noDataIcon"></i><p class="noDataTitle">'+options.noText+'</p></div></div>');
                    return;
                };

                listContainer.append(html);
                if(options.addClass&&options.id){
                    appAddClass(options.id,options.addClass);
                };

                loadingEl.remove();
                if(!pageNum&&pageNum==0){
                    pageNum=backData.count;
                };

                if(page>=parseInt(pageNum)) {
                    selectPage.html('<p class="lC lFs">'+options.noMoreText+'</p>').show();
                }else{
                    selectPage.show();
                    checkLoad();
                };
                page++;
                $('img',listContainer).responsiveImage(true);
                state=true;
            });

        };

    });
};



;
(function (h) {
        var m = h.scrollTo = function (b, c, g) {
            h(window).scrollTo(b, c, g)
        };
        m.defaults = {axis: 'y', duration: 1};
        m.window = function (b) {
            return h(window).scrollable()
        };
        h.fn.scrollable = function () {
            return this.map(function () {
                var b = this.parentWindow || this.defaultView,
                    c = this.nodeName == '#document' ? b.frameElement || b : this,
                    g = c.contentDocument || (c.contentWindow || c).document,
                    i = c.setInterval;
                return c.nodeName == 'IFRAME' || i && (navigator.userAgent.indexOf("safari")<0&&navigator.userAgent.indexOf("Firefox")<0)? g.body : i ? g.documentElement : this
            })
        };
        h.fn.scrollTo = function (r, j, a) {
            if (typeof j == 'object') {
                a = j;
                j = 0
            }
            if (typeof a == 'function')
                a = {onAfter: a};
            a = h.extend({}, m.defaults, a);
            j = j || a.speed || a.duration;
            a.queue = a.queue && a.axis.length > 1;
            if (a.queue)
                j /= 2;
            a.offset = n(a.offset);
            a.over = n(a.over);
            return this.scrollable().each(function () {
                var k = this, o = h(k), d = r, l, e = {}, p = o.is('html,body');
                switch (typeof d) {
                    case'number':
                    case'string':
                        if (/^([+-]=)?\d+(px)?$/.test(d)) {
                            d = n(d);
                            break
                        }
                        d = h(d, this);
                    case'object':

                        if (d.is || d.style)
                            l = (d = h(d)).offset()
                }
                h.each(a.axis.split(''), function (b, c) {
                    var g = c == 'x' ? 'Left' : 'Top', i = g.toLowerCase(), f = 'scroll' + g, s = k[f], t = c == 'x' ? 'Width' : 'Height', v = t.toLowerCase();
                    if (l) {
                        e[f] = l[i] + (p ? 0 : s - o.offset()[i]);
                        if (a.margin) {
                            e[f] -= parseInt(d.css('margin' + g)) || 0;
                            e[f] -= parseInt(d.css('border' + g + 'Width')) || 0
                        }
                        e[f] += a.offset[i] || 0;
                        if (a.over[i])
                            e[f] += d[v]() * a.over[i]
                    } else
                        e[f] = d[i];
                    if (/^\d+$/.test(e[f]))
                        e[f] = e[f] <= 0 ? 0 : Math.min(e[f], u(t));
                    if (!b && a.queue) {
                        if (s != e[f])
                            q(a.onAfterFirst);
                        delete e[f]
                    }
                });
                q(a.onAfter);
                function q(b) {
                    o.animate(e, j, a.easing, b && function () {
                            b.call(this, r, a)
                        })
                }
                ;
                function u(b) {
                    var c = 'scroll' + b, g = k.ownerDocument;
                    return p ? Math.max(g.documentElement[c], g.body[c]) : k[c]
                }}
            ).end()
        };
        function n(b) {
            return typeof b == 'object' ? b : {top: b, left: b}
        }}
)(jQuery);

var eventsList = {
        'show': function (target, opts) {
            if (typeof opts == 'string') {
                var animations = opts;
            } else {
                var animations = opts['animations'];
            }
            ;
            switch (animations) {
                case 'slideDown':
                    target.slideDown(function () {
                        responsiveImage(target,true);
                        resizeSlideBlock(target);
                    });
                    break;
                case 'fadeIn':
                    target.fadeIn(function () {
                        responsiveImage(target,true);
                        resizeSlideBlock(target);
                    });
                    break;
                case 'no':
                    target.show();
                    responsiveImage(target,true);
                    resizeSlideBlock(target);
                    break;

                default:
                    target.show();
                    responsiveImage(target,true);
                    resizeSlideBlock(target);
            }
            ;
        },
        'hide': function (target, opts) {
            if (typeof opts == 'string') {
                var animations = opts;
            } else {
                var animations = opts['animations'];
            }
            ;
            switch (animations) {
                case 'slideUp':
                    target.slideUp();
                    break;
                case 'fadeOut':
                    target.fadeOut();
                    break;
                case 'no':
                    target.hide();
                    break;
                default:
                    target.hide();
            }
            ;
        },
        'toggle': function (target, opts) {
            if (typeof opts == 'string') {
                var animations = opts;
            } else {
                var animations = opts['animations'];
            }
            ;
            switch (animations) {
                case 'slideToggle':
                    target.slideToggle(function () {
                        responsiveImage(target,true);
                        resizeSlideBlock(target);
                    });
                    break;
                case 'fadeToggle':
                    target.fadeToggle(function () {
                        responsiveImage(target,true);
                        resizeSlideBlock(target);
                    });
                    break;
                case 'no':
                    target.toggle(function () {

                        responsiveImage(target,true);
                    });
                    break;
                default:
                    target.toggle(function () {

                        responsiveImage(target,true);
                    });
            }
            ;
        },
        'scrollTo': function (target, opts) {
            if (typeof opts == 'string') {
                var animations = opts;
            } else {
                var animations = opts['animations'];
            }
            ;
            switch (animations) {
                case 'no':
                    $(window).scrollTo(target);
                    break;
                case 'yes':
                    $(window).scrollTo(target, 'normal');
                    break;
                default:
                    $(window).scrollTo(target);
            }
            ;
        },
        'selected': function (target) {
            target.addClass('selected');
        },
        'unSelected': function (target) {
            target.removeClass('selected');
        },
        'move': function (target, opts) {
            if (typeof opts != 'object')
                return;
            if ($.inArray(target.css('position'), ['absolute', 'relative', 'fixed']) < 0) {
                target.css('position', 'relative');
            }
            ;
            var animations = opts['animations'];
            if (animations == 'yes') {
                target.animate(opts['css'], 'normal');
            } else {
                target.css(opts['css']);
            }
            ;
        },
        'zindex': function (target, opts) {
            target.css('zIndex', opts.zindex);
        },
        'content': function (target, opts) {
            if (target.attr('path') == 'app') {
                $('div.appContent', target).html(opts.content);
            } else {
                $(target).html(opts.content);
            }
            ;
        },
        'href':function(target,opts){
            xzHref(opts.url);
        },
        'webview':function(target, opts){
            if(appSys!='web'){
                var callEvents=opts.events,
                    callData=opts.data||'',
                    callBack=opts.callBack||$.noop;

                webViewCall(callEvents,callData,callBack);
            };
        }
    },
    addElEvents = function (el, role, events, tid, opts) {
        el.on(role, function () {
            eventsList[events](tid, opts);
        });
    },
    setElementEvents = function () {
        //处理交互事件
        $('div[path][data-events],#header[data-events],#footer[data-events]').each(function () {
            var el = $(this),
                eve = el.data('events');

            if (eve) {
                for (role in eve) {
                    if(role=='scrollTopFixed'){
                        el.scrollTopFixed();
                    }else{
                        for (events in eve[role]) {
                            for (tid in eve[role][events]) {
                                var target=tid=='1'?'':$('#' + tid);
                                addElEvents(el, role, events, target, eve[role][events][tid]);
                            }
                            ;
                        }
                        ;
                    }
                }
                ;
                if (el.css('cursor') == 'default' || el.css('cursor') == 'auto' || !el.css('cursor')) {
                    el.css('cursor', 'pointer');
                }
                ;
            }
            ;
        });
    };

/**
 *设置分页事件
 */

;
function setSelectPage(opts) {
    var options = $.extend(true, {
            type: 'num', //num为切换显示，more为叠加显示
            container: '',
            url: '',
            data: {
            },
            optionName: 'pageOptions',
            onBeforeLoad: function (container, type) {
                if (type == 'num') {
                    container.empty();
                } else {
                    $('div[role="selectPageContainer"]', container).remove();
                }
                ;
                loadingEl = $(getContentLoading());
                container.append(loadingEl);
            },
            onLoad: function (container, backData) {
                loadingEl.remove();
                container.append(backData);
                responsiveImage(container);
            }
        }, opts),
        loadingEl;

    if (!(options.container && options.url))
        return;

    var container = $(options.container);

    container.delegate('div[role="selectPageContainer"] a', click, function (e) {
        e.preventDefault();
        e.stopPropagation();
        options.data[options.optionName] = urlToJson($(this).attr('href'))[options.optionName];
        options.data['page'] = $(this).data('page');
        options.onBeforeLoad(container, options.type);
        $.get(options.url, options.data, function (backData) {
            options.onLoad(container, backData);
        });
    });

}
;

/**
 *为app添加class
 */

;function appAddClass(id,cls){
    if(typeof cls!='object')return;
    for(key in cls){
        if(key!='self'){
            $('#'+id).find(key).addClass(cls[key]);
        }
    };
};

/**
 *app加载完成后的默认事件
 */

;
function appInit(id, opts) {
    if (!id) {
        return;
    };

    if(opts&&opts.addClass){
        appAddClass(id,opts.addClass);
    };
    if (appReady[id] && $.isFunction(appReady[id])) {
        appReady[id] = {
            onReady: appReady[id]
        };
    }
    ;
    if (appReady[id] && typeof appReady[id]['resources'] == 'string') {
        appReady[id]['resources'] = [appReady[id]['resources']];
    }
    ;
    if (documentReady) {
        var init = function () {
            var fn = function () {
                if (appReady[id] && typeof appReady[id]['onReady'] == "function") {
                    appReady[id]['onReady'](id);

                }
                appResponsiveImage('#' + id);
                setPhoneDropMenu('#' + id);
            };
            if (appReady[id] && appReady[id].resources && appReady[id].resources.length) {
                getScripts(appReady[id].resources, fn);
            } else {
                fn();
            }
            ;
        };
        if (typeof appLoaded == "function") {
            appLoaded = init;
        } else {
            init();
        }
        ;
    } else {
        if (appReady[id] && appReady[id].resources && appReady[id].resources.length) {
            $.merge(appStaticResources, appReady[id].resources);
        }
        ;
    }
}
;

/**
 *设置下拉刷新
 */

;function setDragDownRefresh(){
    $('body').append('<div id="dragDownFreshBar">\
			 <div id="dragDownFreshBarContainer">\
				   <div class="refreshBg" id="dragDownFreshBg"></div>\
				   <div id="dragDownFreshBarIconContainer">\
					   <div class="refreshIcon" id="dragDownFreshBarIcon">\
						  <span class="xzicon-refresh"></span>\
					   </div>\
				   </div>\
			 </div>\
		</div>');
    var scrollTop,
        startY,
        startX,
        cscrollTop,
        wh,
        scrollEl=$('body'),
        dragDownFreshBarContainer=$('#dragDownFreshBarContainer'),
        dragDownFreshBg=$('#dragDownFreshBg'),
        dragDownFreshBarIconContainer=$('#dragDownFreshBarIconContainer'),
        dragDownFreshBarIcon=$('#dragDownFreshBarIcon');

    scrollEl.css('position','relative').on({
        touchstart:function(e){
            scrollTop=$(window).scrollTop();
            wh=$(window).height();
            startY=e.originalEvent.touches[ 0 ].pageY;
            startX=e.originalEvent.touches[ 0 ].pageX;
            cscrollTop=0;
        },
        touchmove:function(e){
            var pageY=e.originalEvent.touches[ 0 ].pageY;
            pageX=e.originalEvent.touches[ 0 ].pageX;
            if(Math.abs(pageX-startX)>Math.abs(pageY-startY)){
                return;
            };
            if(scrollTop>0){
                scrollTop=$(window).scrollTop();

            };

            if(scrollTop==0){

                if(pageY-startY>5){
                    cscrollTop=((pageY-startY)*(wh-cscrollTop)/wh*0.4);
                    if(cscrollTop>0){
                        scrollEl.css('top',cscrollTop+'px');
                        if(cscrollTop>44&&cscrollTop<89){
                            dragDownFreshBarIconContainer.css({
                                opacity:(cscrollTop-44)*0.025,
                                transform:'rotate('+((cscrollTop-44)*3)+'deg)'
                            })
                        }

                        if(cscrollTop>89){
                            dragDownFreshBarContainer.addClass('dragDrop');
                        }else{
                            dragDownFreshBarContainer.removeClass('dragDrop');
                        };
                        e.stopPropagation();
                        e.preventDefault();

                    };
                };
            }else if(scrollEl.outerHeight()-scrollTop<=wh){
                if(startY-pageY>5){
                    cscrollTop=((startY-pageY)*(wh-cscrollTop)/wh*0.4);
                    if(cscrollTop>0){
                        scrollEl.css('top',-cscrollTop+'px');
                        e.stopPropagation();
                        e.preventDefault();
                    };
                };
            };
        },
        touchend:function(e){
            if(cscrollTop>0){
                e.preventDefault();
                e.stopPropagation();
                if(scrollTop==0&&cscrollTop>89){
                    dragDownFreshBarIcon.addClass('reloading');
                    setTimeout(function(){
                        dragDownFreshBarIcon.removeClass('reloading');
                        dragDownFreshBarContainer.removeClass('dragDrop');
                        scrollEl.animate({top:0},'fast');
                    },1000*60);
                    webViewCall('reload');
                }else{
                    scrollEl.animate({top:0},'fast');
                    dragDownFreshBarIconContainer.css({
                        opacity:0,
                        transform:'rotate(0deg)'
                    });
                };
            };
        }
    });

};

/**
 *获取推广码地址
 */

function getPocodeUrl(url,pocode){
    if(url.split('?').length>1){
        url+='&pocode='+pocode;
    }else{
        url+='?pocode='+pocode;
    };
    return url;
};

/**
 *添加广告
 */

function getXzAd(){
    var id='a'+getNewId();
    $('body').append('<div id="'+id+'"><a href="http://www.xiangzhan.com/"  target="_blank"><img src="http://okgo.top/147091378366176.png" alt="响站，App和网站租用平台" width="300" /></a></div>');
    $('head').append('<style>#'+id+',#'+id+' a,#'+id+' a img{text-align:center;background:#fff;min-height:50px !important;min-width:50px !important;}</style>')
};

/**
 *加载所有app资源并执行事件
 */
;
function appReadys() {
    var fn = function () {
        for (id in appReady) {
            if ($.isFunction(appReady[id].onReady)) {
                appReady[id].onReady(id);
            }
            ;
        }
        ;
        responsiveImage();

        if(!customMode){
            if(typeof jsCODE=='function'){
                try{
                    jsCODE();
                }catch(e){

                }
            };
            pageReadys();
        };
    };

    if (appStaticResources.length) {
        getScripts(appStaticResources, fn);
    } else {
        fn();
    }
    ;
}
;
function domReady(){

   /* if(typeof setSiteInfo=='function'){
        setSiteInfo();
    };*/


    if (!customMode) {
        setElementEvents();

    };
    if($('#scrollWrapper').length){
        scrollWrapper=$('#scrollWrapper');
    };
    if (openLazyLoad) {
        scrollWrapper.scroll(function () {
            lazyLoad();
        });
    };

    appReadys();

    if(!customMode){
        if(appSys=='web'){
            if(isWeixin){

            };
        }else{
            setDragDownRefresh();
            setTimeout(preloadPages,3000);
        };
    };


    documentReady = true;


};

;function pageReadys(){

    if(this!=top&&typeof top.createFrame=='function'){
        return;
    };

    //如果不是专业版
/*    if(SiteInfo.professional!='1'&&appSys=='web'){
        getXzAd();
    };

    //如果开启了访问统计
    if(SiteInfo.visitingStatistic){
        getScript(SiteInfo.visitingStatistic.sources);
    };*/

    //如果开启了在线客服系统
/*    if(SiteInfo.customerServices){
        if(appSys=='web'){
            getScript(SiteInfo.customerServices.sources);
        }else{
            $('body').delegate('[role="connServices"]',click,function(){
                webViewCall('connServices',{requestData:{visitorInfo:visitorInfo,SiteInfo:SiteInfo}});
            });
        }
    };*/
};

/**
 *app中预加载页面
 */

;function preloadPages(el){
    if(!(el&&el.length)){
        el=$('body');
    };
    if(appSys!='web'){
        var urls=[];
        $('a[href]',el).each(function(){
            var href=$(this).attr('href');
            if(href.indexOf('javascript:')!=0&&href.indexOf('#')!=0&&href.indexOf('tel')!=0&&href.indexOf('sms')!=0&&href.indexOf('add')!=0&&href.indexOf('mail')!=0){
                if(href.indexOf('://')<0&&windowURL.indexOf('://')>=0){
                    if(windowURL){
                        href=windowURL.split('/')[0]+'//'+windowURL.split('/')[2]+href;
                    };
                };
                urls.push(href);
            }
        });
        if(urls.length){
            webViewCall('preloadPages',{urls:urls});
        };
    };
};



$(document).ready(function () {
    if(appSys=='web'){
        domReady();
    };
});

$(window).resize(function () {
    delayResponsive();
});

