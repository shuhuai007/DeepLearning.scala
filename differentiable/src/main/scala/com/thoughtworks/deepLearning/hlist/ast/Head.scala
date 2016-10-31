package com.thoughtworks.deepLearning.hlist.ast

import com.thoughtworks.deepLearning.{Batch, Differentiable}

/**
  * @author 杨博 (Yang Bo) &lt;pop.atry@gmail.com&gt;
  */
final case class Head[Input0 <: Batch, HeadData, HeadDelta, TailData <: shapeless.HList,
TailDelta <: shapeless.Coproduct](
    differentiableHCons: Differentiable.Aux[
      Input0,
      Batch.Aux[shapeless.::[HeadData, TailData], shapeless.:+:[HeadDelta, TailDelta]]]
) extends Differentiable {
  override type Input = Input0

  final class Output private[Head] (
      upstream: Batch.Aux[shapeless.::[HeadData, TailData], shapeless.:+:[HeadDelta, TailDelta]])
      extends Batch {
    override def backward(delta: Delta): Unit = {
      upstream.backward(shapeless.Inl(delta))
    }

    override def value: Data = {
      upstream.value.head
    }

    override type Data = HeadData
    override type Delta = HeadDelta

    override def close(): Unit = {
      upstream.close()
    }

  }

  override def forward(input: Input) = {
    new Output(differentiableHCons.forward(input))
  }
}
