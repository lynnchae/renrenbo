
/*$(function($) {
    //设置校验信息
    validateInfo();
});*/

function saveSendMessage(){

    //判断表单是否检验成功
    if(!$("#sendMessageForm").valid()){
        return;
    }

    var orderId = $("#orderId").html();
    var sendCompany=$("#company").val();
    var sendNumber=$("#number").val();
    $.ajax({
        url:getRootPath()+'/listenerOrder/saveSendMessage',
        type:'post',
        dataType:'json',
        data:{'orderId':orderId,'sendCompany':sendCompany,'sendNumber':sendNumber},
        success:function(data){
            if(data != null && data == true){
                window.location.href ="shoppingDetail?orderId="+orderId;
            }else{
                alert("提交失败");
            }
        },
        timeout:function(){
            alert("等待超时");
        }
    })
}

function validateInfo(){
    $('#sendMessageForm').validate({
        errorElement: 'div',
        errorClass: 'help-block',
        focusInvalid: false,
        rules: {
            company:{
                required:true
            },
            number:{
                required:true
            }
        },
        messages: {
            company:{
                required:"*输入快递公司"
            },
            number:{
                required:"*输入运单号"
            }
        }
    });
}