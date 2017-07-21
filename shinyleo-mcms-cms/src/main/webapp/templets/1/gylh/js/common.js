/**
 *突唯阿公共js
 *李煜
 *2013-7-16
 */

/*
 *公共数据
 */
var click='click',//默认点击事件
    process=false,//过程状态，过程进行中，禁止操作。
    $preventEvent=false,//禁止触摸事件
    webViewCall=$.noop,
    targetOrigin = '*',
    thisFrameId='',
    supportTouch=isTouch="ontouchend" in document,
    maxImgSize=9999,
    topIndex=11000,
    windowHeight=$(window).height(),
    windowWidth=$(window).width(),
    IEV=0,
    videoExtensions='mp4,mov,flv,f4v,mpe,vob,wmv,mpg,mlv,mpeg,avi,3gp,ra,rm,rmvb,ram',
    imageExtensions='jpg,jpeg,png,gif',
    isPhoneState=windowWidth<=768,//手机状态
    devicePixelRatio=Math.min($.type(window.devicePixelRatio) !== 'undefined'?window.devicePixelRatio:1,1.6),//屏幕倍数
    isMac=navigator.userAgent.indexOf("Mac OS X")>-1,
    isIos=!!navigator.userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
    isAndroid=navigator.userAgent.indexOf('Android') > -1 || navigator.userAgent.indexOf('Linux') > -1, //android终端或uc浏览器
    isIphone=navigator.userAgent.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
    isIpad=navigator.userAgent.indexOf('iPad') > -1,//是否iPad
    isWeixin=navigator.userAgent.toLowerCase().match(/MicroMessenger/i)=="micromessenger";//是否微信

var youku_client_id='450a88312f2d2c7d',//优酷client id
    videoCodeImgSrc=filePath+'videoCode.png',//视频转码中图片
    errorImgSrc= '{ms:globalskin.url/}images/errorImg.png',//加载错误图片
    staticLoadSrc='/images/lazyLoad.png',
    lazyLoadSrc=filePath+'lazyLoad.png';

$.toJSON=function(obj){
    if(typeof obj=='object'){
        return JSON.stringify(obj);
    }else{
        return obj;
    };
};

/**
 *低级浏览器提示
 */

var lowBrowserHtml='有部分功能您的浏览器不支持，请更换高级浏览器，建议使用<a href="http://www.firefox.com" target="_blank" title="下载firefox浏览器">firefox</a>,<a href="https://www.google.com/intl/en/chrome/browser/" target="_blank" title="下载chrome浏览器">chrome</a>或<a href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie" target="_blank" title="下载IE10浏览器">IE10及以上</a>';

;function lowBrowser(callBack){
    Alert(lowBrowserHtml,callBack);
};

;function getHost(){
    return  windowURL.split('://').length>1?windowURL.split('://')[0]+'://'+windowURL.split('://')[1].split('/')[0]:'';
};

;function getDomain(){
    var host=windowURL.split('://').length>1?windowURL.split('://')[1].split('/')[0]:'';
    var domains=host.split('.');
    var domain=domains[domains.length-2]+'.'+domains[domains.length-1];
    return domain;

};

;function getQrcode(data){
    if(data){
        data=data.toString();
    };
    if(data.indexOf('://')>-1){
        data=encodeURIComponent(data);
    };
    return 'http://'+domain+'/api/qrcode/?data='+data;
};


function getIeVersion(){

    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
    var fIEVersion=0;
    if (isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        fIEVersion = parseFloat(RegExp["$1"]);

    };
    return fIEVersion;
};

IEV=getIeVersion();
/**
 *输出测试数据
 */
;function test(smg,plus){
    var testContainer=$('#testContainer');
    if(!testContainer.length){
        testContainer=$('<div id="testContainer" style="position:fixed; z-index:'+getTopIndex()+'; top:0; left:50px;padding:20px;background:rgba(0,0,0,.5);color:#fff; max-height:100%;overflow:auto;"></div>').appendTo('body');
    };

    if(!(typeof smg=='array')){
        smg=[smg];
    };

    var html=plus&&testContainer.html()?testContainer.html()+'<br>':'';
    $.each(smg,function(i,item){
        if(i>0){
            html+='/';
        };
        html+=item;
    });

    testContainer.html(html);
};

;function tests(smg){
    test(smg,1);
};
;function objectLength(obj){
    var i=0;
    if(typeof obj=='object'){
        for(key in obj){
            i++;
        }
    };
    return i;
};

/**
 *过滤script标签
 */

;function stripScript(s) {
    return s.replace(/<script.*?>.*?<\/script>/ig, '');
};

/**
 *图片的加载事件
 */
;$.fn.loadImg=function(callback){
    return this.each(function(){
        if(!$.isFunction(callback))return;
        var img=$(this)[0],
            onLoads;

        if($(this).data('onLoads')){
            onLoads=$(this).data('onLoads');
            onLoads.push(callback);
        }else{
            onLoads=[callback];
        };
        $(this).data('onLoads',onLoads);

        if (document.all) { //如果是IE

            var timer = setInterval(function() {
                if (img.complete) {
                    $.each(onLoads,function(i,item){
                        item(img);
                    });
                    clearInterval(timer);
                }
            }, 50);

        }

        else {

            img.onload=function(){
                $.each(onLoads,function(i,item){
                    item(img);
                });
            };

        };
    });
};

/**
 *动态加载js和css
 */

;function getScript(src,callback){
    if(!src)return;
    var type=src.split('.')[src.split('.').length-1],
        lscript=type=='css'?$('link[href="'+src+'"]'):$('script[src="'+src+'"]'),
        callback=$.isFunction(callback)?callback:$.noop;
    if(lscript.length){
        lscript=lscript[0];

        if(lscript.loadeds){
            if(lscript.loaded=='yes'){
                callback(lscript);
            }else{
                lscript.loadeds.push(callback);
            }
        }else{
            callback(lscript);
        };
    }else{
        var newscript;

        if(type!='css'){
            newscript = document.createElement("script");
            newscript.src=src;
        }else{
            newscript = document.createElement("link");
            newscript.href=src;
            newscript.rel="stylesheet";
            newscript.type="text/css";
        };

        newscript.loadeds=[callback];

        var jsonload=function(){
            newscript.loaded='yes';
            $.each(newscript.loadeds,function(i,item){
                item(newscript);
            });
        };

        if (document.all) { //如果是IE

            newscript.onreadystatechange = function () {

                if (newscript.readyState == 'loaded' || newscript.readyState == 'complete') {

                    jsonload();

                }

            }

        }

        else {

            newscript.onload=jsonload;

        };

        document.body.appendChild(newscript);
    }

};

;function getScripts(srcs,callback){
    var i=0,
        callback=callback||$.noop,
        loads=function(){
            getScript(srcs[i],function(){
                i++;
                if(i<srcs.length){
                    loads();
                }else{
                    callback();
                };
            });
        };
    loads();
};

/**
 *检测并执行方法
 */

;function fireHandler(fn){
    if($.isFunction(fn)){
        fn();
    };
};



/**
 *设置本地存储
 */

;var storage={
    set:function(key,value){
        if(!(key&&value&&localStorage&&localStorage.setItem))return;
        if(typeof value=='object'){
            value='json_'+$.toJSON(value);
        };
        localStorage.setItem(key,value);
    },
    get:function(key){
        if(!(key&&localStorage&&localStorage.setItem))return null;
        var value=localStorage.getItem(key);
        if(value&&value.indexOf('json_')==0){
            value=$.parseJSON(value.substr(5));
        };
        return value;
    },
    clear:function(){
        if(!(localStorage&&localStorage.setItem))return ;
        localStorage.clear();
    },
    remove:function(key){
        if(!(key&&localStorage&&localStorage.setItem))return ;
        localStorage.removeItem(key);
    }
};

/*
 *获取当前时间
 */

function getNowDate(type,returnSeconds) {
    var date = new Date();
    var seperator1 = "/";
    var seperator2 = ":";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours=date.getHours();
    var minutes=date.getMinutes();
    var seconds=date.getSeconds();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    };
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    };

    var currentdate = year + seperator1 + month + seperator1 + strDate;
    var currentTime=(hours<=9?'0'+hours:hours) + seperator2 + (minutes<=9?'0'+minutes:minutes)+(returnSeconds?seperator2 + (seconds<=9?'0'+seconds:seconds):'');

    return type=='all'?currentdate+' '+currentTime:type=='time'?currentTime:currentdate;
};

/**
 *计算一个日期字符串与当前日期相差值
 *输入的参数形式如：2012/12/12 12:12:12或2012-12-12 12:12:12
 *返回相差值的字符串
 */
function getDateDiff(datetime,short){
    if(!datetime)return '';
    // 可以将2012 - 12 - 12 12 : 12 : 12字符串转为JS中的时期对象,
    // 因为默认情况下只把持2000 / 05 / 05这样形式的字符串转为时间对象
    var dateBegin = new Date(datetime.replace(/-/g, "/"));
    var dateEnd = new Date();
    var beginDay = dateBegin.getDate();
    var endDay = dateEnd.getDate();
    var beginMonth = dateBegin.getMonth();
    var endMonth = dateEnd.getMonth();
    var beginYear = dateBegin.getFullYear();
    var endYear = dateEnd.getFullYear();
    var dateDiff = dateEnd.getTime() - dateBegin.getTime();
    var yearDiff=endYear-beginYear;
    // 计算相差的天数
    var dayDiff = endDay-beginDay+yearDiff*365+(endMonth-beginMonth)*31;
    var parttime = datetime.substring(11);
    var returnstr = "";

    if(dayDiff > 2) //前天以前就直接返回时间字符串
    {
        var date=datetime.substr(0,10);
        if(short&&endYear==beginYear){
            date=date.substr(5,10);
        };
        return short?date:datetime;
    }
    else //前天以来的处理
    {

        switch (dayDiff)
        {
            case 2 :
                returnstr += (short?SiteText.beforeYesterday:SiteText.beforeYesterday+' ' + parttime);
                break;
            case 1 :
                returnstr += (short?SiteText.yesterday:SiteText.yesterday+' ' + parttime);
                break;
            default : //对今天的操作
                var minuteleft = Math.floor(dateDiff / (60 * 1000)); //计算相差的分钟数
                if(minuteleft > 30)
                {
                    returnstr += parttime;
                }
                else if (minuteleft < 1)
                {
                    returnstr += SiteText.justNow;
                }
                else
                {
                    returnstr += minuteleft + SiteText.beforeMinutes;
                }
        };

        return returnstr;
    }
};

$.fn.dateDiff=function(opts){
    return this.each(function(){
        var options=$.extend(true,{
            short:true
        },opts)
        var dateTime=$(this).data('time');
        if(!dateTime){
            dateTime=$(this).text();
            $(this).data('time',dateTime);
        };
        var diff=getDateDiff(dateTime,options.short);
        $(this).text(diff);
    });
};
/*
 *解析url
 */
function urlToJson(str)
{
    str=decodeURIComponent(str);
    var data={},
        name=null,
        value=null,
        num=str.indexOf("?");

    str=str.substr(num+1);
    var arr=str.split("&");
    for(var i=0;i < arr.length;i++){
        num=arr[i].indexOf("=");
        if(num>0){
            name=arr[i].substring(0,num);
            value=arr[i].substr(num+1);
            data[name]=value;
        }
    };
    return data;
};

/**
 *字符替换为图标
 */

