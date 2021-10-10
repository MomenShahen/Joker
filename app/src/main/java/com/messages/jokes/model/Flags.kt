package com.messages.jokes.model

data class Flags(
    var nsfw: Boolean,
    var religious: Boolean,
    var political: Boolean,
    var racist: Boolean,
    var sexist: Boolean,
    var explicit: Boolean
) {

}