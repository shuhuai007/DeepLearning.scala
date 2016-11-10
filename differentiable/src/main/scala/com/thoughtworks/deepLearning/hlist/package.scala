package com.thoughtworks.deepLearning
//
//import com.thoughtworks.deepLearning.DifferentiableFunction.{Ast, ToAst}
//import hlist.ast._
//import any._
//import com.thoughtworks.deepLearning.Differentiable.ConcreteBatch
//import com.thoughtworks.deepLearning.any.ast.Identity
//
import com.thoughtworks.deepLearning.DifferentiableType.ConcreteType

import scala.language.implicitConversions
import scala.language.existentials

/**
  * @author 杨博 (Yang Bo) &lt;pop.atry@gmail.com&gt;
  */
package object hlist {
  /** @template */
  type HList = DifferentiableType {
    type Data <: shapeless.HList
    type Delta <: shapeless.Coproduct
  }
  /** @template */
  type HNil = ConcreteType[shapeless.HNil, shapeless.CNil]
  /** @template */
  type ::[Head <: DifferentiableType, Tail <: HList] =
    ConcreteType[shapeless.::[head.Data, tail.Data], shapeless.:+:[head.Delta, tail.Delta]] forSome {
      val head: Head
      val tail: Tail
    }
//
//  implicit final class HListOps[TailAst](val tail: TailAst) {
//
//    def ::[Input0 <: Differentiable,
//           HeadAst,
//           HeadData,
//           HeadDelta,
//           TailData <: shapeless.HList,
//           TailDelta <: shapeless.Coproduct](head: HeadAst)(
//        implicit unapplyHead: ToAst[HeadAst, Input0, HeadData, HeadDelta],
//        unapplyTail: ToAst[TailAst, Input0, TailData, TailDelta]
//    ): Ast[Input0, ConcreteBatch[shapeless.::[HeadData, TailData], shapeless.:+:[HeadDelta, TailDelta]]] = {
//      HCons[Input0, HeadData, HeadDelta, TailData, TailDelta](unapplyHead(head), unapplyTail(tail))
//    }
//
//  }
//
//  implicit final class HConsOps[Input <: Differentiable, HeadData, HeadDelta, TailData <: shapeless.HList,
//  TailDelta <: shapeless.Coproduct](
//      hcons: Ast[Input, ConcreteBatch[shapeless.::[HeadData, TailData], shapeless.:+:[HeadDelta, TailDelta]]]) {
//    def head: Ast[Input, ConcreteBatch[HeadData, HeadDelta]] = Head[Input, HeadData, HeadDelta, TailData, TailDelta](hcons)
//
//    def tail: Ast[Input, ConcreteBatch[TailData, TailDelta]] = Tail[Input, HeadData, HeadDelta, TailData, TailDelta](hcons)
//  }
//
//  /** @template */
//  type HList = Any {
//    type Data <: shapeless.HList
//    type Delta <: shapeless.Coproduct
//  }
//
//  /** @template */
//  type HNil = HList {
//    type Data = shapeless.HNil
//    type Delta = shapeless.CNil
//  }
//
//  def hnil[Input <: Differentiable: Identity]: Ast[Input, ConcreteBatch[shapeless.HNil, shapeless.CNil]] = HNil
//
//  /** @template */
//  type ::[Head <: Differentiable, Tail <: HList] = HList {
//    type Data = shapeless.::[Head#Data, Tail#Data]
//    type Delta = shapeless.:+:[Head#Delta, Tail#Delta]
//  }
}
