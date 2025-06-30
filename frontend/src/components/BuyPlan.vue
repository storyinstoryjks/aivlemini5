<template>

    <v-card outlined>
        <v-card-title>
            BuyPlan
        </v-card-title>

        <v-card-text>
            <Boolean label="IsPurchase" v-model="value.isPurchase" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="buyPlan"
            >
                BuyPlan
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
    name: 'BuyPlanCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.isPurchase = false;
    },
    watch: {
    },
    methods: {
        buyPlan() {
            this.$emit('buyPlan', this.value);
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

