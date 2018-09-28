package io.github.shadowcreative.chadow.entity

class SimpleEntity : EntityUnit<SimpleEntity>
{
    constructor() : super()

    // The constructor is designed to accept a single String value.
    // This will be required if automatic serialization is required.
    constructor(s : String) : super(s)

    val test : String = "Hello world"
    val test2 : String = "This is test for EntityUnit"
    val test3 : Array<Double> = arrayOf(10.0, 22.7, -1.0)
}
