<template>
    <div>
        <Boolean
            label="IsPurchase"
            v-model="value.isPurchase"
            :editMode="editMode"
        />
        <String
            label="StatusMessage"
            v-model="value.statusMessage"
            :editMode="editMode"
        />
        <Number
            label="UserId"
            v-model="value.userId"
            :editMode="editMode"
        />
        <Number
            label="BookId"
            v-model="value.bookId"
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
    name: 'Reading',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "readings",
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
