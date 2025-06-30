<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: '작가관리' }" />
        <VerticalNavLink
            :item="{
                title: '작가',
                to: '/authors',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '집필관리' }" />
        <VerticalNavLink
            :item="{
                title: '원고',
                to: '/scripts',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: 'AI서비스' }" />
        <VerticalNavLink
            :item="{
                title: '출간',
                to: '/publishings',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '회원관리' }" />
        <VerticalNavLink
            :item="{
                title: '회원정보',
                to: '/userInfos',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '서재' }" />
        <VerticalNavLink
            :item="{
                title: '도서',
                to: '/books',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '도서 조회',
                to: '/getAllLibraries',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '포인트관리' }" />
        <VerticalNavLink
            :item="{
                title: '포인트',
                to: '/points',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '포인트 조회',
                to: '/getAllPoints',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '열람관리' }" />
        <VerticalNavLink
            :item="{
                title: '열람',
                to: '/readings',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '열람내역 조회',
                to: '/readAllBooks',
            }"
        />
    </ul>
</template>