;function stringToIcon(str){

    var str;
    if(str=='x'){
        str='<span class="xzicon-close"></span>';
    }else if(str=='<'){
        str='<span class="xzicon-arrow-l"></span>';
    }else if(str=='>'){
        str='<span class="xzicon-arrow-r"></span>';
    }else if(str=='...'){
        str='<span class="xzicon-more"></span>';
    }else if(str=='y'){
        str='<span class="xzicon-yes"></span>';
    }else if(str=='i'){
        str='<span class="xzicon-about"></span>';
    }else if(str=='<-'){
        str='<span class="xzicon-arrow-back"></span>';
    };
    return str;
};

/**
 *获取随机数
 */
;
function getRandom(length) {
    var charactors = "ab1cd2ef3gh4ij5kl6mn7opq8rst9uvw0xyz",
        value = '',
        i;
    length = length || 4;
    for (j = 1; j <= length; j++) {
        i = parseInt(35 * Math.random());

        value += charactors.charAt(i);
    }
    ;
    return value;
}
;

/**
 *获取时间加随机数
 */
function getNowRandom(){
    var j=Math.ceil(Math.random()*10000).toString(),
        m=j.length;
    if(m<4){
        for(q=0;q<4-m;q++){
            j+='0';
        };
    };
    return $.now()+j;
};

/**
 *获取新的唯一id
 */
var getNewTempId = 0;
;
function getNewId() {
    getNewTempId++;
    return $.now() + getRandom() + getNewTempId;
}
;


/**
 *获取最高层级
 */

;function getTopIndex(){
    return topIndex++;
};

/**
 *设置遮罩
 */

;function setMask(flag){
    if(flag==0){
        $('#bodyMask').remove();
    }else if(!$('#bodyMask').length){
        $('<div id="bodyMask" style="position:absolute;left:0;top:0;width:100%;height:100%;background:#fff;opacity:0.01;z-index:'+getTopIndex()+'"></div>').appendTo('body');
    }
};


/**
 *渐隐并删除元素
 */

$.fn.hideRemove = function(opts){
    if($.isFunction(opts)){
        opts={
            onRemove:opts
        }
    };
    var options=$.extend({},{easing:'linear',speed:'fast',height:0,onRemove:$.noop},opts),
        height=opts==1?true:false;
    return this.each(function(i) {
        $(this).animate({opacity:0,height:height?$(this).height:options.height},options.speed,options.easing,function(){
            $(this).remove();
            options.onRemove();
        });
    });
};

/**
 *简短提示信息
 */

;function Tips(type,msg,timer,callback){
    if(this!=top&&top.Tips){
        return top.Tips(type,msg,timer,callback);
    };

    if(type&&!msg){
        var msg=type;
        type='error';
    };

    var html,
        message,
        type=type||'default',
        timer=timer||1000;
    switch(type){
        case 'loading':
            message=msg||SiteText.loading;
            html='<div class="tipsLoading tips"><div class="tipsPad"><i class="xzicon-tipsLoading"></i>'+message+'</div></div>';
            break;

        case 'success':
            message=msg||SiteText.success;
            html='<div class="tipsSuccess tips"><div class="tipsPad"><i class="xzicon-tipsSuccess"></i>'+message+'</div></div>';
            break;

        case 'error':
            message=msg||SiteText.operationFailure;
            html='<div class="tipsError tips"><div class="tipsPad"><i class="xzicon-tipsError"></i>'+message+'</div></div>';
            break;

        case 'warning':
            message=msg||SiteText.attention;
            html='<div class="tipsWarning tips"><div class="tipsPad"><i class="xzicon-tipsWarning"></i>'+message+'</div></div>';
            break;

        case 'news':
            message=msg||SiteText.newMessage;
            html='<div class="tipsNews tips"><div class="tipsPad"><i class="xzicon-about"></i>'+message+'</div></div>';
            break;

    };

    process=true;
    //setMask();

    var msgEl=$(html).appendTo('body');
    msgEl.css({
        zIndex:getTopIndex(),
        left:($('body').width()-msgEl.outerWidth())/2
    }).hide().fadeIn();

    //msgEl.toCenter().hide().fadeIn();

    msgEl.close=function(){
        if(!msgEl.length)return;
        msgEl.fadeOut(function(){
            msgEl.remove();
            process=false;
            //  setMask(0);
            fireHandler(callback);
        });
    };
    msgEl.on(click,msgEl.close);


    if(timer>-1){
        setTimeout(msgEl.close,timer);
    };

    return msgEl;
};

/**
 *提示遮罩层
 */

;function setOverlay(opacity,bgColor,container,zIndex){
    var overlay=jQuery('<div style="position:fixed;display:none;left:0;top:0;width:100%;height:100%;opacity:'+(opacity||0.01)+';background:'+(bgColor||'#000')+';z-index:'+(zIndex||getTopIndex())+'"></div>'),
        container=container||'body';
    return overlay.appendTo(container).fadeIn().on({
        'touchmove':function(e){e.preventDefault();e.stopPropagation();}
    });
};



/**
 *滚动条事件
 */
var niceScrollOpt={autohidemode: true, bouncescroll: true, zindex: getTopIndex(), cursorwidth: 10, railpadding: {top: 0, right: 0, left: 0, bottom: 0}};
$.fn.extend({
    niceScroll: function(warpper,opt) {

        if(appSys!='web'||isMac||isTouch){
            var style={
                'overflow': 'auto'
            };
            if(!isIpad){
                style['-webkit-overflow-scrolling']='touch';
            };
            $(this).css(style);

        }else if(!window.NiceScroll){
            $this=$(this);
            getScript(staticPath+'/js/jquery.nicescroll.min.js',function(){
                $this.niceScroll(warpper,opt);
            })
        }else{
            $(this).niceScroll(warpper,opt);
        };
    },
    getNiceScroll: function() {
        return ({
            hide: $.noop,
            show: $.noop,
            remove: $.noop,
            resize: $.noop
        });
    }
});

;function niceScrolls(el,warpper){
    var el=el?el:$('div.boxscroll,body.boxscroll');
    niceScrollOpt.zindex=getTopIndex();
    el.niceScroll(warpper,niceScrollOpt);
};

/**
 *更新滚动条
 */

;function resizeScroll(el){
    var el=el?el:$('div.boxscroll,body.boxscroll');
    el.getNiceScroll().resize();
};


/**
 *裁切图片
 */
;function cropper(src,cropOpts,opts){

    var options=$.extend(true,{
        aspectRatio:180/180,
        viewMode:1,
        modal:false,
        background:false,
        dragMode:'move',
        resizable:true,
        movable:true,
        guides:false,
        rotatable:false,
        cropBoxResizable:false,
        cropBoxMovable:false,
        toggleDragModeOnDblclick:false,
        autoCropArea:1
    },opts);

    var wh=cropOpts.cropperSize||'180/180',
        w=wh.split('/')[0]*2,
        h=wh.split('/')[1]*2;
    options.aspectRatio=w/h;

    var overlay=setOverlay(1),
        cropperDiv=$('<div style="position:fixed;z-index:'+getTopIndex()+';width:100%;height:100%;left:0;top:0;"><p style="position:absolute;bottom:50%;margin-bottom:-200px;z-index:99;width:320px;margin-left:-160px;left:50%;"><a href="javascript:;" role="cancel" style="color:#ccc;padding-left:10px;font-size:16px;">'+SiteText.cancelText+'</a><a href="javascript:;" role="confirm" style="position:absolute;right:10px;color:#ccc;font-size:16px;">'+SiteText.choose+'</a></p><div style="width:320px;height:320px;top:50%;left:50%;margin-top:-160px;margin-left:-160px;position:absolute;" role="cropper"></div></div>').appendTo('body'),
        img=$('<img src="'+src+'?imageMogr2/thumbnail/'+w+'x/quality/100!" style="display:none;" />').appendTo($('div[role="cropper"]',cropperDiv)),
        closeCrop=function(){
            img.cropper('destroy');
            overlay.fadeOut(function(){
                overlay.remove();
            });
            cropperDiv.fadeOut(function(){
                cropperDiv.remove();
            });
        };
    //cropperDiv.toCenter();
    //alert(cropperDiv.length);
    cropperDiv.delegate('a[role]',click,function(){
        switch($(this).attr('role')){
            case 'cancel':
                fireHandler(cropOpts.onCancel);
                closeCrop();
                break;
            case 'confirm':
                var imageMogr=getCropperData(img);

                rpcJSON('/uploadapi/imageProcessing/',{pic:src.split('/')[src.split('/').length-1],news:false,imageMogr:imageMogr},function(backData){
                    if($.isFunction(cropOpts.onConfirm)){

                        cropOpts.onConfirm({
                            file:backData.file,
                            src:src+'?imageMogr2'+imageMogr
                        });
                    };
                    closeCrop();
                });

                break;
        };
    });

    img.data({
        cropWidth:w,
        cropHeight:h
    });

    if(!$.fn.cropper){
        getScripts([staticPath+'/plugins/cropper/cropper.css',staticPath+'/plugins/cropper/cropper.js'],function(){
            //img.prev().remove();
            img.cropper(options);
        });
    }else{
        //img.prev().remove();
        img.cropper(options);

    };
};

;function getCropperData(img){
    var cropWidth=img.data('cropWidth');
    var cropHeight=img.data('cropHeight');
    var canvasData = img.cropper('getCanvasData');
    var cropBoxData=img.cropper('getCropBoxData');

    var scale=canvasData.naturalWidth/canvasData.width,
        vscale=cropWidth/cropBoxData.width,
        left=Math.max(0,Math.floor((cropBoxData.left-canvasData.left)*vscale)),
        top=Math.max(0,Math.floor((cropBoxData.top-canvasData.top)*vscale));

    var src='/thumbnail/'+Math.floor(canvasData.width*vscale)+'x/crop/!'+cropWidth+'x'+cropHeight+'a'+left+'a'+top+'/quality/100';

    return src;
};

/**
 *树菜单
 */

;function setDynatree(el,opts,callback){
    var fn=function(){
        var tree=el.dynatree(opts);
        if($.isFunction(callback)){
            callback(tree);
        };
    };
    if($.fn.dynatree){
        fn();
    }else{
        var jss=[staticPath+'/plugins/dynatree/skin-vista/ui.dynatree.css',staticPath+'/plugins/dynatree/jquery.dynatree.js'];
        if(!$.widget){
            jss.unshift(staticPath+'/js/jquery-ui-1.9.2.custom.min.js');
        };
        getScripts(jss,fn);
    };
};

/**
 *字符串转换为数字
 */

;function stringToNumber(str){
    if(str&&!isNaN(str)){
        str=parseInt(str);
    };
    return str;
};



/*
 *转换英文符号为代码
 */

