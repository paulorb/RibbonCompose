package org.example.project

import androidx.compose.runtime.Composable


class RibbonTabComponents(private val ribbonGroups: List<RibbonGroup>,
    private val scalePolicies: List<Scale>) {
    fun getGroups():  List<RibbonGroup> = ribbonGroups
    private var scaledStateIndex = 0


    init {
        ribbonGroups.forEach { group ->
            val policy = scalePolicies.find{ it.groupName == group.getName()}
            if(policy != null){
                group.setScaleSize(policy.scaleSize)
            }
        }
    }
    private val stackScaleOrder : ArrayDeque<RibbonGroup> = generateScalePoliciesGroups()

    private fun generateScalePoliciesGroups() : ArrayDeque<RibbonGroup> {
        val scalePoliciesGroups = mutableListOf<RibbonGroup>()
        scalePolicies.forEach { scale ->
            ribbonGroups.find { it.getName() == scale.groupName }.apply {
                this?.let {
                    val temp = it.deepCopy()
                    temp.setScaleSize(scale.scaleSize)
                    scalePoliciesGroups.add(temp)
                }
            }
        }
        return ArrayDeque<RibbonGroup>(scalePoliciesGroups.toList())
    }

    @Composable
    fun compose(scaleSize: RibbonGroup.RibbonComponentSize? = null) {
        ribbonGroups.forEach { group ->
            if(scaleSize != null)
                group.compose(scaleSize!!)
            else
                group.compose()
        }
    }

    fun hasMoreScaleRules(): Boolean {
        return scaledStateIndex < stackScaleOrder.size
    }

    fun resetScaledIndex() {
        scaledStateIndex = 0
    }

    @Composable
    fun composeScaled() {
        if(stackScaleOrder.size == 0){
            compose()
        }else {
            ribbonGroups.forEach { group ->
                var composed = false
                stackScaleOrder.forEachIndexed { index, ribbonGroup ->
                    if (index <= scaledStateIndex &&  group.getName() == ribbonGroup.getName() && !composed) {
                        //search ahead if there is any scale for the same group
                        var indexOtherScaleSameGroup = stackScaleOrder.indexOfLast { it.getName() == ribbonGroup.getName() }
                        if(indexOtherScaleSameGroup != 0 && indexOtherScaleSameGroup <= scaledStateIndex){
                            group.compose(stackScaleOrder[indexOtherScaleSameGroup].getScaleSize())
                        }else {
                            group.compose(ribbonGroup.getScaleSize())
                        }
                        composed = true
                    }
                }
                if(!composed)
                    group.compose()
            }
            scaledStateIndex++
        }
    }



    private fun getNextResizeElement(): RibbonGroup {
        return stackScaleOrder[scaledStateIndex++]
    }

    data class Scale(val groupName: String, val scaleSize: RibbonGroup.RibbonComponentSize)
}