package com.mcdenny.nssfweather.mappers

interface BaseUiMapper<DOMAIN, UI> {
    fun toUi(entity: DOMAIN): UI
}