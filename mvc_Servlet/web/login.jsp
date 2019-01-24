<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
    %>
    <title>登陆页面</title>
    <%--取出错误信息--%>
    <%
        String message = "";
        String msg = (String) request.getAttribute("msg");
        if(msg != null){
            message = msg;
        }
    %>
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <%--取出Cookies--%>
    <%
        String uname = "";
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie c: cookies){
                if("uname".equals(c.getName())){
                    uname = c.getValue();
                }
            }
        }
    %>
    <style>
        .table1{
            margin: 50px auto;
            border-collapse: collapse;
        }
        .table1 ,th ,td{
            border: #e1b244 solid 2px;
        }
        .table1 .signin{
            text-align: center;
        }
        font{
            color: red;
            display: block;
            height: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
    <font ><b id="msg"><%=message%></b></font>

    <form name="formx" action="LoginServlet" onsubmit="return validate_info(this);" method="post">
        <table class="table1" >
            <tr>
                <th>用户名：</th>
                <th>
                    <input type="text" id="name" name="username" onblur="validate_name()" value="<%=uname %>" />
                </th>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>密&nbsp;&nbsp;&nbsp;码：</td>
                <td><input type="password" name="password" id="passwd" onblur="validate_passwd(this.value)"  /></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>验证码：</td>
                <td><input type="text" name="verifyCode"  /></td>
                <td ><img id="img" src=""></td>
                <td><a href="javascript:;" id="a">看不清？换一张</a></td>
            </tr>
            <tr>
                <td></td>
                <td class="signin" >
                    <input type="submit" value="登录">
                </td>
                <td></td>
                <td></td>
            </tr>
        </table>
    </form>
<script>
    /*字母开头，3-10，允许字母数字下划线空格*/
    var unameReg = /^[a-zA-Z][\w\s]{2,9}$/;
    /*必须包含大小写字母和数字，可以使用特殊字符，长度在6-10之间*/
    var passwdReg = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,10}$/;

    function validate_info(fm){
        if (!unameReg.test(fm.username.value)){
            alert("用户名规则（字母开头，3-10，允许字母数字下划线空格）");
            return false;
        }else if(!isNumber(fm.password.value)){
            alert("请输入合法的密码");
            return false;
        }else if(fm.verifyCode.value == ""){
            alert("验证码不能为空");
            return false;
        }
        return true;
    }
    function isNumber(str) {
        return passwdReg.test(str);
    }
    function validate_name(){
        var uname = document.getElementById("name").value;
        if(uname == null || uname.length <= 0){
            $("#msg").html("提示：用户名不能为空");
        }else if(!unameReg.test(uname)){
            $("#msg").html("提示：用户名规则（字母开头，3-10，允许字母数字下划线空格）");
        }
    }
    function validate_passwd(passwd) {
        if(passwd == null || passwd.length <= 0)
            $("#msg").html("提示：密码不能为空");
        else if(!passwdReg.test(passwd)){
            $("#msg").html("提示：密码规则（必须包含大小写字母和数字，可以使用特殊字符，长度在6-10之间）");
        }
    }
</script>
<%--看不清换一张，加时间戳（解决浏览器缓存，导致验证码不刷新）--%>
<script>
    var a = document.getElementById('a');
    a.onclick = function () {
        var imgEle = document.getElementById("img")
        imgEle.src = "VerifyCodeServlet?a=" + new Date().getTime();
    }
</script>
    <%--解决浏览器后退，验证码不刷新--%>
<script>
        var imgEle;
        /*解决Chrome后退，验证码不刷新*/
        $(document).ready(function () {
            var a = document.getElementById('a');
            a.onclick = function () {
                imgEle = document.getElementById("img")
                imgEle.src = "VerifyCodeServlet?a=" + new Date().getTime();
            }
        })
        /*解决火狐页面后退，验证码不刷新*/
        window.onpageshow=function (ev) {
            imgEle = document.getElementById("img")
            imgEle.src = "VerifyCodeServlet?a=" + new Date().getTime();
        }
    </script>

</body>
</html>
