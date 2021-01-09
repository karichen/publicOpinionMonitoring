new Vue({
    el: '#app',
    data: function() {
        return {
            useremail:"",
            search: "",
            tableData:[],
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
        that.getBilibiliHot("bilibili");
        that.useremail=that.getCookie("useremail");
        // var forecastChart1 = echarts.init(document.getElementById('forecastChart1'));
        let forecastChart2 = echarts.init(document.getElementById('forecastChart2'));
        let forecastChart3 = echarts.init(document.getElementById('forecastChart3'));

        let base = +new Date(1968, 9, 3);
        let oneDay = 24 * 3600 * 1000;
        let date = [];

        let data = [Math.random() * 300];

        for (let i = 1; i < 20000; i++) {
            let now = new Date(base += oneDay);
            date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'));
            data.push(Math.round((Math.random() - 0.5) * 20 + data[i - 1]));
        };

        option1 = {
            tooltip: {
                trigger: 'axis',
                position: function(pt) {
                    return [pt[0], '10%'];
                }
            },
            title: {
                left: 'center',
                text: '大数据量面积图',
            },
            toolbox: {
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: date
            },
            yAxis: {
                type: 'value',
                boundaryGap: [0, '100%']
            },
            dataZoom: [{
                type: 'inside',
                start: 0,
                end: 10
            }, {
                start: 0,
                end: 10,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }],
            series: [{
                name: '模拟数据',
                type: 'line',
                smooth: true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    color: 'rgb(255, 70, 131)'
                },
                areaStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                },
                data: data
            }]
        };


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

        // forecastChart1.setOption(option1);
        forecastChart2.setOption(option2);
        forecastChart3.setOption(option3);
    },
})