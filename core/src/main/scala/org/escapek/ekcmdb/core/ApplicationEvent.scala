package org.escapek.ekcmdb.core

object ApplicationEvent {
	val Namespace = "org/escapek/ekcmdb/core/ApplicationEvent/"
	val Topic_startup = Namespace + "STARTUP"
	val Topic_shutdownInProgress = Namespace + "SHUTDOWN_IN_PROGRESS"
	val Topic_stopped = Namespace + "STOPPED"  
}