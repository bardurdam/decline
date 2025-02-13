package com.monovore.decline

import cats.data.{Validated, ValidatedNel}

import _root_.enumeratum._
import _root_.enumeratum.values._

package object enumeratum {

  implicit def enumeratumEnumEntryArgument[A <: EnumEntry](implicit enum: Enum[A]): Argument[A] =
    new Argument[A] {
      override def defaultMetavar: String = "value"

      override def read(string: String): ValidatedNel[String, A] = {
        enum.withNameOption(string) match {
          case Some(v) => Validated.validNel(v)
          case None    => Validated.invalidNel(s"Invalid value: $string")
        }
      }
    }

  implicit def enumeratumIntEnumEntryArgument[A <: IntEnumEntry](implicit enum: IntEnum[A]): Argument[A] =
    new ValueEnumArgument(enum, Argument[Int])

  implicit def enumeratumLongEnumEntryArgument[A <: LongEnumEntry](implicit enum: LongEnum[A]): Argument[A] =
    new ValueEnumArgument(enum, Argument[Long])

  implicit def enumeratumShortEnumEntryArgument[A <: ShortEnumEntry](implicit enum: ShortEnum[A]): Argument[A] =
    new ValueEnumArgument(enum, Argument[Short])

  implicit def enumeratumCharEnumEntryArgument[A <: CharEnumEntry](implicit enum: CharEnum[A]): Argument[A] =
    new ValueEnumArgument(enum, Argument[Char])

  implicit def enumeratumByteEnumEntryArgument[A <: ByteEnumEntry](implicit enum: ByteEnum[A]): Argument[A] =
    new ValueEnumArgument(enum, Argument[Byte])

  implicit def enumeratumStringEnumEntryArgument[A <: StringEnumEntry](implicit enum: StringEnum[A]): Argument[A] =
    new ValueEnumArgument(enum, Argument[String])

}