package com.keypick.core.model

data class Rank(
    val myRank: MyRank,
    val allRank : List<AllRank>
)

data class MyRank(
    val myRank : String,
    val title : String
)

data class AllRank(
    val rank : Int,
    val title : String,
    val link : String
)