package com.changent.marsrobots

/**
 *
 */
final class Coordinates {
    /** horizontal, or east/west, axis. a value of zero is the furthest point west  */
    final Integer x
    /** vertical, or north/south axis. a value of zero is the furthest point south  */
    final Integer y

    /**
     * Class constructor specifying the x and y coordinates.
     *
     * @param x an Integer designating x coordinate
     * @param y an Integer designating y coordinate
     * @throws IllegalArgumentException if x or y < 0
     */
    Coordinates(x, y) {
        if (x.class.simpleName != "Integer" || y.class.simpleName != "Integer") {
            throw new IllegalArgumentException("Coordinates must be integers.")
        }
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates must be equal or greater than 0.")
        }

        setX(x)
        setY(y)
    }

    /**
     * Class constructor specifying the x and y coordinates as a String.
     *
     * @param coordinates a String designating the x and y coordinates in the format of "x y"
     * @throws IllegalArgumentException if x or y < 0
     */
    Coordinates(coordinates) {
        def elem = coordinates?.trim()?.split()
        if (!coordinates || elem?.size() != 2) {
            throw new IllegalArgumentException("Coordinate format is X Y, where X is the X coordinate on the grid and Y is the Y coordinate. Coordinates provided is ${coordinates}")
        }

        def x = new Integer(elem[0])
        def y = new Integer(elem[1])

        setX(x)
        setY(y)
    }

    /**
     * Sets the x coordinate
     *
     * @param x an Integer designating x coordinate
     * @throws IllegalArgumentException if x < 0
     */
    // NOTE: overriding a setter requires that you specify a return type

    private void setX(x) {
        if (x.class.simpleName != "Integer") {
            throw new IllegalArgumentException("Coordinate must be an integer.")
        }
        if (x < 0) {
            throw new IllegalArgumentException("x coordinate must be equal or greater than 0.")
        }

        this.x = x
    }

    /**
     * Sets the y coordinate
     *
     * @param y an Integer designating y coordinate
     * @throws IllegalArgumentException if y < 0
     */
    // NOTE: overriding a setter requires that you specify a return

    private void setY(y) {
        if (y.class.simpleName != "Integer") {
            throw new IllegalArgumentException("Coordinate must be an integer.")
        }
        if (y < 0) {
            throw new IllegalArgumentException("y coordinate must be equal or greater than 0.")
        }
        this.y = y
    }

}
