/*
  https://www.scala-sbt.org/release/docs/Plugins.html

  Root plugins and triggered plugins
  ----------------------------------

  Some plugins should always be explicitly enabled on projects. we call these root plugins, i.e. plugins that are “root” nodes
  in the plugin dependency graph. An auto plugin is by default a root plugin.

  Auto plugins also provide a way for plugins to automatically attach themselves to projects if their dependencies are met.
  We call these triggered plugins, and they are created by overriding the trigger method.

  For example, we might want to create a triggered plugin that can append commands automatically to the build.
  To do this, set the requires method to return empty, and override the trigger method with allRequirements.
*/

package shell

import sbt._, Keys._
import complete.DefaultParsers._
import scala.sys.process.Process
import scala.language.postfixOps

object ShellPlugin extends AutoPlugin {

  object autoImport {
    lazy val sh = inputKey[Int]("Execute a shell command")
  }

  import autoImport._

  override def requires: Plugins = empty // other plugins this plugin depends on
  override def trigger: PluginTrigger = allRequirements // enable automatically

  /* add settings on the global (= default) level */
  // override lazy val globalSettings: Seq[Def.Setting[Seq[Command]]] = Seq(commands += shellCommand)

  /* add settings on the build level */
  override lazy val buildSettings: Seq[Def.Setting[InputTask[Int]]] = Seq(sh := shellTask.evaluated)

  /* add settings on the project level */
  // override lazy val projectSettings: Seq[Def.Setting[Seq[Command]]] = Seq(commands += shellCommand)


  lazy val shellTask = Def.inputTask {
    
    val args: Seq[String] = spaceDelimited("<arg>").parsed
    val commandLine = args.mkString(" ")
    Process(Seq("/bin/sh", "-c", commandLine)) !
  }
}
