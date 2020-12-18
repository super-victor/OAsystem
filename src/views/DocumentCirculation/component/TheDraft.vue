!<!-- TheDraft -->
<template>
  <div class='TheDraft'>
    <div class="headerBox">
      <p class="header">师大发展有限公司文件</p>
      <div class="headerInfo">
        <p class="headerId">师大发展发 [{{getYear}}] {{getId}}号</p>
        <p class="sender">签发人：<span class="s1">{{fileInfo.senderDetail && fileInfo.senderDetail.name}}</span></p>
      </div>
    </div>
    <div class="typeBox">{{fileInfo.type}} <span class="s2" v-if="fileInfo.urgent!='正常'">[{{fileInfo.urgent}}]</span> </div>
    <div class="titleBox">
      <p class="title">{{fileInfo.title}}</p>
    </div>
    <div class="contentBegin">{{fileInfo.target && fileInfo.target.split(" ")[2]}}：</div>
    <div class="content" v-html="fileInfo.content"></div>
    <div class="remark">附件：</div>
    <div class="remarkBox">
      <div v-if="fileInfo.annuxUrlList && fileInfo.annuxUrlList.length!=0" style="width:100%;">
        <div
        v-for="item in fileInfo.annuxUrlList"
        :key="item"
        @click="download(item)"
        class="remarkItem">
          <i class="el-icon-paperclip" style="font-size:0.24rem;"></i>
          <p class="name">{{item.split('/')[2]}}</p>
        </div>
      </div>
      <p class="noRemark" v-else>无附件</p>
    </div>
    <div class="censorBox">
      <img src="@/assets/Document/company_seal.png" alt="" class="censorPass" v-if="fileInfo.status==1">
      <div class="censor">
        <div class="censorName">审稿人：<span class="s3">{{fileInfo.censorDetail && fileInfo.censorDetail.name}}</span></div>
        <div class="censorDate">{{getCensorDate}}</div>
      </div>
    </div>
    <div class="subjectBox">
      <p class="subject1"><span style="font-weight:bolder;">主题词：</span><span class="subject2">{{fileInfo.subject}}</span></p>
    </div>
    <div class="sendBox">发：<span class="send1">{{getAccessEmployee}}</span></div>
    <div class="bottomBox">
      <p class="bottom1">师大发展有限公司</p>
      <p class="bottom2">{{getDate}} 印</p>
    </div>
  </div>
</template>