;function enToString(value){
    if(typeof value=='string'){
        //return value.replace(/\'/g,"&#8217;").replace(/\"/g,"&#8221;");
        return value.replace(/\'/g,"&#8217;").replace(/\‘/g,"&#8217;").replace(/\’/g,"&#8217;");
    }else{
        return value;
    }
};




/**
 *批量操作
 */
;function checkAll(el){
    var el=$(el);
    el.delegate(':checkbox','change',function(e){
        var name=$(this).attr('name'),
            btn=el.find(':checkbox[name="checkAll"]'),
            checkbox=el.find(':checkbox:not([name="checkAll"],[disabled="disabled"])');

        if(name=='checkAll'){
            if($(this).prop('checked')){
                checkbox.prop('checked','checked');
                el.find('[role="item"]').addClass('checked');
            }else{
                checkbox.prop('checked',false);
                el.find('[role="item"]').removeClass('checked');
            }
        }else{
            var check=true;
            checkbox.each(function(){
                if(!$(this).prop('checked')){
                    check=false;
                    return;
                }
            });
            if(check){
                btn.prop('checked','checked');
            }else{
                btn.prop('checked',false);
            };
            if($(this).prop('checked')){
                if($(this).parents('[role="item"]').length){
                    $(this).parents('[role="item"]').addClass('checked');
                }
            }else{
                if($(this).parents('[role="item"]').length){
                    $(this).parents('[role="item"]').removeClass('checked');
                }
            }
        };
        e.stopPropagation();
    });

};

/**
 *获取批量数据
 */

;function getAllCheck(el){
    var items=[],
        value=[];

    $(el).find('input[type="checkbox"]').each(function(){
        if($(this).prop('checked')){
            items.push($(this));
            value.push($(this).attr('value'));
        };
    });
    return {items:items,value:value};
};

/**
 *设置批量删除数据
 */

;function setDelAll(listEl,action,url){
    if(!(listEl.length&&action))return;

    //设置全选按钮
    ;checkAll(listEl);


    //删除数据
    var del=function(checks){
        Confirm(SiteText.removeSelected,function(){
            rpcJSON(action,{ids:checks.value},function(backData){
                Tips('success',SiteText.removeSuccess);
                checks.items.parents('[role="item"]').hideRemove();
            },'',url);
        });
    };

    //批量删除
    var delAll=function(){
        var checks=getAllCheck(listEl);
        if(!checks.value.length){
            Alert(SiteText.noSelected);
        }else{
            del(checks);
        }
    };

    //删除按钮
    listEl.delegate('a[role]',click,function(e){
        e.preventDefault();
        switch($(this).attr('role')){
            case 'delAllBtn':
                delAll();
                break;
            case 'deleteBtn':
                var checks=$(this).parents('[role="item"]:first').find('input[type="checkbox"]');
                del({
                    items:checks,
                    value:[checks.val()]
                });
                break;
        };
    });
};




/**
 *PHPRPC
 */

;function rpc(requestUrl,requestData,callBack,errorBack,dataType,url,action,target){


    url=url||(typeof phprpcURL=='string'?phprpcURL:'');
    action=action||(typeof rpcAction=='string'?rpcAction:'');

    if(!(requestUrl&&action&&url))return;

    process=true;
    //setMask();

    var loading=null,
        loadingEl=null,
        delayLoading=setTimeout(function(){
            loading=true;
            loadingEl=Tips('loading',SiteText.loading,100000000);
        },2000),
        hideLoading=function(){
            if(loading){
                loadingEl.close();
            }else{
                clearTimeout(delayLoading);
            }
        };

    var data={
        requestData:requestData,
        requestUrl:requestUrl
    };
    var fn=function(){

        if(target){
            $(target).addClass('disabled');
        };

        if(appSys=='web'){
            var client = new PHPRPC_Client(url, [action]);
            //client.setEncryptMode(2);
            client[action]($.toJSON(data), function (result, args, output, warning) {
                request(result, args, output, warning);
            }, true);
        }else{
            data.action=action;
            data.url=url;

            webViewCall('rpc',data,function(output){
                request('','',output,'');
            });
        };
    };

    var request=function(result, args, output, warning){

        process=false;
        //setMask(0);

        hideLoading();

        if(target){
            $(target).removeClass('disabled');
        };

        var outData=output,
            success=function(){
                if($.isFunction(callBack)){
                    callBack(outData);
                };
            },
            error=function(msg){
                if($.isFunction(errorBack)){
                    errorBack(msg,result, args, output, warning);
                }else{
                    var message=msg||SiteText.operationFailure;
                    Tips('error',message,2000);
                };
            };
        if(output!=undefined){
            if(dataType=='json'){
                try{
                    outData=typeof output=='object'?outData:$.parseJSON(output);
                    if(outData.code=='0'){
                        outData=outData.data;
                        success();
                    }else{
                        error(outData.errorMessage);
                    };
                }
                catch (e){
                    error();
                };
            }else{
                success();
            };
        }else{
            error();
        }

    };

    if(appSys=='web'){
        if(typeof PHPRPC_Client =='undefined'){
            getScript(rpcJS,fn);
        }else{
            fn();
        }
    }else{
        fn();
    };
};

;function rpcJSON(requestUrl,requestData,callBack,errorBack,url,action,target){
    rpc(requestUrl,requestData,callBack,errorBack,'json',url,action,target);
};

$.fn.rpcJSON=function(requestUrl,requestData,callBack,errorBack,url,action){
    return this.each(function(){
        rpc(requestUrl,requestData,callBack,errorBack,'json',url,action,$(this));
    });
};

/**
 *元素居中效果
 */

;$.fn.toCenter = function(inner){
    return this.each(function(i) {
        var parent=inner?$(this).parent():$(window),
            position=$(this).css('position'),
            pos={
                top:( parent.height() - 　$(this).height() ) / 2 + (position=='fixed'?0:parent.scrollTop()) + 'px',
                left:( parent.width() - 　$(this).width() ) / 2 + parent.scrollLeft() + 'px'
            };
        if(!position){
            pos.position='absolute';
        };
        $(this).css(pos);
    });
};


/**
 *元素激活效果
 */

;$.fn.toActive = function(callBack){
    return this.each(function(i) {
        if(!$(this).hasClass('active')){
            $(this).addClass('active').siblings().removeClass('active');
            if($.isFunction(callBack)){
                callBack($(this));
            };
        }
    });
};


/**
 *删除数组中的值
 */

function removeArray(arr,val){
    if(!(arr&&val))return;

    var narr=[];

    if(typeof val=='object'){
        val=$.toJSON(val);
    };
    $.each(arr,function(i,item){
        var nval=typeof item == 'object'?$.toJSON(item):item;
        if(nval!=val){
            narr.push(item);
        }
    });
    return narr;
};


/**
 *获取网页尺寸
 */

;function getPageSize() {


    var xScroll, yScroll;

    if (window.innerHeight && window.scrollMaxY) {

        xScroll = window.innerWidth + window.scrollMaxX;

        yScroll = window.innerHeight + window.scrollMaxY;

    } else {

        if (document.body.scrollHeight > document.body.offsetHeight) {

            xScroll = document.body.scrollWidth;

            yScroll = document.body.scrollHeight;

        } else {

            xScroll = document.body.offsetWidth;

            yScroll = document.body.offsetHeight;

        }

    };

    var windowWidth, windowHeight;

    if (self.innerHeight) {

        if (document.documentElement.clientWidth) {

            windowWidth = document.documentElement.clientWidth;

        } else {

            windowWidth = self.innerWidth;

        };

        windowHeight = self.innerHeight;

    } else {

        if (document.documentElement && document.documentElement.clientHeight) {

            windowWidth = document.documentElement.clientWidth;

            windowHeight = document.documentElement.clientHeight;

        } else {

            if (document.body) {

                windowWidth = document.body.clientWidth;

                windowHeight = document.body.clientHeight;

            }

        }

    };

    if (yScroll < windowHeight) {

        pageHeight = windowHeight;

    } else {

        pageHeight = yScroll;

    };

    if (xScroll < windowWidth) {

        pageWidth = xScroll;

    } else {

        pageWidth = windowWidth;

    };

    arrayPageSize = new Array(pageWidth, pageHeight, windowWidth, windowHeight);

    return ({
        pageWidth:pageWidth,
        pageHeight:pageHeight,
        windowWidth:windowWidth,
        windowHeight:windowHeight
    });

};

/**
 *表格高亮
 */

;highlight = function(el){

    $(el).delegate('tbody tr',{
        mouseenter:function(){
            $(this).addClass('over');
        },
        mouseleave:function(){
            $(this).removeClass('over');
        }
    });
};

/**
 *简单选项卡
 */

;$.fn.litabs = function(opts){
    if(typeof opts=='function'){
        opts={callBack:opts};
    };
    var options=$.extend({},{event:click,index:0,ajax:false,callBack:false},opts);

    return this.each(function(i) {
        $(this).data('i',i).on(options['event'],function(){
            $(this).addClass('active').siblings().removeClass('active');
            if(options.callBack){
                options.callBack($(this));
            }else{
                if(options.ajax){
                    var data=$.extend({},options.ajax['data'],$(this).data('data'));
                    $(options.ajax['target']).load(options.ajax['url'],data);
                }else{
                    var target=$(this).data('target');
                    $(target).addClass('active').siblings().removeClass('active');
                }
            }
        });
        if(i==options.index){
            $(this).trigger(options['event']);
        }
    });

};


/**
 *jQuery操作cookie
 */

;$.cookie=function(name, value, options,wc) {
    if (typeof value != 'undefined') {


        //app cookie操作
        if(appSys!='web'&&window.webViewCall){
            if(wc!=1){
                webViewCall('cookie',{
                    name:name,
                    value:value,
                    options:options
                });
            };
            xzApp.cookie[name]=value;
        }else{
            if(typeof options == 'number'){
                options={expires:options};
            };
            options = options || {};
            if (value === null) {
                value = '';
                options = $.extend({}, options);
                options.expires = -1;
            };
            var expires = '';
            if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
                var date;
                if (typeof options.expires == 'number') {
                    date = new Date();
                    date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
                } else {
                    date = options.expires;
                };
                expires = '; expires=' + date.toUTCString();
            };
            var path = options.path ? '; path=' + (options.path) : '';
            var domain = options.domain ? '; domain=' + (options.domain) : '';
            var secure = options.secure ? '; secure' : '';
            document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
        };
    } else {
        var cookieValue = null;
        if(appSys!='web'){
            if(xzApp.cookie[name]){
                cookieValue=xzApp.cookie[name];
            };
        }else{

            if (document.cookie && document.cookie != '') {
                var cookies = document.cookie.split(';');
                for (var i = 0; i < cookies.length; i++) {
                    var cookie = jQuery.trim(cookies[i]);
                    if (cookie.substring(0, name.length + 1) == (name + '=')) {
                        cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                        break;
                    };
                };
            };
        };
        return cookieValue;
    };
};

/**
 *获取loading代码
 */

;function getContentLoading(align,msg,style){
    var message=msg||'',
        text_align=align||'center';
    return '<div style="text-align:'+text_align+'; '+(style?style:'padding:10px 0')+';" class="loading"><span class="contentLoading">'+message+'</span></div>';
};


/**
 *获取空数据
 */

;
function getEmptyContent(str) {
    return '<div class="noData empty"><i class="xzicon-nodata noDataIcon"></i><p class="noDataTitle">'+(str?str:SiteText.noData)+'</p></div>';
};

/**
 *无限加载
 */
