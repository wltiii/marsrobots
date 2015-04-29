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
        this.x = isPositiveInteger x
        this.y = isPositiveInteger y
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
            throw new IllegalArgumentException("Coordinate format is 'X Y', where X is the X coordinate on the grid and Y is the Y coordinate. Coordinates provided is ${coordinates}")
        }

        this.x = isPositiveInteger elem[0] as Integer
        this.y = isPositiveInteger elem[1] as Integer
    }

    //TODO make this more fluent
    private Integer isPositiveInteger(coordinate) {
      if (coordinate.class.simpleName != "Integer") {
          throw new IllegalArgumentException("Coordinates must be integers. Coordinate ${coordinate} is of type ${coordinate.class.simpleName}")
      }
      if (coordinate < 0) {
          throw new IllegalArgumentException("Coordinate must be equal or greater than 0, but was ${coordinate}")
      }

      coordinate
    }

}
