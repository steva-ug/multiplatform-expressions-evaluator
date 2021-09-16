import kotlin.jvm.JvmInline

sealed interface Token {

    @JvmInline
    value class Operand(val value: Double): Token {
        constructor(value: Int): this(value.toDouble())
    }

    sealed class Operator(val priority: Int, val associativity: Associativity) : Token {
        object Sum : Operator(1, Associativity.LEFT)
        object Sub : Operator(1, Associativity.LEFT)
        object Mult : Operator(2, Associativity.LEFT)
        object Div : Operator(2, Associativity.LEFT)
        object Pow : Operator(3, Associativity.RIGHT)
        object UnaryMinus : Operator(4, Associativity.RIGHT)
        object UnaryPlus : Operator(4, Associativity.RIGHT)
    }

    sealed class Function : Token {
        object Cos : Function()
        object Sin : Function()
        object Tan : Function()
        object Ctan : Function()
        object Ln : Function()

        object Delimeter: Token

        companion object {
            val allFunctions = mapOf(
                "cos" to Cos,
                "sin" to Sin,
                "tan" to Tan,
                "ctan" to Ctan,
                "ln" to Ln
            )
        }
    }

    sealed class Bracket : Token {
        object Left : Bracket()
        object Right : Bracket()
    }

    enum class Associativity {
        LEFT, RIGHT
    }
}