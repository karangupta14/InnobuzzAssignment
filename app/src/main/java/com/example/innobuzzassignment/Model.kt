package com.example.innobuzzassignment

public class Model {
    lateinit var userId:Integer
    lateinit var id:Integer
    lateinit var title:String
    lateinit var body:String
    constructor(userId:Integer, id:Integer, title:String, body:String){
        this.userId = userId
        this.id = id
        this.title = title
        this.body = body
    }
}