;
function scrollLoad(opts) {
    var options = $.extend(true, {
            loadUrl: '', //ajax地址
            data: {}, //ajax参数
            contentContainer: '', //内容存放容器
            contentLoading: '', //loading 容器
            heightOffset: 20, //底部相差距离
            target: window, //滚动目标
            count: 15, //获取条数
            autoLoad:false,
            load:'',
            onLoad: $.noop//加载完成执行
        }, opts),
        loadUrl = options.loadUrl,
        data = options.data,
        target = options.target,
        contentContainer = options.contentContainer,
        contentLoading = options.contentLoading,
        heightOffset = options.heightOffset,
        count = options.count,
        onLoad = options.onLoad,
        container = target == window ? 'body' : $(target).children(':first'),
        lastId = 0,
        loading = false,
        checkLoad=function(auto){

            var containerHeight =  $(container).height(),
                canLoad = auto=='load'?true:auto=='full'?$(target).height()>containerHeight:$(target).scrollTop() + heightOffset >= containerHeight - $(target).height(),
                lastContent = contentContainer.children().last(),
                lastone = lastContent.length?lastContent.data('lastone'):($(target).data('lastone')||false);
            if (canLoad && !loading && !lastone) {
                lastId = lastContent.length?lastContent.data('id'):'';

                if (lastId||auto) {
                    loadContent();
                }
            }
        },
        autoLoad=function(){
            checkLoad('load');
        },
        loadComplete=function(){
            loading = false;
            if (contentLoading.length) {
                contentLoading.fadeOut();
            };
        },
        loadContent =function() {

            loading = true;
            if (contentLoading.length) {
                contentLoading.fadeIn();
            }
            ;
            data['lastId'] = lastId;
            data['count'] = count;

            var loadSuccess=function(backData){
                loadComplete();
                onLoad(backData);
                //自动加载
                if(options.autoLoad){
                    checkLoad('full');
                };
            };

            if($.isFunction(options.load)){
                options.load(data,function(backData){
                    loadSuccess(backData);
                },function(){
                    loadComplete();
                });
            }else{
                $.post(loadUrl, data, function(backData) {
                    loadSuccess(backData);
                });
            };

        };

    if (!contentContainer)
        return;
    if (contentLoading.length) {
        contentLoading.hide();
        if (!contentLoading.html().length) {
            contentLoading.html(getContentLoading());
        }
    };

    //自动加载
    if(options.autoLoad){
        autoLoad();
    };

    $(target).scroll(function() {
        checkLoad();
    });
    $(target).data('reload',function(){
        checkLoad('load');
    });
}
;

$.fn.scrollLoad=function(opts){
    return this.each(function(){
        if($(this).data('scrollLoad'))return;
        var target=$(this),
            contentContainer=target.children(':first'),
            options=$.extend(true,{
                target:target,
                contentContainer:contentContainer,
                contentLoading:opts.contentLoading||$('<div />').appendTo(target)
            },opts);

        scrollLoad(options);
        target.data('scrollLoad',1);

    });
};

/**
 *自动设置下拉菜单
 */
;
function setPhoneDropMenu(el, cls, onSelect) {
    var el = el || 'body',
        cls = cls || 'phone_active';
    $('ul.phoneDropMenu:not(.setPhoneDropMenu)', el).each(function() {
        var $this = $(this),
            title = ($this.attr('title')||$this.data('title')) || SiteText.screening,
            zIndex = $this.parent().css('zIndex');
        resize = function() {
            if (isPhoneState) {
                if (!$('li.topMenu', $this).length) {
                    var topMenu = $('li:first', $this).clone();
                    topMenu.find('a').attr('href', 'javascript:;').html(title);
                    topMenu.removeClass('active').addClass('topMenu').prependTo($this);

                }
            } else {
                $('li.topMenu', $this).remove();
            }
        };
        $this.removeAttr('title').addClass('setPhoneDropMenu').delegate('li', click, function(e) {

            if (isPhoneState) {

                if ($(this).hasClass('topMenu')) {
                    if ($this.hasClass(cls)) {
                        $this.removeClass(cls).parent().css('zIndex', zIndex);

                    } else {
                        $this.addClass(cls).parent().css('zIndex', 111111);
                        e.preventDefault();
                    };

                } else if (onSelect) {
                    e.preventDefault();
                    $this.removeClass(cls).parent().css('zIndex', zIndex);
                }
                ;
                e.stopPropagation();
            }
            ;
            if (!$(this).hasClass('topMenu')) {
                $(this).toActive();
            }
            ;

            if ($.isFunction(onSelect) && !$(this).hasClass('topMenu')) {

                onSelect($(this));
            }
            ;


        });

        $(document).on(click, function() {
            $this.removeClass(cls).parent().css('zIndex', zIndex);
        });
        $(window).resize(resize);
        resize();
    });
}
;

/*
 *添加jQuery插件
 */

;
(function($) {
    $.fn.extend({
        //显示提示

        showTips: function(flag) {

            //if($(this).data('delayShow'))return;

            var offset = 10,
                $this = $(this),
                title = $this.attr('title') || $this.data('tipsTitle'),
                opt = $this.data('tips') || '1_b',
                tips = opt.split('_'),
                widthHtml = tips[2] ? 'width:' + tips[2] + 'px;' : '',
                tipsEl;


            if (!title)
                return;

            if (!$this.data('tipsTitle')) {
                $this.data('tipsTitle', title);
                $this.removeAttr('title');
            }
            ;

            if ($(this).data('tipsEl')) {
                tipsEl = $(this).data('tipsEl');
            } else {
                var tipsHtml = '<div class="tips_' + tips[0] + '"style="top:-10000px;z-index:11111113; left:50px;' + widthHtml + '">' +
                    '<span class="arrow_' + tips[0] + '_' + tips[1] + ' tipsArrow"><i></i></span>' +
                    '<div class="tipsPad">' + title + '</div>' +
                    '</div>';
                tipsEl = $(tipsHtml).appendTo('body');
                $(this).data('tipsEl', tipsEl).attr('showtipsel', 1);
            }
            ;

            var $width = $this.outerWidth(),
                $height = $this.outerHeight(),
                $left = $this.offset().left,
                $top = $this.offset().top,
                width = tipsEl.outerWidth(),
                height = tipsEl.outerHeight(),
                left,
                top;

            switch (tips[1]) {
                case 'b':
                    left = $left + ($width - width) / 2;
                    top = $top - offset - height;
                    break;

                case 't':
                    left = $left + ($width - width) / 2;
                    top = $top + $height + offset;
                    break;

                case 'l':
                    left = $left + $width + offset;
                    top = $top + ($height - height) / 2;
                    break;

                case 'r':
                    left = $left - offset - width;
                    top = $top + ($height - height) / 2;
                    break;
            }
            ;
            tipsEl.show().css({
                left: left,
                top: top
            });
        },
        //隐藏提示
        hideTips: function(num) {
            return (this.each(function() {

                if ($(this).data('tipsEl')) {
                    var $this = $(this);
                    $this.data('tipsEl').remove();
                    $this.removeData('tipsEl');

                };
            }));

        },
        //设置表单效果
        fancyInput: function() {
            return (this.each(function() {
                $(this).focus(function() {
                    $(this).focusInput();
                }).blur(function() {
                    $(this).removeClass('focus');
                });

            }));
        },
        //选中状态
        focusInput: function() {
            return (this.each(function() {
                $(this).addClass('focus').removeClass('success error').hideTips();
            }));
        },
        //错误状态
        errorInput: function(msg) {
            return (this.each(function() {
                $(this).addClass('error').removeClass('success');
                if (msg) {
                    $(this).data({tipsTitle: msg,
                        tips: '2_b'}).showTips();
                }
                ;
                $(window).scrollTop($(this).offset().top - 20);
            }));
        },
        //正确状态
        successInput: function() {
            return (this.each(function() {
                $(this).addClass('success').removeClass('error').hideTips();
            }));
        },
        //自动增高
        autoHeightTextarea:function(){
            return this.each(function(){
                var $this=$(this),
                    o=this;
                var autoHeight=function(){
                    o.style.height='auto';
                    o.scrollTop=0;
                    o.style.height = o.scrollHeight +2+"px";
                };
                $this.css({
                    overflow:'hidden',
                    resize:'none',
                    minHeight:Math.max($this.height(),50)+'px'
                }).on({
                    keydown:autoHeight,
                    keyup:autoHeight,
                    afterpaste:autoHeight,
                    focus:autoHeight
                });
                autoHeight();
            });
        },
        //在光标中插入内容
        insertAtCaret: function(myValue){
            var $t=$(this)[0];
            if (document.selection) {
                this.focus();
                sel = document.selection.createRange();
                sel.text = myValue;
                this.focus();
            }
            else
            if ($t.selectionStart || $t.selectionStart == '0') {
                var startPos = $t.selectionStart;
                var endPos = $t.selectionEnd;
                var scrollTop = $t.scrollTop;
                $t.value = $t.value.substring(0, startPos) + myValue + $t.value.substring(endPos, $t.value.length);
                this.focus();
                $t.selectionStart = startPos + myValue.length;
                $t.selectionEnd = startPos + myValue.length;
                $t.scrollTop = scrollTop;
            }
            else {
                this.value += myValue;
                this.focus();
            }
        },
        //滚动到顶部时固定
        scrollTopFixed:function(){
            return this.each(function(){
                $(this).data('static',1);
                var spaceId='space_'+getNowRandom(),
                    fixedEl=$(this),
                    fPosition=fixedEl.css('position'),
                    scrolls=function(){
                        if(!fixedEl.data('static')){
                            $(window).off('scroll',scrolls);
                            if($('#'+spaceId).length){
                                $('#'+spaceId).remove();
                            };
                            return;
                        };
                        var scrollTop=$(window).scrollTop();

                        if(!fixedEl.data('fixed')){
                            var ftop=fixedEl.offset().top;

                            if(ftop<=scrollTop){

                                fixedEl.data({
                                    ftop:ftop,
                                    top:fixedEl.css('top'),
                                    zIndex:fixedEl.css('z-index'),
                                    fixed:true,
                                    width:fixedEl[0].style['width']
                                }).css({
                                    position:'fixed',
                                    width:fixedEl.width(),
                                    top:-parseInt(fixedEl.css('margin-top')),
                                    zIndex:99
                                });

                                $('<div id="'+spaceId+'"></div>').css({
                                    position:fPosition,
                                    height:fixedEl.outerHeight()+parseInt(fixedEl.css('margin-top'))+parseInt(fixedEl.css('margin-bottom'))
                                }).insertAfter(fixedEl);
                            }
                        }else{

                            if(scrollTop<fixedEl.data('ftop')&&fixedEl.data('fixed')){
                                fixedEl.css({
                                    position:fPosition,
                                    top:fixedEl.data('top'),
                                    zIndex:fixedEl.data('zIndex'),
                                    width:fixedEl.data('width')
                                }).removeData('fixed');
                                fixedEl.next().remove();
                            };
                        };
                    };
                scrolls();
                $(window).on('scroll',scrolls);
                if(isTouch){
                    $(window).on('touchmove',scrolls);
                }
            });
        }
    });
})(jQuery);

/**
 *清除元素内所有弹出提示
 */
;
function clearTips(el) {
    $('*[showtipsel]', el).hideTips();
};


/**
 *图片加载错误
 */
;function errorImg(img){
    $(img).setThumbImageView();
};

/**
 *获取限定宽度的图片
 */
;function getWidthImageView(file,w,quality){
    if(file!=errorImgSrc&&file.indexOf('/')<0){
        file=filePath+file;
    };
    if(file.indexOf('ico')>-1||file.indexOf('icon')>-1){
        return file;
    };
    file=file.split('?')[0];
    quality=quality||thumbnailQuality;
    file+='?imageMogr2/thumbnail/'+(Math.floor(w*devicePixelRatio))+'x9999%3E/quality/'+quality+'!';
    return file;
};

/**
 *图片根据容器宽度获取大小
 */
;$.fn.setWidthImageView=function(){
    return this.each(function(){
        var $this=$(this),
            src=$this.data('src')?$this.data('src'):errorImgSrc,
            parent=$this.parent(),
            w=parent.width(),
            nSrc=getWidthImageView(src,w);
        if(!w){
            var resize=function(){
                if(!$this.data('ready')){
                    $this.setWidthImageView();
                }else{
                    $(window).off('resize',resize);
                };
            };
            $(window).resize(resize);
            return;
        };


        $this.data('ready',1);
        $this.show().css({'max-height':'100%','max-width':'100%'}).attr('src',nSrc);

        this.onerror=function(){
            var eSrc=getWidthImageView(errorImgSrc,w);
            $this.attr('src',eSrc);
        };
    });
};

