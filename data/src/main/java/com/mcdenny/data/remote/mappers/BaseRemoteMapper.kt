package com.mcdenny.data.remote.mappers

interface BaseRemoteMapper<FROM, TO> {
    fun toDomain(entity: FROM): TO
}