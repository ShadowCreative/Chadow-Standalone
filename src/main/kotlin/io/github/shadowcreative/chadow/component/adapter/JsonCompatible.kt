package io.github.shadowcreative.chadow.component.adapter

import com.google.gson.JsonDeserializer
import com.google.gson.JsonSerializer

interface JsonCompatible<T> : JsonSerializer<T>, JsonDeserializer<T>