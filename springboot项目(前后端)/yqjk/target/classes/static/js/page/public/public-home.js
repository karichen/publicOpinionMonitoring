new Vue({
    el: '#app',
    data: function() {
        return {
            useremail:"",
            search:"",
            input3: '',
            clientHeight: '',
            tableData1:[],
            tableData2:[],
            tableData3:[],
        }
    },

    methods: {
        //根据名称获取指定的cookie
        getCookie: function (name) {
            let strcookie = document.cookie;//获取cookie字符串
            let arrcookie = strcookie.split("; ");//分割
            //遍历匹配
            for (let i = 0; i < arrcookie.length; i++) {
                let arr = arrcookie[i].split("=");
                if (arr[0] == name) {
                    return arr[1];
                }
            }
            return "";
        },
        getData1:function(web){
            let that=this;
            let params = new URLSearchParams();
            params.append('web', web);
            axios.post('/data/queryHot', params)
                .then(function (response) {
                    console.log(response.data);
                    //渲染哔哩哔哩
                    that.tableData1 = response.data;
                    console.log(that.bilibiliHot);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getData2:function(web){
            let that=this;
            let params = new URLSearchParams();
            params.append('web', web);
            axios.post('/data/queryHot', params)
                .then(function (response) {
                    console.log(response.data);
                    //渲染哔哩哔哩
                    that.tableData2 = response.data;
                    console.log(that.bilibiliHot);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getData3:function(web){
            let that=this;
            let params = new URLSearchParams();
            params.append('web', web);
            axios.post('/data/queryHot', params)
                .then(function (response) {
                    console.log(response.data);
                    //渲染哔哩哔哩
                    that.tableData3 = response.data;
                    console.log(that.bilibiliHot);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
    },

    mounted() {
        let that = this;
        that.getData1("bilibili");
        that.getData2("tieba");
        that.getData3("bilibili_daily");
        that.useremail=that.getCookie("useremail");
        let forecastChart2 = echarts.init(document.getElementById('forecastChart2'));
        let forecastChart3 = echarts.init(document.getElementById('forecastChart3'));

        option2 = {
            title: {
                text: '本站点数据来源',
                subtext: '更新时间：2020/7/5',
                left: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c} ({d}%)'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['新浪', '百度', '搜狗', '搜狐', '微信']
            },
            series: [{
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [{
                    value: 335,
                    name: '新浪'
                }, {
                    value: 310,
                    name: '百度'
                }, {
                    value: 234,
                    name: '搜狗'
                }, {
                    value: 135,
                    name: '搜狐'
                }, {
                    value: 1548,
                    name: '微信'
                }],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }]
        };

        option3 = {
            color: ['#ffa34b', '#fc515a', '#67b8cb', '#4074bf', '#b3d4e6', '#193b4d', '#749f83', '#ca8622', '#c23531', '#2f4554', '#61a0a8', '#d48265', '#91c7ae', '#bda29a', '#6e7074', '#546570', '#c4ccd3'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#666'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {
                        show: true,
                        readOnly: false
                    },
                    magicType: {
                        show: true,
                        type: ['line', 'bar']
                    },
                    restore: {
                        show: true
                    },
                    saveAsImage: {
                        show: true
                    }
                }
            },
            legend: {
                data: ['舆情总数量', '负面舆情数量', '热度']
            },
            xAxis: [{
                type: 'category',
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                axisPointer: {
                    type: 'shadow'
                }
            }],
            yAxis: [{
                type: 'value',
                name: '数量',
                min: 0,
                max: 250,
                interval: 50,
                axisLabel: {
                    formatter: '{value} K'
                }
            }, {
                type: 'value',
                name: '热度',
                min: 0,
                max: 25,
                interval: 5,
                axisLabel: {
                    formatter: '{value}M'
                }
            }],
            series: [{
                name: '舆情总数量',
                type: 'bar',
                data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
            }, {
                name: '负面舆情数量',
                type: 'bar',
                data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
            }, {
                name: '热度',
                type: 'line',
                yAxisIndex: 1,
                data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
            }]
        };
        this.$notify({
            title: '提示',
            message: this.useremail+'欢迎使用晴雨表',
        });
        forecastChart2.setOption(option2);
        forecastChart3.setOption(option3);
    },
})