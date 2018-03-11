package net.nikonorov.blockchaincurrency.utils

import timber.log.Timber

/**
 * Created by Vitaly Nikonorov on 11.03.2018.
 * email@nikonorov.net
 */
class LogDebugTree: Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {
        val fullClassName = element.className
        val lastDotIndex = fullClassName.lastIndexOf('.')
        val shortName = fullClassName.substring(lastDotIndex + 1)
        return "$shortName.${element.methodName}.${element.lineNumber}"
    }
}
