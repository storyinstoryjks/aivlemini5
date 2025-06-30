<template>
    <div>
        <Number
            label="Point"
            v-model="value.point"
            :editMode="editMode"
        />
        <Number
            label="ReadingId"
            v-model="value.readingId"
            :editMode="editMode"
        />
        <Number
            label="UserId"
            v-model="value.userId"
            :editMode="editMode"
        />
        <ApplyingId
            offline
            label="열람 ID"
            v-model="value.applyingId"
            :editMode="editMode"
            @change="change"
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
    name: 'Point',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "points",
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
