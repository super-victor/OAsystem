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
  Table,
  TableColumn,
  Radio,
  RadioGroup,
  RadioButton,
  Select,
  Option,
  Pagination,
  InputNumber,
  Autocomplete,
  Card
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
Vue.use(Table);
Vue.use(TableColumn);
Vue.use(Radio);
Vue.use(RadioGroup);
Vue.use(RadioButton);
Vue.use(Select);
Vue.use(Option);
Vue.use(Button);
Vue.use(Input);
Vue.use(Pagination);
Vue.use(InputNumber);
Vue.use(Autocomplete);
Vue.use(Card);

Vue.config.productionTip = false

Vue.prototype.$message = Message;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
