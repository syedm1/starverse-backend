object Episode extends Enumeration {
  val NEWHOPE, EMPIRE, JEDI = Value
}

trait Character {
  def id: String
  def name: Option[String]
  def nickName: Option[String]
  def image: Option[String]
  def friends: List[String]
  def appearsIn: List[Episode.Value]
}

case class Human(
  id: String,
  name: Option[String],
  nickName: Option[String],
  image: Option[String],
  friends: List[String],
  appearsIn: List[Episode.Value],
  homePlanet: Option[String]) extends Character

case class Droid(
  id: String,
  name: Option[String],
  nickName: Option[String],
  image: Option[String],
  friends: List[String],
  appearsIn: List[Episode.Value],
  primaryFunction: Option[String]) extends Character

class CharacterRepo {
  import CharacterRepo._

  def getHero(episode: Option[Episode.Value]): Character =
    episode flatMap (_ => getHuman("1000")) getOrElse droids.last

  def getHuman(id: String): Option[Human] = humans.find(c => c.id == id)

  def getDroid(id: String): Option[Droid] = droids.find(c => c.id == id)
  
  def getHumans(limit: Int, offset: Int): List[Human] = humans.slice(offset, offset + limit)
  
  def getDroids(limit: Int, offset: Int): List[Droid] = droids.slice(offset, offset + limit)
}

object CharacterRepo {
  val humans = List(
    Human(
      id = "1000",
      name = Some("Luke Skywalker"),
      nickName = Some("The chosen one"),
      image = Some("https://am24.mediaite.com/tms/cnt/uploads/2021/02/luke-skywalker-disney-plus-mandalorian-the-rescue.jpeg"),
      friends = List("1002", "1003", "2000", "2001"),
      appearsIn = List(Episode.NEWHOPE, Episode.EMPIRE, Episode.JEDI),
      homePlanet = Some("Tatooine")),
    Human(
      id = "1001",
      name = Some("Darth Vader"),
      nickName = Some("ANI"),
      image = Some("https://i.pinimg.com/originals/7d/5b/56/7d5b5654a51e6f202ca40fc215c4b72d.jpg"),
      friends = List("1004"),
      appearsIn = List(Episode.NEWHOPE, Episode.EMPIRE, Episode.JEDI),
      homePlanet = Some("Tatooine")),
    Human(
      id = "1002",
      name = Some("Han Solo"),
      nickName = Some("captain solo"),
      image = Some("https://upload.wikimedia.org/wikipedia/en/b/be/Han_Solo_depicted_in_promotional_image_for_Star_Wars_%281977%29.jpg"),
      friends = List("1000", "1003", "2001"),
      appearsIn = List(Episode.NEWHOPE, Episode.EMPIRE, Episode.JEDI),
      homePlanet = None),
    Human(
      id = "1003",
      name = Some("Leia Organa"),
      nickName = Some("The Huttslayer"),
      image = Some("https://i.pinimg.com/originals/b5/07/f6/b507f65aa17b52fc312ba3cc7f5ee151.jpg"),
      friends = List("1000", "1002", "2000", "2001"),
      appearsIn = List(Episode.NEWHOPE, Episode.EMPIRE, Episode.JEDI),
      homePlanet = Some("Alderaan")),
    Human(
      id = "1004",
      name = Some("Wilhuff Tarkin"),
      nickName = Some("The First Grand Moff "),
      image = Some("https://static2.srcdn.com/wordpress/wp-content/uploads/2020/03/Grand-Moff-Tarkin-in-A-New-Hope.png?q=50&fit=crop&w=740&h=370&dpr=1.5"),
      friends = List("1001"),
      appearsIn = List(Episode.NEWHOPE, Episode.EMPIRE, Episode.JEDI),
      homePlanet = None)
  )

  val droids = List(
    Droid(
      id = "2000",
      name = Some("C-3PO"),
      nickName = Some("chosen one"),
      image = Some("https://i.pinimg.com/originals/7d/5b/56/7d5b5654a51e6f202ca40fc215c4b72d.jpg"),
      friends = List("1000", "1002", "1003", "2001"),
      appearsIn = List(Episode.NEWHOPE, Episode.EMPIRE, Episode.JEDI),
      primaryFunction = Some("Protocol")),
    Droid(
      id = "2001",
      name = Some("R2-D2"),
      nickName = Some("chosen one"),
      image = Some("https://i.pinimg.com/originals/7d/5b/56/7d5b5654a51e6f202ca40fc215c4b72d.jpg"),
      friends = List("1000", "1002", "1003"),
      appearsIn = List(Episode.NEWHOPE, Episode.EMPIRE, Episode.JEDI),
      primaryFunction = Some("Astromech"))
  )
}