<script>
  import {mapState} from 'vuex';
  export default {
    components: {},
    props:{
      fileInfo:{
        type:Object,
        required:true
      }
    },
    data() {
      return {
      };
    },
    computed: {
      ...mapState(['currentIP']),
      getYear:function(){
        return this.fileInfo.updateTime && this.fileInfo.updateTime.substring(0,4);
      },
      getId:function(){
        return this.fileInfo.sendfileId && this.fileInfo.sendfileId.toString().padStart(3,0);
      },
      getCensorDate:function(){
        if(this.fileInfo.sendfileId){
          let time = new Date(+new Date(new Date(this.fileInfo.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '').toString().split(' ')[0];
          let timeArr = time.split('-');
          let str = '';
          //处理年
          for(let i=0;i<timeArr[0].length;i++){
            str += this.getChineseNumber(timeArr[0].charAt(i));
          }
          str += '年';
          //处理月
          if(Number(timeArr[1])<10){
            str += this.getChineseNumber(Number(timeArr[1]).toString());
          }else{
            switch(timeArr[1]){
              case '10':
                str += '十';
                break;
              case '11':
                str += '十一';
                break;
              case '12':
                str += '十二';
                break;
            }
          }
          str += '月';
          //处理日
          let num = Number(timeArr[2]);
          if(num<10){
            str += this.getChineseNumber(num.toString());
          }else if(num>=10 && num<20){
            if(num==10) str += '十';
            else{
              str += '十'+this.getChineseNumber(num.toString().charAt(1));
            }
          }else if(num>=20 && num<30){
            if(num==20) str += '二十';
            else{
              str += '二十'+this.getChineseNumber(num.toString().charAt(1));
            }
          }else{
            if(num==30) str += '三十';
            else str += '三十一';
          }
          str += '日';
          return str;
        }else{
          return '';
        }
      },
      getDate:function(){
        let time = new Date(+new Date(new Date(this.fileInfo.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '').toString().split(' ')[0];
        let timeArr = time.split('-');
        return timeArr[0] + '年' + timeArr[1] + '月' + timeArr[2] + '日';
      },
      getAccessEmployee:function(){
        if(this.fileInfo.accessEmployeeIdList){
          if(this.fileInfo.isPublic===0){
            let str = '';
            for(let item of this.fileInfo.accessEmployeeIdList){
              str += item.departmentName+item.name+'、';
            }
            return str.substring(0,str.length-1);
          }else{
            return '全体成员';
          }
        }else{
          return '';
        }
      }
    },
    watch: {},
    methods: {
      getChineseNumber(num){
        switch(num){
          case '0':
            return '〇';
          case '1':
            return '一';
          case '2':
            return '二';
          case '3':
            return '三';
          case '4':
            return '四';
          case '5':
            return '五';
          case '6':
            return '六';
          case '7':
            return '七';
          case '8':
            return '八';
          case '9':
            return '九';
        }
      },
      download(item){
        window.open(`http://${this.currentIP}:8080/download${item}`);
      }
    },
    created() {
      
    },
    mounted() {
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .TheDraft{
    box-sizing: border-box;
    box-shadow: 0 0 13px 0 rgba(82,63,105,0.05);
    height: auto;
    width: 100%;
    padding: 30px;
    background-color: @white;
    .headerBox{
      height: 2.5rem;
      width: 100%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .header{
        height: 1.8rem;
        width: 100%;
        font-size: 0.98rem;
        text-align: center;
        line-height: 1.8rem;
        color: #EA3323;
        font-family:'SourceHan-S-SC24d69ea73622004';
      }
      .headerInfo{
        height: 0.5rem;
        width: 100%;
        box-sizing: border-box;
        border-bottom: 3px solid #EA3323;
        display: flex;
        justify-content: space-between;
        .headerId{
          height: 0.4rem;
          width: 5rem;
          font-size: 0.3rem;
          line-height: 0.4rem;
          color: @black;
          font-family:'klfangsong2519f0898a22004';
        }
        .sender{
          height: 0.4rem;
          width: 6rem;
          font-size: 0.3rem;
          line-height: 0.4rem;
          color: @black;
          font-family:'klfangsong2519f0898a22004';
          text-align: right;
          .s1{
            font-family:"Hiragino Sans GB";
          }
        }
      }
    }
    .typeBox{
      margin-top: 0.1rem;
      height: 0.4rem;
      width: 20%;
      font-size: 0.25rem;
      line-height: 0.4rem;
      color: @black;
      font-family:'klfangsong2519f0898a22004';
      .s2{
        color:#EA3323;
        font-weight: bolder;
      }
    }
    .titleBox{
      height: 2rem;
      width: 100%;
      padding-top: 1px;
      .title{
        margin-top: 0.5rem;
        height: 0.6rem;
        width: 100%;
        font-family:"Hiragino Sans GB";
        color: @black;
        font-size: 0.5rem;
        line-height: 0.6rem;
        text-align: center;
        word-break:break-all;
      }
    }
    .contentBegin{
      height: 0.5rem;
      width: 35%;
      font-size: 0.3rem;
      line-height: 0.5rem;
      color: @black;
      font-family:'klfangsong2519f0898a22004';
    }
    .content{
      padding: 0 0.5rem;
      box-sizing: border-box;
      min-height: 3rem;
      height: auto;
      width: 100%;
      margin-top: 0.2rem;
      font-size: 0.25rem;
    }
    .remark{
      margin-top: 0.7rem;
      height: 0.5rem;
      width: 35%;
      font-size: 0.3rem;
      line-height: 0.5rem;
      color: @black;
      font-family:'klfangsong2519f0898a22004';
    }
    .remarkBox{
      margin-top: 0.2rem;
      min-height: 1rem;
      height: auto;
      width: 100%;
      display: flex;
      align-items: center;
      .noRemark{
        height: 0.35rem;
        width: 100%;
        font-family:"klfangsong2519f0898a22004";
        color: @black;
        font-size: 0.23rem;
        line-height: 0.35rem;
        text-align: center;
      }
      .remarkItem{
        height: 0.6rem;
        width: 90%;
        margin: 0.1rem 0 0 5%;
        background-color: @white;
        border-radius: @baseBorderRadius;
        transition: all .3s;
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: space-between;
        box-sizing: border-box;
        padding: 0 15px;
        border: 1px solid @lighterBorder;
        .name{
          height: 0.4rem;
          line-height: 0.4rem;
          width: calc(100% - 0.45rem);
          font-size: 0.21rem;
        }
      }
      .remarkItem:hover{
        background-color: @lighterBackground;
      }
    }
    .censorBox{
      margin-top: 0.5rem;
      height: 2.5rem;
      width: 100%;
      display: flex;
      justify-content: flex-end;
      .censorPass{
        height: 2.5rem;
        width: 2.5rem;
        position: relative;
        right: -3.5rem;
      }
      .censor{
        height: 100%;
        width: 4.5rem;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: space-around;
        .censorName{
          height: 0.7rem;
          width: 100%;
          color: @black;
          font-size: 0.35rem;
          line-height: 0.7rem;
          text-align: center;
          font-family:"klfangsong2519f0898a22004";
          .s3{
            font-family:"Hiragino Sans GB";
          }
        }
        .censorDate{
          height: 0.7rem;
          width: 100%;
          color: @black;
          font-size: 0.35rem;
          line-height: 0.7rem;
          text-align: center;
          font-family:"klfangsong2519f0898a22004";
        }
      }
    }
    .subjectBox{
      margin-top: 0.5rem;
      height: 0.6rem;
      width: 100%;
      border-bottom: 1px solid @black;
      .subject1{
        height: 0.4rem;
        color: @black;
        font-size: 0.25rem;
        line-height: 0.4rem;
        font-family:'SiYuanRegular24d69b231122004';
      }
      .subject2{
        font-family:"Hiragino Sans GB";
      }
    }
    .sendBox{
      margin-top: 0.2rem;
      min-height: 0.25rem;
      height: auto;
      width: 100%;
      color: @black;
      font-size: 0.25rem;
      font-family:'klfangsong2519f0898a22004';
      word-break:break-all;
      box-sizing: border-box;
      padding-bottom: 0.25rem;
      border-bottom: 1px solid @black;
      .send1{
        font-family:"Hiragino Sans GB";
      }
    }
    .bottomBox{
      margin-top: 0.2rem;
      height: 0.4rem;
      width: 100%;
      display: flex;
      justify-content: space-between;
      .bottom1{
        height: 0.4rem;
        width: 3rem;
        line-height: 0.4rem;
        color: @black;
        font-size: 0.22rem;
        font-family:"klfangsong2519f0898a22004";
      }
      .bottom2{
        height: 0.4rem;
        width: 3rem;
        line-height: 0.4rem;
        text-align: right;
        color: @black;
        font-size: 0.22rem;
        font-family:"klfangsong2519f0898a22004";
      }
    }
  }
  /* table 样式 */
  ::v-deep table {
    border-top: 1px solid #ccc;
    border-left: 1px solid #ccc;
  }
  ::v-deep table td,
  table th {
    border-bottom: 1px solid #ccc;
    border-right: 1px solid #ccc;
    padding: 3px 5px;
  }
  ::v-deep table th {
    border-bottom: 2px solid #ccc;
    text-align: center;
  }

  /* blockquote 样式 */
  ::v-deep blockquote {
    display: block;
    border-left: 8px solid #d0e5f2;
    padding: 5px 10px;
    margin: 10px 0;
    line-height: 1.4;
    font-size: 100%;
    background-color: #f1f1f1;
  }

  /* code 样式 */
  ::v-deep code {
    display: inline-block;
    *display: inline;
    *zoom: 1;
    background-color: #f1f1f1;
    border-radius: 3px;
    padding: 3px 5px;
    margin: 0 3px;
  }
  ::v-deep pre code {
    display: block;
  }

  /* ul ol 样式 */
  ::v-deep ul, ol {
    margin: 10px 0 10px 20px;
  }
</style>