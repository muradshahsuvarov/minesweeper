package com.example.minisweaperiqultev2

class Cell {

    var x : Int = 0 // x coordinate of the cell
    var y : Int = 0 // y coordinate of the cell
    var mineAllowed : Boolean = false // Mines is inside the cell
    var minesNear : Int = 0 // Mines near the current cell in up,down,right,left directions if mineAllowed = false
    var flagAllowed : Boolean = false // Is true if flag is set on this cell
    var opened : Boolean = false // // Is tru if the cell is opened

    constructor(x : Int, y : Int, mA : Boolean, mN : Int, fA : Boolean, o : Boolean){
        this.x = x
        this.y = y
        this.mineAllowed = mA
        this.minesNear = mN
        this.flagAllowed = fA
        this.opened = opened
    }

    // Returns this cell
    public fun  getCell() : Cell {
        return this
    }
}