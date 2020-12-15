import Store from '@/store/index';

export default{
  getPageAuthority(firstRoute,secondRoute){
    let obj = Store.state.userAuthority[firstRoute];
    if(secondRoute){
      return obj.children[secondRoute];
    }
    return obj;
  }
}