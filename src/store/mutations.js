import{
  GET_TOKEN,
  GET_USERINFO,
  ASIDE_CLICK,
  ITEM_CLICK,
  MAIN_CLICK
} from './mutation_types'

export default{
  [GET_TOKEN](state,payload){
    state.userToken = payload;
  },
  [GET_USERINFO](state,payload){
    state.userInfo = payload;
  },
  [ASIDE_CLICK](state,payload){
    state.asideItem = payload;
  },
  [ITEM_CLICK](state,payload){
    state.mouseClickIndex = payload;
  },
  [MAIN_CLICK](state,payload){
    state.allowShowItem = payload;
  }
}