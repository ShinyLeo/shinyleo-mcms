<base target="mainFrame" />
<#assign static="http://cdn.mingsoft.net">
<#setting url_escaping_charset='utf-8'>
<#assign bootstrap="3.3.5">
<#assign manager_ui="4.5.7">
<meta content="IE=edge" http-equiv="X-UA-Compatible" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link rel="stylesheet" type="text/css" href="${static}/plugins/animate/1.0.0/animate.css" media="all" />
<link rel="stylesheet" type="text/css" href="${static}/plugins/iconfont/1.0.0/iconfont.css"/>

<script type="text/javascript" src="${static}/plugins/jquery/1.9.1/jquery-1.9.1.js"></script>

<link rel="stylesheet" type="text/css" href="${static}/plugins/ztree/3.5/zTreeStyle.css" media="all" />
<script type="text/javascript" src="${static}/plugins/ztree/3.5/jquery.ztree.all-3.5.min.js"></script>

<link rel="stylesheet" type="text/css" href="${static}/plugins/bootstrap/${bootstrap}/css/bootstrap.min.css" media="all" />
<link rel="stylesheet" type="text/css" href="${static}/plugins/bootstrap/${bootstrap}/css/bootstrap-switch.css" media="all" />
<link rel="stylesheet" type="text/css" href="${static}/plugins/bootstrap/${bootstrap}/css/bootstrapValidator.css" media="all" />
<link rel="stylesheet" type="text/css" href="${static}/plugins/bootstrap/${bootstrap}/css/bootstrap-notify.css" media="all" />

<script type="text/javascript" src="${static}/plugins/bootstrap/${bootstrap}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${static}/plugins/bootstrap/${bootstrap}/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="${static}/plugins/bootstrap/${bootstrap}/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="${static}/plugins/bootstrap/${bootstrap}/js/bootstrap-notify.js"></script>
<!--时间插件-->
<script type="text/javascript" src="${static}/plugins/bootstrap.select2/3.5.2/select2.min.js"></script>
<link rel="stylesheet" href="${static}/plugins/bootstrap.select2/3.5.2/select2.css" type="text/css">
<script type="text/javascript" src="${static}/plugins/jquery.validation/1.15.0/jquery.validate.min.js"></script>
<script type="text/javascript" src="${static}/plugins/jquery.tmpl/1.4.2/jquery.tmpl.min.js"></script>
<!--时间插件-->
<link href="${static}/plugins/bootstrap.daterangepicker/1.3.4/daterangepicker.css" rel="stylesheet">
<script type="text/javascript" src="${static}/plugins/bootstrap.daterangepicker/1.3.4/moment.js"></script>
<script type="text/javascript" src="${static}/plugins/bootstrap.daterangepicker/1.3.4/daterangepicker.js"></script>
<script type="text/javascript" src="${static}/plugins/jquery.cookie/2.2.0/jquery.cookie.js"></script>

<!----上传图片--->
<script type="text/javascript" src="${static}/plugins/jquery.swfupload/1.0.0/swfupload.js"></script>
<script type="text/javascript" src="${static}/plugins/jquery.swfupload/1.0.0/jquery.swfupload.js"></script>
<script type="text/javascript" src="${static}/plugins/jquery.swfupload/1.0.0/fileprogress.js"></script>

<!--后台UI-->

