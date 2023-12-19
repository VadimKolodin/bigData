package philosophers

import java.util.concurrent.Semaphore

object MainPhilosophers {
  def main(args: Array[String]): Unit = {

    val Seq(hostPort, n) = args.toSeq

    val philosophersCount = n.toInt
    val seats = philosophersCount - 1

    val forks = new Array[Semaphore](philosophersCount)
    for (j <- 0 until philosophersCount) {
      forks(j) = new Semaphore(1)
    }

    val threads = new Array[Thread](philosophersCount)
    for (i <- 0 until philosophersCount) {
      threads(i) = new Thread(
        () => {
          val philosopher = Philosopher(i,
            hostPort,
            "/philosophers",
            forks(i),
            forks((i + 1) % philosophersCount),
            seats)

          for (j <- 1 to 2) {
            philosopher.eat()
            philosopher.think()
          }

        }
      )

      threads(i).setDaemon(false)
      threads(i).start()
    }
    for (id <- 0 until philosophersCount) {
      threads(id).join()
    }
  }
}
