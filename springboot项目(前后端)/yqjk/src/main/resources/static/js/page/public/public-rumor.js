new Vue({
    el: '#app',
    data: function() {
        return {
            useremail:"",
            search: "",
            clientHeight: '',
            tableData: [],
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
        getBilibiliHot:function(web){
            let that=this;
            let params = new URLSearchParams();
            params.append('web', web);
            axios.post('/data/queryHot', params)
                .then(function (response) {
                    console.log(response.data);
                    //渲染哔哩哔哩
                    that.tableData = response.data;
                    console.log(that.bilibiliHot);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
    },

    mounted() {
        let that = this;
        that.useremail=that.getCookie("useremail");
        that.getBilibiliHot("bilibili");
        let forecastChart1 = echarts.init(document.getElementById('forecastChart1'));
        option1 = {
            title: {
                text: '一年内谣言数量',
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['新闻总量', '谣言数']
            },
            toolbox: {
                show: true,
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
            calculable: true,
            xAxis: [{
                type: 'category',
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
            }],
            yAxis: [{
                type: 'value'
            }],
            series: [{
                name: '新闻总量',
                type: 'bar',
                data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
                markPoint: {
                    data: [{
                        type: 'max',
                        name: '最大值'
                    }, {
                        type: 'min',
                        name: '最小值'
                    }]
                },
                markLine: {
                    data: [{
                        type: 'average',
                        name: '平均值'
                    }]
                }
            }, {
                name: '谣言数',
                type: 'bar',
                data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
                markPoint: {
                    data: [{
                        name: '年最高',
                        value: 182.2,
                        xAxis: 7,
                        yAxis: 183
                    }, {
                        name: '年最低',
                        value: 2.3,
                        xAxis: 11,
                        yAxis: 3
                    }]
                },
                markLine: {
                    data: [{
                        type: 'average',
                        name: '平均值'
                    }]
                }
            }]
        };
        forecastChart1.setOption(option1);
    },
})