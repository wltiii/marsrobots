package com.changent.akqarobots
/**
 * A <code>Plateau</code> defines the grid to be explored by one or more <code>Robot</code>.
 */

class Plateau {
    /** the upper boundary coordinates of the plateau */
    def upperBoundary
    /** a list of the robots contained within the plateau */
    def robots = []
    /** the invariant lower boundary coordinates of the plateau where x = 0 and y = 0 */
    final static lowerBoundary = new Coordinates(0,0)
    /** the invariant compass points that a robot can face */
    final static compassPoint = ['N', 'W', 'S', 'E']

    /**
     * Class constructor that defines the boundaries of the plateau.
     *
     * @param upperBoundary a String designating the x and y coordinates in the format of "x y"
     *                 where x is the x coordinate on the grid
     *                 where y is the y coordinate on the grid
     * @throws IllegalArgumentException if x or y < 0
     */
    Plateau (upperBoundary) {
        this.upperBoundary = new Coordinates(upperBoundary)
    }

    /**
     * @return a list of Integers designating the bounds the plateau in the format of
     *         [lowerBoundary.x, lowerBoundary.y, upperBoundary.x, upperBoundary.y]
     */
    def getBoundaries() {
        [lowerBoundary.x, lowerBoundary.y, upperBoundary.x, upperBoundary.y]
    }

    /**
     * Validates that the coordinates are valid points within this plateau
     *
     * @param x an Integer designating the x coordinate
     * @param y an Integer designating the y coordinate
     * @return boolean
     */
    def validateCoordinates(x, y) {
        def testCoordinate = new Coordinates(x, y)
        lowerBoundary.x <= testCoordinate.x && upperBoundary.x >= x && lowerBoundary.y <= testCoordinate.y && upperBoundary.y >= y
    }

    /**
     * Creates a <code>Robot</code> for exploring this plateau.
     *
     * @param position a String of the initial position of the robot in the format of "x y h"
     *                 where x is the x coordinate on the grid
     *                 where y is the y coordinate on the grid
     *                 where h is the heading of the robot defined by one of the four cardinal
     *                      compass points (N, S, E, W)
     * @return Robot
     * @throws IllegalArgumentException if x or y < 0, or the heading is not N, S, E, W
     */
    def createRobot(position) {
        def robot = new Robot(robots.size() + 1, this, position)
        robots.add(robot)
        robot
    }
}
