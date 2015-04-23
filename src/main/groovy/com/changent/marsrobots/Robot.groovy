package com.changent.marsrobots
/**
 * A <code>Robot</code> provides access to exploring a plateau.
 */
class Robot {
    /** an identifier for the robot */
    def id
    /** the plateau in which the robot is contained */
    def plateau
    /** the current coordinates of the robot */
    def coordinates
    /** the current heading of the robot */
    def heading

    /**
     * Class constructor specifying the id, the containing <code>Plateau</code>
     * and the initial position of the robot.
     *
     * @param id an Integer identifying the robot
     * @param plateau a Plateau containing the robot
     * @param position a String of the initial position of the robot in the format of "x y h"
     *                 where x is the x coordinate on the grid
     *                 where y is the y coordinate on the grid
     *                 where h is the heading of the robot defined by one of the four cardinal
     *                      compass points (N, S, E, W)
     * @throws IllegalArgumentException if x or y < 0, or the heading is not N, S, E, W
     */
    protected Robot(id, plateau, position) {
        this.id = id
        this.plateau = plateau
        setPosition(position)
    }

    /**
     * returns a String of the current position in the format of "x y h"
     *         where x is the x coordinate on the grid
     *         where y is the y coordinate on the grid
     *         where h is the heading of the robot defined by one of the four cardinal
     *               compass points (N, S, E, W)
     */
    def getPosition() {
        "${coordinates.x} ${coordinates.y} ${heading}"
    }

    /**
     * Sets the position of the robot
     *
     * @param position a String of the initial position of the robot in the format of "x y h"
     *                 where x is the x coordinate on the grid
     *                 where y is the y coordinate on the grid
     *                 where h is the heading of the robot defined by one of the four cardinal
     *                      compass points (N, S, E, W)
     * @throws IllegalArgumentException if x or y < 0, or the heading is not N, S, E, W
     */
    void setPosition(position) {
        def elem = position?.split()

        if (!position || elem.size() != 3) {
            throw new IllegalArgumentException("Position format is X Y H, where X is the X coordinated on the grid, Y is the Y coordinate, and H is the heading. Position provided is ${position}")
        }

        coordinates = new Coordinates("${elem[0]} ${elem[1]}")
        setHeading(elem[2])
    }

    private void setHeading(h) {
        if (!Plateau.compassPoint.find {it == h} ) {
            throw new IllegalArgumentException("Heading ${h} invalid. Headings can only be one of N, S, E, W")
        }
        heading = h
    }

    /**
     * Changes the heading of the robot or instructs it to move to the next grid
     *
     * @param instructionSet an arbitrary length String comprised of consecutive commands made up of the characters L, R and M
     *                 where L turns the robot to the left by one compass point
     *                 where R turns the robot to the right by one compass point
     *                 where M moves the robot to the next grid to which it is facing
     *
     *                 an example instruction set: "LMMRRMRM"
     *
     * @throws IllegalArgumentException if there are any characters other than L, R, and M in the instructionSet. Also, thrown
     *                                  if commands would drive the robot off the plateau.
     */
    void moves(instructionSet) {
        def elem = instructionSet?.toCharArray()
        elem?.each {
            move(it)
        }

    }

    private void move(instruction) {
        switch (instruction) {
            case 'L':
            case 'R':
                changeHeading instruction
                break
            case 'M':
                step()
                break
            default:
                throw new IllegalArgumentException("Move instruction ${instruction} invalid. Instructions can only be a string of values containing the characters L R and M")
        }
    }

    private void changeHeading(instruction) {
        def i = Plateau.compassPoint.indexOf(heading)
        if (instruction == 'L') {
            i++
            if (i  == Plateau.compassPoint.size()) {
                i = 0
            }
            heading = Plateau.compassPoint[i]
        }
        if (instruction == 'R') {
            i--
            if (i  < 0) {
                i = Plateau.compassPoint.size() - 1
            }
            heading = Plateau.compassPoint[i]
        }
    }

    private void step() {
        def x = coordinates.x
        def y = coordinates.y

        switch (heading) {
            case 'N' :
                y++
                break
            case 'S' :
                y--
                break
            case 'E' :
                x++
                break
            case 'W' :
                x--
                break
        }
        if (!plateau.validateCoordinates(x, y)) {
            throw new IllegalArgumentException("Move step instruction outside defined plateau boundaries ${plateau.boundaries}. Current position is ${position}")
        }
        position = "${x} ${y} ${heading}"
    }
}
