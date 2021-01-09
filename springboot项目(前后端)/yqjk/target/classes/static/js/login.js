var app = new Vue({
    el: "#app",
    data: {
        search:"",
        checkcode:"",
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
            },{
                min:6,
                message: "密码大于6位"
            }],
            password:[{
                required: true, //是否必填
                message: '密码不能为空', //规则提示
                trigger: 'blur' //何事件触发
            }],
            usercode:[{
                required: true, //是否必填
                message: '验证码不能为空', //规则提示
                trigger: 'blur' //何事件触发
            }],
        }
    },
    //ajax请求
    mounted() {
        this.requestCode();
    },
    methods: {
        //请求验证码 验证码交给前端校验 以防恶意登陆
        requestCode:function () {
            let that = this;
            axios.post('/data/loginCode', {})
                .then(function (response) {
                    console.log(response.data);
                    that.checkcode=response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        //登陆提交
        submit:function () {
            let that = this;
            if (this.formData.useremail.replace(/^\s*|\s*$/g,"")==""){
                this.$message.error("邮箱不可为空");
                return;
            }
            if (this.formData.password.replace(/^\s*|\s*$/g,"")==""){
                this.$message.error("密码不可为空");
                return;
            }
            if (that.formData.usercode.replace(/^\s*|\s*$/g,"")==""){
                this.$message.error("验证码不可为空")
                that.requestCode();
                return ;
            }

            if (that.formData.usercode.toLowerCase()!= that.checkcode.toLowerCase()){
                this.$message.error("验证码错误!")
                that.requestCode();
                return ;
            }
            //规则检查valid - true 符合规则  - false 不符合规则
            this.$refs.formData.validate((valid) => {
                if (valid) {
                    let params = new URLSearchParams();
                    params.append('useremail',this.formData.useremail);
                    params.append('password',this.formData.password);
                    axios.post('/data/checkLogin', params)
                        .then(function (response) {
                            if (response.data.flag=="no mail"){
                                that.$message.error("账号不存在");
                            }else if (response.data.flag=="error pwd"){
                                that.$message.error("密码错误");
                            }else if (response.data.flag=="success"){
                                location.href="/view/public/toPublicHome";
                            }else {
                                that.$message.error("服务器错误");
                            }
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                    that.requestCode();
                } else {
                    alert('请完整的填入信息');
                    return false;
                }
            });
        }
    }
});