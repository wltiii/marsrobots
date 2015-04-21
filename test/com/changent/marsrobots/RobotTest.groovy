package com.changent.akqarobots

class RobotTest extends GroovyTestCase {

    void testPosition() {
        def plateau = new Plateau('25 25')
        def robby = plateau.createRobot('14 20 N')
        assertEquals '14 20 N', robby.position

        robby.position = '10 13 W'
        assertEquals '10 13 W', robby.position

        shouldFail(IllegalArgumentException) {
            robby.position = '4 4'
        }

        shouldFail(IllegalArgumentException) {
            robby.position = '0 0 0 0'
        }

    }

    void testPositionArgumentCount() {
        def plateau = new Plateau('25 25')
        def robby = plateau.createRobot('14 20 N')

        // too few arguments
        shouldFail(IllegalArgumentException) {
            robby.position = '4 4'
        }

        // too many arguments
        shouldFail(IllegalArgumentException) {
            robby.position = '0 0 0 0'
        }

    }

    void testHeading() {
        def plateau = new Plateau('5 5')
        def tikTok = plateau.createRobot('1 2 N')

        assertEquals 'N', tikTok.heading

        tikTok.heading = 'S'
        assertEquals 'S', tikTok.heading

        tikTok.heading = 'E'
        assertEquals 'E', tikTok.heading

        tikTok.heading = 'W'
        assertEquals 'W', tikTok.heading

        shouldFail(IllegalArgumentException) {
            tikTok.heading = 'NW'
        }

    }

    void testLeftRotations() {
        def plateau = new Plateau('5 5')
        // set initial position
        def tikTok = plateau.createRobot('1 2 N')
        // validate left rotations
        tikTok.move 'L'
        assertEquals '1 2 W', tikTok.position
        tikTok.move 'L'
        assertEquals '1 2 S', tikTok.position
        tikTok.move 'L'
        assertEquals '1 2 E', tikTok.position
        tikTok.move 'L'
        assertEquals '1 2 N', tikTok.position
    }
    void testRightRotations() {
        def plateau = new Plateau('5 5')
        // set initial position
        def tikTok = plateau.createRobot('1 2 N')
        // validate right rotations
        tikTok.move 'R'
        assertEquals '1 2 E', tikTok.position
        tikTok.move 'R'
        assertEquals '1 2 S', tikTok.position
        tikTok.move 'R'
        assertEquals '1 2 W', tikTok.position
        tikTok.move 'R'
        assertEquals '1 2 N', tikTok.position
    }
    void testMoveInEachDirection() {
        def plateau = new Plateau('5 5')
        // set initial position
        def tikTok = plateau.createRobot('1 2 N')

        // move north
        tikTok.move 'M'
        assertEquals '1 3 N', tikTok.position

        // rotate and move west
        tikTok.move 'L'
        assertEquals '1 3 W', tikTok.position
        tikTok.move 'M'
        assertEquals '0 3 W', tikTok.position
        // rotate and move south
        tikTok.move 'L'
        assertEquals '0 3 S', tikTok.position
        tikTok.move 'M'
        assertEquals '0 2 S', tikTok.position
        // rotate and move east
        tikTok.move 'L'
        assertEquals '0 2 E', tikTok.position
        tikTok.move 'M'
        assertEquals '1 2 E', tikTok.position
    }

    void testMoves() {
        def plateau = new Plateau('5 5')
        def tikTok = plateau.createRobot('1 2 N')
        tikTok.moves 'LMLMLMLMM'
        assertEquals '1 3 N', tikTok.position

        def robby = plateau.createRobot('3 3 E')
        robby.moves 'MMRMMRMRRM'
        assertEquals '5 1 E', robby.position

        def marvin = plateau.createRobot('1 1 S')
        shouldFail(IllegalArgumentException) {
            marvin.moves 'MMRMMRMRRM'
        }
        marvin.position = '1 1 W'
        shouldFail(IllegalArgumentException) {
            marvin.moves 'MMRMMRMRRM'
        }
        marvin.position = '4 4 N'
        shouldFail(IllegalArgumentException) {
            marvin.moves 'MMRMMRMRRM'
        }
        marvin.position = '4 4 E'
        shouldFail(IllegalArgumentException) {
            marvin.moves 'MMRMMRMRRM'
        }

    }

}
