package com.hemanth.idrive.data.model


import com.google.gson.annotations.SerializedName

data class QuestionResponse(
    @SerializedName("tree")
    var tree: Tree
) {
    data class Tree(
        @SerializedName("item")
        var item: List<Item>
    ) {
        data class Item(
            @SerializedName("_answer")
            var answer: String,
            @SerializedName("_question")
            var question: String,
            var isSelected: Boolean = false
        )
    }
}