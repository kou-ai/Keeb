package ph.edu.dlsu.mobdeve.group.machineproject.keeb.keebDAO

import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Keycaps
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Layout
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Switches

/* Responsible for storing and initializing the arrayLists for the keyboards */


class KeebDAOArrayList :  KeebDAO{
    var switchList = ArrayList<Switches?>()
    var layoutList = ArrayList<Layout?>()
    var keyList = ArrayList<Keycaps?>()


    // Collection of sample data to be used for the generator
    init {
        switchList.add(Switches("Gateron Yellows")) // Add data to switchList
        switchList.add(Switches("NK Creams"))
        layoutList.add(Layout("70%"))
        layoutList.add(Layout("60%"))
        keyList.add(Keycaps("GMK Blue"))
        keyList.add(Keycaps("GMK Mizu"))
    }

    override fun getSwitches(): ArrayList<Switches?> = switchList
    override fun getLayout(): ArrayList<Layout?> = layoutList
    override fun getKeycaps(): ArrayList<Keycaps?> = keyList



}