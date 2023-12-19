package philosophers

import org.apache.zookeeper._

import java.time.LocalDateTime
import java.util.concurrent.Semaphore
import scala.util.Random

case class Philosopher(id: Int,
                       hostPort: String,
                       root: String,
                       left: Semaphore,
                       right: Semaphore,
                       seats: Integer) extends Watcher {

  val zk = new ZooKeeper(hostPort, 3000, this)
  val mutex = new Object()
  val path: String = root + "/" + id.toString

  if (zk == null) throw new Exception("ZK is NULL.")

  override def process(event: WatchedEvent): Unit = {

    // Блок синхронизации
    mutex.synchronized {
      mutex.notify()
    }
  }

  def eat(): Boolean = {
    print(LocalDateTime.now() + " Philosopher " + id + " is ready to eat\n")

    mutex.synchronized {
      var created = false

      while (true) {
        if (!created) {

          // Создадим эфимерный узел
          zk.create(path, Array.emptyByteArray, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL)
          created = true
        }

        val active = zk.getChildren(root, this)
        if (active.size() > seats) {
          zk.delete(path, -1)

          mutex.wait(3000)

          Thread.sleep(Random.nextInt(5) * 100)
          created = false
        } else {
          left.acquire()
          print(LocalDateTime.now() + " Philosopher " + id + " got the left fork\n")

          right.acquire()
          print(LocalDateTime.now() + " Philosopher " + id + " got the right fork\n")

          Thread.sleep((Random.nextInt(5) + 1) * 1000)

          right.release()
          print(LocalDateTime.now() + " Philosopher " + id + " put the right fork\n")

          left.release()
          print(LocalDateTime.now() + " Philosopher " + id + " put the left fork\n")

          print(LocalDateTime.now() + " Philosopher " + id + " finished eating\n")
          return true
        }
      }
    }

    false
  }

  // особождение узла
  def think(): Unit = {
    printf(LocalDateTime.now() + " Philosopher " + id + " is thinking\n")

    zk.delete(path, -1)

    Thread.sleep((Random.nextInt(5) + 1) * 1000)
  }
}