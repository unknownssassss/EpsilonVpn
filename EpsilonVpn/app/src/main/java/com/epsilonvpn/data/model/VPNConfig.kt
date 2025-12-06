package com.epsilonvpn.data.model

data class VPNConfig(
    val host: String,
    val port: Int,
    val uuid: String,
    val alterId: Int
)
