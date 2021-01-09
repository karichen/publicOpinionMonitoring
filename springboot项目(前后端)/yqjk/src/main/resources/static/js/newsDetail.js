new Vue({
    el: '#app',
    data: function () {
        return {
            useremail: "",
            search: "",
            info: "",
            activities: [{
                content: '北汽集团拟接盘神州租车： 将收购 21.26 % 股份',
                timestamp: '2018-04-15'
            }, {
                content: '神州租车第一季度净亏损 1.88 亿元，同比由盈转亏',
                timestamp: '2018-04-13'
            }, {
                content: '神州租车法定代表人、经理变更，陆正耀暂无职位变动',
                timestamp: '2018-04-11'
            }, {
                content: '吉利回应收购神州租车事宜：目前没有此项计划',
                timestamp: '2018-04-11'
            }, {
                content: '消息称携程正洽谈收购神州租车，做大租车业务平台',
                timestamp: '2018-04-11'
            }]
        }
    },

    mounted() {
        let that = this;
        //查找浏览器里是否有用户信息的cooke,如果没有则提示重新登陆
        let useremail = that.getCookie("useremail");
        that.useremail = useremail;
        console.log(useremail + "====");
        if (useremail == "") {
            alert("登陆状态失效,请重新登陆");
            location.href = "/view/toLogin";
        }
    },

    methods: {
        //根据名称获取指定的cookie
        getCookie: function (name) {
            var strcookie = document.cookie;//获取cookie字符串
            var arrcookie = strcookie.split("; ");//分割
            //遍历匹配
            for (var i = 0; i < arrcookie.length; i++) {
                var arr = arrcookie[i].split("=");
                if (arr[0] == name) {
                    return arr[1];
                }
            }
            return "";
        },
    }
})
