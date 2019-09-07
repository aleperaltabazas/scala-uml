package uml.model

import uml.model.ClassTypes.ClassType
import uml.model.Modifiers.Modifier

case class Class(name: String, attributes: List[Attribute], methods: List[Method], modifiers: List[Modifier],
                 annotations: List[String], parent: Option[Class], interfaces: List[Class], classType: ClassType)
  extends Modifiable {

  def hasGetterFor(attribute: Attribute): Boolean = methods.exists(_.name == s"get${attribute.name.capitalize}")

  def write: String = {
    val attributesText = attributes.filter(a => a.isVisible || hasGetterFor(a))
      .map(a => a.write)
      .mkString("\n")

    val methodsText = methods.filter(_.isVisible)
      .map(m => m.write)
      .mkString("\n")

    val definition = s"${classType.write} $name"

    val inheritance = parent match {
      case Some(p) => s" extends ${p.name}"
      case None => ""
    }

    val implementation = interfaces match {
      case Nil => ""
      case x :: Nil => s" implements $x"
      case _ :: _ :: _ => s" implements ${interfaces.map(i => i.name).mkString(", ")}"
    }

    s"$definition$inheritance$implementation {\n$attributesText\n$methodsText\n}".trim
  }

  def writeRelations: String = {
    attributes.map {
      attr =>
        if (attr.isCollection) s"$name --> \"*\" ${attr.attributeType.name}"
        else s"$name --> ${attr.attributeType.name}"
    }.mkString("\n")
  }

}