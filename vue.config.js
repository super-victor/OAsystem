const path = require('path');

const ip1 = '192.168.31.116';//局域网
const ip2 = '121.196.40.164';//服务器
const ip3 = '192.168.43.245';
const ip4 = '101.201.254.63';

function resolve(dir){
    return path.join(__dirname,dir)
}

module.exports = {
  chainWebpack:config=>{
    config
    .plugin('html')
    .tap(args => {
    args[0].title= '协同办公平台' //设置标题
    return args
    })
    config.resolve.alias
    .set('@',resolve('./src')) //设置路径别名
  },
  lintOnSave: false,
  devServer: {
      open: true,
      host: 'localhost',
      port: 8080,
      https: false,
      proxy: {
          '/api': {//代理字段设置
              target: `http://${ip3}:8080/`,//填写后台接口
              ws: true,
              changOrigin: true,//设置允许跨域
              pathRewrite: {
                  '^/api': ''//除去代理字段
              }
          }
      }
  },
  //配置网站图标
  pwa: {
    iconPaths: {
        favicon32: 'favicon.ico',
        favicon16: 'favicon.ico',
        appleTouchIcon: 'favicon.ico',
        maskIcon: 'favicon.ico',
        msTileImage: 'favicon.ico',
    }
}
}