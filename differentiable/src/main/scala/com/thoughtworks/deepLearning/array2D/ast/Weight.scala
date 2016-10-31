package com.thoughtworks.deepLearning
package array2D.ast

import cats._
import com.thoughtworks.deepLearning.Differentiable
import com.thoughtworks.deepLearning.Differentiable._
import com.thoughtworks.deepLearning.array2D.utilities._
import com.thoughtworks.deepLearning.array2D.utilities.Array2DSemigroupBatch
import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4s.Implicits._


/**
  * @author 杨博 (Yang Bo) &lt;pop.atry@gmail.com&gt;
  */
final case class Weight(var rawValue: INDArray)(implicit learningRate: LearningRate)
    extends Differentiable
    with Array2DSemigroupBatch {
  override type Input = Batch
  override type Output = this.type

  override def value = Eval.now(rawValue)

  override def forward(any: Input) = this

  override def backward(delta: Delta): Unit = {
    rawValue -= delta.value * learningRate()
  }

  override def close(): Unit = {}

}