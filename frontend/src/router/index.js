import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/authors',
      component: () => import('../components/ui/AuthorGrid.vue'),
    },
    {
      path: '/scripts',
      component: () => import('../components/ui/ScriptGrid.vue'),
    },
    {
      path: '/publishings',
      component: () => import('../components/ui/PublishingGrid.vue'),
    },
    {
      path: '/userInfos',
      component: () => import('../components/ui/UserInfoGrid.vue'),
    },
    {
      path: '/books',
      component: () => import('../components/ui/BookGrid.vue'),
    },
    {
      path: '/points',
      component: () => import('../components/ui/PointGrid.vue'),
    },
    {
      path: '/readings',
      component: () => import('../components/ui/ReadingGrid.vue'),
    },
  ],
})

export default router;
