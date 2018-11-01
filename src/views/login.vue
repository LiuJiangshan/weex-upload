<template>
    <div>
        <wxc-button/>
    </div>
</template>

<script>
import {WxcRichText, WxcButton} from 'weex-ui'
export default {
  name: 'login',
  components: {WxcRichText, WxcButton}
}
</script>

<style lang="scss" scoped>
    .login{
        width:100%;
        height: 100%;
    }
</style>
