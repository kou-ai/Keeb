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
        layoutList.add(Layout("70%"))
        keyList.add(Keycaps("GMK Blue"))
    }

    override fun getSwitches(): ArrayList<Switches?> = switchList
    override fun getLayout(): ArrayList<Layout?> = layoutList
    override fun getKeycaps(): ArrayList<Keycaps?> = keyList



}