new Vue({
    el: '#app',
    data: function() {
        return {
            useremail:"",
            search: "",
            tableData: []
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
        that.getBilibiliHot("tieba");
        that.useremail=that.getCookie("useremail");
        let myChart = echarts.init(document.getElementById('forecastChart1'));
        myChart.setOption({
            series: [{
                type: 'map',
                map: 'china'
            }]
        });

    },
})