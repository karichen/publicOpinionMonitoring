
 new Vue({
        el: '#app',
        data:{
            search:"",
            //新闻内容
            key:"",
            allkey:"",
            useremail:"",
            isShowXinlang:true,
            isShowToutiao:true,
            news1:null,
            news2:null,
            news3:null,
            loading1:true,
            loading2:true,
            loading3:true,
            //新闻分页
            new1Pagination:null,
            new2Pagination:null,
            new3Pagination:null,
            //侧边栏
            keywords:[],
            //加载
            // fullscreenLoading: true,
            //筛选：
            property:'',
            rank:'',
            show:'',
            timeLimit:'',
            // 时间选择组件=====================================================
            pickerOptions: {
                shortcuts: [{
                    text: '最近一周',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近一个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近三个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                        picker.$emit('pick', [start, end]);
                    }
                }]
            },
            value1: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
            //    关键词
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
            //this.fullscreenLoading = false;
            setTimeout(function() {
                that.achieveAllnews();
            }, 0)
            setTimeout(function() {
                that.achieveXinlang();
            }, 2000)
            setTimeout(function() {
                that.achieveToutiao();
            }, 4000)
            //ajax请求
            //请求新闻资源
            axios.post('/data/allNews', {})
                .then(function (response) {
                    console.log(response.data);
                    if(response.data.newsList==null){
                        location.href="/view/toLogin";
                    }
                    //渲染舆情文字
                    that.news1 = response.data.newsList;
                    that.new1Pagination=response.data.pageNum;
                    //渲染关键字列表
                    that.keywords=JSON.parse(response.data.keywords);
                    console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        methods: {

            //注销
            logout:function() {
                this.$confirm('此操作将注销'+this.useremail+', 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '注销成功!'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消注销'
                    });
                });
            },

            //所有舆情新闻分页
            handleAllnewslCurrentChange(val,e) {
                console.log(`第${e}种分页,当前页: ${val}`);
                let that = this;
                //请求新闻资源
                let params = new URLSearchParams();
                params.append('page', val);
                params.append("key",that.key);
                axios.post('/data/allNews',params)
                    .then(function (response) {
                        that.news1 = response.data.newsList;
                        that.new1Pagination=response.data.pageNum;
                        that.keywords =JSON.parse(response.data.keywords);
                        //console.log(response.data);
                        console.log(that.news1);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            //新浪分页
            handleAllxinlangCurrentChange(val,e) {
                console.log(`第${e}种分页,当前页: ${val}`);
                let that = this;
                //请求新浪新闻资源
                let params = new URLSearchParams();
                params.append('page', val);
                params.append("key",that.key);
                axios.post('/data/allXinlangNews',params)
                    .then(function (response) {
                        that.news2 = response.data.newsList;
                        that.new2Pagination=response.data.pageNum;
                       // console.log(response.data);
                        that.loading=false;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            //头条分页
            handleAlltoutiaoCurrentChange(val,e) {
                console.log(`第${e}种分页,当前页: ${val}`);
                let that = this;
                //请求头条新闻资源
                var params = new URLSearchParams();
                params.append('page', val);
                params.append("key",that.key);
                axios.post('/data/allToutiaoNews',params)
                    .then(function (response) {
                        that.news3 = response.data.newsList;
                        that.new3Pagination=response.data.pageNum;
                        //console.log(response.data);
                        that.loading=false;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },


            //微信分页
            handleAlltoutiaoCurrentChange(val,e) {
                console.log(`第${e}种分页,当前页: ${val}`);
                let that = this;
                //请求头条新闻资源
                var params = new URLSearchParams();
                params.append('page', val);
                params.append("key",that.key);
                axios.post('/data/allWeixinNews',params)
                    .then(function (response) {
                        that.news4 = response.data.newsList;
                        that.new4Pagination=response.data.pageNum;
                        //console.log(response.data);
                        that.loading=false;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },

            //获取全部新闻
            achieveAllnews(){
                let that = this;
                console.log("achieveAllnews");
                this.loading1 = true;
                this.handleAllnewslCurrentChange(1,1);
                setTimeout(function() {
                    that.loading1 = false;
                }, 1000)
            },

            //获取新浪新闻
            achieveXinlang(){
                console.log("achieveXinlang");
                let that = this;
                this.loading2 = true;
                this.handleAllxinlangCurrentChange(1,2);
                setTimeout(function() {
                    that.loading2 = false;
                }, 1000)
            },

            //获取头条新闻
            achieveToutiao(){
                console.log("achieveToutiao");
                let that = this;
                this.loading3 = true;
                this.handleAlltoutiaoCurrentChange(1,3);
                setTimeout(function() {
                    that.loading3 = false;
                }, 1000)
            },

            //获取微信新闻
            achieveWeixin(){
                console.log("achieveWeixin");
                let that = this;
                this.loading4 = true;
                this.handleAlltoutiaoCurrentChange(1,4);
                setTimeout(function() {
                    that.loading4 = false;
                }, 1000)
            },

            //添加关键字（问题）
            add_keyword: function(index) {
                let that = this;
                let number = index;
                console.log(number);
                console.log( that.keywords[number].keyword);
                this.$prompt('请输入关键字', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(({ value }) => {
                    let newKeyword = {
                        content:value
                    }
                    that.keywords[number].keyword.push(newKeyword);
                    that.updateKeywords();
                    console.log( that.keywords[number].keyword);
                    this.$message({
                        type: 'success',
                        message: '成功添加关键字: ' + value
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });
                });
                console.log(that.keywords)
            },

            add_keyword_file: function() {
                let that = this;
                this.$prompt('请输入关键字', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(({ value }) => {
                    let keyword_file = {
                        keywordFile:value,
                        keyword:[
                        ]
                    }
                    that.keywords.push(keyword_file);
                    that.updateKeywords();
                    this.$message({
                        type: 'success',
                        message: '成功添加关键字: ' + value
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });
                });
                console.log(that.keywords)
            },


            deletekeyword:function (indexF,indexS) {
                let that = this;
                console.log("indexS"+indexS+"indexF"+indexF);
                that.keywords[indexF].keyword.splice(indexS,1);
                that.updateKeywords();
                console.log(that.keywords[indexF].keyword);
            },

            updatakeyword:function (indexF,indexS) {
                let that = this;
                this.$prompt('修改关键字', '提示', {
                    inputValue:that.keywords[indexF].keyword[indexS].content,
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(({ value }) => {
                    that.keywords[indexF].keyword[indexS].content=value;
                    that.updateKeywords();
                    console.log( that.keywords[number].keyword);
                    this.$message({
                        type: 'success',
                        message: '成功修改关键字为: ' + value
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消修改'
                    });
                });

            },

            deletekeywordfile:function(indexF){
                let that = this;
                console.log("indexF"+indexF);
                that.keywords.splice(indexF,1);
                that.updateKeywords();
                console.log(that.keywords);
            },


            updatakeywordfile:function(indexF){
                let that = this;
                this.$prompt('修改关键字组', '提示', {
                    inputValue:that.keywords[indexF].keywordFile,
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(({ value }) => {
                    that.keywords[indexF].keywordFile=value;
                    that.updateKeywords();
                    this.$message({
                        type: 'success',
                        message: '成功修改关键字组为: ' + value
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消修改'
                    });
                });
            },

            //跳转到详情页
            toDetail:function(id){
                location.href="/view/toNewsDetail?id="+id;
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

            //向服务器端修改keywords
            updateKeywords:function () {
                var that=this;
                let params = new URLSearchParams();
                params.append('keywords',JSON.stringify(that.keywords));
                axios.post('/data/updateKeywords', params)
                    .then(function (response) {
                        console.log(response.data+"哈哈哈哈哈哈哈哈哈");
                        if (response.data=="fail"){
                            that.$message.error("服务器错误");
                        }else if (response.data=="success"){;
                            console.log("后台数据更新成功");
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },

            //设置爬取关键字  逐步渲染
            setIndexKey:function (key="") {
                let that=this;
                that.key=key;
                setTimeout(function() {
                    that.achieveAllnews();
                }, 0)
                setTimeout(function() {
                    that.achieveXinlang();
                }, 2000)
                setTimeout(function() {
                    that.achieveToutiao();
                }, 4000)
            },
        }
    })
