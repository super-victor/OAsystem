import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'
import state from './state'
import mutations from './mutations'
import actions from './actions'
import getters from './getters'

const vuexLocal = new VuexPersistence({
  storage: window.sessionStorage
})

Vue.use(Vuex)

const store = new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
  plugins: [vuexLocal.plugin]
})

//动态加载vuex模块
function createModules(arr){
  arr.keys().forEach(key=>{
    store.registerModule(key.replace(/\.\/|\/(.*)\.js/g, ''),arr(key).default);
  })
}

let modulesContext = require.context('./', true, /^\.\/[^/]+\/.+\.js$/);
createModules(modulesContext);

export default store