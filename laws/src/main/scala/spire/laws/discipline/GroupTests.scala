package spire.laws.discipline

import spire.algebra.{Group, Eq}
import spire.laws.GroupLaws

import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll

trait GroupTests[A] extends MonoidTests[A] {

  def laws: GroupLaws[A]

  def group(implicit arbA: Arbitrary[A], eqA: Eq[A]): RuleSet =
    new DefaultRuleSet(
      "group",
      Some(monoid),
      "left inverse" -> forAll(laws.leftInverse _),
      "right inverse" -> forAll(laws.rightInverse _),
      "consistent remove" -> forAll(laws.consistentRemove _)
    )
}

object GroupTests {
  def apply[A: Group]: GroupTests[A] =
    new GroupTests[A] { def laws: GroupLaws[A] = GroupLaws[A] }
}
