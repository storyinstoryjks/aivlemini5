<template>

    <v-card outlined>
        <v-card-title>
            SaveTemporaryScript
        </v-card-title>

        <v-card-text>
            <Number label="Id" v-model="value.id" :editMode="editMode"/>
            <String label="Title" v-model="value.title" :editMode="editMode"/>
            <String label="Content" v-model="value.content" :editMode="editMode"/>
            <Number label="AuthorId" v-model="value.authorId" :editMode="editMode"/>
            <String label="AuthorName" v-model="value.authorName" :editMode="editMode"/>
            <String label="NotifyStatus" v-model="value.notifyStatus" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="saveTemporaryScript"
            >
                SaveTemporaryScript
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>

export default {
    name: 'SaveTemporaryScriptCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.id = 0;
        this.value.title = '';
        this.value.content = '';
        this.value.authorId = 0;
        this.value.authorName = '';
        this.value.notifyStatus = '';
    },
    watch: {
    },
    methods: {
        saveTemporaryScript() {
            this.$emit('saveTemporaryScript', this.value);
        },
        close() {
            this.$emit('closeDialog');
        },
        change() {
            this.$emit("update:modelValue", this.value);
        },
    }
}
</script>