<!--
<script type="text/javascript" src="${static}/skin/manager/${manager_ui}/js/ms.validate.js"></script>
<script type="text/javascript" src="${static}/skin/manager/${manager_ui}/js/ms.web.js"></script>
<script type="text/javascript" src="${static}/skin/manager/${manager_ui}/js/ms.page.js"></script>
<script type="text/javascript" src="${static}/skin/manager/${manager_ui}/js/ms.manager.min.js"></script>
<script src="http://cdn.mingsoft.net/plugins/ms/mstore/mstore-client.min.js"></script>
--->
<!--ms.validate.js-->
<script>
    var Ms={};
    Ms.validate = {
        /**
         * ==================================================================
         * 功能：替换空格（将多个连续空格替换为一个空格） 使用：ReplaceSpace(obj) 返回：string
         * ==================================================================
         */
        "replaceSpace":function(str) {
            while ( str.indexOf("  ") >= 0 )
            {
                str = str.replace("  " ," ");
            }
            return str;
        },
        /**
         * ==================================================================
         * 功能：检查是否要显示提示窗口
         * ==================================================================
         */
        "ifShow":function(ShowMsg){
            if (ShowMsg == "" ){
                return false;
            }else{
                return true;
            }
        },
        /**
         * ==================================================================
         * 功能：检测是否存在变量
         * ==================================================================
         */
        "isExitsFunction":function(funcName){
            try {
                if (typeof(eval(funcName)) == "function") {
                    return true;
                }
            } catch(e) {}
            return false;
        },
        /**
         * ==================================================================
         * 功能：检测是否存在变量
         * ==================================================================
         */
        "isExitsVariable":function(variableName){
            try {
                if (typeof(variableName) == "undefined") {
                    //alert("value is undefined");
                    return false;
                } else {
                    //alert("value is true");
                    return true;
                }
            } catch(e) {}
            return false;
        },
        /**
         * ==================================================================
         * 功能：非空检查，不忽略空格 提示信息：输入框为空，请输入！ 使用：isNull(obj,string) 返回：bool
         * ==================================================================
         */
        "isNull":function(str,ShowMsg){
            var show =this.ifShow(ShowMsg) ;
            // 非空检查
            if(str == ""){
                if (show) alert(ShowMsg);
                return false;
            }else {
                return true;
            }
        },
        /**
         * ================================================================== 功能：邮箱地址检查
         * 提示信息：未输入邮件地址或邮件地址无效！ 使用：MailCheck(obj,string) 返回：bool
         * ==================================================================
         */
        "mailCheck":function(obj,ShowMsg)
        {
            var show = ifShow(ShowMsg) ;

            if(obj.value!= "")
            {
                var ok1=obj.value.indexOf("@");
                var ok2=obj.value.indexOf(".");
                if(!((ok1!=-1)&&(ok2!=-1)))
                {
                    if (show) alert(ShowMsg);
                    obj.focus();
                    obj.select();
                    return false;
                }
                var allowstrlist = "&#%<>";
                var endvalue = true;
                for (i=0;i<obj.value.length;i++)
                {
                    if (allowstrlist.indexOf(obj.value.substr(i,1))!=-1)
                    {
                        endvalue=false;
                        break;
                    }
                }
                if(endvalue==false)
                {
                    if (show) alert(ShowMsg);
                    obj.focus();
                    obj.select();
                    return false;
                }
                // 邮件地址正确
                return true;
            }
            else
            {
                // 请输入电子信箱地址
                if (show) alert(ShowMsg);
                obj.focus();
                obj.select();
                return false;
            }
        },






        /**
         * ==================================================================
         * 功能：检查输入的是否为数字 提示信息：未输入或输入的不是一个合法的数字！ 使用：isNumeric(obj,string) 返回：bool
         * ==================================================================
         */
        "isNumeric":function(obj,ShowMsg)
        {
            var show = ifShow(ShowMsg) ;

            var IfTrue = obj.value.search(/^(-|\+)?\d+(\.\d+)?$/) != -1;

            if (show && IfTrue ==false)
            {
                alert(ShowMsg);
                obj.focus();
                obj.select();
                return false;
            }
            else
            {
                return true;
            }
        },

        /**
         * ================================================================== 功能：打印
         * 使用：Print() 返回：
         * ==================================================================
         */
        "Print":function()
        {

            document.all.print.style.display = "none";
            window.print();
            window.close();
        },

        /**
         * ==================================================================
         * 功能：判断是否为日期(格式:yyyy-mm-dd) 提示信息：未输入或输入的日期格式错误！ 使用：isDate(obj,string) 返回：bool
         * ==================================================================
         */
        "isDate":function(obj,ShowMsg)
        {
            var show = ifShow(ShowMsg) ;

            if(obj.value==null)
            {
                if (show) alert(ShowMsg);
                return false;
            }

            if(obj.value=="")
            {
                if (show) alert(ShowMsg);
                return false;
            }

            var datePat=/^(\d{2}|\d{4})(\-)(\d{1,2})(\-)(\d{1,2})$/;

            var dateStr=obj.value;
            // is the format ok?
            var matchArray = dateStr.match(datePat);


            if (matchArray==null)
            {
                if (show) alert(ShowMsg);
                return false;
            }
            year=matchArray[1];
            month=matchArray[3];
            day=matchArray[5];

            if (year.length!=4 || month.length!=2 || day.length!=2)
            {
                if (show) alert(ShowMsg);
                return false;
            }
            if (month < 1 || month > 12)
            {
                if (show) alert(ShowMsg);
                return false;
            }
            if (day < 1 || day > 31)
            {
                if (show) alert(ShowMsg);
                return false;
            }

            if ((month==4 || month==6 || month==9 || month==11) && day==31)
            {
                if (show) alert(ShowMsg);
                return false;
            }

            if (month==2)
            {
                var isleap=(year % 4==0 && (year % 100 !=0 || year % 400==0));
                if (day>29 || ((day==29) && (!isleap)))
                {
                    if (show) alert(ShowMsg);
                    return false;
                }
            }
            return true;
        },

        /**
         * ==================================================================
         * 功能：日期大小判断(格式：yyyy-mm-dd) 提示信息：未输入或输入的开始日期大于结束日期！
         * 使用：JudgeDate(obj1,obj2,string) 返回：bool
         * ==================================================================
         */
        "JudgeDate":function(obj1,obj2,ShowMsg)
        {
            var show = ifShow(ShowMsg) ;

            var eva = isDate(obj1,"") && isDate(obj2,"");

            if(obj1.value!="" && obj2.value!="" & eva != false)
            {
                var date1 = obj1.value;
                var myDate1 = Date.parse(date1.replace("-","/"));
                var date2 = obj2.value;
                var myDate2 = Date.parse(date2.replace("-","/"));
                if(myDate1 > myDate2)
                {
                    if (show) alert(ShowMsg);
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else
            {
                if (show) alert(ShowMsg);
                return false;
            }
        },

        /**
         * ==================================================================
         * 功能：字符串操作,去除字符串两边的空格 使用：Trim(string) 返回：string
         * ==================================================================
         */
        /**
         * ==================================================================
         * 功能：去除左边的空格 使用：LTrim(string) 返回：string
         * ==================================================================
         */
        "LTrim":function(str)
        {
            var whitespace = new String(" \t\n\r");
            var s = new String(str);

            if (whitespace.indexOf(s.charAt(0)) != -1)
            {
                var j=0, i = s.length;
                while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
                {
                    j++;
                }
                s = s.substring(j, i);
            }
            return s;
        },

        /**
         * ==================================================================
         * 功能：去除右边的空格 使用：RTrim(string) 返回：string
         * ==================================================================
         */
        "RTrim":function(str)
        {
            var whitespace = new String(" \t\n\r");
            var s = new String(str);

            if (whitespace.indexOf(s.charAt(s.length-1)) != -1)
            {
                var i = s.length - 1;
                while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
                {
                    i--;
                }
                s = s.substring(0, i+1);
            }
            return s;
        },

        // 去除前后空格
        "Trim":function(str)
        {
            return this.RTrim(this.LTrim(str));
        },


        /**
         * ==================================================================
         * 功能：无效字符的检测（不允许输入特殊字符） 提示信息：未输入或输入包含非法字符 使用：CheckChar(obj,Lchar,string)
         * Lchar：要检查的特殊字符 返回：bool
         * ==================================================================
         */
        "CheckChar":function(obj,Lchar,ShowMsg)
        {
            var show = ifShow(ShowMsg);

            var strlist = Lchar; // "~!@#$%^&*?<>\"\'";

            // 无效字符的检测
            if(obj.value!= "")
            {
                var tmpbool=true;
                for (i=0;i<obj.value.length;i++)
                {
                    if(strlist.indexOf(obj.value.substr(i,1))!=-1)
                    {
                        tmpbool=false;
                        break;
                    }
                    else
                    {}
                }

                if(tmpbool==false)
                {
                    if (show) alert(ShowMsg + strlist);
                    obj.focus();
                    obj.select();
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else
            {
                if (show) alert(ShowMsg + strlist);
                return false;
            }

        },

        /**
         * ==================================================================
         * 功能：判断是否为整数、正整数、负整数、正整数+0、负整数+0 提示信息：参数错误或输入的不是一个（整数）。。
         * 使用：isInt(obj,string,int or string,string) (测试对象,+ or - or empty,empty or
         * 0,显示信息) 空 整数 + 正整数 - 负整数 返回：bool
         * ==================================================================
         */
        "isInt":function(obj,sign,zero,ShowMsg)
        {
            var show =this.ifShow(ShowMsg);

            var objStr = obj.value;
            var reg;
            var bolzero;

            if(this.Trim(objStr)=="")
            {
                if (show) alert(ShowMsg);
                obj.focus();
                obj.select();
                return false;
            }
            else
            {
                objStr=objStr.toString();
            }

            if((sign==null)||(this.Trim(sign)==""))
            {
                sign="+-";
            }

            if((zero==null)||(this.Trim(zero)==""))
            {
                bolzero=false;
            }
            else
            {
                zero=zero.toString();
                if(zero==0)
                {
                    bolzero=true;
                }
                else
                {
                    if (show) alert(ShowMsg);
                    obj.focus();
                    obj.select();
                    return false;
                }
            }

            switch(sign)
            {
                case "+-":
                    // 整数
                    reg=/(^-?|^\+?)\d+$/;
                    break;
                case "+":
                    if(!bolzero)
                    {
                        // 正整数
                        reg=/^\+?[0-9]*[1-9][0-9]*$/;
                    }
                    else
                    {
                        // 正整数+0
                        // reg=/^\+?\d+$/;
                        reg=/^\+?[0-9]*[0-9][0-9]*$/;
                    }
                    break;
                case "-":
                    if(!bolzero)
                    {
                        // 负整数
                        reg=/^-[0-9]*[1-9][0-9]*$/;
                    }
                    else
                    {
                        // 负整数+0
                        // reg=/^-\d+$/;
                        reg=/^-[0-9]*[0-9][0-9]*$/;
                    }
                    break;
                default:
                    if (show) alert(ShowMsg);
                    obj.focus();
                    obj.select();
                    return false;
                    break;
            }

            var r=objStr.match(reg);
            if(r==null)
            {
                if (show) alert(ShowMsg);
                obj.focus();
                obj.select();
                return false;
            }
            else
            {
                return true;
            }
        },
        /**
         * ==================================================================
         * 功能：判断是否为浮点数、正浮点数、负浮点数、正浮点数+0、负浮点数+0 提示信息：参数错误或输入的不是一个（浮点数）。。
         * 使用：isFloat(obj,string,int or string,string) (测试对象,+ or - or empty,empty or
         * 0,提示信息) 参数二： 空 浮点数 + 正浮点数 - 负浮点数 返回：bool
         * ==================================================================
         */
        "isFloat":function(obj,sign,zero,ShowMsg)
        {
            var show = ifShow(ShowMsg);

            var objStr = obj.value;
            var reg;
            var bolzero;

            if(Trim(objStr)=="")
            {
                if (show) alert(ShowMsg);
                obj.focus();
                obj.select();
                return false;
            }
            else
            {
                objStr=objStr.toString();
            }

            if((sign==null)||(Trim(sign)==""))
            {
                sign="+-";
            }

            if((zero==null)||(Trim(zero)==""))
            {
                bolzero=false;
            }
            else
            {
                zero=zero.toString();
                if(zero==0)
                {
                    bolzero=true;
                }
                else
                {
                    if (show) alert(ShowMsg);
                    obj.focus();
                    obj.select();
                    return false;
                }
            }

            switch(sign)
            {
                case "+-":
                    // 浮点数
                    reg=/^((-?|\+?)\d+)(\.\d+)?$/;
                    break;
                case "+":
                    if(!bolzero)
                    {
                        // 正浮点数
                        reg=/^\+?(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
                    }
                    else
                    {
                        // 正浮点数+0
                        reg=/^\+?\d+(\.\d+)?$/;
                    }
                    break;
                case "-":
                    if(!bolzero)
                    {
                        // 负浮点数
                        reg=/^-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
                    }
                    else
                    {
                        // 负浮点数+0
                        reg=/^((-\d+(\.\d+)?)|(0+(\.0+)?))$/;
                    }
                    break;
                default:
                    if (show) alert(ShowMsg);
                    obj.focus();
                    obj.select();
                    return false;
                    break;
            }

            var r=objStr.match(reg);
            if(r==null)
            {
                if (show) alert(ShowMsg);
                obj.focus();
                obj.select();
                return false;
            }
            else
            {
                return true;
            }
        },


        /**
         * ==================================================================
         * 功能：验证身份证号码是否有效 提示信息：未输入或输入身份证号不正确！ 使用：isIDno(obj,string) 返回：bool
         * ==================================================================
         */
        "isIDcard":function(obj,ShowMsg)
        {
            var show = ifShow(ShowMsg);

            // aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",71:"台湾",81:"香港",82:"澳门",91:"国外"};
            var aCity = "11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91";

            var iSum = 0;
            var info = "";
            var idCardLength = obj.value.length;

            if(!/^\d{17}(\d|x)$/i.test(obj.value)&&!/^\d{15}$/i.test(obj.value))
            {
                if (show) alert(ShowMsg);
                obj.focus();
                obj.select();
                return false;
            }

            // 在后面的运算中x相当于数字10,所以转换成a
            obj.value = obj.value.replace(/x$/i,"a");

            var curCity = obj.value.substr(0,2);

            if(!(aCity.indexOf(curCity) > 0) )
            {
                if (show) alert(ShowMsg);
                obj.focus();
                obj.select();
                return false;
            }

            if (idCardLength==18)
            {
                sBirthday=obj.value.substr(6,4)+"-"+Number(obj.value.substr(10,2))+"-"+Number(obj.value.substr(12,2));
                var d = new Date(sBirthday.replace(/-/g,"/"));
                if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))
                {
                    if (show)
                        alert(ShowMsg);
                    obj.focus();
                    obj.select();
                    return false;
                }

                for(var i = 17;i>=0;i --)
                    iSum += (Math.pow(2,i) % 11) * parseInt(obj.value.charAt(17 - i),11);

                if(iSum%11!=1)
                {
                    if (show)
                        alert(ShowMsg);
                    obj.focus();
                    obj.select();
                    return false;
                }

            }
            else if (idCardLength==15)
            {
                sBirthday = "19" + obj.value.substr(6,2) + "-" + Number(obj.value.substr(8,2)) + "-" + Number(obj.value.substr(10,2));
                var d = new Date(sBirthday.replace(/-/g,"/"));
                var dd = d.getFullYear().toString() + "-" + (d.getMonth()+1) + "-" + d.getDate();

                if(sBirthday != dd)
                {
                    if (show)
                        alert(ShowMsg);
                    obj.focus();
                    obj.select();
                    return false;
                }
            }
            return true;
        },


        /**
         * ==================================================================
         * 功能：验证电话号码格式是否正确 提示信息：未输入或输入电话号码格式不正确！ 使用：isPhoneNo(obj,string) 返回：bool
         * ==================================================================
         */
        "isPhoneNo":function(obj,ShowMsg)
        {
            var show = ifShow(ShowMsg);

            var phoneNo = obj.value;
            var Endvalue = true;
            var allowstrlist = "1234567890()-";
            if(phoneNo!="")
            {
                for (i=0;i<phoneNo.length;i++)
                {
                    if (allowstrlist.indexOf(phoneNo.substr(i,1)) == -1)
                    {
                        Endvalue = false;
                        break;
                    }
                }
                if(Endvalue == false)
                {
                    if (show)
                        alert(ShowMsg);
                    obj.focus();
                    obj.select();
                    return false;
                }
            }
            else
            {
                if (show)
                    alert(ShowMsg);
                obj.focus();
                obj.select();
                return false;
            }
            return true;
        },

        /**
         * ==================================================================
         * 功能：将金额小写转化成汉字大写 提示信息：
         * 使用：MoneyToUpper('零,壹,贰,叁,肆,伍,陆,柒,捌,玖,','元,拾,佰,仟,万,拾,佰,仟,亿,拾,角,分,整,零零,零亿,亿万,零万,零元,零角,零分,',obj.value)
         * 返回：string str1 = "零,壹,贰,叁,肆,伍,陆,柒,捌,玖," str2 =
         * "元,拾,佰,仟,万,拾,佰,仟,亿,拾,角,分,整,零零,零亿,亿万,零万,零元,零角,零分,"
         * ==================================================================
         */
        "MoneyToUpper":function(str1,str2,Num)
        {
            var charList1 = str1.split(",");
            var charList2 = str2.split(",");

            for( i = Num.length-1;i>=0;i--)
            {
                // 替换tomoney()中的“,”
                Num = Num.replace(",","");
                // 替换tomoney()中的空格
                Num = Num.replace(" ","");
            }

            // 替换掉可能出现的￥字符
            Num = Num.replace("￥","");
            // 验证输入的字符是否为数字
            if(isNaN(Num))
            {
                alert("Error: Not a Number !");
                return "";
            }

            // ---字符处理完毕，开始转换，转换采用前后两部分分别转换---
            var part = String(Num).split(".");
            var newchar = "";
            // 小数点前进行转化
            for(i = part[0].length - 1; i>= 0 ; i--)
            {
                // 若数量超过拾亿单位，提示
                if(part[0].length > 10)
                {
                    alert("Error Over Max Length !");
                    return "";
                }
                var tmpnewchar = "";

                var perchar = part[0].charAt(i);

                tmpnewchar= charList1[perchar] + tmpnewchar;

                var indx = part[0].length-i-1;
                if (indx == 0 || indx == 4 || indx == 8 || indx == 9)
                {
                    tmpnewchar = tmpnewchar + charList2[indx];
                }
                else
                {
                    if(perchar!=0) tmpnewchar = tmpnewchar + charList2[indx];
                }
                newchar = tmpnewchar + newchar;

            }

            // 小数点之后进行转化
            if( String(Num).indexOf(".") != -1)
            {
                if(part[1].length > 2)
                {
                    part[1] = part[1].substr(0,2);
                }
                for(i=0;i<part[1].length;i++)
                {
                    tmpnewchar = "";
                    perchar = part[1].charAt(i);

                    tmpnewchar = charList1[perchar] + tmpnewchar;
                    if(i==0)tmpnewchar =tmpnewchar + charList2[10];  // 角
                    if(i==1)tmpnewchar = tmpnewchar + charList2[11]; // 分
                    newchar = newchar + tmpnewchar;
                }

            }

            // alert(newchar);

            // 替换 零零 为 零
            while(newchar.search(charList2[13]) != -1)
            {
                newchar = newchar.replace(charList2[13], charList1[0]);  // 零零 to 零
                newchar = newchar.replace(charList2[14], charList2[8]);  // "零亿" to "亿"
                newchar = newchar.replace(charList2[15], charList2[8]);  // "亿万" to "亿"

                newchar = newchar.replace(charList2[16], charList2[4]);  // "零万" to "万"
                newchar = newchar.replace(charList2[17], charList2[0]);  // "零元" to "元"
                newchar = newchar.replace(charList2[18], "");    // "零角" to ""
                newchar = newchar.replace(charList2[19], "");    // "零分" to ""
            }

            newchar = newchar.replace(charList2[14], charList2[8]);  // "零亿" to "亿"
            newchar = newchar.replace(charList2[15], charList2[8]);  // "亿万" to "亿"

            newchar = newchar.replace(charList2[16], charList2[4]);  // "零万" to "万"
            newchar = newchar.replace(charList2[17], charList2[0]);  // "零元" to "元"
            newchar = newchar.replace(charList2[18], "");    // "零角" to ""
            newchar = newchar.replace(charList2[19], "");    // "零分" to ""

            newchar = newchar + charList2[12];

            // 0.6元的情况
            if(newchar.indexOf(charList2[0]) == 0)
                newchar = newchar.replace(charList2[0],"");

            return newchar;
        },

    };
</script>
<!--ms.web.js-->
<script>
    // JavaScript Document
    (function($) {

        /**
         * ajax提交表单
         *
         * @form 表单 格式:#表单id
         * @config 配置扩展用,可包含参数:func,回调方法
         */
        $.fn.postForm = function(form, config) {
            var target = $(this);
            if (isEmpty($(form).attr("action")) && isEmpty(config.action)) {
                alert("配置错误：from表单不存在action属性");
                return;
            }
            var func;
            var action = $(form).attr("action");
            var data_type = "json";
            if (config != undefined) {
                if (config.func != undefined) {
                    func = config.func;
                }
                if (config.action != undefined) {
                    action = config.action;
                }
            }
            $.ajax({
                type : "POST",
                url : action,
                dataType : data_type,
                data : $(form).serialize(),
                beforeSend : function() {
                    target.attr("disabled", true);
                },
                success : function(data) {
                    if (typeof (func) == "string") {
                        eval(func + "(data)");
                    } else if (typeof (func) == "function") {
                        func.call(this, data);
                    }
                    target.removeAttr("disabled");
                }
            });
        }

        /**
         * 发起ajax连接请求
         *
         * @config(优先) 配置扩展用,可包含参数:func,回调方法
         *             config格式：{url:请求地址,data:请求参数,loadingText:加载时文字}
         *             调用该方法的元素必须存在data-ajax-url参数； 参数：data-ajax-url必须
         *             data-ajax-data 可选
         */
        $.fn.request = function(config) {
            var target = $(this);
            if (isEmpty(target.attr("data-ajax-url"))
                    && isEmpty(config.url)) {
                alert(target.selector + "配置错误：data-ajax-url属性不存在");
                return;
            }
            var method = "POST";
            var data_type = "json";
            var func = null;
            var _url = isEmpty(target.attr("data-ajax-url")) ? null
                    : target.attr("data-ajax-url");// 请求地址
            var _data = isEmpty(target.attr("data-ajax-data")) ? null
                    : target.attr("data-ajax-data");// 请求参数
            var _loadingText = isEmpty(target
                    .attr("data-ajax-loading-text")) ? null : target
                    .attr("data-ajax-loading-text");// 加载状态;
            var data_type = isEmpty(target.attr("data-ajax-type")) ? null
                    : target.attr("data-ajax-type");// 返回数据类型
            var text = target.text();
            if (config != undefined) {
                // 请求方法
                if (config.method != undefined) {
                    var _method = config.method;
                    if (_method.toLowerCase() != "post"
                            || _method.toLowerCase() != "get") {
                        method = _method;
                    }
                }
                // 回调方法
                if (config.func != undefined) {
                    func = config.func;
                }
                // 返回数据类型
                if (config.type != undefined) {
                    var _type = config.type.toLowerCase();
                    if (_type == "xml" || _type == "html"
                            || _type == "script" || _type == "jsonp"
                            || _type == "json" || _type == "text") {
                        data_type = _type;
                    }

                }
                if (config.url != undefined) {
                    _url = config.url;
                }
                if (config.data != undefined) {
                    _data = config.data;
                }
                if (config.loadingText != undefined) {
                    _loadingText = config.loadingText;
                }
            }
            $.ajax({
                type : method,
                url : _url,
                dataType : data_type,
                data : _data,
                beforeSend : function() {
                    if (target[0].nodeName == "INPUT") {
                        if (!isEmpty(_loadingText)) {
                            target.text(_loadingText);
                        }
                        target.attr("disabled", true);
                    }

                },
                success : function(data) {

                    if (typeof (func) == "string") {
                        eval(func + "(data)");
                    } else if (typeof (func) == "function") {
                        func.call(this, data);
                    }
                    if (target[0].nodeName == "INPUT") {
                        target.removeAttr("disabled");
                        target.text(text);
                    }
                }
            });
        },

                $.fn.noDataMsg = function(config) {
                    if (config != undefined) {

                    }
                }

        /**
         * 判断是否为空， target:判断对象 message:提示信息 true:为空 false:不为空
         */
        function isEmpty(target, message) {
            if (target == undefined || target == null || target.trim() == ""
                    || target.trim().length == 0) {
                if (message != undefined) {
                    alert(message);
                }
                return true;
            }
            return false;
        }

    })(jQuery);

    var Ms = {
        _target : this,
        "msg" : function(str, url) { // 提示消息 Ms.msg()调用
            var obj = $("<div class='ms-msg'></div>");
            $("body").append(obj);
            obj.html(str).show();
            obj.animate({
                opacity : 1,
            }, 500, 'ease', function() {
                $(this).animate({
                    opacity : 0,
                }, 800, 'ease', function() {
                    if (typeof (url) != "undefined") {
                        _target.loadUrl(url);
                    }
                });
            });
        },
        "loadUrl" : function(url) {
            location.href = url;
        },
        "post" : function(url, params, func) { // 会员中心ajax请求类
            $.ajax({
                type : "POST",
                url : url,
                dataType : 'json',
                data : params,
                beforeSend : function() {
                    try {
                        _target.msg("加载中...");
                    } catch (e) {

                    }
                },
                success : function(json) {
                    func(json);
                },
                error : function(xhr, type) { // 服务器异常提示
                    try {
                        _target.msg("服务器繁忙稍后重试！");
                    } catch (e) {

                    }
                }
            });
        },
        "get" : function(url, params, func) { // 会员中心ajax请求类
            $.ajax({
                type : "GET",
                url : url,
                dataType : 'json',
                data : params,
                beforeSend : function() {
                    try{
                        _target.msg("加载中...");
                    }catch(e){}
                },

                success : function(json) {
                    func(json);
                },
                error : function(xhr, type) { // 服务器异常提示
                    try{
                        _target.msg("服务器繁忙稍后重试！");
                    }catch(e){}
                }
            });
        },
        "load" : function(url, method, params, func) { // 非会员中心ajax请求类
            $.ajax({
                type : method,
                url : url,
                dataType : 'json',
                data : params,
                beforeSend : function() {
                    _target.msg("加载中...");
                },
                success : function(json) {
                    if (func != null && func != undefined) {
                        func(json);
                    }
                },
                error : function(xhr, type) { // 服务器异常提示
                    _target.msg("服务器繁忙稍后重试！");
                }
            });
        },
        "setCookie" : function(key, value, time) { // 依赖zepto.cookie.min.js
            // time单位为天数字
            $.fn.cookie(key, value, {
                path : '/',
                expires : time
            });
        },
        "getCookie" : function(key) { // 读取cookie
            return decodeURIComponent($.fn.cookie(key));
        },
        "delCookie" : function(key) {
            $.fn.cookie(key, null);
        },
        "queryString" : function(param) {
            var svalue = location.search.match(new RegExp("[\?\&]" + param
                    + "=([^\&]*)(\&?)", "i"));
            return svalue ? svalue[1] : svalue;
        },
        "initModal" : function() { // 初始化模态框
            // 弹出框处理
            if ($("*[data-toggle='modal']").size() > 0) {
                $("*[data-toggle='modal']").each(
                        function(index) {
                            $("body").on(
                                    "tap",
                                    "[data-target=\"" + $(this).attr("data-target")
                                    + "\"]",
                                    function() {
                                        if ($(this).attr("data-target") != "") {
                                            openModal($(this).attr("data-target"),
                                                    w, h);
                                        }
                                    })
                        });
            }

            function openModal(modalId, w, h) {

                $(modalId).show();
                Ms.init(w, h);

                if (!$(modalId).parent().hasClass("modalMask")) {
                    $(modalId)
                            .wrap(
                                    "<div class='modalMask' style='width:"
                                    + w
                                    + "px;height:"
                                    + h
                                    + "px;position: absolute;background:rgba(0, 0, 0, 0.6) none repeat scroll 0 0 !important;filter:Alpha(opacity=80); background:#fff;z-index: 9997;top: 0;'>");
                } else {
                    $(modalId).parent().show();
                }
                $(modalId).find(".ms-modal-button").css("line-height", "200%");
                $(modalId).css(
                        "margin-left",
                        ($(modalId).parent().width() - $(modalId).width()) / 2
                        + "px");
                $(modalId).css("margin-top", "10%");
                $(modalId).on("tap", ".close", function() {
                    // $(modalId).hide();
                    // $(modalId).unwrap().parent();
                    hideModal(modalId);
                })
            }

            function hideModal(modalId) {

                $(modalId).parent().hide();
                $(modalId).hide();
            }
        },
        "browser" : {
            versions : function() {
                var u = navigator.userAgent, app = navigator.appVersion;
                return {
                    android4 : u.indexOf('Android 4') > -1
                    && u.indexOf('Linux') > -1,
                    android2 : u.indexOf('Android 2') > -1
                    && u.indexOf('Linux') > -1,
                    iPhone : u.indexOf('iPhone') > -1,
                    iPad : u.indexOf('iPad') > -1,
                    iPod : u.indexOf('iPod') > -1,
                };
            }(),
            language : (navigator.browserLanguage || navigator.language)
                    .toLowerCase()
        }
    };
    var ms = Ms;

</script>

<!--ms.page.js-->
<script>
    //后台分页js
    (function($){

        var methods = {
            init: function(options) {
                var o = $.extend({
                    items: 1,//总数量
                    itemsOnPage: 1,//每页显示数量
                    pages: 1,//总页数
                    displayedPages: 5,//显示页数
                    edges: 3,//边界显示页数
                    currentPage: 1,
                    hrefTextPrefix: '#page-',//分页链接地址的的前缀
                    hrefTextSuffix: '',
                    prevText: '上一页',//上一页显示文字
                    nextText: '下一页',
                    ellipseText: '&hellip;',
                    cssStyle: 'light-theme',//分页使用的样式
                    labelMap: [],//分页显示的信息
                    selectOnClick: true,
                    onPageClick: function(pageNumber, event) {
                        //点击分页进行的操作
                    },
                    onInit: function() {
                        //初始化时进行的操作
                    }
                }, options || {});

                var self = this;

                o.pages = o.pages ? o.pages : Math.ceil(o.items / o.itemsOnPage) ? Math.ceil(o.items / o.itemsOnPage) : 1;
                o.currentPage = o.currentPage - 1;
                o.halfDisplayed = o.displayedPages / 2;

                this.each(function() {
                    self.addClass(o.cssStyle).data('pagination', o);
                    methods._draw.call(self);
                });

                o.onInit();

                return this;
            },

            selectPage: function(page) {
                methods._selectPage.call(this, page - 1);
                return this;
            },

            prevPage: function() {
                var o = this.data('pagination');
                if (o.currentPage > 0) {
                    methods._selectPage.call(this, o.currentPage - 1);
                }
                return this;
            },

            nextPage: function() {
                var o = this.data('pagination');
                if (o.currentPage < o.pages - 1) {
                    methods._selectPage.call(this, o.currentPage + 1);
                }
                return this;
            },

            getPagesCount: function() {
                return this.data('pagination').pages;
            },

            getCurrentPage: function () {
                return this.data('pagination').currentPage + 1;
            },

            destroy: function(){
                this.empty();
                return this;
            },

            drawPage: function (page) {
                var o = this.data('pagination');
                o.currentPage = page - 1;
                this.data('pagination', o);
                methods._draw.call(this);
                return this;
            },

            redraw: function(){
                methods._draw.call(this);
                return this;
            },

            disable: function(){
                var o = this.data('pagination');
                o.disabled = true;
                this.data('pagination', o);
                methods._draw.call(this);
                return this;
            },

            enable: function(){
                var o = this.data('pagination');
                o.disabled = false;
                this.data('pagination', o);
                methods._draw.call(this);
                return this;
            },

            updateItems: function (newItems) {
                var o = this.data('pagination');
                o.items = newItems;
                o.pages = methods._getPages(o);
                this.data('pagination', o);
                methods._draw.call(this);
            },

            updateItemsOnPage: function (itemsOnPage) {
                var o = this.data('pagination');
                o.itemsOnPage = itemsOnPage;
                o.pages = methods._getPages(o);
                this.data('pagination', o);
                methods._selectPage.call(this, 0);
                return this;
            },

            _draw: function() {
                var	o = this.data('pagination'),
                        interval = methods._getInterval(o),
                        i,
                        tagName;

                methods.destroy.call(this);

                tagName = (typeof this.prop === 'function') ? this.prop('tagName') : this.attr('tagName');

                var $panel = tagName === 'UL' ? this : $('<ul></ul>').appendTo(this);

                //上一页
                if (o.prevText) {
                    methods._appendItem.call(this, o.currentPage - 1, {text: o.prevText, classes: 'prev'});
                }

                // 开始位置的分页
                if (interval.start > 0 && o.edges > 0) {
                    var end = Math.min(o.edges, interval.start);
                    for (i = 0; i < end; i++) {
                        methods._appendItem.call(this, i);
                    }
                    if (o.edges < interval.start && (interval.start - o.edges != 1)) {
                        $panel.append('<li class="disabled"><a>' + o.ellipseText + '</a></span></li>');
                    } else if (interval.start - o.edges == 1) {
                        methods._appendItem.call(this, o.edges);
                    }
                }

                // 中间段的分页显示
                for (i = interval.start; i < interval.end; i++) {
                    methods._appendItem.call(this, i);
                }

                //结束的分页显示
                if (interval.end < o.pages && o.edges > 0) {
                    if (o.pages - o.edges > interval.end && (o.pages - o.edges - interval.end != 1)) {
                        $panel.append('<li class="disabled"><a>' + o.ellipseText + '</a></li>');
                    } else if (o.pages - o.edges - interval.end == 1) {
                        methods._appendItem.call(this, interval.end++);
                    }
                    var begin = Math.max(o.pages - o.edges, interval.end);
                    for (i = begin; i < o.pages; i++) {
                        methods._appendItem.call(this, i);
                    }
                }

                // Generate Next link
                if (o.nextText) {
                    methods._appendItem.call(this, o.currentPage + 1, {text: o.nextText, classes: 'next'});
                }
            },
            //获取总页数
            _getPages: function(o) {
                var pages = Math.ceil(o.items / o.itemsOnPage);
                return pages || 1;
            },
            //获取中间显示的页数
            _getInterval: function(o) {
                return {
                    start: Math.ceil(o.currentPage > o.halfDisplayed ? Math.max(Math.min(o.currentPage - o.halfDisplayed, (o.pages - o.displayedPages)), 0) : 0),
                    end: Math.ceil(o.currentPage > o.halfDisplayed ? Math.min(o.currentPage + o.halfDisplayed, o.pages) : Math.min(o.displayedPages, o.pages))
                };
            },

            _appendItem: function(pageIndex, opts) {
                var self = this, options, $link, o = self.data('pagination'), $linkWrapper = $('<li></li>'), $ul = self.find('ul');

                pageIndex = pageIndex < 0 ? 0 : (pageIndex < o.pages ? pageIndex : o.pages - 1);

                options = {
                    text: pageIndex + 1,
                    classes: ''
                };

                if (o.labelMap.length && o.labelMap[pageIndex]) {
                    options.text = o.labelMap[pageIndex];
                }

                options = $.extend(options, opts || {});

                if (pageIndex == o.currentPage || o.disabled) {
                    if (o.disabled) {
                        $linkWrapper.addClass('disabled');
                    } else {
                        if (!options.classes) {
                            $linkWrapper.addClass('active');
                        }

                    }
                    $link = $('<a target="_self">' + (options.text) + '</a>');
                } else {
                    $link = $('<a  target="_self" href="' + o.hrefTextPrefix + (pageIndex + 1) + o.hrefTextSuffix + '" class="page-link">' + (options.text) + '</a>');
                    $link.click(function(event){
                        return methods._selectPage.call(self, pageIndex, event);
                    });
                }

                if (options.classes) {
                    $link.addClass(options.classes);
                }

                $linkWrapper.append($link);

                if ($ul.length) {
                    $ul.append($linkWrapper);
                } else {
                    self.append($linkWrapper);
                }
            },

            _selectPage: function(pageIndex, event) {
                var o = this.data('pagination');
                o.currentPage = pageIndex;
                if (o.selectOnClick) {
                    methods._draw.call(this);
                }
                return o.onPageClick(pageIndex + 1, event);
            }

        };

        $.fn.pagination = function(method) {

            // Method calling logic
            if (methods[method] && method.charAt(0) != '_') {
                return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
            } else if (typeof method === 'object' || !method) {
                return methods.init.apply(this, arguments);
            } else {
                $.error('Method ' +  method + ' does not exist on jQuery.pagination');
            }

        };

    })(jQuery);

</script>
<!--ms.manager.min.js-->
<script>
    ms.manager={initMenu:function(e){$("#ms-menu-parent-tmpl").tmpl(e).appendTo(".ms-menu"),$(".ms-menu-parent").each(function(s){var n=new Array;for(i=0;i<e.length;i++)e[i].modelModelId==$(this).data("model-id")&&n.push(e[i]);$("#ms-menu-child-tmpl").tmpl(n).appendTo($(this).find("ul:first"))}),$('[data-toggle="tooltip"]').tooltip(),$("body").delegate(".ms-menu-parent > div","click",function(){$(this).siblings(".ms-menu-child").hasClass("openMenuChild")?($(this).removeClass("nav-title"),$(this).addClass("ms-menu-parent-header"),$(this).siblings(".ms-menu-child").slideUp(),$(this).siblings(".ms-menu-child").removeClass("openMenuChild")):($(".ms-menu-parent").children("div").removeClass("nav-title"),$(".ms-menu-parent").children("div").addClass("ms-menu-parent-header"),$(".ms-menu-child").slideUp(),$(".ms-menu-child").removeClass("openMenuChild"),$(this).addClass("nav-title"),$(this).removeClass("ms-menu-parent-header"),$(this).siblings(".ms-menu-child").slideDown(),$(this).siblings(".ms-menu-child").addClass("openMenuChild"),$(this).siblings(".ms-menu-child > li").click(function(){$(this).siblings(".ms-menu-child").show(),$(".ms-menu-parent").siblings(".ms-menu-child > li >a").css("color","#e4e4e4"),$(this).siblings(".ms-menu-child > li > a").css("color","#1CAF9A")}))})}},$(function(){$("body").delegate(".ms-menu-parent","mouseover",function(){$(this).children("div").addClass("nav-title"),$(this).children("div").removeClass("ms-menu-parent-header")}),$("body").delegate(".ms-menu-parent","mouseout",function(){$(this).children(".ms-menu-child").hasClass("openMenuChild")?($(this).children("div").addClass("nav-title"),$(this).children("div").removeClass("ms-menu-parent-header")):($(this).children("div").removeClass("nav-title"),$(this).children("div").addClass("ms-menu-parent-header"))}),$('[data-toggle="tooltip"]').tooltip()});
</script>
<!--mstore-client.min.js-->
<script>

    $(function(){null!=$.cookie()&&"ms_value"!=$.cookie("ms_cookie")&&($(".ms-update").after('<div class="popover bottom ms-top-menuchildTit"><div class="arrow"></div><h3 class="popover-title">MStore<button type="button" class="close" style="line-height: 16px;" data-dismiss="popover" aria-hidden="true">×</button></h3><div class="popover-content"><p>为您提供大量优质插件及免费企业模板</p></div></div>'),$("#ms-href-tool .badge").hide(),$("#ms-href-tool .badge").text("")),$(".close").click(function(){$(this).parent().parent(".popover").remove()});var e=ms.queryString("url");e&&$("#mainFrame").attr("src",e);var o=base+"/manager";"undefined"!=typeof managerPath&&(o=managerPath),$.ajax({type:"post",dataType:"json",url:o+"/upgrader/sync.do",beforeSend:function(){},success:function(e){var o=$.parseJSON(e.resultMsg);o.syncNum>0&&$("#ms-href-tool .badge").html(o.syncNum),$(".ms-update").click(function(){$("#mainFrame").attr("src",o.syncStoreUrl+"?c="+encodeURIComponent(location.href)),$.cookie("ms_cookie","ms_value",{expires:7})})}})});
</script>

<!--
<link rel="stylesheet" type="text/css" href="../../css/ms.manager.min.css" media="all" />
-->

<link rel="stylesheet" type="text/css" href="${static}/skin/manager/${manager_ui}/css/ms.manager.min.css" media="all" />


<#assign skin_manager_logo="${static}/skin/manager/${manager_ui}/images/logo.png"/>
<#assign skin_manager_loadding="${static}/skin/manager/${manager_ui}/images/loading.gif"/>



<#include "${managerViewPath}/include/macro.ftl"/>
<script>
    var basePath = "${basePath}";
    var base = "${base}";
    var managerPath = "${managerPath}";
    var static = "http://cdn.mingsoft.net";
    $(function() {
        //启用工具提示
        //	$("[data-toggle='tooltip']").tooltip();
        //	$("[data-toggle='popover']").popover({html:true});
    })
    <#if manager_session?exists>
    var websiteId= "${manager_session.basicId?default('0')}" ;
    </#if>
</script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="${base}/https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="${base}/https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<script type="text/javascript" charset="utf-8" src="${base}/static/plugins/ueditor/1.4.3.1/ueditor.parse.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/static/plugins/ueditor/1.4.3.1/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/static/plugins/ueditor/1.4.3.1/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/static/plugins/ueditor/1.4.3.1/lang/zh-cn/zh-cn.js"></script>
