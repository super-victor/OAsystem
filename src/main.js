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
  Divider,
  Dialog,
  Select,
  Option,
  Table,
  TableColumn,
  Radio,
  RadioGroup,
  RadioButton,
  Pagination,
  InputNumber,
  Autocomplete,
  Card,
  Form,
  FormItem,
  Row,
  Col,
  Tabs,
  TabPane,
  Tag,
  Popover,
  MessageBox,
  Checkbox,
  CheckboxGroup,
  Loading,
  Switch
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
Vue.use(Divider);
Vue.use(Dialog);
Vue.use(Select);
Vue.use(Option);
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
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Row);
Vue.use(Col);
Vue.use(Tabs);
Vue.use(TabPane);
Vue.use(Tag);
Vue.use(Popover);
Vue.use(Checkbox);
Vue.use(CheckboxGroup);
Vue.use(Loading.directive);
Vue.use(Switch);

Vue.prototype.$loading = Loading.service;

Vue.config.productionTip = false

Vue.prototype.$message = Message;

Vue.prototype.$msgbox = MessageBox;
Vue.prototype.$alert = MessageBox.alert;
Vue.prototype.$confirm = MessageBox.confirm;
Vue.prototype.$prompt = MessageBox.prompt;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
