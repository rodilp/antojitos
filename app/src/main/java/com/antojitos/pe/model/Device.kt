package com.antojitos.pe.model

data class Device(val versionRelease: String,
                  val versionIncremental: String,
                  val versionSdkNumber: Int,
                  val board: String,
                  val bootloader: String,
                  val brand: String,
                  val cpu_abi: String,
                  val cpu_abi2: String,
                  val display: String,
                  val fingerprint: String,
                  val hardware: String,
                  val host: String,
                  val id: String,
                  val manufacturer: String,
                  val model: String,
                  val product: String,
                  val serial: String,
                  val tags: String,
                  val time: Long,
                  val type: String,
                  val unknown: String,
                  val user: String


)


