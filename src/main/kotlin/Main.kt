package org.example

fun add(a: Int, b: Int): Int {
    return a + b
}
fun multiply(a: Int, b: Int): Int {
    return a * b
}
fun subtract(c: Int, d:Int): Int{
    return d - c
}
fun divide(a: Int, b: Int): Double{
    if(b == 0){
        throw IllegalArgumentException("Division by 0 is not allowed")
    }
    return a.toDouble()/b.toDouble()
}

fun main() {
    val result = add(6, 3)
    println("Result of addition: $result")

    val resultMultiply = multiply(5, 3)
    println("Result of multiplication: $resultMultiply")

    val resultSubtract = subtract(10,8)
    println("Result of subtraction: $resultSubtract")

    val resultDivide = divide(3,7)
    println("Result of division: $resultDivide")

    val matrix1 = Matrix(2, 2)
    val matrix2 = Matrix(2, 2)

    val values = listOf(
        listOf(1.0, 5.0),
        listOf(4.0, 4.0),
//        listOf(7.0, 2.0)
    )

    val matrix = Matrix(2, 2)
    matrix.initialize(values)


// Initialize matrices with values for matrix1
    matrix1.initialize(listOf(listOf(1.0, 2.0), listOf(3.0, 4.0)))

// Initialize matrices with values for matrix2
    matrix2.initialize(listOf(listOf(5.0, 5.0), listOf(7.0, 8.0)))

// Perform matrix addition
    val resultAddition = MatrixOperations.add(matrix1, matrix2)
    println("Result of addition:")
    resultAddition.print()

// Perform matrix subtraction
    val resultSubtraction = MatrixOperations.subtract(matrix1, matrix2)
    println("Result of subtraction:")
    resultSubtraction.print()

// Perform matrix multiplication
    val resultMultiplication = MatrixOperations.multiply(matrix1, matrix2)
    println("Result of multiplication:")
    resultMultiplication.print()

    val resultDeterminant = MatrixOperations.determinant(matrix)
    println("The determinant is:$resultDeterminant")

}