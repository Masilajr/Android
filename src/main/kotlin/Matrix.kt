package org.example

class Matrix(private val rows: Int, private val cols: Int) {
    private val data: Array<DoubleArray> = Array(rows) { DoubleArray(cols) }

    fun getRows(): Int {
        return rows
    }

    fun getCols(): Int {
        return cols
    }

    fun setElement(row: Int, col: Int, value: Double) {
        require(row in 0 until rows && col in 0 until cols) { "Invalid row or column index." }
        data[row][col] = value
    }

    fun getElement(row: Int, col: Int): Double {
        require(row in 0 until rows && col in 0 until cols) { "Invalid row or column index." }
        return data[row][col]
    }

    fun initialize(values: List<List<Double>>) {
        require(values.size == rows && values.all { it.size == cols }) { "Invalid input dimensions." }
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                data[i][j] = values[i][j]
            }
        }
    }

    fun print() {
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                print("${data[i][j]} ")
            }
            println()
        }
    }

    fun transpose(): Matrix {
        val result = Matrix(cols, rows)
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                result.setElement(j, i, data[i][j])
            }
        }
        return result
    }

    fun scalarMultiply(value: Double): Matrix {
        val result = Matrix(rows, cols)
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                result.setElement(i, j, data[i][j] * value)
            }
        }
        return result
    }

    fun isSquare(): Boolean {
        return rows == cols
    }

    fun determinant() {
        // Implementation of determinant calculation
    }
}