const UPDATE_FILEMANAGEMENTINFO = 'UPDATE_FILEMANAGEMENTINFO' //更新DocumentCirculationInfo


const state = {
  DocumentCirculationInfo:123456
}

const getters = {
  
}

const mutations = {
  [UPDATE_FILEMANAGEMENTINFO](state,payload){
    state.DocumentCirculationInfo = payload;
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