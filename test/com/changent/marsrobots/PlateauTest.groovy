package com.changent.marsrobots

class PlateauTest extends GroovyTestCase {
    void testUpperBounds() {
        shouldFail(IllegalArgumentException) {
            def nullObj
            new Plateau(nullObj)
        }
        shouldFail(IllegalArgumentException) {
            new Plateau('')
        }
        shouldFail(IllegalArgumentException) {
            new Plateau(' ')
        }
        shouldFail(IllegalArgumentException) {
            new Plateau('-1 1')
        }
        shouldFail(IllegalArgumentException) {
            new Plateau('1 -1')
        }
    }

    void testBoundaries() {
        def plateau = new Plateau('7 4')
        def actual = plateau.boundaries
        def expected = [0,0,7,4]
        assertEquals 0, plateau.lowerBoundary.x
        assertEquals 0, plateau.lowerBoundary.y
        assertEquals 7, plateau.upperBoundary.x
        assertEquals 4, plateau.upperBoundary.y
        assertArrayEquals(expected.toArray(), actual.toArray())
    }

    void testValidateCoordinates() {
        def plateau = new Plateau('5 5')
        assert plateau.validateCoordinates(5,5)
        assertFalse plateau.validateCoordinates(5,6)
        assertFalse plateau.validateCoordinates(6,5)
        assert plateau.validateCoordinates(0,0)

        shouldFail(IllegalArgumentException) {
            plateau.validateCoordinates(0,-1)
        }
        shouldFail(IllegalArgumentException) {
            plateau.validateCoordinates(-1,-0)
        }

    }

    void testCreateRobot() {
        def plateau = new Plateau('5 5')
        def marvin = plateau.createRobot('1 2 N')
        assertEquals plateau, marvin.plateau
        assertEquals 1, marvin.coordinates.x
        assertEquals 2, marvin.coordinates.y
        assertEquals 'N', marvin.heading

        def robby = plateau.createRobot('4 3 W')
        assertNotNull robby.id
        assertEquals plateau, robby.plateau
        assertEquals 4, robby.coordinates.x
        assertEquals 3, robby.coordinates.y
        assertEquals 'W', robby.heading
    }

    void testRobots() {
        def plateau = new Plateau('5 5')
        def marvin = plateau.createRobot('1 2 N')
        def gort = plateau.createRobot('3 3 E')
        def actual = plateau.robots
        def expected = [marvin, gort]
        assertArrayEquals(expected.toArray(), actual.toArray())
    }
}
