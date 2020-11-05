const path = require('path');

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
              target: 'http://47.100.36.240:3000/',//填写后台接口
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