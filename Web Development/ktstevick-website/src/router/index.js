import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PortfolioView from '../views/PortfolioView.vue'
import AboutView from '../views/AboutView.vue' // Not present in the initial code
import FineArtsView from '../views/FineArtsView.vue'
import GraphicDesignView from '../views/GraphicDesignView.vue'
import SoftwareView from '../views/SoftwareView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/portfolio',
      name: 'portfolio',
      component: PortfolioView
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView

      /* Vue had this as the default - it's still here in case I decide to implement it later */

      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.

      // component: () => import('../views/AboutView.vue')
    },
    {
      path: '/portfolio/finearts',
      name: 'fineArts',
      component: FineArtsView
    },
    {
      path: '/portfolio/graphicdesign',
      name: 'graphicDesign',
      component: GraphicDesignView
    },
    {
      path: '/portfolio/software',
      name: 'software',
      component: SoftwareView
    }
  ]
})

export default router