/**
 *获取限定宽高的图片
 */
;function getThumbImageView(file,w,h,quality){

    if(!file){
        file=errorImgSrc;
    };
    if(file!=errorImgSrc&&file.indexOf('//')<0){
        file=filePath+file;
    };
    if(file.indexOf('ico')>-1||file.indexOf('icon')>-1){
        return file;
    };
    file=file.split('?')[0];
    var _w=Math.floor(w*devicePixelRatio),
        _h=Math.floor(h*devicePixelRatio);
    if(_w>maxImgSize){
        _h=Math.floor(maxImgSize/w*_h);
        _w=maxImgSize;
    };
    if(_h>maxImgSize){
        _w=Math.floor(maxImgSize/_h*_w);
        _h=maxImgSize;
    };
    quality=quality||thumbnailQuality;
    if(_w<1||_h<1){
        return file;
    };
    file+='?imageMogr2/thumbnail/'+(_w)+'x'+(_h)+'%3E/quality/'+quality+'!';
    return file;
};

/**
 *获取限定宽高裁切后的图片
 */
;function getCropImageView(file,w,h,quality){

    if(!file){
        file=errorImgSrc;
    };
    if(file!=errorImgSrc&&file.indexOf('//')<0){
        file=filePath+file;
    };
    if(file.indexOf('ico')>-1||file.indexOf('icon')>-1){
        return file;
    };
    file=file.split('?')[0];
    var _w=Math.floor(w*devicePixelRatio),
        _h=Math.floor(h*devicePixelRatio);
    if(_w>maxImgSize){
        _h=Math.floor(maxImgSize/w*_h);
        _w=maxImgSize;
    };
    if(_h>maxImgSize){
        _w=Math.floor(maxImgSize/_h*_w);
        _h=maxImgSize;
    };
    quality=quality||thumbnailQuality;
    if(_w<1||_h<1){
        return file;
    };
    file+='?imageMogr2/thumbnail/!'+(_w)+'x'+(_h)+'r/gravity/Center/crop/'+(_w)+'x'+(_h)+'/quality/'+quality+'!';
    return file;
};

/**
 *图片根据容器宽高获取只能大小
 */
;$.fn.setThumbImageView=function(opts){
    return this.each(function(){
        if(typeof opts=='object'){
            var autoHeight=opts.autoHeight;
            var parent=opts.parent;
        }else{
            var autoHeight=opts;
            var parent=null;
        };
        var $this=$(this),
            AH=Math.floor(maxImgSize/devicePixelRatio),
            src=$this.data('src')?$this.data('src'):$this.data('video')?videoCodeImgSrc:errorImgSrc,
            parent=parent||$this.parents(':not(a,span):first'),
            autoHeight=autoHeight?autoHeight:$this.data('autoheight')?$this.data('autoheight'):'',
            w=$this.data('iw')?$this.data('iw'):parent.width(),
            h=autoHeight?AH:parent.height(),
            crop=parent.data('crop')||$this.data('crop'),
            ratio=parent.data('ratio')||$this.data('ratio');
        if(!w){
            var resize=function(){
                if(!$this.data('ready')){
                    $this.setThumbImageView();
                }else{
                    $(window).off('resize',resize);
                };
            };
            $(window).resize(resize);
            return;
        };
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

        if(isPhoneState&&$(this).data('phonesrc')){
            src=$(this).data('phonesrc');
        };

        var nSrc=crop?getCropImageView(src,w,h):getThumbImageView(src,w,h);

        $this.data('ready',1);
        $this.show().css({'max-height':'100%','max-width':'100%'}).attr('src',nSrc);

        this.onerror=function(){
            if($this.attr('src').indexOf('error')<0){
                var eSrc=crop?getCropImageView(errorImgSrc,w,h):getThumbImageView(errorImgSrc,w,h);
                $this.attr('src',eSrc);
            }else{
                $this.hide();
            };
        };
    });
};

/**
 *查看文件
 */

;function fileView(value){
    var	type=value.indexOf('.mp4')>=0?'video':'image';
    if(value.indexOf('://')<0){
        value=filePath+value;
    };
    var items={
        type:type,
        src:value,
        thumb:type=='video'?value.replace('.mp4','.png'):''
    };
    fileViews([items]);

};


/**
 *查看图片和视频
 items为文件数组
 [{
	 src:'',//文件地址
	 type:'image',类别，image为图片，video为视频
	 thumb:'',//类别为视频的时候的略缩图
	 }]
 index为默认索引，不传为0。
 */

;function fileViews(items,index){
    if(!items.length){
        return;
    };
    if(this!=top&&$.isFunction(top.fileViews)){
        top.fileViews(items,index);
        return;
    };

    $.each(items,function(i,item){
        if(item.type=='image'){
            item.src=getThumbImageView(item.src,$(window).width(),$(window).height());
        };
    });

    index=index||0;
    if(appSys=='web'){
        var itemsHtml='';
        $.each(items,function(i,item){
            if(item.type=='image'){
                itemsHtml+='<div class="swiper-slide imagePlaceholder" type="image">\
									<img data-src="'+item.src+'" class="swiper-lazy">\
								</div>';
            }else if(item.type=='video'){
                itemsHtml+='<div id="v'+getNowRandom()+'" class="swiper-slide" type="video" data-src="'+item.src+'" data-poster="'+item.thumb+'">\
								</div>';
            }
        });
        var fileViewsSwiper=$('<div class="swiper-container" id="fileViewsSwiper" style="opacity:0;z-index:'+getTopIndex()+'">\
									<div class="swiper-wrapper">'+
            itemsHtml+
            '</div>\
            <div id="fileViewsSwiper-close"><span class="xzicon-close"></span></div>\
            <div id="fileViewsSwiper-resources"><span class="xzicon-visibility"></span></div>\
            <div class="swiper-pagination"></div>\
            <div class="swiper-button-next" id="swiper-button-next"><i class="xzicon-arrow-r"></i></div>\
            <div class="swiper-button-prev" id="swiper-button-prev"><i class="xzicon-arrow-l"></i></div>'+
            '</div>').appendTo('body');

        getScript(staticPath+'/plugins/swiper/fileViewsSwiper.css');

        var mySwiper,
            paginationEl=$('div.swiper-pagination',fileViewsSwiper),
            nextBtn=$('#swiper-button-next'),
            prevBtn=$('#swiper-button-prev'),
            setPagination=function(){
                paginationEl.text((index+1)+'/'+items.length);
                if(index==0){
                    prevBtn.hide();
                }else{
                    prevBtn.show();
                };
                if(index==items.length-1){
                    nextBtn.hide();
                }else{
                    nextBtn.show();
                };
            },
            resetResources=function(swiper){
                if(!mySwiper){
                    mySwiper=swiper;
                    fileViewsSwiper.animate({opacity:1});
                    nextBtn.on(click,function(){
                        if(IEV>0&&IEV<9){
                            mySwiper.swipeNext();
                        }else{
                            mySwiper.slideNext();
                        };
                    });
                    prevBtn.on(click,function(){
                        if(IEV>0&&IEV<9){
                            mySwiper.swipePrev();
                        }else{
                            mySwiper.slidePrev();
                        };
                    });
                };
                index=mySwiper.activeIndex;
                setPagination();
                $('div.swiper-slide[type="video"]:not(.swiper-slide-active)',fileViewsSwiper).empty().removeData('videoPlayer');
                if($('div.swiper-slide-active[type="image"]',fileViewsSwiper).length){
                    var img=$('div.swiper-slide-active img',fileViewsSwiper);
                    if(!img.attr('src')){
                        img.loadImg(function(){
                            img.show();
                            $('div.swiper-slide-active').removeClass('imagePlaceholder');
                        }).on('error',function(){
                            errorImg($(this));
                        }).attr('src',img.data('src'));
                    };
                    if(!img.data('resources')){
                        $('#fileViewsSwiper-resources').show();
                    }else{
                        $('#fileViewsSwiper-resources').hide();
                    };
                }else{
                    $('#fileViewsSwiper-resources').hide();
                    if(IEV>0&&IEV<10&&!$('div.swiper-slide-active').data('ready')){
                        $('div.swiper-slide-active').on('mousedown',function(e){
                            e.stopPropagation();
                            e.preventDefault();
                            return false;
                        });
                    };
                    $('div.swiper-slide-active').data('ready',1).video_player();
                };
            };
        if(IEV>0&&IEV<9){
            lazySwiper('#fileViewsSwiper',{initialSlide:index,
                pagination:null,
                onSwiperCreated:resetResources,
                onSlideChangeEnd:resetResources});
        }else{
            lazySwiper('#fileViewsSwiper',{initialSlide:index,
                pagination:null,
                onInit:resetResources,
                onSlideChangeEnd:resetResources});
        }

        fileViewsSwiper.delegate('img',click,function(e){
            $('#fileViewsSwiper-close').trigger(click);
        });

        $('#fileViewsSwiper-close').on(click,function(e){
            fileViewsSwiper.fadeOut(function(){
                fileViewsSwiper.remove();
            });
            e.stopPropagation();
        });
        $('#fileViewsSwiper-resources').on(click,function(e){
            var img=$('div.swiper-slide-active img',fileViewsSwiper),
                src=img.attr('src'),
                nImg=$('<img />');
            src=src.split('?')[0]+'?imageMogr2/quality/100!';

            nImg.loadImg(function(){
                img.attr('src',src).data('resources',1);
            }).attr('src',src);
            $('#fileViewsSwiper-resources').hide();
        });


    }else{
        webViewCall('fileViews',{index:index,items:items});
    };

};


function lazySwiper(el,opts){

    var //el=$(el),
        start=function(){
            var options=$.extend(true,{
                pagination: '.swiper-pagination',
                paginationClickable: true

            },opts)
            var swiper = new Swiper(el, options);

        };
    if(typeof Swiper=='undefined'){
        $(el).hide();
        var scss=staticPath+'/plugins/swiper/swiper-3.3.1.min.css',
            sjs=staticPath+'/plugins/swiper/swiper-3.3.1.jquery.min.js';
        //staticPath+'/plugins/swiper/swiper-3.3.1.min.css',staticPath+'/plugins/swiper/swiper-3.3.1.jquery.min.js'
        if(IEV>0&&IEV<9){
            scss=staticPath+'/plugins/swiper/idangerous.swiper.css';
            sjs=staticPath+'/plugins/swiper/idangerous.swiper.min.js';
        };
        getScripts([scss,sjs],function(){
            $(el).show();
            start();
        });
    }else{
        start();
    };

};

/**
 *页面滚动到
 */

;function pageScrollTo(num){
    var container=$('#pageScrollWrapper').length?$('#pageScrollWrapper'):$(window);
    container.scrollTop(num);
};

/**
 *自动滚屏
 */

$.fn.scrollToBottom=function(){
    return this.each(function(){
        var el=$(this);
        el.scrollTop(el.children().outerHeight()-el.height());
    });
};

/**
 *水平鼠标滚动
 */

