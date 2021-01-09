var app = new Vue({
    el: "#app",
    data: {
        search:"",
        formData: {
            useremail: "",
            password: "",
            usercode: "",
        },
        // 校验规则
        rules: {
            useremail: [{
                required: true, //是否必填
                message: '邮箱地址不能为空', //规则提示
                trigger: 'blur' //何事件触发
            }, {
                pattern: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                message: '请输入正确的邮箱地址',
                trigger: ['blur', 'change']
            }],
            password:[{
                required: true, //是否必填
                message: '密码不能为空', //规则提示
                trigger: 'blur' //何事件触发
            },{
                min:6,
                message: "密码大于6位",
                trigger: 'blur' //何事件触发
            }],
            usercode:[{
                required: true, //是否必填
                message: '验证码不能为空', //规则提示
                trigger: 'blur' //何事件触发
            }],
        },
        show: true,
        count: '',
        timer: null,
        username:undefined,
        securityCode:undefined,
    },
    methods: {
        //倒计时禁用
        requestCode(){
            let that = this;
            //去除两端空格之后判断非空
            if (this.formData.useremail.replace(/^\s*|\s*$/g,"")==""){
                this.$message.error("邮箱不可为空");
                return;
            }
            const TIME_COUNT = 60;
            if (!this.timer) {
                this.count = TIME_COUNT;
                this.show = false;
                this.timer = setInterval(() => {
                    if (this.count > 0 && this.count <= TIME_COUNT) {
                        this.count--;
                    } else {
                        this.show = true;
                        clearInterval(this.timer);
                        this.timer = null;
                    }
                }, 1000)
            }
            //发送验证码
            let params = new URLSearchParams();
            params.append('useremail',this.formData.useremail);
            axios.post('/data/requestCode',params)
                .then(function (response) {
                    console.log("===="+response.data);
                    if (response.data=="same mail"){
                        that.$message.error("邮箱已经被注册!")
                    }else if (response.data=="send ok"){
                        console.log("已经发送");
                    }else {
                        that.$message.error("未知错误");
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        //提交注册请求
        submit:function () {
            let that=this;

            if (this.formData.useremail.replace(/^\s*|\s*$/g,"")==""){
                this.$message.error("邮箱不可为空");
                return;
            }
            if (this.formData.password.replace(/^\s*|\s*$/g,"")==""){
                this.$message.error("密码不可为空");
                return;
            }
            if (this.formData.usercode.replace(/^\s*|\s*$/g,"")==""){
                this.$message.error("验证码不可为空");
                return;
            }

            let params = new URLSearchParams();
            this.$refs.formData.validate((valid) => {
                if (valid) {
                    params.append('useremail',that.formData.useremail);
                    params.append('usercode',that.formData.usercode);
                    params.append('password',that.formData.password);
                    axios.post('/data/registe',params)
                        .then(function (response) {
                            console.log("===="+response.data);
                            if (response.data=="error code"){
                                that.$message.error("验证码错误");
                            }else if (response.data=="same mail"){;
                                that.$message.error("邮箱已经被注册!");
                            }else if(response.data=="fail"){
                                that.$message.error("未知错误!");
                            }else if (response.data=="success"){
                                let r=confirm("注册成功!您的注册邮箱为"+that.formData.useremail+"  您的密码为"+that.formData.password+
                                    "   是否返回登陆?")
                                if (r==true) {
                                    location.href="/view/toLogin";
                                }
                                else {
                                    return
                                }
                            }else {
                                that.$message.error("服务器内部错误");
                            }
                        }).catch(function (error) {
                        console.log(error);
                    });
                } else {
                    alert('请完整的填入信息');
                    return false;
                }
            });
        }
    }
});