package ph.edu.dlsu.mobdeve.group.machineproject.keeb.keebDAO

import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Keycaps
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Layout
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Switches


class KeebDAOArrayList :  KeebDAO{
    var switchList = ArrayList<Switches?>()
    var layoutList = ArrayList<Layout?>()
    var keyList = ArrayList<Keycaps?>()

    init {
        switchList.add(Switches("Gateron Yellows"))
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