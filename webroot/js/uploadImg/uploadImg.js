function ajaxFileUpload(url,params,fileElementId,dialog,opeType,object) {
    //url 请求的路径
    //params 请求需要传入的参数
    //fileElementId 文件上传的input的ID
    //dialog 弹框的对象，用于请求成功后关闭弹框
    //opeType 操作的类型 1 表示修改，2 表示添加
    //object 调用上传操作的对象，用于刷新列表信息
    $.ajaxFileUpload
    (
        {
            url: url, //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: fileElementId, //文件上传域的ID
            dataType: 'content', //返回值类型 一般设置为json
            data:params,
            success: function (data, status)  //服务器成功响应处理函数
            {
                if(data){
                    if(opeType == 1){
                        alert("修改成功");
                    }else{
                        alert("添加成功");
                    }
                    if($(dialog)!= null){
                        $(dialog).dialog("close");
                    }
                    if(object != null){
                        if(opeType == 1){
                            //修改时带条件刷新当前数据
                            object.search();
                        }else{
                            //添加时刷新所有数据
                            object.reloadData();
                        }

                    }
                }else {
                    if(opeType == 1){
                        alert("修改失败");
                    }else{
                        alert("添加失败");
                    }
                }
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                alert(e);
                console.log(e);
            }
        }
    )
}


//判断上传文件是否是图片
function isPicture(fileName){
    if(fileName!=null && fileName !=""){
        //lastIndexOf如果没有搜索到则返回为-1
        if (fileName.lastIndexOf(".")!=-1) {
            var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
            var suppotFile = new Array();
            suppotFile[0] = "jpg";
            suppotFile[1] = "gif";
            suppotFile[2] = "bmp";
            suppotFile[3] = "png";
            suppotFile[4] = "jpeg";
            for (var i =0;i<suppotFile.length;i++) {
                if (suppotFile[i]==fileType) {
                    return true;
                } else{
                    continue;
                }
            }
            return false;
        } else{
            return false;
        }
    }
}
