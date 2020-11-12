const UPDATE_FILEMANAGEMENTINFO = 'UPDATE_FILEMANAGEMENTINFO' //更新sendFileManagementInfo


const state = {
  sendFileManagementInfo:123456
}

const getters = {
  
}

const mutations = {
  [UPDATE_FILEMANAGEMENTINFO](state,payload){
    state.sendFileManagementInfo = payload;
  }
}

const actions = {
  
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}