$.fn.hScrollWrapper=function(itemEl){
    return this.each(function(){
        if(isTouch)return;
        var el=$(this),
            $item=itemEl||el.children().children();
        el.on({
            mouseover:function(e){
                $(this).data('startX',e.pageX);
                var cw=0;
                $item.each(function(){
                    cw+=$(this).fullWidth();
                });
                el.children().width(cw);
            },
            mouseout:function(e){

            },
            mousemove:function(e){
                if(el.children().outerWidth()>el.width()){
                    var moveX=e.pageX,
                        startX=$(this).data('startX');
                    if(Math.abs(moveX-startX)<5){
                        return;
                    };

                    var scrollLeft=el.scrollLeft(),
                        ew=el.width(),
                        cw=el.children().outerWidth(),
                        moveNum=moveX-startX,
                        scale=1;

                    if(moveX>startX){
                        scale=(cw-ew)/(el.offset().left+ew-startX-10);
                    }else{
                        scale=scrollLeft/(startX-el.offset().left-10);
                    };
                    if(scale<1){
                        scale=1;
                    };
                    moveNum*=scale;

                    scrollLeft+=moveNum;
                    if(scrollLeft<0){
                        scrollLeft=0;
                    }else if(scrollLeft>cw-ew){
                        scrollLeft=cw-ew;
                    };
                    el.scrollLeft(scrollLeft);
                    $(this).data('startX',moveX);
                }
            }
        });
    });
};

/**
 *获取元素的margin值
 */

$.fn.getMarginNumber=function(){
    return Number($(this).css('marginLeft').replace('auto',0).replace('px',''))+Number($(this).css('marginRight').replace('auto',0).replace('px',''));
};

/**
 *获取元素的完整宽度，包括margin值
 */

$.fn.fullWidth=function(){
    return $(this).outerWidth()+$(this).getMarginNumber();
};

/**
 *设置数字分页
 */

$.fn.setPageList=function(opts){
    var options=$.extend(true,{
        onChange:$.noop,
        size:10,
        page:1,
        pageSize:5,
        pageSelect:[10,20,30,50,100],
        showPageSelect:true,
        showSize:true,
        showPrev:true,
        showNext:true,
        showFirst:true,
        showLast:true,
        showGo:true,
        units:'',
        goText:'GO'
    },opts);
    return this.each(function(){
        var $self=$(this),
            pageNum=options.page;

        var	html='<div class="tableCount">';

        if(options.showSize){
            html+=SiteText.totally+'<span role="count"></span>'+options.units+'&nbsp;&nbsp;';
        };

        if(options.showPageSelect){
            html+=SiteText.pageSize+
                '&nbsp;<span class="smallPopupBar">'+
                '<a class="smallPopupBtn" href="javascript:;"><span role="pageSizeNum"></span></a>'+
                '<div class="smallPopup" role="pageSize">';

            $.each(options.pageSelect,function(i,item){
                html+='<a class="popupItem '+(item==options.size?'active':'')+'" href="javascript:;">'+item+'</a>';
            });

            html+='</div>'+
                '</span>&nbsp;'+options.units;

        };

        html+='</div><div class="tablePaging" >'+
            '<span class="pagingBox">'+
            (options.showFirst?'<a href="javascript:;" role="first"><i class="xzicon-first"></i></a>':'')+
            (options.showPrev?'<a href="javascript:;" role="prev"><i class="xzicon-arrow-l"></i></a>':'')+
            '<span>'+
            '</span>'+
            (options.showNext?'<a href="javascript:;" role="next"><i class="xzicon-arrow-r"></i></a>':'')+
            (options.showLast?'<a href="javascript:;" role="last"><i class="xzicon-last"></i></a>':'')+
            '</span>';
        if(options.showGo){
            html+='<span class="pagingInput">'+
                SiteText.pageTo+'<input class="inputText" type="text" name="pageInput" role="number">'+SiteText.pageUnits
            '<a href="javascript:;" role="goPage">'+options.goText+'</a>'+
            '</span>';
        };
        html+='</div>';
        $self.html(html).data('pageSize',options.pageSize);
        var tablePaging=$self.find('div.tablePaging');
        //页码操作
        tablePaging.delegate('.pagingBox>a:not(.disable)',click,function(){
            pageNum=$self.data('page');
            switch($(this).attr('role')){

                case 'first':
                    pageNum=1;
                    options.onChange(pageNum);
                    break;

                case 'prev':
                    pageNum--;
                    options.onChange(pageNum);
                    break;

                case 'next':
                    pageNum++;
                    options.onChange(pageNum);
                    break;

                case 'last':
                    pageNum=$self.data('pageCount');
                    options.onChange(pageNum);
                    break;

            }
        }).delegate('.pagingBox>span>a',click,function(){
            if($(this).hasClass('active')){
                if(isPhoneState){
                    var items=[];
                    for(i=1;i<=$self.data('pageCount');i++){
                        items.push({value:i,name:SiteText.theText+i+SiteText.pageUnits});
                    };
                    var dialog=MenuDialog(items);
                    dialog.find('li').on(click,function(){
                        var value=$(this).find('a').data('value');
                        pageNum=Number(value);
                        options.onChange(pageNum);
                        dialog.close();
                    });
                }
            }else{
                var text=$(this).text();
                pageNum=Number(text);
                options.onChange(pageNum);
            };
        });
        $('div[role="pageSize"] a').on(click,function(){
            var size=$(this).text(),
                pageNum=1;
            $(this).toActive();
            options.onChange(pageNum,size);

        });

        $('a[role="goPage"]').on(click,function(){
            var val=Math.round($('input[name="pageInput"]').val());
            if(val&&val<=$self.data('pageCount')){
                pageNum=val;
                options.onChange(pageNum);
            }else{
                Tips('error',SiteText.rightPageNum);
            }
        });

        if(options.showPageSelect){
            $self.find('a.smallPopupBtn').setDropContainer();
        };
    });
};

/**
 *重设数字分页页码
 */

$.fn.resetPageList=function(page,size,count){
    return this.each(function(){
        var  $self=$(this),
            tablePaging=$self.find('div.tablePaging');

        if(!tablePaging.length){
            return;
        };

        $self.find('span[role="count"]').text(count);
        $self.find('span[role="pageSizeNum"]').text(size);

        var pageCount=Math.ceil(count / size),
            pageSize=$self.data('pageSize');

        $self.data({pageCount:pageCount,
            page:page
        });


        if(pageCount<2){
            $self.hide();
            return;
        };
        $self.show();

        var pageStart=1,
            pageEnd=pageCount;

        if(pageCount>pageSize){
            if(page<=Math.floor(pageSize/2)){
                pageEnd=pageSize;

            }else{
                if(page+Math.floor(pageSize/2)<pageCount){
                    pageEnd=page+Math.floor(pageSize/2);
                };
                pageStart=pageEnd-pageSize+1;
            };
        };


        $('.pagingBox>span',tablePaging).empty();
        for (var i=pageStart;i<=pageEnd;i++){
            $('.pagingBox>span',tablePaging).append('<a href="javascript:;" class="'+(i==page?'active':'')+'">'+ i +'</a>');
        };

        $('.pagingBox>a',tablePaging).removeClass('disable');
        if(page==1){
            $('.pagingBox>a[role="first"]',tablePaging).addClass('disable');
            $('.pagingBox>a[role="prev"]',tablePaging).addClass('disable');
        };
        if(page==pageCount){
            $('.pagingBox>a[role="next"]',tablePaging).addClass('disable');
            $('.pagingBox>a[role="last"]',tablePaging).addClass('disable');
        };
    });
};

/**
 *设置下拉框
 */


$.fn.setDropContainer=function(opts){
    return this.each(function(){

        DropDialog($(this));

    });
};




/**
 *设置搜索框
 */
$.fn.setSearchBox=function(opts){
    return this.each(function(){
        if(typeof opts=='function'){
            opts={
                onSubmit:opts
            };
        };
        var $self=$(this),
            options=$.extend(true,{
                cls:'search',
                placeholder:SiteText.searchKeyword,
                cancelText:SiteText.cancelText,
                onSubmit:$.noop
            },opts),
            keyword='';
        var html='<form class="clearfix '+options.cls+'" action="javascript:;">'+
            '<span class="'+options.cls+'Box">'+
            '<input type="search" name="keyword" class="inputText" placeholder="'+options.placeholder+'">'+
            '<a href="javascript:;" class="'+options.cls+'Clear xzicon-close" role="clear"></a>'+
            '<a href="javascript:;" class="'+options.cls+'Btn xzicon-search" role="submit"></a>'+
            '<a href="javascript:;" class="'+options.cls+'Close" role="closeSearch">'+SiteText.cancelText+'</a>'+
            '</span>'+
            '<a href="javascript:;" class="'+options.cls+'Phone xzicon-search" role="openSearch"></a>'+
            '<button type="submit" style="display:none;" />'+
            '</form>';

        $self.html(html);

        if($self.hasClass('specialHeadSearch')&&isPhoneState){
            $self.appendTo($('#topbarContainer'));
        };

        //搜索
        var clearBtn=$('a[role="clear"]',$self),
            input=$('input[name="keyword"]',$self);

        $self.delegate('a',click,function(e){
            switch($(this).attr('role')){
                case 'openSearch':
                    $self.addClass('active');
                    $('.'+options.cls+'',$self).addClass('open');
                    input.focus();
                    break;
                case 'closeSearch':
                    $('.'+options.cls+'',$self).removeClass('open');
                    $self.removeClass('active');
                    input.val('');
                    submitSearch();
                    checkKeyword();
                    break;
                case 'clear':
                    input.val('').focus();
                    checkKeyword();
                    break;
                case 'submit':
                    submitSearch();
                    break;
            };
            e.stopPropagation();
            e.preventDefault();
        });

        input.on('afterpaste',checkKeyword);

        //键盘回车事件
        $self.keyup(function(e){
            if($('input[name="keyword"]:focus',$self).length){
                checkKeyword();
                if(e.keyCode==13){
                    submitSearch();
                };
            }

        });


        //检测搜索框
        function checkKeyword(){
            if(input.val()!=''){
                clearBtn.show();
            }else{
                clearBtn.hide();
            };
        };

        //提交搜索
        function submitSearch(){
            if(keyword!=input.val()){
                keyword=input.val();
                options.onSubmit(input.val());
            };
        };

    });
};


/**
 *优酷视频
 */
;$.fn.youku_player=function(){
    return this.each(function(){
        //if($(this).data('youku_player'))return;
        var elId=$(this).attr('id'),
            vid=$(this).data('vid');
        if(!elId){
            elId='youku_'+getNewId();
            $(this).attr('id',elId);
        };

        $('<iframe height=100% width=100% src="http://player.youku.com/embed/'+vid+'" frameborder=0 allowfullscreen></iframe>').appendTo($(this));

        /*getScript('http://player.youku.com/jsapi',function(){
         new YKU.Player(elId, {
         client_id: youku_client_id,
         vid: vid,
         newPlayer:true
         });
         });*/

        $(this).data('youku_player',1);
    });
};
/**
 *站内视频
 */
