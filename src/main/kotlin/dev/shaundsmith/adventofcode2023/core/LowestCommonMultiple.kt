package dev.shaundsmith.adventofcode2023.core

class LowestCommonMultiple {

    // Grabbed LCM algorithm from StackOverflow
    companion object {

        fun lcm(numbers: List<Long>): Long {

            return numbers.reduce { acc: Long, l: Long -> lcm(acc, l) }
        }

        private fun lcm(a: Long, b: Long): Long {

            return a * (b / gcd(a, b))
        }

        private fun gcd(initialA: Long, initialB: Long): Long {

            var a = initialA
            var b = initialB
            while (b > 0) {
                val temp = b
                b = a % b
                a = temp
            }
            return a
        }


    }

}