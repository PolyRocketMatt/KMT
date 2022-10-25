package com.github.polyrocketmatt.kmt.group.set

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SimpleSetTest {

    private var setA = SimpleSet<Int>()
    private var setB = SimpleSet<Int>()
    private var elementsA = intArrayOf(1, 2, 3, 4, 5).toSet()
    private var elementsB = intArrayOf(3, 4, 5, 6, 7).toSet()

    @BeforeEach
    fun setup() {
        setA = SimpleSet(elementsA)
        setB = SimpleSet(elementsB)
    }


    @Test
    fun testEmptyConstructor() {
        val emptySet = SimpleSet<Int>()

        assertTrue { emptySet.isEmpty() }
    }

    @Test
    fun testVarargConstructor() {
        val set = SimpleSet(1, 2, 3, 4, 5)

        assertTrue { elementsA.all { set.contains(it) } }
    }

    @Test
    fun testConstructor() {
        assertTrue { elementsA.all { setA.contains(it) } }
    }

    @Test
    fun testSingleton() {
        val set = SimpleSet(1)

        assertTrue { set.isSingleton() }
    }

    @Test
    fun testCardinality() {
        assertTrue { setA.card() == elementsA.size }
    }

    @Test
    fun testContains() {
        assertTrue { elementsA.all { setA.contains(it) } }
        assertFalse { setA.contains(6) }
    }

    @Test
    fun testSubset() {
        val subsetA = SimpleSet(1, 2, 3)
        val subsetB = SimpleSet(1, 2, 3, 4, 5)
        val subsetC = SimpleSet(1, 2, 6)

        assertTrue { setA.isSubset(subsetA) }
        assertTrue { setA.isSubset(subsetB) }
        assertFalse { setA.isSubset(subsetC) }
    }

    @Test
    fun testUnion() {
        val union = setA.union(setB)

        assertTrue { elementsA.all { union.contains(it) } }
        assertTrue { elementsB.all { union.contains(it) } }
        assertTrue { union.card() == (elementsA + elementsB).distinct().size }
    }

    @Test
    fun testIntersection() {
        val intersection = setA.intersection(setB)

        assertTrue { intersection.all { elementsA.contains(it) && elementsB.contains(it) } }
        assertTrue { intersection.card() == elementsA.intersect(elementsB).size }
    }

    @Test
    fun testDifference() {
        val difference = setA.difference(setB)

        assertTrue { difference.all { elementsA.contains(it) && !elementsB.contains(it) } }
        assertTrue { difference.card() == elementsA.subtract(elementsB).size }
    }

    @Test
    fun testSymmetricDifference() {
        val symmetricDiff = setA.symmetricDifference(setB)

        assertTrue { symmetricDiff.all { elementsA.contains(it) xor elementsB.contains(it) } }
        assertTrue { symmetricDiff.card() == elementsA.union(elementsB).subtract(elementsA.intersect(elementsB)).size }
    }

    @Test
    fun testCartesianProduct() {
        val cartesianProduct = setA.cartesianProduct(setB)

        assertTrue { cartesianProduct.all { elementsA.contains(it.first) && elementsB.contains(it.second) } }
        assertTrue { cartesianProduct.card() == elementsA.size * elementsB.size }
    }

    @Test
    fun testComplement() {
        val universe = SimpleSet(1, 2, 3, 4, 5, 6, 7)
        val complement = setA.complement(universe)

        assertTrue { complement.all { !elementsA.contains(it) } }
        assertTrue { complement.card() == universe.card() - setA.card() }
    }

    @Test
    fun testMap() {
        val map = { it: Int -> it * 2 }
        val mapped = setA.map(map)

        setA.forEachIndexed { idx, i -> assertTrue { map(i) == mapped.elementAt(idx) } }
    }

}