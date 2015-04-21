package com.changent.marsrobots

/** Main is a crude client demonstrating usage
 *  of the marsrobots api
 */

class Main {
    public static void main(String[] args) {
        def plateau = new Plateau('5 5')
        def tikTok = plateau.createRobot('1 2 N')
        tikTok.moves 'LMLMLMLMM'

        def robby = plateau.createRobot('3 3 E')
        robby.moves 'MMRMMRMRRM'

        plateau.robots.each {
            println "${it.position}"
// you can see the id and position of the robots by uncommenting the line below
//            println "${it.id} position at ${it.position}"
        }
    }
}
