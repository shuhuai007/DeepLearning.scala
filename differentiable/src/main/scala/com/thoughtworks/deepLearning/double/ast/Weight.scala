package com.thoughtworks.deepLearning.double.ast

import cats._
import cats.implicits._
import org.nd4s.Implicits._
import com.thoughtworks.deepLearning.{Batch, Differentiable, LearningRate}
import com.thoughtworks.deepLearning.Differentiable._
import com.thoughtworks.deepLearning.double.utilities.DoubleMonoidBatch
import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import org.nd4j.linalg.ops.transforms.Transforms

/**
  * @author 杨博 (Yang Bo) &lt;pop.atry@gmail.com&gt;
  */
final case class Weight(var rawValue: scala.Double)(implicit learningRate: LearningRate)
    extends Differentiable
    with DoubleMonoidBatch {
  override type Input = Batch
  override type Output = this.type

  override def forward(any: Input) = this

  override def backward(delta: Delta): Unit = {
    rawValue -= delta.value * learningRate()
  }

  override def value = Eval.now(rawValue)

  override def close(): Unit = {}

}
