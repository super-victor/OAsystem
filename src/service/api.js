import Store from '@/store/index';
// import router from '@/router';
import {Message,MessageBox,Loading} from 'element-ui';
import axios from 'axios';
import qs from 'qs';
axios.defaults.baseURL = '/api/';

/**
  * @param {Object} options 网络请求用户配置   timeout:超时时间{number}<可选>
  *                                         postHeaderType:post方式时的请求头{String}<可选>
  *                                         throttle:是否需要loading节流{Boolean}<可选>
  *                                         url:API地址{String}<可选>
  *                                         method:请求方式{String}
  *                                         data:请求时携带的参数{Object}<可选>
  * @returns {Promise<Object>} 
*/


export const NetworkRequest = options => {
  return new Promise((resolve,reject)=>{

    let loading = null;
    let {timeout,postHeaderType,throttle,url,method,data} = options;

    axios.defaults.timeout = timeout || 30000;//设置超时时间
    axios.defaults.headers.post['Content-Type'] = postHeaderType || 'application/x-www-form-urlencoded';//设置post方式时的请求头
    axios.defaults.headers.put['Content-Type'] = postHeaderType || 'application/x-www-form-urlencoded';//设置put方式时的请求头
    axios.defaults.headers.delete['Content-Type'] = postHeaderType || 'application/x-www-form-urlencoded';//设置delete方式时的请求头

    //请求拦截
    axios.interceptors.request.use(config=>{
      if(throttle){
        loading = Loading.service({
          lock: true,
          text: '请稍等',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        })
      }
      if(Store.state.userToken!==null){
        config.headers['token'] = Store.state.userToken;
      }
      return config;
    },error=>{
      Message.error({message: '请求超时，请稍后重试'});
    })

    //响应拦截
    axios.interceptors.response.use(response=>{
      let {data,headers} = response;
      if(throttle){
        loading.close();
      }
      //这里判断自定义错误，进行页面跳转等，抛出异常
      if(data.status===301){
        Message.error({message: '权限认证错误'});
        throw new Error('权限认证错误');
      }
      if(data.status===500){
        if(data.msg==='登录失败'){
          if(Store.state.userToken!==null){
            MessageBox.alert('点击确定跳转到登录页面','登录验证错误',{
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            }).then(()=>{
              Store.commit('GET_TOKEN',null);
              //router.replace('/login');
            })
          }
          throw new Error('登录信息错误');
        }else{
          throw new Error(`网络请求错误:${data.msg}`);
        }
      }
      if(headers.token) Store.commit('GET_TOKEN',headers.token);
      return response;
    },error=>{
      return Promise.reject(error);
    })

    if(method=='get'){
      axios.get(url,{
        params:data
      }).then(res=>{
        resolve(res);
      }).catch(err=>{
        reject(err);
      })
    }else{
      let params;
      if(postHeaderType==='multipart/form-data'){
        let formData = new FormData();
        for(let item in data){
          formData.append(item,data[item]);
        }
        params = formData;
      }else if(postHeaderType==='application/json'){
        params = data;
      }else{
        params = qs.stringify(data);
      }
      if(method=='post'){
        axios.post(url,params).then(res=>{
          resolve(res);
        }).catch(err=>{
          reject(err);
        })
      }else if(method=='put'){
        axios.put(url,params).then(res=>{
          resolve(res);
        }).catch(err=>{
          reject(err);
        })
      }else{
        axios.delete(url,params).then(res=>{
          resolve(res);
        }).catch(err=>{
          reject(err);
        })
      }
    }
  })
}