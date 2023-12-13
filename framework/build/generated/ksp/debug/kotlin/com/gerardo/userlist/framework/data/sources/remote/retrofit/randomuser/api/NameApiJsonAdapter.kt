// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress("DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN", "IMPLICIT_NOTHING_TYPE_ARGUMENT_IN_RETURN_POSITION")

package com.gerardo.userlist.framework.`data`.sources.remote.retrofit.randomuser.api

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.`internal`.Util
import java.lang.NullPointerException
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.emptySet
import kotlin.text.buildString

public class NameApiJsonAdapter(
  moshi: Moshi,
) : JsonAdapter<NameApi>() {
  private val options: JsonReader.Options = JsonReader.Options.of("first", "last")

  private val stringAdapter: JsonAdapter<String> = moshi.adapter(String::class.java, emptySet(),
      "first")

  public override fun toString(): String = buildString(29) {
      append("GeneratedJsonAdapter(").append("NameApi").append(')') }

  public override fun fromJson(reader: JsonReader): NameApi {
    var first: String? = null
    var last: String? = null
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> first = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull("first", "first",
            reader)
        1 -> last = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull("last", "last",
            reader)
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    return NameApi(
        first = first ?: throw Util.missingProperty("first", "first", reader),
        last = last ?: throw Util.missingProperty("last", "last", reader)
    )
  }

  public override fun toJson(writer: JsonWriter, value_: NameApi?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("first")
    stringAdapter.toJson(writer, value_.first)
    writer.name("last")
    stringAdapter.toJson(writer, value_.last)
    writer.endObject()
  }
}