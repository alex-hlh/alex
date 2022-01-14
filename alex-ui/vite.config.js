import vue from '@vitejs/plugin-vue'
import styleImport from 'vite-plugin-style-import'
const path = require('path')
// vite.config.js # or vite.config.ts

module.exports = {
  plugins:[
    vue(),
    styleImport({
      libs: [
        {
          libraryName: 'element-plus',
          esModule: true,
          ensureStyleFile: true,
          resolveStyle: (name) => {
            return `element-plus/lib/theme-chalk/${name}.css`;
          },
          resolveComponent: (name) => {
            return `element-plus/lib/${name}`;
          },
        }
      ]
    })
  ],
  resolve:{
    alias: {
      "@": path.resolve(__dirname, "src"),
      "components": path.resolve(__dirname, "src/components"),
      "plugins": path.resolve(__dirname, "src/plugins"),
      "views": path.resolve(__dirname, "src/views"),
      "utils": path.resolve(__dirname, "src/utils"),
      "apis": path.resolve(__dirname, "src/apis"),
      "dirs": path.resolve(__dirname, "src/directives"),
    },
  },
  chainWebpack: config => {
    config.resolve.alias.set('vue-i18n', 'vue-i18n/dist/vue-i18n.cjs.js')
  },
  hostname: 'localhost',
  port: 8080,
  // 是否自动在浏览器打开
  open: true,
  // 是否开启 https
  https: false,
  // 服务端渲染
  ssr: false,
  /**
   * 在生产中服务时的基本公共路径。
   * @default '/'
   */
  base: './',
  /**
   * 与“根”相关的目录，构建输出将放在其中。如果目录存在，它将在构建之前被删除。
   * @default 'dist'
   */
  optimizeDeps: {
    include: ['schart.js']
  },
  outDir: 'dist',
  // 反向代理
  proxy: {
    '/api': {
      target: 'https://localhost:8081',
      changeOrigin: true,
      rewrite: path => path.replace( '^/','/')
    }
  }
}
