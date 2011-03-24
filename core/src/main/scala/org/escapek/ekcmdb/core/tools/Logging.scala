package org.escapek.ekcmdb.core.tools

import org.slf4j.{Logger,LoggerFactory}

trait Logging {
	protected lazy val logger : Logger = LoggerFactory.getLogger(this.getClass)
}