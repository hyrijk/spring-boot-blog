
var Header = {
    template: '#header',
}

var PostList = {
    template: '#posts',
    data: function () {
        return {
            posts: {}
        }
    },
    filters: {
        longToDate: function (long) {
            var date = new Date(long)
            return date.toLocaleString()
        }
    },
    mounted: function () {
        axios.get("/api/post").then(function (res) {
            this.posts = res.data
        }.bind(this))
    },
    methods: {
        showDetail: {}
    }
}

var LoginForm = {
    template: '#login-form',
    data: function () {
        return {
            name: "",
            password: ""
        }
    },
    methods: {
        handleSubmit: function (e) {
            e.preventDefault()
            if (this.name ==='') {
                alert("用户名不能为空")
                return false
            }
            if (this.password === '') {
                alert('密码不能为空')
                return false
            }
            console.log(this.name, this.password)
        }
    }
}

var SignupForm = {
    template: '#signup-form',
    data: function () {
        return {
            name: "",
            password: "",
            passwordAgain: ""
        }
    },
    methods: {
        handleSubmit: function (e) {
            e.preventDefault()
            if (this.name ==='') {
                alert("用户名不能为空")
                return false
            }
            if (this.password === '') {
                alert('密码不能为空')
                return false
            }
            if (this.password !== this.passwordAgain) {
                alert('两次输入的密码不一致')
                return false
            }
            console.log(this.name, this.password)
        }
    }
}


marked.setOptions({
    renderer: new marked.Renderer(),
    gfm: true,
    tables: true,
    breaks: true,
    pedantic: false,
    sanitize: true,
    smartLists: true,
    smartypants: true
});

var PostDetail = {
    template:  '#post-detail',
    data: function () {
        return {
            post: {
                content: '',
                author: {}
            }
        }
    },
    mounted: function () {
        axios.get('/api/post/' + this.$route.params.id).then(function (res) {
            this.post = res.data
        }.bind(this))
    },
    filters: {
        longToDate: PostList.filters.longToDate
    },
    computed: {
        compiledMarkdown: function () {
            return marked(this.post.content)
        }
    }
}

var routes = [
    { path: "/" , component: PostList} ,
    { path: '/posts', component: PostList },
    { path: '/login', component: LoginForm },
    { path: '/signup', component: SignupForm },
    { path: '/posts/:id', component: PostDetail },
]

var router = new VueRouter({
    routes: routes
})

new Vue({
    router: router,
    components: {
        'x-header': Header
    }
}).$mount('#app')