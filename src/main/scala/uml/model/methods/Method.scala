package uml.model.methods

import uml.model.Modifiable
import uml.model.Modifiers.Modifier
import uml.model.classes.Class
import uml.model.types.Type

case class Method(name: String, returnType: Type, arguments: List[Argument], modifiers: List[Modifier],
                  annotations: List[String]) extends Modifiable {

  def isBoilerplate(clazz: Class): Boolean = clazz.attributes
    .exists(attr => name == attr.getterMethod || name == attr.setterMethod)

  def write: String = s"$name(${arguments.map(a => s"${a.write}").mkString(", ")}): ${returnType.name}"
}