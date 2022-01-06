package ph.edu.dlsu.mobdeve.group.machineproject.keeb.keebDAO

import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Keycaps
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Layout
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Switches

interface KeebDAO {
    fun getSwitches(): ArrayList<Switches?>
    fun getLayout(): ArrayList<Layout?>
    fun getKeycaps(): ArrayList<Keycaps?>

}