;$.fn.video_player=function(){
    return this.each(function(){
        if($(this).data('videoPlayer'))return;
        var $this=$(this),
            id=$this.attr('id'),
            width=$this.width(),
            height=$this.height(),
            src=$this.data('src'),
            autoplay=$this.data('autoplay'),
            poster=$this.data('poster'),
            loop=$this.data('loop'),
            playerId=id+'_player';
        if(!poster){
            poster=src.replace('mp4','png');
        };
        if(IEV>0&&IEV<10){

            getScripts([staticPath+'/plugins/swfobject/swfobject.js',staticPath+'/plugins/swfobject/flashVideoPlayer.js'],function(){
                $('<span id="'+playerId+'" ></span>').appendTo($this);
                flashVideoPlayer(playerId,src,width,height,autoplay,loop,poster);
            });
        }else{

            if(appSys!='web'&&isAndroid){
                $this.css({
                    "text-align":'center',
                    "position":"relative"
                });

                if(poster){
                    var videoPoster=$('<img src="'+getThumbImageView(poster,$this.width(),$this.height())+'" />').appendTo($(this));
                };
                $('<div style="background:rgba(0,0,0,.5);width:50px;height:50px;border-radius:50px;position:absolute;left:50%;top:50%;margin-left:-25px;margin-top:-25px;font-size:20px;text-align:center;line-height:50px;color:#fff;"><span class="xzicon-play"></span></div>').appendTo($this);
                $this.on(click,function(){
                    webViewCall('videoPlayer',{src:src});
                });
            }else{
                $('<video id="'+playerId+'" src="'+src+'" width="'+width+'" height="'+height+'" '+(autoplay?'autoplay':'')+' '+(poster?'poster="'+poster+'"':'')+' '+(loop?'loop':'')+'  controls></video').appendTo($this);
            };
        };
        $this.data('videoPlayer',1);
    });
};

/**
 *拖拽左滑块
 onSlideArrived:滑动到指定位置后触发事件,
 onSlideReturn:滑动返回到起点后触发事件,
 distance:滑动的距离，未设置则取元素宽度
 */
$.fn.dragSlideLeft=function(opts){
    return this.each(function(){
        var options=$.extend(true,{
            onSlideArrived:$.noop,
            onSlideReturn:$.noop,
            distance:0
        },opts);
        var dragEl=$(this),
            distance=options.distance||dragEl.width(),
            startTimer,
            startLeft;

        dragEl.on({
            touchstart:function(e){
                startLeft=dragEl.position().left;
                dragEl.data('left',startLeft);
                startTimer=e.timeStamp;

            },
            touchdrag:function(e){

                if(Math.abs(e.dragDeltaX)<3||Math.abs(e.dragDeltaX)<Math.abs(e.dragDeltaY))return;


                e.preventDefault();
                var num=dragEl.data('left')+e.dragDeltaX;

                if(num>0||isNaN(num)){
                    num=0;
                };
                dragEl.data('left',num);
                dragEl.css('left',num);

            },
            touchend:function(e){

                if(dragEl.data('left')<startLeft&&((dragEl.data('left')<-distance/2&&dragEl.data('left')!=-distance)||(e.timeStamp-startTimer<200&&dragEl.data('left')<-10))){
                    dragEl.animate({left:-distance},'fast','easeOut',function(){
                        options.onSlideArrived();
                    });
                    dragEl.data('left',-distance);
                }else if(dragEl.data('left')!=0){
                    dragEl.animate({left:0},'fast','easeOut',function(){
                        options.onSlideReturn();
                        dragEl.data('left',0);
                    });
                }
            }
        }).on(click,function(e){
            if(dragEl.data('left')||dragEl.css('left')!='0px'){
                e.stopPropagation();
                dragEl.animate({left:0},'fast','easeOut',function(){
                    options.onSlideReturn();
                }).data('left',0);
            };
        });
    });
};

/**
 *分享
 */
;function share(data){
    if(!window.shareDialog){
        getScript(staticPath+'/js/share.js',function(){
            shareDialog(data);
        });
    }else{
        shareDialog(data);
    };
};



/**
 *上传文件
 var plUploadURL = '/upload/index/type/root/';//响站用户上传，必须是响站用户登录才能传，判断cookie xzUid和xzUserToken
 var plUploadURL = '/upload/index/type/site/folderId/';//站点管理员上传，必须是响站用户登录并打开站点才能传，判断cookie xzUid和xzUserToken和xzSiteId
 var plUploadURL = '/upload/index/type/user/app/xxxxx/userId/xxxxx';//站点用户上传，如果是已登录用户，须记录用户id，判断cookie visitorSiteId和是否有userId
 */

;function xzUpload(opts){
    var uploadOpts={
            count:opts.selectCount||1,
            title:opts.title||SiteText.uploadFile,
            filters:typeof opts.extensions!='undefined'?opts.extensions:'jpg,gif,png,jpeg',
            max_size:opts.max_size||'104857600',
            maxFiles:opts.maxFiles||30,
            cropper:opts.cropper||'',
            type:opts.type||'root',
            watermark:opts.watermark||'yes',
            siteId:opts.siteId||'',
            folderId:opts.folderId,
            appId:opts.appId,
            userId:opts.userId||(typeof SiteInfo=='object'?(SiteInfo.userId?SiteInfo.userId:'clientKey_'+SiteInfo.clientKey):'0')
        },
        uploading,
        useSpaceSize,
        spaceSize,
        start=function(){
            if(appSys=='web'){
                var plUploadURL= '/upload/index/type/'+uploadOpts.type+'/watermark/'+uploadOpts.watermark,
                    uploadId='uploadBtn_'+getNowRandom(),
                    el=$('<div class="uploadWrap"><a href="javascript:;" class="addUpload" id="'+uploadId+'"><i class="xzicon-add"></i></a><div class="uploadList" style="display:none;"><div class="uploadBox"></div></div></div>'),
                    uploadList=el.find('div.uploadBox'),
                    delFiles=function(ids){
                        var  obj={
                            ids:ids,
                            type:uploadOpts.type
                        };
                        if(uploadOpts.type=='user'){
                            obj.userId=uploadOpts.userId;
                            obj.appId=uploadOpts.appId;
                        };
                        rpcJSON('/file/delFiles',obj);
                    },
                    uploader;

                if(uploadOpts.type=='user'){
                    plUploadURL+='/appId/'+uploadOpts.appId+'/userId/'+uploadOpts.userId;
                }else if(uploadOpts.type=='site'){
                    plUploadURL+='/folderId/'+uploadOpts.folderId;
                };

                var uploadDialog=ConfirmDialog(uploadOpts.title,el,{
                    height:400,
                    width:600,
                    confirmText:SiteText.completeText,
                    showConfirmText:false,
                    onBeforeConfirm:function(fn){
                        if(uploading){
                            Tips('error',SiteText.uploadProcess);
                            return;
                        };

                        var files=uploadList.find('div.success');
                        if(!files.length){
                            Tips('error',SiteText.noUpload);
                            return;
                        }else{
                            var selectedData=[];
                            files.each(function(){
                                selectedData.push($(this).data('data'));
                            });
                            if($.isFunction(opts.callBack)){
                                opts.callBack(selectedData);
                            };
                            fn();
                        };
                    },
                    onConfirm:function(){
                        uploader.destroy();
                    },
                    onBeforeCancel:function(fn){
                        if(uploading){
                            Tips('error',SiteText.uploadProcess);
                            return;
                        };

                        var files=uploadList.find('div.success');
                        if(!files.length){
                            fn();
                        }else{
                            Confirm(SiteText.cancelUploadWarning,function(){
                                var selectedData=[];
                                files.each(function(){

                                    selectedData.push($(this).data('data').fileId);
                                });
                                //删除已经上传的文件
                                delFiles(selectedData);

                                fn();
                            });

                        };

                    },
                    onCancel:function(){
                        uploader.destroy();
                    }
                });

                var setUpload=function(){
                    //设置上传

                    var maxFiles = uploadOpts.count?Math.min(uploadOpts.maxFiles,uploadOpts.count):uploadOpts.maxFiles,
                        upContainer = $('#fileListBox');

                    //uploader = new plupload.Uploader({
                    uploader = Qiniu.uploader({
                        chunk_size: '4mb',
                        get_new_uptoken: false,
                        uptoken_url:'/upload/uptoken',
                        domain:filePath,
                        runtimes: 'html5,flash,html4,silverlight',
                        //url: plUploadURL,
                        browse_button: uploadId,
                        max_file_size: '1024mb',
                        flash_swf_url: staticPath+'/plugins/plupload/Moxie.swf',
                        silverlight_xap_url: staticPath+'/plugins/plupload/Moxie.xap',
                        filters: uploadOpts.filters ? [{title: uploadOpts.filters, extensions: uploadOpts.filters}] : '',
                        autostart: true,
                        max_retries: 3,
                        multi_selection: uploadOpts.count==1?false:true,
                        init: {
                            FilesAdded: function(up, files) {
                                $('#'+uploadId).hide();
                                if(uploadOpts.count==1){
                                    $('div.uploadItem',uploadList).remove();
                                };
                                var t = $('div.uploadItem',uploadList).length;
                                if (files.length > maxFiles) {
                                    top.Tips('error', SiteText.mostUpload + maxFiles + SiteText.fileUnits);
                                    return;
                                };
                                if(uploadOpts.count!=0&&t+files.length>uploadOpts.count){
                                    top.Tips('error', SiteText.mostUpload + uploadOpts.count + SiteText.fileUnits);
                                    return;
                                };
                                var upSize=0,
                                    c=0;

                                $.each(files, function(i, item) {
                                    upSize+=item.size;
                                    if(upSize+useSpaceSize>spaceSize){
                                        spaceError();
                                        return false;
                                    };
                                    var $li = $('<div id="' + item.id + '" class="uploadItem upload">'+
                                        '<div class="clearfix uploadBar">'+
                                        '<span class="icon"><i class="xzicon-time"></i></span>'+
                                        '<div class="text">'+
                                        '<div class="textBox">'+
                                        '<p class="title">' + item.name + '</p>'+
                                        '<p class="size">' + plupload.formatSize(item.size) + '</p>'+
                                        '</div>'+
                                        '</div>'+
                                        '<div class="rightSet">'+
                                        '<span class="progress" role="progress"></span>'+
                                        '<a href="javascript:;" class="cancel tc_2" role="cancel">'+SiteText.cancelText+'</a>'+
                                        '</div>'+
                                        '<span class="canvas" style="width:0;" role="fileUpLoading"></span>'+
                                        '</div>'+
                                        '</div>').appendTo(uploadList);
                                    $li.find('a[role="cancel"]').on(click,function(){
                                        if(!$li.hasClass('upload')){
                                            delFiles([$li.data('data').fileId]);
                                            useSpaceSize-=item.size;
                                        };
                                        uploader.removeFile(item.id);
                                        $li.hideRemove(function(){

                                            if(!uploadList.find('div.uploadItem').length){
                                                $('#'+uploadId).show();
                                                uploadList.parent().hide();
                                                uploadDialog.hideConfirmText();
                                                return;
                                            };
                                            if(!uploadList.find('div.upload').length){
                                                uploading=false;
                                            };
                                        });

                                    });
                                    c++;
                                });
                                if(c>0){
                                    uploadList.parent().show();
                                    uploader.start();
                                    uploading = true;
                                    uploadDialog.showConfirmText();
                                };
                            },
                            StateChanged:function(){
                                //tests(uploader.state);
                            },
                            UploadProgress: function(up, file) {
                                var fileBar=$('#' + file.id);
                                fileBar.find('span[role="fileUpLoading"]').css('width', file.percent + "%");
                                fileBar.find('span[role="progress"]').text(file.percent + "%");
                                if(file.percent>0&&!fileBar.data('progress')){
                                    fileBar.find('span.icon').html('<i class="xzicon-upload"></i>');
                                    fileBar.data('progress',1);
                                };
                            },

                            FileUploaded: function(up, file, response) {
                                var id = file.id,
                                    info = $.parseJSON(response),
                                    requestData={
                                        type:uploadOpts.type,
                                        watermark:uploadOpts.watermark,
                                        appId:uploadOpts.appId,
                                        userId:uploadOpts.userId,
                                        folderId:uploadOpts.folderId,
                                        name:file.name,
                                        size:file.size,
                                        key:info.key
                                    };

                                rpcJSON('/upload/index/',requestData,function(data){

                                    var $li=$('#' + file.id);
                                    $li.data('data',data).removeClass('upload').addClass('success');
                                    var fileType=data.file.split('.')[data.file.split('.').length - 1],
                                        images = ['ico','jpg', 'png', 'gif', 'jpeg','mp4'],
                                        fileGridIcon=$li.find('span.icon');
                                    if($.inArray(fileType,images)>=0){
                                        if(fileType=='mp4'){
                                            fileGridIcon.html('<img src="'+data.thumb+'" data-video="1"  />');

                                        }else{
                                            fileGridIcon.html('<img data-src="'+data.src+'" />');
                                        };
                                        fileGridIcon.find('img').setThumbImageView();
                                    }else{
                                        fileGridIcon.html('<i class="xzicon-file"></i>');
                                    };

                                    $li.find('a[role="cancel"]').text(SiteText.remove);

                                    if(uploadOpts.count==1){
                                        uploading=false;
                                        uploader.destroy();
                                        if(uploadOpts.cropper){
                                            uploadDialog.close(function(){
                                                cropper(data.src,{
                                                    cropperSize:uploadOpts.cropper,
                                                    imgData:data,
                                                    onConfirm:function(sData){
                                                        data.src=sData.src;
                                                        data.file=sData.file;

                                                        if($.isFunction(opts.callBack)){
                                                            opts.callBack([data]);
                                                        };
                                                    },
                                                    onCancel:function(){
                                                        delFiles([data.fileId]);
                                                    }
                                                });
                                            });

                                        }else{
                                            if($.isFunction(opts.callBack)){
                                                opts.callBack([data]);
                                            };
                                            uploadDialog.close();
                                        };
                                    };

                                },function(errorMessage){
                                    if (errorMessage == 'space') {
                                        tips('error',SiteText.spaceExceed);
                                    } else if (errorMessage == 'maxsize') {
                                        Tips('error',SiteText.sizeExceed);
                                    } else {
                                        Tips('error',errorMessage);
                                    };
                                    uploader.removeFile(id);
                                    $('#' + id).remove();
                                });
                                useSpaceSize+=file.size;
                            },
                            Error: function(up, err) {
                                Tips('error',err.message);
                            },
                            UploadComplete: function(uploader, files) {
                                uploading = false;

                            },
                            Key: function(up, file) {
                                var fileType=file.name.split('.')[file.name.split('.').length - 1];
                                var key = getNowRandom()+'.'+fileType;
                                return key
                            }
                        }
                    });
                    //uploader.init();
                };

                if(typeof plupload=='undefined'){
                    getScripts([staticPath+'/plugins/plupload/plupload.full.min.js',staticPath+'/plugins/plupload/i18n/zh-cn.js',staticPath+'/css/xzupload.css',staticPath+'/plugins/plupload/qiniu.js'],setUpload);
                }else{
                    setUpload();
                };
            }else{

                if(uploadOpts.count){
                    uploadOpts.maxFiles=Math.min(uploadOpts.maxFiles,uploadOpts.count);
                };
                uploadOpts.max_size=spaceSize-useSpaceSize;
                webViewCall('upload',uploadOpts,function(selectedData){
                    if(!selectedData.length){
                        Tips(SiteText.noUpload);
                    }else{
                        if($.isFunction(opts.callBack)){
                            opts.callBack(selectedData);
                        };
                    };
                });
            }
        },
        spaceError=function(){
            Alert(SiteText.spaceExceed);
        };
    if(uploadOpts.type=='root'){
        useSpaceSize=0;
        spaceSize=1024*1024*1024;
        start();
    }else{
        rpcJSON('/file/getSpaceSize',{siteId:uploadOpts.siteId||(typeof SiteInfo!='undefined'?SiteInfo.siteId:'')},function(backData){
            useSpaceSize=backData.useSpaceSize;
            spaceSize=backData.spaceSize;
            if(spaceSize>useSpaceSize){
                start();
            }else{
                spaceError();

            }
        });
    }
};



