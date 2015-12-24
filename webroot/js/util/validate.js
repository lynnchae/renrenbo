/*只能输入数字，可按退格键删除数字*/
function vaildIntegerNumber(evnt){
    evnt=evnt||window.event;
    var keyCode=window.event?evnt.keyCode:evnt.which;
    return keyCode>=48&&keyCode<=57||keyCode==8;
}
/*只能输入数字和点,可按退格键删除数字或点*/
function vaildFloatNumber(evnt){
    evnt=evnt||window.event;
    var keyCode=window.event?evnt.keyCode:evnt.which;
    return keyCode>=48&&keyCode<=57||keyCode==46||keyCode==8;
}

/*只能输入数字和一个点,可按退格键删除数字或点*/
function vailFloatNumberLimitDecimalPoint(evnt,obj){
    evnt=evnt||window.event;
    var keyCode=window.event?evnt.keyCode:evnt.which;
    if(obj.value.indexOf(".")!=-1 && keyCode==46) return false;
    return keyCode>=48&&keyCode<=57||keyCode==46||keyCode==8;
}

/*只能输入数字和一个点，且输入的第一个字符不能为点，可按退格键删除数字或点*/
function vailFloatNumberPerfect(evnt,obj){
    evnt=evnt||window.event;
    var keyCode=window.event?evnt.keyCode:evnt.which;

    if((obj.value.length==0 || obj.value.indexOf(".")!=-1) && keyCode==46) return false;
    return keyCode>=48&&keyCode<=57||keyCode==46||keyCode==8;
}