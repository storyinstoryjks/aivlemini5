<template>
    <div>
        <String
            label="Title"
            v-model="value.title"
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
        <String
            label="Category"
            v-model="value.category"
            :editMode="editMode"
        />
        <String
            label="Content"
            v-model="value.content"
            :editMode="editMode"
        />
        <String
            label="SummaryContent"
            v-model="value.summaryContent"
            :editMode="editMode"
        />
        <String
            label="CoverImagePath"
            v-model="value.coverImagePath"
            :editMode="editMode"
        />
        <String
            label="PdfPath"
            v-model="value.pdfPath"
            :editMode="editMode"
        />
        <Number
            label="Price"
            v-model="value.price"
            :editMode="editMode"
        />
        <Boolean
            label="NotifyStatus"
            v-model="value.notifyStatus"
            :editMode="editMode"
        />
        <Number
            label="ManuscriptId"
            v-model="value.manuscriptId"
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
    name: 'Publishing',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "publishings",
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