//设置图表
function getChart(fn){
    var months=['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
        shortMonths=[1,2,3,4,5,6,7,8,9,10,11,12],
        weekdays=['周日','周一','周二','周三','周四','周五','周六'];
    if($.fn.highcharts){
        fn();
    }else{
        getScript(staticPath+'/js/highcharts.js',function(){
            Highcharts.setOptions({
                lang:{
                    months:months,
                    shortMonths:shortMonths,
                    weekdays:weekdays,
                    thousandsSep:','
                }
            });
            fn();
        });
    }
};

//预览app
$.fn.previewApp=function(type,id){
    return this.each(function(){
        if(!(type&&id)){
            return;
        };
        var $this=$(this),
            clickFn,btnUrl;

        if(isPhoneState){
            if(isWeixin){
                btnUrl='http://a.app.qq.com/o/simple.jsp?pkgname=com.tuweia.xiangjian';
            }else if(isIos){
                btnUrl='http://itunes.apple.com/cn/app/id1128668754?mt=8';
            }else if(isAndroid){
                btnUrl='https://ssl.xiangzhan.com/xiangjian.apk';
            }else{
                btnUrl='http://a.app.qq.com/o/simple.jsp?pkgname=com.tuweia.xiangjian';
            };

            $this.data('href',btnUrl);
            clickFn=function(){
                if(isAndroid&&appSys!='web'){
                    var callData={
                        pagename:'com.tuweia.xiangjian',
                        url:'http://a.app.qq.com/o/simple.jsp?pkgname=com.tuweia.xiangjian',
                        id:type+':'+id
                    };

                    webViewCall('openapp',callData);
                }else if(appSys!='web'){
                    var iframeHtml = $('<iframe src="xj://xiangzhan.com/?url='+type+':'+id+'" style="display:none;"></iframe>').appendTo('body');
                    window.setTimeout(function(){
                        iframeHtml.remove();
                        xzHref(btnUrl,'new');
                    },500);
                }else{
                    var DialogEl=$('<div style="padding:20px 0;" class="tac"><p style="font-size:16px;">已经安装响见App</p><a href="xj://xiangzhan.com/?url='+type+':'+id+'" target="_blank" class="button b2 largel mt10">在响见中打开预览</a>\
					<p style="margin-top:30px;">还没有安装响见App？<a href="'+ btnUrl +'" class="tc_5">前往免费下载</a></p></div>');

                    top.Dialog(DialogEl,{
                        confirmText:'',
                        cancelText:''
                    });
                };
            };
        }else{
            clickFn=function(){
                var DialogEl=$('<div class="qrcodeBox">'+
                    '<span class="qrImg"><img src="'+getQrcode(type+':'+id)+'" /></span>'+
                    '<p class="mt10 tc_4">打开响见App扫描二维码实时预览</p>'+
                    '<p class="tc_3 mt5">还没有安装响见App? <a href="http://www.xiangzhan.com/t/xjapp" class="qrImgLink tc_5" target="_blank">前往免费下载</a></p>'+
                    '</div>');

                top.ConfirmDialog('预览App',DialogEl,{
                    width:480,
                    height:300,
                    scroll:false

                });
            };
        };
        $this.on(click,clickFn);
    });
};

//获取主题预览地址
function getThemePreviewUrl(url,tid){
    if(!url)return '';
    url='http://'+url+'.'+getDomain()+'';
    if($(window).width()>=1024||top.document.body.clientWidth>=1024){
        url='http://'+getDomain()+'/site/viewIframe?pageUrl='+encodeURIComponent(url);
    };
    return url;
};

//去除空格
function Trim(str,is_global){

    var result;

    result = str.replace(/(^\s+)|(\s+$)/g,"");

    if(is_global&&is_global.toLowerCase()=="g")

    {

        result = result.replace(/\s/g,"");

    };

    return result;

};

//获取完整地址
function getFullUrl(href){
    if(href.indexOf('://')<0&&windowURL.indexOf('://')>=0){
        if(windowURL){
            href=windowURL.split('/')[0]+'//'+windowURL.split('/')[2]+href;
        };
    };
    return href;
};

//重定义链接
function xzHref(href,tab,title,data){
    if(appSys=='web'){
        if(tab&&top.openTab&&((tab=='phone'&&top.phoneFrame)||tab!='phone')){
            top.openTab(href,tab,title);
        }else{
            window.location.href=href;
        }
    }else{

        webViewCall('href',{href:getFullUrl(href),title:title,tab:tab,requestData:data});
    };
};

/*
 *添加默认事件
 */

$(document).ready(function(){

    $('body').delegate('a',click,function(e){

        var tab=$(this).attr('tab'),
            href=$(this).attr('href')||'javascript:;';

        if(href.indexOf('javascript:')!=0&&href.indexOf('#')!=0&&href.indexOf('tel')!=0&&href.indexOf('sms')!=0&&href.indexOf('add')!=0&&href.indexOf('mail')!=0){
            if(appSys!='web'||(appSys=='web'&&tab&&$.isFunction(top.openTab)&&!(tab=='phone'&&!top.phoneFrame))){

                xzHref(href,tab,(($(this).attr('tabtitle')||$(this).attr('title'))||$(this).text().replace(/\s/g,"").substring(0,20)));
                e.preventDefault();
            };
        }else if(href.indexOf('#')==0&&$(href).length&&$.fn.scrollTo){
            $(window).scrollTo($(href),'normal');
        };

    }).delegate('[data-tips]',{
        mouseenter:function(e){
            if(!isTouch){
                var $this=$(this);
                $this.showTips();
            };
        },
        mouseleave:function(e){
            if(!isTouch){
                var $this=$(this);
                $this.hideTips();
            };
        },
        click:function(e){
            if(!isTouch){
                $(this).hideTips();
            };
        }
    }).delegate('input[type="text"],input[type="password"],input[type="tel"],input[type="email"],textarea',{
        focus:function(){
            $(this).removeClass('error success').addClass('focus');
        },
        blur:function(){
            var value=$(this).val();

            if(!$(this).data('script')){
                value=stripScript(value);
            };
            if($(this).data('clearspace')||$(this).attr('type')=='email'||$(this).attr('type')=='tel'||$(this).attr('type')=='password'){
                value=Trim(value,'g');
            }else{
                value=Trim(value);
            };
            if(value!==''&&Trim(value,'g')==''){
                $(this).val('');
            }else{
                $(this).val(value);
            };
            $(this).removeClass('focus');
        }
    }).delegate('input[role="number"]',{
        keyup:function(){
            this.value=this.value.replace(/\D/g,'');
        },
        afterpaste:function(){
            this.value=this.value.replace(/\D/g,'');
        }
    }).delegate('input[role="price"]',{
        keyup:function(){
            checkPrice($(this));
        },
        afterpaste:function(){
            checkPrice($(this));
        }
    });

    setPhoneDropMenu();

    //接收iframe信息，设置frameid
    if(!IEV||IEV>9){
        window.addEventListener("message", function(e){

            if(e.data=='toparTitleClick'){
                toparTitleClick();

            }else if(e.data.indexOf('thisFrameId')==0){
                thisFrameId=e.data.replace('thisFrameId=','');
            };
        }, true);
    };

}).ajaxStart()
    .ajaxError(function(){Tips('error')})
    .ajaxStop(function(){})
    .ajaxSuccess(function(){});
$(window).resize(function(){
    windowHeight=$(window).height();
    windowWidth=$(window).width();
    isPhoneState=windowWidth<=768;
});


