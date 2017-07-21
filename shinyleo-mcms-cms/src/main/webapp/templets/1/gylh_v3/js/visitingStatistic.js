/**
 *访问统计
 */
(function(){
    var vData={
        title:SiteInfo.pageTitle,//网页标题
        url:windowURL,//网页网址
        appSys:appSys,//系统类型
        userAgent:navigator.userAgent,//浏览器信息
        systemLanguage:navigator.systemLanguage,// 用户操作系统支持的默认语言（火狐没有）
        userLanguage:navigator.userLanguage,    // 用户在自己的操作系统上设置的语言（火狐没有）
        screenWidth:window.screen.width,
        screenHeight:window.screen.height,
        devicePixelRatio:devicePixelRatio,
        clientKey:SiteInfo.clientKey,
        visitorInfo:visitorInfo
    };

    rpcJSON('/visitingStatistic/addpv',vData,function(backData){
        if(backData.data){
            visitorInfo.area=backData.data;
        }
    },function(){});
})();