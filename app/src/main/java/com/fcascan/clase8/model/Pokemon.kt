package com.fcascan.clase8.model

class Pokemon {
    var id: Int = 0
    var name: Map<String, String>? = null
    var type: List<String>? = null
    var base: Map<String, Int>? = null
    var imageURL: String? = null
//
//    init {
//        this.id = id
//        this.name = name
//        this.type = type
//        this.base = base
//        this.imageURL = imageURL
//    }
        override fun toString(): String {
            return """$id) ${name?.get("english")}"""
        }
//    override fun toString(): String {
//        return """
//            ${
//                """
//                ID : $id
//                Name : $name
//                """.trimIndent()
//            }
//            """.trimIndent()
//    }
}