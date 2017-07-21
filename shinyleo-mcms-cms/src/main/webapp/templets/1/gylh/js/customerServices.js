/**
 *在线客服系统
 */

(function(){

    var state=0,
        imCustomURL='/xzcs/customer/',
        offlineMessage='暂时没有客服在线，请稍后再试。',
        inviteMessage='客服邀请您对话，点击接受。',
        servicesId=$.cookie('servicesId'),
        inviteData={};


    //连接客服
    function connect(sid,status){

        if(sid=='queue'){
            sid='';
        };

        if(!status){
            status='invite';
        };

        state=2;

        var nickName=SiteInfo.userNickName||SiteInfo.userName||$.cookie('userNickName');


        var url=encodeURI(imCustomURL+'?status='+status+'&servicesId='+sid+'&now='+getNowRandom());
        var el='<iframe src="'+url+'" width="100%" height="100%" scrolling="no" frameborder="0"/>';

        $('<div class="xzcsWrapper '+(status=='connecting'?'none':'')+'" id="xzMessage"></div>').append(el).appendTo('body');

        $('[role="connServices"][autohide]').parents('div.appWrapper:first').remove();
    };


    //请求客服对话框
    function getServices(){
        if(state==2){
            if(typeof recoverMessageWindow=='function'){
                recoverMessageWindow();
            };
        }else{
            rpcJSON('/message/getMyServices',{invitation:2},function(backData){

                if(backData.servicesId=='offline'){
                    offline();
                }else{
                    connect('');
                }
            });
        };

    };

    //自动弹出邀请对话框
    function openInvite(){
        if(state==2)return;
        rpcJSON('/message/getMyServices',{invitation:1},function(backData){
            if(backData.servicesId){
                var delayClose,
                    el=$('<div style="cursor:pointer;">'+(inviteData.inviteMessage||inviteMessage)+'</div>'),
                    dialog=Dialog(el,{
                        onClose:function(){
                            if(delayClose){
                                clearTimeout(delayClose);
                                delayClose=null;
                            };
                            if(state!=2){
                                resetsXZCS();
                            };
                        }
                    });

                el.on(click,function(){
                    connect(backData.servicesId);
                    dialog.close();
                });

                if(inviteData.autoClose&&inviteData.autoCloseTime){
                    delayClose=setTimeout(function(){
                        dialog.close();
                    },inviteData.autoCloseTime);
                };

                state=1;
            }else{
                //resetsXZCS();
            };
        });

    };

    //重设状态
    resetsXZCS=function(){
        state=0;
        if(inviteData.autoSendTime2){
            setTimeout(openInvite,inviteData.autoSendTime2);
        };
        if($('#xzMessage').length){
            $('#xzMessage').remove();
        };
        $.cookie('servicesId',null);
    };



    //留言
    function offline(){
        var dialog=Alert(inviteData.offlineMessage||offlineMessage);
    };


    //如果本页允许自动邀请
    if(servicesId){
        connect(servicesId,'connecting');
    }else{
        if(SiteInfo.invitation=='1'){
            //获取站点邀请设置
            rpcJSON('/message/getInviteSet',{vsiteId:SiteInfo.siteId},function(backData){

                if(backData.autoSendTime1){
                    backData.autoSendTime1*=1000;
                };
                if(backData.autoSendTime2){
                    backData.autoSendTime2*=1000;
                };
                if(backData.autoCloseTime){
                    backData.autoCloseTime*=1000;
                };

                inviteData=backData;

                //如果已经连接客服
                if(servicesId){
                    connect(servicesId,'connecting');
                }else{
                    //如果设置了自动弹出邀请，则定时弹出邀请对话框
                    if(inviteData.autoSend&&inviteData.autoSendTime1){
                        setTimeout(openInvite,inviteData.autoSendTime1);
                    };
                };

            });
        };
    };
    $('body').delegate('[role="connServices"]',click,getServices);

}());