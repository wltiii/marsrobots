package com.changent.marsrobots

class CoordinatesTest extends GroovyTestCase {

    void testCoordinatesAcceptsNonNegativeValues() {
        def coordinate = new Coordinates(0, 0)
        assertEquals(0, coordinate.x)
        assertEquals(0, coordinate.y)

        coordinate = new Coordinates(1, 1)
        assertEquals(1, coordinate.x)
        assertEquals(1, coordinate.y)

        coordinate.x = 12
        coordinate.y = 14
        assertEquals(12, coordinate.x)
        assertEquals(14, coordinate.y)

        coordinate = new Coordinates('13 10')
        assertEquals(13, coordinate.x)
        assertEquals(10, coordinate.y)

    }

    void testCoordinatesRejectsNegativeValues() {
        shouldFail(IllegalArgumentException) {
            new Coordinates(-1, -1)
        }
        shouldFail(IllegalArgumentException) {
            new Coordinates('-1 -1')
        }
        def coordinate = new Coordinates(0, 0)

        shouldFail(IllegalArgumentException) {
            coordinate.x = -1
        }
        shouldFail(IllegalArgumentException) {
            coordinate.y = -1
        }
    }

    void testNonIntegerValuesRejected() {
        shouldFail(IllegalArgumentException) {
            new Coordinates('x', 0)
        }
        shouldFail(IllegalArgumentException) {
            new Coordinates(0, 'x')
        }
        shouldFail(IllegalArgumentException) {
            new Coordinates('1.1', 0)
        }
        shouldFail(IllegalArgumentException) {
            new Coordinates(0, '1.1')
        }
        shouldFail(IllegalArgumentException) {
            new Coordinates('x 0')
        }
        shouldFail(IllegalArgumentException) {
            new Coordinates('0 x')
        }
        shouldFail(IllegalArgumentException) {
            new Coordinates('1.1 0')
        }
        shouldFail(IllegalArgumentException) {
            new Coordinates('0 1.1')
        }

        def coordinate = new Coordinates(0, 0)
        shouldFail(IllegalArgumentException) {
            coordinate.x = 'x'
        }
        shouldFail(IllegalArgumentException) {
            coordinate.y = 'y'
        }
        shouldFail(IllegalArgumentException) {
            coordinate.x = '1.1'
        }
        shouldFail(IllegalArgumentException) {
            coordinate.y = '1.1'
        }

    }

}
