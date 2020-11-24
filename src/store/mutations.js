import{
  GET_TOKEN,
  GET_USERINFO,
  UPDATE_USERINFO,
  UPDATE_PAGE_PERMISSIONS,
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
  [UPDATE_USERINFO](state,{email,homeAddress,phone}){
    state.userInfo.email = email;
    state.userInfo.homeAddress = homeAddress;
    state.userInfo.phone = phone;
  },
  [UPDATE_PAGE_PERMISSIONS](state,payload){
    let authority = state.userAuthority;
    for(let firstPage in authority){
      if(!authority[firstPage].children){
        let flag = false;
        let theRoles = authority[firstPage].role;
        for(let role in theRoles){
          let theRole = theRoles[role];
          if(payload[role]==true){
            theRole.own = true;
            flag = true;
          }
        }
        if(flag) authority[firstPage].show = true;
      }else{
        let firstFlag = false;
        let theChildren = authority[firstPage].children;
        for(let secondPage in theChildren){
          let secondFlag = false;
          let theRoles = theChildren[secondPage].role;
          for(let role in theRoles){
            let theRole = theRoles[role];
            if(payload[role]==true){
              theRole.own = true;
              secondFlag = true;
            }
          }
          if(secondFlag){
            theChildren[secondPage].show = true;
            firstFlag = true;
          }
        }
        if(firstFlag) authority[firstPage].show = true;
      }
    }
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