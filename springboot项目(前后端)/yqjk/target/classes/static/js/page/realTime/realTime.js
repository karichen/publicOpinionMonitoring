new Vue({
    el: '#app',
    data: function () {
        return {
            useremail:"",
            search: "",
            bilibiliHot:[],
            baiduHot:[],
            bilibili_dailyHot:[],
            tiebaHot:[],
            weiboHot:[],
            weixinHot:[],
            zhihuHot:[],
            wangyiHot:[]
        }
    },
    mounted() {
        let that = this;
        //查找浏览器里是否有用户信息的cooke,如果没有则提示重新登陆
        let useremail=that.getCookie("useremail");
        that.useremail=useremail;
        console.log(useremail+"====");
        if (useremail==""){
            alert("登陆状态失效,请重新登陆");
            location.href="/view/toLogin";
        }
        //获取初步渲染
        that.getBilibiliHot("bilibili");
        that.getBaiduHot("baidu");
        that.getBilibili_dailyHot("bilibili_daily");
        that.getTiebaHot("tieba");
        that.getWeiboHot("weibo");
        that.getWeixinHot("weixin");
        that.getZhihuHot("zhihu");
        that.getWangyiHot("wangyi");
        this.$notify({
            title: '提示',
            message: this.useremail+'欢迎来到热点',
        });
    },

    methods: {
        /**
         * 数字转整数 如 100000 转为10万
         * @param {需要转化的数} num
         * @param {需要保留的小数位数} point
         */
        transform(num ) {
            let point = 0;
            let numStr = num.toString()
            // 十万以内直接返回
            if (numStr.length < 4) {
                return numStr;
            }
            //大于8位数是亿
            else if (numStr.length > 8) {
                let decimal = numStr.substring(numStr.length - 8, numStr.length - 8 + point);
                return parseFloat(parseInt(num / 100000000) + '.' + decimal) + '亿';
            }
            //大于6位数是十万 (以10W分割 10W以下全部显示)
            else if (numStr.length > 5) {
                let decimal = numStr.substring(numStr.length - 4, numStr.length - 4 + point)
                return parseFloat(parseInt(num / 10000) + '.' + decimal) + '万';
            }
        },

        getBilibiliHot:function(web){
            let that=this;
            let params = new URLSearchParams();
            params.append('web', web);
            axios.post('/data/queryHot', params)
                .then(function (response) {
                    console.log(response.data);
                    //渲染哔哩哔哩
                    that.bilibiliHot = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getBaiduHot:function(web){
            let that=this;
            let params = new URLSearchParams();
            params.append('web', web);
            axios.post('/data/queryHot', params)
                .then(function (response) {
                    console.log(response.data);
                    //渲染哔哩哔哩
                    that.baiduHot = response.data;

                })
                .catch(function (error) {
                    console.log(error);
                });
        },


        getBilibili_dailyHot:function(web){
            let that=this;
            let params = new URLSearchParams();
            params.append('web', web);
            axios.post('/data/queryHot', params)
                .then(function (response) {
                    console.log(response.data);
                    //渲染哔哩哔哩
                    that.bilibili_dailyHot = response.data;

                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getTiebaHot:function(web){
            let that=this;
            let params = new URLSearchParams();
            params.append('web', web);
            axios.post('/data/queryHot', params)
                .then(function (response) {
                    console.log(response.data);
                    //渲染哔哩哔哩
                    that.tiebaHot = response.data;

                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getWeiboHot:function(web){
            let that=this;
            let params = new URLSearchParams();
            params.append('web', web);
            axios.post('/data/queryHot', params)
                .then(function (response) {
                    console.log(response.data);
                    //渲染哔哩哔哩
                    that.weiboHot = response.data;

                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getWeixinHot:function(web){
            let that=this;
            let params = new URLSearchParams();
            params.append('web', web);
            axios.post('/data/queryHot', params)
                .then(function (response) {
                    console.log(response.data);
                    //渲染哔哩哔哩
                    that.weixinHot = response.data;

                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        getZhihuHot:function(web){
            let that=this;
            let params = new URLSearchParams();
            params.append('web', web);
            axios.post('/data/queryHot', params)
                .then(function (response) {
                    console.log(response.data);
                    //渲染哔哩哔哩
                    that.zhihuHot = response.data;

                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        getWangyiHot:function(web){
            let that=this;
            let params = new URLSearchParams();
            params.append('web', web);
            axios.post('/data/queryHot', params)
                .then(function (response) {
                    console.log(response.data);
                    //渲染哔哩哔哩
                    that.wangyiHot = response.data;

                })
                .catch(function (error) {
                    console.log(error);
                });
        },


        //根据名称获取指定的cookie
        getCookie:function (name) {
            var strcookie = document.cookie;//获取cookie字符串
            var arrcookie = strcookie.split("; ");//分割
            //遍历匹配
            for ( var i = 0; i < arrcookie.length; i++) {
                var arr = arrcookie[i].split("=");
                if (arr[0] == name){
                    console.log(arrcookie[i]+"红火火恍恍惚惚");
                    return arr[1];
                }
            }
            return "";
        },
    }
})