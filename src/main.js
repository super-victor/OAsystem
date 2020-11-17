import Vue from 'vue'
import '@/theme/index.css'
import App from './App.vue'
import router from './router'
import store from './store'

import {
  Container,
  Header,
  Aside,
  Main,
  Footer,
  Breadcrumb,
  BreadcrumbItem,
  Input,
  Link,
  Button,
  Message,
  Form,
  FormItem,
  Row,
  Col,
} from 'element-ui';

Vue.use(Container);
Vue.use(Header);
Vue.use(Aside);
Vue.use(Main);
Vue.use(Footer);
Vue.use(Breadcrumb);
Vue.use(BreadcrumbItem);
Vue.use(Input);
Vue.use(Link);
Vue.use(Button);
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Row);
Vue.use(Col);

Vue.config.productionTip = false

Vue.prototype.$message = Message;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
