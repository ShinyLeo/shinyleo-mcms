if(typeof(btoa)=="undefined"){btoa=function(){var base64EncodeChars='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.split('');return function(str){var out,i,j,len,r,l,c;i=j=0;len=str.length;r=len%3;len=len-r;l=(len/3)<<2;if(r>0){l+=4};out=new Array(l);while(i<len){c=str.charCodeAt(i++)<<16|str.charCodeAt(i++)<<8|str.charCodeAt(i++);out[j++]=base64EncodeChars[c>>18]+base64EncodeChars[c>>12&0x3f]+base64EncodeChars[c>>6&0x3f]+base64EncodeChars[c&0x3f]};if(r==1){c=str.charCodeAt(i++);out[j++]=base64EncodeChars[c>>2]+base64EncodeChars[(c&0x03)<<4]+"=="}else if(r==2){c=str.charCodeAt(i++)<<8|str.charCodeAt(i++);out[j++]=base64EncodeChars[c>>10]+base64EncodeChars[c>>4&0x3f]+base64EncodeChars[(c&0x0f)<<2]+"="};return out.join('')}}()};if(typeof(atob)=="undefined"){atob=function(){var base64DecodeChars=[-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,62,-1,-1,-1,63,52,53,54,55,56,57,58,59,60,61,-1,-1,-1,-1,-1,-1,-1,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,-1,-1,-1,-1,-1,-1,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,-1,-1,-1,-1,-1];return function(str){var c1,c2,c3,c4;var i,j,len,r,l,out;len=str.length;if(len%4!=0){return''};if(/[^ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789\+\/\=]/.test(str)){return''};if(str.charAt(len-2)=='='){r=1}else if(str.charAt(len-1)=='='){r=2}else{r=0};l=len;if(r>0){l-=4};l=(l>>2)*3+r;out=new Array(l);i=j=0;while(i<len){c1=base64DecodeChars[str.charCodeAt(i++)];if(c1==-1)break;c2=base64DecodeChars[str.charCodeAt(i++)];if(c2==-1)break;out[j++]=String.fromCharCode((c1<<2)|((c2&0x30)>>4));c3=base64DecodeChars[str.charCodeAt(i++)];if(c3==-1)break;out[j++]=String.fromCharCode(((c2&0x0f)<<4)|((c3&0x3c)>>2));c4=base64DecodeChars[str.charCodeAt(i++)];if(c4==-1)break;out[j++]=String.fromCharCode(((c3&0x03)<<6)|c4)};return out.join('')}}()};String.prototype.md5=function(){var add32=function(x,y){var lsw=(x&0xFFFF)+(y&0xFFFF);var msw=(x>>16)+(y>>16)+(lsw>>16);return(msw<<16)|(lsw&0xFFFF)};var bitrol=function(n,c){return(n<<c)|(n>>>(32-c))};var cmn=function(q,a,b,x,s,t){return add32(bitrol(add32(add32(a,q),add32(x,t)),s),b)};var ff=function(a,b,c,d,x,s,t){return cmn((b&c)|((~b)&d),a,b,x,s,t)};var gg=function(a,b,c,d,x,s,t){return cmn((b&d)|(c&(~d)),a,b,x,s,t)};var hh=function(a,b,c,d,x,s,t){return cmn(b^c^d,a,b,x,s,t)};var ii=function(a,b,c,d,x,s,t){return cmn(c^(b|(~d)),a,b,x,s,t)};var pack=function(b){var l=b.length<<2;var s=new Array(l);for(var i=0;i<l;i++){s[i]=String.fromCharCode((b[i>>2]>>>((i%4)<<3))&255)};return s.join("")};var unpack=function(s){var l=s.length;var b=new Array();for(var i=0;i<l;i++){b[i>>2]|=(s.charCodeAt(i)&0xff)<<((i%4)<<3)};return b};var x=unpack(this);var len=this.length<<3;x[len>>5]|=0x80<<((len)%32);x[(((len+64)>>>9)<<4)+14]=len;var a=1732584193;var b=-271733879;var c=-1732584194;var d=271733878;for(var i=0;i<x.length;i+=16){var olda=a;var oldb=b;var oldc=c;var oldd=d;a=ff(a,b,c,d,x[i+0],7,-680876936);d=ff(d,a,b,c,x[i+1],12,-389564586);c=ff(c,d,a,b,x[i+2],17,606105819);b=ff(b,c,d,a,x[i+3],22,-1044525330);a=ff(a,b,c,d,x[i+4],7,-176418897);d=ff(d,a,b,c,x[i+5],12,1200080426);c=ff(c,d,a,b,x[i+6],17,-1473231341);b=ff(b,c,d,a,x[i+7],22,-45705983);a=ff(a,b,c,d,x[i+8],7,1770035416);d=ff(d,a,b,c,x[i+9],12,-1958414417);c=ff(c,d,a,b,x[i+10],17,-42063);b=ff(b,c,d,a,x[i+11],22,-1990404162);a=ff(a,b,c,d,x[i+12],7,1804603682);d=ff(d,a,b,c,x[i+13],12,-40341101);c=ff(c,d,a,b,x[i+14],17,-1502002290);b=ff(b,c,d,a,x[i+15],22,1236535329);a=gg(a,b,c,d,x[i+1],5,-165796510);d=gg(d,a,b,c,x[i+6],9,-1069501632);c=gg(c,d,a,b,x[i+11],14,643717713);b=gg(b,c,d,a,x[i+0],20,-373897302);a=gg(a,b,c,d,x[i+5],5,-701558691);d=gg(d,a,b,c,x[i+10],9,38016083);c=gg(c,d,a,b,x[i+15],14,-660478335);b=gg(b,c,d,a,x[i+4],20,-405537848);a=gg(a,b,c,d,x[i+9],5,568446438);d=gg(d,a,b,c,x[i+14],9,-1019803690);c=gg(c,d,a,b,x[i+3],14,-187363961);b=gg(b,c,d,a,x[i+8],20,1163531501);a=gg(a,b,c,d,x[i+13],5,-1444681467);d=gg(d,a,b,c,x[i+2],9,-51403784);c=gg(c,d,a,b,x[i+7],14,1735328473);b=gg(b,c,d,a,x[i+12],20,-1926607734);a=hh(a,b,c,d,x[i+5],4,-378558);d=hh(d,a,b,c,x[i+8],11,-2022574463);c=hh(c,d,a,b,x[i+11],16,1839030562);b=hh(b,c,d,a,x[i+14],23,-35309556);a=hh(a,b,c,d,x[i+1],4,-1530992060);d=hh(d,a,b,c,x[i+4],11,1272893353);c=hh(c,d,a,b,x[i+7],16,-155497632);b=hh(b,c,d,a,x[i+10],23,-1094730640);a=hh(a,b,c,d,x[i+13],4,681279174);d=hh(d,a,b,c,x[i+0],11,-358537222);c=hh(c,d,a,b,x[i+3],16,-722521979);b=hh(b,c,d,a,x[i+6],23,76029189);a=hh(a,b,c,d,x[i+9],4,-640364487);d=hh(d,a,b,c,x[i+12],11,-421815835);c=hh(c,d,a,b,x[i+15],16,530742520);b=hh(b,c,d,a,x[i+2],23,-995338651);a=ii(a,b,c,d,x[i+0],6,-198630844);d=ii(d,a,b,c,x[i+7],10,1126891415);c=ii(c,d,a,b,x[i+14],15,-1416354905);b=ii(b,c,d,a,x[i+5],21,-57434055);a=ii(a,b,c,d,x[i+12],6,1700485571);d=ii(d,a,b,c,x[i+3],10,-1894986606);c=ii(c,d,a,b,x[i+10],15,-1051523);b=ii(b,c,d,a,x[i+1],21,-2054922799);a=ii(a,b,c,d,x[i+8],6,1873313359);d=ii(d,a,b,c,x[i+15],10,-30611744);c=ii(c,d,a,b,x[i+6],15,-1560198380);b=ii(b,c,d,a,x[i+13],21,1309151649);a=ii(a,b,c,d,x[i+4],6,-145523070);d=ii(d,a,b,c,x[i+11],10,-1120210379);c=ii(c,d,a,b,x[i+2],15,718787259);b=ii(b,c,d,a,x[i+9],21,-343485551);a=add32(a,olda);b=add32(b,oldb);c=add32(c,oldc);d=add32(d,oldd)};return pack([a,b,c,d])};String.prototype.toUTF8=function(){var str=this;if(str.match(/^[\x00-\x7f]*$/)!=null){return str.toString()};var out,i,j,len,c,c2;out=[];len=str.length;for(i=0,j=0;i<len;i++,j++){c=str.charCodeAt(i);if(c<=0x7f){out[j]=str.charAt(i)}else if(c<=0x7ff){out[j]=String.fromCharCode(0xc0|(c>>>6),0x80|(c&0x3f))}else if(c<0xd800||c>0xdfff){out[j]=String.fromCharCode(0xe0|(c>>>12),0x80|((c>>>6)&0x3f),0x80|(c&0x3f))}else{if(++i<len){c2=str.charCodeAt(i);if(c<=0xdbff&&0xdc00<=c2&&c2<=0xdfff){c=((c&0x03ff)<<10|(c2&0x03ff))+0x010000;if(0x010000<=c&&c<=0x10ffff){out[j]=String.fromCharCode(0xf0|((c>>>18)&0x3f),0x80|((c>>>12)&0x3f),0x80|((c>>>6)&0x3f),0x80|(c&0x3f))}else{out[j]='?'}}else{i--;out[j]='?'}}else{i--;out[j]='?'}}};return out.join('')};String.prototype.toUTF16=function(){var str=this;if((str.match(/^[\x00-\x7f]*$/)!=null)||(str.match(/^[\x00-\xff]*$/)==null)){return str.toString()};var out,i,j,len,c,c2,c3,c4,s;out=[];len=str.length;i=j=0;while(i<len){c=str.charCodeAt(i++);switch(c>>4){case 0:case 1:case 2:case 3:case 4:case 5:case 6:case 7:out[j++]=str.charAt(i-1);break;case 12:case 13:c2=str.charCodeAt(i++);out[j++]=String.fromCharCode(((c&0x1f)<<6)|(c2&0x3f));break;case 14:c2=str.charCodeAt(i++);c3=str.charCodeAt(i++);out[j++]=String.fromCharCode(((c&0x0f)<<12)|((c2&0x3f)<<6)|(c3&0x3f));break;case 15:switch(c&0xf){case 0:case 1:case 2:case 3:case 4:case 5:case 6:case 7:c2=str.charCodeAt(i++);c3=str.charCodeAt(i++);c4=str.charCodeAt(i++);s=((c&0x07)<<18)|((c2&0x3f)<<12)|((c3&0x3f)<<6)|(c4&0x3f)-0x10000;if(0<=s&&s<=0xfffff){out[j++]=String.fromCharCode(((s>>>10)&0x03ff)|0xd800,(s&0x03ff)|0xdc00)}else{out[j++]='?'}break;case 8:case 9:case 10:case 11:i+=4;out[j++]='?';break;case 12:case 13:i+=5;out[j++]='?';break}}};return out.join('')};var PHPSerializer=(function(){function freeEval(s){return eval(s)};var prototypePropertyOfArray=function(){var result={};for(var p in[]){result[p]=true};return result}();var prototypePropertyOfObject=function(){var result={};for(var p in{}){result[p]=true};return result}();return{serialize:function(o){var p=0,sb=[],ht=[],hv=1;function getClassName(o){if(typeof(o)=='undefined'||typeof(o.constructor)=='undefined')return'';var c=o.constructor.toString();c=c.substr(0,c.indexOf('(')).replace(/(^\s*function\s*)|(\s*$)/ig,'').toUTF8();return((c=='')?'Object':c)};function isInteger(n){var i,s=n.toString(),l=s.length;if(l>11)return false;for(i=(s.charAt(0)=='-')?1:0;i<l;i++){switch(s.charAt(i)){case'0':case'1':case'2':case'3':case'4':case'5':case'6':case'7':case'8':case'9':break;default:return false}};return!(n<-2147483648||n>2147483647)};function inHashTable(o){for(var i=0;i<ht.length;i++)if(ht[i]===o)return i;return false};function serializeNull(){sb[p++]='N;'};function serializeBoolean(b){sb[p++]=(b?'b:1;':'b:0;')};function serializeInteger(i){sb[p++]='i:'+i+';'};function serializeDouble(d){if(isNaN(d))d='NAN';else if(d==Number.POSITIVE_INFINITY)d='INF';else if(d==Number.NEGATIVE_INFINITY)d='-INF';sb[p++]='d:'+d+';'};function serializeString(s){var utf8=s.toUTF8();sb[p++]='s:'+utf8.length+':"';sb[p++]=utf8;sb[p++]='";'};function serializeDate(dt){sb[p++]='O:11:"PHPRPC_Date":7:{';sb[p++]='s:4:"year";';serializeInteger(dt.getFullYear());sb[p++]='s:5:"month";';serializeInteger(dt.getMonth()+1);sb[p++]='s:3:"day";';serializeInteger(dt.getDate());sb[p++]='s:4:"hour";';serializeInteger(dt.getHours());sb[p++]='s:6:"minute";';serializeInteger(dt.getMinutes());sb[p++]='s:6:"second";';serializeInteger(dt.getSeconds());sb[p++]='s:11:"millisecond";';serializeInteger(dt.getMilliseconds());sb[p++]='}'};function serializeArray(a){sb[p++]='a:';var k,lp=p;sb[p++]=0;sb[p++]=':{';for(k in a){if((typeof(a[k])!='function')&&!prototypePropertyOfArray[k]){isInteger(k)?serializeInteger(k):serializeString(k);serialize(a[k]);sb[lp]++}}sb[p++]='}'};function serializeObject(o){var cn=getClassName(o);if(cn=='')serializeNull();else if(typeof(o.serialize)!='function'){sb[p++]='O:'+cn.length+':"'+cn+'":';var lp=p;sb[p++]=0;sb[p++]=':{';var k;if(typeof(o.__sleep)=='function'){var a=o.__sleep();for(k in a){serializeString(a[k]);serialize(o[a[k]]);sb[lp]++}}else{for(k in o){if(typeof(o[k])!='function'&&!prototypePropertyOfObject[k]){serializeString(k);serialize(o[k]);sb[lp]++}}};sb[p++]='}'}else{var cs=o.serialize();sb[p++]='C:'+cn.length+':"'+cn+'":'+cs.length+':{'+cs+'}'}};function serializePointRef(R){sb[p++]='R:'+R+';'};function serializeRef(r){sb[p++]='r:'+r+';'};function serialize(o){if(typeof(o)=="undefined"||o==null||o.constructor==Function){hv++;serializeNull();return};var className=getClassName(o);switch(o.constructor){case Boolean:{hv++;serializeBoolean(o);break}case Number:{hv++;isInteger(o)?serializeInteger(o):serializeDouble(o);break}case String:{hv++;serializeString(o);break}case Date:{hv+=8;serializeDate(o);break}default:{if(className=="Object"||o.constructor==Array){var r=inHashTable(o);if(r){serializePointRef(r)}else{ht[hv++]=o;serializeArray(o)}break}else{var r=inHashTable(o);if(r){hv++;serializeRef(r)}else{ht[hv++]=o;serializeObject(o)}}}}};serialize(o);return sb.join('')},unserialize:function(ss){var p=0,ht=[],hv=1;function unserializeNull(){p++;return null};function unserializeBoolean(){p++;var b=(ss.charAt(p++)=='1');p++;return b};function unserializeInteger(){p++;var i=parseInt(ss.substring(p,p=ss.indexOf(';',p)));p++;return i};function unserializeDouble(){p++;var d=ss.substring(p,p=ss.indexOf(';',p));switch(d){case'NAN':d=NaN;break;case'INF':d=Number.POSITIVE_INFINITY;break;case'-INF':d=Number.NEGATIVE_INFINITY;break;default:d=parseFloat(d)}p++;return d};function unserializeString(){p++;var l=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;var s=ss.substring(p,p+=l).toUTF16();p+=2;return s};function unserializeEscapedString(len){p++;var l=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;var i,sb=new Array(l);for(i=0;i<l;i++){if((sb[i]=ss.charAt(p++))=='\\'){sb[i]=String.fromCharCode(parseInt(ss.substring(p,p+=len),16))}}p+=2;return sb.join('')};function unserializeArray(){p++;var n=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;var i,k,a=[];ht[hv++]=a;for(i=0;i<n;i++){switch(ss.charAt(p++)){case'i':k=unserializeInteger();break;case's':k=unserializeString();break;case'S':k=unserializeEscapedString(2);break;case'U':k=unserializeEscapedString(4);break;default:return false}a[k]=unserialize()};p++;return a};function unserializeDate(n){var i,k,a={};for(i=0;i<n;i++){switch(ss.charAt(p++)){case's':k=unserializeString();break;case'S':k=unserializeEscapedString(2);break;case'U':k=unserializeEscapedString(4);break;default:return false}if(ss.charAt(p++)=='i'){a[k]=unserializeInteger()}else{return false}};p++;var dt=new Date(a.year,a.month-1,a.day,a.hour,a.minute,a.second,a.millisecond);ht[hv++]=dt;ht[hv++]=a.year;ht[hv++]=a.month;ht[hv++]=a.day;ht[hv++]=a.hour;ht[hv++]=a.minute;ht[hv++]=a.second;ht[hv++]=a.millisecond;return dt};function unserializeObject(){p++;var l=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;var cn=ss.substring(p,p+=l).toUTF16();p+=2;var n=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;if(cn=="PHPRPC_Date"){return unserializeDate(n)};var i,k,o=createObjectOfClass(cn);ht[hv++]=o;for(i=0;i<n;i++){switch(ss.charAt(p++)){case's':k=unserializeString();break;case'S':k=unserializeEscapedString(2);break;case'U':k=unserializeEscapedString(4);break;default:return false};if(k.charAt(0)=='\0'){k=k.substring(k.indexOf('\0',1)+1,k.length)};o[k]=unserialize()};p++;if(typeof(o.__wakeup)=='function')o.__wakeup();return o};function unserializeCustomObject(){p++;var l=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;var cn=ss.substring(p,p+=l).toUTF16();p+=2;var n=parseInt(ss.substring(p,p=ss.indexOf(':',p)));p+=2;var o=createObjectOfClass(cn);ht[hv++]=o;if(typeof(o.unserialize)!='function')p+=n;else o.unserialize(ss.substring(p,p+=n));p++;return o};function unserializeRef(){p++;var r=parseInt(ss.substring(p,p=ss.indexOf(';',p)));p++;return ht[r]};function getObjectOfClass(cn,poslist,i,c){if(i<poslist.length){var pos=poslist[i];cn[pos]=c;var obj=getObjectOfClass(cn,poslist,i+1,'.');if(i+1<poslist.length){if(obj==null){obj=getObjectOfClass(cn,poslist,i+1,'_')}};return obj};var classname=cn.join('');try{return freeEval('new '+classname+'()')}catch(e){return null}};function createObjectOfClass(classname){if(freeEval('typeof('+classname+') == "function"')){return freeEval('new '+classname+'()')};var poslist=[];var pos=classname.indexOf("_");while(pos>-1){poslist[poslist.length]=pos;pos=classname.indexOf("_",pos+1)};if(poslist.length>0){var cn=classname.split('');var obj=getObjectOfClass(cn,poslist,0,'.');if(obj==null){obj=getObjectOfClass(cn,poslist,0,'_')}if(obj!=null){return obj}};return freeEval('new function '+classname+'(){};')};function unserialize(){switch(ss.charAt(p++)){case'N':return ht[hv++]=unserializeNull();case'b':return ht[hv++]=unserializeBoolean();case'i':return ht[hv++]=unserializeInteger();case'd':return ht[hv++]=unserializeDouble();case's':return ht[hv++]=unserializeString();case'S':return ht[hv++]=unserializeEscapedString(2);case'U':return ht[hv++]=unserializeEscapedString(4);case'r':return ht[hv++]=unserializeRef();case'a':return unserializeArray();case'O':return unserializeObject();case'C':return unserializeCustomObject();case'R':return unserializeRef();default:return false}};return unserialize()}}})();var XXTEA=new function(){var delta=0x9E3779B9;function longArrayToString(data,includeLength){var length=data.length;var n=(length-1)<<2;if(includeLength){var m=data[length-1];if((m<n-3)||(m>n))return null;n=m};for(var i=0;i<length;i++){data[i]=String.fromCharCode(data[i]&0xff,data[i]>>>8&0xff,data[i]>>>16&0xff,data[i]>>>24&0xff)};if(includeLength){return data.join('').substring(0,n)}else{return data.join('')}};function stringToLongArray(string,includeLength){var length=string.length;var result=[];for(var i=0;i<length;i+=4){result[i>>2]=string.charCodeAt(i)|string.charCodeAt(i+1)<<8|string.charCodeAt(i+2)<<16|string.charCodeAt(i+3)<<24};if(includeLength){result[result.length]=length};return result};this.encrypt=function(string,key){if(string==""){return""};var v=stringToLongArray(string,true);var k=stringToLongArray(key,false);if(k.length<4){k.length=4};var n=v.length-1;var z=v[n],y=v[0];var mx,e,p,q=Math.floor(6+52/(n+1)),sum=0;while(0<q--){sum=sum+delta&0xffffffff;e=sum>>>2&3;for(p=0;p<n;p++){y=v[p+1];mx=(z>>>5^y<<2)+(y>>>3^z<<4)^(sum^y)+(k[p&3^e]^z);z=v[p]=v[p]+mx&0xffffffff};y=v[0];mx=(z>>>5^y<<2)+(y>>>3^z<<4)^(sum^y)+(k[p&3^e]^z);z=v[n]=v[n]+mx&0xffffffff};return longArrayToString(v,false)};this.decrypt=function(string,key){if(string==""){return""};var v=stringToLongArray(string,false);var k=stringToLongArray(key,false);if(k.length<4){k.length=4};var n=v.length-1;var z=v[n-1],y=v[0];var mx,e,p,q=Math.floor(6+52/(n+1)),sum=q*delta&0xffffffff;while(sum!=0){e=sum>>>2&3;for(p=n;p>0;p--){z=v[p-1];mx=(z>>>5^y<<2)+(y>>>3^z<<4)^(sum^y)+(k[p&3^e]^z);y=v[p]=v[p]-mx&0xffffffff};z=v[n];mx=(z>>>5^y<<2)+(y>>>3^z<<4)^(sum^y)+(k[p&3^e]^z);y=v[0]=v[0]-mx&0xffffffff;sum=sum-delta&0xffffffff};return longArrayToString(v,true)}};var BigInteger=new function(){function mul(a,b){var n=a.length,m=b.length,nm=n+m,i,j,c=Array(nm);for(i=0;i<nm;i++)c[i]=0;for(i=0;i<n;i++){for(j=0;j<m;j++){c[i+j]+=a[i]*b[j];c[i+j+1]+=(c[i+j]>>16)&0xffff;c[i+j]&=0xffff}};return c};function div(a,b,is_mod){var n=a.length,m=b.length,i,j,d,tmp,qq,rr,c=Array();d=Math.floor(0x10000/(b[m-1]+1));a=mul(a,[d]);b=mul(b,[d]);for(j=n-m;j>=0;j--){tmp=a[j+m]*0x10000+a[j+m-1];rr=tmp%b[m-1];qq=Math.round((tmp-rr)/b[m-1]);if(qq==0x10000||(m>1&&qq*b[m-2]>0x10000*rr+a[j+m-2])){qq--;rr+=b[m-1];if(rr<0x10000&&qq*b[m-2]>0x10000*rr+a[j+m-2])qq--};for(i=0;i<m;i++){tmp=i+j;a[tmp]-=b[i]*qq;a[tmp+1]+=Math.floor(a[tmp]/0x10000);a[tmp]&=0xffff};c[j]=qq;if(a[tmp+1]<0){c[j]--;for(i=0;i<m;i++){tmp=i+j;a[tmp]+=b[i];if(a[tmp]>0xffff){a[tmp+1]++;a[tmp]&=0xffff}}}};if(!is_mod)return c;b=Array();for(i=0;i<m;i++)b[i]=a[i];return div(b,[d])};function powmod(a,b,c){var n=b.length,p=[1],i,j,tmp;for(i=0;i<n-1;i++){tmp=b[i];for(j=0;j<0x10;j++){if(tmp&1)p=div(mul(p,a),c,1);tmp>>=1;a=div(mul(a,a),c,1)}};tmp=b[i];while(tmp){if(tmp&1)p=div(mul(p,a),c,1);tmp>>=1;a=div(mul(a,a),c,1)};return p};function zerofill(str,num){var n=num-str.toString().length,i,s='';for(i=0;i<n;i++)s+='0';return s+str};function dec2num(str){var n=str.length,a=[0],i,j,m;n+=4-(n%4);str=zerofill(str,n);n>>=2;for(i=0;i<n;i++){a=mul(a,[10000]);a[0]+=parseInt(str.substr(i<<2,4),10);m=a.length;j=a[m]=0;while(j<m&&a[j]>0xffff){a[j]&=0xffff;j++;a[j]++};while(a.length>1&&!a[a.length-1])a.length--};return a};function num2dec(a){var n=a.length<<1,b=Array(),i;for(i=0;i<n;i++){b[i]=zerofill(div(a,[10000],1)[0],4);a=div(a,[10000])};while(b.length>1&&!parseInt(b[b.length-1],10))b.length--;n=b.length-1;b[n]=parseInt(b[n],10);b=b.reverse().join('');return b};function str2num(str){var len=str.length;if(len&1){str="\0"+str;len++};len>>=1;var result=Array();for(var i=0;i<len;i++){result[len-i-1]=str.charCodeAt(i<<1)<<8|str.charCodeAt((i<<1)+1)};return result};function num2str(num){var n=num.length;var s=Array();for(var i=0;i<n;i++){s[n-i-1]=String.fromCharCode(num[i]>>8&0xff,num[i]&0xff)};return s.join('')};function rand(n,s){var lowBitMasks=new Array(0x0000,0x0001,0x0003,0x0007,0x000f,0x001f,0x003f,0x007f,0x00ff,0x01ff,0x03ff,0x07ff,0x0fff,0x1fff,0x3fff,0x7fff);var r=n%16;var q=n>>4;var result=Array();for(var i=0;i<q;i++){result[i]=Math.floor(Math.random()*0xffff)};if(r!=0){result[q]=Math.floor(Math.random()*lowBitMasks[r]);if(s){result[q]|=1<<(r-1)}}else if(s){result[q-1]|=0x8000};return result};this.mul=mul;this.div=div;this.powmod=powmod;this.dec2num=dec2num;this.num2dec=num2dec;this.str2num=str2num;this.num2str=num2str;this.rand=rand};function PHPRPC_Error(errno,errstr){this.getNumber=function(){return errno};this.getMessage=function(){return errstr};this.toString=function(){return errno+":"+errstr}}var PHPRPC_Client=(function(){function freeEval(s){return eval(s)};return(function(){var s_clientList=[];var s_lastID=0;var s_XMLHttpNameCache=null;function createXMLHttp(){if(window.XMLHttpRequest){var objXMLHttp=new XMLHttpRequest();if(objXMLHttp.readyState==null){objXMLHttp.readyState=0;objXMLHttp.addEventListener("load",function(){objXMLHttp.readyState=4;if(typeof(objXMLHttp.onreadystatechange)=="function"){objXMLHttp.onreadystatechange()}},false)};return objXMLHttp}else if(s_XMLHttpNameCache!=null){return new ActiveXObject(s_XMLHttpNameCache)}else{var MSXML=['MSXML2.XMLHTTP.6.0','MSXML2.XMLHTTP.5.0','MSXML2.XMLHTTP.4.0','MSXML2.XMLHTTP.3.0','MsXML2.XMLHTTP.2.6','MSXML2.XMLHTTP','Microsoft.XMLHTTP.1.0','Microsoft.XMLHTTP.1','Microsoft.XMLHTTP'];var n=MSXML.length;for(var i=0;i<n;i++){try{objXMLHttp=new ActiveXObject(MSXML[i]);s_XMLHttpNameCache=MSXML[i];return objXMLHttp}catch(e){}};return null}};function createID(){return s_lastID++};function abort(clientID,id){if(typeof(s_clientList[clientID])!="undefined"){s_clientList[clientID].abort(id)}};function PHPRPC_Client(serverURL,functions){var m_xxtea=XXTEA;var m_bigint=BigInteger;var m_php=PHPSerializer;var m_ready=false;var m_preID='js'+Math.floor((new Date()).getTime()*Math.random());var m_clientID=s_clientList.length;var m_timeout=30000;var m_ajax;var m_clientName;var m_username;var m_password;var m_serverURL;var m_key;var m_keyLength;var m_encryptMode;var m_reqHeap;var m_taskQueue;var m_dataObject;var m_keyExchanging;this.dispose=function(){this.abort();s_clientList[m_clientID]=null;delete s_clientList[m_clientID]};this.useService=function(serverURL,username,password,functions){m_username=null;m_password=null;if(typeof(serverURL)=="undefined"){return new PHPRPC_Error(1,"You should set serverURL first!")};m_serverURL=serverURL;if((typeof(username)!="undefined")&&(typeof(password)!="undefined")){m_username=username;m_password=password};initService();if((typeof(functions)=="undefined")||(functions==null)){useService(this.onready)}else{setFunctions(functions,this.onready)};return true};this.setKeyLength=function(keyLength){if(m_key!=null){return false}else{m_keyLength=keyLength;return true}};this.getKeyLength=function(){return m_keyLength};this.setEncryptMode=function(encryptMode){if(encryptMode>=0&&encryptMode<=3){m_encryptMode=parseInt(encryptMode);return true}else{m_encryptMode=0;return false}};this.getEncryptMode=function(){return m_encryptMode};this.invoke=function(){var args=argsToArray(arguments);var func=args.shift();return invoke(func,args)};this.abort=function(id){if(typeof(id)=="undefined"){for(id in m_reqHeap){this.abort(id)}}else if(typeof(m_reqHeap[id])!="undefined"){if(m_ajax){if((m_reqHeap[id]!=null)&&(typeof(m_reqHeap[id].abort)=="function")){m_reqHeap[id].onreadystatechange=function(){};m_reqHeap[id].abort()}deleteReqHeap(id)}else{removeScript(id);deleteReqHeap(id)}}};this.setTimeout=function(timeout){m_timeout=timeout};this.getTimeout=function(){return m_timeout};this.getReady=function(){return m_ready};this.__getFunctions=function(id){var functions=phprpc_functions;delete phprpc_functions;setFunctions(m_php.unserialize(functions),this.onready);removeScript(id)};this.__keyExchange=function(id){if(typeof(phprpc_url)!="undefined"){initURL(phprpc_url);delete phprpc_url};if(typeof(phprpc_encrypt)=='undefined'){removeScript(id);m_key=null;m_encryptMode=0;m_keyExchanging=false;keyExchanged()}else{if(typeof(phprpc_keylen)!='undefined'){m_keyLength=parseInt(phprpc_keylen);delete phprpc_keylen}else{m_keyLength=128};var encrypt=phprpc_encrypt;delete phprpc_encrypt;removeScript(id);var callback=btoa((m_clientName+".__keyExchange2('"+id+"');").toUTF8());var request='phprpc_encrypt='+getKey(m_php.unserialize(encrypt))+'&phprpc_encode=false&phprpc_callback='+callback;appendScript(id,request)}};this.__keyExchange2=function(id){removeScript(id);m_keyExchanging=false;keyExchanged()};this.__callback=function(id){if(typeof(m_reqHeap[id])=='undefined')return;var data={};data.phprpc_errno=phprpc_errno;data.phprpc_errstr=phprpc_errstr;data.phprpc_output=phprpc_output;delete phprpc_errno;delete phprpc_errstr;delete phprpc_output;if(typeof(phprpc_result)!='undefined'){data.phprpc_result=phprpc_result;delete phprpc_result};if(typeof(phprpc_args)!='undefined'){data.phprpc_args=phprpc_args;delete phprpc_args};m_dataObject[id]=data;var script=document.getElementById('script_'+id);getResult(id,script.args,script.ref,script.encrypt,script.callback);deleteDataObject(id);deleteReqHeap(id);removeScript(id)};function initURL(url){var p=0;var protocol=null;var host=null;var path=null;if(url.substr(0,7).toLowerCase()=='http://'){protocol='http:';p=7}else if(url.substr(0,8).toLowerCase()=='https://'){protocol='https:';p=8};if(p>0){host=url.substring(p,url.indexOf('/',p));var m=host.match(/^([^:]*):([^@]*)@(.*)$/);if(m!=null){if(m_username==null){m_username=decodeURIComponent(m[1])};if(m_password==null){m_password=decodeURIComponent(m[2])};host=m[3]};path=url.substr(url.indexOf('/',p))};if(((protocol==null)||(location.protocol=='file:')||(protocol==location.protocol&&host==location.host))&&createXMLHttp()!=null){m_ajax=true}else{m_ajax=false};if((p>0)&&(m_username!=null)&&(m_password!=null)){url=protocol+'//';if(!m_ajax){url+=encodeURIComponent(m_username)+':'+encodeURIComponent(m_password)+'@'};url+=host+path};m_serverURL=url.replace(/[\&\?]+$/g,'');m_serverURL+=(m_serverURL.indexOf('?',0)==-1)?'?':'&';m_serverURL+='phprpc_id='+m_preID+m_clientID+'&'};function initService(){m_ready=false;m_key=null;m_keyLength=128;m_keyExchanging=false;m_encryptMode=0;m_reqHeap=[];m_taskQueue=[];m_dataObject=[];initURL(m_serverURL)};function useService(onready){if(m_ajax){var xmlhttp=createXMLHttp();var xmlhttpDone=false;xmlhttp.onreadystatechange=function(){if(xmlhttp.readyState==4&&!xmlhttpDone){xmlhttpDone=true;if(xmlhttp.responseText){var id=createID();createDataObject(xmlhttp.responseText,id);setFunctions(m_php.unserialize(m_dataObject[id].phprpc_functions),onready);deleteDataObject(id)};xmlhttp=null}};try{xmlhttp.open('GET',m_serverURL+'phprpc_encode=false',true);if(m_username!==null){xmlhttp.setRequestHeader('Authorization','Basic '+btoa(m_username+":"+m_password))};xmlhttp.send(null)}catch(e){xmlhttp=null;m_ajax=false;useService(onready)}}else{var id=createID();var callback=btoa((m_clientName+".__getFunctions('"+id+"');").toUTF8());var request='phprpc_encode=false&phprpc_callback='+callback;appendScript(id,request)}};function appendScript(id,request,args,ref,encrypt,callback){var script=document.createElement('script');script.id='script_'+id;script.src=m_serverURL+request.replace(/\+/g,'%2B');script.charset="UTF-8";script.defer=true;script.type='text/javascript';script.args=args;script.ref=ref;script.encrypt=encrypt;script.callback=callback;var head=document.getElementsByTagName('head');if(head[0]){head[0].appendChild(script)}else{document.body.appendChild(script)}};function removeScript(id){var script=document.getElementById('script_'+id);if(script){try{script.parentNode.removeChild(script)}catch(e){}}};function argsToArray(args){var n=args.length;var argArray=new Array(n);for(var i=0;i<n;i++){argArray[i]=args[i]}return argArray};function createDataObject(string,id){var params=string.split(";\r\n");var result={};var n=params.length;for(var i=0;i<n;i++){var p=params[i].indexOf('=');if(p>=0){var l=params[i].substr(0,p);var r=params[i].substr(p+1);result[l]=freeEval(r)}};m_dataObject[id]=result};function deleteDataObject(id){if(m_dataObject[id]){delete m_dataObject[id]}};function deleteReqHeap(id){if(typeof(m_reqHeap[id])!='undefined'){m_reqHeap[id]=null;delete m_reqHeap[id]}};function invoke(func,args){var id=createID();m_reqHeap[id]=null;var task=function(){if(m_timeout){setTimeout(function(){abort(m_clientID,id)},m_timeout)};call(id,func,args)};m_taskQueue.push(task);keyExchange();return id};function setFunction(func){return function(){return invoke(func,argsToArray(arguments))}};function setFunctions(functions,onready){for(var i=0;i<functions.length;i++){s_clientList[m_clientID][functions[i]]=setFunction(functions[i])};m_ready=true;if(typeof(onready)=='function'){onready()}};function keyExchange(){if(m_keyExchanging)return;if(m_key==null&&m_encryptMode>0){m_keyExchanging=true;if(m_ajax){var xmlhttp=createXMLHttp();var xmlhttpDone=false;xmlhttp.onreadystatechange=function(){if(xmlhttp.readyState==4&&!xmlhttpDone){xmlhttpDone=true;if(xmlhttp.responseText){var id=createID();createDataObject(xmlhttp.responseText,id);keyExchange2(id);deleteDataObject(id)};xmlhttp=null}};xmlhttp.open('GET',m_serverURL+'phprpc_encrypt=true&phprpc_encode=false&phprpc_keylen='+m_keyLength,true);if(m_username!==null){xmlhttp.setRequestHeader('Authorization','Basic '+btoa(m_username+':'+m_password))};xmlhttp.send(null)}else{var id=createID();var callback=btoa((m_clientName+".__keyExchange('"+id+"');").toUTF8());var request='phprpc_encrypt=true&phprpc_encode=false&phprpc_keylen='+m_keyLength+'&phprpc_callback='+callback;appendScript(id,request)}}else{keyExchanged()}}function keyExchange2(id){if(typeof(m_dataObject[id].phprpc_url)!="undefined"){initURL(m_dataObject[id].phprpc_url)};var data=m_dataObject[id];if(typeof(data.phprpc_encrypt)=='undefined'){m_key=null;m_encryptMode=0;m_keyExchanging=false;keyExchanged()}else{if(typeof(data.phprpc_keylen)!='undefined'){m_keyLength=parseInt(data.phprpc_keylen)}else{m_keyLength=128};var encrypt=getKey(m_php.unserialize(data.phprpc_encrypt));var xmlhttp=createXMLHttp();var xmlhttpDone=false;xmlhttp.onreadystatechange=function(){if(xmlhttp.readyState==4&&!xmlhttpDone){xmlhttpDone=true;m_keyExchanging=false;keyExchanged();xmlhttp=null}};xmlhttp.open('GET',m_serverURL+'phprpc_encode=false&phprpc_encrypt='+encrypt,true);if(m_username!==null){xmlhttp.setRequestHeader('Authorization','Basic '+btoa(m_username+":"+m_password))};xmlhttp.send(null)}};function getKey(encrypt){var p=m_bigint.dec2num(encrypt['p']);var g=m_bigint.dec2num(encrypt['g']);var y=m_bigint.dec2num(encrypt['y']);var x=m_bigint.rand(m_keyLength-1,1);var key=m_bigint.powmod(y,x,p);if(m_keyLength==128){key=m_bigint.num2str(key);var n=16-key.length;var k=[];for(var i=0;i<n;i++){k[i]='\0'};k[n]=key;m_key=k.join('')}else{m_key=m_bigint.num2dec(key).md5()};return m_bigint.num2dec(m_bigint.powmod(g,x,p))};function keyExchanged(){while(m_taskQueue.length>0){var task=m_taskQueue.shift();if(typeof(task)=='function'){task()}}};function call(id,func,args){if(typeof(m_reqHeap[id])=='undefined')return;var ref=false;var encrypt=m_encryptMode;var callback=s_clientList[m_clientID][func+'_callback'];if(typeof(callback)!='function'){callback=null};if(typeof(args[args.length-1])=='boolean'&&typeof(args[args.length-2])=='function'){ref=args[args.length-1];callback=args[args.length-2];args.length-=2}else if(typeof(args[args.length-1])=='function'){callback=args[args.length-1];args.length--};var request='phprpc_func='+func+'&phprpc_args='+btoa(encryptString(m_php.serialize(args),encrypt,1))+'&phprpc_encode=false'+'&phprpc_encrypt='+encrypt;if(!ref){request+='&phprpc_ref=false'};if(m_ajax){if(typeof(m_reqHeap[id])=='undefined')return;var xmlhttp=createXMLHttp();m_reqHeap[id]=xmlhttp;var xmlhttpDone=false;xmlhttp.onreadystatechange=function(){if(xmlhttp.readyState==4&&!xmlhttpDone){xmlhttpDone=true;if(xmlhttp.responseText){createDataObject(xmlhttp.responseText,id);getResult(id,args,ref,encrypt,callback);deleteDataObject(id)}deleteReqHeap(id);xmlhttp=null}};xmlhttp.open('POST',m_serverURL,true);xmlhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded; charset=UTF-8');if(m_username!==null){xmlhttp.setRequestHeader('Authorization','Basic '+btoa(m_username+":"+m_password))};xmlhttp.send(request.replace(/\+/g,'%2B'))}else{request+='&phprpc_callback='+btoa((m_clientName+".__callback('"+id+"');").toUTF8());if(typeof(m_reqHeap[id])=='undefined')return;appendScript(id,request,args,ref,encrypt,callback)}};function encryptString(string,encrypt,level){if((m_key!=null)&&(encrypt>=level)){string=m_xxtea.encrypt(string,m_key)};return string};function decryptString(string,encrypt,level){if((m_key!=null)&&(encrypt>=level)){string=m_xxtea.decrypt(string,m_key)};return string};function getResult(id,args,ref,encrypt,callback){if(typeof(callback)=='function'&&typeof(m_reqHeap[id])!='undefined'){var data=m_dataObject[id];var output=data.phprpc_output;if((m_key!==null)&&(encrypt>2)){output=m_xxtea.decrypt(output,m_key);if(output===null){output=data.phprpc_output}else{output=output.toUTF16()}};var result=new PHPRPC_Error(data.phprpc_errno,data.phprpc_errstr);var warning=result;if(typeof(data.phprpc_result)!='undefined'){result=m_php.unserialize(decryptString(data.phprpc_result,encrypt,2));if(ref&&(typeof(data.phprpc_args)!='undefined')){args=m_php.unserialize(decryptString(data.phprpc_args,encrypt,1))}};callback(result,args,output,warning)}};{s_clientList[m_clientID]=this;m_clientName='PHPRPC_Client.__getClient('+m_clientID+')';if(typeof(serverURL)!="undefined"){if(typeof(functions)=="undefined"){functions=null};this.useService(serverURL,null,null,functions)}}};PHPRPC_Client.create=function(serverURL,functions){if(typeof(functions)=="undefined"){functions=null};return new PHPRPC_Client(serverURL,functions)};PHPRPC_Client.__getClient=function(clientID){return s_clientList[clientID]};return PHPRPC_Client})()})();