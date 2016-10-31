package com.thoughtworks.deepLearning.boolean
package utilities

import cats._
import cats.implicits._
import org.nd4s.Implicits._
import com.thoughtworks.deepLearning.{Batch, Differentiable}
import com.thoughtworks.deepLearning.Differentiable._
import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import org.nd4j.linalg.ops.transforms.Transforms

/**
  * @author 杨博 (Yang Bo) &lt;pop.atry@gmail.com&gt;
  */
private[deepLearning] trait BooleanMonoidBatch extends Batch {

  override type Data = Boolean#Data

  override type Delta = Boolean#Delta

  final def monoid = new Monoid[Delta] {
    override def empty = Eval.now(false)

    override def combine(x: Delta, y: Delta) = x.map2(y)(_ ^ _)
  }
}
