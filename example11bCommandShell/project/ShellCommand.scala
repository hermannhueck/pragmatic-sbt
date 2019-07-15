import sbt._

import scala.language.postfixOps
import scala.sys.process._

object ShellCommand {

  // Command.single passes the unparsed rest of the line to the state changing function
  //
  def shellCommand = Command.single("sh") { (state, commandLine) =>
    Process(Seq("/bin/sh", "-c", commandLine)) !;
    // '\n' or ';' required for postfix op, otherwise compiler treats '!' as infix op and 'state' as it's arg
    state
  }


  // ---------------------------------------------------

  // alternative impl using the Scala Process API
  //
  def shellCommandAlt = Command.args("shAlt", "<args>") { (state, args) =>
    executeShell(args)
    state
  }

  def executeShell(args: Seq[String]): Int = {
    
    val commands =
      splitWhere("|", args)

    val processes: Seq[ProcessBuilder] = commands.map(Process(_))
    
    def exec(procs: Seq[ProcessBuilder]): Int =
      if (procs.isEmpty)
        throw new NoSuchElementException("No command line to execute")
      else if (procs.length == 1)
        procs.head!
      else if(procs.length == 2)
        procs.head #| procs.tail.head !
      else
        throw new IllegalArgumentException("Piping to multiple processes is not supported.")
    
    exec(processes)
  }

  def splitWhere(delim: String, seq: Seq[String]): Seq[Seq[String]] = {

    @scala.annotation.tailrec
    def go(delim: String, seq: Seq[String], acc: Seq[Seq[String]]): Seq[Seq[String]] = {
      val NotFound = -1
      val index = seq.indexOf(delim)
      if (seq.isEmpty)
        acc
      else if (index == NotFound)
        seq +: acc
      else {
        val (fst, snd) = seq.splitAt(index)
        go(delim, snd.tail, fst +: acc)
      }
    }
    
    go(delim, seq, Nil).reverse
  }
}
