package com.ai;

import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;

public class HelloTensorFlow {

    public static void main(String... strs) throws Exception {

        try (Graph g = new Graph()) {
            final String value = "Hello from " + TensorFlow.version();
            // Construct the computation graph with a single operation, a constant
            // 使用单个操作（常量）构造计算图

            // named "MyConst" with a value "value". 名为“MyConst”的值为“value”。
            try (Tensor<?> t = Tensor.create(value.getBytes("UTF-8"))) {
                // The Java API doesn't yet include convenience functions for adding operations.
                // Java API尚未包含用于添加操作的便捷功能。
                g.opBuilder("Const", "MyConst").setAttr("dtype", t.dataType())
                        .setAttr("value", t).build();
            }

            try (Session s = new Session(g);
                 // Generally, there may be multiple output tensors,
                 // 通常，可能有多个输出张量，
                 // all of them must be closed to prevent resource leaks.
                 // 必须关闭所有这些以防止资源泄漏。
                 Tensor<?> output = s.runner().fetch("MyConst").run().get(0);
            ) {
                System.err.println(new String(output.bytesValue(), "UTF-8"));
            }
        }
    }
}
