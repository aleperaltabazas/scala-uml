package uml

import uml.model.Modifiers.{Abstract, Private, Public}
import uml.model.types.SimpleType
import uml.model.{ActualClass, Argument, Attribute, Method}

package object io {
  val characterClass: ActualClass = ActualClass("Character", List(
    Attribute("id", SimpleType("Long"), List(Private), List("@Id", "@GeneratedValue")),
    Attribute("maxHp", SimpleType("Double"), List(Private), Nil),
    Attribute("currentHp", SimpleType("Double"), List(Private), Nil),
    Attribute("level", SimpleType("Level"), List(Private), Nil),
    Attribute("currentLocation", SimpleType("Location"), List(Private), List("@ManyToMany")),
    Attribute("characterState", SimpleType("CharacterState"), List(Private), List("@OneToOne")),
    Attribute("inventory", SimpleType("Inventory"), List(Private), List("@OneToOne")),
    Attribute("equippedWeapon", SimpleType("Weapon"), List(Private), List("@OneToOne"))
  ), List(
    Method("gainHp", SimpleType("Void"), List(Argument("gain", SimpleType("Double"))), List(Public), Nil),
    Method("loseHp", SimpleType("Void"), List(Argument("damage", SimpleType("Double"))), List(Public), Nil),
    Method("getCurrentHp", SimpleType("Int"), Nil, List(Public), Nil),
    Method("getMaxHp", SimpleType("Double"), Nil, List(Public), Nil),
    Method("gainExp", SimpleType("Void"), List(Argument("exp", SimpleType("Double"))), List(Public), Nil),
    Method("setMaxHp", SimpleType("Void"), List(Argument("maxHp", SimpleType("Double"))), List(Public), Nil),
    Method("setCurrentHp", SimpleType("Void"), List(Argument("currentHp", SimpleType("Double"))), List(Public), Nil),
  ), List(
    Public
  ), List("@Entity"),
    None,
    Nil,
    false)

  val characterStateClass = ActualClass("CharacterState", List(
    Attribute("id", SimpleType("Long"), List(Private), List("@Id", "@GeneratedValue"))
  ), List(
    Method("call", SimpleType("CharacterState"), List(Argument("character", SimpleType("Character"))), List(Public,
      Abstract), Nil)
  ), List(
    Public, Abstract
  ), List("@Entity", "@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)"),
    None,
    Nil,
    true)

  val aliveClass = ActualClass("Alive", Nil, List(
    Method("call", SimpleType("CharacterState"), List(Argument("character", SimpleType("Character"))), List(Public), Nil)
  ), List(Public),
    List("@Entity"),
    Some(characterStateClass),
    Nil,
    false)

  val knockedOutClass = ActualClass("KnockedOut", Nil, List(
    Method("call", SimpleType("CharacterState"), List(Argument("character", SimpleType("Character"))), List(Public), Nil)
  ), List(Public),
    List("@Entity"),
    Some(characterStateClass),
    Nil,
    false)
}