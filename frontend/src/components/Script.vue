<template>
    <div>
        <String
            label="Title"
            v-model="value.title"
            :editMode="editMode"
        />
        <String
            label="Content"
            v-model="value.content"
            :editMode="editMode"
        />
        <Number
            label="AuthorId"
            v-model="value.authorId"
            :editMode="editMode"
        />
        <String
            label="AuthorName"
            v-model="value.authorName"
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
    name: 'Script',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "scripts",
        value: {
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
        async save() {
            try {
                await axios.post(`/api/scripts/savescript`, this.value)
                alert('저장 성공')
            } catch (e) {
                alert('저장 실패')
            }
        },
        async savetemporaryscript() {
            try {
                const id = this.value.id
                await axios.put(`/api/scripts/${id}/SaveTemporaryScript`, this.value)
                alert('임시 저장 성공')
            } catch (e) {
                alert('임시 저장 실패')
            }
        }
    },
}
</script>
