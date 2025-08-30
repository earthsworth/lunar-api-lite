package org.cubewhy.lunar_api.factory

import com.google.protobuf.Timestamp
import java.time.Instant

object TimestampFactory {
    fun fromInstant(instant: Instant): Timestamp = Timestamp.newBuilder()
        .setSeconds(instant.epochSecond)
        .setNanos(instant.nano)
        .build()
}