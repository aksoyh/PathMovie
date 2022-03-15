package com.aksoyhasan.path.utils

import androidx.navigation.NavController
import androidx.navigation.NavGraph

fun NavController.changeNodeDestination(nodeId: Int, destinationId: Int): NavController {
    val graph = graph.findNode(nodeId) as NavGraph
    graph.setStartDestination(destinationId)
    return this
}

fun NavController.changeNodeDestination(vararg nodeIds: Int, destinationId: Int): NavController {
    var currentNode = graph

    nodeIds.forEachIndexed { index, i ->
        currentNode = currentNode.findNode(nodeIds[index]) as NavGraph
    }
    currentNode.setStartDestination(destinationId)
    return this
}