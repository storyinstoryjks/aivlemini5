<template>
    <div>
        <String
            label="UserName"
            v-model="value.userName"
            :editMode="editMode"
        />
        <Boolean
            label="IsPurchase"
            v-model="value.isPurchase"
            :editMode="editMode"
        />
        <Date
            label="PlanStartDate"
            v-model="value.planStartDate"
            :editMode="editMode"
        />
        <Date
            label="PlanEndDate"
            v-model="value.planEndDate"
            :editMode="editMode"
        />
        <String
            label="LoginId"
            v-model="value.loginId"
            :editMode="editMode"
        />
        <String
            label="LoginPw"
            v-model="value.loginPw"
            :editMode="editMode"
        />
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'UserInfo',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "userInfos",
        value: {
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
