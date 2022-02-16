package ph.edu.dlsu.mobdeve.group.machineproject.keeb.keebDAO

import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Keycaps
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Layout
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Switches


/* In charge of storing all of the Keyboard ArrayLists */

interface KeebDAO {
    fun getSwitches(): ArrayList<Switches?> // gets the switich arraylist of the switches
    fun getLayout(): ArrayList<Layout?> //.. same behavior
    fun getKeycaps(): ArrayList<Keycaps?> //.. same behavior
}

