<template>
    <wxc-tab-bar class="app" :tab-titles="tabTitles" :tab-styles="tabStyles">
        <home :style="contentStyle"/>
        <div :style="contentStyle" style="background-color: green;">
            <user/>
        </div>
    </wxc-tab-bar>
</template>

<script>
import { Utils, WxcTabBar } from 'weex-ui'
import Home from '@/views/home'
import User from '@/views/user'

const modal = weex.requireModule('modal')
export default {
  name: 'app',
  components: {User, Home, WxcTabBar},
  computed: {
    contentStyle () {
      const tabPageHeight = Utils.env.getPageHeight()
      modal.toast({message: tabPageHeight})
      return {height: (tabPageHeight - this.tabStyles.height) + 'px'}
    }
  },
  data () {
    return {
      tabTitles: [
        {
          title: '首页',
          icon: 'https://gw.alicdn.com/tfs/TB1MWXdSpXXXXcmXXXXXXXXXXXX-72-72.png',
          activeIcon: 'https://gw.alicdn.com/tfs/TB1kCk2SXXXXXXFXFXXXXXXXXXX-72-72.png'
        },
        {
          title: '我的主页',
          icon: 'https://gw.alicdn.com/tfs/TB1Do3tSXXXXXXMaFXXXXXXXXXX-72-72.png',
          activeIcon: 'https://gw.alicdn.com/tfs/TB1LiNhSpXXXXaWXXXXXXXXXXXX-72-72.png'
        }
      ],
      tabStyles: {
        bgColor: '#FFFFFF',
        titleColor: '#666666',
        activeTitleColor: '#3D3D3D',
        activeBgColor: '#FFFFFF',
        isActiveTitleBold: true,
        iconWidth: 70,
        iconHeight: 70,
        width: 160,
        height: 120,
        fontSize: 24,
        textPaddingLeft: 10,
        textPaddingRight: 10
      }
    }
  }
}
</script>

<style lang="scss" scoped>
    @import "~ljs-sass/src/mixin";

    .app {
    }
</style>
