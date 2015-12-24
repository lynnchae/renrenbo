/**
 * 点击菜单修改右边主页面的导航
 */

/**
 *设置导航条数据
 * @param str 二级菜单数据
 * @param obj 三级菜单数据
 */
function setNavigationBar(str,obj) {

    var objStr = $(obj).html();
    var subStr = objStr.substring(51, objStr.length);

    $("#homePage").html("首页");
    if(str != null){
        $("#twoLevel").html(str);
    }
    if(objStr != null){
        $("#threeLevel").html(subStr);
    }

}
